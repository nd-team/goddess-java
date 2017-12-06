package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.projectprocing.bo.*;
import com.bjike.goddess.projectprocing.dto.NotificationFormulaDTO;
import com.bjike.goddess.projectprocing.entity.NotificationFormula;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.enums.SummTableName;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.NotificationFormulaTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 通报机制制定业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:24 ]
 * @Description: [ 通报机制制定业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class NotificationFormulaSerImpl extends ServiceImpl<NotificationFormula, NotificationFormulaDTO> implements NotificationFormulaSer {
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SettleProgressManageSer settleProgressManageSer;
    @Autowired
    private SettleWorkProgreManageSer settleWorkProgreManageSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countNotifi(NotificationFormulaDTO notificationFormulaDTO) throws SerException {
        Long count = super.count(notificationFormulaDTO);
        return count;
    }

    @Override
    public NotificationFormulaBO getOne(String id) throws SerException {
        NotificationFormula notificationFormula = super.findById(id);
        return BeanTransform.copyProperties(notificationFormula, NotificationFormulaBO.class);
    }

    @Override
    public List<NotificationFormulaBO> listCollectEmail(NotificationFormulaDTO notificationFormulaDTO) throws SerException {
        checkPermission();
        List<NotificationFormula> notificationFormulaList = super.findByCis(notificationFormulaDTO);
        return BeanTransform.copyProperties(notificationFormulaList, NotificationFormulaBO.class);
    }

    @Override
    public NotificationFormulaBO addCollectEmail(NotificationFormulaTO notificationFormulaTO) throws SerException {
        checkPermission();
        List<String> sendObjectList = new ArrayList<>();
        if (notificationFormulaTO.getSendSectoral() == null || !notificationFormulaTO.getSendSectoral()) {
            sendObjectList = notificationFormulaTO.getSendObjects();
        } else {
            List<String> users = new ArrayList<>();
            List<UserBO> allUser = positionDetailUserAPI.findUserListInOrgan();
            for (int i = 0; i < allUser.size(); i++) {
                users.add(allUser.get(i).getUsername());
            }
            String[] user_str = new String[users.size()];
            user_str = users.toArray(user_str);
            sendObjectList = internalContactsAPI.getEmails(user_str);
        }
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        String recivers = emails.substring(0, emails.toString().lastIndexOf(","));
        NotificationFormula notificationFormula = BeanTransform.copyProperties(notificationFormulaTO, NotificationFormula.class, true, "sendObjects");
        notificationFormula.setCreateTime(LocalDateTime.now());
        notificationFormula.setStatus(Status.THAW);
        notificationFormula.setModifier(userAPI.currentUser().getUsername());
        //设置发送对象
        notificationFormula.setSendObject(recivers);


        super.save(notificationFormula);

        return BeanTransform.copyProperties(notificationFormula, NotificationFormulaBO.class);
    }

    @Override
    public NotificationFormulaBO editCollectEmail(NotificationFormulaTO notificationFormulaTO) throws SerException {
        checkPermission();
        List<String> sendObjectList = new ArrayList<>();
        if (notificationFormulaTO.getSendSectoral() == null || !notificationFormulaTO.getSendSectoral()) {
            sendObjectList = notificationFormulaTO.getSendObjects();
        } else {
            List<String> users = new ArrayList<>();
            List<UserBO> allUser = positionDetailUserAPI.findUserListInOrgan();
            for (int i = 0; i < allUser.size(); i++) {
                users.add(allUser.get(i).getUsername());
            }
            String[] user_str = new String[users.size()];
            user_str = users.toArray(user_str);
            sendObjectList = internalContactsAPI.getEmails(user_str);
        }
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        String recivers = emails.substring(0, emails.toString().lastIndexOf(","));
        NotificationFormula temp = super.findById(notificationFormulaTO.getId());

        NotificationFormula buySendEmail = BeanTransform.copyProperties(notificationFormulaTO, NotificationFormula.class, true, "sendObject");
        //设置发送对象
        buySendEmail.setSendObject(recivers);

        BeanUtils.copyProperties(buySendEmail, temp, "id", "createTime", "modifier", "status");
        temp.setModifyTime(LocalDateTime.now());
        temp.setModifier(userAPI.currentUser().getUsername());


        super.update(temp);
        return BeanTransform.copyProperties(temp, NotificationFormulaBO.class);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        checkPermission();
        NotificationFormula notificationFormula = super.findById(id);
        notificationFormula.setStatus(Status.CONGEAL);
        super.update(notificationFormula);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        checkPermission();
        NotificationFormula notificationFormula = super.findById(id);
        notificationFormula.setStatus(Status.THAW);
        super.update(notificationFormula);
    }

    @Override
    public void checkEmail() throws SerException {
        NotificationFormulaDTO notificationFormulaDTO = new NotificationFormulaDTO();
        notificationFormulaDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<NotificationFormula> list = super.findByCis(notificationFormulaDTO);
        if (list != null && list.size() > 0) {
            for (NotificationFormula notificationFormula : list) {
                switch (notificationFormula.getSendFrequency()) {
                    case EVERYDAY:
                        LocalDateTime sendTimeNode = notificationFormula.getSendTimeNode();
                        if (sendTimeNode.getHour() == LocalDateTime.now().getHour() && sendTimeNode.getMinute() == LocalDateTime.now().getMinute()) {
                            sendEmail(notificationFormula);
                        }
                        break;
                    case EVERYWEEK:
                        if (notificationFormula.getSendTimeNode().getDayOfWeek().getValue() == LocalDate.now().getDayOfWeek().getValue()) {
                            if (notificationFormula.getSendTimeNode().getHour() == LocalDateTime.now().getHour() && notificationFormula.getSendTimeNode().getMinute() == LocalDateTime.now().getMinute()) {
                                sendEmail(notificationFormula);
                            }
                        }
                        break;
                    case EVERYMONTH:
                        if (notificationFormula.getSendTimeNode().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
                            if (notificationFormula.getSendTimeNode().getHour() == LocalDateTime.now().getHour() && notificationFormula.getSendTimeNode().getMinute() == LocalDateTime.now().getMinute()) {
                                sendEmail(notificationFormula);
                            }
                        }
                        break;
                    case EVERYYEAR:
                        if (notificationFormula.getSendTimeNode().getMonthValue() == LocalDate.now().getMonthValue() && notificationFormula.getSendTimeNode().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
                            if (notificationFormula.getSendTimeNode().getHour() == LocalDateTime.now().getHour() && notificationFormula.getSendTimeNode().getMinute() == LocalDateTime.now().getMinute()) {
                                sendEmail(notificationFormula);
                            }
                        }
                        break;
                }
            }
        }
    }

    private void sendEmail(NotificationFormula notificationFormula) throws SerException {

        //拼表格
        String content = htmlSign(notificationFormula);

        if (StringUtils.isNotBlank(notificationFormula.getSendObject())) {
            MessageTO messageTO = new MessageTO();
            messageTO.setContent(content);
            messageTO.setTitle("定时发送项目结算验收签字汇总");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
//        messageTO.setRangeType(RangeType.SPECIFIED);
            //定时发送必须写
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");


            messageTO.setReceivers(notificationFormula.getSendObject().split(","));
            messageAPI.send(messageTO);
        }
    }

    private String htmlSign(NotificationFormula notificationFormula) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (notificationFormula.getSummTableName() == SummTableName.BALANCE) {
            //结算进度管理汇总
            List<SettleProgressManageSummBO> settleProgressManageSummBOS = settleProgressManageSer.settleProgressManageSumm();
            sb = new StringBuffer("<h4>结算进度管理汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");

            sb.append("<tr>");
            sb.append("<td>所属项目组</td>");
            sb.append("<td>总合同数量</td>");
            sb.append("<td>总金额/万</td>");
            sb.append("<td>已完工数量</td>");
            sb.append("<td>已完工金额</td>");
            sb.append("<td>未完工数量</td>");
            sb.append("<td>未完工金额</td>");
            sb.append("<td>已完工已启动结算</td>");
            sb.append("<td>已完工已启动结算金额</td>");
            sb.append("<td>已完工未启动结算</td>");
            sb.append("<td>已完工未启动结算金额</td>");
            sb.append("<td>未完工已启动结算</td>");
            sb.append("<td>未完工已启动结算金额</td>");
            sb.append("<td>未完工未结算</td>");
            sb.append("<td>未完工未结算金额</td>");
            sb.append("<td>已回款单数</td>");
            sb.append("<td>已回款金额</td>");
            sb.append("<td>未回款单数</td>");
            sb.append("<td>未回款金额</td>");

            sb.append("</tr>");

            //拼body部分
            if (settleProgressManageSummBOS != null && settleProgressManageSummBOS.size() > 0) {
                for (SettleProgressManageSummBO settleProgressManageSummBO : settleProgressManageSummBOS) {
                    sb.append("<tr>");
                    sb.append("<td>" + settleProgressManageSummBO.getProject() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getContractTotal() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getAmountTotal() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getCompletedCount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getCompletedAmount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getUncompletedCount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getUncompletedAmount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getSettleCompletedStart() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getSettleCompletedStartAmount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getSettleCompletedNoStart() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getSettleCompletedNoStartAmount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getSettleUnCompletedStart() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getSettleUnCompletedStartAmount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getUnfinishedSettled() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getUnfinishedSettledAmount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getReturnedItemsNum() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getReturnedItemsAmount() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getNoReturnSingular() + "</td>");
                    sb.append("<td>" + settleProgressManageSummBO.getNoReturnSingularAmount() + "</td>");

                    sb.append("</tr>");
                }
            }

            //结束
            sb.append("</table>");
        } else if (notificationFormula.getSummTableName() == SummTableName.SCHEDULE) {
            //结算工作进度汇总
            PersonalTasksSummBO personalTasksSummBO = settleWorkProgreManageSer.personalSummDay(String.valueOf(LocalDate.now()));
            sb = new StringBuffer("<h4>结算工作进度汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            if (personalTasksSummBO != null) {
                sb.append("<tr>");
                for (String heard : personalTasksSummBO.getHeaderData()) {
                    sb.append("<td>" + (StringUtils.isBlank(heard) ? "" : heard) + "</td>");
                }
                sb.append("</tr>");

                //拼body部分
                if (personalTasksSummBO.getPeoperDataBOList() != null && personalTasksSummBO.getPeoperDataBOList().size() > 0) {
                    for (PeoperDataBO peoperDataBO : personalTasksSummBO.getPeoperDataBOList()) {
                        int i = 0;
                        for (TypeDataBO typeDataBO : peoperDataBO.getTypeDataBOS()) {
                            sb.append("<tr>");
                            if (i == 0) {
                                sb.append("<td rowspan=\"3\">" + (StringUtils.isBlank(peoperDataBO.getRespPerson()) ? "" : peoperDataBO.getRespPerson()) + "</td>");
                            }
                            sb.append("<td>" + typeDataBO.getType() + "</td>");
                            for (Integer num : typeDataBO.getNumber()) {
                                sb.append("<td>" + num + "</td>");
                            }
                            sb.append("</tr>");
                            i++;
                        }

                    }
                }
            }

            //结束
            sb.append("</table>");

        } else if (notificationFormula.getSummTableName() == SummTableName.COLLECT) {
            //结算进度汇总模板
            List<SettleProgressSummBO> settleProgressSummBOList = settleProgressManageSer.settleProgress(null, null);
            sb = new StringBuffer("<h4>结算进度汇总模板:</h4>");
            sb.append("<table width=\"2500px\" border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");

            sb.append("<tr>");
            sb.append("<td width=\"3%\">地区</td>");
            sb.append("<td width=\"3%\">统计</td>");
            sb.append("<td width=\"6%\">外包单位</td>");
            sb.append("<td width=\"6%\">总计</td>");
            sb.append("<td width=\"6%\">派工情况</td>");
            sb.append("<td width=\"6%\">汇总</td>");
            sb.append("<td width=\"6%\">完工状态</td>");
            sb.append("<td width=\"6%\">合计</td>");
            sb.append("<td width=\"6%\">验收情况</td>");
            sb.append("<td width=\"6%\">计数</td>");

            sb.append("</tr>");

            //拼body部分
            if (settleProgressSummBOList != null && settleProgressSummBOList.size() > 0) {
                Boolean i = true;
                for (int a = 0; a < settleProgressSummBOList.size(); a++) {
                    Boolean j = true;
                    Boolean s = true;
                    List<OutUnitSummBO> outUnitSummBOList = settleProgressSummBOList.get(a).getOutUnitSummBOS();
                    for (int b = 0; b < outUnitSummBOList.size(); b++) {
                        Boolean k = true;
                        List<DispatchingConditionBO> dispatchingConditionBOList = outUnitSummBOList.get(b).getDispatchingConditionBOS();
                        List<NodeDataBO> nodeDataBOList = outUnitSummBOList.get(b).getNodeDataBOS();
                        for (int c = 0; c < dispatchingConditionBOList.size(); c++) {
                            Boolean l = true;
                            List<CompletionStateBO> completionStateBOList = dispatchingConditionBOList.get(c).getCompletionStateBOS();
                            for (int d = 0; d < completionStateBOList.size(); d++) {
                                List<AcceptSituationBO> acceptSituationBOList = completionStateBOList.get(d).getAcceptSituationBOS();
                                for (int e = 0; e < 6; e++) {
                                    sb.append("<tr>");
                                    if (i) {
                                        i = false;
                                        sb.append("<td  rowspan=\"" + settleProgressSummBOList.get(a).getOutUnitSummBOS().size() * 2 * 2 * 6 + "\">" + settleProgressSummBOList.get(a).getArea() + "</td>");
                                        sb.append("<td  rowspan=\"" + settleProgressSummBOList.get(a).getOutUnitSummBOS().size() * 2 * 2 * 6 + "\">" + settleProgressSummBOList.get(a).getAreaStatistics() + "</td>");
                                    }
                                    if (j) {
                                        j = false;
                                        sb.append("<td  rowspan=\"" + 2 * 2 * 6 + "\">" + outUnitSummBOList.get(b).getOutUnit() + "</td>");
                                        sb.append("<td  rowspan=\"" + 2 * 2 * 6 + "\">" + outUnitSummBOList.get(b).getOutUnitTotal() + "</td>");
                                    }
                                    if (k) {
                                        k = false;
                                        sb.append("<td  rowspan=\"" + 2 * 6 + "\">" + dispatchingConditionBOList.get(c).getCompletedAmount() + "</td>");
                                        sb.append("<td  rowspan=\"" + 2 * 6 + "\">" + dispatchingConditionBOList.get(c).getCompletedAmountTot() + "</td>");
                                    }
                                    if (l) {
                                        l = false;
                                        sb.append("<td  rowspan=\"" + 6 + "\">" + completionStateBOList.get(d).getCompletedAmount() + "</td>");
                                        sb.append("<td  rowspan=\"" + 6 + "\">" + completionStateBOList.get(d).getCompletedAmountTot() + "</td>");
                                    }
                                    if (acceptSituationBOList != null && acceptSituationBOList.size() > 0) {
                                        sb.append("<td >" + acceptSituationBOList.get(e).getCompletedAmount() + "</td>");
                                        sb.append("<td >" + acceptSituationBOList.get(e).getCompletedAmountCount() + "</td>");
                                        if (nodeDataBOList != null && nodeDataBOList.size() > 0) {
                                            if (s && e == 5) {
                                                s = false;
                                                for (NodeDataBO nodeDataBO : nodeDataBOList) {
                                                    sb.append("<td >" + nodeDataBO.getNode() + "</td>");
                                                    sb.append("<td >" + (nodeDataBO.getNodeAmount() == null ? "" : nodeDataBO.getNodeAmount()) + "</td>");
                                                }
                                            }
                                        }
                                        sb.append("</tr>");
                                    } else {
                                        if (e == 0) {
                                            sb.append("<td colspan=\"2\" rowspan=\"6\"></td>");
                                        }
                                    }
                                }
                                l = true;
                            }
                            k = true;
                        }
                        j = true;
                        s = true;
                    }
                    i = true;
                }
            }
            //结束
            sb.append("</table>");
        }

        return sb.toString();
    }
}