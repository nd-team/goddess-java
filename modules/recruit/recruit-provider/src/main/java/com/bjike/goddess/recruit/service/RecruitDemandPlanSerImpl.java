package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.recruit.bo.RecruitDemandPlanBO;
import com.bjike.goddess.recruit.bo.RecruitProgressBO;
import com.bjike.goddess.recruit.dto.RecruitDemandPlanDTO;
import com.bjike.goddess.recruit.entity.RecruitDemandPlan;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandPlanTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 招聘需求与计划业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘需求与计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class RecruitDemandPlanSerImpl extends ServiceImpl<RecruitDemandPlan, RecruitDemandPlanDTO> implements RecruitDemandPlanSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

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

    @Override
    public Long count(RecruitDemandPlanDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public RecruitDemandPlanBO getId(String id) throws SerException {
        RecruitDemandPlan recruitDemandPlan = super.findById(id);
        RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
        return bo;
    }

    @Override
    public List<RecruitDemandPlanBO> list(RecruitDemandPlanDTO dto) throws SerException {
        List<RecruitDemandPlan> recruitDemandPlans = super.findByCis(dto);
        List<RecruitDemandPlanBO> recruitDemandPlanBOS = BeanTransform.copyProperties(recruitDemandPlans, RecruitDemandPlanBO.class);
        return recruitDemandPlanBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecruitDemandPlanBO save(RecruitDemandPlanTO to) throws SerException {
        RecruitDemandPlan recruitDemandPlan = BeanTransform.copyProperties(to, RecruitDemandPlan.class, true);
        recruitDemandPlan.setCreateTime(LocalDateTime.now());
        super.save(recruitDemandPlan);
        RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecruitDemandPlanBO update(RecruitDemandPlanTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            RecruitDemandPlan recruitDemandPlan = super.findById(to.getId());
            LocalDateTime createTime = recruitDemandPlan.getCreateTime();
            recruitDemandPlan = BeanTransform.copyProperties(to, RecruitDemandPlan.class, true);
            recruitDemandPlan.setCreateTime(createTime);
            recruitDemandPlan.setModifyTime(LocalDateTime.now());
            RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecruitDemandPlanBO makePlan(RecruitDemandPlanTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            RecruitDemandPlan recruitDemandPlan = super.findById(to.getId());
            LocalDateTime createTime = recruitDemandPlan.getCreateTime();
            recruitDemandPlan = BeanTransform.copyProperties(to, RecruitDemandPlan.class, true);
            recruitDemandPlan.setCreateTime(createTime);
            recruitDemandPlan.setModifyTime(LocalDateTime.now());
            RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<RecruitProgressBO> dayCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startTime = time;
        String endTime = time;
        return progressCollect(startTime, endTime);
    }

    private List<RecruitProgressBO> progressCollect(String startTime, String endTime) throws SerException {
        List<RecruitProgressBO> boList = new ArrayList<>();
        List<RecruitProgressBO> progressBOList = new ArrayList<>();
        //部门/项目组 人口缺口隐患(待离职人员) 已完成招聘人数 计划筛选简历数 计划邀约面试量 计划面试量 计划成功通过面试量 计划入职人数
        String[] fields = new String[]{"projectGroup", "populationGapNum", "completeNum", "planScreenNum",
                "planInviteNum", "planInterviewNum", "planPassNum", "planEntryNum"};
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT projectGroup AS projectGroup,count(populationGap) AS populationGapNum, ");
        sb.append(" sum(completeNum) AS  completeNum,sum(planScreenNum) AS planScreenNum, ");
        sb.append(" sum(planInterviewAmount) AS planInviteNum,sum(planInterviewNum) AS planInterviewNum, ");
        sb.append(" sum(planPassNum) AS planPassNum,sum(planEntryNum) AS planEntryNum");
        sb.append(" FROM recruit_recruitdemandplan WHERE time BETWEEN '" + startTime + "' AND '" + endTime + "' ");
        sb.append(" GROUP BY projectGroup ");
        List<RecruitProgressBO> progressBOS = super.findBySql(sb.toString(), RecruitProgressBO.class, fields);
        //下载简历数差异(实际筛选简历数-)
        Integer downloadBalance = 0;
        //实际筛选简历数
        Integer realityScreenNum = 0;
        if (progressBOS != null && progressBOS.size() > 0) {
            for (RecruitProgressBO progressBO : progressBOS) {
                //实际筛选简历数
                String[] realityFields = new String[]{"projectGroup", "realityScreenNum"};
                String realitySql = " SELECT projectGroup AS projectGroup,count(*) AS realityScreenNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' GROUP BY projectGroup ";
                progressBOList = super.findBySql(realitySql, RecruitProgressBO.class, realityFields);
                for (RecruitProgressBO bo : progressBOList) {
                    realityScreenNum = bo.getRealityScreenNum();
                    progressBO.setRealityScreenNum(realityScreenNum);
                    //下载简历数差异
                    downloadBalance = realityScreenNum - progressBO.getPlanScreenNum();
                    progressBO.setDownloadBalance(downloadBalance);
                }
                //电访数量
                String[] phoneFields = new String[]{"projectGroup", "phoneNum"};
                String phoneSql = " SELECT projectGroup AS projectGroup,count(whetherPhoneSuccess) AS phoneNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' GROUP BY projectGroup ";
                progressBOList = super.findBySql(phoneSql, RecruitProgressBO.class, phoneFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setRealityScreenNum(bo.getPhoneNum());
                }
                //预约面试量
                String[] orderFields = new String[]{"projectGroup", "orderNum"};
                String orderSql = " SELECT projectGroup AS projectGroup,count(whetherPhoneSuccess) AS orderNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' AND whetherPhoneSuccess = 1 GROUP BY projectGroup ";
                progressBOList = super.findBySql(orderSql, RecruitProgressBO.class, orderFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setOrderNum(bo.getOrderNum());
                }
                //邀约面试量
                String[] inviteFields = new String[]{"projectGroup", "inviteNum"};
                String inviteSql = " SELECT projectGroup AS projectGroup,count(whetherFirstInviteSuccess) AS inviteNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' AND whetherFirstInviteSuccess = 1 GROUP BY projectGroup ";
                progressBOList = super.findBySql(inviteSql, RecruitProgressBO.class, inviteFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setInviteNum(bo.getInviteNum());
                }
                //初试面试量
                String[] firstFields = new String[]{"projectGroup", "firstInterviewNum"};
                String firstSql = " SELECT department AS projectGroup,count(whetherFaceTest) AS firstInterviewNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' and whetherFaceTest = 1 GROUP BY department ";
                progressBOList = super.findBySql(firstSql, RecruitProgressBO.class, firstFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setFirstInterviewNum(bo.getFirstInterviewNum());
                }
                //复试面试量
                String[] secondFields = new String[]{"projectGroup", "secondInterviewNum"};
                String secondSql = " SELECT department AS department,count(secondTestAdvice) AS secondInterviewNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' GROUP BY department ";
                progressBOList = super.findBySql(secondSql, RecruitProgressBO.class, secondFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setSecondInterviewNum(bo.getSecondInterviewNum());
                }
                //薪资面谈量
                String[] accountFields = new String[]{"projectGroup", "accountInterviewNum"};
                String accountSql = " SELECT department AS projectGroup,count(faceAdvice) AS accountInterviewNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' GROUP BY department ";
                progressBOList = super.findBySql(accountSql, RecruitProgressBO.class, accountFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setAccountInterviewNum(bo.getAccountInterviewNum());
                }
                //成功通过面试量
                String[] successFields = new String[]{"projectGroup", "successPassNum"};
                String successSql = " SELECT department AS department,count(agreedEmployed) AS successPassNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' AND agreedEmployed = 1 GROUP BY department ";
                progressBOList = super.findBySql(successSql, RecruitProgressBO.class, successFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setSuccessPassNum(bo.getSuccessPassNum());
                }
                //入职量
                Integer entryNum = entryRegisterAPI.findNumByEntryDate(progressBO.getProjectGroup());
                progressBO.setEntryNum(entryNum);
                //面试率(初试面试量/邀约面试量*100%)
                Double interviewRate = progressBO.getFirstInterviewNum() / progressBO.getInviteNum() * 0.1;
                progressBO.setInterviewRate(interviewRate);
                //入职率(成功通过面试量/入职量*100%)
                Double inductionRate = progressBO.getSuccessPassNum() / progressBO.getEntryNum() * 0.1;
                progressBO.setInductionRate(inductionRate);
                boList.add(progressBO);
            }
        }
        return boList;
    }

    @Override
    public List<RecruitProgressBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    @Override
    public List<RecruitProgressBO> monthCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    @Override
    public List<RecruitProgressBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    @Override
    public List<RecruitProgressBO> yearCollect(Integer year) throws SerException {
        return null;
    }

    @Override
    public List<RecruitProgressBO> totalCollect(String time) throws SerException {
        return null;
    }
}