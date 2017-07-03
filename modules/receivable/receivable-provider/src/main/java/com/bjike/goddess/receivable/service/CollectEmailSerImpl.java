package com.bjike.goddess.receivable.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.receivable.bo.CollectEmailBO;
import com.bjike.goddess.receivable.dto.CollectEmailDTO;
import com.bjike.goddess.receivable.entity.CollectEmail;
import com.bjike.goddess.receivable.enums.CollectSendUnit;
import com.bjike.goddess.receivable.enums.GuideAddrStatus;
import com.bjike.goddess.receivable.to.CollectEmailTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * 回款管理邮件发送定制业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 回款管理邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "receivableSerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CollectEmailSer collectEmailSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        Long count = super.count(collectEmailDTO);
        return count;
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        CollectEmail selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability, CollectEmailBO.class);

    }

    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        //checkSeeIdentity();

        collectEmailDTO.getSorts().add("createTime=desc");
        List<CollectEmail> list = super.findByPage(collectEmailDTO);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
        //checkAddIdentity();
        RpcTransmit.transmitUserToken(useToken);

        if (collectEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }
        if (collectEmailTO.getSendNum() < 30 && collectEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (collectEmailTO.getSendNum() > collectEmailTO.getSendNum().longValue() && collectEmailTO.getSendNum() < (collectEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱输入不正确");
                }
                emails.append(emailStr + ";");
            }
        }
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        if (null == collectEmail.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置汇总条件
        StringBuffer condiSb = new StringBuffer("");
        String[] condis = collectEmailTO.getCondis();
        if (condis != null && condis.length >= 0) {
            for (String condiStr : condis) {
                condiSb.append(condiStr + ";");
            }
            collectEmail.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
        } else {
            throw new SerException("发送条件不能为空");
        }

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        collectEmail.setLastSendTime(LocalDateTime.now());


        super.save(collectEmail);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
        //checkAddIdentity();
        RpcTransmit.transmitUserToken(useToken);

        if (collectEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }

        if (collectEmailTO.getSendNum() < 30 && collectEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (collectEmailTO.getSendNum() > collectEmailTO.getSendNum().longValue() && collectEmailTO.getSendNum() < (collectEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱输入不正确");
                }
                emails.append(emailStr + ";");
            }
        }
        CollectEmail temp = super.findById(collectEmailTO.getId());
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);

        BeanUtils.copyProperties(collectEmail, temp, "id", "createTime", "createPersion", "lastSendTime", "status");
        temp.setModifyTime(LocalDateTime.now());
        temp.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置汇总条件
        StringBuffer condiSb = new StringBuffer("");
        String[] condis = collectEmailTO.getCondis();
        if (condis != null && condis.length >= 0) {
            for (String condiStr : condis) {
                condiSb.append(condiStr + ";");
            }
            temp.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
        } else {
            throw new SerException("发送条件不能为空");
        }


        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
       // checkAddIdentity();

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {
        //checkAddIdentity();

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
        //checkAddIdentity();

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }


    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "小时";
                break;
            case 2:
                unit = "天";
                break;
            case 3:
                unit = "周";
                break;
            case 4:
                unit = "月";
                break;
            case 5:
                unit = "季度";
                break;
            case 6:
                unit = "年";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }

    @Override
    public void checkSendEmail() throws SerException {
        List<CollectEmail> allEmails = new ArrayList<>();
        List<CollectEmail> marketEmails = new ArrayList<>();

        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        CollectEmailDTO dto = new CollectEmailDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<CollectEmail> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (CollectEmail str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastSendTime();
            //发送间隔
            Double sendNum = str.getSendNum();
            //发送单位
            CollectSendUnit collectSendUnit = str.getCollectSendUnit();
            //发送类型
            String type = str.getType();
            //发送对象;隔开
            String sendObject = str.getSendObject();

            Long mis = nowTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - lastTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Double temp_sendNum = 0d;
            Boolean flag = false;
            switch (collectSendUnit) {
                case MINUTE:
                    //毫秒数
                    temp_sendNum = sendNum * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case HOURS:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3 * sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3 * sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
            }

            if (flag && type.equals("回款管理汇总")) {
                marketEmails.add(str);
                allEmails.add(str);
            }


        }

        //调用发邮件
 //       allEmails = sendObject(marketEmails);

        //修改上次发送时间
        super.update(allEmails);

    }


//    private String htmlMarket(List<MarketCollectBO> marketBOS) throws SerException {
//        StringBuffer sb = new StringBuffer("");
//        if (marketBOS != null && marketBOS.size() > 0) {
//            sb = new StringBuffer("<h4>市场信息汇总:</h4>");
//            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
//            //拼表头
//            MarketCollectBO title = marketBOS.get(marketBOS.size() - 1);
//            sb.append("<tr>");
//            sb.append("<td>地区</td>");
//            sb.append("<td>移动通信行业市场信息数量</td>");
//            sb.append("<td>软件开发行业市场信息数量</td>");
//            sb.append("<td>智能系统集成行业市场信息数量</td>");
//            sb.append("<td>策划与营销方案行业市场信息数量</td>");
//            sb.append("<td>项目级别-A级市场信息数量</td>");
//            sb.append("<td>项目级别-B级市场信息数量</td>");
//            sb.append("<td>项目级别-C级市场信息数量</td>");
//            sb.append("<td>项目级别-D级市场信息数量</td>");
//            sb.append("<td>有效信息市场数量</td>");
//            sb.append("<td>无效信息市场数量</td>");
//            sb.append("<td>新项目市场信息数量</td>");
//            sb.append("<td>已有项目or进行中项目市场信息数量</td>");
//            sb.append("<tr>");
//
//            //拼body部分
//            for (MarketCollectBO bo : marketBOS) {
//                sb.append("<tr>");
//                sb.append("<td>" + (StringUtils.isBlank(bo.getArea())? "" : bo.getArea()) + "</td>");
//                sb.append("<td>" + (null == bo.getMobile() ? "" : bo.getMobile()) + "</td>");
//                sb.append("<td>" + (null == bo.getSoft() ? "" : bo.getSoft()) + "</td>");
//                sb.append("<td>" + (null == bo.getSystem() ? "" : bo.getSystem()) + "</td>");
//                sb.append("<td>" + (null == bo.getPlan() ? "" : bo.getPlan()) + "</td>");
//                sb.append("<td>" + (null == bo.getFirst()? "" :bo.getFirst()) + "</td>");
//                sb.append("<td>" + (null == bo.getSecond() ?"" : bo.getSecond()) + "</td>");
//                sb.append("<td>" + (null == bo.getThird() ?"" : bo.getThird()) + "</td>");
//                sb.append("<td>" + (null == bo.getFourth() ?"" : bo.getFourth()) + "</td>");
//                sb.append("<td>" + (null == bo.getHas() ?"" : bo.getHas()) + "</td>");
//                sb.append("<td>" + (null == bo.getNotHas() ?"" : bo.getNotHas()) + "</td>");
//                sb.append("<td>" + (null == bo.getFresh() ?"" : bo.getFresh()) + "</td>");
//                sb.append("<td>" + (null == bo.getOld() ?"" : bo.getOld()) + "</td>");
//
//                sb.append("<tr>");
//            }
//
//            //结束
//            sb.append("</table>");
//        }
//        return sb.toString();
//    }
//
//    private List<CollectEmail> sendObject(List<CollectEmail> marketEmails) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        List<CollectEmail> allEmails = new ArrayList<>();
//        //招投信息汇总
//        RpcTransmit.transmitUserToken(userToken);
//        if (marketEmails != null && marketEmails.size() > 0) {
//            for (CollectEmail market : marketEmails) {
//                String[] condis = market.getCondi().split(";");
//                List<MarketCollectBO> marketCollectBOS = collectEmailSer.marketCollect(condis);
//                //拼表格
//                String content = htmlMarket(marketCollectBOS);
//                MessageTO messageTO = new MessageTO();
//                messageTO.setContent(content);
//                messageTO.setTitle("定时发送市场息汇总");
//                messageTO.setMsgType(MsgType.SYS);
//                messageTO.setSendType(SendType.EMAIL);
//                messageTO.setRangeType(RangeType.SPECIFIED);
//                //定时发送必须写
//                messageTO.setSenderId("SYSTEM");
//                messageTO.setSenderName("SYSTEM");
//
//                messageTO.setReceivers(market.getSendObject().split(";"));
//                messageAPI.send(messageTO);
//
//                market.setLastSendTime(LocalDateTime.now());
//                market.setModifyTime(LocalDateTime.now());
//                allEmails.add(market);
//            }
//        }
//        return allEmails;
//
//    }

}