package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.bo.ReceivedFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemAcceptDTO;
import com.bjike.goddess.feedback.dto.ProblemResultDTO;
import com.bjike.goddess.feedback.entity.ProblemAccept;
import com.bjike.goddess.feedback.entity.ProblemResult;
import com.bjike.goddess.feedback.to.ProblemResultTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题处理结果业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ProblemResultSerImpl extends ServiceImpl<ProblemResult, ProblemResultDTO> implements ProblemResultSer {

    @Autowired
    private ProblemAcceptSer problemAcceptSer;
    @Override
    public Long count(ProblemResultDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ProblemResultBO getOne(String id) throws SerException {
        ProblemResult problemResult = super.findById(id);
        return BeanTransform.copyProperties(problemResult,ProblemResultBO.class);
    }

    @Override
    public List<ProblemResultBO> list(ProblemResultDTO dto) throws SerException {
        ProblemAcceptDTO problemAcceptDTO = new ProblemAcceptDTO();
        List<ProblemAccept> problemAccepts = problemAcceptSer.findByCis(problemAcceptDTO);
        List<ProblemResultBO> problemResultBOS = new ArrayList<>();
        for (ProblemAccept problemAccept : problemAccepts) {
            ProblemResultBO problemResultBO = new ProblemResultBO();
            //录入人
            problemResultBO.setInputUser(problemAccept.getProblemFeedback().getInputUser());
            //问题编号(对外)
            problemResultBO.setProblemNum(problemAccept.getProblemFeedback().getProblemNum());
            //问题受理编号(对内)
            problemResultBO.setAcceptNum(problemAccept.getAcceptNum());
            //事件(背景)描述
            problemResultBO.setEventDescription(problemAccept.getProblemFeedback().getEventDescription());
            //问题描述
            problemResultBO.setProblemDescription(problemAccept.getProblemFeedback().getProblemDescription());
            //获取时间(问题提出时间)
            problemResultBO.setGetTime(String.valueOf(problemAccept.getProblemFeedback().getGetTime()));
            //是否通报
            problemResultBO.setNotification(problemAccept.getProblemFeedback().getNotification());
            //通报时间
            problemResultBO.setNotificationTime(String.valueOf(problemAccept.getProblemFeedback().getNotificationTime()));
            //通报途径
            problemResultBO.setNotificationWay(problemAccept.getProblemFeedback().getNotificationWay());
            //问题受理所属部门
            problemResultBO.setAcceptDepartment(problemAccept.getAcceptDepartment());
            //问题受理所属模块
            problemResultBO.setAcceptModule(problemAccept.getAcceptModule());
            //问题受理人
            problemResultBO.setAcceptPerson(problemAccept.getAcceptPerson());
            //问题跟进处理计划完成时间
            problemResultBO.setAcceptTime(String.valueOf(problemAccept.getAcceptTime()));
            //意见收集完成时间
            problemResultBO.setIdeaTime(String.valueOf(problemAccept.getIdeaTime()));
            //期望处理时间
            problemResultBO.setExpectDealTime(String.valueOf(problemAccept.getProblemFeedback().getExpectDealTime()));
            //问题类型
            problemResultBO.setProblemType(problemAccept.getProblemFeedback().getProblemType());
            //主功能
            problemResultBO.setMainFunction(problemAccept.getProblemFeedback().getMainFunction());
            //关联相关模块
            problemResultBO.setCorrelativeModule(problemAccept.getProblemFeedback().getCorrelativeModule());
            //所属地区
            problemResultBO.setArea(problemAccept.getProblemFeedback().getArea());
            //问题提出人
            problemResultBO.setProblemExhibitor(problemAccept.getProblemFeedback().getProblemExhibitor());
            //所属项目组/部门
            problemResultBO.setProjectGroup(problemAccept.getProblemFeedback().getProjectGroup());
            //问题责任人
            problemResultBO.setProblemDutyOfficer(problemAccept.getProblemFeedback().getProblemDutyOfficer());
            //问题来源
            problemResultBO.setProblemSource(problemAccept.getProblemFeedback().getProblemSource());
            problemResultBOS.add(problemResultBO);
        }
//        List<ProblemResult> problemResults = super.findByCis(dto);
//        List<ProblemResultBO> problemResultBOS = BeanTransform.copyProperties(problemResults,ProblemResultBO.class);
        return problemResultBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemResultBO partyConfirm(ProblemResultTO to) throws SerException {
        if(StringUtils.isNotBlank(to.getId())){
            ProblemResult problemResult = super.findById(to.getId());
            BeanTransform.copyProperties(to,problemResult,true);
            problemResult.setPartyFinish(to.getPartyFinish());
            problemResult.setPartyIdea(to.getPartyIdea());
            super.update(problemResult);
            return BeanTransform.copyProperties(problemResult,ProblemResultBO.class);
        }else {
            throw new SerException("id不能为空");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemResultBO coordinate(ProblemResultTO to) throws SerException {
        if(StringUtils.isNotBlank(to.getId())){
            ProblemResult problemResult = super.findById(to.getId());
            BeanTransform.copyProperties(to,problemResult,true);
            if(to.getCoordinate().equals(false)){
                problemResult.setFinalSolutionTime(LocalDateTime.now());
                problemResult.setProblemResult("已处理");
            }else if(to.getCoordinate().equals(true)){
                problemResult.setProblemResult("待处理");
                String[] feilds = new String[]{"otherIdeaNum"};
                String sql = "SELECT count(*) as count FROM feedback_otheridea";
                List<ProblemResult> problemResults = super.findBySql(sql,ProblemResult.class,feilds);
                Integer otherIdeaNum = problemResults.get(0).getOtherIdeaNum();
                problemResult.setOtherIdeaNum(otherIdeaNum);

            }
            super.update(problemResult);
            return BeanTransform.copyProperties(problemResult,ProblemResultBO.class);
        }else {
            throw new SerException("id不能为空");
        }

    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemResultBO result(ProblemResultTO to) throws SerException {
        ProblemResult problemResult = super.findById(to.getId());
        BeanTransform.copyProperties(to,problemResult,true);
        problemResult.setCoordinateResult(to.getCoordinateResult());
        problemResult.setFinalSolutionTime(LocalDateTime.now());
        problemResult.setProblemResult("已处理");
        super.update(problemResult);
        return BeanTransform.copyProperties(problemResult,ProblemResultBO.class);
    }
}