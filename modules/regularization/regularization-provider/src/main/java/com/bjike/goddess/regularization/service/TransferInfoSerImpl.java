package com.bjike.goddess.regularization.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.intromanage.excel.FirmIntroExport;
import com.bjike.goddess.intromanage.excel.FirmIntroExportTemple;
import com.bjike.goddess.intromanage.type.DemandType;
import com.bjike.goddess.regularization.bo.*;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.Regularization;
import com.bjike.goddess.regularization.entity.TransferInfo;
import com.bjike.goddess.regularization.excel.TransferInfoTemple;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TransferInfoExcelTO;
import com.bjike.goddess.regularization.to.TransferInfoTO;
import com.bjike.goddess.regularization.type.GuideAddrStatus;
import com.bjike.goddess.regularization.type.StaffStatus;
import com.bjike.goddess.salarymanage.api.SalaryConfirmRecordAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private RegularizationSer regularizationSer;


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
        LocalDate probationDue = null;
        switch (transferInfoTO.getProbationaryPer()) {
            case "1个月":
                probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(1);
                break;
            case "1-3个月":
                probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(3);
                break;
            case "3个月":
                probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(3);
                break;
            default:
                throw new SerException("请输入正确的格式,正确格式为(1个月/1-3个月/3个月)");
        }

//        if (moduleAPI.isCheck("salarymanage")) {
//            Probation probation = salaryConfirmRecordAPI.findProbationById(transferInfoTO.getEmpNo());
//            if (null != probation) {
//                switch (probation) {
//                    case ONEMONTH:
//                        probationaryPer = "一个月";
//                        probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(1);
//                        break;
//                    case ONETWOMONTH:
//                        probationaryPer = "1-3个月";
//                        probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(3);
//                        break;
//                    case THREEMONTH:
//                        probationaryPer = "三个月";
//                        probationDue = DateUtil.parseDate(transferInfoTO.getHiredate()).plusMonths(3);
//                        break;
//                    default:
//                        probationaryPer = null;
//                        break;
//                }
//            }
//        }
        transferInfo.setCreateTime(LocalDateTime.now());
        transferInfo.setStaffStatus(StaffStatus.PROBATION);
        transferInfo.setProbationDue(probationDue);
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
        switch ( transferInfo.getStaffStatus() ){
            case POSITIVE:
                transferInfo.setApplyDateAtten(transferInfoTO.getApplyDateAtten());
                transferInfo.setRewardPunOpinion(transferInfoTO.getRewardPunOpinion());
                transferInfo.setModifyTime(LocalDateTime.now());
                super.update(transferInfo);
                break;
            case BECOMEMEM:
                throw new SerException("该员工已转正,不能进行跟进");
            case STAYPOSITIVE:
                throw new SerException("该员工还没有申请转正,请先申请转正");
            case PROBATION:
                throw new SerException("该员工还没有申请转正,请先申请转正");
            case NOPASS:
                throw new SerException("该员工转正不通过,请重新申请转正");
        }
//        if (transferInfo.getStaffStatus() == StaffStatus.POSITIVE) {
//            transferInfo.setApplyDateAtten(transferInfoTO.getApplyDateAtten());
//            transferInfo.setRewardPunOpinion(transferInfoTO.getRewardPunOpinion());
//            transferInfo.setModifyTime(LocalDateTime.now());
//            super.update(transferInfo);
//        } else {
//            throw new SerException("该员工还没有申请转正或者已经转正了");
//        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void planAssess(TransferInfoTO transferInfoTO) throws SerException {
        checkModPPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        switch ( transferInfo.getStaffStatus() ){
            case POSITIVE:
                transferInfo.setAdditionalSkill(transferInfoTO.getAdditionalSkill());
                transferInfo.setAdditionalSkillGrade(transferInfoTO.getAdditionalSkillGrade());
                transferInfo.setEventsSkill(transferInfoTO.getEventsSkill());
                transferInfo.setEventsSkillGrade(transferInfoTO.getEventsSkillGrade());
                transferInfo.setConformStaffing(transferInfoTO.getConformStaffing());
                super.update(transferInfo);
                break;
            case BECOMEMEM:
                throw new SerException("该员工已转正,不能进行跟进");
            case STAYPOSITIVE:
                throw new SerException("该员工还没有申请转正,请先申请转正");
            case PROBATION:
                throw new SerException("该员工还没有申请转正,请先申请转正");
            case NOPASS:
                throw new SerException("该员工转正不通过,请重新申请转正");
        }
//        if (transferInfo.getStaffStatus() == StaffStatus.POSITIVE) {
//            transferInfo.setAdditionalSkill(transferInfoTO.getAdditionalSkill());
//            transferInfo.setAdditionalSkillGrade(transferInfoTO.getAdditionalSkillGrade());
//            transferInfo.setEventsSkill(transferInfoTO.getEventsSkill());
//            transferInfo.setEventsSkillGrade(transferInfoTO.getEventsSkillGrade());
//            transferInfo.setConformStaffing(transferInfoTO.getConformStaffing());
//            super.update(transferInfo);
//        } else {
//            throw new SerException("该员工还没有申请转正或者已经转正了");
//        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void budgetAssess(TransferInfoTO transferInfoTO) throws SerException {
        checkModBPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        switch ( transferInfo.getStaffStatus() ){
            case POSITIVE:
                transferInfo.setIncomeCostOpinion(transferInfoTO.getIncomeCostOpinion());
                super.update(transferInfo);
                break;
            case BECOMEMEM:
                throw new SerException("该员工已转正,不能进行跟进");
            case STAYPOSITIVE:
                throw new SerException("该员工还没有申请转正,请先申请转正");
            case PROBATION:
                throw new SerException("该员工还没有申请转正,请先申请转正");
            case NOPASS:
                throw new SerException("该员工转正不通过,请重新申请转正");
        }
//        if (transferInfo.getStaffStatus() == StaffStatus.POSITIVE) {
//            transferInfo.setIncomeCostOpinion(transferInfoTO.getIncomeCostOpinion());
//            super.update(transferInfo);
//        } else {
//            throw new SerException("该员工还没有申请转正或者已经转正了");
//        }
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void moduleRespon(TransferInfoTO transferInfoTO) throws SerException {
        checkModPepolPermission();
        TransferInfo transferInfo = super.findById(transferInfoTO.getId());
        if (transferInfo.getModuleLeader() == null) {
            if (transferInfo.getApplyDateAtten() == null || transferInfo.getAdditionalSkill() == null || transferInfo.getIncomeCostOpinion() == null) {
                throw new SerException("需要福利模块,规划模块,预算模块先填写考察内容");
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
                RegularizationBO regularizationBO = regularizationSer.findByEmpNo(transferInfo.getEmpNo());
                Regularization regularization = regularizationSer.findById(regularizationBO.getId());
                regularization.setPassed(transferInfoTO.getConsentPositive());
                regularization.setPositiveStartDate(DateUtil.parseDate(transferInfoTO.getPositiveStartDate()));
                regularizationSer.update(regularization);
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
        String sql = "select min(hiredate) as hiredate from  " + getTableName(TransferInfo.class);
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            String startDate = String.valueOf(objects.get(0));
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

    @Override
    public List<String> findDepartment() throws SerException {
        List<TransferInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (TransferInfo model : list) {
            String department = model.getDepartment();
            if (StringUtils.isNotBlank(model.getDepartment())) {
                set.add(department);
            }
        }
        return new ArrayList<>(set);
    }

    public OptionBO figureShowDay(String summDate) throws SerException {
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;
        String text_1 = "转正管理日汇总" + "(" + summDate + ")";
        return totalShowMethod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
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
        String text_1 = "转正管理周汇总" + "(" + startDate + "-" + endDate + ")";
        return totalShowMethod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "转正管理月汇总" + "(" + month + "月)";
        return totalShowMethod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        checkPermission();
        String startDate = " ";
        String sql = "select min(hiredate) as hiredate from  " + getTableName(TransferInfo.class);
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "转正管理累计汇总" + "(累计)";
        return totalShowMethod(startDate, endDate, text_1);
    }

    public OptionBO totalShowMethod(String startDate, String endDate, String text_1) throws SerException {
        List<FigureShowBO> figureShowBOS = new ArrayList<>();
        List<String> deps = findDepartment();
        if (deps != null && deps.size() > 0) {
            for (String dep : deps) {
                StringBuilder sb = new StringBuilder("select * from( ");
                sb.append("(select count(*)as entryNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + startDate + "' AND '" + endDate + "' AND department = '" + dep + "')a,");
                sb.append("(select count(*)as threeFollowNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + threeFront(startDate) + "' AND '" + threeFront(endDate) + "' AND department = '" + dep + "')b,");
                sb.append("(select count(*)as weekFollowNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + weekFront(startDate) + "' AND '" + weekFront(endDate) + "' AND department = '" + dep + "')c,");
                sb.append("(select count(*)as monthFollowNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + monthFront(startDate) + "' AND '" + monthFront(endDate) + "' AND department = '" + dep + "')d,");
                sb.append("(select count(*)as stayPositiveNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + startDate + "' AND '" + endDate + "' AND department = '" + dep + "' AND staffStatus = " + StaffStatus.STAYPOSITIVE.getCode() + ")e,");
                sb.append("(select count(*)as applyPositiveNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + startDate + "' AND '" + endDate + "' AND department = '" + dep + "' AND staffStatus = " + StaffStatus.POSITIVE.getCode() + ")f,");
                sb.append("(select count(*)as hasPositiveNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + startDate + "' AND '" + endDate + "' AND department = '" + dep + "' AND staffStatus = " + StaffStatus.BECOMEMEM.getCode() + ")i,");
                sb.append("(select count(*)as noInterviewNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + startDate + "' AND '" + endDate + "' AND department = '" + dep + "' AND interviewPeper is not NULL)j,");
                sb.append("(select count(*)as noPositiveNum FROM regularization_transferinfo WHERE hiredate BETWEEN '" + startDate + "' AND '" + endDate + "' AND department = '" + dep + "' AND staffStatus != " + StaffStatus.BECOMEMEM.getCode() + ")k");
                sb.append(" ) ");
                List<Object> objects = super.findBySql(sb.toString());
                Object[] obj = (Object[]) objects.get(0);
                Integer entryNum = Integer.parseInt(String.valueOf(obj[0]));//入职数量
                Integer threeFollowNum = Integer.parseInt(String.valueOf(obj[1]));//三天需跟进收集意见数
                Integer weekFollowNum = Integer.parseInt(String.valueOf(obj[2]));//一周需跟进收集意见数
                Integer monthFollowNum = Integer.parseInt(String.valueOf(obj[3]));//一个月需跟进收集意见数
                Integer stayPositiveNum = Integer.parseInt(String.valueOf(obj[4]));//待转正人数
                Integer applyPositiveNum = Integer.parseInt(String.valueOf(obj[5]));//申请转正人数
                Integer hasPositiveNum = Integer.parseInt(String.valueOf(obj[6]));//已转正人数
                Integer noInterviewNum = Integer.parseInt(String.valueOf(obj[7]));//已转正面谈人数
                Integer noPositiveNum = Integer.parseInt(String.valueOf(obj[8]));//未转正人数

                FigureShowBO figureShowBO = new FigureShowBO();
                figureShowBO.setDepartment(dep);//项目组
                figureShowBO.setEntryNum(entryNum);//入职数量
                figureShowBO.setThreeFollowNum(threeFollowNum);//三天需跟进收集意见数
                figureShowBO.setWeekFollowNum(weekFollowNum);//一周需跟进收集意见数
                figureShowBO.setMonthFollowNum(monthFollowNum);//一个月需跟进收集意见数
                figureShowBO.setStayPositiveNum(stayPositiveNum);//待转正人数
                figureShowBO.setApplyPositiveNum(applyPositiveNum);//申请转正人数
                figureShowBO.setHasPositiveNum(hasPositiveNum);//已转正人数
                figureShowBO.setNoInterviewNum(noInterviewNum);//已转正面谈人数
                figureShowBO.setNoPositiveNum(noPositiveNum);//未转正人数
                figureShowBOS.add(figureShowBO);
            }
        }
//        String option = option_1 + "%s" + option_2 + "%s" + option_3 + "%s" + option_4 + "%s" + option_5;
//
//        String text_3 = "'入职人数','三天需跟进收集意见数',' 一周需跟进收集意见数'," +
//                "' 一个月需跟进收集意见数',' 待转正人数',' 申请转正人数'," +
//                "' 已转正人数',' 已转正面谈人数',' 未转正人数'";
//        StringBuilder text_2 = new StringBuilder();
//        StringBuilder text_4 = new StringBuilder();
//
//        for (FigureShowDateBO figureShowBO : figureShowBOS) {
//
//            text_2.append("'").append(figureShowBO.getDepartment()).append("',");
//
//            StringBuilder number = new StringBuilder();
//            number.append(figureShowBO.getEntryNum()).append(",")
//                    .append(figureShowBO.getThreeFollowNum()).append(",")
//                    .append(figureShowBO.getWeekFollowNum()).append(",")
//                    .append(figureShowBO.getMonthFollowNum()).append(",")
//                    .append(figureShowBO.getStayPositiveNum()).append(",")
//                    .append(figureShowBO.getApplyPositiveNum()).append(",")
//                    .append(figureShowBO.getHasPositiveNum()).append(",")
//                    .append(figureShowBO.getNoInterviewNum()).append(",")
//                    .append(figureShowBO.getNoPositiveNum());
//
//            text_4.append("{name: '").append(figureShowBO.getDepartment()).append("',type: 'bar',data: [").append(number).append("]},");
//        }
//        text_4.setLength(text_4.length() - 1);
//        text_2.setLength(text_2.length() - 1);
//        return String.format(option, text_1, text_2, text_3, text_4);


        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"入职人数", "三天需跟进收集意见数",
                "一周需跟进收集意见数", "一个月需跟进收集意见数", "待转正人数",
                "申请转正人数", "已转正人数", "已转正面谈人数", "未转正人数"};
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (figureShowBOS != null && figureShowBOS.size() > 0) {
            for (FigureShowBO figureShowBO : figureShowBOS) {
                text_list2.add(figureShowBO.getDepartment());

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(figureShowBO.getDepartment());
                seriesBO.setType("bar");

                Integer[] number = new Integer[]{figureShowBO.getEntryNum(), figureShowBO.getThreeFollowNum(),
                        figureShowBO.getWeekFollowNum(), figureShowBO.getMonthFollowNum(), figureShowBO.getStayPositiveNum(),
                        figureShowBO.getApplyPositiveNum(), figureShowBO.getHasPositiveNum(), figureShowBO.getNoInterviewNum(),
                        figureShowBO.getNoPositiveNum()};
                seriesBO.setData(number);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);
        optionBO.setTooltip(tooltipBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<TransferInfoTemple> transferInfoTemples = new ArrayList<>();
        TransferInfoTemple transferInfoTemple = new TransferInfoTemple();
        transferInfoTemple.setArea("test");
        transferInfoTemple.setDepartment("test");
        transferInfoTemple.setJobs("test");
        transferInfoTemple.setName("test");
        transferInfoTemple.setEmpNo("test");
        transferInfoTemple.setGender("男/女");
        transferInfoTemple.setEducation("test");
        transferInfoTemple.setProfession("test");
        transferInfoTemple.setWorkingYear(1.5);
        transferInfoTemple.setHiredate("test");
        transferInfoTemple.setProbationaryPer("1个月/1-3个月/3个月");
        transferInfoTemple.setThreeFollow("待跟进/已跟进/未跟进");
        transferInfoTemple.setThreeFollowOpinion("test");
        transferInfoTemple.setWeekFollow("待跟进/已跟进/未跟进");
        transferInfoTemple.setWeekFollowOpinion("test");
        transferInfoTemple.setMonthFollow("待跟进/已跟进/未跟进");
        transferInfoTemple.setMonthFollowOpinion("test");
        transferInfoTemple.setStaffStatus("试用期/转正中/转正不通过/已转正/待转正");
        transferInfoTemple.setApplyDate("test");
        transferInfoTemple.setAsProbationLength(3);
        transferInfoTemple.setConfirmEvent("是/否");
        transferInfoTemple.setConfirmPeople("test");
        transferInfoTemple.setApplyDateAtten("test");
        transferInfoTemple.setRewardPunOpinion("test");
        transferInfoTemple.setAdditionalSkill("test");
        transferInfoTemple.setAdditionalSkillGrade("test");
        transferInfoTemple.setEventsSkill("test");
        transferInfoTemple.setEventsSkillGrade("test");
        transferInfoTemple.setConformStaffing("test");
        transferInfoTemple.setIncomeCostOpinion("test");
        transferInfoTemple.setModuleLeader("test");
        transferInfoTemple.setModuleLeaderOpinion("test");
        transferInfoTemple.setProManage("test");
        transferInfoTemple.setProManageOpinion("test");
        transferInfoTemple.setGenerManage("test");
        transferInfoTemple.setGenerManageOpinion("test");
        transferInfoTemple.setConsentPositive("是/否");
        transferInfoTemple.setPositiveStartDate("test");
        transferInfoTemple.setPositiveThrough("是/否");
        transferInfoTemple.setPraProbationaryPer(3);
        transferInfoTemple.setInterviewPeper("test");
        transferInfoTemple.setInterviewContent("test");
        transferInfoTemples.add(transferInfoTemple);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(transferInfoTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<TransferInfoExcelTO> transferInfoExcelTOS) throws SerException {
        List<TransferInfo> transferInfoList = BeanTransform.copyProperties(transferInfoExcelTOS, TransferInfo.class, true);
        for (TransferInfo transferInfo : transferInfoList){
            transferInfo.setCreateTime(LocalDateTime.now());
            transferInfo.setModifyTime(LocalDateTime.now());
            LocalDate probationDue = null;
            switch (transferInfo.getProbationaryPer()) {
                case "1个月":
                    probationDue = transferInfo.getHiredate().plusMonths(1);
                    break;
                case "1-3个月":
                    probationDue = transferInfo.getHiredate().plusMonths(3);
                    break;
                case "3个月":
                    probationDue = transferInfo.getHiredate().plusMonths(3);
                    break;
                default:
                    throw new SerException("请输入正确的格式,正确格式为(1个月/1-3个月/3个月)");
            }
            transferInfo.setProbationDue(probationDue);
        }
        super.save(transferInfoList);
    }
}