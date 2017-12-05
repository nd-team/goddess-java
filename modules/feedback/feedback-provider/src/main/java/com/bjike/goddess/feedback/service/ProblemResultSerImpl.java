package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.dto.ProblemResultDTO;
import com.bjike.goddess.feedback.dto.ReceivedFeedbackDTO;
import com.bjike.goddess.feedback.entity.ProblemResult;
import com.bjike.goddess.feedback.entity.ReceivedFeedback;
import com.bjike.goddess.feedback.enums.GuideAddrStatus;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemResultTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
    @Autowired
    private ReceivedFeedbackSer receivedFeedbackSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("2",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1",null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2",null);
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long count(ProblemResultDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ProblemResultBO getOne(String id) throws SerException {
        ProblemResult problemResult = super.findById(id);
        return BeanTransform.copyProperties(problemResult, ProblemResultBO.class);
    }

    @Override
    public List<ProblemResultBO> list(ProblemResultDTO dto) throws SerException {
        checkSeeIdentity();
        ReceivedFeedbackDTO receivedFeedbackDTO = new ReceivedFeedbackDTO();
        List<ReceivedFeedback> receivedFeedbacks = receivedFeedbackSer.findByCis(receivedFeedbackDTO);

        List<ProblemResultBO> problemResultBOS = new ArrayList<>();
        for (ReceivedFeedback receivedFeedback : receivedFeedbacks) {
            ProblemResultBO problemResultBO = new ProblemResultBO();
            dto = new ProblemResultDTO();
            dto.getConditions().add(Restrict.eq("receivedFeedback.id", receivedFeedback.getId()));
            ProblemResult problemResult = super.findOne(dto);
            if (null == problemResult) {
                problemResult = new ProblemResult();
                problemResult.setReceivedFeedback(receivedFeedback);
                problemResult.setCreateTime(LocalDateTime.now());
                problemResult = super.save(problemResult);
            }
            problemResultBO.setId(problemResult.getId());
            //录入人
            problemResultBO.setInputUser(receivedFeedback.getProblemAccept().getProblemFeedback().getInputUser());
            //问题编号(对外)
            problemResultBO.setProblemNum(receivedFeedback.getProblemAccept().getProblemFeedback().getProblemNum());
            //问题受理编号(对内)
            problemResultBO.setAcceptNum(receivedFeedback.getProblemAccept().getAcceptNum());
            //事件(背景)描述
            problemResultBO.setEventDescription(receivedFeedback.getProblemAccept().getProblemFeedback().getEventDescription());
            //问题描述
            problemResultBO.setProblemDescription(receivedFeedback.getProblemAccept().getProblemFeedback().getProblemDescription());
            //获取时间(问题提出时间)
            problemResultBO.setGetTime(String.valueOf(receivedFeedback.getProblemAccept().getProblemFeedback().getGetTime()));
            //是否通报
            problemResultBO.setNotification(receivedFeedback.getProblemAccept().getProblemFeedback().getNotification());
            //通报时间
            problemResultBO.setNotificationTime(String.valueOf(receivedFeedback.getProblemAccept().getProblemFeedback().getNotificationTime()));
            //通报途径
            problemResultBO.setNotificationWay(receivedFeedback.getProblemAccept().getProblemFeedback().getNotificationWay());
            //问题受理所属部门
            problemResultBO.setAcceptDepartment(receivedFeedback.getProblemAccept().getAcceptDepartment());
            //问题受理所属模块
            problemResultBO.setAcceptModule(receivedFeedback.getProblemAccept().getAcceptModule());
            //问题受理人
            problemResultBO.setAcceptPerson(receivedFeedback.getProblemAccept().getAcceptPerson());
            //问题跟进处理计划完成时间
            problemResultBO.setAcceptTime(String.valueOf(receivedFeedback.getProblemAccept().getAcceptTime()));
            //意见收集完成时间
            problemResultBO.setIdeaTime(String.valueOf(receivedFeedback.getProblemAccept().getIdeaTime()));
            //期望处理时间
            problemResultBO.setExpectDealTime(String.valueOf(receivedFeedback.getProblemAccept().getProblemFeedback().getExpectDealTime()));
            //问题类型
            problemResultBO.setProblemType(receivedFeedback.getProblemAccept().getProblemFeedback().getProblemType());
            //主功能
            problemResultBO.setMainFunction(receivedFeedback.getProblemAccept().getProblemFeedback().getMainFunction());
            //关联相关模块
            problemResultBO.setCorrelativeModule(receivedFeedback.getProblemAccept().getProblemFeedback().getCorrelativeModule());
            //所属地区
            problemResultBO.setArea(receivedFeedback.getProblemAccept().getProblemFeedback().getArea());
            //问题提出人
            problemResultBO.setProblemExhibitor(receivedFeedback.getProblemAccept().getProblemFeedback().getProblemExhibitor());
            //所属项目组/部门
            problemResultBO.setProjectGroup(receivedFeedback.getProblemAccept().getProblemFeedback().getProjectGroup());
            //问题责任人
            problemResultBO.setProblemDutyOfficer(receivedFeedback.getProblemAccept().getProblemFeedback().getProblemDutyOfficer());
            //问题来源
            problemResultBO.setProblemSource(receivedFeedback.getProblemAccept().getProblemFeedback().getProblemSource());
            //优先级(人工编辑)
            problemResultBO.setArtificialPriority(receivedFeedback.getArtificialPriority());
            //优先级（系统计算分值）
            problemResultBO.setSystemPriority(receivedFeedback.getSystemPriority());
            problemResultBO.setFinalSolution(problemResult.getFinalSolution());//最终解决方案
            problemResultBO.setFinalSolutionTime(String.valueOf(problemResult.getFinalSolutionTime()));//最终解决方案确定时间
            problemResultBO.setPartyFinish(problemResult.getPartyFinish());//当事人是否确认处理完成
            problemResultBO.setPartyIdea(problemResult.getPartyIdea());//当事人确认意见
            problemResultBO.setOtherIdeaNum(problemResult.getOtherIdeaNum());//其他模块意见数
            problemResultBO.setCoordinate(problemResult.getCoordinate());//是否需要协调
            problemResultBO.setCoordinateResult(problemResult.getCoordinateResult());//协调结果
            problemResultBO.setProblemResult(problemResult.getProblemResult());//问题处理结果
            problemResultBOS.add(problemResultBO);
        }
        return problemResultBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemResultBO partyConfirm(ProblemResultTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            ProblemResult problemResult = super.findById(to.getId());
            BeanTransform.copyProperties(to, problemResult, true);
            problemResult.setPartyFinish(to.getPartyFinish());
            problemResult.setPartyIdea(to.getPartyIdea());
            super.update(problemResult);
            return BeanTransform.copyProperties(problemResult, ProblemResultBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemResultBO coordinate(ProblemResultTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            ProblemResult problemResult = super.findById(to.getId());
            BeanTransform.copyProperties(to, problemResult, true);
            if (to.getCoordinate().equals(false)) {
                problemResult.setFinalSolutionTime(LocalDateTime.now());
                problemResult.setProblemResult("已处理");
            } else if (to.getCoordinate().equals(true)) {
                problemResult.setProblemResult("待处理");
                String[] feilds = new String[]{"otherIdeaNum"};
                String sql = "SELECT count(*) as count FROM feedback_otheridea";
                List<ProblemResult> problemResults = super.findBySql(sql, ProblemResult.class, feilds);
                Integer otherIdeaNum = problemResults.get(0).getOtherIdeaNum();
                problemResult.setOtherIdeaNum(otherIdeaNum);

            }
            super.update(problemResult);
            return BeanTransform.copyProperties(problemResult, ProblemResultBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemResultBO result(ProblemResultTO to) throws SerException {
        checkAddIdentity();
        ProblemResult problemResult = super.findById(to.getId());
        BeanTransform.copyProperties(to, problemResult, true);
        problemResult.setCoordinateResult(to.getCoordinateResult());
        problemResult.setFinalSolutionTime(LocalDateTime.now());
        problemResult.setProblemResult("已处理");
        super.update(problemResult);
        return BeanTransform.copyProperties(problemResult, ProblemResultBO.class);
    }
}