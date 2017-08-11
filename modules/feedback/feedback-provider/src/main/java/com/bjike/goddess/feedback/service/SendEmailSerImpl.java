package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.feedback.bo.SendEmailBO;
import com.bjike.goddess.feedback.dto.ProblemAcceptDTO;
import com.bjike.goddess.feedback.dto.ProblemFeedbackDTO;
import com.bjike.goddess.feedback.dto.SendEmailDTO;
import com.bjike.goddess.feedback.entity.ProblemAccept;
import com.bjike.goddess.feedback.entity.ProblemFeedback;
import com.bjike.goddess.feedback.entity.SendEmail;
import com.bjike.goddess.feedback.enums.CollectSendUnit;
import com.bjike.goddess.feedback.enums.Type;
import com.bjike.goddess.feedback.to.SendEmailTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 发送邮件业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-05 10:27 ]
 * @Description: [ 发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class SendEmailSerImpl extends ServiceImpl<SendEmail, SendEmailDTO> implements SendEmailSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProblemFeedbackSer problemFeedbackSer;
    @Autowired
    private ProblemAcceptSer problemAcceptSer;

    @Override
    public Long counts(SendEmailDTO sendEmailDTO) throws SerException {
        Long count = super.count(sendEmailDTO);
        return count;
    }


    @Override
    public SendEmailBO getOne(String id) throws SerException {
        SendEmail sendEmail = super.findById(id);
        return BeanTransform.copyProperties(sendEmail, SendEmailBO.class);
    }

    @Override
    public List<SendEmailBO> list(SendEmailDTO sendEmailDTO) throws SerException {
        List<SendEmail> sendEmails = super.findByCis(sendEmailDTO);
        List<SendEmailBO> sendEmailBOS = BeanTransform.copyProperties(sendEmails, SendEmailBO.class);
        return sendEmailBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SendEmailBO add(SendEmailTO sendEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
//        checkAddIdentity();
        RpcTransmit.transmitUserToken(useToken);

        if (sendEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }
        if (sendEmailTO.getSendNum() < 30 && sendEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (sendEmailTO.getSendNum() > sendEmailTO.getSendNum().longValue() && sendEmailTO.getSendNum() < (sendEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = sendEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱输入不正确");
                }
                emails.append(emailStr + ";");
            }
        }
        SendEmail sendEmail = BeanTransform.copyProperties(sendEmailTO, SendEmail.class, true);
        sendEmail.setCreateTime(LocalDateTime.now());
        sendEmail.setStatus(Status.THAW);
        sendEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        if (null == sendEmail.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        String unit = sendUnitConverse(sendEmail.getCollectSendUnit().getCode());
        sendEmail.setSendNumAndUnit(sendEmail.getSendNum() + unit);

        //设置汇总条件
//        StringBuffer condiSb = new StringBuffer("");
//        String[] condis = sendEmailTO.getCondis();
//        if (condis != null && condis.length >= 0) {
//            for (String condiStr : condis) {
//                condiSb.append(condiStr + ";");
//            }
//            collectEmail.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
//        } else {
//            throw new SerException("发送条件不能为空");
//        }

        ProblemFeedbackDTO dto = new ProblemFeedbackDTO();
        List<ProblemFeedback> problemFeedbacks = problemFeedbackSer.findByCis(dto);
        ProblemAcceptDTO problemAcceptDTO = new ProblemAcceptDTO();
        List<ProblemAccept> problemAccepts = problemAcceptSer.findByCis(problemAcceptDTO);
        for(ProblemAccept p : problemAccepts){
            //意见收集完成时间
            String ideaTime = String.valueOf(p.getIdeaTime());
        }
        for(ProblemFeedback p:problemFeedbacks){
            String getTime = String.valueOf(p.getGetTime());//获取时间(问题提出时间)
            String area = p.getArea();//所属地区
            String projectGroup = p.getProjectGroup(); //所属项目组/部门
            String problemExhibitor = p.getProblemExhibitor();//问题提出人
            String problemDescription = p.getProblemDescription(); //问题描述
            String expectDealTime = String.valueOf(p.getExpectDealTime());//期望处理时间
            StringBuffer content = new StringBuffer();
            //设置发送类型
            if (Type.FEEDBACK.equals(sendEmailTO.getType())) {

                content.append("各位同事:");
                content.append("本人是"+area+" "+projectGroup+" "+problemExhibitor+",在"+getTime+"发现"+problemDescription+"请在"+expectDealTime+"前跟进处理,谢谢!");

            }else if(Type.ASSIST.equals(sendEmailTO.getType())){
                content.append("各项目组/部门：");
                content.append("  福利模块在"+getTime+"收到所属地区所属项目组/部门问题提出人反馈的问题，问题描述如下：问题描述。请各模块就此问题，依据各自的工作权责，提出处理意见或防止再次发生的建议，并请各模块在意见收集完成时间前反馈，前回复至综合资源部。特此说明：如果各部门/模块无法在规定时间内提出建议和预计解决时间，默认为已承担问题处理延后的责任。 特此函告，请答复！");

            }else if(Type.ACCEPT.equals(sendEmailTO.getType())){
                content.append("" +
                        "");

            }
        }

        //设置发送对象
        sendEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        sendEmail.setLastSendTime(LocalDateTime.now());


        super.save(sendEmail);

        return BeanTransform.copyProperties(sendEmail, SendEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SendEmailBO edit(SendEmailTO sendEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
//        checkAddIdentity();
        RpcTransmit.transmitUserToken(useToken);

        if (sendEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }

        if (sendEmailTO.getSendNum() < 30 && sendEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (sendEmailTO.getSendNum() > sendEmailTO.getSendNum().longValue() && sendEmailTO.getSendNum() < (sendEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = sendEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱输入不正确");
                }
                emails.append(emailStr + ";");
            }
        }
        SendEmail temp = super.findById(sendEmailTO.getId());
        SendEmail sendEmail = BeanTransform.copyProperties(sendEmailTO, SendEmail.class, true);

        BeanUtils.copyProperties(sendEmail, temp, "id", "createTime", "createPersion", "lastSendTime", "status");
        temp.setModifyTime(LocalDateTime.now());
        temp.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(sendEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(sendEmail.getSendNum() + unit);

        //设置汇总条件
//        StringBuffer condiSb = new StringBuffer("");
//        String[] condis = sendEmailTO.getCondis();
//        if (condis != null && condis.length >= 0) {
//            for (String condiStr : condis) {
//                condiSb.append(condiStr + ";");
//            }
//            temp.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
//        } else {
//            throw new SerException("发送条件不能为空");
//        }


        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, SendEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congeal(String id) throws SerException {
        SendEmail sendEmail = super.findById(id);
        sendEmail.setStatus(Status.CONGEAL);
        super.update(sendEmail);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thaw(String id) throws SerException {
        SendEmail sendEmail = super.findById(id);
        sendEmail.setStatus(Status.THAW);
        super.update(sendEmail);
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
        return;
    }

}