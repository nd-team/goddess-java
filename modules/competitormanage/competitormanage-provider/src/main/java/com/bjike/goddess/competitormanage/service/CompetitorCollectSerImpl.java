package com.bjike.goddess.competitormanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.competitormanage.bo.CollectionTotalBO;
import com.bjike.goddess.competitormanage.bo.CompetitorCollectBO;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.entity.Competitor;
import com.bjike.goddess.competitormanage.entity.CompetitorCollect;
import com.bjike.goddess.competitormanage.enums.BusinessType;
import com.bjike.goddess.competitormanage.enums.CollectIntervalType;
import com.bjike.goddess.competitormanage.enums.GuideAddrStatus;
import com.bjike.goddess.competitormanage.enums.SendIntervalType;
import com.bjike.goddess.competitormanage.to.CompetitorCollectTO;
import com.bjike.goddess.competitormanage.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 竞争对手汇总业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "competitormanageSerCache")
@Service
public class CompetitorCollectSerImpl extends ServiceImpl<CompetitorCollect, CompetitorCollectDTO> implements CompetitorCollectSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CompetitorSer competitorSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private MessageAPI messageAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorCollectBO saveModel(CompetitorCollectTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();

        getCusPermission();

        if (to.getSendIntervalType() == SendIntervalType.MINUTE && to.getSendInterval() < 30) {
            throw new SerException("汇总发送间隔不能低于30分钟!");
        }

        RpcTransmit.transmitUserToken(userToken);

        CompetitorCollect model = BeanTransform.copyProperties(to, CompetitorCollect.class, true);
        if (to.getSendUsers() != null && to.getSendUsers().length > 0) {
            StringBuilder sendUser = new StringBuilder();
            for (int i = 0; i < to.getSendUsers().length; i++) {
                if (i == to.getSendUsers().length - 1) {
                    sendUser.append(to.getSendUsers()[i]);
                } else {
                    sendUser.append(to.getSendUsers()[i] + ",");
                }
            }
            model.setSendUser(sendUser.toString());
        } else {
            throw new SerException("发送对象不能为空!");
        }
        model.setOperateUser(userAPI.currentUser().getUsername());
        //新记录设置上次发送时间为创建时间
        model.setLastSendTime(LocalDateTime.now());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, CompetitorCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorCollectBO editModel(CompetitorCollectTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        getCusPermission();

        if (to.getSendIntervalType() == SendIntervalType.MINUTE && to.getSendInterval() < 30) {
            throw new SerException("汇总发送间隔不能低于30分钟!");
        }

        RpcTransmit.transmitUserToken(userToken);
        if (!StringUtils.isEmpty(to.getId())) {
            CompetitorCollect model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                model.setOperateUser(userAPI.currentUser().getUsername());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, CompetitorCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        getCusPermission();
        RpcTransmit.transmitUserToken(userToken);
        if (!StringUtils.isEmpty(id)) {
            CompetitorCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                model.setOperateUser(userAPI.currentUser().getUsername());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        getCusPermission();
        RpcTransmit.transmitUserToken(userToken);
        if (!StringUtils.isEmpty(id)) {
            CompetitorCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                model.setOperateUser(userAPI.currentUser().getUsername());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<CollectionTotalBO> collectionTotal() throws SerException {

        getCusPermission();
        return collect(null, null);
    }

    public List<CollectionTotalBO> collect(LocalDateTime start, LocalDateTime end) throws SerException {

        CompetitorDTO dto = new CompetitorDTO();

        //查询地区
        StringBuilder sb = new StringBuilder("select distinct area  from competitormanage_competitor where 0 = 0");
        if (start != null && end != null) {
            sb.append(" and createTime > '" + DateUtil.dateToString(start) + "'");
            sb.append(" and createTime < '" + DateUtil.dateToString(end) + "'");
            dto.getConditions().add(Restrict.gt("createTime", start));
            dto.getConditions().add(Restrict.lt("createTime", end));
        }

        List<Competitor> competitorList = competitorSer.findBySql(sb.toString(), Competitor.class, new String[]{"area"});

        List<Competitor> list = competitorSer.findByCis(dto);
        List<CollectionTotalBO> returnList = new ArrayList<CollectionTotalBO>();

        //遍历地区
        for (Competitor competitor : competitorList) {
            CollectionTotalBO bo = new CollectionTotalBO();
            bo.setArea(competitor.getArea());

            Long areaCommunicate = 0l;
            Long areaSoftware = 0l;
            Long areaMarketingplan = 0l;
            Long areaIntelligentize = 0l;
            Long areaElectroniccommerce = 0l;
            Long areaRealty = 0l;
            Long areaFinancial = 0l;
            Long areaFood = 0l;

            for (Competitor model : list) {
                if (competitor.getArea().equals(model.getArea())) {

                    if (model.getBusinessType() == BusinessType.COMMUNICATE) {
                        areaCommunicate++;
                    } else if (model.getBusinessType() == BusinessType.SOFTWARE) {
                        areaSoftware++;
                    } else if (model.getBusinessType() == BusinessType.MARKETINGPLAN) {
                        areaMarketingplan++;
                    } else if (model.getBusinessType() == BusinessType.INTELLIGENTIZE) {
                        areaIntelligentize++;
                    } else if (model.getBusinessType() == BusinessType.ELECTRONICCOMMERCE) {
                        areaElectroniccommerce++;
                    } else if (model.getBusinessType() == BusinessType.REALTY) {
                        areaRealty++;
                    } else if (model.getBusinessType() == BusinessType.FINANCIAL) {
                        areaFinancial++;
                    } else if (model.getBusinessType() == BusinessType.FOOD) {
                        areaFood++;
                    }
                }
                bo.setCommunicate(areaCommunicate);
                bo.setSoftware(areaSoftware);
                bo.setMarketingplan(areaMarketingplan);
                bo.setIntelligentize(areaIntelligentize);
                bo.setElectroniccommerce(areaElectroniccommerce);
                bo.setRealty(areaRealty);
                bo.setFinancial(areaFinancial);
                bo.setFood(areaFood);
            }
            returnList.add(bo);
        }

        if (!returnList.isEmpty()) {

            Long toalCommunicate = returnList.stream().filter(p -> null != p.getCommunicate()).mapToLong(p -> p.getCommunicate()).sum();
            Long toalSoftware = returnList.stream().filter(p -> null != p.getSoftware()).mapToLong(p -> p.getSoftware()).sum();
            Long toalMarketingplan = returnList.stream().filter(p -> null != p.getMarketingplan()).mapToLong(p -> p.getMarketingplan()).sum();
            Long toalIntelligentize = returnList.stream().filter(p -> null != p.getIntelligentize()).mapToLong(p -> p.getIntelligentize()).sum();
            Long toalElectroniccommerce = returnList.stream().filter(p -> null != p.getElectroniccommerce()).mapToLong(p -> p.getElectroniccommerce()).sum();
            Long toalRealty = returnList.stream().filter(p -> null != p.getRealty()).mapToLong(p -> p.getRealty()).sum();
            Long toalFinancial = returnList.stream().filter(p -> null != p.getFinancial()).mapToLong(p -> p.getFinancial()).sum();
            Long toalFood = returnList.stream().filter(p -> null != p.getFood()).mapToLong(p -> p.getFood()).sum();

            CollectionTotalBO totalBO = new CollectionTotalBO("合计", toalCommunicate, toalSoftware, toalMarketingplan, toalIntelligentize,
                    toalElectroniccommerce, toalRealty, toalFinancial, toalFood);
            returnList.add(totalBO);
        } else {
            CollectionTotalBO totalBO = new CollectionTotalBO("合计", 0l, 0l, 0l, 0l,
                    0l, 0l, 0l, 0l);
            returnList.add(totalBO);
        }

        return returnList;
    }

    //定时器，每10s轮询一次该接口
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void sendCollectEmail() throws SerException {
        CompetitorCollectDTO dto = new CompetitorCollectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<CompetitorCollect> list = super.findByCis(dto);
        //遍历所有未冻结汇总定时器,
        for (CompetitorCollect model : list) {
            validate(model);
        }
    }

    @Override
    public Boolean sonPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = sonPermission();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    //校验是否满足发送条件
    public void validate(CompetitorCollect model) throws SerException {

        switch (model.getSendIntervalType()) {
            case MINUTE:
                if (LocalDateTime.now().minusMinutes(model.getSendInterval()).compareTo(model.getLastSendTime()) > 0) {
                    collectInterval(model);
                }
                break;

            case HOUR:
                if (LocalDateTime.now().minusHours(model.getSendInterval()).compareTo(model.getLastSendTime()) > 0) {
                    collectInterval(model);
                }
                break;

            case DAY:
                if (LocalDateTime.now().minusDays(model.getSendInterval()).compareTo(model.getLastSendTime()) > 0) {
                    collectInterval(model);
                }
                break;

            case WEEK:
                if (LocalDateTime.now().minusWeeks(model.getSendInterval()).compareTo(model.getLastSendTime()) > 0) {
                    collectInterval(model);
                }
                break;

            case MONTH:
                if (LocalDateTime.now().minusMonths(model.getSendInterval()).compareTo(model.getLastSendTime()) > 0) {
                    collectInterval(model);
                }
                break;

            case QUARTER:
                //满足一个季度
                if (LocalDateTime.now().minusMonths(model.getSendInterval() * 3).compareTo(model.getLastSendTime()) > 0) {
                    collectInterval(model);
                }
                break;

            case YEAR:
                if (LocalDateTime.now().minusYears(model.getSendInterval()).compareTo(model.getLastSendTime()) > 0) {
                    collectInterval(model);
                }
                break;
        }
    }

    //查询指定汇总间隔的数据
    public void collectInterval(CompetitorCollect model) throws SerException {

        LocalDateTime start = null;
        LocalDateTime end = null;

        switch (model.getCollectInterval()) {

            case ONEDAY:
                LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
                start = startOfDay;
                end = startOfDay.plusDays(1);
                break;

            case ONEWEEK:
                LocalDateTime startOfWeek = DateUtil.getStartWeek().atStartOfDay();
                start = startOfWeek;
                end = startOfWeek.plusWeeks(1);
                break;

            case ONEMONTH:
                LocalDateTime startOfMonth = DateUtil.getStartMonth().atStartOfDay();
                start = startOfMonth;
                end = startOfMonth.plusMonths(1);
                break;
        }

        List<CollectionTotalBO> list = collect(start, end);
        String[] sendUsers = model.getSendUser().split(",");
        sendEmail(model, list, sendUsers);
    }

    public void sendEmail(CompetitorCollect model, List<CollectionTotalBO> list, String[] sendUsers) throws SerException {

        String table = emailBody(list);

        String field = "";
        if (model.getCollectInterval() == CollectIntervalType.ONEDAY) {
            field = "天";
        } else if (model.getCollectInterval() == CollectIntervalType.ONEWEEK) {
            field = "周";
        } else if (model.getCollectInterval() == CollectIntervalType.ONEMONTH) {
            field = "月";
        }

        MessageTO to = new MessageTO("竞争对手管理汇总每" + field + "数据", table);
        to.setSendType(SendType.EMAIL);
        to.setReceivers(sendUsers);
//        messageAPI.send(to);
    }

    //渲染邮件内容
    public String emailBody(List<CollectionTotalBO> list) throws SerException {

        StringBuffer sb = new StringBuffer();
        if (!CollectionUtils.isEmpty(list)) {
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            sb.append("<tr>");

            sb.append("<td>地区</td>");
            sb.append("<td>通讯类</td>");
            sb.append("<td>软件类</td>");
            sb.append("<td>营销策划类</td>");
            sb.append("<td>智能化类</td>");
            sb.append("<td>电子商务类</td>");
            sb.append("<td>房地产类</td>");
            sb.append("<td>理财类</td>");
            sb.append("<td>食品类</td>");

            sb.append("<tr>");

            //拼body部分
            for (int i = 0; i < list.size(); i++) {

                if (i == list.size() - 1) {
                    sb.append("<tr bgcolor='yellow'>");
                } else {
                    sb.append("<tr>");
                }

                sb.append("<td>" + list.get(i).getArea() + "</td>");
                sb.append("<td>" + list.get(i).getCommunicate() + "</td>");
                sb.append("<td>" + list.get(i).getSoftware() + "</td>");
                sb.append("<td>" + list.get(i).getMarketingplan() + "</td>");
                sb.append("<td>" + list.get(i).getIntelligentize() + "</td>");
                sb.append("<td>" + list.get(i).getElectroniccommerce() + "</td>");
                sb.append("<td>" + list.get(i).getRealty() + "</td>");
                sb.append("<td>" + list.get(i).getFinancial() + "</td>");
                sb.append("<td>" + list.get(i).getFood() + "</td>");

                sb.append("<tr>");
            }
            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<CompetitorCollectBO> pageList(CompetitorCollectDTO dto) throws SerException {
        getCusPermission();
        dto.getSorts().add("createTime=desc");
        List<CompetitorCollect> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, CompetitorCollectBO.class);
    }

    public void getCusPermission() throws SerException {

        Boolean permission = cusPermissionSer.getCusPermission("1");

        if (!permission) {
            throw new SerException("该模块只有商务模块负责人可操作，您的帐号尚无没有权限");
        }
    }
}