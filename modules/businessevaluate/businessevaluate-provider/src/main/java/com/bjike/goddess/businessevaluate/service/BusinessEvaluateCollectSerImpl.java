package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.BusinessEvaluateCollectBO;
import com.bjike.goddess.businessevaluate.bo.EvaluateCollectTotalBO;
import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.entity.BusinessEvaluateCollect;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.ProblemDispose;
import com.bjike.goddess.businessevaluate.entity.ProjectCost;
import com.bjike.goddess.businessevaluate.enums.CollectIntervalType;
import com.bjike.goddess.businessevaluate.enums.CooperateWay;
import com.bjike.goddess.businessevaluate.enums.GuideAddrStatus;
import com.bjike.goddess.businessevaluate.enums.SendIntervalType;
import com.bjike.goddess.businessevaluate.to.BusinessEvaluateCollectTO;
import com.bjike.goddess.businessevaluate.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
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
 * 商务评估汇总业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class BusinessEvaluateCollectSerImpl extends ServiceImpl<BusinessEvaluateCollect, BusinessEvaluateCollectDTO> implements BusinessEvaluateCollectSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;
    @Autowired
    private ProblemDisposeSer problemDisposeSer;
    @Autowired
    private ProjectCostSer projectCostSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private MessageAPI messageAPI;


    @Override
    @Transactional(rollbackFor = SerException.class)
    public BusinessEvaluateCollectBO insertModel(BusinessEvaluateCollectTO to) throws SerException {
        String token = RpcTransmit.getUserToken();

        if (to.getSendIntervalType() == SendIntervalType.MINUTE && to.getSendInterval() < 30) {
            throw new SerException("汇总发送间隔不能低于30分钟!");
        }

        RpcTransmit.transmitUserToken(token);

        BusinessEvaluateCollect model = BeanTransform.copyProperties(to, BusinessEvaluateCollect.class, true);
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
        model.setOperateUser(userAPI.currentUser(token).getUsername());
        model.setLastSendTime(LocalDateTime.now());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, BusinessEvaluateCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BusinessEvaluateCollectBO updateModel(BusinessEvaluateCollectTO to) throws SerException {
        String token = RpcTransmit.getUserToken();

        if (to.getSendIntervalType() == SendIntervalType.MINUTE && to.getSendInterval() < 30) {
            throw new SerException("汇总发送间隔不能低于30分钟!");
        }

        RpcTransmit.transmitUserToken(token);

        if (!StringUtils.isEmpty(to.getId())) {
            BusinessEvaluateCollect model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                String[] users = to.getSendUsers();
                String sendUser = new String();
                for(String user : users){
                    sendUser = user + ","+sendUser;
                }
                sendUser.substring(0,sendUser.length()-1);
                model.setSendUser(sendUser);
                model.setOperateUser(userAPI.currentUser(token).getUsername());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, BusinessEvaluateCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {

        if (!StringUtils.isEmpty(id)) {
            BusinessEvaluateCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
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

        if (!StringUtils.isEmpty(id)) {
            BusinessEvaluateCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
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
    public List<BusinessEvaluateCollectBO> pageList(BusinessEvaluateCollectDTO dto) throws SerException {

        dto.getSorts().add("createTime=desc");
        List<BusinessEvaluateCollect> list = super.findByPage(dto);

        List<BusinessEvaluateCollectBO> boList = BeanTransform.copyProperties(list, BusinessEvaluateCollectBO.class);

        if (boList != null && !boList.isEmpty()) {
            for (BusinessEvaluateCollectBO bo : boList) {
                EvaluateProjectInfo info = evaluateProjectInfoSer.findById(bo.getProjectId());
                if (info != null) {
                    bo.setProject(info.getProject());
                }
            }
        }

        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<EvaluateCollectTotalBO> collectTotal(String area, String project) throws SerException {

        return collect(area, project, null, null);
    }

    public List<EvaluateCollectTotalBO> collect(String area, String projectId, LocalDateTime start, LocalDateTime end) throws SerException {
        EvaluateProjectInfoDTO dto = new EvaluateProjectInfoDTO();
        dto.getSorts().add("createTime=desc");
        String sql = "select distinct area  from businessevaluate_evaluateprojectinfo info where 0 = 0 ";
        if (!StringUtils.isEmpty(projectId)) {
            dto.getConditions().add(Restrict.eq("id", projectId));
            sql = sql + "and info.id = '" + projectId + "'";
        }
        if (!StringUtils.isEmpty(area)) {
            dto.getConditions().add(Restrict.eq("area", area));
            sql = sql + "and info.area = '" + area + "'";
        }

        if (start != null && end != null) {
            sql = sql + " and createTime > '" + DateUtil.dateToString(start) + "'";
            sql = sql + " and createTime < '" + DateUtil.dateToString(end) + "'";
            dto.getConditions().add(Restrict.gt("createTime", start));
            dto.getConditions().add(Restrict.lt("createTime", end));
        }

        //查询符合条件的地区
        List<EvaluateProjectInfo> areaList = evaluateProjectInfoSer.findBySql(sql, EvaluateProjectInfo.class, new String[]{"area"});
        //查询符合条件的项目信息
        List<EvaluateProjectInfo> infoList = evaluateProjectInfoSer.findByCis(dto);
        List<EvaluateCollectTotalBO> boList = BeanTransform.copyProperties(areaList, EvaluateCollectTotalBO.class);

        if (boList != null && !boList.isEmpty()) {

            if (infoList != null && !infoList.isEmpty()) {

                for (EvaluateCollectTotalBO bo : boList) {
                    Double areaTotalAmount = 0.0;
                    Double areaTotalCost = 0.0;
                    Double areaTotalTaxes = 0.0;
                    Double areaTotalManageCost = 0.0;
                    Double areaTotalProfit = 0.0;
                    Double areaTotalFee = 0.0;

                    Integer longtermCount = 0;
                    Integer itemCount = 0;
                    Integer agencyCount = 0;

                    Integer problemCount = 0;

                    List<String> projects = new ArrayList<String>();
                    for (EvaluateProjectInfo info : infoList) {

                        if (bo.getArea().equals(info.getArea())) {

                            if (!projects.contains(info.getProject())) {
                                projects.add(info.getProject());
                            }

                            //设置总金额、成本、税金、项目管理费、费用、项目利润
                            areaTotalAmount = areaTotalAmount + info.getTotalAmount();
                            areaTotalCost = areaTotalCost + evaluateProjectInfoSer.getCost(info.getId());
                            areaTotalTaxes = areaTotalTaxes + info.getTaxes();
                            areaTotalManageCost = areaTotalManageCost + info.getManageCost();

                            areaTotalFee = areaTotalFee + findProjectCost(info.getId());
                            areaTotalProfit = areaTotalProfit + (info.getTotalAmount() - evaluateProjectInfoSer.getCost(info.getId()) - info.getManageCost() - info.getTaxes() - areaTotalFee);

                            if (info.getCooperateWay() == CooperateWay.LONGTERM) {
                                longtermCount++;
                            } else if (info.getCooperateWay() == CooperateWay.ITEM) {
                                itemCount++;
                            } else if (info.getCooperateWay() == CooperateWay.AGENCY) {
                                agencyCount++;
                            }

                            //设置项目问题数量
                            problemCount = problemCount + findProblemCount(info.getId());
                        }
                    }

                    if (!projects.isEmpty()) {
                        if (projects.size() > 1) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < projects.size(); i++) {
                                if (i == projects.size() - 1) {
                                    sb.append(projects.get(i));
                                } else {
                                    sb.append(projects.get(i) + "、");
                                }
                            }
                            bo.setProject(sb.toString());
                        } else {
                            bo.setProject(projects.get(0));
                        }

                    }


                    bo.setProjectTotalAmount(areaTotalAmount);
                    bo.setProjectCost(areaTotalCost);
                    bo.setProjectTaxes(areaTotalTaxes);
                    bo.setProjectManageFee(areaTotalManageCost);
                    bo.setProjectProfit(areaTotalProfit);
                    bo.setProjectFee(areaTotalFee);
                    bo.setLongtermCount(longtermCount);
                    bo.setItemCount(itemCount);
                    bo.setAgencyCount(agencyCount);
                    bo.setProblemCount(problemCount);
                }
            }

            Double totalAmount = boList.stream().filter(p -> null != p.getProjectTotalAmount()).mapToDouble(p -> p.getProjectTotalAmount()).sum();
            Integer totalProblemCount = boList.stream().filter(p -> null != p.getProblemCount()).mapToInt(p -> p.getProblemCount()).sum();
            Double totalCost = boList.stream().filter(p -> null != p.getProjectCost()).mapToDouble(p -> p.getProjectCost()).sum();
            Double totalFee = boList.stream().filter(p -> null != p.getProjectFee()).mapToDouble(p -> p.getProjectFee()).sum();
            Double totalTaxes = boList.stream().filter(p -> null != p.getProjectTaxes()).mapToDouble(p -> p.getProjectTaxes()).sum();
            Double totalManageFee = boList.stream().filter(p -> null != p.getProjectManageFee()).mapToDouble(p -> p.getProjectManageFee()).sum();
            Double totalProfit = boList.stream().filter(p -> null != p.getProjectProfit()).mapToDouble(p -> p.getProjectProfit()).sum();
            Integer totalAgency = boList.stream().filter(p -> null != p.getAgencyCount()).mapToInt(p -> p.getAgencyCount()).sum();
            Integer totalItem = boList.stream().filter(p -> null != p.getItemCount()).mapToInt(p -> p.getItemCount()).sum();
            Integer totalLongtern = boList.stream().filter(p -> null != p.getLongtermCount()).mapToInt(p -> p.getLongtermCount()).sum();

            EvaluateCollectTotalBO totalBO = new EvaluateCollectTotalBO("合计", null, totalProblemCount, totalLongtern,
                    totalItem, totalAgency, totalCost, totalFee, totalTaxes, totalManageFee, totalProfit, totalAmount);

            boList.add(totalBO);
        }
        return boList;
    }

    //计算项目费用
    public Double findProjectCost(String id) throws SerException {
        ProjectCostDTO costDTO = new ProjectCostDTO();
        costDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        List<ProjectCost> list = projectCostSer.findByCis(costDTO);
        Double totalCost = 0.0;
        if (list != null && !list.isEmpty()) {
            Double cost = 0.0;
            for (ProjectCost model : list) {
                if (model.getAnother() != null) {
                    cost = cost + model.getServiceCost() + model.getEntertainCost() + model.getCommission() + model.getAnother();
                } else {
                    cost = cost + model.getServiceCost() + model.getEntertainCost() + model.getCommission();
                }

            }
            totalCost = cost;
        }
        return totalCost;
    }

    //计算项目问题数量
    public Integer findProblemCount(String id) throws SerException {
        ProblemDisposeDTO problemDTO = new ProblemDisposeDTO();
        problemDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        List<ProblemDispose> list = problemDisposeSer.findByCis(problemDTO);
        Integer problemSize = 0;
        if (list != null && !list.isEmpty()) {
            problemSize = list.size();
        }
        return problemSize;
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
                RpcTransmit.transmitUserToken(userToken);
                break;
            case ADD:
                flag = guideAddIdentity();
                RpcTransmit.transmitUserToken(userToken);
                break;
            case EDIT:
                flag = guideAddIdentity();
                RpcTransmit.transmitUserToken(userToken);
                break;
            case DELETE:
                flag = guideAddIdentity();
                RpcTransmit.transmitUserToken(userToken);
            case CONGEL:
                flag = guideAddIdentity();
                RpcTransmit.transmitUserToken(userToken);
                break;
            case COLLECT:
                flag = guideAddIdentity();
                RpcTransmit.transmitUserToken(userToken);
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
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

    //定时器，每10s轮询一次该接口
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void sendCollectEmail() throws SerException {
        BusinessEvaluateCollectDTO dto = new BusinessEvaluateCollectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<BusinessEvaluateCollect> list = super.findByCis(dto);
        //遍历所有未冻结汇总定时器,
        for (BusinessEvaluateCollect model : list) {
            validate(model);
            model.setLastSendTime(LocalDateTime.now());
        }
    }


    //校验是否满足发送条件
    public void validate(BusinessEvaluateCollect model) throws SerException {

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
    public void collectInterval(BusinessEvaluateCollect model) throws SerException {

        LocalDateTime start = null;
        LocalDateTime end = null;

        switch (model.getCollectInterval()) {

            case DAY:
                LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
                start = startOfDay;
                end = startOfDay.plusDays(1);
                break;

            case WEEK:
                LocalDateTime startOfWeek = DateUtil.getStartWeek().atStartOfDay();
                start = startOfWeek;
                end = startOfWeek.plusWeeks(1);
                break;

            case MONTH:
                LocalDateTime startOfMonth = DateUtil.getStartMonth().atStartOfDay();
                start = startOfMonth;
                end = startOfMonth.plusMonths(1);
                break;
        }

        List<EvaluateCollectTotalBO> list = collect(model.getArea(), model.getProjectId(), start, end);
        String[] sendUsers = model.getSendUser().split(",");
        sendEmail(model, list, sendUsers);
    }

    public void sendEmail(BusinessEvaluateCollect model, List<EvaluateCollectTotalBO> list, String[] sendUsers) throws SerException {

        String table = emailBody(list);

        String field = "";
        if (model.getCollectInterval() == CollectIntervalType.DAY) {
            field = "天";
        } else if (model.getCollectInterval() == CollectIntervalType.WEEK) {
            field = "周";
        } else if (model.getCollectInterval() == CollectIntervalType.MONTH) {
            field = "月";
        }

        String title = "商务评估汇总每" + field + "数据";

        MessageTO to = new MessageTO(title, table);
        to.setSendType(SendType.EMAIL);
        to.setReceivers(sendUsers);
        messageAPI.send(to);
    }

    //渲染邮件内容
    public String emailBody(List<EvaluateCollectTotalBO> list) throws SerException {

        StringBuffer sb = new StringBuffer();
        if (!CollectionUtils.isEmpty(list)) {
            list.get(list.size()-1).setProject("合计");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            sb.append("<tr>");

            sb.append("<td>地区</td>");
            sb.append("<td>项目</td>");
            sb.append("<td>项目问题数量</td>");
            sb.append("<td>长期合作项目数</td>");
            sb.append("<td>事项合作项目数</td>");
            sb.append("<td>中介合作项目数</td>");
            sb.append("<td>项目成本</td>");
            sb.append("<td>项目费用</td>");
            sb.append("<td>项目税金</td>");
            sb.append("<td>项目管理费</td>");
            sb.append("<td>项目利润</td>");
            sb.append("<td>项目总金额</td>");

            sb.append("<tr>");

            //拼body部分
            for (int i = 0; i < list.size(); i++) {

                if (i == list.size() - 1) {
                    sb.append("<tr bgcolor='yellow'>");
                } else {
                    sb.append("<tr>");
                }

                sb.append("<td>" + list.get(i).getArea() + "</td>");
                sb.append("<td>" + list.get(i).getProject() + "</td>");
                sb.append("<td>" + list.get(i).getProblemCount() + "</td>");
                sb.append("<td>" + list.get(i).getLongtermCount() + "</td>");
                sb.append("<td>" + list.get(i).getItemCount() + "</td>");
                sb.append("<td>" + list.get(i).getAgencyCount() + "</td>");
                sb.append("<td>" + list.get(i).getProjectCost() + "</td>");
                sb.append("<td>" + list.get(i).getProjectFee() + "</td>");
                sb.append("<td>" + list.get(i).getProjectTaxes() + "</td>");
                sb.append("<td>" + list.get(i).getProjectManageFee() + "</td>");
                sb.append("<td>" + list.get(i).getProjectProfit() + "</td>");
                sb.append("<td>" + list.get(i).getProjectTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }
}