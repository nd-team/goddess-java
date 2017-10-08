package com.bjike.goddess.regularization.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.regularization.bo.SummationBO;
import com.bjike.goddess.regularization.bo.TransferInfoBO;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.TransferInfo;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TransferInfoTO;
import com.bjike.goddess.regularization.type.GuideAddrStatus;
import com.bjike.goddess.regularization.type.StaffStatus;
import com.bjike.goddess.salarymanage.api.SalaryConfirmRecordAPI;
import com.bjike.goddess.salarymanage.enums.Probation;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 转正人员信息业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class TransferInfoSerImpl extends ServiceImpl<TransferInfo, TransferInfoDTO> implements TransferInfoSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private SalaryConfirmRecordAPI salaryConfirmRecordAPI;


    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);
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
     * 检查权限(层次)
     *
     * @throws SerException
     */
    private void checkArrPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(福利模块考察)
     *
     * @throws SerException
     */
    private void checkModWPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(规划模块考察)
     *
     * @throws SerException
     */
    private void checkModPPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(预算模块考察)
     *
     * @throws SerException
     */
    private void checkModBPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(模块负责人审核)
     *
     * @throws SerException
     */
    private void checkModPepolPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("7");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是模块负责人,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 检查权限(总经理岗位)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是总经理,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(项目经理岗位)
     *
     * @throws SerException
     */
    private void checkManagePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("8");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是项目经理,没有该操作权限");
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

    /**
     * 核对层次审核权限（层次）
     */
    private Boolean guideArrIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * 检查权限(福利模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModWIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 检查权限(规划模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModPIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
        } else {
            flag = true;
        }
        return flag;

    }

    /**
     * 检查权限(预算模块考察)
     *
     * @throws SerException
     */
    private Boolean guideModBIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
        } else {
            flag = true;
        }
        return flag;

    }

    /**
     * 核对模块负责人审核权限(岗位级别)
     *
     * @throws SerException
     */
    private Boolean guideModPepolIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("7");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对项目经理审核权限(岗位)
     *
     * @throws SerException
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("8");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagGuide = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideModW = guideModWIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideModP = guideModPIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideModB = guideModBIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideModPepol = guideModPepolIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuideMana = guideManageIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagGuide || flagGuideModW || flagGuideModP || flagGuideModB || flagGuideModPepol || flagGuidePosi || flagGuideMana) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 权限
     */
    private Boolean guideAllTrueIdentity() throws SerException {
        return true;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case ZZLIST:
                flag = guideAllTrueIdentity();
                break;
            case ZZADD:
                flag = guideAllTrueIdentity();
                break;
            case ZZEDIT:
                flag = guideAllTrueIdentity();
                break;
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
            case MANAGSCORE:
                flag = guideArrIdentity();
                break;
            case DECISIONSCORE:
                flag = guideArrIdentity();
                break;
            case PLANMODUL:
                flag = guideModPIdentity();
                break;
            case BUDGETMODUL:
                flag = guideModBIdentity();
                break;
            case AUDIT:
                flag = guidePosinIdentity();
                break;
            case WELFAREASSESS:
                flag = guideModWIdentity();
                break;
            case PLANASSESS:
                flag = guideModPIdentity();
                break;
            case BUDGETASSESS:
                flag = guideModBIdentity();
                break;
            case MODULERESPON:
                flag = guideModPepolIdentity();
                break;
            case PROJECTMANAGE:
                flag = guideManageIdentity();
                break;
            case GENMANAGE:
                flag = guidePosinIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countTrans(TransferInfoDTO transferInfoDTO) throws SerException {
        Long count = super.count(transferInfoDTO);
        return count;
    }

    @Override
    public TransferInfoBO getOne(String id) throws SerException {
        TransferInfo transferInfo = super.findById(id);
        return BeanTransform.copyProperties(transferInfo, TransferInfoBO.class);
    }

    @Override
    public List<TransferInfoBO> list(TransferInfoDTO dto) throws SerException {
        checkPermission();
        List<TransferInfo> transferInfos = super.findByCis(dto);
        return BeanTransform.copyProperties(transferInfos, TransferInfoBO.class);
    }

    @Override
    public TransferInfoBO findByEmpNo(String empNo) throws SerException {
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.eq("empNo", empNo));
        TransferInfo transferInfo = super.findOne(transferInfoDTO);
        return BeanTransform.copyProperties(transferInfo, TransferInfoBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void saveTransferInfo(TransferInfoTO transferInfoTO) throws SerException {
        TransferInfo transferInfo = BeanTransform.copyProperties(transferInfoTO, TransferInfo.class, true);
        String probationaryPer = null;
        LocalDate probationDue = null;
        if (moduleAPI.isCheck("salarymanage")) {
            Probation probation = salaryConfirmRecordAPI.findProbationById(transferInfoTO.getEmpNo());
            if (null != probation) {
                switch (probation) {
                    case ONEMONTH:
                        probationaryPer = "一个月";
                        probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(1);
                        break;
                    case ONETWOMONTH:
                        probationaryPer = "1-3个月";
                        probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(3);
                        break;
                    case THREEMONTH:
                        probationaryPer = "三个月";
                        probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(3);
                        break;
                    default:
                        probationaryPer = null;
                        break;
                }
            }
        }
        transferInfo.setCreateTime(LocalDateTime.now());
        transferInfo.setStaffStatus(StaffStatus.PROBATION);
        if (null!=probationaryPer){
            transferInfo.setProbationaryPer(probationaryPer);
            transferInfo.setProbationDue(probationDue);
        }
        super.save(transferInfo);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void followUp(TransferInfoTO transferInfoTO) throws SerException {
        checkPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        transferInfo.setThreeFollow(transferInfoTO.getThreeFollow());
        transferInfo.setThreeFollowOpinion(transferInfoTO.getThreeFollowOpinion());
        transferInfo.setWeekFollow(transferInfoTO.getWeekFollow());
        transferInfo.setWeekFollowOpinion(transferInfoTO.getWeekFollowOpinion());
        transferInfo.setMonthFollow(transferInfoTO.getMonthFollow());
        transferInfo.setMonthFollowOpinion(transferInfoTO.getMonthFollowOpinion());
        transferInfo.setModifyTime(LocalDateTime.now());
        super.update(transferInfo);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void welfareAssess(TransferInfoTO transferInfoTO) throws SerException {
        checkModWPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getStaffStatus() == StaffStatus.POSITIVE) {
            transferInfo.setApplyDateAtten(transferInfoTO.getApplyDateAtten());
            transferInfo.setRewardPunOpinion(transferInfoTO.getRewardPunOpinion());
            transferInfo.setModifyTime(LocalDateTime.now());
            super.update(transferInfo);
        } else {
            throw new SerException("该员工还没有申请转正或者已经转正了");
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void planAssess(TransferInfoTO transferInfoTO) throws SerException {
        checkModPPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getStaffStatus() == StaffStatus.POSITIVE) {
            transferInfo.setAdditionalSkill(transferInfoTO.getAdditionalSkill());
            transferInfo.setAdditionalSkillGrade(transferInfoTO.getAdditionalSkillGrade());
            transferInfo.setEventsSkill(transferInfoTO.getEventsSkill());
            transferInfo.setEventsSkillGrade(transferInfoTO.getEventsSkillGrade());
            transferInfo.setConformStaffing(transferInfoTO.getConformStaffing());
            super.update(transferInfo);
        } else {
            throw new SerException("该员工还没有申请转正或者已经转正了");
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void budgetAssess(TransferInfoTO transferInfoTO) throws SerException {
        checkModBPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getStaffStatus() == StaffStatus.POSITIVE) {
            transferInfo.setIncomeCostOpinion(transferInfoTO.getIncomeCostOpinion());
            super.update(transferInfo);
        } else {
            throw new SerException("该员工还没有申请转正或者已经转正了");
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void moduleRespon(TransferInfoTO transferInfoTO) throws SerException {
        checkModPepolPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getModuleLeader() == null) {
            if (transferInfo.getApplyDateAtten() == null || transferInfo.getAdditionalSkill() == null || transferInfo.getIncomeCostOpinion() == null) {
                throw new SerException("需要福利模块,规划模块,预算模块先填写将考察内容");
            } else {
                transferInfo.setModuleLeader(transferInfoTO.getModuleLeader());
                transferInfo.setModuleLeaderOpinion(transferInfoTO.getModuleLeaderOpinion());
                super.update(transferInfo);
            }
        } else {
            throw new SerException("您已经审核过了");
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void projectManage(TransferInfoTO transferInfoTO) throws SerException {
        checkManagePermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getProManage() == null) {
            if (transferInfo.getModuleLeader() == null) {
                throw new SerException("需要模块负责人先审核");
            } else {
                transferInfo.setProManage(transferInfoTO.getProManage());
                transferInfo.setProManageOpinion(transferInfoTO.getProManageOpinion());
                super.update(transferInfo);
            }
        } else {
            throw new SerException("您已经审核过了");
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void genManage(TransferInfoTO transferInfoTO) throws SerException {
        checkPonsPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getGenerManage() == null) {
            if (transferInfo.getProManage() == null) {
                throw new SerException("需要项目经理先审核");
            } else {
                transferInfo.setGenerManage(transferInfoTO.getGenerManage());
                transferInfo.setGenerManageOpinion(transferInfoTO.getGenerManageOpinion());
                transferInfo.setConsentPositive(transferInfoTO.getConsentPositive());//是否同意转正
                transferInfo.setPositiveStartDate(DateUtil.parseDate(transferInfoTO.getPositiveStartDate()));
                transferInfo.setPositiveThrough(transferInfoTO.getConsentPositive());//转正是否通过
                //如果已经通过
                if (transferInfoTO.getConsentPositive()) {
                    LocalDate time = transferInfo.getHiredate();
                    LocalDate date = transferInfo.getPositiveStartDate();
                    int monthDiff = (date.getYear() - time.getYear()) * 12 + date.getMonthValue() - time.getMonthValue();
                    transferInfo.setPraProbationaryPer(monthDiff);
                    transferInfo.setStaffStatus(StaffStatus.BECOMEMEM);
                } else {
                    transferInfo.setStaffStatus(StaffStatus.NOPASS);
                }
                super.update(transferInfo);
            }
        } else {
            throw new SerException("您已经审核过了");
        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void interview(TransferInfoTO transferInfoTO) throws SerException {
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getInterviewPeper() == null) {
            if (transferInfo.getGenerManage() == null) {
                throw new SerException("需要总经理先审核");
            } else {
                transferInfo.setInterviewPeper(transferInfoTO.getInterviewPeper());
                transferInfo.setInterviewContent(transferInfoTO.getInterviewContent());
                super.update(transferInfo);
            }
        } else {
            throw new SerException("您已经填写过了");
        }
    }

    //定时检测所有在试用期的员工是否到期未申请转正
    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void check() throws SerException {
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.eq("staffStatus", StaffStatus.PROBATION));
        List<TransferInfo> transferInfos = super.findByCis(transferInfoDTO);
        for (TransferInfo transferInfo : transferInfos) {
            if (transferInfo.getProbationDue().isBefore(LocalDate.now())) {
                transferInfo.setStaffStatus(StaffStatus.STAYPOSITIVE);
            }
            super.update(transferInfo);
        }
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        checkPermission();
        List<SummationBO> summationBOS = new ArrayList<>();
        String sql = "select min(createTime) as createTime from  " + getTableName(TransferInfo.class);
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            String startDate = StringUtils.substringBefore(String.valueOf(objects.get(0)), " ");
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
            summationBOS = totalMethod(startDate, endDate);
        }
        return summationBOS;
    }

    //汇总总方法
    public List<SummationBO> totalMethod(String startDate, String endDate) throws SerException {
        List<SummationBO> summationBOList = new ArrayList<>();
        List<String> areas = findArea();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                List<String> deps = findDepByArea(area);
                if (deps != null && deps.size() > 0) {
                    for (String dep : deps) {
                        Integer entryNum = entryNum(area, dep, startDate, endDate);//入职数量
                        Integer threeFollowNum = entryNum(area, dep, threeFront(startDate), threeFront(endDate));//三天需跟进收集意见数
                        Integer weekFollowNum = entryNum(area, dep, weekFront(startDate), threeFront(endDate));//一周需跟进收集意见数
                        Integer monthFollowNum = entryNum(area, dep, monthFront(startDate), monthFront(endDate));//一个月需跟进收集意见数
                        Integer stayPositiveNum = stayPositiveNum(area, dep, startDate, endDate);//待转正人数
                        Integer applyPositiveNum = applyPositiveNum(area, dep, startDate, endDate);//申请转正人数
                        Integer hasPositiveNum = hasPositiveNum(area, dep, startDate, endDate);//已转正人数
                        Integer noInterviewNum = noInterviewNum(area, dep, startDate, endDate);//已转正面谈人数
                        Integer noPositiveNum = noPositiveNum(area, dep, startDate, endDate);//未转正人数
                        SummationBO summationBO = new SummationBO();
                        summationBO.setArea(area);//地区
                        summationBO.setDepartment(dep);//项目组
                        summationBO.setEntryNum(entryNum);//入职数量
                        summationBO.setThreeFollowNum(threeFollowNum);//三天需跟进收集意见数
                        summationBO.setWeekFollowNum(weekFollowNum);//一周需跟进收集意见数
                        summationBO.setMonthFollowNum(monthFollowNum);//一个月需跟进收集意见数
                        summationBO.setStayPositiveNum(stayPositiveNum);//待转正人数
                        summationBO.setApplyPositiveNum(applyPositiveNum);//申请转正人数
                        summationBO.setHasPositiveNum(hasPositiveNum);//已转正人数
                        summationBO.setNoInterviewNum(noInterviewNum);//已转正面谈人数
                        summationBO.setNoPositiveNum(noPositiveNum);//未转正人数
                        summationBOList.add(summationBO);
                    }
                }
            }
        }
        return summationBOList;
    }

    @Override
    public List<String> findArea() throws SerException {
        List<TransferInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (TransferInfo model : list) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findDepByArea(String area) throws SerException {
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.eq("area", area));
        List<TransferInfo> list = super.findByCis(transferInfoDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (TransferInfo model : list) {
            String dep = model.getDepartment();
            if (StringUtils.isNotBlank(model.getDepartment())) {
                set.add(dep);
            }
        }
        return new ArrayList<>(set);
    }

    //指定日期的前三天
    private String threeFront(String date) throws SerException {
        LocalDate time = DateUtil.parseDate(date);
        return time.minusDays(3).toString();
    }

    //指定日期的前一周
    private String weekFront(String date) throws SerException {
        LocalDate time = DateUtil.parseDate(date);
        return time.minusWeeks(1).toString();
    }

    //指定日期的前一个月
    private String monthFront(String date) throws SerException {
        LocalDate time = DateUtil.parseDate(date);
        return time.minusMonths(1).toString();
    }

    /**
     * 根据时间地区部门查询入职人数
     * 三天需跟进收集意见数
     * 一周需跟进收集意见数
     * 一个月需跟进收集意见数
     *
     * @param area
     * @param department
     * @param startSummDate
     * @param endSummDate
     * @return
     * @throws SerException
     */
    public Integer entryNum(String area, String department, String startSummDate, String endSummDate) throws SerException {
        Integer number = 0;
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.between("hiredate", new String[]{startSummDate, endSummDate}));
        transferInfoDTO.getConditions().add(Restrict.eq("area", area));
        transferInfoDTO.getConditions().add(Restrict.eq("department", department));
        List<TransferInfo> transferInfos = super.findByCis(transferInfoDTO);
        if (transferInfos != null) {
            number = transferInfos.size();
        }
        return number;
    }

    //待转正人数
    public Integer stayPositiveNum(String area, String department, String startSummDate, String endSummDate) throws SerException {
        Integer entryNum = 0;
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.between("hiredate", new String[]{startSummDate, endSummDate}));
        transferInfoDTO.getConditions().add(Restrict.eq("area", area));
        transferInfoDTO.getConditions().add(Restrict.eq("department", department));
        transferInfoDTO.getConditions().add(Restrict.eq("staffStatus", StaffStatus.STAYPOSITIVE));
        List<TransferInfo> transferInfos = super.findByCis(transferInfoDTO);
        if (transferInfos != null) {
            entryNum = transferInfos.size();
        }
        return entryNum;
    }

    //申请转正人数
    public Integer applyPositiveNum(String area, String department, String startSummDate, String endSummDate) throws SerException {
        Integer entryNum = 0;
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.between("hiredate", new String[]{startSummDate, endSummDate}));
        transferInfoDTO.getConditions().add(Restrict.eq("area", area));
        transferInfoDTO.getConditions().add(Restrict.eq("department", department));
        transferInfoDTO.getConditions().add(Restrict.eq("staffStatus", StaffStatus.POSITIVE));
        List<TransferInfo> transferInfos = super.findByCis(transferInfoDTO);
        if (transferInfos != null) {
            entryNum = transferInfos.size();
        }
        return entryNum;
    }

    //已转正人数
    public Integer hasPositiveNum(String area, String department, String startSummDate, String endSummDate) throws SerException {
        Integer entryNum = 0;
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.between("hiredate", new String[]{startSummDate, endSummDate}));
        transferInfoDTO.getConditions().add(Restrict.eq("area", area));
        transferInfoDTO.getConditions().add(Restrict.eq("department", department));
        transferInfoDTO.getConditions().add(Restrict.eq("staffStatus", StaffStatus.BECOMEMEM));
        List<TransferInfo> transferInfos = super.findByCis(transferInfoDTO);
        if (transferInfos != null) {
            entryNum = transferInfos.size();
        }
        return entryNum;
    }

    //已转正面谈人数
    public Integer noInterviewNum(String area, String department, String startSummDate, String endSummDate) throws SerException {
        Integer entryNum = 0;
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.between("hiredate", new String[]{startSummDate, endSummDate}));
        transferInfoDTO.getConditions().add(Restrict.eq("area", area));
        transferInfoDTO.getConditions().add(Restrict.eq("department", department));
        transferInfoDTO.getConditions().add(Restrict.isNotNull("interviewPeper"));
        List<TransferInfo> transferInfos = super.findByCis(transferInfoDTO);
        if (transferInfos != null) {
            entryNum = transferInfos.size();
        }
        return entryNum;
    }

    //未转正人数
    public Integer noPositiveNum(String area, String department, String startSummDate, String endSummDate) throws SerException {
        Integer entryNum = 0;
        TransferInfoDTO transferInfoDTO = new TransferInfoDTO();
        transferInfoDTO.getConditions().add(Restrict.between("hiredate", new String[]{startSummDate, endSummDate}));
        transferInfoDTO.getConditions().add(Restrict.eq("area", area));
        transferInfoDTO.getConditions().add(Restrict.eq("department", department));
        transferInfoDTO.getConditions().add(Restrict.ne("staffStatus", StaffStatus.POSITIVE));
        List<TransferInfo> transferInfos = super.findByCis(transferInfoDTO);
        if (transferInfos != null) {
            entryNum = transferInfos.size();
        }
        return entryNum;
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
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
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }

}