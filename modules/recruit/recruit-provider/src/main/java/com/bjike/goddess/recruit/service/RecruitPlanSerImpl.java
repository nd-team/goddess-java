package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.recruit.bo.CountBO;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.bo.SituationBO;
import com.bjike.goddess.recruit.bo.SituationTBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.entity.RecruitPlan;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitPlanTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitPlanSerImpl extends ServiceImpl<RecruitPlan, RecruitPlanDTO> implements RecruitPlanSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private FirstPhoneRecordSer firstPhoneRecordSer;
    @Autowired
    private InterviewInforSer interviewInforSer;

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
            flag = cusPermissionSer.busCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("1");
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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

    /**
     * 分页查询招聘计划
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<RecruitPlanBO> list(RecruitPlanDTO dto) throws SerException {
        checkSeeIdentity();
        List<RecruitPlan> list = super.findByPage(dto);
        List<RecruitPlanBO> listBO = BeanTransform.copyProperties(list, RecruitPlanBO.class);
        return listBO;
    }

    /**
     * 保存招聘计划
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RecruitPlanBO save(RecruitPlanTO to) throws SerException {
        checkAddIdentity();
        RecruitPlan failFirstInterviewReason = BeanTransform.copyProperties(to, RecruitPlan.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        RecruitPlanBO bo = BeanTransform.copyProperties(failFirstInterviewReason, RecruitPlanBO.class);
        return bo;
    }

    /**
     * 更新招聘计划
     *
     * @param to 招聘计划to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RecruitPlanTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            RecruitPlan model = super.findById(to.getId());
            if (model != null) {
                updateRecruitPlan(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新招聘计划
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRecruitPlan(RecruitPlanTO to, RecruitPlan model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除招聘计划
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(RecruitPlan entity) throws SerException {
        checkAddIdentity();
        super.remove(entity);
    }

    @Override
    public List<Object> countSituation(RecruitPlanDTO dto) throws SerException {
        checkSeeIdentity();
        List<Object> list = new ArrayList<>();
        LocalDate time = DateUtil.parseDate(dto.getTime());
        String area = dto.getArea();
        String depart = dto.getDepart();
        String position = dto.getPosition();
        InterviewInforDTO interviewInforDTO = new InterviewInforDTO();
        interviewInforDTO.getConditions().add(Restrict.eq("date", time));
        interviewInforDTO.getConditions().add(Restrict.eq("area", area));
        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
        firstPhoneRecordDTO.getConditions().add(Restrict.eq("date", time));
        RecruitPlanDTO recruitPlanDTO = new RecruitPlanDTO();
        recruitPlanDTO.getConditions().add(Restrict.eq("recruitDate", time));
        recruitPlanDTO.getConditions().add(Restrict.eq("recruitArea", area));
        if (StringUtils.isNotBlank(depart)) {
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitDepart", depart));
            interviewInforDTO.getConditions().add(Restrict.eq("department", depart));
        }
        if (StringUtils.isNotBlank(position)) {
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitPost", position));
            firstPhoneRecordDTO.getConditions().add(Restrict.eq("position", position));
            interviewInforDTO.getConditions().add(Restrict.eq("position", position));
        }
        List<RecruitPlan> recruitPlans = super.findByCis(recruitPlanDTO);
        List<FirstPhoneRecord> firstPhoneRecords = firstPhoneRecordSer.findByCis(firstPhoneRecordDTO);
        List<InterviewInfor> interviewInfors = interviewInforSer.findByCis(interviewInforDTO);
        int planDayResumeNo = 0;    //计划日简历筛选量
        int actualDayResumeNo = 0;   //实际日简历筛选量
        int planDayInviteNo = 0;   //计划日邀约面试量
        int actualDayInviteNo = 0;   //实际日邀约面试量
        int planDayInterviewNo = 0;   //计划日面试量
        int actualDayInterviewNo = 0;   //实际日面试量
        int planDayPassInterviewNo = 0;   //计划日成功通过面试量
        int actualDayPassInterviewNo = 0;   //实际日成功通过面试量
        int failInterReasonNum = 0;    //未邀约成功原因类型出现量
        int failInterNum = 0;     //未邀约成功量
        int denyFirViewNum = 0;   //未应约初试量
        int denyFirViewReasonNum = 0;  //未应约初试原因类型出现量
        int denyAdmitNum = 0;   //未接受录取量
        int denyAdmitReasonNum = 0;  //未接受录取原因类型出现量
        if (StringUtils.isNotBlank(depart) && StringUtils.isNotBlank(position)) {
            SituationTBO situationTBO = new SituationTBO();
            situationTBO.setDate(dto.getTime());
            situationTBO.setRecruitArea(area);
            situationTBO.setRecruitDepart(depart);
            situationTBO.setRecruitPosition(position);
            situationTBO.setId("2");
            for (String fi : fInviteReason()) {
                for (String fd : fDenyFirViewReason()) {
                    for (FirstPhoneRecord f : firstPhoneRecords) {
                        actualDayResumeNo++;
                        if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                            actualDayInviteNo++;
                        }
                        if ((f.getWhetherFirstInviteSuccess() != null) && (!f.getWhetherFirstInviteSuccess()) && fi.equals(f.getFailureInviteReason())) {
                            failInterReasonNum++;
                            failInterNum++;
                            situationTBO.setFailInterReason(fi);
                            situationTBO.setFailInterReasonNum(failInterReasonNum);
                            list.add(situationTBO);
                            failInterReasonNum = 0;
                        }
                        if ((f.getWhetherFirstInterview() != null) && (!f.getWhetherFirstInterview()) && fd.equals(f.getDenyFirViewReason())) {
                            denyFirViewReasonNum++;
                            denyFirViewNum++;
                            situationTBO.setDenyFirViewReason(fd);
                            situationTBO.setDenyFirViewReasonNum(denyFirViewReasonNum);
                            list.add(situationTBO);
                            denyFirViewReasonNum = 0;
                        }
                    }
                }
            }
            for (String id : iDenyAdmitReason()) {
                for (InterviewInfor i : interviewInfors) {
                    actualDayInterviewNo++;
                    if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                        actualDayPassInterviewNo++;
                    }
                    if ((i.getWhetherAcceptAdmit() != null) && (!i.getWhetherAcceptAdmit()) && id.equals(i.getDenyAdmitReason())) {
                        denyAdmitNum++;
                        denyAdmitReasonNum++;
                        situationTBO.setDenyAdmitReason(id);
                        situationTBO.setDenyAdmitReasonNum(denyAdmitReasonNum);
                        list.add(situationTBO);
                        denyAdmitReasonNum = 0;
                    }
                }
            }
            situationTBO.setFailInterNum(failInterNum);
            situationTBO.setDenyFirViewNum(denyFirViewNum);
            situationTBO.setDenyAdmitNum(denyAdmitNum);
            list.add(situationTBO);
            Set<String> set = allFirmPrincipal();
            for (String s : set) {
                for (RecruitPlan r : recruitPlans) {
                    if (s.equals(r.getFirmPrincipal())) {
                        planDayResumeNo += r.getPlanDayResumeNo();
                        planDayInviteNo += r.getPlanDayInviteNo();
                        planDayInterviewNo += r.getPlanDayInterviewNo();
                        planDayPassInterviewNo += r.getPlanDayPassInterviewNo();
                        SituationBO situationBO = new SituationBO();
                        situationBO.setDate(dto.getTime());
                        situationBO.setRecruitArea(area);
                        situationBO.setRecruitDepart(depart);
                        situationBO.setRecruitPosition(position);
                        situationBO.setPlanFilterCount(planDayResumeNo);
                        situationBO.setActualFilterCount(actualDayResumeNo);
                        situationBO.setDayFilterCountDiff(actualDayResumeNo - planDayResumeNo);
                        situationBO.setPlanInviteCount(planDayInviteNo);
                        situationBO.setActualInviteCount(actualDayInviteNo);
                        situationBO.setDayInviteCountDiff(actualDayInviteNo - planDayInviteNo);
                        situationBO.setPlanInterviewCount(planDayInterviewNo);
                        situationBO.setActualInterviewCount(actualDayInterviewNo);
                        situationBO.setDayInviteCountDiff(actualDayInterviewNo - planDayInterviewNo);
                        situationBO.setPlanPassInterviewCount(planDayPassInterviewNo);
                        situationBO.setActualPassInterviewCount(actualDayPassInterviewNo);
                        situationBO.setDayPassInterviewCount(actualDayPassInterviewNo - planDayPassInterviewNo);
                        situationBO.setPrincipalStaff(s);
                        situationBO.setId("1");
                        list.add(situationBO);
                        planDayResumeNo = 0;
                        planDayInviteNo = 0;
                        planDayInterviewNo = 0;
                        planDayPassInterviewNo = 0;   //置为0
                    }
                }
            }
            return list;
        } else if (StringUtils.isNotBlank(depart)) {
            return departCount(dto.getTime(), area, depart, interviewInfors, firstPhoneRecords, recruitPlans);
        } else if (StringUtils.isNotBlank(position)) {
            return positionCount(dto.getTime(), area, position, interviewInfors, firstPhoneRecords, recruitPlans);
        }
        SituationTBO situationTBO = new SituationTBO();
        situationTBO.setDate(dto.getTime());
        situationTBO.setRecruitArea(area);
        situationTBO.setId("2");
        for (String fi : fInviteReason()) {
            for (String fd : fDenyFirViewReason()) {
                for (FirstPhoneRecord f : firstPhoneRecords) {
                    actualDayResumeNo++;
                    if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                        actualDayInviteNo++;
                    }
                    if ((f.getWhetherFirstInviteSuccess() != null) && (!f.getWhetherFirstInviteSuccess()) && fi.equals(f.getFailureInviteReason())) {
                        failInterReasonNum++;
                        failInterNum++;
                        situationTBO.setFailInterReason(fi);
                        situationTBO.setFailInterReasonNum(failInterReasonNum);
                        list.add(situationTBO);
                        failInterReasonNum = 0;
                    }
                    if ((f.getWhetherFirstInterview() != null) && (!f.getWhetherFirstInterview()) && fd.equals(f.getDenyFirViewReason())) {
                        denyFirViewReasonNum++;
                        denyFirViewNum++;
                        situationTBO.setDenyFirViewReason(fd);
                        situationTBO.setDenyFirViewReasonNum(denyFirViewReasonNum);
                        list.add(situationTBO);
                        denyFirViewReasonNum = 0;
                    }
                }
            }
        }
        for (String id : iDenyAdmitReason()) {
            for (InterviewInfor i : interviewInfors) {
                actualDayInterviewNo++;
                if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                    actualDayPassInterviewNo++;
                }
                if ((i.getWhetherAcceptAdmit() != null) && (!i.getWhetherAcceptAdmit()) && id.equals(i.getDenyAdmitReason())) {
                    denyAdmitNum++;
                    denyAdmitReasonNum++;
                    situationTBO.setDenyAdmitReason(id);
                    situationTBO.setDenyAdmitReasonNum(denyAdmitReasonNum);
                    list.add(situationTBO);
                    denyAdmitReasonNum = 0;
                }
            }
        }
        situationTBO.setFailInterNum(failInterNum);
        situationTBO.setDenyFirViewNum(denyFirViewNum);
        situationTBO.setDenyAdmitNum(denyAdmitNum);
        list.add(situationTBO);
        Set<String> set = allFirmPrincipal();
        for (String s : set) {
            for (RecruitPlan r : recruitPlans) {
                if (s.equals(r.getFirmPrincipal())) {
                    planDayResumeNo += r.getPlanDayResumeNo();
                    planDayInviteNo += r.getPlanDayInviteNo();
                    planDayInterviewNo += r.getPlanDayInterviewNo();
                    planDayPassInterviewNo += r.getPlanDayPassInterviewNo();
                    SituationBO situationBO = new SituationBO();
                    situationBO.setDate(dto.getTime());
                    situationBO.setRecruitArea(area);
                    situationBO.setPlanFilterCount(planDayResumeNo);
                    situationBO.setActualFilterCount(actualDayResumeNo);
                    situationBO.setDayFilterCountDiff(actualDayResumeNo - planDayResumeNo);
                    situationBO.setPlanInviteCount(planDayInviteNo);
                    situationBO.setActualInviteCount(actualDayInviteNo);
                    situationBO.setDayInviteCountDiff(actualDayInviteNo - planDayInviteNo);
                    situationBO.setPlanInterviewCount(planDayInterviewNo);
                    situationBO.setActualInterviewCount(actualDayInterviewNo);
                    situationBO.setDayInviteCountDiff(actualDayInterviewNo - planDayInterviewNo);
                    situationBO.setPlanPassInterviewCount(planDayPassInterviewNo);
                    situationBO.setActualPassInterviewCount(actualDayPassInterviewNo);
                    situationBO.setDayPassInterviewCount(actualDayPassInterviewNo - planDayPassInterviewNo);
                    situationBO.setPrincipalStaff(s);
                    situationBO.setId("1");
                    list.add(situationBO);
                    planDayResumeNo = 0;
                    planDayInviteNo = 0;
                    planDayInterviewNo = 0;
                    planDayPassInterviewNo = 0;   //置为0
                }
            }
        }
        return list;
    }

    private List<Object> departCount(String time, String area, String depart, List<InterviewInfor> interviewInfors, List<FirstPhoneRecord> firstPhoneRecords, List<RecruitPlan> recruitPlans) throws SerException {
        List<Object> list = new ArrayList<>();
        int planDayResumeNo = 0;    //计划日简历筛选量
        int actualDayResumeNo = 0;   //实际日简历筛选量
        int planDayInviteNo = 0;   //计划日邀约面试量
        int actualDayInviteNo = 0;   //实际日邀约面试量
        int planDayInterviewNo = 0;   //计划日面试量
        int actualDayInterviewNo = 0;   //实际日面试量
        int planDayPassInterviewNo = 0;   //计划日成功通过面试量
        int actualDayPassInterviewNo = 0;   //实际日成功通过面试量
        int failInterReasonNum = 0;    //未邀约成功原因类型出现量
        int failInterNum = 0;     //未邀约成功量
        int denyFirViewNum = 0;   //未应约初试量
        int denyFirViewReasonNum = 0;  //未应约初试原因类型出现量
        int denyAdmitNum = 0;   //未接受录取量
        int denyAdmitReasonNum = 0;  //未接受录取原因类型出现量
        SituationTBO situationTBO = new SituationTBO();
        situationTBO.setDate(time);
        situationTBO.setRecruitArea(area);
        situationTBO.setRecruitDepart(depart);
        situationTBO.setId("2");
        for (String position : fPosition()) {
            for (String fi : fInviteReason()) {
                for (String fd : fDenyFirViewReason()) {
                    for (FirstPhoneRecord f : firstPhoneRecords) {
                        if (position.equals(f.getPosition())) {
                            actualDayResumeNo++;
                            if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                                actualDayInviteNo++;
                            }
                            if ((f.getWhetherFirstInviteSuccess() != null) && (!f.getWhetherFirstInviteSuccess()) && fi.equals(f.getFailureInviteReason())) {
                                failInterReasonNum++;
                                failInterNum++;
                                situationTBO.setRecruitPosition(position);
                                situationTBO.setFailInterReason(fi);
                                situationTBO.setFailInterReasonNum(failInterReasonNum);
                                list.add(situationTBO);
                                failInterReasonNum = 0;
                            }
                            if ((f.getWhetherFirstInterview() != null) && (!f.getWhetherFirstInterview()) && fd.equals(f.getDenyFirViewReason())) {
                                denyFirViewReasonNum++;
                                denyFirViewNum++;
                                situationTBO.setRecruitPosition(position);
                                situationTBO.setDenyFirViewReason(fd);
                                situationTBO.setDenyFirViewReasonNum(denyFirViewReasonNum);
                                list.add(situationTBO);
                                denyFirViewReasonNum = 0;
                            }
                        }
                    }
                }
            }
        }
        for (String position : iPosition()) {
            for (String id : iDenyAdmitReason()) {
                for (InterviewInfor i : interviewInfors) {
                    if (position.equals(i.getPosition())) {
                        actualDayInterviewNo++;
                        if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                            actualDayPassInterviewNo++;
                        }
                        if ((i.getWhetherAcceptAdmit() != null) && (!i.getWhetherAcceptAdmit()) && id.equals(i.getDenyAdmitReason())) {
                            denyAdmitNum++;
                            denyAdmitReasonNum++;
                            situationTBO.setDenyAdmitReason(id);
                            situationTBO.setDenyAdmitReasonNum(denyAdmitReasonNum);
                            situationTBO.setRecruitPosition(position);
                            list.add(situationTBO);
                            denyAdmitReasonNum = 0;
                        }
                    }
                }
            }
        }
        situationTBO.setFailInterNum(failInterNum);
        situationTBO.setDenyFirViewNum(denyFirViewNum);
        situationTBO.setDenyAdmitNum(denyAdmitNum);
        list.add(situationTBO);
        Set<String> set = allFirmPrincipal();
        for (String s : set) {
            for (String position : allRecruitPost()) {
                for (RecruitPlan r : recruitPlans) {
                    if (s.equals(r.getFirmPrincipal()) && position.equals(r.getRecruitPost())) {
                        planDayResumeNo += r.getPlanDayResumeNo();
                        planDayInviteNo += r.getPlanDayInviteNo();
                        planDayInterviewNo += r.getPlanDayInterviewNo();
                        planDayPassInterviewNo += r.getPlanDayPassInterviewNo();
                        SituationBO situationBO = new SituationBO();
                        situationBO.setDate(time);
                        situationBO.setRecruitArea(area);
                        situationBO.setRecruitDepart(depart);
                        situationBO.setRecruitPosition(position);
                        situationBO.setPlanFilterCount(planDayResumeNo);
                        situationBO.setActualFilterCount(actualDayResumeNo);
                        situationBO.setDayFilterCountDiff(actualDayResumeNo - planDayResumeNo);
                        situationBO.setPlanInviteCount(planDayInviteNo);
                        situationBO.setActualInviteCount(actualDayInviteNo);
                        situationBO.setDayInviteCountDiff(actualDayInviteNo - planDayInviteNo);
                        situationBO.setPlanInterviewCount(planDayInterviewNo);
                        situationBO.setActualInterviewCount(actualDayInterviewNo);
                        situationBO.setDayInviteCountDiff(actualDayInterviewNo - planDayInterviewNo);
                        situationBO.setPlanPassInterviewCount(planDayPassInterviewNo);
                        situationBO.setActualPassInterviewCount(actualDayPassInterviewNo);
                        situationBO.setDayPassInterviewCount(actualDayPassInterviewNo - planDayPassInterviewNo);
                        situationBO.setPrincipalStaff(s);
                        situationBO.setId("1");
                        list.add(situationBO);
                        planDayResumeNo = 0;
                        planDayInviteNo = 0;
                        planDayInterviewNo = 0;
                        planDayPassInterviewNo = 0;   //置为0
                    }
                }
            }
        }
        return list;
    }

    private List<Object> positionCount(String time, String area, String position, List<InterviewInfor> interviewInfors, List<FirstPhoneRecord> firstPhoneRecords, List<RecruitPlan> recruitPlans) throws SerException {
        List<Object> list = new ArrayList<>();
        int planDayResumeNo = 0;    //计划日简历筛选量
        int actualDayResumeNo = 0;   //实际日简历筛选量
        int planDayInviteNo = 0;   //计划日邀约面试量
        int actualDayInviteNo = 0;   //实际日邀约面试量
        int planDayInterviewNo = 0;   //计划日面试量
        int actualDayInterviewNo = 0;   //实际日面试量
        int planDayPassInterviewNo = 0;   //计划日成功通过面试量
        int actualDayPassInterviewNo = 0;   //实际日成功通过面试量
        int failInterReasonNum = 0;    //未邀约成功原因类型出现量
        int failInterNum = 0;     //未邀约成功量
        int denyFirViewNum = 0;   //未应约初试量
        int denyFirViewReasonNum = 0;  //未应约初试原因类型出现量
        int denyAdmitNum = 0;   //未接受录取量
        int denyAdmitReasonNum = 0;  //未接受录取原因类型出现量
        SituationTBO situationTBO = new SituationTBO();
        situationTBO.setDate(time);
        situationTBO.setRecruitArea(area);
        situationTBO.setRecruitPosition(position);
        situationTBO.setId("2");
        for (String fi : fInviteReason()) {
            for (String fd : fDenyFirViewReason()) {
                for (FirstPhoneRecord f : firstPhoneRecords) {
                    actualDayResumeNo++;
                    if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                        actualDayInviteNo++;
                    }
                    if ((f.getWhetherFirstInviteSuccess() != null) && (!f.getWhetherFirstInviteSuccess()) && fi.equals(f.getFailureInviteReason())) {
                        failInterReasonNum++;
                        failInterNum++;
                        situationTBO.setFailInterReason(fi);
                        situationTBO.setFailInterReasonNum(failInterReasonNum);
                        list.add(situationTBO);
                        failInterReasonNum = 0;
                    }
                    if ((f.getWhetherFirstInterview() != null) && (!f.getWhetherFirstInterview()) && fd.equals(f.getDenyFirViewReason())) {
                        denyFirViewReasonNum++;
                        denyFirViewNum++;
                        situationTBO.setDenyFirViewReason(fd);
                        situationTBO.setDenyFirViewReasonNum(denyFirViewReasonNum);
                        list.add(situationTBO);
                        denyFirViewReasonNum = 0;
                    }
                }
            }
        }
        for (String depart : iDepartment()) {
            for (String id : iDenyAdmitReason()) {
                for (InterviewInfor i : interviewInfors) {
                    if (depart.equals(i.getDepartment())) {
                        actualDayInterviewNo++;
                        if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                            actualDayPassInterviewNo++;
                        }
                        if ((i.getWhetherAcceptAdmit() != null) && (!i.getWhetherAcceptAdmit()) && id.equals(i.getDenyAdmitReason())) {
                            denyAdmitNum++;
                            denyAdmitReasonNum++;
                            situationTBO.setDenyAdmitReason(id);
                            situationTBO.setRecruitDepart(depart);
                            situationTBO.setDenyAdmitReasonNum(denyAdmitReasonNum);
                            list.add(situationTBO);
                            denyAdmitReasonNum = 0;
                        }
                    }
                }
            }
        }
        situationTBO.setFailInterNum(failInterNum);
        situationTBO.setDenyFirViewNum(denyFirViewNum);
        situationTBO.setDenyAdmitNum(denyAdmitNum);
        list.add(situationTBO);
        Set<String> set = allFirmPrincipal();
        for (String s : set) {
            for (String depart : allRecruitDepart()) {
                for (RecruitPlan r : recruitPlans) {
                    if (s.equals(r.getFirmPrincipal()) && depart.equals(r.getRecruitDepart())) {
                        planDayResumeNo += r.getPlanDayResumeNo();
                        planDayInviteNo += r.getPlanDayInviteNo();
                        planDayInterviewNo += r.getPlanDayInterviewNo();
                        planDayPassInterviewNo += r.getPlanDayPassInterviewNo();
                        SituationBO situationBO = new SituationBO();
                        situationBO.setDate(time);
                        situationBO.setRecruitArea(area);
                        situationBO.setRecruitDepart(depart);
                        situationBO.setRecruitPosition(position);
                        situationBO.setPlanFilterCount(planDayResumeNo);
                        situationBO.setActualFilterCount(actualDayResumeNo);
                        situationBO.setDayFilterCountDiff(actualDayResumeNo - planDayResumeNo);
                        situationBO.setPlanInviteCount(planDayInviteNo);
                        situationBO.setActualInviteCount(actualDayInviteNo);
                        situationBO.setDayInviteCountDiff(actualDayInviteNo - planDayInviteNo);
                        situationBO.setPlanInterviewCount(planDayInterviewNo);
                        situationBO.setActualInterviewCount(actualDayInterviewNo);
                        situationBO.setDayInviteCountDiff(actualDayInterviewNo - planDayInterviewNo);
                        situationBO.setPlanPassInterviewCount(planDayPassInterviewNo);
                        situationBO.setActualPassInterviewCount(actualDayPassInterviewNo);
                        situationBO.setDayPassInterviewCount(actualDayPassInterviewNo - planDayPassInterviewNo);
                        situationBO.setPrincipalStaff(s);
                        situationBO.setId("1");
                        list.add(situationBO);
                        planDayResumeNo = 0;
                        planDayInviteNo = 0;
                        planDayInterviewNo = 0;
                        planDayPassInterviewNo = 0;   //置为0
                    }
                }
            }
        }
        return list;
    }

    private Set<String> allFirmPrincipal() throws SerException {
        Set<String> set = new HashSet<>();
        List<RecruitPlan> list = super.findAll();
        for (RecruitPlan r : list) {
            set.add(r.getFirmPrincipal());
        }
        return set;
    }

    private Set<String> allRecruitDepart() throws SerException {
        Set<String> set = new HashSet<>();
        List<RecruitPlan> list = super.findAll();
        for (RecruitPlan r : list) {
            set.add(r.getRecruitDepart());
        }
        return set;
    }

    private Set<String> allRecruitPost() throws SerException {
        Set<String> set = new HashSet<>();
        List<RecruitPlan> list = super.findAll();
        for (RecruitPlan r : list) {
            set.add(r.getRecruitPost());
        }
        return set;
    }

    private Set<String> iDepartment() throws SerException {
        Set<String> set = new HashSet<>();
        List<InterviewInfor> list = interviewInforSer.findAll();
        for (InterviewInfor i : list) {
            set.add(i.getDepartment());
        }
        return set;
    }

    private Set<String> iPosition() throws SerException {
        Set<String> set = new HashSet<>();
        List<InterviewInfor> list = interviewInforSer.findAll();
        for (InterviewInfor i : list) {
            set.add(i.getPosition());
        }
        return set;
    }

    private Set<String> fPosition() throws SerException {
        Set<String> set = new HashSet<>();
        List<FirstPhoneRecord> list = firstPhoneRecordSer.findAll();
        for (FirstPhoneRecord f : list) {
            set.add(f.getPosition());
        }
        return set;
    }

    private Set<String> fInviteReason() throws SerException {
        Set<String> set = new HashSet<>();
        List<FirstPhoneRecord> list = firstPhoneRecordSer.findAll();
        for (FirstPhoneRecord f : list) {
            set.add(f.getFailureInviteReason());
        }
        return set;
    }

    private Set<String> fDenyFirViewReason() throws SerException {
        Set<String> set = new HashSet<>();
        List<FirstPhoneRecord> list = firstPhoneRecordSer.findAll();
        for (FirstPhoneRecord f : list) {
            set.add(f.getDenyFirViewReason());
        }
        return set;
    }

    private Set<String> iDenyAdmitReason() throws SerException {
        Set<String> set = new HashSet<>();
        List<InterviewInfor> list = interviewInforSer.findAll();
        for (InterviewInfor i : list) {
            set.add(i.getDenyAdmitReason());
        }
        return set;
    }

    @Override
    public List<CountBO> dayCount(RecruitPlanDTO dto) throws SerException {
        checkSeeIdentity();
        List<CountBO> list = new ArrayList<>();
        LocalDate time = DateUtil.parseDate(dto.getTime());
        String area = dto.getArea();
        String depart = dto.getDepart();
        String position = dto.getPosition();
        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
        firstPhoneRecordDTO.getConditions().add(Restrict.eq("date", time));
        InterviewInforDTO interviewInforDTO = new InterviewInforDTO();
        interviewInforDTO.getConditions().add(Restrict.eq("date", time));
        interviewInforDTO.getConditions().add(Restrict.eq("area", area));
        RecruitPlanDTO recruitPlanDTO = new RecruitPlanDTO();
        recruitPlanDTO.getConditions().add(Restrict.eq("recruitDate", time));
        recruitPlanDTO.getConditions().add(Restrict.eq("recruitArea", area));
        if (StringUtils.isNotBlank(depart)) {
            interviewInforDTO.getConditions().add(Restrict.eq("department", depart));
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitDepart", depart));
        }
        if (StringUtils.isNotBlank(position)) {
            firstPhoneRecordDTO.getConditions().add(Restrict.eq("position", position));
            interviewInforDTO.getConditions().add(Restrict.eq("position", position));
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitPost", position));
        }
        int planRecruitNo = 0;   //计划招聘人数
        int actualSel = 0;   //实际日简历筛选量
        int actualInterview = 0;   //实际日邀约面试量
        int actualFace = 0;   //实际日面试量
        int actualSuccess = 0;   //实际日成功通过面试量
        int employ = 0;   //录用量
        int entry = 0;   //入职量
        List<InterviewInfor> interviewInfors = interviewInforSer.findByCis(interviewInforDTO);
        List<FirstPhoneRecord> firstPhoneRecords = firstPhoneRecordSer.findByCis(firstPhoneRecordDTO);
        List<RecruitPlan> recruitPlans = super.findByCis(recruitPlanDTO);
        for (RecruitPlan r : recruitPlans) {
            planRecruitNo += r.getPlanRecruitNo();
        }
        for (FirstPhoneRecord f : firstPhoneRecords) {
            actualSel++;
            if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                actualInterview++;
            }
        }
        for (InterviewInfor i : interviewInfors) {
            actualFace++;
            if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                actualSuccess++;
            }
            if ((i.getWhetherPassBoss() != null) && i.getWhetherPassBoss()) {
                employ++;
            }
            if ((i.getWhetherEntry() != null) && i.getWhetherEntry()) {
                entry++;
            }
        }
        if (StringUtils.isNotBlank(depart) && StringUtils.isNotBlank(position)) {
            CountBO countBO = new CountBO();
            countBO.setDate(dto.getTime());
            countBO.setArea(area);
            countBO.setDepart(depart);
            countBO.setPosition(position);
            countBO.setPlanRecruitNo(planRecruitNo);
            countBO.setActualSel(actualSel);
            countBO.setActualInterview(actualInterview);
            countBO.setActualFace(actualFace);
            countBO.setActualSuccess(actualSuccess);
            countBO.setEmploy(employ);
            countBO.setEntry(entry);
            countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
            countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
            countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
            countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
            list.add(countBO);
            return list;
        } else if (StringUtils.isNotBlank(depart)) {
            return departCount1(dto.getTime(), area, depart, interviewInfors, firstPhoneRecords, recruitPlans);
        } else if (StringUtils.isNotBlank(position)) {
            return positionCount1(dto.getTime(), area, position, interviewInfors, firstPhoneRecords, recruitPlans);
        }
        CountBO countBO = new CountBO();
        countBO.setDate(dto.getTime());
        countBO.setArea(area);
        countBO.setPlanRecruitNo(planRecruitNo);
        countBO.setActualSel(actualSel);
        countBO.setActualInterview(actualInterview);
        countBO.setActualFace(actualFace);
        countBO.setActualSuccess(actualSuccess);
        countBO.setEmploy(employ);
        countBO.setEntry(entry);
        countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
        countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
        countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
        countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
        list.add(countBO);
        return list;
    }

//    @Override
//    public List<CountBO> weekCount(RecruitPlanDTO dto) throws SerException {
//        List<CountBO> list = new ArrayList<>();
//        int year = dto.getYear();
//        int month = dto.getMonth();
//        int week = dto.getWeek();
//        String time1 = month + "的第" + week + "周";
//        LocalDate[] time = getTimes(year, month, week);
//        String area = dto.getArea();
//        String depart = dto.getDepart();
//        String position = dto.getPosition();
//        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
//        firstPhoneRecordDTO.getConditions().add(Restrict.between("date", time));
//        InterviewInforDTO interviewInforDTO = new InterviewInforDTO();
//        interviewInforDTO.getConditions().add(Restrict.between("date", time));
//        interviewInforDTO.getConditions().add(Restrict.eq("area", area));
//        RecruitPlanDTO recruitPlanDTO = new RecruitPlanDTO();
//        recruitPlanDTO.getConditions().add(Restrict.between("recruitDate", time));
//        recruitPlanDTO.getConditions().add(Restrict.eq("recruitArea", area));
//        if (StringUtils.isNotBlank(depart)) {
//            interviewInforDTO.getConditions().add(Restrict.eq("department", depart));
//            recruitPlanDTO.getConditions().add(Restrict.eq("recruitDepart", depart));
//        }
//        if (StringUtils.isNotBlank(position)) {
//            firstPhoneRecordDTO.getConditions().add(Restrict.eq("position", position));
//            interviewInforDTO.getConditions().add(Restrict.eq("position", position));
//            recruitPlanDTO.getConditions().add(Restrict.eq("recruitPost", position));
//        }
//        int planRecruitNo = 0;   //计划招聘人数
//        int actualSel = 0;   //实际日简历筛选量
//        int actualInterview = 0;   //实际日邀约面试量
//        int actualFace = 0;   //实际日面试量
//        int actualSuccess = 0;   //实际日成功通过面试量
//        int employ = 0;   //录用量
//        int entry = 0;   //入职量
//        List<InterviewInfor> interviewInfors = interviewInforSer.findByCis(interviewInforDTO);
//        List<FirstPhoneRecord> firstPhoneRecords = firstPhoneRecordSer.findByCis(firstPhoneRecordDTO);
//        List<RecruitPlan> recruitPlans = super.findByCis(recruitPlanDTO);
//        for (RecruitPlan r : recruitPlans) {
//            planRecruitNo += r.getPlanRecruitNo();
//        }
//        for (FirstPhoneRecord f : firstPhoneRecords) {
//            actualSel++;
//            if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
//                actualInterview++;
//            }
//        }
//        for (InterviewInfor i : interviewInfors) {
//            actualFace++;
//            if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
//                actualSuccess++;
//            }
//            if ((i.getWhetherPassBoss() != null) && i.getWhetherPassBoss()) {
//                employ++;
//            }
//            if ((i.getWhetherEntry() != null) && i.getWhetherEntry()) {
//                entry++;
//            }
//        }
//        if (StringUtils.isNotBlank(depart) && StringUtils.isNotBlank(position)) {
//            CountBO countBO = new CountBO();
//            countBO.setDate(time1);
//            countBO.setArea(area);
//            countBO.setDepart(depart);
//            countBO.setPosition(position);
//            countBO.setPlanRecruitNo(planRecruitNo);
//            countBO.setActualSel(actualSel);
//            countBO.setActualInterview(actualInterview);
//            countBO.setActualFace(actualFace);
//            countBO.setActualSuccess(actualSuccess);
//            countBO.setEmploy(employ);
//            countBO.setEntry(entry);
//            countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
//            countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
//            countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
//            countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
//            list.add(countBO);
//            return list;
//        } else if (StringUtils.isNotBlank(depart)) {
//            return departCount1(time1, area, depart, interviewInfors, firstPhoneRecords, recruitPlans);
//        } else if (StringUtils.isNotBlank(position)) {
//            return positionCount1(time1, area, position, interviewInfors, firstPhoneRecords, recruitPlans);
//        }
//        return null;
//    }

    @Override
    public List<CountBO> weekCount(RecruitPlanDTO dto) throws SerException {
        checkSeeIdentity();
        List<CountBO> list = new ArrayList<>();
        int year = dto.getYear();
        int month = dto.getMonth();
        int week = dto.getWeek();
        String time1 = month + "月的第" + week + "周";
        LocalDate[] time = getTimes(year, month, week);
        String area = dto.getArea();
        String depart = dto.getDepart();
        String position = dto.getPosition();
        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
        firstPhoneRecordDTO.getConditions().add(Restrict.between("date", time));
        InterviewInforDTO interviewInforDTO = new InterviewInforDTO();
        interviewInforDTO.getConditions().add(Restrict.between("date", time));
        interviewInforDTO.getConditions().add(Restrict.eq("area", area));
        RecruitPlanDTO recruitPlanDTO = new RecruitPlanDTO();
        recruitPlanDTO.getConditions().add(Restrict.between("recruitDate", time));
        recruitPlanDTO.getConditions().add(Restrict.eq("recruitArea", area));
        if (StringUtils.isNotBlank(depart)) {
            interviewInforDTO.getConditions().add(Restrict.eq("department", depart));
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitDepart", depart));
        }
        if (StringUtils.isNotBlank(position)) {
            firstPhoneRecordDTO.getConditions().add(Restrict.eq("position", position));
            interviewInforDTO.getConditions().add(Restrict.eq("position", position));
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitPost", position));
        }
        int planRecruitNo = 0;   //计划招聘人数
        int actualSel = 0;   //实际日简历筛选量
        int actualInterview = 0;   //实际日邀约面试量
        int actualFace = 0;   //实际日面试量
        int actualSuccess = 0;   //实际日成功通过面试量
        int employ = 0;   //录用量
        int entry = 0;   //入职量
        List<InterviewInfor> interviewInfors = interviewInforSer.findByCis(interviewInforDTO);
        List<FirstPhoneRecord> firstPhoneRecords = firstPhoneRecordSer.findByCis(firstPhoneRecordDTO);
        List<RecruitPlan> recruitPlans = super.findByCis(recruitPlanDTO);
        for (RecruitPlan r : recruitPlans) {
            planRecruitNo += r.getPlanRecruitNo();
        }
        for (FirstPhoneRecord f : firstPhoneRecords) {
            actualSel++;
            if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                actualInterview++;
            }
        }
        for (InterviewInfor i : interviewInfors) {
            actualFace++;
            if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                actualSuccess++;
            }
            if ((i.getWhetherPassBoss() != null) && i.getWhetherPassBoss()) {
                employ++;
            }
            if ((i.getWhetherEntry() != null) && i.getWhetherEntry()) {
                entry++;
            }
        }
        if (StringUtils.isNotBlank(depart) && StringUtils.isNotBlank(position)) {
            CountBO countBO = new CountBO();
            countBO.setDate(time1);
            countBO.setArea(area);
            countBO.setDepart(depart);
            countBO.setPosition(position);
            countBO.setPlanRecruitNo(planRecruitNo);
            countBO.setActualSel(actualSel);
            countBO.setActualInterview(actualInterview);
            countBO.setActualFace(actualFace);
            countBO.setActualSuccess(actualSuccess);
            countBO.setEmploy(employ);
            countBO.setEntry(entry);
            countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
            countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
            countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
            countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
            list.add(countBO);
            return list;
        } else if (StringUtils.isNotBlank(depart)) {
            return departCount1(time1, area, depart, interviewInfors, firstPhoneRecords, recruitPlans);
        } else if (StringUtils.isNotBlank(position)) {
            return positionCount1(time1, area, position, interviewInfors, firstPhoneRecords, recruitPlans);
        }
        CountBO countBO = new CountBO();
        countBO.setDate(time1);
        countBO.setArea(area);
        countBO.setPlanRecruitNo(planRecruitNo);
        countBO.setActualSel(actualSel);
        countBO.setActualInterview(actualInterview);
        countBO.setActualFace(actualFace);
        countBO.setActualSuccess(actualSuccess);
        countBO.setEmploy(employ);
        countBO.setEntry(entry);
        countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
        countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
        countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
        countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
        list.add(countBO);
        return list;
    }

    @Override
    public List<CountBO> monthCount(RecruitPlanDTO dto) throws SerException {
        checkSeeIdentity();
        List<CountBO> list = new ArrayList<>();
        int year = dto.getYear();
        int month = dto.getMonth();
        String time1 = "第" + month + "月";
        Set<LocalDate> rTimes = time(year, month, rTimes());
        Set<LocalDate> fTimes = time(year, month, fTimes());
        Set<LocalDate> iTimes = time(year, month, iTimes());
        String area = dto.getArea();
        String depart = dto.getDepart();
        String position = dto.getPosition();
        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
//        firstPhoneRecordDTO.getConditions().add(Restrict.eq("date", fTimes));
        InterviewInforDTO interviewInforDTO = new InterviewInforDTO();
//        interviewInforDTO.getConditions().add(Restrict.eq("date", iTimes));
        interviewInforDTO.getConditions().add(Restrict.eq("area", area));
        RecruitPlanDTO recruitPlanDTO = new RecruitPlanDTO();
//        recruitPlanDTO.getConditions().add(Restrict.eq("recruitDate", rTimes));
        recruitPlanDTO.getConditions().add(Restrict.eq("recruitArea", area));
        if (StringUtils.isNotBlank(depart)) {
            interviewInforDTO.getConditions().add(Restrict.eq("department", depart));
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitDepart", depart));
        }
        if (StringUtils.isNotBlank(position)) {
            firstPhoneRecordDTO.getConditions().add(Restrict.eq("position", position));
            interviewInforDTO.getConditions().add(Restrict.eq("position", position));
            recruitPlanDTO.getConditions().add(Restrict.eq("recruitPost", position));
        }
        int planRecruitNo = 0;   //计划招聘人数
        int actualSel = 0;   //实际日简历筛选量
        int actualInterview = 0;   //实际日邀约面试量
        int actualFace = 0;   //实际日面试量
        int actualSuccess = 0;   //实际日成功通过面试量
        int employ = 0;   //录用量
        int entry = 0;   //入职量
        List<InterviewInfor> interviewInfor = interviewInforSer.findByCis(interviewInforDTO);
        List<FirstPhoneRecord> firstPhoneRecord= firstPhoneRecordSer.findByCis(firstPhoneRecordDTO);
        List<RecruitPlan> recruitPlan = super.findByCis(recruitPlanDTO);
        List<InterviewInfor> interviewInfors=new ArrayList<>();
        List<FirstPhoneRecord> firstPhoneRecords=new ArrayList<>();
        List<RecruitPlan> recruitPlans=new ArrayList<>();
        for (LocalDate l:iTimes){
            for (InterviewInfor i:interviewInfor){
                if (l.getYear()==i.getDate().getYear()&&l.getMonthValue()==i.getDate().getMonthValue()){
                    interviewInfors.add(i);
                }
            }
        }
        for (LocalDate l:fTimes){
            for (FirstPhoneRecord i:firstPhoneRecord){
                if (l.getYear()==i.getDate().getYear()&&l.getMonthValue()==i.getDate().getMonthValue()){
                    firstPhoneRecords.add(i);
                }
            }
        }for (LocalDate l:rTimes){
            for (RecruitPlan i:recruitPlan){
                if (l.getYear()==i.getRecruitDate().getYear()&&l.getMonthValue()==i.getRecruitDate().getMonthValue()){
                    recruitPlans.add(i);
                }
            }
        }
        for (RecruitPlan r : recruitPlans) {
            planRecruitNo += r.getPlanRecruitNo();
        }
        for (FirstPhoneRecord f : firstPhoneRecords) {
            actualSel++;
            if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                actualInterview++;
            }
        }
        for (InterviewInfor i : interviewInfors) {
            actualFace++;
            if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                actualSuccess++;
            }
            if ((i.getWhetherPassBoss() != null) && i.getWhetherPassBoss()) {
                employ++;
            }
            if ((i.getWhetherEntry() != null) && i.getWhetherEntry()) {
                entry++;
            }
        }
        if (StringUtils.isNotBlank(depart) && StringUtils.isNotBlank(position)) {
            CountBO countBO = new CountBO();
            countBO.setDate(time1);
            countBO.setArea(area);
            countBO.setDepart(depart);
            countBO.setPosition(position);
            countBO.setPlanRecruitNo(planRecruitNo);
            countBO.setActualSel(actualSel);
            countBO.setActualInterview(actualInterview);
            countBO.setActualFace(actualFace);
            countBO.setActualSuccess(actualSuccess);
            countBO.setEmploy(employ);
            countBO.setEntry(entry);
            countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
            countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
            countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
            countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
            list.add(countBO);
            return list;
        } else if (StringUtils.isNotBlank(depart)) {
            return departCount1(time1, area, depart, interviewInfors, firstPhoneRecords, recruitPlans);
        } else if (StringUtils.isNotBlank(position)) {
            return positionCount1(time1, area, position, interviewInfors, firstPhoneRecords, recruitPlans);
        }
        CountBO countBO = new CountBO();
        countBO.setDate(time1);
        countBO.setArea(area);
        countBO.setPlanRecruitNo(planRecruitNo);
        countBO.setActualSel(actualSel);
        countBO.setActualInterview(actualInterview);
        countBO.setActualFace(actualFace);
        countBO.setActualSuccess(actualSuccess);
        countBO.setEmploy(employ);
        countBO.setEntry(entry);
        countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
        countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
        countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
        countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
        list.add(countBO);
        return list;
    }

    private LocalDate[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        LocalDate s = DateUtil.parseDate(start);
        LocalDate[] time = new LocalDate[]{s, e};
        return time;
    }

    private List<CountBO> departCount1(String time, String area, String depart, List<InterviewInfor> interviewInfors, List<FirstPhoneRecord> firstPhoneRecords, List<RecruitPlan> recruitPlans) throws SerException {
        List<CountBO> list = new ArrayList<>();
        List<CountBO> list1 = new ArrayList<>();
        int planRecruitNo = 0;   //计划招聘人数
        int actualSel = 0;   //实际日简历筛选量
        int actualInterview = 0;   //实际日邀约面试量
        int actualFace = 0;   //实际日面试量
        int actualSuccess = 0;   //实际日成功通过面试量
        int employ = 0;   //录用量
        int entry = 0;   //入职量
        for (String position : allRecruitPost()) {
            for (RecruitPlan r : recruitPlans) {
                planRecruitNo += r.getPlanRecruitNo();
            }
            CountBO c = new CountBO();
            c.setPlanRecruitNo(planRecruitNo);
            c.setPosition(position);
            list1.add(c);
        }
        for (String position : fPosition()) {
            for (FirstPhoneRecord f : firstPhoneRecords) {
                actualSel++;
                if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                    actualInterview++;
                }
            }
            CountBO c = new CountBO();
            c.setActualSel(actualSel);
            c.setActualInterview(actualInterview);
            c.setPosition(position);
            list1.add(c);
        }
        for (String position : iPosition()) {
            for (InterviewInfor i : interviewInfors) {
                actualFace++;
                if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                    actualSuccess++;
                }
                if ((i.getWhetherPassBoss() != null) && i.getWhetherPassBoss()) {
                    employ++;
                }
                if ((i.getWhetherEntry() != null) && i.getWhetherEntry()) {
                    entry++;
                }
            }
            CountBO c = new CountBO();
            c.setActualFace(actualFace);
            c.setActualSuccess(actualSuccess);
            c.setEmploy(employ);
            c.setEntry(entry);
            c.setPosition(position);
            list1.add(c);
        }
        for (String position : iPosition()) {
            for (CountBO c : list1) {
                if (position.equals(c.getPosition())) {
                    if (c.getPlanRecruitNo() != null) {
                        planRecruitNo = c.getPlanRecruitNo();
                    }
                    if (c.getActualSel() != null) {
                        actualSel = c.getActualSel();
                    }
                    if (c.getActualInterview() != null) {
                        actualInterview = c.getActualInterview();
                    }
                    if (c.getActualFace() != null) {
                        actualFace = c.getActualFace();
                    }
                    if (c.getActualSuccess() != null) {
                        actualSuccess = c.getActualSuccess();
                    }
                    if (c.getEmploy() != null) {
                        employ = c.getEmploy();
                    }
                    if (c.getEntry() != null) {
                        entry = c.getEntry();
                    }
                }
            }
            CountBO countBO = new CountBO();
            countBO.setDate(time);
            countBO.setArea(area);
            countBO.setDepart(depart);
            countBO.setPosition(position);
            countBO.setPlanRecruitNo(planRecruitNo);
            countBO.setActualSel(actualSel);
            countBO.setActualInterview(actualInterview);
            countBO.setActualFace(actualFace);
            countBO.setActualSuccess(actualSuccess);
            countBO.setEmploy(employ);
            countBO.setEntry(entry);
            countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
            countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
            countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
            countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
            list.add(countBO);
            planRecruitNo = 0;
            actualSel = 0;
            actualInterview = 0;
            actualFace = 0;
            actualSuccess = 0;
            employ = 0;
            entry = 0;
        }
        return list;
    }

    private List<CountBO> positionCount1(String time, String area, String position, List<InterviewInfor> interviewInfors, List<FirstPhoneRecord> firstPhoneRecords, List<RecruitPlan> recruitPlans) throws SerException {
        List<CountBO> list = new ArrayList<>();
        List<CountBO> list1 = new ArrayList<>();
        int planRecruitNo = 0;   //计划招聘人数
        int actualSel = 0;   //实际日简历筛选量
        int actualInterview = 0;   //实际日邀约面试量
        int actualFace = 0;   //实际日面试量
        int actualSuccess = 0;   //实际日成功通过面试量
        int employ = 0;   //录用量
        int entry = 0;   //入职量
        for (String depart : allRecruitDepart()) {
            for (RecruitPlan r : recruitPlans) {
                planRecruitNo += r.getPlanRecruitNo();
            }
            CountBO c = new CountBO();
            c.setPlanRecruitNo(planRecruitNo);
            c.setDepart(depart);
            list1.add(c);
        }
        for (FirstPhoneRecord f : firstPhoneRecords) {
            actualSel++;
            if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                actualInterview++;
            }
        }
        CountBO c1 = new CountBO();
        c1.setActualSel(actualSel);
        c1.setActualInterview(actualInterview);
        list1.add(c1);
        for (String depart : iDepartment()) {
            for (InterviewInfor i : interviewInfors) {
                actualFace++;
                if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                    actualSuccess++;
                }
                if ((i.getWhetherPassBoss() != null) && i.getWhetherPassBoss()) {
                    employ++;
                }
                if ((i.getWhetherEntry() != null) && i.getWhetherEntry()) {
                    entry++;
                }
            }
            CountBO c = new CountBO();
            c.setActualFace(actualFace);
            c.setActualSuccess(actualSuccess);
            c.setEmploy(employ);
            c.setEntry(entry);
            c.setDepart(depart);
            list1.add(c);
        }
        for (String depart : iDepartment()) {
            for (CountBO c : list1) {
                if (depart.equals(c.getDepart())) {
                    if (c.getPlanRecruitNo() != null) {
                        planRecruitNo = c.getPlanRecruitNo();
                    }
                    if (c.getActualSel() != null) {
                        actualSel = c.getActualSel();
                    }
                    if (c.getActualInterview() != null) {
                        actualInterview = c.getActualInterview();
                    }
                    if (c.getActualFace() != null) {
                        actualFace = c.getActualFace();
                    }
                    if (c.getActualSuccess() != null) {
                        actualSuccess = c.getActualSuccess();
                    }
                    if (c.getEmploy() != null) {
                        employ = c.getEmploy();
                    }
                    if (c.getEntry() != null) {
                        entry = c.getEntry();
                    }
                }
            }
            CountBO countBO = new CountBO();
            countBO.setDate(time);
            countBO.setArea(area);
            countBO.setDepart(depart);
            countBO.setPosition(position);
            countBO.setPlanRecruitNo(planRecruitNo);
            countBO.setActualSel(actualSel);
            countBO.setActualInterview(actualInterview);
            countBO.setActualFace(actualFace);
            countBO.setActualSuccess(actualSuccess);
            countBO.setEmploy(employ);
            countBO.setEntry(entry);
            countBO.setSuccess(Double.valueOf(actualInterview) / Double.valueOf(actualSel));
            countBO.setComeFace(Double.valueOf(actualFace) / Double.valueOf(actualInterview));
            countBO.setEmployLV(Double.valueOf(employ) / Double.valueOf(actualFace));
            countBO.setStaffLV(Double.valueOf(employ) / Double.valueOf(entry));
            list.add(countBO);
            planRecruitNo = 0;
            actualFace = 0;
            actualSuccess = 0;
            employ = 0;
            entry = 0;
        }
        return list;
    }

    private Set<LocalDate> rTimes() throws SerException {
        List<RecruitPlan> list = super.findAll();
        Set<LocalDate> set = new HashSet<>();
        for (RecruitPlan r : list) {
            set.add(r.getRecruitDate());
        }
        return set;
    }

    private Set<LocalDate> fTimes() throws SerException {
        List<FirstPhoneRecord> list = firstPhoneRecordSer.findAll();
        Set<LocalDate> set = new HashSet<>();
        for (FirstPhoneRecord r : list) {
            set.add(r.getDate());
        }
        return set;
    }

    private Set<LocalDate> iTimes() throws SerException {
        List<InterviewInfor> list = interviewInforSer.findAll();
        Set<LocalDate> set = new HashSet<>();
        for (InterviewInfor r : list) {
            set.add(r.getDate());
        }
        return set;
    }

    private Set<LocalDate> time(int year, int month, Set<LocalDate> times) throws SerException {
        Set<LocalDate> set = new HashSet<>();
        for (LocalDate date : times) {
            if (year == date.getYear() && month == date.getMonthValue()) {
                set.add(date);
            }
        }
        return set;
    }
}
