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
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.ApplyLendBO;
import com.bjike.goddess.lendreimbursement.bo.CollectDataBO;
import com.bjike.goddess.lendreimbursement.bo.LendAuditDetailBO;
import com.bjike.goddess.lendreimbursement.dto.*;
import com.bjike.goddess.lendreimbursement.dto.reimshape.*;
import com.bjike.goddess.lendreimbursement.entity.ApplyLend;
import com.bjike.goddess.lendreimbursement.entity.ApplyLendCopy;
import com.bjike.goddess.lendreimbursement.entity.LendAuditDetail;
import com.bjike.goddess.lendreimbursement.entity.ReimburseRecord;
import com.bjike.goddess.lendreimbursement.enums.*;
import com.bjike.goddess.lendreimbursement.excel.ApplyLendExcel;
import com.bjike.goddess.lendreimbursement.excel.lendreimimport.LendReimImportExcelTO;
import com.bjike.goddess.lendreimbursement.to.*;
import com.bjike.goddess.lendreimbursement.vo.LendMixReimCollectDataVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.*;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcelTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private LendAuditDetailSer lendAuditDetailSer;
    @Autowired
    private ApplyLendCopySer applyLendCopySer;
    @Autowired
    private LendPermissionSer cusPermissionSer;
    @Autowired
    private AccountAPI accountAPI;
    @Autowired
    private ReimburseRecordSer reimburseRecordSer;

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
//                flag = guideIdentity("applySurePay-Recieve");
                flag = true;
            case RECIVEMONEYSURE2:
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
                flag = checkPermission("applyReturn-ReturnCheck");
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
        if (!listpermission && !"admin".equals(userName.toLowerCase())) {
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
        if (!listpermission && !"admin".equals(userBO.getUsername().toLowerCase())) {
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
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 6, 7, 8, 9}));

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
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 6, 7, 8, 9}));

        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getEstimateLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

        applyLendDTO = addCondition(applyLendDTO);

        List<ApplyLend> list = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> listBO = BeanTransform.copyProperties(list, ApplyLendBO.class);


        return listBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO addApplyLend(ApplyLendTO applyLendTO) throws SerException {

//        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
//            throw new SerException("添加失败，未填无发票备注");
//        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        //填单人
        applyLend.setFillSingler(userAPI.currentUser().getUsername());
//        applyLend.setLendDate(LocalDate.now());
        applyLend.setCreateTime(LocalDateTime.now());
        applyLend.setCommitTime(LocalDateTime.now());
        //未付款
        applyLend.setPayCondition("否");
        //这个字段用于手机字段
        applyLend.setLendRetunStatus(LendRetunStatus.NONE);
        applyLend.setLendStatus(LendStatus.NONE);
        applyLend.setChargerPass("未处理");
        applyLend.setManagerPass("未处理");
        applyLend.setFincerPass("未处理");
        super.save(applyLend);
        ApplyLendBO bo = BeanTransform.copyProperties(applyLend, ApplyLendBO.class, "lendStatus");
        bo.setId(applyLend.getId());
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
        RpcTransmit.transmitUserToken(userToken);
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
        applyLendDTO.getSorts().add("createTime=desc");

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
        applyLendDTO.getSorts().add("createTime=desc");

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
        RpcTransmit.transmitUserToken(userToken);

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
        if (!"admin".equals(userBO.getUsername().toLowerCase()) && !userBO.getUsername().equals(lend.getCharger())) {
            throw new SerException("负责人审核失败，您不是负责人，不能进行负责人审核");
        }

        if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("该条数据已经申请过冻结，不能再操作");
        }
        if (lend.getLendStatus().getCode() == 3) {
            throw new SerException("该条数据已经被财务分析过，不能再操作");
        }
        if (lend.getLendStatus().getCode() == 4) {
            throw new SerException("该条数据已经被财务分析过，不能再操作");
        }
        lend.setCharger(userBO.getUsername());
        lend.setChargerOpinion(applyLendTO.getChargerOpinion());
        lend.setChargerPass(applyLendTO.getChargerPass());
        if ("是".equals(applyLendTO.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGEPASS);
        } else if ("否".equals(applyLendTO.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGENOTPASS);
            lend.setLendError(9);
        }

        lend.setChargerPassTime(LocalDateTime.now());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表,先查一下是否审核过,若审核过则修改，否则添加
        LendAuditDetailDTO lendAuditDetailDTO = new LendAuditDetailDTO();
        lendAuditDetailDTO.getConditions().add(Restrict.eq("empNumber", userBO.getEmployeeNumber()));
        lendAuditDetailDTO.getConditions().add(Restrict.eq("auditIdentity", "负责人"));
        lendAuditDetailDTO.getConditions().add(Restrict.eq("applyLendId", lend.getId()));
        List<LendAuditDetail> listDetails = lendAuditDetailSer.findByCis(lendAuditDetailDTO);
        if (listDetails != null && listDetails.size() > 0) {
            LendAuditDetail updateDetail = listDetails.get(0);
            updateDetail.setAuditDate(LocalDate.now());
            updateDetail.setAuditSuggest(applyLendTO.getChargerOpinion());
            updateDetail.setPassOr(applyLendTO.getChargerPass());
            updateDetail.setModifyTime(LocalDateTime.now());
            lendAuditDetailSer.update(updateDetail);
        } else {
            List<PositionDetailBO> positionBO = positionDetailUserAPI.findPositionByUser(userBO.getId());
            RpcTransmit.transmitUserToken(userToken);
            String position = "";
            if (positionBO != null && positionBO.size() > 0) {
                position = positionBO.get(0).getPosition();
            }
            //职位名
            LendAuditDetail lendAuditDetail = new LendAuditDetail();
            lendAuditDetail.setPosition(position);
            lendAuditDetail.setAuditIdentity("负责人");
            lendAuditDetail.setEmpNumber(userBO.getEmployeeNumber());
            lendAuditDetail.setAuditor(userBO.getUsername());
            lendAuditDetail.setAuditDate(LocalDate.now());
            lendAuditDetail.setAuditSuggest(applyLendTO.getChargerOpinion());
            lendAuditDetail.setPassOr(applyLendTO.getChargerPass());
            lendAuditDetail.setApplyLendId(lend.getId());
            lendAuditDetail.setCreateTime(LocalDateTime.now());
            lendAuditDetailSer.save(lendAuditDetail);
        }

        ApplyLendBO bo = BeanTransform.copyProperties(lend, ApplyLendBO.class, "lendStatus");
        bo.setLendStatus(lend.getLendStatus());
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editOperateWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        LendGuidePermissionTO guidePermissionTO = new LendGuidePermissionTO();
        guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.FINACEAUDIT);
        Boolean finaceFlag = guidePermission(guidePermissionTO);
        if (!finaceFlag) {
            throw new SerException("您没有财务审核的权限，审核失败");
        }
        RpcTransmit.transmitUserToken(userToken);

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
        RpcTransmit.transmitUserToken(userToken);
        lend.setFinacer(userBO.getUsername());
        lend.setFincerOpinion(applyLendTO.getFincerOpinion());
        lend.setFincerPass(applyLendTO.getFincerPass());
        if ("是".equals(applyLendTO.getFincerPass())) {
            lend.setLendStatus(LendStatus.FINACEPASS);
        } else if ("否".equals(applyLendTO.getFincerPass())) {
            lend.setLendStatus(LendStatus.FINACENOTPASS);
        }
        lend.setFincerPassTime(LocalDateTime.now());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表,先查一下是否审核过,若审核过则修改，否则添加
        LendAuditDetailDTO lendAuditDetailDTO = new LendAuditDetailDTO();
        lendAuditDetailDTO.getConditions().add(Restrict.eq("empNumber", userBO.getEmployeeNumber()));
        lendAuditDetailDTO.getConditions().add(Restrict.eq("auditIdentity", "财务"));
        lendAuditDetailDTO.getConditions().add(Restrict.eq("applyLendId", lend.getId()));
        List<LendAuditDetail> listDetails = lendAuditDetailSer.findByCis(lendAuditDetailDTO);
        if (listDetails != null && listDetails.size() > 0) {
            LendAuditDetail updateDetail = listDetails.get(0);
            updateDetail.setAuditDate(LocalDate.now());
            updateDetail.setAuditSuggest(applyLendTO.getFincerOpinion());
            updateDetail.setPassOr(applyLendTO.getFincerPass());
            updateDetail.setModifyTime(LocalDateTime.now());
            lendAuditDetailSer.update(updateDetail);
        } else {
            List<PositionDetailBO> positionBO = positionDetailUserAPI.findPositionByUser(userBO.getId());
            RpcTransmit.transmitUserToken(userToken);
            String position = "";
            if (positionBO != null && positionBO.size() > 0) {
                position = positionBO.get(0).getPosition();
            }
            //职位名
            LendAuditDetail lendAuditDetail = new LendAuditDetail();
            lendAuditDetail.setPosition(position);
            lendAuditDetail.setAuditIdentity("财务");
            lendAuditDetail.setEmpNumber(userBO.getEmployeeNumber());
            lendAuditDetail.setAuditor(userBO.getUsername());
            lendAuditDetail.setAuditDate(LocalDate.now());
            lendAuditDetail.setAuditSuggest(applyLendTO.getFincerOpinion());
            lendAuditDetail.setPassOr(applyLendTO.getFincerPass());
            lendAuditDetail.setApplyLendId(lend.getId());
            lendAuditDetail.setCreateTime(LocalDateTime.now());
            lendAuditDetailSer.save(lendAuditDetail);
        }
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editManageWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("总经办审核失败，未填id");
        }
        LendGuidePermissionTO guidePermissionTO = new LendGuidePermissionTO();
        guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.MANAGEAUDIT);
        Boolean finaceFlag = guidePermission(guidePermissionTO);
        if (!finaceFlag) {
            throw new SerException("您没有总经办审核的权限，审核失败");
        }
        RpcTransmit.transmitUserToken(userToken);

        ApplyLend lend = super.findById(applyLendTO.getId());
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        //说明财务还没审核
        if (lend.getLendStatus().getCode() == 3 || lend.getLendStatus().getCode() == 4) {
            lend.setManager(userBO.getUsername());
            lend.setManagerOpinion(applyLendTO.getManagerOpinion());
            lend.setManagerPass(applyLendTO.getManagerPass());
            if ("是".equals(applyLendTO.getManagerPass())) {
                lend.setLendStatus(LendStatus.MANAGEPASS);
            } else if ("否".equals(applyLendTO.getManagerPass())) {
                lend.setLendStatus(LendStatus.MANAGENOTPASS);
            }
            lend.setManagerPassTime(LocalDateTime.now());
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


        //存审核详情表,先查一下是否审核过,若审核过则修改，否则添加
        LendAuditDetailDTO lendAuditDetailDTO = new LendAuditDetailDTO();
        lendAuditDetailDTO.getConditions().add(Restrict.eq("empNumber", userBO.getEmployeeNumber()));
        lendAuditDetailDTO.getConditions().add(Restrict.eq("auditIdentity", "总经办"));
        lendAuditDetailDTO.getConditions().add(Restrict.eq("applyLendId", lend.getId()));
        List<LendAuditDetail> listDetails = lendAuditDetailSer.findByCis(lendAuditDetailDTO);
        if (listDetails != null && listDetails.size() > 0) {
            LendAuditDetail updateDetail = listDetails.get(0);
            updateDetail.setAuditDate(LocalDate.now());
            updateDetail.setAuditSuggest(applyLendTO.getManagerOpinion());
            updateDetail.setPassOr(applyLendTO.getManagerPass());
            updateDetail.setModifyTime(LocalDateTime.now());
            lendAuditDetailSer.update(updateDetail);
        } else {
            List<PositionDetailBO> positionBO = positionDetailUserAPI.findPositionByUser(userBO.getId());
            RpcTransmit.transmitUserToken(userToken);
            String position = "";
            if (positionBO != null && positionBO.size() > 0) {
                position = positionBO.get(0).getPosition();
            }
            //职位名
            LendAuditDetail lendAuditDetail = new LendAuditDetail();
            lendAuditDetail.setPosition(position);
            lendAuditDetail.setAuditIdentity("总经办");
            lendAuditDetail.setEmpNumber(userBO.getEmployeeNumber());
            lendAuditDetail.setAuditor(userBO.getUsername());
            lendAuditDetail.setAuditDate(LocalDate.now());
            lendAuditDetail.setAuditSuggest(applyLendTO.getManagerOpinion());
            lendAuditDetail.setPassOr(applyLendTO.getManagerPass());
            lendAuditDetail.setApplyLendId(lend.getId());
            lendAuditDetail.setCreateTime(LocalDateTime.now());
            lendAuditDetailSer.save(lendAuditDetail);
        }

        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editOperateCongel(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
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
        RpcTransmit.transmitUserToken(userToken);
        lend.setFinacer(userBO.getUsername());
        lend.setFincerOpinion(applyLendTO.getFincerOpinion());
        lend.setFincerPass("未处理");

        lend.setLendStatus(LendStatus.FINACECONGEL);

        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表,先查一下是否审核过,若审核过则修改，否则添加
        List<PositionDetailBO> positionBO = positionDetailUserAPI.findPositionByUser(userBO.getId());
        RpcTransmit.transmitUserToken(userToken);
        String position = "";
        if (positionBO != null && positionBO.size() > 0) {
            position = positionBO.get(0).getPosition();
        }
        //职位名
        LendAuditDetail lendAuditDetail = new LendAuditDetail();
        lendAuditDetail.setPosition(position);
        lendAuditDetail.setAuditIdentity("财务");
        lendAuditDetail.setEmpNumber(userBO.getEmployeeNumber());
        lendAuditDetail.setAuditor(userBO.getUsername());
        lendAuditDetail.setAuditDate(LocalDate.now());
        lendAuditDetail.setAuditSuggest("财务运营部申请冻结");
        lendAuditDetail.setPassOr("不通过");
        lendAuditDetail.setApplyLendId(lend.getId());
        lendAuditDetail.setCreateTime(LocalDateTime.now());
        lendAuditDetailSer.save(lendAuditDetail);

        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editChargeSureCongel(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("负责人冻结失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (lend.getLendStatus().getCode() != 5) {
            throw new SerException("负责人确认冻结失败，此条数据财务部还未冻结");
        }
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        lend.setCharger(userBO.getUsername());

        lend.setLendStatus(LendStatus.CHARGESURECONGEL);
        lend.setChargerPassTime(LocalDateTime.now());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表,先查一下是否审核过,若审核过则修改，否则添加
        List<PositionDetailBO> positionBO = positionDetailUserAPI.findPositionByUser(userBO.getId());
        RpcTransmit.transmitUserToken(userToken);
        String position = "";
        if (positionBO != null && positionBO.size() > 0) {
            position = positionBO.get(0).getPosition();
        }
        //职位名
        LendAuditDetail lendAuditDetail = new LendAuditDetail();
        lendAuditDetail.setPosition(position);
        lendAuditDetail.setAuditIdentity("负责人");
        lendAuditDetail.setEmpNumber(userBO.getEmployeeNumber());
        lendAuditDetail.setAuditor(userBO.getUsername());
        lendAuditDetail.setAuditDate(LocalDate.now());
        lendAuditDetail.setAuditSuggest("负责人确认冻结");
        lendAuditDetail.setPassOr("通过");
        lendAuditDetail.setApplyLendId(lend.getId());
        lendAuditDetail.setCreateTime(LocalDateTime.now());
        lendAuditDetailSer.save(lendAuditDetail);

        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editChargeConcelCongel(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("负责人取消冻结失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (lend.getLendStatus().getCode() != 5) {
            throw new SerException("负责人确认取消冻结失败，此条数据财务部还未冻结");
        }
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        lend.setCharger(userBO.getUsername());

        if ("是".equals(lend.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGEPASS);
        } else {
            lend.setLendStatus(LendStatus.CHARGENOTPASS);
        }
        lend.setChargerPassTime(LocalDateTime.now());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表,先查一下是否审核过,若审核过则修改，否则添加
        List<PositionDetailBO> positionBO = positionDetailUserAPI.findPositionByUser(userBO.getId());
        RpcTransmit.transmitUserToken(userToken);
        String position = "";
        if (positionBO != null && positionBO.size() > 0) {
            position = positionBO.get(0).getPosition();
        }
        //职位名
        LendAuditDetail lendAuditDetail = new LendAuditDetail();
        lendAuditDetail.setPosition(position);
        lendAuditDetail.setAuditIdentity("负责人");
        lendAuditDetail.setEmpNumber(userBO.getEmployeeNumber());
        lendAuditDetail.setAuditor(userBO.getUsername());
        lendAuditDetail.setAuditDate(LocalDate.now());
        lendAuditDetail.setAuditSuggest("负责人取消冻结");
        lendAuditDetail.setPassOr("是".equals(lend.getChargerPass()) ? "负责人取消冻结" : "负责人确认冻结");
        lendAuditDetail.setApplyLendId(lend.getId());
        lendAuditDetail.setCreateTime(LocalDateTime.now());
        lendAuditDetailSer.save(lendAuditDetail);

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
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{0, 1, 3, 4, 5, 7}));
        applyLendDTO = addCondition(applyLendDTO);
        applyLendDTO.getSorts().add("createTime=desc");

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
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{0, 1, 3, 4, 5, 7}));

        applyLendDTO = addCondition(applyLendDTO);
        applyLendDTO.getSorts().add("createTime=desc");

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

        RpcTransmit.transmitUserToken(userToken);
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
        RpcTransmit.transmitUserToken(userToken);
        lend.setFillSingler(userAPI.currentUser().getUsername());
        RpcTransmit.transmitUserToken(userToken);
        lend.setLendDate(applyLend.getLendDate());
        lend.setModifyTime(LocalDateTime.now());
        //未付款
        lend.setPayCondition("否");
        lend.setChargerPass("未处理");
        lend.setManagerPass("未处理");
        lend.setFincerPass("未处理");
        lend.setLendStatus(LendStatus.NONE);
        //这个字段用于手机字段
        lend.setLendRetunStatus(LendRetunStatus.NONE);
        lend.setCommitTime(LocalDateTime.now());

        super.update(lend);

        //清掉审核记录
        LendAuditDetailDTO lendAuditDetailDTO = new LendAuditDetailDTO();
        lendAuditDetailDTO.getConditions().add(Restrict.eq("applyLendId", lend.getId()));
        List<LendAuditDetail> listDetails = lendAuditDetailSer.findByCis(lendAuditDetailDTO);
        lendAuditDetailSer.remove(listDetails);

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
        ApplyLend lend = super.findById(id);
        if (lend == null) {
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
        dto.getSorts().add("createTime=desc");

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
        dto.getSorts().add("createTime=desc");

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
        dto.getSorts().add("createTime=desc");

        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public Long countWaitPayCJH(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "否"));
        //LendStatus.FINACEPASS
        dto.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
        //LendStatus.MANAGEPASS
//        dto.getConditions().add(Restrict.or("lendStatus", 7));

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
        dto.getSorts().add("createTime=desc");

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
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("编辑失败，id不能为空");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setPayOrigin(applyLendTO.getPayOrigin());
        lend.setPayDate(applyLend.getPayDate());
        lend.setLendDate(applyLend.getPayDate());
        //支付人
        lend.setPayer(userAPI.currentUser().getUsername());
        lend.setPayCondition("是");
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Override
    public Long countSureRecieve(ApplyLendDTO applyLendDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean listpermission = cusPermissionSer.getCusPermission("apply-ListAll");
        RpcTransmit.transmitUserToken(userToken);

        String userName = userAPI.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        String[] field = new String[]{"id"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT payCondition ,id ")
                .append(" FROM lendreimbursement_applylend ")
                .append(" WHERE  payCondition = '是' AND  receivePay IS NULL ");
        if (!"admin".equals(userName.toLowerCase()) && listpermission.equals(false)) {
            sql.append(" AND ( fillSingler = '" + userName + "'  OR lender = '" + userName + "') ");
        }

        List<Object> list = super.findBySql(sql.toString());
        Long counts = (list != null && list.size() > 0) ? list.size() : 0L;
        return counts;
    }

    @Override
    public List<ApplyLendBO> listSureRecieveMoney(ApplyLendDTO applyLendDTO) throws SerException {

        String userToken = RpcTransmit.getUserToken();
        Boolean listpermission = cusPermissionSer.getCusPermission("apply-ListAll");
        RpcTransmit.transmitUserToken(userToken);

        String userName = userAPI.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        String[] field = new String[]{"id", "fillSingler", "lendDate", "estimateLendDate", "area", "projectGroup", "projectName", "lender",
                "firstSubject", "secondSubject", "thirdSubject", "explains", "writeUp", "writeUpCondition", "lendReson", "remark",
                "money", "attender", "lendWay", "goodsLink", "charger", "chargerOpinion"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT id, fillSingler,lendDate,estimateLendDate,area,projectGroup,projectName,lender ,")
                .append(" firstSubject,secondSubject,thirdSubject,explains,writeUp,writeUpCondition,lendReson,remark, ")
                .append(" money,attender,lendWay,goodsLink,charger,chargerOpinion ")
                .append(" FROM lendreimbursement_applylend ")
                .append(" WHERE  payCondition = '是' AND  receivePay IS NULL ");
        if (!"admin".equals(userName.toLowerCase()) && listpermission.equals(false)) {
            sql.append(" AND ( fillSingler = '" + userName + "'  OR lender = '" + userName + "')  order by createTime desc ");
        }

        List<ApplyLendBO> applyLend = super.findBySql(sql.toString(), ApplyLendBO.class, field);

        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editSureRecieveMoney(ApplyLendTO applyLendTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);

        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("编辑失败，id不能为空");
        }

        LendGuidePermissionTO guidePermissionTO = new LendGuidePermissionTO();
        guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.RECIVEMONEYSURE2);
        Boolean sureReciveMoneyFlag = guidePermission(guidePermissionTO);
        RpcTransmit.transmitUserToken(userToken);

        String id = applyLendTO.getId();
        ApplyLend lend = super.findById(id);
        //只有借款人和有权限的人才可以去确认收款
        if (userName.equals(lend.getLender()) || sureReciveMoneyFlag) {
            //确认收款
            lend.setReceivePay("是");
            lend.setReceivePayTime(LocalDate.now());
            lend.setModifyTime(LocalDateTime.now());
            super.update(lend);
        }

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
        dto.getSorts().add("createTime=desc");

        List<ApplyLend> applyLend = super.findByCis(dto, true);
        return BeanTransform.copyProperties(applyLend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editReturnBorrowRecord(ApplyLendTO applyLendTO) throws SerException {

        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("还款失败，id不能为空");
        }
        if ("转账".equals(applyLendTO.getReturnWays()) && StringUtils.isBlank(applyLendTO.getReturnAccount())) {
            throw new SerException("还款失败，还款账户不能为空");
        }
        if (null != applyLendTO.getReimMoney() && applyLendTO.getReimMoney() <= 0d) {
            throw new SerException("还款失败，借款金额必须大于0");
        }

        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (LendRetunStatus.NOTPASS.equals(lend.getLendRetunStatus()) || LendRetunStatus.PASS.equals(lend.getLendRetunStatus())) {
            throw new SerException("该条数据已经进行过还款核对，不能编辑");
        }
        lend.setReimMoney(applyLend.getReimMoney());
        lend.setLendMoney((null != applyLend.getLendMoney() && applyLend.getLendMoney().equals(lend.getMoney())) ? applyLend.getLendMoney() : lend.getMoney());
        lend.setReturnMoney(applyLend.getReturnMoney());
        lend.setReturnDate(applyLend.getReturnDate());
        lend.setReturnWays(applyLend.getReturnWays());
        lend.setReturnAccount(applyLend.getReturnAccount());
        //表示已填还款，还未进行还款核对
        lend.setLendRetunStatus(LendRetunStatus.WAITRETURNCHECK);
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editBorrowRecordSend(ApplyLendTO applyLendTO) throws SerException {
        String userName = userAPI.currentUser().getUsername();
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("寄件失败，id不能为空");
        }

        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setSendDate(applyLend.getSendDate());
        lend.setSendCondition(applyLend.getSendCondition());
        lend.setReceiveAddr(applyLend.getReceiveAddr());
        lend.setSender(userName);
        lend.setDocumentQuantity(applyLendTO.getDocumentQuantity());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }


    @Override
    public ApplyLendBO editPhoneReturn(PhoneLendReturnSendTO phoneLendReturnSendTO) throws SerException {
        if (StringUtils.isBlank(phoneLendReturnSendTO.getId())) {
            throw new SerException("还款失败，id不能为空");
        }
        if ("转账".equals(phoneLendReturnSendTO.getReturnWays()) && StringUtils.isBlank(phoneLendReturnSendTO.getReturnAccount())) {
            throw new SerException("还款失败，还款账户不能为空");
        }
        if (null != phoneLendReturnSendTO.getReimMoney() && phoneLendReturnSendTO.getReimMoney() <= 0d) {
            throw new SerException("还款失败，借款金额必须大于0");
        }
        ApplyLend temp = BeanTransform.copyProperties(phoneLendReturnSendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(phoneLendReturnSendTO.getId());

        lend.setReimMoney(phoneLendReturnSendTO.getReimMoney());
        //这里不同，把money改成了lendmoney
        lend.setLendMoney(lend.getMoney());
        lend.setReturnMoney(phoneLendReturnSendTO.getReturnMoney());
        lend.setReturnDate(temp.getReturnDate());
        lend.setReturnWays(phoneLendReturnSendTO.getReturnWays());
        lend.setReturnAccount(phoneLendReturnSendTO.getReturnAccount());
        lend.setModifyTime(LocalDateTime.now());
        //之前在添加的页面，现在搬到这里来了：是否有发票（单据）
        lend.setInvoice(phoneLendReturnSendTO.getInvoice());
        //表示已填还款，还未进行还款核对
        lend.setLendRetunStatus(LendRetunStatus.WAITRETURNCHECK);
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Override
    public ApplyLendBO editPhoneSend(PhoneLendSendTO phoneLendSendTO) throws SerException {
        String userName = userAPI.currentUser().getUsername();
        if (StringUtils.isBlank(phoneLendSendTO.getId())) {
            throw new SerException("寄件失败，id不能为空");
        }

        ApplyLend applyLend = BeanTransform.copyProperties(phoneLendSendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(phoneLendSendTO.getId());

        lend.setSendDate(applyLend.getSendDate());
        lend.setSendCondition(phoneLendSendTO.getSendCondition());
        lend.setReceiveAddr(phoneLendSendTO.getReceiveAddr());
        lend.setReceiveArea(phoneLendSendTO.getReceiveArea());
        lend.setSender(userName);
        lend.setSendRecevier(phoneLendSendTO.getSendRecevier());
        lend.setDocumentQuantity(phoneLendSendTO.getDocumentQuantity());
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
        //还款记录是借款金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "是"));
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));
        //还款状态为未处理和审核不通过的情况
        applyLendDTO.getConditions().add(Restrict.in("lendRetunStatus", new Integer[]{0, 1, 3}));

        applyLendDTO = addCondition(applyLendDTO);

        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listReturnMoneyRecord(ApplyLendDTO applyLendDTO) throws SerException {
        //还款记录是借款金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "是"));
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));
        //还款状态为未处理和审核不通过的情况
        applyLendDTO.getConditions().add(Restrict.in("lendRetunStatus", new Integer[]{0, 1, 3}));

        applyLendDTO = addCondition(applyLendDTO);
        applyLendDTO.getSorts().add("createTime=desc");

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
        if (StringUtils.isBlank(applyLendTO.getCheckPassOr()) || (!"是".equals(applyLendTO.getCheckPassOr()) && !"否".equals(applyLendTO.getCheckPassOr()))) {
            throw new SerException("失败，是否通过只能选是或否");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        lend.setChecker(userAPI.currentUser().getUsername());
        lend.setCheckDate(LocalDate.now());
        lend.setCheckcontent(applyLendTO.getCheckcontent());
        lend.setModifyTime(LocalDateTime.now());//表示已填还款，还未进行还款核对
        lend.setLendRetunStatus("是".equals(applyLendTO.getCheckPassOr()) ? LendRetunStatus.PASS : LendRetunStatus.NOTPASS);
        //这里把是否收到单据设置，而账务核对那里的收到单据只是填一下情况
        lend.setDocumentCondition("是".equals(applyLendTO.getCheckPassOr()) ? "是" : "否");
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);

    }

    @Override
    public ApplyLendBO phoneCheckReturn(PhoneLendReturnCheckTO phoneLendReturnCheckTO) throws SerException {
        if (StringUtils.isBlank(phoneLendReturnCheckTO.getId())) {
            throw new SerException("失败，id不能为空");
        }
        if (StringUtils.isBlank(phoneLendReturnCheckTO.getCheckcontent())) {
            throw new SerException("失败，审核原因不能为空");
        }
        if (StringUtils.isBlank(phoneLendReturnCheckTO.getDocumentCondition())) {
            throw new SerException("失败，请填写是否收到单据（是/否）");
        }
        if (!LendRetunStatus.NOTPASS.equals(phoneLendReturnCheckTO.getLendRetunStatus())
                && !LendRetunStatus.PASS.equals(phoneLendReturnCheckTO.getLendRetunStatus())) {
            throw new SerException("失败，请填写是否通过，lendRetunStatus");
        }
        ApplyLend lend = super.findById(phoneLendReturnCheckTO.getId());

        //还款核对
        UserBO userBO = userAPI.currentUser();
        lend.setChecker(userBO.getUsername());
        lend.setCheckDate(LocalDate.now());
        lend.setCheckcontent(phoneLendReturnCheckTO.getCheckcontent());
        lend.setDocumentCondition(phoneLendReturnCheckTO.getDocumentCondition());

        //账务核对的收到单据
        lend.setTicketDate(lend.getReturnDate());
        lend.setTicketer(userBO.getUsername());
        lend.setTicketCondition(phoneLendReturnCheckTO.getCheckcontent());
        lend.setReceiveTicket(phoneLendReturnCheckTO.getDocumentCondition());
        lend.setModifyTime(LocalDateTime.now());
        //表示已填还款，已进行还款核对
        lend.setLendRetunStatus(phoneLendReturnCheckTO.getLendRetunStatus());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);

    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editErrorReturn(ApplyLendTO applyLendTO) throws SerException {

        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("还款失败，id不能为空");
        }
        if ("转账".equals(applyLendTO.getReturnWays()) && StringUtils.isBlank(applyLendTO.getReturnAccount())) {
            throw new SerException("还款失败，还款账户不能为空");
        }
        if (null != applyLendTO.getReimMoney() && applyLendTO.getReimMoney() <= 0d) {
            throw new SerException("还款失败，借款金额必须大于0");
        }


        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (!LendRetunStatus.NOTPASS.equals(lend.getLendRetunStatus())) {
            throw new SerException("该条数据不是还款核对有误的记录，不能编辑");
        }
        lend.setReimMoney(applyLend.getReimMoney());
        lend.setLendMoney((null != applyLend.getLendMoney() && applyLend.getLendMoney().equals(lend.getMoney())) ? applyLend.getLendMoney() : lend.getMoney());
        lend.setReturnMoney(applyLend.getReturnMoney());
        lend.setReturnDate(applyLend.getReturnDate());
        lend.setReturnWays(applyLend.getReturnWays());
        lend.setReturnAccount(applyLend.getReturnAccount());
        //表示已填还款，还未进行还款核对
        lend.setLendRetunStatus(LendRetunStatus.WAITRETURNCHECK);
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Override
    public Long countBusCheck(ApplyLendDTO applyLendDTO) throws SerException {
        //账务核对是借款金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));
        //还款状态为未处理和审核不通过的情况
        applyLendDTO.getConditions().add(Restrict.in("lendRetunStatus", new Integer[]{0, 1, 3}));

        applyLendDTO = addCondition(applyLendDTO);

        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listBusinessCheck(ApplyLendDTO applyLendDTO) throws SerException {
        //账务核对是借款金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));
        //还款状态为未处理和审核不通过的情况
        applyLendDTO.getConditions().add(Restrict.in("lendRetunStatus", new Integer[]{0, 1, 3}));

        applyLendDTO = addCondition(applyLendDTO);
        applyLendDTO.getSorts().add("createTime=desc");

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
        if (StringUtils.isBlank(applyLendTO.getTicketDate())) {
            throw new SerException("失败，收票日期不能为空");
        }
//        if (!"否".equals(applyLendTO.getDocumentCondition()) && !"是".equals(applyLendTO.getDocumentCondition())) {
//            throw new SerException("失败，是否收到单据填写是或否");
//        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        lend.setDocumentCondition(applyLendTO.getDocumentCondition());
        lend.setTicketDate(LocalDate.parse(applyLendTO.getTicketDate()));
        lend.setTicketer(userAPI.currentUser().getUsername());
        lend.setTicketCondition(applyLendTO.getTicketCondition());
        lend.setReceiveTicket(applyLendTO.getReceiveTicket());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);

    }

    @Override
    public Long countRecTicket(ApplyLendDTO applyLendDTO) throws SerException {
        //收到单据 是 或手机端还款核对通过的
//        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));
        //手机端还款核对（账务核对）通过的
        applyLendDTO.getConditions().add(Restrict.eq("lendRetunStatus", 2));

        applyLendDTO = addCondition(applyLendDTO);

        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listRecieveTicketRecord(ApplyLendDTO applyLendDTO) throws SerException {
        //收到单据 是 或手机端还款核对通过的
//        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));
        //手机端还款核对（账务核对）通过的
        applyLendDTO.getConditions().add(Restrict.eq("lendRetunStatus", 2));

        applyLendDTO = addCondition(applyLendDTO);
        applyLendDTO.getSorts().add("createTime=desc");

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
    public List<String> getAllUser() throws SerException {
        List<UserBO> userBOS = userAPI.findAllUser();
        List<String> lenderList = new ArrayList<>();
        if (userBOS != null && userBOS.size() > 0) {
            lenderList = userBOS.stream().map(UserBO::getUsername).collect(Collectors.toList());
        }
        return lenderList;
    }

    @Override
    public List<String> listLender() throws SerException {
        String[] fields = new String[]{"lender"};
        List<ApplyLend> list = super.findBySql(
                "select lender  from lendreimbursement_applylend group by lender ", ApplyLend.class, fields);

        List<String> lenderList = list.stream().filter(applyLend -> (StringUtils.isNotBlank(applyLend.getLender())))
                .map(ApplyLend::getLender)
                .filter(str -> (str != null || !"".equals(str.trim()))).distinct().collect(Collectors.toList());

        return lenderList;
    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<ApplyLend> list = super.findBySql(
                "select area  from lendreimbursement_applylend group by area ", ApplyLend.class, fields);

        List<String> areaList = list.stream().filter(applyLend -> (StringUtils.isNotBlank(applyLend.getArea())))
                .map(ApplyLend::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());

        return areaList;
    }

    @Override
    public List<String> listPhoneArea() throws SerException {
        List<AreaBO> list = departmentDetailAPI.findArea();
        List<String> areaList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            areaList = list.stream().map(AreaBO::getArea).collect(Collectors.toList());
        }
        if (areaList != null && areaList.size() > 0) {
            areaList = areaList.stream().distinct().collect(Collectors.toList());
        }
        return areaList;
    }

    @Override
    public List<String> listProjectGroup() throws SerException {
        String[] fields = new String[]{"projectGroup"};
        List<ApplyLend> list = super.findBySql(
                "select projectGroup  from lendreimbursement_applylend group by projectGroup ", ApplyLend.class, fields);

        List<String> areaList = list.stream().filter(applyLend -> (StringUtils.isNotBlank(applyLend.getProjectGroup())))
                .map(ApplyLend::getProjectGroup)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());

        return areaList;
    }

    @Override
    public List<String> listPhoneProjectGroup(PhoneApplyLendSelectDTO phoneApplyLendSelectDTO) throws SerException {
        String area = phoneApplyLendSelectDTO.getArea();
        List<String> list = departmentDetailAPI.findDepartByArea(area);

        return list;
    }

    @Override
    public List<String> listPhoneProjectName(PhoneApplyLendSelectDTO phoneApplyLendSelectDTO) throws SerException {
        String area = phoneApplyLendSelectDTO.getArea();
        String depart = phoneApplyLendSelectDTO.getProjectGroup();
        List<String> list = departmentDetailAPI.findPnameByAreaAndDepart(area, depart);

        return list;
    }

    @Override
    public List<String> listProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<ApplyLend> list = super.findBySql(
                "select projectName  from lendreimbursement_applylend group by projectName ", ApplyLend.class, fields);

        List<String> areaList = list.stream().filter(applyLend -> (StringUtils.isNotBlank(applyLend.getProjectName())))
                .map(ApplyLend::getProjectName)
                .distinct().collect(Collectors.toList());

        return areaList;

    }

    @Override
    public List<String> listAccountCom() throws SerException {
        // 账户来源
        List<String> list = accountAPI.listAccountOrigin();
        return list;
    }

    /**
     * 申请
     * 对比的日期为预计借款时间
     * 要求是未付款且不在申请单有误里面
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
            applyLendDTO.getConditions().add(Restrict.between("estimateLendDate", lendDate));//预计借款时间段查询
        }
        //未付款且不在有误单里面
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
        //还款记录是借款金额和借款金额>0
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
//        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));
        applyLendDTO.getConditions().add(Restrict.or("lendRetunStatus", 2));
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


    @Override
    public Boolean phoneShowRight(LendPhoneShowStatus lendPhoneShowStatus, String lendId) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flag = false;
        ApplyLend applyLend = super.findById(lendId);
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        String charger = applyLend.getCharger();
        LendGuidePermissionTO guidePermissionTO = new LendGuidePermissionTO();
        //待审核详情,负责人，财务权限，总经办权限
        if (lendPhoneShowStatus.equals(LendPhoneShowStatus.WAITAUDIT)) {
            guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.FINACEAUDIT);
            Boolean finaceFlag = guidePermission(guidePermissionTO);
            RpcTransmit.transmitUserToken(userToken);
            guidePermissionTO = new LendGuidePermissionTO();
            guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.MANAGEAUDIT);
            Boolean manageFlag = guidePermission(guidePermissionTO);
            RpcTransmit.transmitUserToken(userToken);
            if (userName.equals(charger) || finaceFlag || manageFlag) {
                flag = true;
            } else {
                flag = false;
            }
        } else if (lendPhoneShowStatus.equals(LendPhoneShowStatus.WAITPAY)) {
            //付款详情,只有有权限的人才可以付款
            guidePermissionTO = new LendGuidePermissionTO();
            guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.PAY);
            flag = guidePermission(guidePermissionTO);
            RpcTransmit.transmitUserToken(userToken);
        } else if (lendPhoneShowStatus.equals(LendPhoneShowStatus.SURERECEIVE)) {
            //确认收款详情,只有借款人和有权限的人才可以去确认收款
            guidePermissionTO = new LendGuidePermissionTO();
            guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.RECIVEMONEYSURE2);
            Boolean sureReciveMoneyFlag = guidePermission(guidePermissionTO);
            RpcTransmit.transmitUserToken(userToken);

            if (userName.equals(applyLend.getLender()) || sureReciveMoneyFlag) {
                flag = true;
            }
        } else if ("是".equals(applyLend.getReceivePay()) && applyLend.getLendRetunStatus().equals(LendRetunStatus.WAITRETURNCHECK) && lendPhoneShowStatus.equals(LendPhoneShowStatus.WAITRETURNCHECK)) {
            //还款核对详情（包括网页版中的账务核对）
            guidePermissionTO = new LendGuidePermissionTO();
            guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.RETURNCHECK);
            flag = guidePermission(guidePermissionTO);
            RpcTransmit.transmitUserToken(userToken);
        } else if (lendPhoneShowStatus.equals(LendPhoneShowStatus.WAITTHAW)) {
            //待解冻状态,只有admin和借款人有权限重新编辑
            if (applyLend.getLender().equals(userName)) {
                flag = true;
            }

        }

        if ("是".equals(applyLend.getReceivePay()) && !applyLend.getLendRetunStatus().equals(LendRetunStatus.WAITRETURNCHECK) && applyLend.getLender().equals(userName)) {
            //表示已确认付款，但还没填还款和还未进行还款核对
            //只有借款人自己有权限可以操作”去还款“
            flag = true;
        }

        return flag;
    }

    @Override
    public List<ApplyLendBO> listAll(PhoneApplyLendDTO dto) throws SerException {
        //先查个人的
        //再查所有的
        LendPhoneSelectStatus lendPhoneSelectStatus = dto.getLendPhoneSelectStatus();
        String userToken = RpcTransmit.getUserToken();
        ApplyLendDTO applyLendDTO = new ApplyLendDTO();
        List<ApplyLend> list = new ArrayList<>();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        switch (lendPhoneSelectStatus) {
            case ALL:
                break;
            case WAITAUDIT:
                //若没有审核权限的则只有借款人自己的数据，判断权限
                LendGuidePermissionTO guidePermissionTO = new LendGuidePermissionTO();
                guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.FINACEAUDIT);
                Boolean finaceFlag = guidePermission(guidePermissionTO);
                RpcTransmit.transmitUserToken(userToken);
                guidePermissionTO = new LendGuidePermissionTO();
                guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.MANAGEAUDIT);
                Boolean manageFlag = guidePermission(guidePermissionTO);
                RpcTransmit.transmitUserToken(userToken);
                if (!finaceFlag && !manageFlag) {
                    applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
                    applyLendDTO.getConditions().add(Restrict.or("charger", userName));
                }
                //只要审核
                applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
                applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 3, 6, 7, 8, 9}));
                break;
            case WAITPAY:
                guidePermissionTO = new LendGuidePermissionTO();
                guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.PAY);
                Boolean payFlag = guidePermission(guidePermissionTO);
                RpcTransmit.transmitUserToken(userToken);
                if (!payFlag) {
                    applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
                }
                //还未付款操作
                applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
                applyLendDTO.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
                break;
            case HASLEND:
                //每个人只能看到自己的,除了admin可以看到所有
                if (!"admin".equals(userName.toLowerCase())) {
                    applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
                }
                //已付款，包括付款了还未确认收款
                applyLendDTO.getConditions().add(Restrict.eq("payCondition", "是"));
                applyLendDTO.getConditions().add(Restrict.isNull("receivePay"));

                break;
            case WAITRETURN:
                guidePermissionTO = new LendGuidePermissionTO();
                guidePermissionTO.setGuideAddrStatus(GuideAddrStatus.RETURNCHECK);
                Boolean returnFlag = guidePermission(guidePermissionTO);
                RpcTransmit.transmitUserToken(userToken);
                if (!returnFlag) {
                    applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
                }
                //已付款了且已确认收款，且没有还款核对通过和账务核对通过的数据，对应手机端（还款状态为未处理和审核不通过的情况）
                applyLendDTO.getConditions().add(Restrict.eq("payCondition", "是"));
                applyLendDTO.getConditions().add(Restrict.eq("receivePay", "是"));
//                applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
//                applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));
                //还款状态为未处理和审核不通过和等待核对的情况
                applyLendDTO.getConditions().add(Restrict.in("lendRetunStatus", new Integer[]{0, 1, 3}));
                break;
            case HASRETURN:
                //每个人只能看到自己的,除了admin可以看到所有
                if (!"admin".equals(userName.toLowerCase())) {
                    applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
                }
                //手机端还款核对（账务核对）通过的
                applyLendDTO.getConditions().add(Restrict.eq("lendRetunStatus", 2));
                break;
            case WAITTHAW:
                //待解冻（负责人，总经办审核不通过的，即申请单有误的情况）
                //每个人只能看到自己的,除了admin可以看到所有
                if (!"admin".equals(userName.toLowerCase())) {
                    applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
                }
                applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{0, 1, 3, 4, 5, 7}));
                break;
            default:
                break;
        }

        applyLendDTO.getSorts().add("modifyTime=desc");
        applyLendDTO.setPage(dto.getPage() + 1);
        applyLendDTO.setLimit(dto.getLimit());
        list = super.findByPage(applyLendDTO);
        List<ApplyLendBO> listBO = BeanTransform.copyProperties(list, ApplyLendBO.class);
        if (listBO != null && listBO.size() > 0) {
            for (ApplyLendBO str : listBO) {
                String payCondition = str.getPayCondition();
                LendStatus lendStatus = str.getLendStatus();
                if ("否".equals(payCondition)) {
                    if (!lendStatus.equals(LendStatus.CHARGENOTPASS) && !lendStatus.equals(LendStatus.CHARGESURECONGEL)
                            && !lendStatus.equals(LendStatus.MANAGEPASS) && !lendStatus.equals(LendStatus.MANAGENOTPASS)
                            && !lendStatus.equals(LendStatus.LISTERROR) && !lendStatus.equals(LendStatus.FINACEPASS)) {
                        //待审核
                        str.setLendPhoneSelectStatus(LendPhoneSelectStatus.WAITAUDIT);
                    } else if (lendStatus.equals(LendStatus.FINACEPASS) || lendStatus.equals(LendStatus.MANAGEPASS)) {
                        //待付款
                        str.setLendPhoneSelectStatus(LendPhoneSelectStatus.WAITPAY);
                    } else if (!lendStatus.equals(LendStatus.NONE) && !lendStatus.equals(LendStatus.CHARGEPASS)
                            && !lendStatus.equals(LendStatus.FINACEPASS) && !lendStatus.equals(LendStatus.FINACENOTPASS)
                            && !lendStatus.equals(LendStatus.FINACECONGEL) && !lendStatus.equals(LendStatus.MANAGEPASS)) {
                        //待解冻
                        str.setLendPhoneSelectStatus(LendPhoneSelectStatus.WAITTHAW);
                    }
                } else if ("是".equals(payCondition)) {
                    LendRetunStatus lendRetunStatus = str.getLendRetunStatus();
                    if (!"是".equals(str.getReceivePay())) {
                        //已借款,付款后但未确认收款就是已借款
                        //TODO 已借款和待还款界定
                        str.setLendPhoneSelectStatus(LendPhoneSelectStatus.HASLEND);
                    } else if ("是".equals(str.getReceivePay()) && !lendRetunStatus.equals(LendRetunStatus.PASS)) {
                        //待还款(确认收款后的未经过还款核对和核对不通过)
                        str.setLendPhoneSelectStatus(LendPhoneSelectStatus.WAITRETURN);
                    } else if ("是".equals(str.getReceivePay()) && lendRetunStatus.equals(LendRetunStatus.PASS)) {
                        //已还款
                        str.setLendPhoneSelectStatus(LendPhoneSelectStatus.HASRETURN);
                    }
                }
            }
        }
        return listBO;
    }

    /**
     * 处理dto添加用户名和时间范围
     *
     * @param applyLendDTO
     * @param userName
     * @param startTime
     * @param endTime
     * @return
     */
    private ApplyLendDTO addQueryCon(ApplyLendDTO applyLendDTO, String userName, LocalDateTime startTime, LocalDateTime endTime) {
        applyLendDTO = new ApplyLendDTO();
        applyLendDTO.getConditions().add(Restrict.eq("lender", userName));
        applyLendDTO.getConditions().add(Restrict.between("createTime", new LocalDateTime[]{startTime, endTime}));
        return applyLendDTO;
    }

    /**
     * 处理dto添加待审核借款记录
     *
     * @param applyLendDTO
     * @return
     */
    private ApplyLendDTO addWaitAuditQueryCon(ApplyLendDTO applyLendDTO) {
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{2, 3, 6, 7, 8, 9}));
        return applyLendDTO;
    }

    /**
     * 处理dto添加待支付借款记录
     *
     * @param applyLendDTO
     * @return
     */
    private ApplyLendDTO addWaitPayQueryCon(ApplyLendDTO applyLendDTO) {
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "否"));
        applyLendDTO.getConditions().add(Restrict.in("lendStatus", new Integer[]{3, 7}));
        return applyLendDTO;
    }

    /**
     * 处理dto添加已支付借款记录
     * 已支付，但未还款
     *
     * @param applyLendDTO
     * @return
     */
    private ApplyLendDTO addHasPayQueryCon(ApplyLendDTO applyLendDTO) {
        applyLendDTO.getConditions().add(Restrict.eq("payCondition", "是"));
        //还款状态为未处理和审核不通过的情况
        applyLendDTO.getConditions().add(Restrict.in("lendRetunStatus", new Integer[]{0, 1, 3}));
        return applyLendDTO;
    }

    /**
     * 处理dto添加还款记录（已还款未还款和账务核对）
     * 这里的还款图的含义是填完还款数据还没进行还款核对和账务核对
     *
     * @param applyLendDTO
     * @return
     */
    private ApplyLendDTO addHasReturnQueryCon(ApplyLendDTO applyLendDTO) {
//        applyLendDTO.getConditions().add(Restrict.eq("lendRetunStatus", 2));
        applyLendDTO.getConditions().add(Restrict.eq("lendRetunStatus", 1));
        return applyLendDTO;
    }

    /**
     * 处理dto添加账务核对记录（已还款未还款和账务核对）
     * 这里的账务核对图的含义是已进行还款核对和账务核对
     * 已还款且还款核对通过（这里面账务核对一定是通过的）,(相当于action里面的已收票记录)
     *
     * @param applyLendDTO
     * @return
     */
    private ApplyLendDTO addHasCheckQueryCon(ApplyLendDTO applyLendDTO) {
        applyLendDTO.getConditions().add(Restrict.eq("lendRetunStatus", 2));
        return applyLendDTO;
    }

    /**
     * 饼型图制作
     *
     * @param seriesDataVOList
     * @return
     */
    private ReimShapeVO echartPieObject(List<ReimShapeSeriesDataVO> seriesDataVOList, ReimShapeTitleVO titleVO,String titleName ) {
        ReimShapeVO reimShapeVO = new ReimShapeVO();
        //echart饼状图形数据封装
        //title
        ReimShapeTitleVO reimShapeTitleVO = titleVO;
        titleVO.setX("center");
        reimShapeVO.setTitleVO(reimShapeTitleVO);
        //tootltip
        ReimShapeToolTipVO toolTipVO = new ReimShapeToolTipVO();
        toolTipVO.setTrigger("item");
        toolTipVO.setFormatter("{a} <br/>{b} : {c} ({d}%)");
        reimShapeVO.setToolTipVO(toolTipVO);
        //legend
        ReimShapeLegendVO legendVO = new ReimShapeLegendVO();
        legendVO.setOrient("vertical");
        legendVO.setLeft("left");
        legendVO.setData(Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对"));
        reimShapeVO.setRlegendVO(legendVO);
        //series
        ReimShapeSeriesVO seriesVO = new ReimShapeSeriesVO();
        seriesVO.setName(titleName);
        seriesVO.setType("pie");
        seriesVO.setRadius("20%");
        seriesVO.setSeriesDataVOList(seriesDataVOList);
        reimShapeVO.setSeriesVO(seriesVO);

        return reimShapeVO;
    }

    /**
     * 柱状图制作
     *
     * @param userName
     * @param start
     * @param end
     * @param seriesDataVOList
     * @return
     */
    private ReimShapeBarVO echartBarObject(String userName, LocalDateTime start, LocalDateTime end, List<Double> seriesDataVOList) {
        ReimShapeBarVO shapeBarVO = new ReimShapeBarVO();
        //echart饼状图形数据封装
        //title
        ReimShapeTitleVO titleVO = new ReimShapeTitleVO();
        titleVO.setText(userName + "借款指标金额统计");
        titleVO.setSubtext("时间: " + start + "至" + end);
        shapeBarVO.setReimShapeTitleVO(titleVO);
        //color
        shapeBarVO.setColorList(Arrays.asList("#3398DB"));
        //tootltip
        ReimShapeBarToolTipVO toolTipVO = new ReimShapeBarToolTipVO();
        toolTipVO.setTrigger("axis");
        toolTipVO.setAxisPointerBarVO(new AxisPointerBarVO("shadow"));
        shapeBarVO.setToolTipVO(toolTipVO);
        //grid
        ReimShapeBarGridVO gridVO = new ReimShapeBarGridVO();
        gridVO.setLeft("30%");
        gridVO.setRight("40%");
        gridVO.setBottom("70%");
        gridVO.setContainLabel(true);
        shapeBarVO.setBarGridVO(gridVO);
        //xAxis
        ReimShapeXaxisVO xAxisVO = new ReimShapeXaxisVO();
        xAxisVO.setName("状态");
        xAxisVO.setType("category");
        xAxisVO.setData(Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对"));
        xAxisVO.setAxisTickBarVO(new AxisTickBarVO(true));
        shapeBarVO.setXaxisVO(xAxisVO);
        //yAxis
        ReimShapeYaxisVO yAxisVO = new ReimShapeYaxisVO();
        yAxisVO.setName("金额");
        yAxisVO.setType("value");
        shapeBarVO.setXaxisVO(xAxisVO);
        //series
        ReimShapeBarSeriesVO seriesVO = new ReimShapeBarSeriesVO();
        seriesVO.setName("金额");
        seriesVO.setType("bar");
        seriesVO.setBarWidth("60%");
        seriesVO.setSeriesDataVOList(seriesDataVOList);
        shapeBarVO.setSeriesVO(seriesVO);

        shapeBarVO.setRlegendVO(new ReimShapeLegendVO(Arrays.asList("金额")));

        return shapeBarVO;
    }

    private ReimShapeAllVO caculate(ReimburseShapeConDTO reimburseShapeConDTO) throws SerException {
        ReimShapeAllVO allVO = new ReimShapeAllVO();
        //用来存饼型累计的数据
        ReimShapeVO reimShapeVO = new ReimShapeVO();
        List<ReimShapeSeriesDataVO> seriesDataVOList = new ArrayList<>();
        //用来存饼型金额的数据
        ReimShapeVO reimShape2VO = new ReimShapeVO();
        List<ReimShapeSeriesDataVO> seriesDataVO2List = new ArrayList<>();
        //用来存条形金额的数据
        ReimShapeBarVO reimShapeBarVO = new ReimShapeBarVO();
        List<Double> seriesDataVOBarList = new ArrayList<>();

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");
        String userName = reimburseShapeConDTO.getUserName();

        //计算某用户在这个时间段内的申报借款记录个数
        ReimShapeSeriesDataVO seriesDataVO = new ReimShapeSeriesDataVO();
        ReimShapeSeriesDataVO seriesData2VO = new ReimShapeSeriesDataVO();
        ApplyLendDTO applyLendDTO = new ApplyLendDTO();
        applyLendDTO = addQueryCon(applyLendDTO, userName, startTime, endTime);
        List<ApplyLend> listReim = super.findByCis(applyLendDTO);
        seriesDataVO.setName("申请借款记录");
        seriesData2VO.setName("申请借款记录");
        Double tempReimMoney = 0d;
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            tempReimMoney = listReim.stream().mapToDouble(ApplyLend::getMoney).sum();
            seriesData2VO.setValue(String.valueOf(tempReimMoney));
            seriesDataVOBarList.add(tempReimMoney == null || tempReimMoney.equals(0d) ? 0d : tempReimMoney);

        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);
        //计算某用户在这个时间段内的待审核借款记录个数(包括负责人未审核/申请冻结)
        applyLendDTO = addQueryCon(applyLendDTO, userName, startTime, endTime);
        applyLendDTO = addWaitAuditQueryCon(applyLendDTO);
        listReim = super.findByCis(applyLendDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("待审核");
        seriesData2VO.setName("待审核");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ApplyLend::getMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);


        //计算某用户在这个时间段内的待支付借款记录个数
        applyLendDTO = addQueryCon(applyLendDTO, userName, startTime, endTime);
        applyLendDTO = addWaitPayQueryCon(applyLendDTO);
        listReim = super.findByCis(applyLendDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("待支付");
        seriesData2VO.setName("待支付");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ApplyLend::getMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);
        //计算某用户在这个时间段内的已支付借款记录个数
        applyLendDTO = addQueryCon(applyLendDTO, userName, startTime, endTime);
        applyLendDTO = addHasPayQueryCon(applyLendDTO);
        listReim = super.findByCis(applyLendDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("已支付");
        seriesData2VO.setName("已支付");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ApplyLend::getMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);

        //计算某用户在这个时间段内的还款记录个数
        applyLendDTO = addQueryCon(applyLendDTO, userName, startTime, endTime);
        applyLendDTO = addHasReturnQueryCon(applyLendDTO);
        listReim = super.findByCis(applyLendDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("还款记录");
        seriesData2VO.setName("还款记录");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ApplyLend::getMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);

        //TODO 账务核对数据()
        applyLendDTO = addQueryCon(applyLendDTO, userName, startTime, endTime);
        applyLendDTO = addHasCheckQueryCon(applyLendDTO);
        listReim = super.findByCis(applyLendDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("账务核对记录");
        seriesData2VO.setName("账务核对记录");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ApplyLend::getMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);

        //echart饼状图形数据封装
        ReimShapeTitleVO titlePieVO = new ReimShapeTitleVO();
        titlePieVO.setText(reimburseShapeConDTO.getUserName() + "借款情况统计");
        titlePieVO.setSubtext("时间: " + startTime + "至" + endTime + "累计记录");
        reimShapeVO = echartPieObject(seriesDataVOList, titlePieVO,"单数");
        //echart饼状图形数据封装
        ReimShapeTitleVO titlePie2VO = new ReimShapeTitleVO();
        titlePie2VO.setText(reimburseShapeConDTO.getUserName() + "借款金额指标统计");
        titlePie2VO.setSubtext("时间: " + startTime + "至" + endTime + "借款金额");
        reimShape2VO = echartPieObject(seriesDataVO2List, titlePie2VO,"金额");
        //echart柱状图形数据封装
        reimShapeBarVO = echartBarObject(reimburseShapeConDTO.getUserName(), startTime, endTime, seriesDataVOBarList);

        allVO.setReimConIndexPercent(reimShapeVO);
        allVO.setReimMoneyIndexPercent(reimShape2VO);
        allVO.setReimShapeBarVO(reimShapeBarVO);
        return allVO;
    }

    @Override
    public ReimShapeAllVO collectSelfShape(ReimburseShapeDTO reimburseShapeDTO) throws SerException {
        ReimShapeAllVO reimShapeAllVO = new ReimShapeAllVO();

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimburseShapeDTO, ReimburseShapeConDTO.class, "serialVersionUID");
        if (null == reimburseShapeDTO.getReimburseShapeStatus()) {
            throw new SerException("汇总状态类型不能为空");
        }
        //根据选择的日周月年和用户名
        if (StringUtils.isBlank(reimburseShapeDTO.getUserName())) {
            throw new SerException("用户名不能为空");
        }
        switch (reimburseShapeDTO.getReimburseShapeStatus()) {
            case YEAR:
                if (StringUtils.isBlank("" + reimburseShapeDTO.getYear()) || reimburseShapeDTO.getYear() < 1900) {
                    throw new SerException("年份(year)不能为空且大于1900");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.parseDate(reimburseShapeDTO.getYear() + "-01-01"));
                reimburseShapeConDTO.setEndTime(DateUtil.parseDate(reimburseShapeDTO.getYear() + "-12-31"));

                break;
            case QUART:
                if (StringUtils.isBlank("" + reimburseShapeDTO.getYear()) || reimburseShapeDTO.getQuart() > 4 || reimburseShapeDTO.getQuart() < 1) {
                    throw new SerException("年份(year)或季度（quart）不能为空,且year大于1900,quart在1～4之间");
                }
                LocalDate timeBegan = LocalDate.now();
                LocalDate timeEnd = LocalDate.now();
                switch (reimburseShapeDTO.getQuart()) {
                    case 1:
                        timeBegan = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-01-01");
                        timeEnd = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-03-31");
                        break;
                    case 2:
                        timeBegan = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-04-01");
                        timeEnd = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-06-30");
                        break;
                    case 3:
                        timeBegan = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-07-01");
                        timeEnd = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-09-31");
                        break;
                    case 4:
                        timeBegan = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-10-01");
                        timeEnd = DateUtil.parseDate(reimburseShapeDTO.getYear() + "-12-31");
                        break;
                    default:
                        break;
                }
                reimburseShapeConDTO.setStartTime(timeBegan);
                reimburseShapeConDTO.setEndTime(timeEnd);

                break;
            case MONTH:
                if (StringUtils.isBlank("" + reimburseShapeDTO.getYear()) || StringUtils.isBlank("" + reimburseShapeDTO.getMonth()) || reimburseShapeDTO.getYear() < 1900 || reimburseShapeDTO.getMonth() < 1 || reimburseShapeDTO.getMonth() > 12) {
                    throw new SerException("年份(year)或月份(month)不能为空,年大于1900,月份在1-12之间");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.getStartDayOfMonth(reimburseShapeDTO.getYear(), reimburseShapeDTO.getMonth()));
                reimburseShapeConDTO.setEndTime(DateUtil.getEndDaYOfMonth(reimburseShapeDTO.getYear(), reimburseShapeDTO.getMonth()));
                break;
            case WEEK:
                if (StringUtils.isBlank("" + reimburseShapeDTO.getYear()) || StringUtils.isBlank("" + reimburseShapeDTO.getMonth()) || StringUtils.isBlank("" + reimburseShapeDTO.getWeek())) {
                    throw new SerException("年份或月份或周数不能为空");
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes(reimburseShapeDTO.getYear(), reimburseShapeDTO.getMonth(), reimburseShapeDTO.getWeek());
                reimburseShapeConDTO.setStartTime(dateDuring[0]);
                reimburseShapeConDTO.setEndTime(dateDuring[1]);
                break;
            case DAY:
                if (StringUtils.isBlank(reimburseShapeDTO.getTime())) {
                    throw new SerException("日期不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.parseDate(reimburseShapeDTO.getTime()));
                reimburseShapeConDTO.setEndTime(DateUtil.parseDate(reimburseShapeDTO.getTime()));
                break;
            default:
                return null;
        }
        if (null == reimburseShapeConDTO.getStartTime() || null == reimburseShapeConDTO.getEndTime()) {
            reimShapeAllVO = null;
        } else {
            reimShapeAllVO = caculate(reimburseShapeConDTO);
        }

        return reimShapeAllVO;
    }

    @Override
    public ReimShapeMixVO collectSelfTrend(ReimburseTrendShapeDTO reimburseTrendShapeDTO) throws SerException {
        ReimShapeMixVO reimShapeMixVO = new ReimShapeMixVO();
        List<ReimShapeMixSeriesVO> mixSeriesVOList = new ArrayList<>(1);


        if (StringUtils.isBlank(reimburseTrendShapeDTO.getUserName())) {
            throw new SerException("用户名不能为空");
        }
        if (StringUtils.isBlank(reimburseTrendShapeDTO.getStartTime())) {
            throw new SerException("开始时间不能为空,格式为2017-09");
        }
        if (StringUtils.isBlank(reimburseTrendShapeDTO.getEndTime())) {
            throw new SerException("结束时间不能为空,格式为2017-09");
        }

        //用户
        String userName = reimburseTrendShapeDTO.getUserName();
        //处理第一个时间
        LocalDateTime startMonStart = DateUtil.parseDateTime(reimburseTrendShapeDTO.getStartTime() + "-01 00:00:00");
        LocalDateTime startMonEnd = startMonStart.with(TemporalAdjusters.lastDayOfMonth());
        //处理第二个时间
        LocalDateTime endMonStart = DateUtil.parseDateTime(reimburseTrendShapeDTO.getEndTime() + "-01 00:00:00");
        LocalDateTime endMonEnd = endMonStart.with(TemporalAdjusters.lastDayOfMonth());
        Double reimMoney = 0d;
        ReimShapeMixSeriesVO mixSeriesVO = new ReimShapeMixSeriesVO();
        List<Double> seriesDataList = new ArrayList<>();

        ApplyLendDTO dto = new ApplyLendDTO();
        //查询第一个时间所有申报借款记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        List<ApplyLend> list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第一个时间所有待审核借款记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addWaitAuditQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第一个时间所有待支付借款记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addWaitPayQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);

        //查询第一个时间所有已支付借款记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addHasPayQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);

        //查询第一个时间所有已支还款记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addHasReturnQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);

        //账务核对
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addHasCheckQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);

        //封装返回数据
        mixSeriesVO.setName(reimburseTrendShapeDTO.getStartTime());
        mixSeriesVO.setType("bar");
        mixSeriesVO.setBarWidth("40%");
        mixSeriesVO.setSeriesDataVOList(seriesDataList);
        mixSeriesVOList.add(mixSeriesVO);

        //处理第二个数据
        seriesDataList = new ArrayList<>();
        ApplyLendDTO dto2 = new ApplyLendDTO();
        //查询第二个时间所有申报借款记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        List<ApplyLend> list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有待审核借款记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addWaitAuditQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有待支付借款记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addWaitPayQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有已支付借款记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addHasPayQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);

        //查询第二个时间所有已还款记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addHasReturnQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);

        //账务核对
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addHasCheckQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ApplyLend::getMoney).sum();
        seriesDataList.add(reimMoney);

        //封装返回数据
        mixSeriesVO = new ReimShapeMixSeriesVO();
        mixSeriesVO.setName(reimburseTrendShapeDTO.getEndTime());
        mixSeriesVO.setType("line");
        mixSeriesVO.setSeriesDataVOList(seriesDataList);
        mixSeriesVOList.add(mixSeriesVO);

        ReimShapeXaxisVO xaxisVO = new ReimShapeXaxisVO();
        xaxisVO.setName("状态");
        xaxisVO.setType("category");
        xaxisVO.setData(Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对"));
        xaxisVO.setAxisTickBarVO(new AxisTickBarVO(true));
        xaxisVO.setAxisPointerBarVO(new AxisPointerBarVO("shadow"));
        reimShapeMixVO.setXaxisVO(xaxisVO);
        reimShapeMixVO.setTitleVO(new ReimShapeTitleVO(userName + "的借款情况变化趋势图", reimburseTrendShapeDTO.getStartTime() + "至" + reimburseTrendShapeDTO.getEndTime(), "center", "45%"));
        reimShapeMixVO.setToolTipVO(new ReimShapeMixToolTipVO("axis", new AxisPointerBarVO("cross")));
        reimShapeMixVO.setGridVO(new ReimShapeBarGridVO("20%", "30%", "60%", true));
        reimShapeMixVO.setRlegendVO(new ReimShapeLegendVO("x", "center", Arrays.asList(reimburseTrendShapeDTO.getStartTime(), reimburseTrendShapeDTO.getEndTime())));
        reimShapeMixVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)", 50, new AxisLabelVO("{value}")));
        reimShapeMixVO.setSeriesVO(mixSeriesVOList);
        return reimShapeMixVO;
    }

    @Override
    public ReimCompanyMixShapeVO collectGroupBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        ReimCompanyMixShapeVO mixShapeVO = new ReimCompanyMixShapeVO();
        //柱状图数据
        ReimCompanyShapeBarVO reimCompanyShapeBarVO = new ReimCompanyShapeBarVO();
        //饼状图数据
        ReimShapeVO shapeVO = new ReimShapeVO();

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimCompanyShapeDTO, ReimburseShapeConDTO.class, "serialVersionUID");
        reimburseShapeConDTO.setReimburseShapeStatus(reimCompanyShapeDTO.getShapeStatus());
        if (null == reimCompanyShapeDTO.getShapeStatus()) {
            throw new SerException("汇总类型不能为空");
        }
        //根据选择的日周月年枚举转成时间
        reimburseShapeConDTO = enumTOTime(reimCompanyShapeDTO, reimburseShapeConDTO);
        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");

        //查询公司所有的项目组
        List<String> groupList = positionDetailUserAPI.getAllDepartment();
        //查询所有项目组的申报借款记录
        String[] fields = new String[]{"money", "projectGroup"};
        String sql = "select sum(money) as money , projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "' ";
        String sqlGroup = "  group by projectGroup ";
        String sqlQuery = sql + sqlGroup;
        List<ApplyLend> listAll = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的待审核
        sqlQuery = sql + " and payCondition = '否' and lendStatus not in (2, 3, 6, 7, 8, 9)  " + sqlGroup;
        List<ApplyLend> listWaitAudit = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的待支付
        sqlQuery = sql + " and payCondition = '否' and lendStatus in ( 3, 7 ) " + sqlGroup;
        List<ApplyLend> listWaitPay = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的已支付
        sqlQuery = sql + " and payCondition = '是' and lendStatus in (0, 1, 3 )   " + sqlGroup;
        List<ApplyLend> listHasPay = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的已还款
        sqlQuery = sql + " and lendRetunStatus = 1   " + sqlGroup;
        List<ApplyLend> listHasReturn = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //账务核对
        sqlQuery = sql + " and lendRetunStatus = 2   " + sqlGroup;
        List<ApplyLend> listHasCheck = super.findBySql(sqlQuery, ApplyLend.class, fields);

        //柱状图型数据封装
        List<ReimShapeBarSeriesVO> seriesDataList = TransformBarData(groupList, listAll, listWaitAudit, listWaitPay, listHasPay, listHasReturn, listHasCheck);
        reimCompanyShapeBarVO.setReimShapeTitleVO(new ReimShapeTitleVO("公司内部项目组特定指标统计表", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
        reimCompanyShapeBarVO.setToolTipVO(new ReimShapeBarToolTipVO("axis"));
        reimCompanyShapeBarVO.setLegendVO(new ReimShapeLegendVO(groupList));
        reimCompanyShapeBarVO.setBarGridVO(new ReimShapeBarGridVO("22%", "22%", "44%", true));
        reimCompanyShapeBarVO.setXaxisVO(new ReimShapeXaxisVO("category", "状态", Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对记录")));
        reimCompanyShapeBarVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)"));
        reimCompanyShapeBarVO.setSeriesVOList(seriesDataList);

        //饼型图形数据封装
        shapeVO.setTitleVO(new ReimShapeTitleVO("公司特定指标统计表", "所有项目组" + reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime(), "center", "20%"));
        shapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b} : {c} ({d}%)"));
        shapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "left", Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对记录")));
        ReimShapeSeriesVO seriesVO = new ReimShapeSeriesVO();
        seriesVO.setName("金额");
        seriesVO.setType("pie");
        seriesVO.setRadius("55%");
        seriesVO.setSeriesDataVOList(TransformPieData(seriesDataList));
        shapeVO.setSeriesVO(seriesVO);
        mixShapeVO.setReimShapeVO(shapeVO);
        mixShapeVO.setCompanyShapeBarVO(reimCompanyShapeBarVO);
        return mixShapeVO;
    }

    private ReimburseShapeConDTO enumTOTime(ReimCompanyShapeDTO reimCompanyShapeDTO, ReimburseShapeConDTO reimburseShapeConDTO) throws SerException {
        switch (reimCompanyShapeDTO.getShapeStatus()) {
            case YEAR:
                if (StringUtils.isBlank("" + reimCompanyShapeDTO.getYear())) {
                    throw new SerException("年份不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.parseDate(reimCompanyShapeDTO.getYear() + "-01-01"));
                reimburseShapeConDTO.setEndTime(DateUtil.parseDate(reimCompanyShapeDTO.getYear() + "-12-31"));

                break;
            case MONTH:
                if (StringUtils.isBlank("" + reimCompanyShapeDTO.getYear()) || StringUtils.isBlank("" + reimCompanyShapeDTO.getMonth())) {
                    throw new SerException("年份或月份不能为空");
                }
                reimburseShapeConDTO.setStartTime(DateUtil.getStartDayOfMonth(reimCompanyShapeDTO.getYear(), reimCompanyShapeDTO.getMonth()));
                reimburseShapeConDTO.setEndTime(DateUtil.getEndDaYOfMonth(reimCompanyShapeDTO.getYear(), reimCompanyShapeDTO.getMonth()));
                break;
            case WEEK:
                if (StringUtils.isBlank("" + reimCompanyShapeDTO.getYear()) || StringUtils.isBlank("" + reimCompanyShapeDTO.getMonth()) || StringUtils.isBlank("" + reimCompanyShapeDTO.getWeek())) {
                    throw new SerException("年份或月份或周数不能为空");
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes(reimCompanyShapeDTO.getYear(), reimCompanyShapeDTO.getMonth(), reimCompanyShapeDTO.getWeek());
                reimburseShapeConDTO.setStartTime(dateDuring[0]);
                reimburseShapeConDTO.setEndTime(dateDuring[1]);
                break;
            default:
                return null;
        }
        return reimburseShapeConDTO;
    }

    @Override
    public ReimCompanyMixShapeVO collectProjectBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        ReimCompanyMixShapeVO mixShapeVO = new ReimCompanyMixShapeVO();
        //柱状图数据
        ReimCompanyShapeBarVO reimCompanyShapeBarVO = new ReimCompanyShapeBarVO();
        //饼状图数据
        ReimShapeVO shapeVO = new ReimShapeVO();

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimCompanyShapeDTO, ReimburseShapeConDTO.class, "serialVersionUID");
        reimburseShapeConDTO.setReimburseShapeStatus(reimCompanyShapeDTO.getShapeStatus());
        if (null == reimCompanyShapeDTO.getShapeStatus()) {
            throw new SerException("汇总类型不能为空");
        }
        //根据选择的日周月年枚举转成时间
        reimburseShapeConDTO = enumTOTime(reimCompanyShapeDTO, reimburseShapeConDTO);

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");

        //查询公司所有的项目名称
        List<String> groupList = departmentDetailAPI.findAllProject();
        //查询所有项目组的申报借款记录
        String[] fields = new String[]{"money", "projectGroup"};
        String sql = "select sum(money) as money , projectName as projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "' ";
        String sqlGroup = "  group by projectName ";
        String sqlQuery = sql + sqlGroup;
        List<ApplyLend> listAll = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的待审核
        sqlQuery = sql + " and payCondition = '否' and lendStatus not in (2, 3, 6, 7, 8, 9)  " + sqlGroup;
        List<ApplyLend> listWaitAudit = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的待支付
        sqlQuery = sql + " and payCondition = '否' and lendStatus in ( 3, 7 ) " + sqlGroup;
        List<ApplyLend> listWaitPay = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的已支付
        sqlQuery = sql + " and payCondition = '是' and lendStatus in (0, 1, 3 )   " + sqlGroup;
        List<ApplyLend> listHasPay = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的已还款
        sqlQuery = sql + " and lendRetunStatus = 1   " + sqlGroup;
        List<ApplyLend> listHasReturn = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //账务核对
        sqlQuery = sql + " and lendRetunStatus = 2   " + sqlGroup;
        List<ApplyLend> listHasCheck = super.findBySql(sqlQuery, ApplyLend.class, fields);

        //柱状图型数据封装
        List<ReimShapeBarSeriesVO> seriesDataList = TransformBarData(groupList, listAll, listWaitAudit, listWaitPay, listHasPay, listHasReturn, listHasCheck);
        reimCompanyShapeBarVO.setReimShapeTitleVO(new ReimShapeTitleVO("公司内部项目名称特定指标统计表", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
        reimCompanyShapeBarVO.setToolTipVO(new ReimShapeBarToolTipVO("axis"));
        reimCompanyShapeBarVO.setLegendVO(new ReimShapeLegendVO(groupList));
        reimCompanyShapeBarVO.setBarGridVO(new ReimShapeBarGridVO("22%", "22%", "44%", true));
        reimCompanyShapeBarVO.setXaxisVO(new ReimShapeXaxisVO("category", "状态", Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对记录")));
        reimCompanyShapeBarVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)"));
        reimCompanyShapeBarVO.setSeriesVOList(seriesDataList);

        //饼型图形数据封装
        shapeVO.setTitleVO(new ReimShapeTitleVO("公司特定指标统计表", "所有项目名称" + reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime(), "center", "20%"));
        shapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b} : {c} ({d}%)"));
        shapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "left", Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对记录")));
        ReimShapeSeriesVO seriesVO = new ReimShapeSeriesVO();
        seriesVO.setName("金额");
        seriesVO.setType("pie");
        seriesVO.setRadius("55%");
        seriesVO.setSeriesDataVOList(TransformPieData(seriesDataList));
        shapeVO.setSeriesVO(seriesVO);
        mixShapeVO.setReimShapeVO(shapeVO);
        mixShapeVO.setCompanyShapeBarVO(reimCompanyShapeBarVO);
        return mixShapeVO;
    }

    @Override
    public ReimCompanyMixShapeVO collectAreaBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        ReimCompanyMixShapeVO mixShapeVO = new ReimCompanyMixShapeVO();
        //柱状图数据
        ReimCompanyShapeBarVO reimCompanyShapeBarVO = new ReimCompanyShapeBarVO();
        //饼状图数据
        ReimShapeVO shapeVO = new ReimShapeVO();

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimCompanyShapeDTO, ReimburseShapeConDTO.class, "serialVersionUID");
        reimburseShapeConDTO.setReimburseShapeStatus(reimCompanyShapeDTO.getShapeStatus());
        if (null == reimCompanyShapeDTO.getShapeStatus()) {
            throw new SerException("汇总类型不能为空");
        }
        //根据选择的日周月年枚举转成时间
        reimburseShapeConDTO = enumTOTime(reimCompanyShapeDTO, reimburseShapeConDTO);

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");

        //查询公司所有的项目组
        List<AreaBO> areaBOList = departmentDetailAPI.findArea();
        List<String> groupList = (areaBOList != null && areaBOList.size() > 0) ? areaBOList.stream().map(AreaBO::getArea).collect(Collectors.toList()) : new ArrayList<>();
        //查询所有项目组的申报借款记录
        String[] fields = new String[]{"money", "projectGroup"};
        String sql = "select sum(money) as money , area as projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "' ";
        String sqlGroup = "  group by area ";
        String sqlQuery = sql + sqlGroup;
        List<ApplyLend> listAll = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的待审核
        sqlQuery = sql + " and payCondition = '否' and lendStatus not in (2, 3, 6, 7, 8, 9)  " + sqlGroup;
        List<ApplyLend> listWaitAudit = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的待支付
        sqlQuery = sql + " and payCondition = '否' and lendStatus in ( 3, 7 ) " + sqlGroup;
        List<ApplyLend> listWaitPay = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的已支付
        sqlQuery = sql + " and payCondition = '是' and lendStatus in (0, 1, 3 )   " + sqlGroup;
        List<ApplyLend> listHasPay = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //查询所有项目组的已还款
        sqlQuery = sql + " and lendRetunStatus = 1   " + sqlGroup;
        List<ApplyLend> listHasReturn = super.findBySql(sqlQuery, ApplyLend.class, fields);
        //账务核对
        sqlQuery = sql + " and lendRetunStatus = 2   " + sqlGroup;
        List<ApplyLend> listHasCheck = super.findBySql(sqlQuery, ApplyLend.class, fields);

        //柱状图型数据封装
        List<ReimShapeBarSeriesVO> seriesDataList = TransformBarData(groupList, listAll, listWaitAudit, listWaitPay, listHasPay, listHasReturn, listHasCheck);
        reimCompanyShapeBarVO.setReimShapeTitleVO(new ReimShapeTitleVO("公司内部地区特定指标统计表", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
        reimCompanyShapeBarVO.setToolTipVO(new ReimShapeBarToolTipVO("axis"));
        reimCompanyShapeBarVO.setLegendVO(new ReimShapeLegendVO(groupList));
        reimCompanyShapeBarVO.setBarGridVO(new ReimShapeBarGridVO("22%", "22%", "44%", true));
        reimCompanyShapeBarVO.setXaxisVO(new ReimShapeXaxisVO("category", "状态", Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对记录")));
        reimCompanyShapeBarVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)"));
        reimCompanyShapeBarVO.setSeriesVOList(seriesDataList);

        //饼型图形数据封装
        shapeVO.setTitleVO(new ReimShapeTitleVO("公司特定指标统计表", "所有地区" + reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime(), "center", "20%"));
        shapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b} : {c} ({d}%)"));
        shapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "left", Arrays.asList("申请借款", "待审核", "待支付", "已支付", "还款记录", "账务核对记录")));
        ReimShapeSeriesVO seriesVO = new ReimShapeSeriesVO();
        seriesVO.setName("金额");
        seriesVO.setType("pie");
        seriesVO.setRadius("55%");
        seriesVO.setSeriesDataVOList(TransformPieData(seriesDataList));
        shapeVO.setSeriesVO(seriesVO);
        mixShapeVO.setReimShapeVO(shapeVO);
        mixShapeVO.setCompanyShapeBarVO(reimCompanyShapeBarVO);
        return mixShapeVO;
    }

    private List<ReimShapeSeriesDataVO> TransformPieData(List<ReimShapeBarSeriesVO> seriesDataList) {
        Double val1 = 0d;
        Double val2 = 0d;
        Double val3 = 0d;
        Double val4 = 0d;
        Double val5 = 0d;
        Double val6 = 0d;
        List<ReimShapeSeriesDataVO> seriesPieDataVOList = new ArrayList<>();
        for (ReimShapeBarSeriesVO piedata : seriesDataList) {

            val1 = val1 + piedata.getSeriesDataVOList().get(0);
            val2 = val2 + piedata.getSeriesDataVOList().get(1);
            val3 = val3 + piedata.getSeriesDataVOList().get(2);
            val4 = val4 + piedata.getSeriesDataVOList().get(3);
            val5 = val5 + piedata.getSeriesDataVOList().get(4);
            val6 = val6 + piedata.getSeriesDataVOList().get(5);
        }
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("申请借款", String.valueOf(new BigDecimal(val1).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("待审核", String.valueOf(new BigDecimal(val2).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("待支付", String.valueOf(new BigDecimal(val3).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("已支付", String.valueOf(new BigDecimal(val4).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("还款记录", String.valueOf(new BigDecimal(val5).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("账务核对记录", String.valueOf(new BigDecimal(val6).setScale(5, BigDecimal.ROUND_UP).doubleValue())));

        return seriesPieDataVOList;

    }

    private List<ReimShapeBarSeriesVO> TransformBarData(List<String> groupList, List<ApplyLend> listAll,
                                                        List<ApplyLend> listWaitAudit, List<ApplyLend> listWaitPay,
                                                        List<ApplyLend> listHasPay, List<ApplyLend> listHasReturn,
                                                        List<ApplyLend> listHasCheck) {
        List<ReimShapeBarSeriesVO> seriesDataList = new ArrayList<>();
        for (String tempArea : groupList) {
            ReimShapeBarSeriesVO seriesVO = new ReimShapeBarSeriesVO();
            List<Double> seriesDataVOListArea = new ArrayList<>();
            List<Double> reimMoney = listAll.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ApplyLend::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listWaitAudit.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ApplyLend::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listWaitPay.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ApplyLend::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listHasPay.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ApplyLend::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listHasReturn.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ApplyLend::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listHasCheck.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ApplyLend::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));

            seriesVO.setName(tempArea);
            seriesVO.setSeriesDataVOList(seriesDataVOListArea);
            seriesDataList.add(seriesVO);
        }
        return seriesDataList;
    }

    @Override
    public ReimShapeVO collectAreaDetailBar(LendShapeDetailDTO reimburseShapeDetailDTO) throws SerException {
        String titleStr = "";
        List<ReimShapeSeriesDataVO> dataVOS = new ArrayList<>();

        if (null == reimburseShapeDetailDTO.getDetailStatus()) {
            throw new SerException("详细汇总的标识不能为空哦");
        }
        if (null == reimburseShapeDetailDTO.getShapeStatus()) {
            throw new SerException("汇总类型（年月周）不能为空");
        }
        if (null == reimburseShapeDetailDTO.getDetailTypeStatus()) {
            throw new SerException("汇总数据（项目组/项目名称/地区）不能为空");
        }

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimburseShapeDetailDTO, ReimburseShapeConDTO.class, "serialVersionUID");
        reimburseShapeConDTO.setReimburseShapeStatus(reimburseShapeDetailDTO.getShapeStatus());

        //根据选择的日周月年枚举转成时间
        ReimCompanyShapeDTO reimCompanyShapeDTO = BeanTransform.copyProperties(reimburseShapeDetailDTO, ReimCompanyShapeDTO.class, "serialVersionUID");
        reimburseShapeConDTO = enumTOTime(reimCompanyShapeDTO, reimburseShapeConDTO);

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");

        //查询公司所有的地区
        List<AreaBO> areaBOList = departmentDetailAPI.findArea();
        if (areaBOList == null || areaBOList.size() < 0) {
            return new ReimShapeVO();
        }
        LendShapeDetailStatus detailStatus = reimburseShapeDetailDTO.getDetailStatus();
        List<String> groupList = (areaBOList != null && areaBOList.size() > 0) ? areaBOList.stream().map(AreaBO::getArea).collect(Collectors.toList()) : new ArrayList<>();
        //查询所有项目组的申报借款记录
        String[] fields = new String[]{"money", "projectGroup"};
        String sql = "";
        String sqlWhere = " ";
        String sqlGroup = " ";
        switch (reimburseShapeDetailDTO.getDetailTypeStatus()) {
            case AREA:
                sql = "select sum(money) as money , area as projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "' ";
                sqlGroup = "  group by area ";
                titleStr = "各地区的";
                break;
            case GROUP:
                sql = "select sum(money) as money , projectGroup as projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "' ";
                sqlGroup = "  group by projectGroup ";
                titleStr = "各项目组的";
                break;
            case PROJECT:
                sql = "select sum(money) as money , projectName as projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "' ";
                sqlGroup = "  group by projectName ";
                titleStr = "各项目名称的";
                break;
            default:
                return null;
        }

        //数据库查询处理数据
        dataVOS = companyDetailPieData(reimburseShapeDetailDTO, groupList, fields, sql, sqlGroup, sqlWhere);
        //封装饼型图
        ReimShapeVO reimShapeVO = new ReimShapeVO();
        reimShapeVO.setTitleVO(new ReimShapeTitleVO(titleStr + LendShapeDetailStatus.nameExplain(detailStatus.getCode()) + "统计", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
        reimShapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b} : {c} ({d}%)"));
        reimShapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "left", dataVOS.stream().map(ReimShapeSeriesDataVO::getName).collect(Collectors.toList())));

        ReimShapeSeriesVO reimShapeSeriesVO = new ReimShapeSeriesVO();
        reimShapeSeriesVO.setName("金额");
        reimShapeSeriesVO.setType("pie");
        reimShapeSeriesVO.setSeriesDataVOList(dataVOS);
        reimShapeVO.setSeriesVO(reimShapeSeriesVO);
        return reimShapeVO;
    }


    /**
     * 公司借款饼型图形的详细饼图
     *
     * @param reimburseShapeDetailDTO
     * @param groupList
     * @param fields
     * @param sql
     * @param sqlGroup
     * @param sqlWhere
     * @return
     * @throws SerException
     */
    private List<ReimShapeSeriesDataVO> companyDetailPieData(LendShapeDetailDTO reimburseShapeDetailDTO, List<String> groupList, String[] fields, String sql, String sqlGroup, String sqlWhere) throws SerException {
        List<ReimShapeSeriesDataVO> dataVOS = new ArrayList<>();
        LendShapeDetailStatus detailStatus = reimburseShapeDetailDTO.getDetailStatus();
        switch (detailStatus) {
            case ALL:
                break;
            case WAITAUIT:
                sqlWhere = "  and payCondition = '否' and lendStatus not in (2, 3, 6, 7, 8, 9)  ";
                break;
            case WAITPAY:
                sqlWhere = " and payCondition = '否' and lendStatus in ( 3, 7 ) ";
                break;
            case HASPAY:
                sqlWhere = " and payCondition = '是' and lendStatus in (0, 1, 3 )  ";
                break;
            case HASRETURN:
                sqlWhere = " and lendRetunStatus = 1  ";
                break;
            case CHECK:
                //TODO 账务核对
                sqlWhere = " and lendRetunStatus = 2 ";
                break;
            default:
                break;

        }
        String sqlQuery = sql + sqlWhere + sqlGroup;

        List<String> groupNameList = new ArrayList<>();
        List<ApplyLend> listResult = new ArrayList<>();
        List<String> distinctGroupNameList = new ArrayList<>();

        List<ApplyLend> list = super.findBySql(sqlQuery, ApplyLend.class, fields);
        if (list != null && list.size() > 0) {
            listResult.addAll(list);
            //把没有申请借款的项目组的金额设置为0
            groupNameList = list.stream().map(ApplyLend::getProjectGroup).collect(Collectors.toList());
            List<String> finalGroupNameList = groupNameList;
            if (groupNameList != null && groupNameList.size() > 0) {
                distinctGroupNameList = groupList.stream().filter(str -> finalGroupNameList.contains(str)).collect(Collectors.toList());
            }
        } else {
            distinctGroupNameList = groupList;
        }
        distinctGroupNameList.forEach(str -> {
            ApplyLend temp = new ApplyLend(str, 0d);
            listResult.add(temp);
        });
        listResult.stream().sorted(new Comparator<ApplyLend>() {
            @Override
            public int compare(ApplyLend o1, ApplyLend o2) {
                return o1.getProjectGroup().compareTo(o2.getProjectGroup());
            }
        });
        listResult.stream().forEach(str -> {
            ReimShapeSeriesDataVO seriesDataVO = new ReimShapeSeriesDataVO();
            seriesDataVO.setName(str.getProjectGroup());
            seriesDataVO.setValue("" + str.getMoney());
            dataVOS.add(seriesDataVO);
        });

        return dataVOS;

    }

    @Override
    public LendMixReimShapeVO collectMixMonAndWeek(LendMixReimSelfShapeDTO lendMixReimShapeDTO) throws SerException {
        LendMixReimShapeVO result = new LendMixReimShapeVO();

        ReimCompanyShapeDTO reimCompanyShapeDTO = BeanTransform.copyProperties(lendMixReimShapeDTO, ReimCompanyShapeDTO.class, "serialVersionUID");
        reimCompanyShapeDTO.setShapeStatus(lendMixReimShapeDTO.getReimburseShapeStatus());

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(lendMixReimShapeDTO, ReimburseShapeConDTO.class, "serialVersionUID");
        reimburseShapeConDTO.setReimburseShapeStatus(lendMixReimShapeDTO.getReimburseShapeStatus());
        if (null == lendMixReimShapeDTO.getReimburseShapeStatus()) {
            throw new SerException("汇总状态reimburseShapeStatus不能为空");
        }
        if (null == lendMixReimShapeDTO.getTypeStatus()) {
            throw new SerException("汇总类型typeStatus不能为空");
        }
        String reimTypeFlag = "";
        String lendTypeFlag = "";
        ReimShapeDetailTypeStatus typeStatus = lendMixReimShapeDTO.getTypeStatus();
        switch (typeStatus) {
            case PROJECT:
                reimTypeFlag = "project";
                lendTypeFlag = "projectName";
                break;
            case GROUP:
                reimTypeFlag = "projectGroup";
                lendTypeFlag = "projectGroup";
                break;
            case AREA:
                reimTypeFlag = "area";
                lendTypeFlag = "area";
                break;
            case REIMER:
                reimTypeFlag = "reimer";
                lendTypeFlag = "lender";
                break;
        }


        if (StringUtils.isBlank(lendMixReimShapeDTO.getUserName())) {
            throw new SerException("地区/项目名称/项目组/报销人/借款人不能为空");
        }
        //根据选择的日周月年枚举转成时间
        reimburseShapeConDTO = enumTOTime(reimCompanyShapeDTO, reimburseShapeConDTO);

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");
        String userName = lendMixReimShapeDTO.getUserName();
        List<Double> moneyList = new ArrayList<>();

        String[] reimFields = new String[]{"money", "username"};
        String reimSql = " select if(sum(reimMoney) IS NULL , 0.00 , sum(reimMoney) ) as money ,  if(" + reimTypeFlag + " IS NULL , '" + userName + "' , sum(" + reimTypeFlag + ") ) as username from lendreimbursement_reimburserecord where 1=1 ";
        reimSql = reimSql + " and createTime between '" + startTime + "' and '" + endTime + "'  ";
        String lendSql = " select if(sum(money) IS NULL , 0.00 , sum(money) ) as money ,  if(" + lendTypeFlag + " IS NULL , '" + userName + "' , sum(" + lendTypeFlag + ") ) as username from lendreimbursement_applylend where 1=1 ";
        lendSql = lendSql + " and createTime between '" + startTime + "' and '" + endTime + "'  ";
        //查询某个人当前时间申请报销的记录
        reimSql = reimSql + " and " + reimTypeFlag + " = '" + userName + "' ";
        String reimGroupBy = " group by " + reimTypeFlag + "";
        String lendGroupBy = " group by " + lendTypeFlag + "";
        List<LendMixReimCollectDataVO> queryData = reimburseRecordSer.findBySql(reimSql+reimGroupBy, LendMixReimCollectDataVO.class, reimFields);
        moneyList.add(queryData != null && queryData.size() > 0 ? queryData.stream().mapToDouble(LendMixReimCollectDataVO::getMoney).sum() : 0d);
        //查询某个人当前时间已报销记录
        reimSql = reimSql + "  and payCondition = '是' ";
        System.out.println( reimSql);
        queryData = reimburseRecordSer.findBySql(reimSql+reimGroupBy, LendMixReimCollectDataVO.class, reimFields);
        moneyList.add(queryData != null && queryData.size() > 0 ? queryData.stream().mapToDouble(LendMixReimCollectDataVO::getMoney).sum() : 0d);
        //查询某个人当前时间申请借款记录
        lendSql = lendSql + " and " + lendTypeFlag + " = '" + userName + "'  ";
        queryData = super.findBySql(lendSql+lendGroupBy, LendMixReimCollectDataVO.class, reimFields);
        moneyList.add(queryData != null && queryData.size() > 0 ? queryData.stream().mapToDouble(LendMixReimCollectDataVO::getMoney).sum() : 0d);
        //查询某个人当前时间已还款核对和帐务核对的借款记录
        lendSql = lendSql + "  and lendRetunStatus = 2 ";
        queryData = super.findBySql(lendSql+lendGroupBy, LendMixReimCollectDataVO.class, reimFields);
        moneyList.add(queryData != null && queryData.size() > 0 ? queryData.stream().mapToDouble(LendMixReimCollectDataVO::getMoney).sum() : 0d);

        //TODO
        //封装柱型图形数据
        ReimShapeMixVO shapeVO = new ReimShapeMixVO();
        shapeVO.setTitleVO(new ReimShapeTitleVO(userName + "指标统计", startTime + "与" + endTime, "", ""));
        shapeVO.setRlegendVO(new ReimShapeLegendVO("", "", Arrays.asList("金额")));
        shapeVO.setToolTipVO(new ReimShapeMixToolTipVO("axis", new AxisPointerBarVO()));
        shapeVO.setXaxisVO(new ReimShapeXaxisVO("category", "", Arrays.asList("申报报销", "报销已支付", "申请借款", "借款已支付")));
        List<ReimShapeMixSeriesVO> seriesData = new ArrayList<>();
        ReimShapeMixSeriesVO seriesVO = new ReimShapeMixSeriesVO("金额", "bar", "60%", moneyList);
        seriesData.add(seriesVO);
        shapeVO.setSeriesVO(seriesData);
        //封装饼状图形数据
        ReimShapeVO pieShapeVO = new ReimShapeVO();
        pieShapeVO.setTitleVO(new ReimShapeTitleVO(userName + "指标统计", startTime + "与" + endTime));
        pieShapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b}: {c} ({d}%)"));
        pieShapeVO.setRlegendVO(new ReimShapeLegendVO("", "", Arrays.asList("申报报销", "报销已支付", "申请借款", "借款已支付")));
        List<ReimShapeSeriesDataVO> pieDataList = new ArrayList<>();
        ReimShapeSeriesDataVO pieData = new ReimShapeSeriesDataVO("申报报销", "" + moneyList.get(0));
        pieDataList.add(pieData);
        pieData = new ReimShapeSeriesDataVO("报销已支付", "" + moneyList.get(1));
        pieDataList.add(pieData);
        pieData = new ReimShapeSeriesDataVO("申请借款", "" + moneyList.get(2));
        pieDataList.add(pieData);
        pieData = new ReimShapeSeriesDataVO("借款已支付", "" + moneyList.get(3));
        pieDataList.add(pieData);
        pieShapeVO.setSeriesVO(new ReimShapeSeriesVO("金额", "pie", "", pieDataList));

        result.setReimShapeMixVO(shapeVO);
        result.setReimShapeVO(pieShapeVO);
        return result;
    }


    @Override
    public LendMixReimShapeVO collectMixCompany(LendMixCompanyShapeDTO lendMixCompanyShapeDTO) throws SerException {
        LendMixReimShapeVO result = new LendMixReimShapeVO();

        ReimCompanyShapeDTO reimCompanyShapeDTO = BeanTransform.copyProperties(lendMixCompanyShapeDTO, ReimCompanyShapeDTO.class, "serialVersionUID");
        reimCompanyShapeDTO.setShapeStatus(lendMixCompanyShapeDTO.getReimburseShapeStatus());

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(lendMixCompanyShapeDTO, ReimburseShapeConDTO.class, "serialVersionUID");
        reimburseShapeConDTO.setReimburseShapeStatus(lendMixCompanyShapeDTO.getReimburseShapeStatus());
        if (null == lendMixCompanyShapeDTO.getReimburseShapeStatus()) {
            throw new SerException("汇总reimburseShapeStatus状态不能为空");
        }
        if (null == lendMixCompanyShapeDTO.getTypeStatus()) {
            throw new SerException("汇总类型typeStatus不能为空");
        }
        String reimTypeFlag = "";
        String lendTypeFlag = "";
        List<String> groupList = new ArrayList<>();
        ReimShapeDetailTypeStatus typeStatus = lendMixCompanyShapeDTO.getTypeStatus();
        switch (typeStatus) {
            case PROJECT:
                reimTypeFlag = "project";
                lendTypeFlag = "projectName";
                //查询公司所有的项目名称
                groupList = departmentDetailAPI.findAllProject();
                break;
            case GROUP:
                reimTypeFlag = "projectGroup";
                lendTypeFlag = "projectGroup";
                //查询公司所有的项目组
                groupList = positionDetailUserAPI.getAllDepartment();
                break;
            case AREA:
                reimTypeFlag = "area";
                lendTypeFlag = "area";
                //查询公司所有的项目组
                List<AreaBO> areaBOList = departmentDetailAPI.findArea();
                groupList = (areaBOList != null && areaBOList.size() > 0) ? areaBOList.stream().map(AreaBO::getArea).collect(Collectors.toList()) : new ArrayList<>();
                break;
        }

        //根据选择的日周月年枚举转成时间
        reimburseShapeConDTO = enumTOTime(reimCompanyShapeDTO, reimburseShapeConDTO);

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");

        String[] reimFields = new String[]{"money","username"};
        String reimSql = " select if(sum(reimMoney) IS NULL , 0.00 , sum(reimMoney) ) as money ,"+reimTypeFlag+" as username from lendreimbursement_reimburserecord where 1=1 ";
        reimSql = reimSql + " and createTime between '" + startTime + "' and '" + endTime + "'  ";
        String lendSql = " select if(sum(money) IS NULL , 0.00 , sum(money) ) as money ,"+lendTypeFlag+" as username from lendreimbursement_applylend where 1=1 ";
        lendSql = lendSql + " and createTime between '" + startTime + "' and '" + endTime + "'  ";
        //查询当前时间所有申请报销的记录
        String reimGroupBy = "  group by " + reimTypeFlag + " ";
        List<LendMixReimCollectDataVO> queryDataApply = reimburseRecordSer.findBySql(reimSql+reimGroupBy, LendMixReimCollectDataVO.class, reimFields);
        //查询当前时间所有已报销记录
        reimSql = reimSql + " and payCondition = '是' ";
        List<LendMixReimCollectDataVO> queryDataRecord = reimburseRecordSer.findBySql(reimSql+reimGroupBy, LendMixReimCollectDataVO.class, reimFields);
        //查询当前时间所有申请借款记录
        String lendGroupBy =  "  group by " + lendTypeFlag + " ";
        System.out.println( lendSql+lendGroupBy );
        List<LendMixReimCollectDataVO> queryDataLendApply = super.findBySql(lendSql+lendGroupBy, LendMixReimCollectDataVO.class, reimFields);
        //查询当前时间所有已还款核对和帐务核对的借款记录
        lendSql = lendSql + " and lendRetunStatus = 2  ";
        List<LendMixReimCollectDataVO> queryDataLendRecord = super.findBySql(lendSql+lendGroupBy, LendMixReimCollectDataVO.class, reimFields);

        //封装柱型图形数据
        List<ReimShapeBarSeriesVO> barDataList = TransformBarData(groupList, queryDataApply,queryDataRecord, queryDataLendApply,queryDataLendRecord);
        ReimCompanyShapeBarVO shapeVO = new ReimCompanyShapeBarVO();
        shapeVO.setReimShapeTitleVO(new ReimShapeTitleVO("公司总指标统计", startTime + "与" + endTime, "", ""));
        shapeVO.setLegendVO(new ReimShapeLegendVO("", "", groupList));
        shapeVO.setToolTipVO(new ReimShapeBarToolTipVO("axis"));
        shapeVO.setXaxisVO(new ReimShapeXaxisVO("category", "", Arrays.asList("申报报销", "报销已支付", "申请借款", "借款已支付")));
        shapeVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)"));
        shapeVO.setSeriesVOList( barDataList );
        //封装饼状图形数据
        ReimShapeVO pieShapeVO = new ReimShapeVO();
        pieShapeVO.setTitleVO(new ReimShapeTitleVO("公司总指标统计", startTime + "与" + endTime));
        pieShapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b}: {c} ({d}%)"));
        pieShapeVO.setRlegendVO(new ReimShapeLegendVO("", "", Arrays.asList("申报报销", "报销已支付", "申请借款", "借款已支付")));
        Double val1 = 0d;
        Double val2 = 0d;
        Double val3 = 0d;
        Double val4 = 0d;
        List<ReimShapeSeriesDataVO> seriesPieDataVOList = new ArrayList<>();
        for (ReimShapeBarSeriesVO piedata : barDataList) {
            val1 = val1 + piedata.getSeriesDataVOList().get(0);//申报报销
            val2 = val2 + piedata.getSeriesDataVOList().get(1);//报销已支付
            val3 = val3 + piedata.getSeriesDataVOList().get(2);//申请借款
            val4 = val4 + piedata.getSeriesDataVOList().get(3);//借款已支付
        }
        List<ReimShapeSeriesDataVO> pieDataList = new ArrayList<>();
        ReimShapeSeriesDataVO pieData = new ReimShapeSeriesDataVO("申报报销", String.valueOf( new BigDecimal(val1).setScale(5, BigDecimal.ROUND_UP).doubleValue()));
        pieDataList.add(pieData);
        pieData = new ReimShapeSeriesDataVO("报销已支付", String.valueOf( new BigDecimal(val2).setScale(5, BigDecimal.ROUND_UP).doubleValue()));
        pieDataList.add(pieData);
        pieData = new ReimShapeSeriesDataVO("申请借款", String.valueOf( new BigDecimal(val3).setScale(5, BigDecimal.ROUND_UP).doubleValue()));
        pieDataList.add(pieData);
        pieData = new ReimShapeSeriesDataVO("借款已支付", String.valueOf(new BigDecimal(val4).setScale(5, BigDecimal.ROUND_UP).doubleValue()));
        pieDataList.add(pieData);
        pieShapeVO.setSeriesVO(new ReimShapeSeriesVO("金额", "pie", "", pieDataList));

        result.setCompanyShapeMixVO(shapeVO);
        result.setReimShapeVO(pieShapeVO);
        return result;
    }

    private List<ReimShapeBarSeriesVO> TransformBarData(List<String> groupList, List<LendMixReimCollectDataVO> queryDataApply,
                                                        List<LendMixReimCollectDataVO> queryDataRecord, List<LendMixReimCollectDataVO> queryDataLendApply,
                                                        List<LendMixReimCollectDataVO> queryDataLendRecord) {
        List<ReimShapeBarSeriesVO> seriesDataList = new ArrayList<>();
        for (String tempArea : groupList) {
            ReimShapeBarSeriesVO seriesVO = new ReimShapeBarSeriesVO();
            List<Double> seriesDataVOListArea = new ArrayList<>();
            List<Double> reimMoney = queryDataApply.stream().filter(str -> tempArea.equals(str.getUsername())).map(LendMixReimCollectDataVO::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = queryDataRecord.stream().filter(str -> tempArea.equals(str.getUsername())).map(LendMixReimCollectDataVO::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = queryDataLendApply.stream().filter(str -> tempArea.equals(str.getUsername())).map(LendMixReimCollectDataVO::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = queryDataLendRecord.stream().filter(str -> tempArea.equals(str.getUsername())).map(LendMixReimCollectDataVO::getMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            seriesVO.setName( tempArea );
            seriesVO.setSeriesDataVOList( seriesDataVOListArea );

            seriesDataList.add( seriesVO );
        }
        return seriesDataList;
    }

    @Override
    public ReimShapeVO collectDetailMixCompany(LendMixCompanyShapeDTO lendMixCompanyShapeDTO) throws SerException {
        //点击某一块（申报报销/报销已支付/申请借款/借款已支付）
        //传块类型，传时间（年周月），传汇总类型（地区项目组项目名称）
        if (null == lendMixCompanyShapeDTO.getTypeStatus()) {
            throw new SerException("typeStatus:汇总类型（地区或项目组或项目名称）不能为空");
        }
        if (null == lendMixCompanyShapeDTO.getReimburseShapeStatus()) {
            throw new SerException("reimburseShapeStatus:时间（年或周或月）不能为空");
        }
        if (null == lendMixCompanyShapeDTO.getCompanyDetailType()) {
            throw new SerException("companyDetailType:点击块类型（申报报销/报销已支付/申请借款/借款已支付）不能为空");
        }
        ReimShapeDetailTypeStatus typeStatus = lendMixCompanyShapeDTO.getTypeStatus();//传汇总类型（地区项目组项目名称）
        ReimburseShapeStatus detailStatus = lendMixCompanyShapeDTO.getReimburseShapeStatus();//传时间（年周月）
        CompanyShapeDetailTypeStatus companyDetailType = lendMixCompanyShapeDTO.getCompanyDetailType();//某一块（申报报销/报销已支付/申请借款/借款已支付）

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        switch (detailStatus) {
            case YEAR:
                if (StringUtils.isBlank("" + lendMixCompanyShapeDTO.getYear()) || lendMixCompanyShapeDTO.getYear() < 1900) {
                    throw new SerException("年份(year)不能为空且大于1900");
                }
                startTime = LocalDateTime.of(lendMixCompanyShapeDTO.getYear(), 1, 1, 0, 0, 1);
                endTime = LocalDateTime.of(lendMixCompanyShapeDTO.getYear(), 12, 31, 23, 59, 59);
                break;
            case MONTH:
                if (StringUtils.isBlank("" + lendMixCompanyShapeDTO.getYear()) || StringUtils.isBlank("" + lendMixCompanyShapeDTO.getMonth()) || lendMixCompanyShapeDTO.getYear() < 1900 || lendMixCompanyShapeDTO.getMonth() < 1 || lendMixCompanyShapeDTO.getMonth() > 12) {
                    throw new SerException("年份(year)或月份(month)不能为空,年大于1900,月份在1-12之间");
                }
                LocalDate start = DateUtil.getStartDayOfMonth(lendMixCompanyShapeDTO.getYear(), lendMixCompanyShapeDTO.getMonth());
                LocalDate end = DateUtil.getEndDaYOfMonth(lendMixCompanyShapeDTO.getYear(), lendMixCompanyShapeDTO.getMonth());
                startTime = LocalDateTime.of(start.getYear(), start.getMonthValue(), start.getDayOfMonth(), 0, 0, 1);
                endTime = LocalDateTime.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 23, 59, 59);
                break;
            case WEEK:
                if (StringUtils.isBlank("" + lendMixCompanyShapeDTO.getYear()) || StringUtils.isBlank("" + lendMixCompanyShapeDTO.getMonth()) || StringUtils.isBlank("" + lendMixCompanyShapeDTO.getWeek())) {
                    throw new SerException("年份或月份或周数不能为空");
                }
                LocalDate[] dateDuring = DateUtil.getWeekTimes(lendMixCompanyShapeDTO.getYear(), lendMixCompanyShapeDTO.getMonth(), lendMixCompanyShapeDTO.getWeek());
                startTime = LocalDateTime.of(dateDuring[0].getYear(), dateDuring[0].getMonthValue(), dateDuring[0].getDayOfMonth(), 0, 0, 1);
                endTime = LocalDateTime.of(dateDuring[1].getYear(), dateDuring[1].getMonthValue(), dateDuring[1].getDayOfMonth(), 23, 59, 59);
                break;
            default:
                return null;
        }

        //查询公司所有的项目组
        List<String> groupList = new ArrayList<>();
        switch (typeStatus) {
            case AREA:
                List<AreaBO> areaBOList = departmentDetailAPI.findArea();
                groupList = (areaBOList != null && areaBOList.size() > 0) ? areaBOList.stream().map(AreaBO::getArea).collect(Collectors.toList()) : new ArrayList<>();
                break;
            case GROUP:
                groupList = positionDetailUserAPI.getAllDepartment();
                break;
            case PROJECT:
                groupList = departmentDetailAPI.findAllProject();
                break;
            default:
                break;
        }
        String detailTypeStr = "";
        List<ReimShapeSeriesDataVO> result = new ArrayList<>();
        switch (companyDetailType) {
            case REIMRECORD:
                detailTypeStr = "申请报销";
                result = getReimDataSeries(groupList, startTime, endTime, typeStatus, "REIMRECORD");
                break;
            case REIMHASPAY:
                detailTypeStr = "报销已支付";
                result = getReimDataSeries(groupList, startTime, endTime, typeStatus, "REIMHASPAY");
                break;
            case LENDRECORD:
                detailTypeStr = "申请借款";
                result = getLendDataSeries(groupList, startTime, endTime, typeStatus, "LENDRECORD");
                break;
            case LENDHASCHECK:
                detailTypeStr = "借款已支付";
                result = getLendDataSeries(groupList, startTime, endTime, typeStatus, "LENDHASCHECK");
                break;
            default:
                break;
        }

        ReimShapeVO pieShapeVO = new ReimShapeVO();
        pieShapeVO.setTitleVO(new ReimShapeTitleVO("公司总指标统计" + detailTypeStr, startTime + "与" + endTime));
        pieShapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b}: {c} ({d}%)"));
        pieShapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "", groupList));
        ReimShapeSeriesVO seriesVO = new ReimShapeSeriesVO("", "pie", "", result);
        pieShapeVO.setSeriesVO(seriesVO);

        return pieShapeVO;
    }

    //统计所有地区的已报销记录
    private List<ReimShapeSeriesDataVO> getReimDataSeries(List<String> groupList, LocalDateTime startTime, LocalDateTime endTime, ReimShapeDetailTypeStatus typeStatus, String flag) throws SerException {
        List<ReimShapeSeriesDataVO> dataVOList = new ArrayList<>();
        String type = "";
        switch (typeStatus) {
            case AREA:
                type = "area";
                break;
            case GROUP:
                type = "projectGroup";
                break;
            case PROJECT:
                type = "project";
                break;
            default:
                break;
        }
        for (String areaStr : groupList) {
            String[] fields = new String[]{"reimMoney", "projectGroup"};
            String sql = "";
            if (flag.equals("REIMRECORD")) {
                //出现广州江门佛山湛江所有已报销记录
                sql = "select sum(reimMoney) as reimMoney , " + type + " as projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "'  ";
            } else if (flag.equals("REIMHASPAY")) {
                //出现广州江门佛山湛江所有报销已支付记录
                sql = "select sum(reimMoney) as reimMoney , " + type + " as projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "'   and payCondition = '是' ";
            }
            String sqlGroup = " and " + type + " = '" + areaStr + "' group by " + type + " ";
            String sqlQuery = sql + sqlGroup;
            List<ReimburseRecord> list = super.findBySql(sqlQuery, ReimburseRecord.class, fields);

            ReimShapeSeriesDataVO seriesDataVO = new ReimShapeSeriesDataVO();
            seriesDataVO.setName(areaStr);
            seriesDataVO.setValue(list != null && list.size() > 0 ? String.valueOf(list.get(0).getReimMoney()) : String.valueOf(0));
            dataVOList.add(seriesDataVO);

        }
        return dataVOList;
    }

    //统计所有地区的申请借款记录
    private List<ReimShapeSeriesDataVO> getLendDataSeries(List<String> groupList, LocalDateTime startTime, LocalDateTime endTime, ReimShapeDetailTypeStatus typeStatus, String flag) throws SerException {
        List<ReimShapeSeriesDataVO> dataVOList = new ArrayList<>();
        String type = "";
        switch (typeStatus) {
            case AREA:
                type = "area";
                break;
            case GROUP:
                type = "projectGroup";
                break;
            case PROJECT:
                type = "projectName";
                break;
            default:
                break;
        }
        for (String areaStr : groupList) {
            String[] fields = new String[]{"money", "projectGroup"};
            String sql = "";
            if (flag.equals("LENDRECORD")) {
                //出现广州江门佛山湛江所有已报销记录
                sql = "select sum(money) as money , " + type + " as projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "'  ";
            } else if (flag.equals("LENDHASCHECK")) {
                //或出现广州江门佛山湛江所有借款已支付记录
                sql = "select sum(money) as money , " + type + " as projectGroup from lendreimbursement_applylend where createTime between '" + startTime + "' and '" + endTime + "'  and lendRetunStatus = 2  ";
            }
            String sqlGroup = " and " + type + " = '" + areaStr + "' group by " + type + " ";
            String sqlQuery = sql + sqlGroup;
            List<ApplyLend> list = super.findBySql(sqlQuery, ApplyLend.class, fields);

            ReimShapeSeriesDataVO seriesDataVO = new ReimShapeSeriesDataVO();
            seriesDataVO.setName(areaStr);
            seriesDataVO.setValue(list != null && list.size() > 0 ? String.valueOf(list.get(0).getMoney()) : String.valueOf(0));
            dataVOList.add(seriesDataVO);

        }
        return dataVOList;
    }


}