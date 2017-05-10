package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
import com.bjike.goddess.lendreimbursement.enums.LendStatus;
import com.bjike.goddess.lendreimbursement.enums.Words;
import com.bjike.goddess.lendreimbursement.to.ApplyLendTO;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.entity.User;
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

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Long countApplyLend(ApplyLendDTO applyLendDTO) throws SerException {
        applyLendDTO.getConditions().add(Restrict.eq("receivePay", "否"));
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
            applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }
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
        //未收款
        applyLendDTO.getConditions().add(Restrict.eq("receivePay", "否"));
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
            applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }
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
        super.save(applyLend);
        ApplyLendBO bo = BeanTransform.copyProperties(applyLend, ApplyLendBO.class, "lendStatus");
        bo.setLendStatus(applyLend.getLendStatus());
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editApplyLend(ApplyLendTO applyLendTO) throws SerException {
        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
            throw new SerException("编辑失败，未填无发票备注");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (LendStatus.CHARGEPASS.equals(lend.getLendStatus()) || LendStatus.LISTERROR.equals(lend.getLendStatus())) {
            throw new SerException("负责人已审核过，不可再编辑");
        }
//        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        lend.setEstimateLendDate(applyLend.getEstimateLendDate());
        lend.setLender( applyLend.getLender());
        lend.setCharger( applyLend.getCharger());
        lend.setArea( applyLend.getArea());
        lend.setProjectGroup(applyLend.getProjectGroup());
        lend.setProjectName(applyLend.getProjectName());
        lend.setLendWay( applyLend.getLendWay());
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
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{3, 6, 7, 8}));


        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
            applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }
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
        applyLendDTO.getConditions().add(Restrict.notIn("lendStatus", new Integer[]{3, 6, 7, 8}));


        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
            applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

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
        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
            throw new SerException("编辑失败，未填无发票备注");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        BeanUtils.copyProperties(applyLend, lend, "id", "createTime", "lendStatus");
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

        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("负责人审核失败，未填id");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        lend.setCharger(userAPI.currentUser().getUsername());
        lend.setChargerOpinion(applyLendTO.getChargerOpinion());
        lend.setChargerPass(applyLendTO.getChargerPass());
        if ("是".equals(applyLendTO.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGEPASS);
        } else if ("否".equals(applyLendTO.getChargerPass())) {
            lend.setLendStatus(LendStatus.CHARGENOTPASS);
        }
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);

        //存审核详情表
        UserBO userBO = userAPI.currentUser();
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
        lend.setFinacer(userAPI.currentUser().getUsername());
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
        UserBO userBO = userAPI.currentUser();
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

        //说明财务还没审核
        if (lend.getLendStatus().getCode() == 3 || lend.getLendStatus().getCode() == 4) {
            lend.setManager(userAPI.currentUser().getUsername());
            lend.setManagerOpinion(applyLendTO.getManagerOpinion());
            lend.setManagerPass(applyLendTO.getManagerPass());
            lend.setModifyTime(LocalDateTime.now());
            super.update(lend);
        } else if (lend.getLendStatus().getCode() == 5) {
            throw new SerException("总经办审核失败，此条数据已被冻结");
        } else {
            throw new SerException("总经办审核失败，财务运营部还未审核");
        }

        lend.setManager(userAPI.currentUser().getUsername());
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
        UserBO userBO = userAPI.currentUser();
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
        lend.setFinacer(userAPI.currentUser().getUsername());
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

        applyLendDTO.getConditions().add(Restrict.eq("lendError", 9));
        // LendStatus.CHARGESURECONGEL
        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 6));

        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
            applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

        Long count = super.count(applyLendDTO);
        return count;
    }

    @Override
    public List<ApplyLendBO> listApplyError(ApplyLendDTO applyLendDTO) throws SerException {
        applyLendDTO.getConditions().add(Restrict.eq("lendError", 9));
        //LendStatus.CHARGESURECONGEL
        applyLendDTO.getConditions().add(Restrict.or("lendStatus", 6));

        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("lender", applyLendDTO.getLender()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getCharger())) {
            applyLendDTO.getConditions().add(Restrict.eq("charger", applyLendDTO.getCharger()));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLendDate())) {
            applyLendDTO.getConditions().add(Restrict.eq("lendDate", LocalDate.parse(applyLendDTO.getLendDate(), formatter)));
        }
        if (StringUtils.isNotBlank(applyLendDTO.getLender())) {
            applyLendDTO.getConditions().add(Restrict.eq("estimateLendDate", LocalDate.parse(applyLendDTO.getEstimateLendDate(), formatter)));
        }

        List<ApplyLend> applyLendList = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> applyLendBOS = BeanTransform.copyProperties(applyLendList, ApplyLendBO.class);
        return applyLendBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyLendBO editApplyError(ApplyLendTO applyLendTO) throws SerException {
        if ("否".equals(applyLendTO.getInvoice()) && StringUtils.isBlank(applyLendTO.getNoInvoiceRemark())) {
            throw new SerException("编辑失败，未填无发票备注");
        }
        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        if (!LendStatus.CHARGESURECONGEL.equals(lend.getLendStatus())) {
            throw new SerException("编辑失败，此条数据负责人还未确认冻结状态");
        }

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
            BeanUtils.copyProperties(lend, ac, "id", "createTime", "applyLendId");
            ac.setApplyLendId(lend.getId());
            ac.setModifyTime(LocalDateTime.now());
            applyLendCopySer.save(ac);
        }

        //申请单有误编辑
        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
        //填单人
        lend.setFillSingler(userAPI.currentUser().getUsername());
        lend.setModifyTime(LocalDateTime.now());
        lend.setLendStatus(LendStatus.LISTERROR);
        super.update(lend);


        return BeanTransform.copyProperties(lend, ApplyLendBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteApplyError(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("删除失败，id不能为空");
        }
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
        dto.getConditions().add(Restrict.eq("managerPass", "是"));
        dto.getConditions().add(Restrict.or("fincerPass", "是"));
        dto.getConditions().add(Restrict.or("chargerPass", "是"));
        //LendStatus.CHARGESURECONGEL
        dto.getConditions().add(Restrict.ne("lendStatus", 6));
        //LendStatus.FINACECONGEL
        dto.getConditions().add(Restrict.ne("lendStatus", 5));
        //LendStatus.LISTERROR
        dto.getConditions().add(Restrict.ne("lendStatus", 9));

        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listHasAudit(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("managerPass", "是"));
        dto.getConditions().add(Restrict.or("fincerPass", "是"));
        dto.getConditions().add(Restrict.or("chargerPass", "是"));
        //LendStatus.CHARGESURECONGEL
        dto.getConditions().add(Restrict.ne("lendStatus", 6));
        //LendStatus.FINACECONGEL
        dto.getConditions().add(Restrict.ne("lendStatus", 5));
        //LendStatus.LISTERROR
        dto.getConditions().add(Restrict.ne("lendStatus", 9));

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
        dto.getConditions().add(Restrict.eq("lendStatus", 3));
        //LendStatus.MANAGEPASS
        dto.getConditions().add(Restrict.or("lendStatus", 7));
        Long counts = super.count(dto);
        return counts;
    }


    @Override
    public List<ApplyLendBO> listWaitPay(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "否"));
        //LendStatus.FINACEPASS
        dto.getConditions().add(Restrict.eq("lendStatus", 3));
        //LendStatus.MANAGEPASS
        dto.getConditions().add(Restrict.or("lendStatus", 7));
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

        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
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
        dto.getConditions().add(Restrict.eq("fillSingler", userName));
        dto.getConditions().add(Restrict.or("lender", userName));
        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listSureRecieveMoney(ApplyLendDTO applyLendDTO) throws SerException {
        String userName = userAPI.currentUser().getUsername();
        ApplyLendDTO dto = applyLendDTO;
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

        ApplyLend applyLend = BeanTransform.copyProperties(applyLendTO, ApplyLend.class, true);
        ApplyLend lend = super.findById(applyLendTO.getId());

        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");
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
        List<ApplyLend> applyLend = super.findByCis(dto, true);
        Long counts = super.count(dto);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listBorrowRecord(ApplyLendDTO applyLendDTO) throws SerException {
        ApplyLendDTO dto = applyLendDTO;
        dto.getConditions().add(Restrict.eq("receivePay", "是"));
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

        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");

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

        BeanUtils.copyProperties(applyLend, lend, "id", "createTime");

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
        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listReturnMoneyRecord(ApplyLendDTO applyLendDTO) throws SerException {
        //还款记录是报销金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));

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
    public List<ApplyLendBO> checkReturnMoney(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("失败，id不能为空");
        }
        if (StringUtils.isBlank(applyLendTO.getCheckcontent())) {
            throw new SerException("失败，核对内容不能为空");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        BeanUtils.copyProperties(applyLendTO, lend, "id", "createTime");

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

        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listBusinessCheck(ApplyLendDTO applyLendDTO) throws SerException {
        //帐务核对是报销金额和借款金额>0
        applyLendDTO.getConditions().add(Restrict.gt("reimMoney", 0));
        applyLendDTO.getConditions().add(Restrict.gt("lendMoney", 0));

        List<ApplyLend> list = super.findByCis(applyLendDTO, true);
        List<ApplyLendBO> lendBOList = BeanTransform.copyProperties(list, ApplyLendBO.class);
        return lendBOList;
    }

    @Override
    public ApplyLendBO checkTicket(ApplyLendTO applyLendTO) throws SerException {
        if (StringUtils.isBlank(applyLendTO.getId())) {
            throw new SerException("失败，id不能为空");
        }
        if (StringUtils.isBlank(applyLendTO.getCheckcontent())) {
            throw new SerException("失败，核对内容不能为空");
        }
        if (!"否".equals(applyLendTO.getDocumentCondition()) || !"是".equals(applyLendTO.getDocumentCondition())) {
            throw new SerException("失败，是否收到单据填写是或否");
        }
        ApplyLend lend = super.findById(applyLendTO.getId());

        BeanUtils.copyProperties(applyLendTO, lend, "id", "createTime");

        lend.setDocumentCondition(applyLendTO.getDocumentCondition());
        lend.setTicketer(userAPI.currentUser().getUsername());
        lend.setTicketCondition(applyLendTO.getTicketCondition());
        lend.setModifyTime(LocalDateTime.now());
        super.update(lend);
        return BeanTransform.copyProperties(lend, ApplyLendBO.class);

    }

    @Override
    public Long countRecTicket(ApplyLendDTO applyLendDTO) throws SerException {
        //收到单据 是
        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));
        Long counts = super.count(applyLendDTO);
        return counts;
    }

    @Override
    public List<ApplyLendBO> listRecieveTicketRecord(ApplyLendDTO applyLendDTO) throws SerException {
        //收到单据 是
        applyLendDTO.getConditions().add(Restrict.eq("documentCondition", "是"));

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
                    .append("   FROM lendreimbursement_applylend where area = '" + applyLendDTO.getProjectGroup().trim() + "'");
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
                    .append("   FROM lendreimbursement_applylend where area = '" + applyLendDTO.getProjectName().trim() + "'");
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
}