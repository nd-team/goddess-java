package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.entity.Account;
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.ApplyLendBO;
import com.bjike.goddess.lendreimbursement.bo.CollectDataBO;
import com.bjike.goddess.lendreimbursement.bo.LendAuditDetailBO;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendCopyDTO;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.LendAuditDetailDTO;
import com.bjike.goddess.lendreimbursement.entity.ApplyLend;
import com.bjike.goddess.lendreimbursement.entity.ApplyLendCopy;
import com.bjike.goddess.lendreimbursement.entity.LendAuditDetail;
import com.bjike.goddess.lendreimbursement.enums.GuideAddrStatus;
import com.bjike.goddess.lendreimbursement.enums.LendStatus;
import com.bjike.goddess.lendreimbursement.enums.Words;
import com.bjike.goddess.lendreimbursement.excel.ApplyLendExcel;
import com.bjike.goddess.lendreimbursement.to.ApplyLendTO;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcelTO;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 申请借款业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class ApplyLendSerImpl extends ServiceImpl<ApplyLend, ApplyLendDTO> implements ApplyLendSer {


    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private PositionAPI positionAPI;
    @Autowired
    private LendAuditDetailSer lendAuditDetailSer;
    @Autowired
    private ApplyLendCopySer applyLendCopySer;
    @Autowired
    private LendPermissionSer cusPermissionSer;
    @Autowired
    private AccountAPI accountAPI;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * 检查权限(岗位)
     *
     * @throws SerException
     */
    private Boolean checkPermission(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(idFlag);
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(idFlag);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = true;
                break;
            case ADD:
                flag = true;
                break;
            case EDIT:
                flag = true;
                break;
            case DELETE:
                flag = true;
                break;
            case CONGEL:
                flag = true;
                break;
            case THAW:
                flag = true;
                break;
            case COLLECT:
                flag = true;
                break;
            case UPLOAD:
                flag = true;
                break;
            case DOWNLOAD:
                flag = true;
                break;
            case IMPORT:
                flag = true;
                break;
            case EXPORT:
                flag = true;
                break;
            case SEE:
                flag = true;
                break;
            case SEEFILE:
                flag = true;
                break;
            case MANAGEAUDIT:
                flag = guideIdentity("applyWaitAudit-ManageAudit");
                break;
            case FINACEAUDIT:
                flag = guideIdentity("applyWaitAudit-FinaceAudit");
                break;
            case FINACECONGEL:
                flag = guideIdentity("applyWaitAudit-FinaceCongel");
                break;
            case PAY:
                flag = checkPermission("applyWaitPay-Pay");
                break;
            case RECIVEMONEYSURE:
                flag = guideIdentity("applySurePay-Recieve");
            case RETURN:
                flag = guideIdentity("applyRecord-ReturnAndsendTicket");
                break;
            case VOUCHER:
                flag = checkPermission("applyRecord-Voucher");
                break;
            case RETURNCHECK:
                flag = checkPermission("applyReturn-ReturnCheck");
                break;
            case RECIVETICKET:
                flag = guideIdentity("applyAccountCheck-ReciveTicket");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 判断是否可以查看列表的所有数据
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    private ApplyLendDTO addCondition(ApplyLendDTO applyLendDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean listpermission = cusPermissionSer.getCusPermission("apply-ListAll");
        RpcTransmit.transmitUserToken(userToken);
        String userName = userAPI.currentUser().getUsername();
        if (!listpermission && !"admin".equals( userName.toLowerCase())) {
            //没有查看所有数据的权限，则只能查看自己的数据
            if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
                applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
            } else {
                applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
            }
            if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
                applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
            } else {
                applyLendDTO.getConditions().add(Restrict.or("charger", userName));
            }
            applyLendDTO.getConditions().add(Restrict.or("fillSingler", userName));
            RpcTransmit.transmitUserToken(userToken);
        } else {
            if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
                applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
            }
            if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
                applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
            }
        }
        return applyLendDTO;
    }

    /**
     * 特殊岗位权限的添加编辑删除权限
     *
     * @param userToken
     * @param userBO
     * @param lend
     * @throws SerException
     */
    private void checkAddAndEditPermission(String userToken, UserBO userBO, ApplyLend lend) throws SerException {
        Boolean listpermission = cusPermissionSer.getCusPermission("applyLend-EditAndDelete");
        if (!listpermission && !"admin".equals( userBO.getUsername().toLowerCase()) ) {
            //没有特殊权限的话,就只能自己或填单人修改自己的
            if (!userBO.getUsername().equals(lend.getLender()) && !userBO.getUsername().equals(lend.getFillSingler())) {
                throw new SerException("您没有权限");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    @Override
    public Long countApplyLend(ApplyLendDTO applyLendDTO) throws SerException {
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 6, 8, 9}));

        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEstimateLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

        applyLendDTO = addCondition(applyLendDTO);
        Long count = super.count(applyLendDTO);
        return count;
    }

    @Override
    public ApplyLendBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        ApplyLend applyLend = super.findById(id);
        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }

    @Override
    public List<ApplyLendBO> listApplyLend(ApplyLendDTO applyLendDTO) throws SerException {
        applyLendDTO.getSorts().add("createTime=desc");
        //未收款且不在有误单里面
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 6, 8, 9}));

        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEstimateLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

        applyLendDTO = addCondition(applyLendDTO);

        List<ApplyLend> list = super.findByCis(applyLendDTO, true);

        return BeanTransform.copyProperties(list, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO addApplyLend(ApplyLendTO applyLendTO) throws SerException {

        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
            throw new SerException("添加失败，未填无发票备注");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        //填单人
        applyLend.setFillSingler(userAPI.currentUser().getUsername());
        applyLend.setLendDate(LocalDate.now());
        applyLend.setCreateTime(LocalDateTime.now());
        //未付款
        applyLend.setPayCondition("否");
        applyLend.setLendStatus(LendStatus.NONE);
        applyLend.setChargerPass("未处理");
        applyLend.setManagerPass("未处理");
        applyLend.setFincerPass("未处理");
        applyLend.setLendDate(LocalDate.now());
        super.save(applyLend);
        ApplyLendBO bo = BeanTransform.copyProperties(applyLend, ApplyLendBO.class, "lendStatus");
        bo.setLendStatus(applyLend.getLendStatus());
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editApplyLend(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        checkAddAndEditPermission(userToken, userBO, lend);

        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
            throw new SerException("编辑失败，未填无发票备注");
        }

        if (LendStatus.CHARGEPASS.equals(lend.getLendStatus()) || LendStatus.LISTERROR.equals(lend.getLendStatus())) {
            throw new SerException("负责人已审核过，不可再编辑");
        }
//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setEstimateLendDate(applyLend.getEstimateLendDate());
        lend.setLender(applyLend.getLender());
        lend.setCharger(applyLend.getCharger());
        lend.setArea(applyLend.getArea());
        lend.setProjectGroup(applyLend.getProjectGroup());
        lend.setProjectName(applyLend.getProjectName());
        lend.setLendWay(applyLend.getLendWay());
        lend.setFirstSubject(applyLend.getFirstSubject());
        lend.setSecondSubject(applyLend.getSecondSubject());
        lend.setThirdSubject(applyLend.getThirdSubject());
        lend.setExplains(applyLend.getExplains());
        lend.setWriteUp(applyLend.getWriteUp());
        lend.setLendReson(applyLend.getLendReson());
        lend.setMoney(applyLend.getMoney());
        lend.setInvoice(applyLend.getInvoice());
        lend.setRemark(applyLend.getRemark());
        //填单人
        lend.setFillSingler(userBO.getUsername());
        lend.setLendDate(LocalDate.now());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteApplyLend(String id) throws SerException {
        if (StringUtils.isBlank(id)) {

            throw new SerException("id不能为空哦");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken( userToken );
        ApplyLend lend = super.findById(id);

        if (lend == null) {
            throw new SerException("该条数据已不存在，请刷新数据");
        }
        checkAddAndEditPermission(userToken, userBO, lend);
        super.remove(id);

        //删除副本
        ApplyLendCopyDTO acdto = new ApplyLendCopyDTO();
        acdto.getConditions().add(Restrict.eq("applyLendId", id));
        List<ApplyLendCopy> ac = applyLendCopySer.findByCis(acdto);
        if (ac != null && ac.size() > 0) {
            applyLendCopySer.remove(ac);
        }

        //删除审核详情记录
        LendAuditDetailDTO ladDTO = new LendAuditDetailDTO();
        ladDTO.getConditions().add(Restrict.eq("applyLendId", id));
        List<LendAuditDetail> details = lendAuditDetailSer.findByCis(ladDTO);
        if (details != null && details.size() > 0) {
            lendAuditDetailSer.remove(details);
        }

    }

    @Override
    public ApplyLendBO getApplyLendDetail(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        ApplyLend applyLend = super.findById(id);
        ApplyLendBO applyLendBO = BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
        return applyLendBO;
    }

    @Override
    public List<LendAuditDetailBO> getLendAuditDetailByApplyLendId(String applyLendId) throws SerException {
        if (StringUtils.isBlank(applyLendId)) {
            throw new SerException("申请借款id不能为空哦");
        }
        LendAuditDetailDTO dto = new LendAuditDetailDTO();
        dto.getConditions().add(Restrict.eq("applyLendId", applyLendId));
        List<LendAuditDetail> list = lendAuditDetailSer.findByCis(dto);
        return BeanTransform.copyProperties(list, LendAuditDetailBO.class);
    }

    @Override
    public Long countWaitAudit(ApplyLendDTO applyLendDTO) throws SerException {
//        applyLendDTO.getConditions().add(Restrict.eq("lendStatus", 0));
////        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 1));
////        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 2));
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 4));
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 5));
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 9));//3,6,7,8
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 3, 6, 7, 8}));

        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }
        applyLendDTO = addCondition(applyLendDTO);

        Long count = super.count(applyLendDTO);
        return count;
    }

    @Override
    public List<ApplyLendBO> listWaitAudit(ApplyLendDTO applyLendDTO) throws SerException {
//        applyLendDTO.getConditions().add(Restrict.eq("lendStatus", 0));
////        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 1));
////        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 2));
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 4));
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 5));
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 9));
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 3, 6, 7, 8}));

        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEstimateLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }
        applyLendDTO = addCondition(applyLendDTO);

        List<ApplyLend> list = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> listBO = new ArrayList<>();
        list.stream().forEach(str -> {
            ApplyLendBO bo = BeanTransform.copyProperties(str, ApplyLendBO.class, "lendStatus");
            bo.setLendStatus(str.getLendStatus());
            listBO.add(bo);
        });


        return listBO;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();

        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
            throw new SerException("编辑失败，未填无发票备注");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        checkAddAndEditPermission(userToken, userBO, lend);

        if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("该条数据已经申请过冻结，不能再操作");
        }

//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime", "lendStatus");
        lend.setEstimateLendDate(applyLend.getEstimateLendDate());
        lend.setLender(applyLend.getLender());
        lend.setCharger(applyLend.getCharger());
        lend.setArea(applyLend.getArea());
        lend.setProjectGroup(applyLend.getProjectGroup());
        lend.setProjectName(applyLend.getProjectName());
        lend.setLendWay(applyLend.getLendWay());
        lend.setFirstSubject(applyLend.getFirstSubject());
        lend.setSecondSubject(applyLend.getSecondSubject());
        lend.setThirdSubject(applyLend.getThirdSubject());
        lend.setExplains(applyLend.getExplains());
        lend.setWriteUp(applyLend.getWriteUp());
        lend.setLendReson(applyLend.getLendReson());
        lend.setMoney(applyLend.getMoney());
        lend.setInvoice(applyLend.getInvoice());
        lend.setRemark(applyLend.getRemark());
        //填单人
        lend.setLendDate(applyLend.getLendDate());
        lend.setModifyTime(LocalDateTime.now());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //添加副本
        ApplyLendCopyDTO acdto = new ApplyLendCopyDTO();
        acdto.getConditions().add(Restrict.eq("applyLendId", lend.getId()));
        ApplyLendCopy ac = applyLendCopySer.findOne(acdto);
        if (ac != null && StringUtils.isNotBlank(ac.getId())) {
            BeanUtils.copyProperties(lend, ac, "id", "createTime", "applyLendId");
            ac.setApplyLendId(lend.getId());
            ac.setModifyTime(LocalDateTime.now());
            applyLendCopySer.update(ac);
        } else {
            ac = new ApplyLendCopy();
            BeanUtils.copyProperties(lend, ac, "id", "createTime", "applyLendId");
            ac.setApplyLendId(lend.getId());
            ac.setModifyTime(LocalDateTime.now());
            applyLendCopySer.save(ac);
        }

        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editChargerWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();

        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("负责人审核失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        lend.setCharger(userBO.getUsername());
        lend.setChargerOpinion(applyLendTO.getChargerOpinion());
        lend.setChargerPass(applyLendTO.getChargerPass());
        if ("是".equals(applyLendTO.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGEPASS);
        } else if ("否".equals(applyLendTO.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGENOTPASS);
            lend.setLendError(9);
        }
        if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("该条数据已经申请过冻结，不能再操作");
        }
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表
//        UserBO userBO = userAPI.currentUser(token);
        UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = new PositionBO();
        if (userDetailBO != null) {
            positionBO = positionAPI.findById(userDetailBO.getPositionId());
        }
        //职位名
        LendAuditDetail lendAuditDetail = new LendAuditDetail();
        lendAuditDetail.setPosition(positionBO.getName());
        lendAuditDetail.setAuditor(userBO.getUsername());
        lendAuditDetail.setAuditDate(LocalDate.now());
        lendAuditDetail.setAuditSuggest(applyLendTO.getChargerOpinion());
        lendAuditDetail.setPassOr(applyLendTO.getChargerPass());
        lendAuditDetail.setApplyLendId(lend.getId());
        lendAuditDetail.setCreateTime(LocalDateTime.now());
        lendAuditDetailSer.save(lendAuditDetail);

        ApplyLendBO bo = BeanTransform.copyProperties(lend, ApplyLendBO.class, "lendStatus");
        bo.setLendStatus(lend.getLendStatus());
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editOperateWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("财务运营部审核失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (lend.getLendStatus().getCode() == 0) {
            throw new SerException("财务运营部审核失败，负责人还未审核");
        } else if (lend.getLendStatus().getCode() == 9) {
            throw new SerException("财务运营部审核失败，此单为申请单有误编辑过来的，先得要负责人先审");
        }
        if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("该条数据已经申请过冻结，不能再操作");
        }
        UserBO userBO = userAPI.currentUser();
        lend.setFinacer(userBO.getUsername());
        lend.setFincerOpinion(applyLendTO.getFincerOpinion());
        lend.setFincerPass(applyLendTO.getFincerPass());
        if ("是".equals(applyLendTO.getFincerPass())) {
            lend.setLendStatus(LendStatus.FINACEPASS);
        } else if ("否".equals(applyLendTO.getFincerPass())) {
            lend.setLendStatus(LendStatus.FINACENOTPASS);
        }
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表
//        UserBO userBO = userAPI.currentUser();
        UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = new PositionBO();
        if (userDetailBO != null) {
            positionBO = positionAPI.findById(userDetailBO.getPositionId());
        }
        //职位名
        LendAuditDetail lendAuditDetail = new LendAuditDetail();
        lendAuditDetail.setPosition(positionBO.getName());
        lendAuditDetail.setAuditor(userBO.getUsername());
        lendAuditDetail.setAuditDate(LocalDate.now());
        lendAuditDetail.setAuditSuggest(applyLendTO.getFincerOpinion());
        lendAuditDetail.setPassOr(applyLendTO.getFincerPass());
        lendAuditDetail.setApplyLendId(lend.getId());
        lendAuditDetail.setCreateTime(LocalDateTime.now());
        lendAuditDetailSer.save(lendAuditDetail);

        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editManageWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("总经办审核失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());
        UserBO userBO = userAPI.currentUser();
        //说明财务还没审核
        if (lend.getLendStatus().getCode() == 3 || lend.getLendStatus().getCode() == 4) {
            lend.setManager(userBO.getUsername());
            lend.setManagerOpinion(applyLendTO.getManagerOpinion());
            lend.setManagerPass(applyLendTO.getManagerPass());
            lend.setModifyTime(LocalDateTime.now());
            super.update(lend);
        } else if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("总经办审核失败，此条数据已被冻结");
        } else {
            throw new SerException("总经办审核失败，财务运营部还未审核");
        }
        if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("该条数据已经申请过冻结，不能再操作");
        }

        lend.setManager(userBO.getUsername());
        lend.setManagerOpinion(applyLendTO.getManagerOpinion());
        lend.setManagerPass(applyLendTO.getManagerPass());
        if ("是".equals(applyLendTO.getManagerPass())) {
            lend.setLendStatus(LendStatus.MANAGEPASS);
        } else if ("否".equals(applyLendTO.getManagerPass())) {
            lend.setLendStatus(LendStatus.MANAGENOTPASS);
        }
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表
//        UserBO userBO = userAPI.currentUser();
        UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = new PositionBO();
        if (userDetailBO != null) {
            positionBO = positionAPI.findById(userDetailBO.getPositionId());
        }
        //职位名
        LendAuditDetail lendAuditDetail = new LendAuditDetail();
        lendAuditDetail.setPosition(positionBO.getName());
        lendAuditDetail.setAuditor(userBO.getUsername());
        lendAuditDetail.setAuditDate(LocalDate.now());
        lendAuditDetail.setAuditSuggest(applyLendTO.getManagerOpinion());
        lendAuditDetail.setPassOr(applyLendTO.getManagerPass());
        lendAuditDetail.setApplyLendId(lend.getId());
        lendAuditDetail.setCreateTime(LocalDateTime.now());
        lendAuditDetailSer.save(lendAuditDetail);

        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editOperateCongel(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("财务运营部冻结失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (lend.getLendStatus().getCode() == 0 || lend.getLendStatus().getCode() == 9) {
            throw new SerException("财务运营部冻结失败，负责人还未审核");
        }
        if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("该条数据已经申请过冻结，不能再操作");
        }
        UserBO userBO = userAPI.currentUser();
        lend.setFinacer(userBO.getUsername());
        lend.setFincerOpinion(applyLendTO.getFincerOpinion());
        lend.setFincerPass("未处理");

        lend.setLendStatus(LendStatus.FINACECONGEL);

        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editChargeSureCongel(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("负责人冻结失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (lend.getLendStatus().getCode() != 5) {
            throw new SerException("负责人确认冻结失败，此条数据财务部还未冻结");
        }
        lend.setCharger(userAPI.currentUser().getUsername());

        lend.setLendStatus(LendStatus.CHARGESURECONGEL);
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editChargeConcelCongel(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("负责人取消冻结失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (lend.getLendStatus().getCode() != 5) {
            throw new SerException("负责人确认取消冻结失败，此条数据财务部还未冻结");
        }
        lend.setCharger(userAPI.currentUser().getUsername());

        if ("是".equals(lend.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGEPASS);
        } else {
            lend.setLendStatus(LendStatus.CHARGENOTPASS);
        }
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Override
    public Long countApplyError(ApplyLendDTO applyLendDTO) throws SerException {
        /*applyLendDTO.getConditions().add(Restrict.or("managerPass","否"));
        //负责人确认冻结
        applyLendDTO.getConditions().add(Restrict.or("lendStatus",LendStatus.CHARGECONGEL));*/

        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEstimateLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

//        applyLendDTO.getConditions().add(Restrict.eq("lendError", 9));
        //LendStatus.CHARGESURECONGEL
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 6));
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{0, 1, 2, 3, 4, 5, 7, 8}));
        applyLendDTO = addCondition(applyLendDTO);

        Long count = super.count(applyLendDTO);
        return count;
    }

    @Override
    public List<ApplyLendBO> listApplyError(ApplyLendDTO applyLendDTO) throws SerException {

        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEstimateLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

//        applyLendDTO.getConditions().add(Restrict.eq("lendError", 9));
        //LendStatus.CHARGESURECONGEL
//        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 6));
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{0, 1, 2, 3, 4, 5, 7, 8}));

        applyLendDTO = addCondition(applyLendDTO);

        List<ApplyLend> applyLendList = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> applyLendBOS = BeanTransform.copyProperties(applyLendList, ApplyLendBO.class);
        return applyLendBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editApplyError(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();

        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
            throw new SerException("编辑失败，未填无发票备注");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        checkAddAndEditPermission(userToken, userBO, lend);

        lend.setLendError(0);

//        if (!LendStatus.CHARGESURECONGEL.equals(lend.getLendStatus())) {
//            throw new SerException("编辑失败，此条数据负责人还未确认冻结状态");
//        }

        //添加副本
        ApplyLendCopyDTO acdto = new ApplyLendCopyDTO();
        acdto.getConditions().add(Restrict.eq("applyLendId", lend.getId()));
        ApplyLendCopy ac = applyLendCopySer.findOne(acdto);
        if (ac != null && StringUtils.isNotBlank(ac.getId())) {
            BeanUtils.copyProperties(lend, ac, "id", "createTime", "applyLendId");
            ac.setApplyLendId(lend.getId());
            ac.setModifyTime(LocalDateTime.now());
            applyLendCopySer.update(ac);
        } else {
            ac = new ApplyLendCopy();
            BeanUtils.copyProperties(lend, ac);
            ac.setApplyLendId(lend.getId());
            ac.setModifyTime(LocalDateTime.now());
            applyLendCopySer.save(ac);
        }

        //申请单有误编辑
        BeanUtils.copyProperties(applyLend, lend, "id", "createTime"
                , "attender", "writeUpCondition", "noInvoiceRemark", "goodsLink", "fillSingler", "lendDate", "chargerOpinion", "chargerPass", "finacer", "fincerOpinion"
                , "fincerPass", "manager", "managerOpinion", "managerPass", "proxyAuditRemark", "payCondition", "payer"
                , "payDate", "payOrigin", "documentQuantity", "documentCondition", "reimMoney", "lendMoney", "returnMoney", "returnDate", "returnWays", "returnAccount"
                , "sender", "sendDate", "sendCondition", "receiveAddr", "ticketer", "ticketDate", "ticketCondition", "receiveTicket"
                , "checker", "checkDate", "checkcontent", "lendStatus", "receivePay", "lendError");
        lend.setEstimateLendDate(applyLend.getEstimateLendDate());
        lend.setLender(applyLend.getLender());
        lend.setCharger(applyLend.getCharger());
        lend.setArea(applyLend.getArea());
        lend.setProjectGroup(applyLend.getProjectGroup());
        lend.setProjectName(applyLend.getProjectName());
        lend.setLendWay(applyLend.getLendWay());
        lend.setFirstSubject(applyLend.getFirstSubject());
        lend.setSecondSubject(applyLend.getSecondSubject());
        lend.setThirdSubject(applyLend.getThirdSubject());
        lend.setExplains(applyLend.getExplains());
        lend.setWriteUp(applyLend.getWriteUp());
        lend.setLendReson(applyLend.getLendReson());
        lend.setMoney(applyLend.getMoney());
        lend.setInvoice(applyLend.getInvoice());
        lend.setRemark(applyLend.getRemark());
        //填单人
        lend.setFillSingler(userAPI.currentUser().getUsername());
        lend.setLendDate(applyLend.getLendDate());
        lend.setModifyTime(LocalDateTime.now());
        //未付款
        applyLend.setPayCondition("否");
        applyLend.setChargerPass("未处理");
        applyLend.setManagerPass("未处理");
        applyLend.setFincerPass("未处理");
        lend.setLendStatus(LendStatus.NONE);
        super.update(lend);


        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteApplyError(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("删除失败，id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        ApplyLend lend = super.findById( id );
        if( lend == null ){
            throw new SerException("该条数据已经不存在，请刷新数据");
        }
        checkAddAndEditPermission(userToken, userBO, lend);

        super.remove(id);

        //删除副本
        ApplyLendCopyDTO acdto = new ApplyLendCopyDTO();
        acdto.getConditions().add(Restrict.eq("applyLendId", id));
        List<ApplyLendCopy> ac = applyLendCopySer.findByCis(acdto);
        if (ac != null && ac.size() > 0) {
            applyLendCopySer.remove(ac);
        }

        //删除审核详情记录
        LendAuditDetailDTO ladDTO = new LendAuditDetailDTO();
        ladDTO.getConditions().add(Restrict.eq("applyLendId", id));
        List<LendAuditDetail> details = lendAuditDetailSer.findByCis(ladDTO);
        if (details != null && details.size() > 0) {
            lendAuditDetailSer.remove(details);
        }

    }

    @Override
    public ApplyLendBO getApplyApplyError(String id) throws SerException {
        ApplyLend applyLend = super.findById(id);
        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }

    @Override
    public ApplyLendBO getApplyApplyErrorCopy(String id) throws SerException {
        ApplyLendCopyDTO dto = new ApplyLendCopyDTO();
        dto.getConditions().add(Restrict.eq("applyLendId", id));
        ApplyLendCopy applyLendCopy = applyLendCopySer.findOne(dto);
        ApplyLendBO applyLendBO = BeanTransform.copyProperties(applyLendCopy, ApplyLendBO.class);
        return applyLendBO;
    }

    @Override
    public Long countHasAudit(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        //LendStatus.CHARGESURECONGEL
        dto.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{6, 5, 9}));
        //LendStatus.FINACECONGEL
//        dto.getConditions().add(Restrict.ne("lendStatus", 5));
        //LendStatus.LISTERROR
//        dto.getConditions().add(Restrict.ne("lendStatus", 9));
        dto.getConditions().add(Restrict.eq("managerPass", "是"));
        dto.getConditions().add(Restrict.or("fincerPass", "是"));
//        dto.getConditions().add(Restrict.or("chargerPass", "是"));

        dto = addCondition(dto);

        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listHasAudit(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        //LendStatus.CHARGESURECONGEL
        dto.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{6, 5, 9}));
        //LendStatus.FINACECONGEL
//        dto.getConditions().add(Restrict.ne("lendStatus", 5));
        //LendStatus.LISTERROR
//        dto.getConditions().add(Restrict.ne("lendStatus", 9));
        dto.getConditions().add(Restrict.eq("managerPass", "是"));
        dto.getConditions().add(Restrict.or("fincerPass", "是"));
//        dto.getConditions().add(Restrict.or("chargerPass", "是"));

        dto = addCondition(dto);

        List<ApplyLend> applyLend = super.findByCis(dto, true);
        List<ApplyLendBO> bolist = new ArrayList<>();
        applyLend.stream().forEach(str -> {
            ApplyLendBO temp = BeanTransform.copyProperties(str, ApplyLendBO.class, "lendStatus");
            temp.setLendStatus(str.getLendStatus());
            bolist.add(temp);
        });
        return bolist;
    }

    @Override
    public Long countWaitPay(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "否"));
        //LendStatus.FINACEPASS
        dto.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
        //LendStatus.MANAGEPASS
//        dto.getConditions().add(Restrict.or("lendStatus", 7));

        dto = addCondition(dto);

        Long counts = super.count(dto);
        return counts;
    }


    @Override
    public List<ApplyLendBO> listWaitPay(ApplyLendDTO applyLendDTO) throws SerException {

        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "否"));
        //LendStatus.FINACEPASS
        dto.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
        //LendStatus.MANAGEPASS
//        dto.getConditions().add(Restrict.or("lendStatus", 7));

        dto = addCondition(dto);

        List<ApplyLend> applyLend = super.findByCis(dto, true);
        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editPayMoney(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getPayOrigin())) {
            throw new SerException("编辑失败，支付来源不能为空");
        }
        if (StringUtils.isBlank(applyLendTO.getPayDate())) {
            throw new SerException("编辑失败，支付日期不能为空");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setPayOrigin(applyLend.getPayOrigin());
        lend.setPayDate(applyLend.getPayDate());
        //支付人
        lend.setPayer(userAPI.currentUser().getUsername());
        lend.setPayCondition("是");
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Override
    public Long countSureRecieve(ApplyLendDTO applyLendDTO) throws SerException {
        String userName = userAPI.currentUser().getUsername();
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "是"));
        dto.getConditions().add(Restrict.eq("fillSingler", userName));
        dto.getConditions().add(Restrict.or("lender", userName));
        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listSureRecieveMoney(ApplyLendDTO applyLendDTO) throws SerException {

        String userName = userAPI.currentUser().getUsername();
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "是"));
        dto.getConditions().add(Restrict.eq("fillSingler", userName));
        dto.getConditions().add(Restrict.or("lender", userName));
        List<ApplyLend> applyLend = super.findByCis(dto, true);
        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editSureRecieveMoney(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("编辑失败，id不能为空");
        }

        String id = applyLendTO.getId();
        ApplyLend lend = super.findById(id);

        //确认收款
        lend.setReceivePay("是");
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Override
    public Long countBorrowRecord(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("receivePay", "是"));

        dto = addCondition(dto);
        List<ApplyLend> applyLend = super.findByCis(dto, true);
        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listBorrowRecord(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("receivePay", "是"));

        dto = addCondition(dto);

        List<ApplyLend> applyLend = super.findByCis(dto, true);
        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editReturnBorrowRecord(ApplyLendTO applyLendTO) throws SerException {

        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("还款失败，id不能为空");
        }
        if ("转账".equals(applyLendTO.getReturnWays()) && StringUtils.isBlank(applyLendTO.getPayOrigin())) {
            throw new SerException("还款失败，还款账户不能为空");
        }

        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setReimMoney(applyLend.getReimMoney());
        lend.setLendMoney(applyLend.getLendMoney());
        lend.setReturnMoney(applyLend.getReturnMoney());
        lend.setReturnDate(applyLend.getReturnDate());
        lend.setReturnWays(applyLend.getReturnWays());
        lend.setReturnAccount(applyLend.getReturnAccount());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editBorrowRecordSend(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("寄件失败，id不能为空");
        }

        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setSendDate(applyLend.getSendDate());
        lend.setSendCondition(applyLend.getSendCondition());
        lend.setReceiveAddr(applyLend.getReceiveAddr());

        lend.setDocumentQuantity(applyLendTO.getDocumentQuantity());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Override
    public List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("生成记账凭证失败，id不能为空");
        }
        String userName = userAPI.currentUser().getUsername();

        ApplyLend lend = super.findById(id);
        String dates = String.valueOf(lend.getLendDate());
        String stage = lend.getLendDate().getYear() + "年第" + lend.getLendDate().getMonthValue() + "期";
        Double ticketNum = lend.getDocumentQuantity();

        List<AccountVoucherBO> list = new ArrayList<>();
        AccountVoucherBO accountVoucherBO = new AccountVoucherBO();
        accountVoucherBO.setWords(Words.PAY);
        accountVoucherBO.setDates(dates);
        accountVoucherBO.setStage(stage);
        accountVoucherBO.setTicketNum(ticketNum);
        accountVoucherBO.setTicketUser(userName);
        accountVoucherBO.setBorrowResion(lend.getLendReson());
        accountVoucherBO.setSubject("其他应收款-" + lend.getLender());
        accountVoucherBO.setBorrowMoney(lend.getMoney());
        accountVoucherBO.setLoanMoney(0d);
        list.add(accountVoucherBO);

        accountVoucherBO = new AccountVoucherBO();
        accountVoucherBO.setWords(Words.PAY);
        accountVoucherBO.setDates(dates);
        accountVoucherBO.setStage(stage);
        accountVoucherBO.setTicketNum(ticketNum);
        accountVoucherBO.setTicketUser(userName);
        accountVoucherBO.setBorrowResion(lend.getLendReson());
        accountVoucherBO.setSubject(lend.getPayOrigin());
        accountVoucherBO.setLoanMoney(lend.getMoney());
        accountVoucherBO.setBorrowMoney(0d);
        list.add(accountVoucherBO);

        Double borrowMoney = list.stream().mapToDouble(AccountVoucherBO::getBorrowMoney).sum();
        Double loanMoney = list.stream().mapToDouble(AccountVoucherBO::getLoanMoney).sum();
        accountVoucherBO = new AccountVoucherBO();
        accountVoucherBO.setWords(Words.PAY);
        accountVoucherBO.setDates(dates);
        accountVoucherBO.setStage(stage);
        accountVoucherBO.setTicketNum(ticketNum);
        accountVoucherBO.setTicketUser(userName);
        accountVoucherBO.setBorrowResion("合计");
        accountVoucherBO.setBorrowMoney(borrowMoney);
        accountVoucherBO.setLoanMoney(loanMoney);
        list.add(accountVoucherBO);

        return list;
    }

    @Override
    public Long countReturn(ApplyLendDTO applyLendDTO) throws SerException {
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));

        applyLendDTO = addCondition(applyLendDTO);

        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listReturnMoneyRecord(ApplyLendDTO applyLendDTO) throws SerException {
        //还款记录是报销金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));

        applyLendDTO = addCondition(applyLendDTO);
        List<ApplyLend> list = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> lendBOList = BeanTransform.copyProperties(list, ApplyLendBO.class);
        return lendBOList;
    }

    @Override
    public List<AccountVoucherBO> listAccountVoucherByReturnMoney(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("生成记账凭证失败，id不能为空");
        }
        String userName = userAPI.currentUser().getUsername();

        ApplyLend lend = super.findById(id);
        String dates = String.valueOf(lend.getLendDate());
        String stage = lend.getLendDate().getYear() + "年第" + lend.getLendDate().getMonthValue() + "期";
        Double ticketNum = lend.getDocumentQuantity();

        List<AccountVoucherBO> list = new ArrayList<>();
        AccountVoucherBO accountVoucherBO = new AccountVoucherBO();
        //退回金额大于0
        if (lend.getReturnMoney() > 0) {
            accountVoucherBO.setWords(Words.PAY);
            accountVoucherBO.setDates(dates);
            accountVoucherBO.setStage(stage);
            accountVoucherBO.setTicketNum(ticketNum);
            accountVoucherBO.setTicketUser(userName);
            accountVoucherBO.setBorrowResion(lend.getLendReson());
            accountVoucherBO.setSubject(lend.getFirstSubject() + "-" + lend.getSecondSubject() + "-" + lend.getThirdSubject());
            accountVoucherBO.setBorrowMoney(lend.getReimMoney());
            accountVoucherBO.setLoanMoney(0d);
            list.add(accountVoucherBO);

            accountVoucherBO = new AccountVoucherBO();
            accountVoucherBO.setWords(Words.PAY);
            accountVoucherBO.setDates(dates);
            accountVoucherBO.setStage(stage);
            accountVoucherBO.setTicketNum(ticketNum);
            accountVoucherBO.setTicketUser(userName);
            accountVoucherBO.setBorrowResion(lend.getLendReson());
            accountVoucherBO.setSubject(lend.getReturnAccount());
            accountVoucherBO.setBorrowMoney(lend.getReturnMoney());
            accountVoucherBO.setLoanMoney(0d);
            list.add(accountVoucherBO);


            accountVoucherBO = new AccountVoucherBO();
            accountVoucherBO.setWords(Words.PAY);
            accountVoucherBO.setDates(dates);
            accountVoucherBO.setStage(stage);
            accountVoucherBO.setTicketNum(ticketNum);
            accountVoucherBO.setTicketUser(userName);
            accountVoucherBO.setBorrowResion(lend.getLendReson());
            accountVoucherBO.setSubject("其他应收款-" + lend.getLender());
            accountVoucherBO.setBorrowMoney(0d);
            accountVoucherBO.setLoanMoney(lend.getLendMoney());
            list.add(accountVoucherBO);

            Double borrowMoney = list.stream().mapToDouble(AccountVoucherBO::getBorrowMoney).sum();
            Double loanMoney = list.stream().mapToDouble(AccountVoucherBO::getLoanMoney).sum();
            accountVoucherBO = new AccountVoucherBO();
            accountVoucherBO.setWords(Words.PAY);
            accountVoucherBO.setDates(dates);
            accountVoucherBO.setStage(stage);
            accountVoucherBO.setTicketNum(ticketNum);
            accountVoucherBO.setTicketUser(userName);
            accountVoucherBO.setBorrowResion("合计");
            accountVoucherBO.setBorrowMoney(borrowMoney);
            accountVoucherBO.setLoanMoney(loanMoney);
            list.add(accountVoucherBO);

        } else {
            accountVoucherBO.setWords(Words.PAY);
            accountVoucherBO.setDates(dates);
            accountVoucherBO.setStage(stage);
            accountVoucherBO.setTicketNum(ticketNum);
            accountVoucherBO.setTicketUser(userName);
            accountVoucherBO.setBorrowResion(lend.getLendReson());
            accountVoucherBO.setSubject(lend.getFirstSubject() + "-" + lend.getSecondSubject() + "-" + lend.getThirdSubject());
            accountVoucherBO.setBorrowMoney(lend.getLendMoney());
            accountVoucherBO.setLoanMoney(0d);
            list.add(accountVoucherBO);

            accountVoucherBO = new AccountVoucherBO();
            accountVoucherBO.setWords(Words.PAY);
            accountVoucherBO.setDates(dates);
            accountVoucherBO.setStage(stage);
            accountVoucherBO.setTicketNum(ticketNum);
            accountVoucherBO.setTicketUser(userName);
            accountVoucherBO.setBorrowResion(lend.getLendReson());
            accountVoucherBO.setSubject("其他应收款-" + lend.getLender());
            accountVoucherBO.setLoanMoney(lend.getLendMoney());
            accountVoucherBO.setBorrowMoney(0d);
            list.add(accountVoucherBO);

            Double borrowMoney = list.stream().mapToDouble(AccountVoucherBO::getBorrowMoney).sum();
            Double loanMoney = list.stream().mapToDouble(AccountVoucherBO::getLoanMoney).sum();
            accountVoucherBO = new AccountVoucherBO();
            accountVoucherBO.setWords(Words.PAY);
            accountVoucherBO.setDates(dates);
            accountVoucherBO.setStage(stage);
            accountVoucherBO.setTicketNum(ticketNum);
            accountVoucherBO.setTicketUser(userName);
            accountVoucherBO.setBorrowResion("合计");
            accountVoucherBO.setBorrowMoney(borrowMoney);
            accountVoucherBO.setLoanMoney(loanMoney);
            list.add(accountVoucherBO);
        }


        return list;
    }


    @Override
    public ApplyLendBO checkReturnMoney(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("失败，id不能为空");
        }
        if (StringUtils.isBlank(applyLendTO.getCheckcontent())) {
            throw new SerException("失败，核对内容不能为空");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLendTO, lend, "id", "createTime");

        lend.setChecker(userAPI.currentUser().getUsername());
        lend.setCheckDate(LocalDate.now());
        lend.setCheckcontent(applyLendTO.getCheckcontent());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);

    }

    @Override
    public Long countBusCheck(ApplyLendDTO applyLendDTO) throws SerException {
        //帐务核对是报销金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));

        applyLendDTO = addCondition(applyLendDTO);

        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listBusinessCheck(ApplyLendDTO applyLendDTO) throws SerException {
        //帐务核对是报销金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));

        applyLendDTO = addCondition(applyLendDTO);

        List<ApplyLend> list = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> lendBOList = BeanTransform.copyProperties(list, ApplyLendBO.class);
        return lendBOList;
    }

    @Override
    public ApplyLendBO checkTicket(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("失败，id不能为空");
        }
        if (StringUtils.isBlank(applyLendTO.getTicketCondition())) {
            throw new SerException("失败，收票情况不能为空");
        }
        if (!"否".equals(applyLendTO.getDocumentCondition()) && !"是".equals(applyLendTO.getDocumentCondition())) {
            throw new SerException("失败，是否收到单据填写是或否");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLendTO, lend, "id", "createTime");

        lend.setDocumentCondition(applyLendTO.getDocumentCondition());
        lend.setTicketer(userAPI.currentUser().getUsername());
        lend.setTicketCondition(applyLendTO.getTicketCondition());
        lend.setReceiveTicket(applyLendTO.getReceiveTicket());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);

    }

    @Override
    public Long countRecTicket(ApplyLendDTO applyLendDTO) throws SerException {
        //收到单据 是
        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));

        applyLendDTO = addCondition(applyLendDTO);

        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listRecieveTicketRecord(ApplyLendDTO applyLendDTO) throws SerException {
        //收到单据 是
        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));

        applyLendDTO = addCondition(applyLendDTO);

        List<ApplyLend> list = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> lendBOList = BeanTransform.copyProperties(list, ApplyLendBO.class);
        return lendBOList;
    }


    @Override
    public List<CollectDataBO> collectLender(ApplyLendDTO applyLendDTO) throws SerException {
        //说明选了借款人
        String[] fields = new String[]{"lender", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                "thirdSubject", "payCondition", "money"};

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            sb.append("SELECT  lender,  area, projectGroup,")
                    .append("  projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject, payCondition, money ")
                    .append("   FROM lendreimbursement_applylend where lender = '" + applyLendDTO.getLender().trim() + "' order by payCondition desc ");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else {
            fields = new String[]{"lender", "money"};
            sb = new StringBuffer("");
            sb.append("select lender , sum(money) as money from lendreimbursement_applylend group by lender");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        }
        return collectDataBOList;
    }

    @Override
    public List<CollectDataBO> collectArea(ApplyLendDTO applyLendDTO) throws SerException {
        //说明选了地区
        String[] fields = new String[]{"area", "lender", "projectGroup", "projectName", "firstSubject", "secondSubject",
                "thirdSubject", "payCondition", "money"};

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        if (StringUtils.isNotBlank(applyLendDTO.getArea())) {
            sb.append("SELECT  area,lender, projectGroup,")
                    .append("  projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject, payCondition, money ")
                    .append("   FROM lendreimbursement_applylend where area = '" + applyLendDTO.getArea().trim() + "'");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else {
            fields = new String[]{"area", "money"};
            sb = new StringBuffer("");
            sb.append("select area , sum(money) as money from lendreimbursement_applylend group by area");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        }
        return collectDataBOList;
    }

    @Override
    public List<CollectDataBO> collectProjectGroup(ApplyLendDTO applyLendDTO) throws SerException {
        //说明选了项目组
        String[] fields = new String[]{"projectGroup", "lender", "area", "projectName", "firstSubject", "secondSubject",
                "thirdSubject", "payCondition", "money"};

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        if (StringUtils.isNotBlank(applyLendDTO.getProjectGroup())) {
            sb.append("SELECT  projectGroup,lender, area,")
                    .append("  projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject, payCondition, money ")
                    .append("   FROM lendreimbursement_applylend where projectGroup = '" + applyLendDTO.getProjectGroup().trim() + "'");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else {
            fields = new String[]{"projectGroup", "money"};
            sb = new StringBuffer("");
            sb.append("select projectGroup , sum(money) as money from lendreimbursement_applylend group by projectGroup");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        }
        return collectDataBOList;
    }

    @Override
    public List<CollectDataBO> collectProjectName(ApplyLendDTO applyLendDTO) throws SerException {
        //说明选了项目名称
        String[] fields = new String[]{"projectName", "lender", "area", "projectGroup", "firstSubject", "secondSubject",
                "thirdSubject", "payCondition", "money"};

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        if (StringUtils.isNotBlank(applyLendDTO.getProjectName())) {
            sb.append("SELECT  projectName,lender, area,")
                    .append("  projectGroup,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject, payCondition, money ")
                    .append("   FROM lendreimbursement_applylend where projectName = '" + applyLendDTO.getProjectName().trim() + "'");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else {
            fields = new String[]{"projectName", "money"};
            sb = new StringBuffer("");
            sb.append("select projectName , sum(money) as money from lendreimbursement_applylend group by projectName");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        }
        return collectDataBOList;
    }

    @Override
    public List<String> listLender() throws SerException {
        String[] fields = new String[]{"lender"};
        List<ApplyLend> list = super.findBySql(
                "select lender  from lendreimbursement_applylend group by lender ", ApplyLend.class, fields);

        List<String> lenderList = list.stream().map(ApplyLend::getLender)
                .filter(str -> (str != null || !"".equals(str.trim()))).distinct().collect(Collectors.toList());

        return lenderList;
    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<ApplyLend> list = super.findBySql(
                "select area  from lendreimbursement_applylend group by area ", ApplyLend.class, fields);

        List<String> areaList = list.stream().map(ApplyLend::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());

        return areaList;
    }

    @Override
    public List<String> listProjectGroup() throws SerException {
        String[] fields = new String[]{"projectGroup"};
        List<ApplyLend> list = super.findBySql(
                "select projectGroup  from lendreimbursement_applylend group by projectGroup ", ApplyLend.class, fields);

        List<String> areaList = list.stream().map(ApplyLend::getProjectGroup)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());

        return areaList;
    }

    @Override
    public List<String> listProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<ApplyLend> list = super.findBySql(
                "select projectName  from lendreimbursement_applylend group by projectName ", ApplyLend.class, fields);

        List<String> areaList = list.stream().map(ApplyLend::getProjectName)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());

        return areaList;

    }

    @Override
    public List<String> listAccountCom() throws SerException {
        // 账户来源
        List<String> list= accountAPI.listAccountOrigin();
        return list;
    }

    /**
     * 申请
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    @Override
    public byte[] exportExcel(ApplyLendDTO applyLendDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(applyLendDTO.getStartTime())) {
            start = DateUtil.parseDate(applyLendDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            end = DateUtil.parseDate(applyLendDTO.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(applyLendDTO.getStartTime()) || StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            applyLendDTO.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        //未收款且不在有误单里面
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 6, 8, 9}));

        List<ApplyLend> list = super.findByCis(applyLendDTO);

        List<ApplyLendExcel> applyLendExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ApplyLendExcel excel = BeanTransform.copyProperties(str, ApplyLendExcel.class, "lendStatus");
            excel.setLendStatus(LendStatus.exportStrConvert(str.getLendStatus()));

            applyLendExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(applyLendExcels, excel);
        return bytes;
    }

    /**
     * 等待付款导出
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    @Override
    public byte[] waitingPayExcel(ApplyLendDTO applyLendDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(applyLendDTO.getStartTime())) {
            start = DateUtil.parseDate(applyLendDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            end = DateUtil.parseDate(applyLendDTO.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(applyLendDTO.getStartTime()) || StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            applyLendDTO.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
        //LendStatus.FINACEPASS
        applyLendDTO.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
        List<ApplyLend> list = super.findByCis(applyLendDTO);

        List<ApplyLendExcel> applyLendExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ApplyLendExcel excel = BeanTransform.copyProperties(str, ApplyLendExcel.class, "lendStatus");
            excel.setLendStatus(LendStatus.exportStrConvert(str.getLendStatus()));

            applyLendExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(applyLendExcels, excel);
        return bytes;
    }

    /**
     * 借款导出
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    @Override
    public byte[] borrowExcel(ApplyLendDTO applyLendDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(applyLendDTO.getStartTime())) {
            start = DateUtil.parseDate(applyLendDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            end = DateUtil.parseDate(applyLendDTO.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(applyLendDTO.getStartTime()) || StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            applyLendDTO.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        applyLendDTO.getConditions().add(Restrict.eq("receivePay", "是"));
        List<ApplyLend> list = super.findByCis(applyLendDTO);

        List<ApplyLendExcel> applyLendExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ApplyLendExcel excel = BeanTransform.copyProperties(str, ApplyLendExcel.class, "lendStatus");
            excel.setLendStatus(LendStatus.exportStrConvert(str.getLendStatus()));

            applyLendExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(applyLendExcels, excel);
        return bytes;
    }

    /**
     * 还款记录
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    @Override
    public byte[] returnExcel(ApplyLendDTO applyLendDTO) throws SerException {

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(applyLendDTO.getStartTime())) {
            start = DateUtil.parseDate(applyLendDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            end = DateUtil.parseDate(applyLendDTO.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(applyLendDTO.getStartTime()) || StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            applyLendDTO.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        //还款记录是报销金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));
        List<ApplyLend> list = super.findByCis(applyLendDTO);

        List<ApplyLendExcel> applyLendExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ApplyLendExcel excel = BeanTransform.copyProperties(str, ApplyLendExcel.class, "lendStatus");
            excel.setLendStatus(LendStatus.exportStrConvert(str.getLendStatus()));

            applyLendExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(applyLendExcels, excel);
        return bytes;
    }

    @Override
    public byte[] receiveExcel(ApplyLendDTO applyLendDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(applyLendDTO.getStartTime())) {
            start = DateUtil.parseDate(applyLendDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            end = DateUtil.parseDate(applyLendDTO.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(applyLendDTO.getStartTime()) || StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            applyLendDTO.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        //收到单据 是
        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));
        List<ApplyLend> list = super.findByCis(applyLendDTO);

        List<ApplyLendExcel> applyLendExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ApplyLendExcel excel = BeanTransform.copyProperties(str, ApplyLendExcel.class, "lendStatus");
            excel.setLendStatus(LendStatus.exportStrConvert(str.getLendStatus()));

            applyLendExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(applyLendExcels, excel);
        return bytes;
    }

    /**
     * chenjunhao
     * 等待付款导出cjh
     *
     * @param applyLendDTO
     * @return
     * @throws SerException
     */
    @Override
    public List<ExportExcelTO> waitPayExport(ApplyLendDTO applyLendDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(applyLendDTO.getStartTime())) {
            start = DateUtil.parseDate(applyLendDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            end = DateUtil.parseDate(applyLendDTO.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(applyLendDTO.getStartTime()) || StringUtils.isNotBlank(applyLendDTO.getEndTime())) {
            applyLendDTO.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
        //LendStatus.FINACEPASS
        applyLendDTO.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
        List<ApplyLend> list = super.findByCis(applyLendDTO);

        List<ExportExcelTO> exportExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ExportExcelTO excel = BeanTransform.copyProperties(str, ExportExcelTO.class);
            excel.setPayStatus(PayStatus.WAITPAY);
            exportExcels.add(excel);
        });
        return exportExcels;
    }

    @Override
    //chenjunhao
    public List<ApplyLendBO> listWaitPayCJH(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "否"));
        //LendStatus.FINACEPASS
        dto.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
        //LendStatus.MANAGEPASS
//        dto.getConditions().add(Restrict.or("lendStatus", 7));
        List<ApplyLend> applyLend = super.findByCis(dto, true);
        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    //chenjunhao
    public ApplyLendBO editPayMoneyCJH(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getPayOrigin())) {
            throw new SerException("编辑失败，支付来源不能为空");
        }
        if (StringUtils.isBlank(applyLendTO.getPayDate())) {
            throw new SerException("编辑失败，支付日期不能为空");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setPayOrigin(applyLend.getPayOrigin());
        lend.setPayDate(applyLend.getPayDate());
        //支付人
        lend.setPayer(userAPI.currentUser().getUsername());
        lend.setPayCondition("是");
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

}