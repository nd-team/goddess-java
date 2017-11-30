package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.feedback.bo.ProblemAcceptBO;
import com.bjike.goddess.feedback.bo.ProblemFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemCodeRuleDTO;
import com.bjike.goddess.feedback.dto.ProblemFeedbackDTO;
import com.bjike.goddess.feedback.entity.ProblemAccept;
import com.bjike.goddess.feedback.entity.ProblemCodeRule;
import com.bjike.goddess.feedback.entity.ProblemFeedback;
import com.bjike.goddess.feedback.enums.GuideAddrStatus;
import com.bjike.goddess.feedback.excel.SonPermissionObject;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemFeedbackTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题反馈模块业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 10:38 ]
 * @Description: [ 问题反馈模块业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ProblemFeedbackSerImpl extends ServiceImpl<ProblemFeedback, ProblemFeedbackDTO> implements ProblemFeedbackSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ProblemAcceptSer problemAcceptSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ProblemCodeRuleSer problemCodeRuleSer;
    @Autowired
    private ReceivedFeedbackSer receivedFeedbackSer;
    @Autowired
    private ProblemResultSer problemResultSer;
    @Autowired
    private ConnectSer connectSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private MessageAPI messageAPI;

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
            flag = cusPermissionSer.getCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("problemfeedback");
        obj.setDescribesion("问题反馈模块");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = receivedFeedbackSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("receivedfeedback");
        obj.setDescribesion("已受理的反馈");
        if (flagSeeAnswer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeWeb = problemResultSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("problemresult");
        obj.setDescribesion("问题处理结果");
        if (flagSeeWeb) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeConnect = connectSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("connect");
        obj.setDescribesion("各类沟通模板");
        if (flagSeeConnect) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCode = problemCodeRuleSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("problemcoderule");
        obj.setDescribesion("问题编码规则");
        if (flagSeeCode) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
    public Long count(ProblemFeedbackDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ProblemFeedbackBO getOne(String id) throws SerException {
        ProblemFeedback problemFeedback = super.findById(id);
        return BeanTransform.copyProperties(problemFeedback, ProblemFeedbackBO.class);
    }

    @Override
    public List<ProblemFeedbackBO> list(ProblemFeedbackDTO dto) throws SerException {
        checkSeeIdentity();
        List<ProblemFeedback> problemFeedbacks = super.findByCis(dto);
        List<ProblemFeedbackBO> problemFeedbackBOS = BeanTransform.copyProperties(problemFeedbacks, ProblemFeedbackBO.class);

        return problemFeedbackBOS;
    }

    private Logger log = Logger.getLogger(ProblemFeedbackSerImpl.class);


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemFeedbackBO insert(ProblemFeedbackTO to) throws SerException {
        checkAddIdentity();
        ProblemFeedback problemFeedback = BeanTransform.copyProperties(to, ProblemFeedback.class, true);
        UserBO userBO = userAPI.currentUser();
        problemFeedback.setInputUser(userBO.getUsername());
        problemFeedback.setGetTime(LocalDateTime.now());
        problemFeedback.setNotification(false);
        problemFeedback.setCreateTime(LocalDateTime.now());
        LocalDateTime a = problemFeedback.getGetTime();
        String ss = DateUtil.dateToString(a.toLocalDate());
        String s = DateUtil.dateToString(a.toLocalDate()).replaceAll("-", "");
        ProblemFeedbackDTO problemFeedbackDTO = new ProblemFeedbackDTO();
        LocalDate now = DateUtil.parseDate(ss);
        problemFeedbackDTO.getConditions().add(Restrict.between("getTime", new LocalDate[]{now, now}));
        long b = super.count(problemFeedbackDTO);
        long num = b + 1;
        String s1 = s + "-" + String.valueOf(num);
        String name = to.getProblemExhibitor();
        List<PositionDetailBO> positionDetailBOS = positionDetailUserAPI.getPositionDetail(name);
        if (positionDetailBOS != null) {
            for (PositionDetailBO positionDetailBO : positionDetailBOS) {
                problemFeedback.setArea(positionDetailBO.getArea());
//            positionDetailAPI.findStatus();
                String departmentName = positionDetailBO.getDepartmentName();
                String moduleName = positionDetailBO.getModuleName();
                problemFeedback.setProjectGroup(departmentName);
                if (departmentName != null) {
                    ProblemCodeRuleDTO problemCodeRuleDTO = new ProblemCodeRuleDTO();
                    problemCodeRuleDTO.getConditions().add(Restrict.eq("projectGroup", departmentName));
                    problemCodeRuleDTO.getConditions().add(Restrict.eq("module", moduleName));
                    List<ProblemCodeRule> problemCodeRule = problemCodeRuleSer.findByCis(problemCodeRuleDTO);
                    if (problemCodeRule != null) {
                        problemFeedback.setProblemNum(problemCodeRule.get(0).getProblemCodeRule() + "Q" + s1);

                    }
                }
                String passName = problemFeedback.getProblemExhibitor();
                LocalDateTime getTime = problemFeedback.getGetTime();//获取时间(问题提出时间)
                String area = problemFeedback.getArea();//所属地区
                String projectGroup = problemFeedback.getProjectGroup(); //所属项目组/部门
                String problemExhibitor = problemFeedback.getProblemExhibitor();//问题提出人
                String problemDescription = problemFeedback.getProblemDescription(); //问题描述
                LocalDateTime expectDealTime = problemFeedback.getExpectDealTime();//期望处理时间
                StringBuffer content = new StringBuffer();
                //设置发送内容
                content.append("各位同事:");
                content.append("本人是" + area + " " + projectGroup + " " + problemExhibitor + ",在" + getTime + "发现" + problemDescription + "请在" + expectDealTime + "前跟进处理,谢谢!");
                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content.toString());
                messageTO.setTitle("问题反馈");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                if (to.getPublicEmail() != null) {
                    messageTO.setReceivers(to.getPublicEmail());
                    messageAPI.send(messageTO);
                }
                if (to.getSendObject() != null) {
                    List<String> sendObject = internalContactsAPI.getEmails(to.getSendObject());
                    if(!sendObject.isEmpty()){
                        String[] strings = new String[sendObject.size()];
                        strings = sendObject.toArray(strings);
                        messageTO.setReceivers(strings);
                        messageAPI.send(messageTO);
                    }
                }

            }
            super.save(problemFeedback);

        }
        return BeanTransform.copyProperties(problemFeedback, ProblemFeedbackBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemFeedbackBO edit(ProblemFeedbackTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            ProblemFeedback problemFeedback = super.findById(to.getId());
            BeanTransform.copyProperties(to, problemFeedback, true);
            problemFeedback.setModifyTime(LocalDateTime.now());
            problemFeedback.setNotification(true);
            problemFeedback.setNotificationWay("邮件");
            problemFeedback.setNotificationTime(LocalDateTime.now());
            String passName = problemFeedback.getProblemExhibitor();
            String getTime = String.valueOf(problemFeedback.getGetTime());//获取时间(问题提出时间)
            String area = problemFeedback.getArea();//所属地区
            String projectGroup = problemFeedback.getProjectGroup(); //所属项目组/部门
            String problemExhibitor = problemFeedback.getProblemExhibitor();//问题提出人
            String problemDescription = problemFeedback.getProblemDescription(); //问题描述
            String ideaTime = String.valueOf(to.getIdeaTime()); //意见收集完成时间
            StringBuffer content = new StringBuffer();
            //设置发送内容
            content.append("各项目组/部门：");
            content.append("福利模块在" + getTime + "收到" + area + " " + projectGroup + " " + problemExhibitor + "反馈的问题，" +
                    " 问题描述如下：" + problemDescription + "。请各模块就此问题，依据各自的工作权责，提出处理意见或防止再次发生的建议，" +
                    " 并请各模块在" + ideaTime + "前反馈，前回复至综合资源部。特此说明：如果各部门/模块无法在规定时间内提出建议和预计解决时间，" +
                    " 默认为已承担问题处理延后的责任。特此函告，请答复！");
            content.append("综合资源部福利模块" + LocalDate.now());
            MessageTO messageTO = new MessageTO();
            messageTO.setContent(content.toString());
            messageTO.setTitle("协助");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);

            if (to.getPublicEmail() != null) {
                messageTO.setReceivers(to.getPublicEmail());
                messageAPI.send(messageTO);
            }
            if (to.getSendObject() != null) {
                List<String> sendObject = internalContactsAPI.getEmails(to.getSendObject());
                String[] strings = new String[sendObject.size()];
                strings = sendObject.toArray(strings);
                messageTO.setReceivers(strings);
                messageAPI.send(messageTO);
            }
            super.update(problemFeedback);

            return BeanTransform.copyProperties(problemFeedback, ProblemFeedbackBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemAcceptBO problemAccept(ProblemFeedbackTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            ProblemFeedback problemFeedback = super.findById(to.getId());
            ProblemAccept problemAccept = new ProblemAccept();
            problemAccept.setProblemFeedback(problemFeedback);
            problemAccept.setAcceptTime(DateUtil.parseDateTime(to.getAcceptTime()));
            problemAccept.setIdeaTime(DateUtil.parseDateTime(to.getIdeaTime()));

            UserBO userBO = userAPI.currentUser();
            String acceptPerson = userBO.getUsername();
            problemAccept.setAcceptPerson(acceptPerson);
            String name = acceptPerson;
            List<PositionDetailBO> positionDetailBOS = positionDetailUserAPI.getPositionDetail(name);
            for (PositionDetailBO positionDetailBO : positionDetailBOS) {
                problemAccept.setAcceptDepartment(positionDetailBO.getDepartmentName());
                problemAccept.setAcceptModule(positionDetailBO.getModuleName());
            }
            String passName = problemFeedback.getProblemExhibitor();
            String getTime = String.valueOf(problemFeedback.getGetTime());//获取时间(问题提出时间)
            String problemExhibitor = problemFeedback.getProblemExhibitor();//问题提出人
            String problemDescription = problemFeedback.getProblemDescription(); //问题描述
            String ideaTime = String.valueOf(to.getIdeaTime()); //意见收集完成时间
            String acceptTime = String.valueOf(to.getAcceptTime());//问题跟进处理计划完成时间不能为空
            StringBuffer content = new StringBuffer();
            //设置发送内容
            content.append(problemExhibitor + ":");
            content.append("你好！关于" + getTime + "发现" + problemDescription + "，已收悉。 " +
                    " 经与各项目组/部门商讨，现函付如下： " +
                    " 你的问题我们会在" + ideaTime + "内收集完各项目组各部门意见，计划于" + acceptTime + "反馈处理结果，" +
                    " 如跟你的期望时间有出入，福利模块负责人跟你协商，请悉知！");
            MessageTO messageTO = new MessageTO();
            messageTO.setContent(content.toString());
            messageTO.setTitle("问题受理后的回复");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);

            if (to.getPublicEmail() != null) {
                messageTO.setReceivers(to.getPublicEmail());
                messageAPI.send(messageTO);
            }
            if (to.getSendObject() != null) {
                List<String> sendObject = internalContactsAPI.getEmails(to.getSendObject());
                String[] strings = new String[sendObject.size()];
                strings = sendObject.toArray(strings);
                messageTO.setReceivers(strings);
                messageAPI.send(messageTO);
            }
            problemAcceptSer.save(problemAccept);


            return BeanTransform.copyProperties(problemAccept, ProblemAcceptBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

}