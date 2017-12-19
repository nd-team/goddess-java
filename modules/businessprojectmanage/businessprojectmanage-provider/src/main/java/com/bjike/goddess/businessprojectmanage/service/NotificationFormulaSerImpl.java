package com.bjike.goddess.businessprojectmanage.service;

import com.bjike.goddess.businessprojectmanage.bo.BusinessContractCollectBO;
import com.bjike.goddess.businessprojectmanage.bo.NotificationFormulaBO;
import com.bjike.goddess.businessprojectmanage.enums.CollectSendUnit;
import com.bjike.goddess.businessprojectmanage.enums.GuideAddrStatus;
import com.bjike.goddess.businessprojectmanage.to.CollectEmailTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.businessprojectmanage.to.NotificationFormulaTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessprojectmanage.dto.NotificationFormulaDTO;
import com.bjike.goddess.businessprojectmanage.entity.NotificationFormula;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通报机制制定业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-14 05:05 ]
 * @Description: [ 通报机制制定业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectmanageSerCache")
@Service
public class NotificationFormulaSerImpl extends ServiceImpl<NotificationFormula, NotificationFormulaDTO> implements NotificationFormulaSer {

    @Autowired
    BusinessContractProSer businessContractProSer;

    @Autowired
    MessageAPI messageAPI;

    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1", null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（部门级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1", null);
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
            flag = cusPermissionSer.getCusPermission("1", null);
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
            flag = cusPermissionSer.getCusPermission("1", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guidePlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("4", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideBudgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("5", null);
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
            case MANAGER:
                flag = guideManageIdentity();
                break;
            case PLAN:
                flag = guidePlanIdentity();
                break;
            case BUDGET:
                flag = guideBudgetIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public List<NotificationFormulaBO> list() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), NotificationFormulaBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void add(NotificationFormulaTO to) throws SerException {

        List<String> sendObjectList = to.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if(!Validator.isEmail( emailStr)){
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ";");
            }
        }

        NotificationFormula entity = BeanTransform.copyProperties(to, NotificationFormula.class, true, "sendTimeNode", "sendObject", "conditions");
        //设置发送间隔
        if (null == entity.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        //设置发送间隔和单位
        String unit = sendUnitConverse(entity.getCollectSendUnit().getCode());
        entity.setSendNumAndUnit(entity.getSendFrequency() + unit);
        //设置汇总条件
//        StringBuffer condiSb = new StringBuffer("");
//        String[] condis = to.getConditions();
//        if (condis != null && condis.length >= 0) {
//            for (String condiStr : condis) {
//                condiSb.append(condiStr + ";");
//            }
//            entity.setConditions(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
//        } else {
//            throw new SerException("发送条件不能为空");
//        }
        entity.setConditions(to.getConditions());

        entity.setCreateTime(LocalDateTime.now());
        entity.setStatus(Status.THAW);
//        entity.setCreator(userAPI.currentUser().getUsername()); todo测试注释
        entity.setLastSendTime(LocalDateTime.now());
        entity.setSendObject(String.valueOf(emails));

        super.save(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(NotificationFormulaTO to) throws SerException {
        NotificationFormula entity = BeanTransform.copyProperties(to, NotificationFormula.class, true);
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void sendEmail(CollectEmailTO to) throws SerException {

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void checkSendEmail() throws SerException {
        List<NotificationFormula> emails = new ArrayList<>();

        NotificationFormulaDTO dto = new NotificationFormulaDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<NotificationFormula> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (NotificationFormula str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastSendTime();
            //发送间隔
            Double sendNum = str.getSendFrequency();
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
//                        str.setLastSendTime(lastTime.plusMinutes( sendNum.longValue() ));
                        str.setLastSendTime( LocalDateTime.now());
                    }
                    break;
                case HOURS:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
//                        str.setLastSendTime(lastTime.plusHours( sendNum.longValue() ));
                        str.setLastSendTime( LocalDateTime.now());
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
//                        str.setLastSendTime(lastTime.plusDays( sendNum.longValue() ));
                        str.setLastSendTime( LocalDateTime.now());
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
//                        str.setLastSendTime(lastTime.plusWeeks( sendNum.longValue() ));
                        str.setLastSendTime( LocalDateTime.now());
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
//                        str.setLastSendTime(lastTime.plusMonths( sendNum.longValue() ));
                        str.setLastSendTime( LocalDateTime.now());
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3*sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3*sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
//                        str.setLastSendTime(lastTime.plusMonths( 3* sendNum.longValue() ));
                        str.setLastSendTime( LocalDateTime.now());
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
//                        str.setLastSendTime(lastTime.plusYears( sendNum.longValue() ));
                        str.setLastSendTime( LocalDateTime.now() );
                    }
                    break;
            }

            if (flag) {
                emails.add(str);
            }

        }

        //调用发邮件
        emails = sendObject(emails);

        //修改上次发送时间
        super.update(emails);
    }

    private List<NotificationFormula> sendObject(List<NotificationFormula> emails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<NotificationFormula> allEmails = new ArrayList<>();
        if (emails != null && emails.size() > 0) {
            for (NotificationFormula sign : emails) {
//                sign.setLastSendTime( LocalDateTime.now() );

//                String[] condis = sign.getConditions().split(";");
//                List<NotificationFormulaBO> signBOList = collectEmailSer.collectCollectEmail(condis);
                List<BusinessContractCollectBO> bos = businessContractProSer.collectCollectEmail(sign.getConditions());
                //拼表格
                String content = getBCCTable(bos);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送项目实施进度管理汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(sign.getSendObject().split(";") );
                messageAPI.send(messageTO);

                sign.setModifyTime(LocalDateTime.now());
                allEmails.add(sign);
            }
        }

        return allEmails;

    }

    /**
     * 实施进度汇总表格
     *
     * @param
     * @return class
     * @version v1
     */
    private String getBCCTable(List<BusinessContractCollectBO> list) {
        StringBuffer sb = new StringBuffer("");
        if (list != null && list.size() > 0) {
            sb = new StringBuffer("<h4>项目实施进度管理汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
//            CollectEmailBO title = signBOList.get(signBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>所属项目组</td>");
            sb.append("<td>项目总规模</td>");
            sb.append("<td>项目总金额</td>");
            sb.append("<td>增合同单数</td>");
            sb.append("<td>新增合同金额</td>");
            sb.append("<td>已立项合同规模</td>");
            sb.append("<td>已立项合同金额</td>");
            sb.append("<td>预立项合同规模</td>");
            sb.append("<td>预立项合同金额</td>");
            sb.append("<td>已完工规模</td>");
            sb.append("<td>已完工金额</td>");
            sb.append("<td>未完工规模</td>");
            sb.append("<td>未完工金额</td>");
            sb.append("<td>需项目总结数量</td>");
            sb.append("<td>已完成项目总结</td>");
            sb.append("<td>未完成项目总结</td>");
            sb.append("<tr>");

            //拼body部分
            for (BusinessContractCollectBO bo : list) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getProjectGroup() + "</td>");
                sb.append("<td>" + bo.getScaleContract() + "</td>");
                sb.append("<td>" + bo.getTotalMoney() + "</td>");
                sb.append("<td>" + bo.getContractAmount() + "</td>");
                sb.append("<td>" + bo.getContractMoney() + "</td>");
                sb.append("<td>" + bo.getMakeContractAmout() + "</td>");
                sb.append("<td>" + bo.getMakeMoney() + "</td>");
                sb.append("<td>" + bo.getForecastContractAmount() + "</td>");
                sb.append("<td>" + bo.getForecastMoney() + "</td>");
                sb.append("<td>" + bo.getComplete() + "</td>");
                sb.append("<td>" + bo.getCompleteMoney() + "</td>");
                sb.append("<td>" + bo.getNotCompleteContract() + "</td>");
                sb.append("<td>" + bo.getNotCompleteMoney() + "</td>");
                sb.append("<td>" + bo.getNeedSummarizeAmount() + "</td>");
                sb.append("<td>" + bo.getSummarizeAmount() + "</td>");
                sb.append("<td>" + bo.getNotSummarizeAmount() + "</td>");
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
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

}