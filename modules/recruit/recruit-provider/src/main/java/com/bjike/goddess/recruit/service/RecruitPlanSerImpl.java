package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        super.remove(entity);
    }

    @Override
    public List<SituationBO> countSituation(RecruitPlanDTO dto) throws SerException {
        List<SituationBO> list = new ArrayList<>();
        List<SituationTBO> tList = new ArrayList<>();
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
                            SituationTBO situationTBO = new SituationTBO();
                            situationTBO.setRecruitArea(area);
                            situationTBO.setRecruitDepart(depart);
                            situationTBO.setRecruitPosition(position);
                            situationTBO.setFailInterReason(fi);
                            situationTBO.setFailInterReasonNum(failInterReasonNum);
                            tList.add(situationTBO);
                            failInterReasonNum = 0;
                        }
                        if ((f.getWhetherFirstInterview() != null) && (!f.getWhetherFirstInterview()) && fd.equals(f.getDenyFirViewReason())) {
                            denyFirViewReasonNum++;
                            denyFirViewNum++;
                            SituationTBO situationTBO = new SituationTBO();
                            situationTBO.setRecruitArea(area);
                            situationTBO.setRecruitDepart(depart);
                            situationTBO.setRecruitPosition(position);
                            situationTBO.setDenyFirViewReason(fd);
                            situationTBO.setDenyFirViewReasonNum(denyFirViewReasonNum);
                            tList.add(situationTBO);
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
                        SituationTBO situationTBO = new SituationTBO();
                        situationTBO.setRecruitArea(area);
                        situationTBO.setRecruitDepart(depart);
                        situationTBO.setRecruitPosition(position);
                        situationTBO.setDenyAdmitReason(id);
                        situationTBO.setDenyAdmitReasonNum(denyAdmitReasonNum);
                        tList.add(situationTBO);
                        denyAdmitReasonNum=0;
                    }
                }
            }
            for (SituationTBO s:tList){
                if ()
            }
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
        return null;
    }

    private List<SituationBO> departCount(String time, String area, String depart, List<InterviewInfor> interviewInfors, List<FirstPhoneRecord> firstPhoneRecords, List<RecruitPlan> recruitPlans) throws SerException {
        List<SituationBO> list = new ArrayList<>();
        int planDayResumeNo = 0;    //计划日简历筛选量
        int actualDayResumeNo = 0;   //实际日简历筛选量
        int planDayInviteNo = 0;   //计划日邀约面试量
        int actualDayInviteNo = 0;   //实际日邀约面试量
        int planDayInterviewNo = 0;   //计划日面试量
        int actualDayInterviewNo = 0;   //实际日面试量
        int planDayPassInterviewNo = 0;   //计划日成功通过面试量
        int actualDayPassInterviewNo = 0;   //实际日成功通过面试量
        for (String position : fPosition()) {
            for (FirstPhoneRecord f : firstPhoneRecords) {
                if (position.equals(f.getPosition())) {
                    actualDayResumeNo++;
                    if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                        actualDayInviteNo++;
                    }
                }
            }
        }
        for (String position : iPosition()) {
            for (InterviewInfor i : interviewInfors) {
                if (position.equals(i.getPosition())) {
                    actualDayInterviewNo++;
                    if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                        actualDayPassInterviewNo++;
                    }
                }
            }
        }
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

    private List<SituationBO> positionCount(String time, String area, String position, List<InterviewInfor> interviewInfors, List<FirstPhoneRecord> firstPhoneRecords, List<RecruitPlan> recruitPlans) throws SerException {
        List<SituationBO> list = new ArrayList<>();
        int planDayResumeNo = 0;    //计划日简历筛选量
        int actualDayResumeNo = 0;   //实际日简历筛选量
        int planDayInviteNo = 0;   //计划日邀约面试量
        int actualDayInviteNo = 0;   //实际日邀约面试量
        int planDayInterviewNo = 0;   //计划日面试量
        int actualDayInterviewNo = 0;   //实际日面试量
        int planDayPassInterviewNo = 0;   //计划日成功通过面试量
        int actualDayPassInterviewNo = 0;   //实际日成功通过面试量
        for (FirstPhoneRecord f : firstPhoneRecords) {
            actualDayResumeNo++;
            if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
                actualDayInviteNo++;
            }
        }
        for (String depart : iDepartment()) {
            for (InterviewInfor i : interviewInfors) {
                if (depart.equals(i.getDepartment())) {
                    actualDayInterviewNo++;
                    if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
                        actualDayPassInterviewNo++;
                    }
                }
            }
        }
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
}
