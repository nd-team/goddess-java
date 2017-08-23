package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.bo.ReceivedFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemAcceptDTO;
import com.bjike.goddess.feedback.dto.ReceivedFeedbackDTO;
import com.bjike.goddess.feedback.entity.ProblemAccept;
import com.bjike.goddess.feedback.entity.ProblemResult;
import com.bjike.goddess.feedback.entity.ReceivedFeedback;
import com.bjike.goddess.feedback.enums.IdentityChoice;
import com.bjike.goddess.feedback.to.ReceivedFeedbackTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 已受理的反馈业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 09:24 ]
 * @Description: [ 已受理的反馈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ReceivedFeedbackSerImpl extends ServiceImpl<ReceivedFeedback, ReceivedFeedbackDTO> implements ReceivedFeedbackSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProblemResultSer problemResultSer;
    @Autowired
    private ProblemAcceptSer problemAcceptSer;

    @Override
    public Long count(ReceivedFeedbackDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ReceivedFeedbackBO getOne(String id) throws SerException {
        ReceivedFeedback receivedFeedback = super.findById(id);
        return BeanTransform.copyProperties(receivedFeedback, ReceivedFeedbackBO.class);
    }

    @Override
    public List<ReceivedFeedbackBO> list(ReceivedFeedbackDTO dto) throws SerException {
        ProblemAcceptDTO problemAcceptDTO = new ProblemAcceptDTO();
        List<ProblemAccept> problemAccepts = problemAcceptSer.findByCis(problemAcceptDTO);
        List<ReceivedFeedbackBO> receivedFeedbackBOS = new ArrayList<>();
        for (ProblemAccept problemAccept : problemAccepts) {
            ReceivedFeedbackBO receivedFeedbackBO = new ReceivedFeedbackBO();
            //录入人
            receivedFeedbackBO.setInputUser(problemAccept.getProblemFeedback().getInputUser());
            //问题编号(对外)
            receivedFeedbackBO.setProblemNum(problemAccept.getProblemFeedback().getProblemNum());
            //问题受理编号(对内)
            receivedFeedbackBO.setAcceptNum(problemAccept.getAcceptNum());
            //事件(背景)描述
            receivedFeedbackBO.setEventDescription(problemAccept.getProblemFeedback().getEventDescription());
            //问题描述
            receivedFeedbackBO.setProblemDescription(problemAccept.getProblemFeedback().getProblemDescription());
            //获取时间(问题提出时间)
            receivedFeedbackBO.setGetTime(String.valueOf(problemAccept.getProblemFeedback().getGetTime()));
            //是否通报
            receivedFeedbackBO.setNotification(problemAccept.getProblemFeedback().getNotification());
            //通报时间
            receivedFeedbackBO.setNotificationTime(String.valueOf(problemAccept.getProblemFeedback().getNotificationTime()));
            //通报途径
            receivedFeedbackBO.setNotificationWay(problemAccept.getProblemFeedback().getNotificationWay());
            //问题受理所属部门
            receivedFeedbackBO.setAcceptDepartment(problemAccept.getAcceptDepartment());
            //问题受理所属模块
            receivedFeedbackBO.setAcceptModule(problemAccept.getAcceptModule());
            //问题受理人
            receivedFeedbackBO.setAcceptPerson(problemAccept.getAcceptPerson());
            //问题跟进处理计划完成时间
            receivedFeedbackBO.setAcceptTime(String.valueOf(problemAccept.getAcceptTime()));
            //意见收集完成时间
            receivedFeedbackBO.setIdeaTime(String.valueOf(problemAccept.getIdeaTime()));
            //期望处理时间
            receivedFeedbackBO.setExpectDealTime(String.valueOf(problemAccept.getProblemFeedback().getExpectDealTime()));
            //问题类型
            receivedFeedbackBO.setProblemType(problemAccept.getProblemFeedback().getProblemType());
            //主功能
            receivedFeedbackBO.setMainFunction(problemAccept.getProblemFeedback().getMainFunction());
            //关联相关模块
            receivedFeedbackBO.setCorrelativeModule(problemAccept.getProblemFeedback().getCorrelativeModule());
            //所属地区
            receivedFeedbackBO.setArea(problemAccept.getProblemFeedback().getArea());
            //问题提出人
            receivedFeedbackBO.setProblemExhibitor(problemAccept.getProblemFeedback().getProblemExhibitor());
            //所属项目组/部门
            receivedFeedbackBO.setProjectGroup(problemAccept.getProblemFeedback().getProjectGroup());
            //问题责任人
            receivedFeedbackBO.setProblemDutyOfficer(problemAccept.getProblemFeedback().getProblemDutyOfficer());
            //问题来源
            receivedFeedbackBO.setProblemSource(problemAccept.getProblemFeedback().getProblemSource());
            receivedFeedbackBOS.add(receivedFeedbackBO);
        }
//        List<ReceivedFeedback> receivedFeedbacks = super.findByCis(dto);
//        List<ReceivedFeedbackBO> receivedFeedbackBOS = BeanTransform.copyProperties(receivedFeedbacks, ReceivedFeedbackBO.class);
        return receivedFeedbackBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReceivedFeedbackBO provideAdvice(ReceivedFeedbackTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            ReceivedFeedback receivedFeedback = super.findById(to.getId());
            UserBO userBO = userAPI.currentUser();
            if (IdentityChoice.RESPONSIBLE.equals(to.getIdentityChoice())) {

                //获得非责任人的数量
                String[] fields = new String[]{"responsibleNum"};
                String sql = "SELECT count(*) as count FROM feedback_responsibleidea";
                List<ReceivedFeedback> count = super.findBySql(sql, ReceivedFeedback.class, fields);
                Integer responsibleNum = count.get(0).getResponsibleNum();
                receivedFeedback.setResponsibleNum(responsibleNum);
            } else if (IdentityChoice.FIRST.equals(to.getIdentityChoice())) {
                receivedFeedback.setFirstIdea(userBO.getUsername());
                receivedFeedback.setFirstProjectGroupOpinion(to.getFirstProjectGroupOpinion());
            } else if (IdentityChoice.PLAN.equals(to.getIdentityChoice())) {
                receivedFeedback.setPlanIdea(userBO.getUsername());
                receivedFeedback.setPlanOpinion(to.getPlanOpinion());
            } else if (IdentityChoice.LITERACY.equals(to.getIdentityChoice())) {
                receivedFeedback.setLiteracyIdea(userBO.getUsername());
                receivedFeedback.setLiteracyOpinion(to.getLiteracyOpinion());
            } else if (IdentityChoice.BUSINESS.equals(to.getIdentityChoice())) {
                receivedFeedback.setBusinessIdea(userBO.getUsername());
                receivedFeedback.setBusinessOpinion(to.getBusinessOpinion());
            } else if (IdentityChoice.MONEY.equals(to.getIdentityChoice())) {
                receivedFeedback.setMoneyIdea(userBO.getUsername());
                receivedFeedback.setMoneyOpinion(to.getMoneyOpinion());
            } else if (IdentityChoice.ACCOUNT.equals(to.getIdentityChoice())) {
                receivedFeedback.setAccountIdea(userBO.getUsername());
                receivedFeedback.setAccountOpinion(to.getAccountOpinion());
            } else if (IdentityChoice.BUDGET.equals(to.getIdentityChoice())) {
                receivedFeedback.setBudgetIdea(userBO.getUsername());
                receivedFeedback.setBudgetOpinion(to.getBudgetOpinion());
            } else if (IdentityChoice.DIVISION.equals(to.getIdentityChoice())) {
                receivedFeedback.setDivisionIdea(userBO.getUsername());
                receivedFeedback.setDivisionOpinion(to.getDivisionOpinion());
            } else if (IdentityChoice.GENERAL.equals(to.getIdentityChoice())) {
                receivedFeedback.setGeneralManagerIdea(userBO.getUsername());
                receivedFeedback.setGeneralManagerOpinion(to.getGeneralManagerOpinion());
            }
            super.update(receivedFeedback);
            return BeanTransform.copyProperties(receivedFeedback, ReceivedFeedbackBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReceivedFeedbackBO priority(ReceivedFeedbackTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            ReceivedFeedback receivedFeedback = super.findById(to.getId());
            BeanTransform.copyProperties(to, receivedFeedback, true);
            receivedFeedback.setArtificialPriority(to.getArtificialPriority());
            super.update(receivedFeedback);
            return BeanTransform.copyProperties(receivedFeedback, ReceivedFeedbackBO.class);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemResultBO solve(ReceivedFeedbackTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            ReceivedFeedback receivedFeedback = super.findById(to.getId());
            ProblemResult problemResult = new ProblemResult();
            BeanTransform.copyProperties(problemResult, receivedFeedback, true);
            problemResult.setFinalSolution(to.getFinalSolution());
            problemResult.setProblemSolveTime(DateUtil.parseDateTime(to.getProblemSolveTime()));
            problemResultSer.update(problemResult);
            return BeanTransform.copyProperties(problemResult, ProblemResultBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }
}