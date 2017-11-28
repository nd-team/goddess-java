package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.CollectReimerDataBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAuditLogBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.*;
import com.bjike.goddess.lendreimbursement.dto.reimshape.*;
import com.bjike.goddess.lendreimbursement.entity.*;
import com.bjike.goddess.lendreimbursement.enums.*;
import com.bjike.goddess.lendreimbursement.excel.ReimburseRecordExcel;
import com.bjike.goddess.lendreimbursement.excel.SonPermissionObject;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.PhoneReimbursePayTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.*;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcelTO;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 报销记录业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class ReimburseRecordSerImpl extends ServiceImpl<ReimburseRecord, ReimburseRecordDTO> implements ReimburseRecordSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private PositionAPI positionAPI;
    @Autowired
    private ReimburseAnalisisorSer reimburseAnalisisorSer;
    @Autowired
    private ReimburseAuditLogSer reimburseAuditLogSer;
    @Autowired
    private ReimburseRecordLogSer reimburseRecordLogSer;
    @Autowired
    private FinoddinforSer finoddinforSer;
    @Autowired
    private LendPermissionSer cusPermissionSer;
    @Autowired
    private ApplyLendSer applyLendSer;

    private Logger logger = Logger.getLogger(ReimburseRecordSerImpl.class);

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private Boolean checkPermission(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //岗位权限
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        SonPermissionObject obj = new SonPermissionObject();
        obj.setName("applyReimRecord");
        obj.setDescribesion("申请报销记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("applyReimWrong");
        obj.setDescribesion("报销单有误记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimWaitingAuditRecord");
        obj.setDescribesion("等待审核记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimHasAuditRecord");
        obj.setDescribesion("已审核记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimHasAnalysisRecord");
        obj.setDescribesion("已分析记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimAccountCheckRecord");
        obj.setDescribesion("账户核对记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimWaitingPay");
        obj.setDescribesion("等待付款记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimHasPayRecord");
        obj.setDescribesion("已付款记录-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimAnalisisSet");
        obj.setDescribesion("报销分析权限设置-报销");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("reimFinoddInfor");
        obj.setDescribesion("报销单号管理-报销");
        obj.setFlag(true);
        list.add(obj);

        //借款
        obj = new SonPermissionObject();
        obj.setName("applyLend");
        obj.setDescribesion("申请借款-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("applyErrorBorrow");
        obj.setDescribesion("申请单有误记录-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendWaitingAudit");
        obj.setDescribesion("等待审核-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendHasAudit");
        obj.setDescribesion("已审核/已分析-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendWaitPay");
        obj.setDescribesion("等待付款-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendSurePay");
        obj.setDescribesion("确认付款-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendApplyRecord");
        obj.setDescribesion("借款记录-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendReturnRecord");
        obj.setDescribesion("还款记录-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendAccountcheck");
        obj.setDescribesion("账务核对-借款");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendReceiveTicket");
        obj.setDescribesion("已收票记录");
        obj.setFlag(true);
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("lendAnalysisRecord");
        obj.setDescribesion("已分析情况记录");
        obj.setFlag(true);
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
//                flag = guideIdentity();
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
//            case CONGEL:
//                flag = guideIdentity();
//                break;
//            case THAW:
//                flag = guideIdentity();
//                break;
//            case COLLECT:
//                flag = guideIdentity();
//                break;
//            case UPLOAD:
//                flag = guideIdentity();
//                break;
//            case DOWNLOAD:
//                flag = guideIdentity();
//                break;
//            case IMPORT:
//                flag = guideIdentity();
//                break;
//            case EXPORT:
//                flag = guideIdentity();
//                break;
//            case SEE:
//                flag = guideIdentity();
//                break;
//            case SEEFILE:
//                flag = guideIdentity();
//                break;
            case PAY:
                //帐务核对和付款
                flag = checkPermission("reim-accountCheckAndPay");
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
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    private ReimburseRecordDTO addCondition(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean listpermission = cusPermissionSer.getCusPermission("reim-ListAll");
        RpcTransmit.transmitUserToken(userToken);
        String userName = userAPI.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        if (!listpermission && !"admin".equals(userName.toLowerCase())) {
            //没有查看所有数据的权限，则只能查看自己的数据
            if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
                reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
            } else {
                reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", userName));
            }
            if (StringUtils.isNotBlank(reimburseRecordDTO.getCharger())) {
                reimburseRecordDTO.getConditions().add(Restrict.eq("charger", reimburseRecordDTO.getCharger()));
            } else {
                reimburseRecordDTO.getConditions().add(Restrict.or("charger", userName));
            }
            reimburseRecordDTO.getConditions().add(Restrict.or("filler", userName));
            RpcTransmit.transmitUserToken(userToken);
        } else {
            if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
                reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
            }
            if (StringUtils.isNotBlank(reimburseRecordDTO.getCharger())) {
                reimburseRecordDTO.getConditions().add(Restrict.eq("charger", reimburseRecordDTO.getCharger()));
            }
        }
        return reimburseRecordDTO;
    }

    /**
     * 判断是否可以查看列表的所有数据
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    private StringBuffer addConditionTOReimError(ReimburseRecordDTO reimburseRecordDTO, StringBuffer sql) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean listpermission = cusPermissionSer.getCusPermission("reim-ListAll");
        RpcTransmit.transmitUserToken(userToken);
        String userName = userAPI.currentUser().getUsername();
        if (!listpermission && !"admin".equals(userName.toLowerCase())) {
            //没有查看所有数据的权限，则只能查看自己的数据
            if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
                sql.append(" and r2.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
            } else {
                sql.append(" and r2.reimer = '" + userName + "' ");
            }
            if (StringUtils.isNotBlank(reimburseRecordDTO.getCharger())) {
                sql.append(" and r2.charger = '" + reimburseRecordDTO.getCharger() + "' ");
            } else {
                sql.append(" or r2.charger = '" + userName + "' ");
            }
            sql.append(" or r2.filler = '" + userName + "' ");
            RpcTransmit.transmitUserToken(userToken);
        } else {
            if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
                sql.append(" and r2.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
            }
            if (StringUtils.isNotBlank(reimburseRecordDTO.getCharger())) {
                sql.append(" and r2.charger = '" + reimburseRecordDTO.getCharger() + "' ");
            }
        }
        return sql;
    }

    /**
     * 报销特殊岗位对申请报销的编辑、删除权限
     *
     * @param userToken
     * @param userBO
     * @param reimburseRecord
     * @throws SerException
     */
    private void checkAddAndEditPermission(String idFlag, String userToken, UserBO userBO, ReimburseRecord reimburseRecord) throws SerException {
        Boolean listpermission = cusPermissionSer.getCusPermission(idFlag);
        if (!listpermission && !"admin".equals(userBO.getUsername().toLowerCase())) {
            //没有特殊权限的话,就只能自己或填单人修改自己的
            if (!userBO.getUsername().equals(reimburseRecord.getReimer()) && !userBO.getUsername().equals(reimburseRecord.getFiller())) {
                throw new SerException("您没有权限");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    @Override
    public ReimburseRecordBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        ReimburseRecord reimburseRecord = super.findById(id);
        return BeanTransform.copyProperties(reimburseRecord, ReimburseRecordBO.class);
    }

    @Override
    public Long countReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        reimburseRecordDTO.getSorts().add("createTime=desc");
        reimburseRecordDTO.getConditions().add(Restrict.eq("payCondition", "否"));

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        reimburseRecordDTO = addCondition(reimburseRecordDTO);

        Long count = super.count(reimburseRecordDTO);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        reimburseRecordDTO.getSorts().add("createTime=desc");
        reimburseRecordDTO.getConditions().add(Restrict.eq("payCondition", "否"));

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        reimburseRecordDTO = addCondition(reimburseRecordDTO);

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO, true);
        List<ReimburseRecordBO> recordBOList = BeanTransform.copyProperties(list, ReimburseRecordBO.class);
        return recordBOList;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO addReimburseRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {

        if (StringUtils.isBlank(reimburseRecordTO.getReimer())) {
            throw new SerException("报销人不能为空");
        }
        String reimer = reimburseRecordTO.getReimer();
        LocalDate todayDate = LocalDate.now();
        //借款日期超过15天的借款金额和报销金额等于0的就不给予报销
        ApplyLendDTO applyLendDTO = new ApplyLendDTO();
        applyLendDTO.getConditions().add(Restrict.eq("lender", reimer));
        applyLendDTO.getConditions().add(Restrict.lt("lendDate", todayDate.minusDays(15)));
//        applyLendDTO.getConditions().add(Restrict.eq("lendMoney", 0d));
        //还款状态为非通过的
        applyLendDTO.getConditions().add(Restrict.ne("lendRetunStatus", 2));
        List<ApplyLend> applyLendList = applyLendSer.findByCis(applyLendDTO);
        if (applyLendList != null && applyLendList.size() > 0) {
            throw new SerException("报销人有超过15天的借款未还，请先还款，再来报销");
        }

        //获取最小报销单号
        String runNum = finoddinforSer.getMinRunNum();
        if (StringUtils.isBlank(runNum)) {
            throw new SerException("不好意思报销单号已用完,请先去生成报销单号,谢谢！");
        }
        String userName = userAPI.currentUser().getUsername();
        ReimburseRecord reimburseRecord = BeanTransform.copyProperties(reimburseRecordTO, ReimburseRecord.class, true);
        reimburseRecord.setCreateTime(LocalDateTime.now());
        reimburseRecord.setFiller(userName);
        reimburseRecord.setCommitDate(LocalDate.now());
        //设置报销单号
        reimburseRecord.setReimNumber(runNum);
        //设置单据编号
        reimburseRecord.setTicketNumber(runNum + "-" + reimburseRecordTO.getTicketQuantity());
        reimburseRecord.setPayCondition("否");
        reimburseRecord.setReimStatus(ReimStatus.NONE);

        //冻结该报销单，说明已经被使用了
        FinoddinforDTO finoddinforDTO = new FinoddinforDTO();
        finoddinforDTO.getConditions().add(Restrict.eq("runNum", runNum));
        Finoddinfor finoddinfor = finoddinforSer.findOne(finoddinforDTO);
        finoddinfor.setStatus(Status.CONGEAL);
        finoddinfor.setModifyTime(LocalDateTime.now());
        finoddinforSer.update(finoddinfor);

        super.save(reimburseRecord);

        //存分析审核日志记录表
        List<ReimburseAuditLog> auditLogs = new ArrayList<>();
        List<ReimburseAnalisisor> analysistorList = reimburseAnalisisorSer.findAll();
        analysistorList.stream().forEach(str -> {
            ReimburseAuditLog logs = new ReimburseAuditLog();
            logs.setCreateTime(LocalDateTime.now());
            logs.setAuditTime(LocalDate.now());
            logs.setEmpNum(str.getEmpNum());
            logs.setUserName(str.getUserName());
            logs.setPosition(str.getPosition());
            logs.setReimrecordId(reimburseRecord.getId());
            logs.setAuditStatus("未处理");

            auditLogs.add(logs);
        });
        if (auditLogs != null && auditLogs.size() > 0) {
            reimburseAuditLogSer.save(auditLogs);
        }

        return BeanTransform.copyProperties(reimburseRecord, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO editReimburseRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if (StringUtils.isBlank(reimburseRecordTO.getReimer())) {
            throw new SerException("报销人不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        checkAddAndEditPermission("reimApply-EditAndDelete", userToken, userBO, temp);

        //修改报销记录
        addRecordLog(reimburseRecordTO, temp);

        temp.setReimer(reimburseRecordTO.getReimer());
        temp.setCharger(reimburseRecordTO.getCharger());
        temp.setAttender(reimburseRecordTO.getAttender());
        temp.setArea(reimburseRecordTO.getArea());
        temp.setProject(reimburseRecordTO.getProject());
        temp.setOccureDate(LocalDate.parse(reimburseRecordTO.getOccureDate()));
        temp.setTicketQuantity(reimburseRecordTO.getTicketQuantity());
//        temp.setFinanceSubject(reimburseRecordTO.getFinanceSubject());
        temp.setFirstSubject(reimburseRecordTO.getFirstSubject());
        temp.setSecondSubject(reimburseRecordTO.getSecondSubject());
        temp.setThirdSubject(reimburseRecordTO.getThirdSubject());
        temp.setDayTask(reimburseRecordTO.getDayTask());
        temp.setAddContent(reimburseRecordTO.getAddContent());
        temp.setPlainInfo(reimburseRecordTO.getPlainInfo());
        temp.setReimerRemark(reimburseRecordTO.getReimerRemark());
        temp.setSummary(reimburseRecordTO.getSummary());
        temp.setModifyTime(LocalDateTime.now());
        temp.setCommitDate(LocalDate.now());

        super.update(temp);

        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    public void addRecordLog(ReimburseRecordTO reimburseRecordTO, ReimburseRecord temp) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = null;
        if (udetailBO != null) {
            positionBO = positionAPI.findById(udetailBO.getPositionId());
        }

        //修改报销记录
        ReimburseRecordLog rrLog = new ReimburseRecordLog();
        StringBuffer sb = new StringBuffer("");
        if (!temp.getReimer().equals(reimburseRecordTO.getReimer())) {
            sb.append("报销人由:" + temp.getReimer() + " 修改为:" + reimburseRecordTO.getReimer());
        }
        if (!temp.getCharger().equals(reimburseRecordTO.getCharger())) {
            sb.append("负责人由:" + temp.getCharger() + " 修改为:" + reimburseRecordTO.getCharger());
        }
        if (null != temp.getAttender() && !temp.getAttender().equals(reimburseRecordTO.getAttender())) {
            sb.append("参与人由:" + temp.getAttender() + " 修改为:" + reimburseRecordTO.getAttender());
        }
        if (!temp.getArea().equals(reimburseRecordTO.getArea())) {
            sb.append("地区由:" + temp.getArea() + " 修改为:" + reimburseRecordTO.getArea());
        }
        if (!temp.getProject().equals(reimburseRecordTO.getProject())) {
            sb.append("项目名称由:" + temp.getProject() + " 修改为:" + reimburseRecordTO.getProject());
        }
        if (!temp.getOccureDate().equals(reimburseRecordTO.getOccureDate())) {
            sb.append("报销发生日期由:" + temp.getOccureDate() + " 修改为:" + reimburseRecordTO.getOccureDate());
        }
        if (null != temp.getTicketQuantity() && !temp.getTicketQuantity().equals(reimburseRecordTO.getTicketQuantity())) {
            sb.append("单据数量由:" + temp.getTicketQuantity() + " 修改为:" + reimburseRecordTO.getTicketQuantity());
        }
        if (null != temp.getFirstSubject() && !temp.getFirstSubject().equals(reimburseRecordTO.getFirstSubject())) {
            sb.append("一级科目由:" + temp.getFirstSubject() + " 修改为:" + reimburseRecordTO.getFirstSubject());
        }
        if (null != temp.getSecondSubject() && !temp.getSecondSubject().equals(reimburseRecordTO.getSecondSubject())) {
            sb.append("二级科目由:" + temp.getSecondSubject() + " 修改为:" + reimburseRecordTO.getSecondSubject());
        }
        if (!temp.getThirdSubject().equals(reimburseRecordTO.getThirdSubject())) {
            sb.append("三级科目由:" + temp.getThirdSubject() + " 修改为:" + reimburseRecordTO.getThirdSubject());
        }
        if (null != temp.getDayTask() && !temp.getDayTask().equals(reimburseRecordTO.getDayTask())) {
            sb.append("报销当天任务由:" + temp.getDayTask() + " 修改为:" + reimburseRecordTO.getDayTask());
        }
        if (null != temp.getAddContent() && !temp.getAddContent().equals(reimburseRecordTO.getAddContent())) {
            sb.append("补充内容由:" + temp.getAddContent() + " 修改为:" + reimburseRecordTO.getAddContent());
        }
        if (!temp.getPlainInfo().equals(reimburseRecordTO.getPlainInfo())) {
            sb.append("说明由:" + temp.getPlainInfo() + " 修改为:" + reimburseRecordTO.getPlainInfo());
        }
        if (null != temp.getReimerRemark() && !temp.getReimerRemark().equals(reimburseRecordTO.getReimerRemark())) {
            sb.append("报销人备注由:" + temp.getReimerRemark() + " 修改为:" + reimburseRecordTO.getReimerRemark());
        }
        if (!temp.getSummary().equals(reimburseRecordTO.getSummary())) {
            sb.append("摘要由:" + temp.getSummary() + " 修改为:" + reimburseRecordTO.getSummary());
        }
        if (!temp.getReimMoney().equals(reimburseRecordTO.getReimMoney())) {
            sb.append("金额由:" + temp.getReimMoney() + " 修改为:" + reimburseRecordTO.getReimMoney());
        }
        rrLog.setCreateTime(LocalDateTime.now());
        rrLog.setReimrecordId(temp.getId());
        rrLog.setUserName(userName);
        rrLog.setEmpNum(userBO.getEmployeeNumber());
        rrLog.setPosition(positionBO != null ? positionBO.getName() : "");
        rrLog.setContent(sb.toString());
        reimburseRecordLogSer.save(rrLog);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteReimburseRecord(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        ReimburseRecord temp = super.findById(id);
        checkAddAndEditPermission("reimApply-EditAndDelete", userToken, userBO, temp);


        //删除报销级路日志
        ReimburseRecordLogDTO rrLogDTO = new ReimburseRecordLogDTO();
        rrLogDTO.getConditions().add(Restrict.eq("reimrecordId", id));
        List<ReimburseRecordLog> rrlogList = reimburseRecordLogSer.findByCis(rrLogDTO);
        if (rrlogList != null && rrlogList.size() > 0) {
            reimburseRecordLogSer.remove(rrlogList);
        }
        //删除报销审核人员审核记录
        ReimburseAuditLogDTO raLogDTO = new ReimburseAuditLogDTO();
        raLogDTO.getConditions().add(Restrict.eq("reimrecordId", id));
        List<ReimburseAuditLog> ralogList = reimburseAuditLogSer.findByCis(raLogDTO);
        if (ralogList != null && ralogList.size() > 0) {
            reimburseAuditLogSer.remove(ralogList);
        }

        //还原报销单号为解冻状态
        String reimNumber = temp.getReimNumber();
        FinoddinforDTO finoddinforDTO = new FinoddinforDTO();
        finoddinforDTO.getConditions().add(Restrict.eq("runNum", reimNumber));
        List<Finoddinfor> finoddinforList = finoddinforSer.findByCis(finoddinforDTO);
        if (finoddinforList != null && finoddinforList.size() > 0) {
            finoddinforList.stream().forEach(str -> {
                str.setStatus(Status.THAW);
            });
            finoddinforSer.update(finoddinforList);
        }


        super.remove(id);


    }


    @Override
    public Long countErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        String[] fields = new String[]{"count"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT count(*) as count ")
                .append(" FROM lendreimbursement_reimburserecord re1")
                .append(" RIGHT JOIN lendreimbursement_reimburserecord r2 ON r2.id = re1.id ");
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and r2.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and r2.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and r2.occureDate = '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        sql = addConditionTOReimError(reimburseRecordDTO, sql);
        sql.append(" WHERE re1.chargerAuditStatus = '不通过'  or re1.reimStatus  = 6 or re1.accountCheckPassOr  = '否' ");

        List<ReimburseRecordBO> count = super.findBySql(sql.toString(), ReimburseRecordBO.class, fields);
        return count != null && count.size() > 0 ? count.get(0).getCount().longValue() : 0L;
    }

    @Override
    public List<ReimburseRecordBO> listErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        String[] fields = new String[]{"id", "AccountFlag", "addContent", "area", "attender", "auditAdvice",
                "budgetPayTime", "charger", "chargerAuditStatus", "chargerAuditTime", "commitDate",
                "dayTask", "editDate", "filler", "firstSubject", "noTicketRemark", "occureDate", "payCondition",
                "payOrigin", "payPlan", "payTime", "plainInfo", "project", "projectGroup", "receiveTicketCon",
                "receiveTicketTime", "receiveTicketer", "reimMoney", "reimNumber", "reimStatus", "reimer", "reimerRemark",
                "secondSubject", "summary", "thirdSubject", "ticketCondition", "ticketNumber", "ticketQuantity", "receiveTicketCheck"
        };
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT re1.id , re1.AccountFlag , re1.addContent , re1.area , re1.attender , re1.auditAdvice, ")
                .append(" re1.budgetPayTime , re1.charger , re1.chargerAuditStatus , re1.chargerAuditTime , re1.commitDate , ")
                .append(" re1.dayTask , re1.editDate , re1.filler,re1.firstSubject , re1.noTicketRemark, re1.occureDate , re1.payCondition, ")
                .append(" re1.payOrigin , re1.payPlan , re1.payTime , re1.plainInfo , re1.project , re1.projectGroup,re1.receiveTicketCon, ")
                .append(" re1.receiveTicketTime, re1.receiveTicketer , re1.reimMoney , re1.reimNumber , re1.reimStatus , re1.reimer , re1.reimerRemark , ")
                .append(" re1.secondSubject , re1.summary , re1.thirdSubject , re1.ticketCondition ,re1.ticketNumber , re1.ticketQuantity ,re1.receiveTicketCheck ")
                .append(" FROM lendreimbursement_reimburserecord re1")
                .append(" RIGHT JOIN lendreimbursement_reimburserecord r2 ON r2.id = re1.id ");
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and r2.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and r2.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and r2.occureDate = '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        sql = addConditionTOReimError(reimburseRecordDTO, sql);

        sql.append(" WHERE re1.chargerAuditStatus = '不通过'  or re1.reimStatus  = 6 or re1.accountCheckPassOr  = '否'  ");
        sql.append(" order by re1.modifyTime desc ");
        sql.append(" limit " + (reimburseRecordDTO.getPage()) + "," + reimburseRecordDTO.getLimit() + " ");
        List<ReimburseRecord> list = super.findBySql(sql.toString(), ReimburseRecord.class, fields);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(list, ReimburseRecordBO.class);

        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO editErrorRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {

        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        if (StringUtils.isBlank(reimburseRecordTO.getReimer())) {
            throw new SerException("报销人不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if ("否".equals(temp.getAccountFlag())) {
            throw new SerException("此单已不给报销了，不能进行编辑");
        }
        //有权限的人才可以对别人（非自己的）的单进行编辑
        checkAddAndEditPermission("reimApplyError-Edit", userToken, userBO, temp);


        //修改报销记录
        addRecordLog(reimburseRecordTO, temp);

        temp.setReimer(reimburseRecordTO.getReimer());
        temp.setCharger(reimburseRecordTO.getCharger());
        temp.setAttender(reimburseRecordTO.getAttender());
        temp.setArea(reimburseRecordTO.getArea());
        temp.setProject(reimburseRecordTO.getProject());
        temp.setOccureDate(LocalDate.parse(reimburseRecordTO.getOccureDate()));
        temp.setTicketQuantity(reimburseRecordTO.getTicketQuantity());
//        temp.setFinanceSubject(reimburseRecordTO.getFinanceSubject());
        temp.setFirstSubject(reimburseRecordTO.getFirstSubject());
        temp.setSecondSubject(reimburseRecordTO.getSecondSubject());
        temp.setThirdSubject(reimburseRecordTO.getThirdSubject());
        temp.setDayTask(reimburseRecordTO.getDayTask());
        temp.setAddContent(reimburseRecordTO.getAddContent());
        temp.setPlainInfo(reimburseRecordTO.getPlainInfo());
        temp.setReimerRemark(reimburseRecordTO.getReimerRemark());
        temp.setSummary(reimburseRecordTO.getSummary());
        temp.setModifyTime(LocalDateTime.now());
        temp.setCommitDate(LocalDate.now());
        temp.setReimStatus(ReimStatus.NONE);
        temp.setChargerAuditStatus("");
        temp.setAuditAdvice("");
        temp.setChargerAuditTime(null);
        temp.setChargerCongelTime(null);
        temp.setChargeCongleAdvice("");

        super.update(temp);

        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

//        reimburseRecordDTO.getConditions().add(Restrict.isNull("chargerAuditStatus"));
//        reimburseRecordDTO.getConditions().add(Restrict.or("chargerAuditStatus", null));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 0}));
//        reimburseRecordDTO.getConditions().add(Restrict.or("ticketCondition", "否"));


        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }

        reimburseRecordDTO = addCondition(reimburseRecordDTO);

        Long count = super.count(reimburseRecordDTO);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        reimburseRecordDTO.getConditions().add(Restrict.isNull("chargerAuditStatus"));
//        reimburseRecordDTO.getConditions().add(Restrict.or("chargerAuditStatus", null));

        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 0}));
//        reimburseRecordDTO.getConditions().add(Restrict.or("ticketCondition", "否"));

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        reimburseRecordDTO = addCondition(reimburseRecordDTO);

        reimburseRecordDTO.getSorts().add("createTime=desc");
        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO, true);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(list, ReimburseRecordBO.class);
        return boList;
    }


    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO auditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
//        List<PositionDetailBO> positionBOList = positionDetailUserAPI.findPositionByUser(userBO.getId());
//
//        RpcTransmit.transmitUserToken(userToken);
//        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
//        RpcTransmit.transmitUserToken(userToken);
//        PositionBO positionBO = null;
//        if (udetailBO != null) {
//            positionBO = positionAPI.findById(udetailBO.getPositionId());
//            RpcTransmit.transmitUserToken(userToken);
//        }

        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if (!userName.equals(temp.getCharger()) && !userName.equals("admin")) {
            throw new SerException("您不是负责人，不能审核");
        }

        if (StringUtils.isBlank(reimburseRecordTO.getChargerAuditStatus()) &&
                StringUtils.isBlank(reimburseRecordTO.getTicketCondition())) {
            throw new SerException("负责人审核是否通过、是否有发票不能为空");
        }

        if (!"通过".equals(reimburseRecordTO.getChargerAuditStatus()) && !"不通过".equals(reimburseRecordTO.getChargerAuditStatus())) {
            throw new SerException("请标准填写负责人审核是否通过(通过/不通过)");
        }
        if (!"是".equals(reimburseRecordTO.getTicketCondition()) && !"否".equals(reimburseRecordTO.getTicketCondition())) {
            throw new SerException("请标准填写是否有发票");
        }

        temp.setChargerAuditStatus(reimburseRecordTO.getChargerAuditStatus());
        temp.setChargerAuditTime(LocalDate.now());
        temp.setTicketCondition(reimburseRecordTO.getTicketCondition());
        temp.setAuditAdvice(reimburseRecordTO.getAuditAdvice());
        if ("通过".equals(reimburseRecordTO.getChargerAuditStatus())) {
            temp.setReimStatus(ReimStatus.CHARGEPASS);
        } else if ("不通过".equals(reimburseRecordTO.getChargerAuditStatus())) {
            temp.setReimStatus(ReimStatus.CHARGENOTPASS);

            //把寄件信息清空
            temp.setSendDate(null);
            temp.setSendRecevier("");
            temp.setReceiveArea("");
            temp.setReceiveAddr("");
            temp.setSendCondition("");
            temp.setSender("");
        }
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);

        //填审核日志表,负责人审核部改变日志表
//        ReimburseAuditLog reimburseAuditLog = new ReimburseAuditLog();
//        reimburseAuditLog.setUserName(userName);
//        reimburseAuditLog.setEmpNum(userBO.getEmployeeNumber());
//        reimburseAuditLog.setAuditStatus("通过".equals(reimburseRecordTO.getChargerAuditStatus()) ? "负责人通过" : "负责人不通过");
//        reimburseAuditLog.setAuditTime(LocalDate.now());
//        reimburseAuditLog.setContent(reimburseRecordTO.getAuditAdvice());
//        reimburseAuditLog.setPosition(positionBO != null ? positionBO.getName() : "");
//        reimburseAuditLog.setReimrecordId(reimburseRecordTO.getId());
//        reimburseAuditLog.setCreateTime(LocalDateTime.now());
//        reimburseAuditLog.setModifyTime(LocalDateTime.now());
//        reimburseAuditLogSer.save(reimburseAuditLog);

        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO congelAuditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
//        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
//        RpcTransmit.transmitUserToken(userToken);
//        PositionBO positionBO = null;
//        if (udetailBO != null) {
//            positionBO = positionAPI.findById(udetailBO.getPositionId());
//            RpcTransmit.transmitUserToken(userToken);
//        }

        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if (!userName.equals(temp.getCharger())) {
            throw new SerException("您不是负责人，不能进行确认冻结操作");
        }

        if (!ReimStatus.CONGEL.equals(temp.getReimStatus())) {
            throw new SerException("改记录没有进行申请冻结，现在不能进行确认冻结操作");
        }


        if (StringUtils.isBlank(reimburseRecordTO.getSureCongel())) {
            throw new SerException("是否确认冻结不能为空");
        }
        if (!"是".equals(reimburseRecordTO.getSureCongel()) && !"否".equals(reimburseRecordTO.getSureCongel())) {
            throw new SerException("是否确认冻结只能填写是或否");
        }


        //记录确认冻结前负责人审核的情况
        temp.setChargerCongelTime(temp.getChargerAuditTime());
        temp.setChargeCongleAdvice(temp.getAuditAdvice());

        //进行是否确认冻结
        if ("是".equals(reimburseRecordTO.getSureCongel())) {
            temp.setChargerAuditStatus("不通过");
            temp.setChargerAuditTime(LocalDate.now());
            temp.setReimStatus(ReimStatus.CHARGECONGEL);
        } else if ("否".equals(reimburseRecordTO.getSureCongel())) {
            temp.setChargerAuditStatus("通过");
            temp.setChargerAuditTime(LocalDate.now());
            temp.setReimStatus(ReimStatus.CHARGEPASS);
        }

        temp.setAuditAdvice(temp.getAuditAdvice());
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);

        //填审核日志表，负责人冻结不改变日志表
//        ReimburseAuditLog reimburseAuditLog = new ReimburseAuditLog();
//        reimburseAuditLog.setUserName(userName);
//        reimburseAuditLog.setEmpNum(userBO.getEmployeeNumber());
//        reimburseAuditLog.setAuditStatus("不通过");
//        reimburseAuditLog.setAuditTime(LocalDate.now());
//        reimburseAuditLog.setContent("冻结");
//        reimburseAuditLog.setPosition(positionBO != null ? positionBO.getName() : "");
//        reimburseAuditLog.setReimrecordId(reimburseRecordTO.getId());
//        reimburseAuditLog.setCreateTime(LocalDateTime.now());
//        reimburseAuditLog.setModifyTime(LocalDateTime.now());
//        reimburseAuditLogSer.save(reimburseAuditLog);

        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO sendRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("报销id不能为空");
        }
        if (StringUtils.isBlank(reimburseRecordTO.getTicketCondition())) {
            throw new SerException("是否有单据(发票)不能为空");
        }
        if (StringUtils.isNotBlank(reimburseRecordTO.getTicketCondition())
                && (StringUtils.isBlank(reimburseRecordTO.getSendRecevier())
                || StringUtils.isBlank(reimburseRecordTO.getSendDate()) || StringUtils.isBlank(reimburseRecordTO.getReceiveAddr()))) {
            throw new SerException("寄件失败，请把寄件信息填全");
        }
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        ReimburseRecord temp = BeanTransform.copyProperties(reimburseRecordTO, ReimburseRecord.class, true);
        ReimburseRecord reimburseRecord = super.findById(reimburseRecordTO.getId());
        reimburseRecord.setTicketCondition(reimburseRecordTO.getTicketCondition());
        reimburseRecord.setSendDate(temp.getSendDate());
        reimburseRecord.setSendRecevier(reimburseRecordTO.getSendRecevier());
        reimburseRecord.setReceiveArea(reimburseRecordTO.getReceiveArea());
        reimburseRecord.setReceiveAddr(reimburseRecordTO.getReceiveAddr());
        reimburseRecord.setSendCondition(reimburseRecordTO.getSendCondition());
        reimburseRecord.setSender(userName);
        reimburseRecord.setModifyTime(LocalDateTime.now());

        super.update(reimburseRecord);

        return BeanTransform.copyProperties(reimburseRecord, ReimburseRecordBO.class);
    }

    @Override
    public Long countAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        //当前用户审核分析报销日志
        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.eq("reimStatus", 1));
        dto.getConditions().add(Restrict.eq("ticketCondition", "是"));
        dto.getConditions().add(Restrict.eq("analisisIsAll", false));
        dto.getSorts().add("createTime=desc");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            dto.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }


        Long count = super.count(dto);
        return count;
    }


    @Override
    public List<ReimburseRecordBO> listAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        //负责人审核通过的记录,且未全部分析完的
        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.eq("reimStatus", 1));
        dto.getConditions().add(Restrict.eq("ticketCondition", "是"));
        dto.getConditions().add(Restrict.eq("analisisIsAll", false));
        dto.getSorts().add("createTime=desc");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.between("occureDate", new String[]{reimburseRecordDTO.getStartTime(), reimburseRecordDTO.getEndTime()}));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            dto.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }

        dto = addCondition(dto);
        dto.getSorts().add("createTime=desc");

        List<ReimburseRecord> recordBOList = super.findByCis(dto, true);
        List<ReimburseRecordBO> recordBOList1 = BeanTransform.copyProperties(recordBOList, ReimburseRecordBO.class);
        return recordBOList1;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO analisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
//        checkPermission();
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if (temp.getReimStatus().getCode() == 0) {
            throw new SerException("负责人还未审核");
        }
        if (temp.getReimStatus().getCode() == 6) {
            throw new SerException("负责人还未确认冻结");
        }

        UserBO userBO = userAPI.currentUser();


        //审核日志表
        ReimburseAuditLogDTO reimburseAuditLogDTO = new ReimburseAuditLogDTO();
        reimburseAuditLogDTO.getConditions().add(Restrict.eq("reimrecordId", reimburseRecordTO.getId()));
        reimburseAuditLogDTO.getConditions().add(Restrict.eq("empNum", userBO.getEmployeeNumber()));
        List<ReimburseAuditLog> listReimAuditLog = reimburseAuditLogSer.findByCis(reimburseAuditLogDTO);

        if (listReimAuditLog != null && listReimAuditLog.size() > 0) {
            if (StringUtils.isBlank(reimburseRecordTO.getChargerAuditStatus()) ||
                    (reimburseRecordTO.getChargerAuditStatus().equals("不通过") && reimburseRecordTO.getChargerAuditStatus().equals("通过"))) {
                throw new SerException("分析人员只能选择通过或不通过二个状态,reimStatus");
            }
            listReimAuditLog.stream().forEach(str -> {
                str.setContent("分析意见:" + reimburseRecordTO.getAuditAdvice());
                str.setAuditTime(LocalDate.now());
                str.setModifyTime(LocalDateTime.now());
                if (StringUtils.isNotBlank(reimburseRecordTO.getChargerAuditStatus()) && reimburseRecordTO.getChargerAuditStatus().equals("通过")) {
                    str.setAuditStatus("分析通过");
                } else if (StringUtils.isNotBlank(reimburseRecordTO.getChargerAuditStatus()) && reimburseRecordTO.getChargerAuditStatus().equals("不通过")) {
                    str.setAuditStatus("分析不通过");
                }
            });
            reimburseAuditLogSer.update(listReimAuditLog);
//            }
        } else if (listReimAuditLog == null || listReimAuditLog.size() <= 0) {
            //先去报销分析人员里面看一下是不是有设置分析人员
            ReimburseAnalisisorDTO analisisorDTO = new ReimburseAnalisisorDTO();
            List<ReimburseAnalisisor> listReimAuditor = reimburseAnalisisorSer.findByCis(analisisorDTO);
            if (listReimAuditor == null || listReimAuditor.size() < 0) {
                throw new SerException("还没有可以分析的分析人员，请去'报销分析人员设置'里面去设置可以分析的人员,再重新添加报销数据");
            } else {
                throw new SerException("您不是该条记录的分析人员，不可以分析");
            }
        }

        //查看一下规定的分析人员是否全部分析完了，若分析完了，就把报销表里面的“isAnalisisAll”改为true
        ReimburseAuditLogDTO checkAuditLogDTO = new ReimburseAuditLogDTO();
        checkAuditLogDTO.getConditions().add(Restrict.eq("reimrecordId", reimburseRecordTO.getId()));
        checkAuditLogDTO.getConditions().add(Restrict.eq("auditStatus", "未处理"));
        List<ReimburseAuditLog> listAuditLog = reimburseAuditLogSer.findByCis(checkAuditLogDTO);
        if (listAuditLog == null || listAuditLog.size() <= 0) {
            temp.setAnalisisIsAll(true);
            super.update(temp);
        }

        temp.setReimStatus(ReimStatus.CHARGEPASS);
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO congelAnalisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
//        checkPermission();
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }


        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();


        //审核日志记录
        //审核日志表
        ReimburseAuditLogDTO reimburseAuditLogDTO = new ReimburseAuditLogDTO();
        reimburseAuditLogDTO.getConditions().add(Restrict.eq("reimrecordId", reimburseRecordTO.getId()));
        reimburseAuditLogDTO.getConditions().add(Restrict.eq("userName", userName));
        List<ReimburseAuditLog> listReimAuditLog = reimburseAuditLogSer.findByCis(reimburseAuditLogDTO);
        if (listReimAuditLog == null || listReimAuditLog.size() <= 0) {
            //先去报销分析人员里面看一下是不是有设置分析人员
            ReimburseAnalisisorDTO analisisorDTO = new ReimburseAnalisisorDTO();
            List<ReimburseAnalisisor> listReimAuditor = reimburseAnalisisorSer.findByCis(analisisorDTO);
            if (listReimAuditor == null || listReimAuditor.size() < 0) {
                throw new SerException("还没有可以分析的分析人员，请去'报销分析人员设置'里面去设置可以分析的人员,再重新添加报销数据");
            } else {
                throw new SerException("您不是该条记录的分析人员，不可以分析");
            }
        }

        for (ReimburseAuditLog log : listReimAuditLog) {
            if (userName.equals(log.getUserName())) {
                log.setUserName(userName);
                log.setEmpNum(userBO.getEmployeeNumber());
                log.setAuditTime(LocalDate.now());
                log.setReimrecordId(reimburseRecordTO.getId());
                log.setAuditStatus("申请冻结");
                log.setContent(reimburseRecordTO.getAuditAdvice());
                log.setModifyTime(LocalDateTime.now());
                reimburseAuditLogSer.update(log);
            }
        }

        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setReimStatus(ReimStatus.CONGEL);
        temp.setModifyTime(LocalDateTime.now());

        //查看一下规定的分析人员是否全部分析完了，若分析完了，就把报销表里面的“isAnalisisAll”改为true
        ReimburseAuditLogDTO checkAuditLogDTO = new ReimburseAuditLogDTO();
        checkAuditLogDTO.getConditions().add(Restrict.eq("reimrecordId", reimburseRecordTO.getId()));
        checkAuditLogDTO.getConditions().add(Restrict.eq("auditStatus", "未处理"));
        List<ReimburseAuditLog> listAuditLog = reimburseAuditLogSer.findByCis(checkAuditLogDTO);
        if (listAuditLog == null || listAuditLog.size() <= 0) {
            temp.setAnalisisIsAll(true);
        }

        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

//        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        //查询审核分析表,如果已分析了的，就有
        String[] fields = new String[]{"count"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT count(*) as count ")
                .append(" from lendreimbursement_reimburserecord  ")
                .append(" where id  IN ( ")
                .append(" SELECT record.id FROM lendreimbursement_reimburserecord record ")
                .append(" INNER JOIN lendreimbursement_reimburseauditlog log ")
                .append("  ON record.id = log.reimrecordId AND log.auditStatus in( '申请冻结','分析通过','分析不通过') ")
                .append(" and log.userName = '" + userBO.getUsername() + "' ")
                .append(" ) and reimStatus = 1 AND ticketCondition = '是' ");
        //and reimer = '' and reimNumber = '' and occureDate BETWEEN '' and ''
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }
        logger.info("总条数" + sql);

        List<ReimburseRecordBO> count = super.findBySql(sql.toString(), ReimburseRecordBO.class, fields);
        return count != null ? count.size() : 0L;
    }

    @Override
    public List<ReimburseRecordBO> listHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
        //负责人审核通过的记录
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        //查询审核分析表,如果已分析了的，就会有
        String[] fields = new String[]{"id", "AccountFlag", "addContent", "area", "attender", "auditAdvice",
                "budgetPayTime", "charger", "chargerAuditStatus", "chargerAuditTime", "commitDate",
                "dayTask", "editDate", "filler", "firstSubject", "noTicketRemark", "occureDate", "payCondition",
                "payOrigin", "payPlan", "payTime", "plainInfo", "project", "projectGroup", "receiveTicketCon",
                "receiveTicketTime", "receiveTicketer", "reimMoney", "reimNumber", "reimStatus", "reimer", "reimerRemark",
                "secondSubject", "summary", "thirdSubject", "ticketCondition", "ticketNumber", "ticketQuantity", "receiveTicketCheck"
        };
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT id , AccountFlag , addContent , area , attender , auditAdvice, ")
                .append(" budgetPayTime , charger , chargerAuditStatus , chargerAuditTime , commitDate , ")
                .append(" dayTask , editDate , filler,firstSubject , noTicketRemark, occureDate , payCondition, ")
                .append(" payOrigin , payPlan , payTime , plainInfo , project , projectGroup,receiveTicketCon, ")
                .append(" receiveTicketTime, receiveTicketer , reimMoney , reimNumber , reimStatus , reimer , reimerRemark , ")
                .append(" secondSubject , summary , thirdSubject , ticketCondition ,ticketNumber , ticketQuantity ,receiveTicketCheck ")
                .append(" from lendreimbursement_reimburserecord  ")
                .append(" where id IN ( ")
                .append(" SELECT record.id FROM lendreimbursement_reimburserecord record ")
                .append(" INNER JOIN lendreimbursement_reimburseauditlog log ")
                .append("  ON record.id = log.reimrecordId AND log.auditStatus in( '申请冻结','分析通过','分析不通过') ")
                .append(" and log.userName = '" + userBO.getUsername() + "' ")
                .append(" ) and reimStatus = 1 AND ticketCondition = '是' ");
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }
        sql.append(" order by modifyTime desc ");
        sql.append(" limit " + (reimburseRecordDTO.getPage()) + "," + reimburseRecordDTO.getLimit() + " ");
        logger.info("分析列表" + sql);
        List<ReimburseRecordBO> list = super.findBySql(sql.toString(), ReimburseRecordBO.class, fields);
        return list;

    }

    @Override
    public Long countAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        reimburseRecordDTO.getConditions().equals(Restrict.eq("chargerAuditStatus", "通过"));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1, 3, 4}));
        reimburseRecordDTO.getConditions().add(Restrict.eq("ticketCondition", "是"));
//        reimburseRecordDTO.getConditions().add(Restrict.isNull("receiveTicketCheck"));
//        reimburseRecordDTO.getConditions().add(Restrict.eq("receiveTicketCheck", "否"));
        reimburseRecordDTO.getConditions().add(Restrict.isNull("accountCheckPassOr"));
        reimburseRecordDTO.getConditions().add(Restrict.eq("accountCheckPassOr", "否"));

        reimburseRecordDTO = addCondition(reimburseRecordDTO);

        Long count = super.count(reimburseRecordDTO);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        reimburseRecordDTO.getConditions().equals(Restrict.eq("chargerAuditStatus", "通过"));
//        checkPermission();
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1, 3, 4}));
        reimburseRecordDTO.getConditions().add(Restrict.eq("ticketCondition", "是"));
// //       reimburseRecordDTO.getConditions().add(Restrict.isNull("receiveTicketCheck"));
// //       reimburseRecordDTO.getConditions().add(Restrict.or("receiveTicketCheck", "否"));
        reimburseRecordDTO.getConditions().add(Restrict.isNull("accountCheckPassOr"));
        reimburseRecordDTO.getConditions().add(Restrict.or("accountCheckPassOr", "否"));

        reimburseRecordDTO = addCondition(reimburseRecordDTO);

        reimburseRecordDTO.getSorts().add("createTime=desc");
        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO, true);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(list, ReimburseRecordBO.class);
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO recieveTicketCondition(ReimburseRecordTO reimburseRecordTO) throws SerException {
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        if (StringUtils.isBlank(reimburseRecordTO.getAccountCheckPassOr())) {
            throw new SerException("是否核对通过（是/否）不能为空");
        }
        //若审核分析表的分析人员全部分析完才能使用这个功能
        //查询是否全部人已经分析完
        String[] fields = new String[]{"count"};
        StringBuffer sql = new StringBuffer("");
        sql.append("select count(*) as count ")
                .append(" from  lendreimbursement_reimburseauditlog ")
                .append("  WHERE reimrecordId = '" + reimburseRecordTO.getId() + "' and auditStatus ='未处理' ");

        List<ReimburseAuditLogBO> audirLogBOList = reimburseAuditLogSer.findBySql(sql.toString(), ReimburseAuditLogBO.class, fields);

        if (audirLogBOList != null && audirLogBOList.size() > 0) {
            if (audirLogBOList.get(0).getCount() > 0) {
                throw new SerException("规定的运营分析人员还没有全部分析完，不能确认收到单据，请所有分析人员先分析完该条数据");
            }
        }

//        if (StringUtils.isBlank(reimburseRecordTO.getReceiveTicketCheck())) {
//            throw new SerException("是否收到单据不能为空");
//        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setReceiveTicketer(reimburseRecordTO.getReceiveTicketer());
        temp.setReceiveTicketCon(reimburseRecordTO.getReceiveTicketCon());
        temp.setReceiveTicketTime(LocalDate.parse(reimburseRecordTO.getReceiveTicketTime()));
//        if ("是".equals(reimburseRecordTO.getReceiveTicketCheck())) {
//            temp.setReceiveTicketCheck("是");
//        } else if ("否".equals(reimburseRecordTO.getReceiveTicketCheck())) {
//            temp.setReceiveTicketCheck("否");
//        }
        if ("是".equals(reimburseRecordTO.getAccountCheckPassOr())) {
            temp.setAccountCheckPassOr("是");
        } else if ("否".equals(reimburseRecordTO.getAccountCheckPassOr())) {
            temp.setAccountCheckPassOr("否");
        }
        temp.setReceiveTicketCheck(temp.getTicketCondition().equals("是") ? "是" : "否");
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {


        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
//        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
        //帐务核对通过
        dto.getConditions().add(Restrict.eq("accountCheckPassOr", "是"));
//        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
        dto.getConditions().add(Restrict.eq("reimStatus", 1));
        dto.getSorts().add("modifyTime=desc");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            dto.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }


        dto = addCondition(dto);

        Long count = super.count(dto);
        return count;

    }

    @Override
    public Long countWaitPayCJH(ReimburseRecordDTO reimburseRecordDTO) throws SerException {


        ReimburseRecordDTO dto = reimburseRecordDTO;
        //不等于
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
//        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
        //帐务核对通过
        dto.getConditions().add(Restrict.eq("accountCheckPassOr", "是"));
//        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
        dto.getConditions().add(Restrict.eq("reimStatus", 1));
        dto.getSorts().add("modifyTime=desc");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            dto.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        Long count = super.count(dto);
        return count;

    }

    @Override
    public List<ReimburseRecordBO> listWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        ReimburseRecordDTO dto = reimburseRecordDTO;
        //不等于
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
//        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
        //帐务核对通过
        dto.getConditions().add(Restrict.eq("accountCheckPassOr", "是"));
//        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
        dto.getConditions().add(Restrict.eq("reimStatus", 1));
        dto.getSorts().add("modifyTime=desc");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            dto.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        dto = addCondition(dto);

        List<ReimburseRecord> recordList = super.findByCis(dto, true);
        return BeanTransform.copyProperties(recordList, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO prePay(ReimburseRecordTO reimburseRecordTO) throws SerException {
        if (reimburseRecordTO.getReimNumbers() == null || reimburseRecordTO.getReimNumbers().length <= 0) {
            throw new SerException("报销单号不能为空，至少要有一个");
        }
        String[] reimNumbers = reimburseRecordTO.getReimNumbers();

        ReimburseRecordDTO dto = new ReimburseRecordDTO();
        dto.getConditions().add(Restrict.in("reimNumber", reimNumbers));
        List<ReimburseRecord> recordList = super.findByCis(dto);

        recordList.stream().forEach(str -> {
            str.setBudgetPayTime(LocalDate.parse(reimburseRecordTO.getBudgetPayTime()));
            str.setPayPlan(reimburseRecordTO.getPayPlan());
            str.setModifyTime(LocalDateTime.now());
        });

        super.update(recordList);
        ReimburseRecord reimburseRecord = new ReimburseRecord();
        return BeanTransform.copyProperties(reimburseRecord, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO phonePrePay(ReimburseRecordTO reimburseRecordTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if (reimburseRecordTO.getReimNumbers() == null || reimburseRecordTO.getReimNumbers().length <= 0) {
            throw new SerException("报销单号不能为空，至少要有一个");
        }
        String[] reimNumbers = reimburseRecordTO.getReimNumbers();
        StringBuffer sb = new StringBuffer(" ");
        for (String str : reimNumbers) {
            sb.append("'" + str + "' , ");
        }
        ReimburseRecordDTO dto = new ReimburseRecordDTO();
        dto.getConditions().add(Restrict.in("reimNumber", StringUtils.substringBeforeLast(sb.toString(), ",")));
        List<ReimburseRecord> recordList = super.findByCis(dto);

        recordList.stream().forEach(str -> {
            str.setBudgetPayTime(LocalDate.parse(reimburseRecordTO.getBudgetPayTime()));
            str.setPayPlan(reimburseRecordTO.getPayPlan());
            str.setModifyTime(LocalDateTime.now());

            //当手机端上点击去付款时，说明帐务核对通过
            str.setAccountCheckPassOr("是");
            str.setReceiveTicketer(userBO.getUsername());
            str.setReceiveTicketCon(reimburseRecordTO.getReceiveTicketCon());
            str.setReceiveTicketTime(LocalDate.now());
        });

        super.update(recordList);
        ReimburseRecord reimburseRecord = new ReimburseRecord();
        return BeanTransform.copyProperties(reimburseRecord, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO waitPay(ReimburseRecordTO reimburseRecordTO) throws SerException {
//        checkPermission();
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setPayOrigin(reimburseRecordTO.getPayOrigin());
        temp.setPayCondition("是");
        temp.setPayTime(LocalDate.now());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO phoneWaitPay(PhoneReimbursePayTO phoneReimbursePayTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(phoneReimbursePayTO.getId())) {
            throw new SerException("id不能为空");
        }
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        ReimburseRecord temp = super.findById(phoneReimbursePayTO.getId());
        if (!phoneReimbursePayTO.getReimMoney().equals(temp.getReimMoney())) {
            throw new SerException("付款金额与报销金额相等，不给报销");
        }
        temp.setPayOrigin(phoneReimbursePayTO.getPayOrigin());
        temp.setPayCondition("是");
        temp.setPayTime(LocalDate.now());
        temp.setModifyTime(LocalDateTime.now());

        //当手机端上点击去付款时，说明帐务核对通过
        temp.setAccountCheckPassOr("是");
        temp.setReceiveTicketer(userBO.getUsername());
        temp.setReceiveTicketCon(phoneReimbursePayTO.getReceiveTicketCon());
        temp.setReceiveTicketTime(LocalDate.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countHasPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "是"));


        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        dto = addCondition(dto);

        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listHasPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.eq("payCondition", "是"));

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            dto.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        dto = addCondition(dto);

        dto.getSorts().add("createTime=desc");
        List<ReimburseRecord> recordList = super.findByCis(dto, true);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(recordList, ReimburseRecordBO.class);
        return boList;
    }


    @Override
    public List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {

        String userToken = RpcTransmit.getUserToken();
        if (StringUtils.isBlank(id)) {
            throw new SerException("生成记账凭证失败，id不能为空");
        }
        String userName = userAPI.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);

        ReimburseRecord reim = super.findById(id);
        String dates = String.valueOf(reim.getOccureDate());
        String stage = reim.getOccureDate().getYear() + "年第" + reim.getOccureDate().getMonthValue() + "期";
        Double ticketNum = reim.getTicketQuantity();

        List<AccountVoucherBO> list = new ArrayList<>();
        AccountVoucherBO accountVoucherBO = new AccountVoucherBO();
        accountVoucherBO.setWords(Words.PAY);
        accountVoucherBO.setDates(dates);
        accountVoucherBO.setStage(stage);
        accountVoucherBO.setTicketNum(ticketNum);
        accountVoucherBO.setTicketUser(userName);
        accountVoucherBO.setBorrowResion(reim.getSummary());
        accountVoucherBO.setSubject(reim.getFirstSubject() + "-" + reim.getSecondSubject() + "-" + reim.getThirdSubject());
//        accountVoucherBO.setSubject(reim.getFinanceSubject());
        accountVoucherBO.setBorrowMoney(reim.getReimMoney());
        accountVoucherBO.setLoanMoney(0d);
        list.add(accountVoucherBO);

        accountVoucherBO = new AccountVoucherBO();
        accountVoucherBO.setWords(Words.PAY);
        accountVoucherBO.setDates(dates);
        accountVoucherBO.setStage(stage);
        accountVoucherBO.setTicketNum(ticketNum);
        accountVoucherBO.setTicketUser(userName);
        accountVoucherBO.setBorrowResion(reim.getSummary());
        accountVoucherBO.setSubject(reim.getPayOrigin());
        accountVoucherBO.setBorrowMoney(0d);
        accountVoucherBO.setLoanMoney(reim.getReimMoney());
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

    //汇总已付款记录
    @Override
    public List<CollectReimerDataBO> collectLender(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectReimerDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"lender", "money"};
        //当没选择报销人和时间时，表头有：(报销人 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getReimer()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select reimer as lender , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by reimer");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getReimer()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选报销人时，表头有:(报销人/时间段/金额)
            fields = new String[]{"lender", "money"};
            sb = new StringBuffer("");
            sb.append("select reimer as lender, sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by reimer ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            collectDataBOList.stream().forEach(str -> {
                str.setReimDatePeriod("" + reimburseRecordDTO.getStartTime() + "到" + reimburseRecordDTO.getEndTime() + "");
            });

        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            //当有选报销人，没选日期时，表头有:(报销人/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where payCondition = '是' and reimer = '" + reimburseRecordDTO.getReimer().trim() + "'  ")
                    .append(" order by area desc , project desc ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期和报销人时，表头有:(报销人/时间/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "reimDate", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,occureDate as reimDate,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where reimer = '" + reimburseRecordDTO.getReimer().trim() + "'")
                    .append(" and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "'")
                    .append("  and  payCondition = '是' ")
                    .append(" order by area desc , project desc ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            if (collectDataBOList != null && collectDataBOList.size() > 0) {
                collectDataBOList.stream().forEach(str -> {
                    str.setReimDatePeriod(str.getReimDate());
                });
            }

        }

        return collectDataBOList;
    }

    @Override
    public List<CollectReimerDataBO> collectArea(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectReimerDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"area", "money"};
        //当没选择地区和时间时，表头有：(地区 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getArea()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select area , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by area");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getArea()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选地区时，表头有:(地区/时间段/金额)
            fields = new String[]{"area", "money"};
            sb = new StringBuffer("");
            sb.append("select area, sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by area ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            collectDataBOList.stream().forEach(str -> {
                str.setReimDatePeriod("" + reimburseRecordDTO.getStartTime() + "到" + reimburseRecordDTO.getEndTime() + "");
            });
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getArea()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            //当有选地区，没选日期时，表头有:(报销人/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where payCondition = '是' and area = '" + reimburseRecordDTO.getArea().trim() + "'  ")
                    .append("  order by firstSubject desc , secondSubject desc , thirdSubject desc  ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getArea()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期和地区时，表头有:(报销人/时间/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "reimDate", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,occureDate as reimDate,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where area = '" + reimburseRecordDTO.getArea().trim() + "'")
                    .append(" and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "'")
                    .append("  and  payCondition = '是' ")
                    .append(" order by firstSubject desc , secondSubject desc , thirdSubject desc ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            if (collectDataBOList != null && collectDataBOList.size() > 0) {
                collectDataBOList.stream().forEach(str -> {
                    str.setReimDatePeriod(str.getReimDate());
                });
            }
        }

        return collectDataBOList;
    }

    @Override
    public List<CollectReimerDataBO> collectFirstSubject(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectReimerDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"firstSubject", "money"};
        //当没选择一级科目和时间时，表头有：(一级科目 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select firstSubject , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by firstSubject");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选一级科目时，表头有:(一级科目/时间段/金额)
            fields = new String[]{"firstSubject", "money"};
            sb = new StringBuffer("");
            sb.append("select firstSubject  , sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by firstSubject ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            collectDataBOList.stream().forEach(str -> {
                str.setReimDatePeriod("" + reimburseRecordDTO.getStartTime() + "到" + reimburseRecordDTO.getEndTime() + "");
            });
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            //当有选一级科目，没选日期时，表头有:(报销人/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where payCondition = '是' and firstSubject = '" + reimburseRecordDTO.getFirstSubject().trim() + "'  ")
                    .append("  order by firstSubject desc , secondSubject desc , thirdSubject desc  ");

            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期和一级科目时，表头有:(报销人/时间/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "reimDate", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,occureDate as reimDate,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where firstSubject = '" + reimburseRecordDTO.getFirstSubject().trim() + "'")
                    .append(" and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "'")
                    .append("  and  payCondition = '是' ")
                    .append(" order by firstSubject desc , secondSubject desc , thirdSubject desc ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            if (collectDataBOList != null && collectDataBOList.size() > 0) {
                collectDataBOList.stream().forEach(str -> {
                    str.setReimDatePeriod(str.getReimDate());
                });
            }
        }

        return collectDataBOList;
    }

    @Override
    public List<CollectReimerDataBO> collectProjectName(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectReimerDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"projectName", "money"};
        //当没选择项目和时间时，表头有：(项目 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getProject()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select project as projectName , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by project");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getProject()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选项目时，表头有:(项目/时间段/金额)
            fields = new String[]{"projectName", "money"};
            sb = new StringBuffer("");
            sb.append("select project as projectName ,  sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by project ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            collectDataBOList.stream().forEach(str -> {
                str.setReimDatePeriod("" + reimburseRecordDTO.getStartTime() + "到" + reimburseRecordDTO.getEndTime() + "");
            });
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getProject()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            //当有选项目，没选日期时，表头有:(报销人/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where payCondition = '是' and project = '" + reimburseRecordDTO.getProject().trim() + "'  ")
                    .append("  order by firstSubject desc , secondSubject desc , thirdSubject desc  ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getProject()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期和项目时，表头有:(报销人/时间/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "reimDate", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,occureDate as reimDate,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where project = '" + reimburseRecordDTO.getProject().trim() + "'")
                    .append(" and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "'")
                    .append("  and  payCondition = '是' ")
                    .append(" order by firstSubject desc , secondSubject desc , thirdSubject desc ");
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);
            if (collectDataBOList != null && collectDataBOList.size() > 0) {
                collectDataBOList.stream().forEach(str -> {
                    str.setReimDatePeriod(str.getReimDate());
                });
            }
        }

        return collectDataBOList;
    }

    @Override
    public List<String> listAllUser() throws SerException {
        List<UserBO> list = userAPI.findAllUser();
        List<String> userList = list.stream().map(UserBO::getUsername).collect(Collectors.toList());
        return userList;
    }

    @Override
    public List<String> reimNumByPrepay() throws SerException {
        ReimburseRecordDTO dto = new ReimburseRecordDTO();
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
//        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
        //帐务核对通过
        dto.getConditions().add(Restrict.eq("accountCheckPassOr", "是"));
        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{1}));

        List<ReimburseRecord> recordList = super.findByCis(dto);
        List<String> list = new ArrayList<>();
        if (recordList != null && recordList.size() > 0) {
            list = recordList.stream().map(ReimburseRecord::getReimNumber).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public List<String> listFirstSubject() throws SerException {
        String[] fields = new String[]{"firstSubject"};
        String sql = " select firstSubject  from lendreimbursement_reimburserecord group by firstSubject ";
        List<ReimburseRecord> list = super.findBySql(sql, ReimburseRecord.class, fields);
        List<String> firstSubject = list.stream().map(ReimburseRecord::getFirstSubject).collect(Collectors.toList());
        return firstSubject;
    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        String sql = " select area  from lendreimbursement_reimburserecord group by area ";
        List<ReimburseRecord> list = super.findBySql(sql, ReimburseRecord.class, fields);
        List<String> area = list.stream().map(ReimburseRecord::getArea).collect(Collectors.toList());
        return area;
    }

    @Override
    public List<String> listProject() throws SerException {
        String[] fields = new String[]{"project"};
        String sql = " select project  from lendreimbursement_reimburserecord group by project ";
        List<ReimburseRecord> list = super.findBySql(sql, ReimburseRecord.class, fields);
        List<String> project = list.stream().map(ReimburseRecord::getProject).collect(Collectors.toList());
        return project;
    }

    @Override
    public List<String> listReimUser() throws SerException {
        String[] fields = new String[]{"reimer"};
        String sql = " select reimer  from lendreimbursement_reimburserecord group by reimer ";
        List<ReimburseRecord> list = super.findBySql(sql, ReimburseRecord.class, fields);
        List<String> project = list.stream().map(ReimburseRecord::getReimer).collect(Collectors.toList());
        return project;
    }

    /**
     * 等待付款导出
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    @Override
    public byte[] exportExcel(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            start = DateUtil.parseDate(reimburseRecordDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            end = DateUtil.parseDate(reimburseRecordDTO.getEndTime());
        }
        LocalDate budgetPayTime[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) || StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.between("occureDate", budgetPayTime));//借款时间段查询
        }
        reimburseRecordDTO.getConditions().add(Restrict.ne("payCondition", "是"));
//        reimburseRecordDTO.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));

        //帐务核对通过
        reimburseRecordDTO.getConditions().add(Restrict.eq("accountCheckPassOr", "是"));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1}));

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO);

        List<ReimburseRecordExcel> reimburseRecordExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ReimburseRecordExcel excel = BeanTransform.copyProperties(str, ReimburseRecordExcel.class, "reimStatus");
            excel.setReimStatus(ReimStatus.exportStrConvert(str.getReimStatus()));

            reimburseRecordExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(reimburseRecordExcels, excel);
        return bytes;
    }

    /**
     * 已付款记录导出
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    @Override
    public byte[] exportAlPayExcel(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            start = DateUtil.parseDate(reimburseRecordDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            end = DateUtil.parseDate(reimburseRecordDTO.getEndTime());
        }
        LocalDate budgetPayTime[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) || StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            reimburseRecordDTO.getConditions().add(Restrict.between("occureDate", budgetPayTime));//借款时间段查询
        }
        reimburseRecordDTO.getConditions().add(Restrict.eq("payCondition", "是"));

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO);

        List<ReimburseRecordExcel> reimburseRecordExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ReimburseRecordExcel excel = BeanTransform.copyProperties(str, ReimburseRecordExcel.class, "reimStatus");
            excel.setReimStatus(ReimStatus.exportStrConvert(str.getReimStatus()));

            reimburseRecordExcels.add(excel);
        });

        if (reimburseRecordExcels == null) {
            ReimburseRecordExcel excel = new ReimburseRecordExcel();
            reimburseRecordExcels.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(reimburseRecordExcels, excel);
        return bytes;
    }

    /**
     * chenjunhao
     * 等待付款导出cjh
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    @Override
    public List<ExportExcelTO> exportExcelCjh(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            start = DateUtil.parseDate(reimburseRecordDTO.getStartTime());
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            end = DateUtil.parseDate(reimburseRecordDTO.getEndTime());
        }
        LocalDate[] occureDate = new LocalDate[]{start, end};

//        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) || StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
        reimburseRecordDTO.getConditions().add(Restrict.between("occureDate", occureDate));
//        }
        reimburseRecordDTO.getConditions().add(Restrict.ne("payCondition", "是"));
//        reimburseRecordDTO.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));

        //帐务核对通过
        reimburseRecordDTO.getConditions().add(Restrict.eq("accountCheckPassOr", "是"));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1}));

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO);

        List<ExportExcelTO> exportExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ExportExcelTO excel = BeanTransform.copyProperties(str, ExportExcelTO.class, true);
            excel.setLendDate(DateUtil.dateToString(str.getOccureDate()));
            excel.setLendMoney(str.getReimMoney());
            excel.setPayDate(DateUtil.dateToString(str.getBudgetPayTime()));
            excel.setPayStatus(PayStatus.WAITPAY);
            exportExcels.add(excel);
        });
        return exportExcels;
    }

    @Override
    //chenjunhao
    public List<ReimburseRecordBO> listWaitPayCJH(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
//        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));

        //帐务核对通过
        dto.getConditions().add(Restrict.eq("accountCheckPassOr", "是"));
        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{1}));
        dto.getSorts().add("modifyTime=desc");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            dto.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            dto.getConditions().add(Restrict.eq("reimNumber", reimburseRecordDTO.getReimNumber()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getEndTime()));
        }

        List<ReimburseRecord> recordList = super.findByCis(dto, true);
        return BeanTransform.copyProperties(recordList, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    //chenjunhao
    public ReimburseRecordBO waitPayCJH(ReimburseRecordTO reimburseRecordTO) throws SerException {
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setPayOrigin(reimburseRecordTO.getPayOrigin());
        temp.setPayCondition("是");
        temp.setPayTime(LocalDate.now());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }


    @Override
    public List<ReimburseRecordBO> listAll(PhoneReimburseDTO phoneReimburseDTO) throws SerException {
        ReimPhoneSelectStatus reimPhoneSelectStatus = phoneReimburseDTO.getReimPhoneSelectStatus();
        ReimburseRecordDTO dto = new ReimburseRecordDTO();
        switch (reimPhoneSelectStatus) {
            case ALL:
                //全部

                break;
            case WAITAUDIT:
                //待审核（负责人未审核或分析人申请冻结后负责人还未确认冻结的情况）
                dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 0}));
//                dto.getConditions().add(Restrict.or("ticketCondition", "否"));

                break;
            case WAITANALISIS:
                //待分析(负责人审核通过)
                dto.getConditions().add(Restrict.eq("reimStatus", 1));
                dto.getConditions().add(Restrict.eq("ticketCondition", "是"));
                dto.getConditions().add(Restrict.eq("analisisIsAll", false));

                break;
            case WAITCHECK:
                //待核对(帐务核对和付款未通过或还未付款的)
                //负责人通过，且分析的人全部分析完，且还未付款,且帐务核对为null
                dto.getConditions().add(Restrict.eq("reimStatus", 1));
                dto.getConditions().add(Restrict.eq("analisisIsAll", true));
                dto.getConditions().add(Restrict.ne("payCondition", "是"));
                dto.getConditions().add(Restrict.isNull("accountCheckPassOr"));

                break;
            case HASREIM:
                //已报销
                dto.getConditions().add(Restrict.eq("payCondition", "是"));
                break;
            case WAITTHAW:
                //待解冻(负责人审核不通过/负责人确认冻结/付款核对不通过（accountCheckPassOr='否'这个是不可以给重新编辑，因为已经不给报销了）/分析人申请冻结（已经转入待审核里面）)
                dto.getConditions().add(Restrict.eq("chargerAuditStatus", "不通过"));
                dto.getConditions().add(Restrict.or("reimStatus", 6));
                dto.getConditions().add(Restrict.or("accountCheckPassOr", "否"));

                break;
        }
        dto.getSorts().add("modifyTime=desc");
        dto.setPage(phoneReimburseDTO.getPage() + 1);
        dto.setLimit(phoneReimburseDTO.getLimit());
        List<ReimburseRecord> list = super.findByPage(dto);
        List<ReimburseRecordBO> listBO = BeanTransform.copyProperties(list, ReimburseRecordBO.class);
        if (listBO != null && listBO.size() > 0) {
            for (ReimburseRecordBO str : listBO) {
                String payCondition = str.getPayCondition();
                String ticketCondition = str.getTicketCondition();
                Boolean analisisIsAll = str.getAnalisisIsAll();
                ReimStatus reimStatus = str.getReimStatus();
                String chargeAuditStatus = str.getChargerAuditStatus();
                String accountCheckPassOr = str.getAccountCheckPassOr();

                if (!"是".equals(payCondition)) {
                    if (!analisisIsAll) {
                        //待审核（负责人未审核或分析人申请冻结后负责人还未确认冻结的情况）
                        if ((ReimStatus.NONE.equals(reimStatus) || ReimStatus.CONGEL.equals(reimStatus)) || "否".equals(ticketCondition)) {
                            str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.WAITAUDIT);
                        }
                        //待分析(负责人审核通过)
                        if (ReimStatus.CHARGEPASS.equals(reimStatus) && "是".equals(ticketCondition)) {
                            str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.WAITANALISIS);
                        }
                        //   待解冻（负责人未通过）
                        if ("不通过".equals(chargeAuditStatus) || ReimStatus.CHARGECONGEL.equals(reimStatus)) {
                            //  待解冻 (负责人审核不通过/确认冻结/帐务核对不通过)
                            str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.WAITTHAW);
                        }
                    } else if (analisisIsAll) {
                        //待核对(帐务核对和付款未通过或还未付款的)
                        if (ReimStatus.CHARGEPASS.equals(reimStatus) && StringUtils.isBlank(accountCheckPassOr)) {
                            str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.WAITCHECK);
                        } else if (ReimStatus.CHARGEPASS.equals(reimStatus) && "否".equals(accountCheckPassOr)) {
                            str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.WAITTHAW);
                        } else if (ReimStatus.CONGEL.equals(reimStatus)) {
                            //已经分析完，但是是申请冻结状态
                            str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.WAITAUDIT);
                        }
//                        if ("不通过".equals(chargeAuditStatus) || ReimStatus.CHARGECONGEL.equals(reimStatus)
//                                || "否".equals(accountCheckPassOr)) {
//                            //待解冻 (负责人审核不通过/确认冻结/帐务核对不通过)
//                            str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.WAITTHAW);
//                        }
                    }

                } else if ("是".equals(payCondition)) {
                    //已报销
                    str.setReimPhoneSelectStatus(ReimPhoneSelectStatus.HASREIM);
                }
            }

        }
        return listBO;
    }

    @Override
    public ReimPhoneShowStatus phoneShowRight(ReimPhoneSelectStatus reimPhoneSelectStatus, String reimId) throws SerException {
        ReimPhoneShowStatus showStatus = ReimPhoneShowStatus.NONE;
        String userToken = RpcTransmit.getUserToken();
        ReimburseRecord record = super.findById(reimId);
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        String charger = record.getCharger();
        Boolean analisisIsAll = record.getAnalisisIsAll();//true/false
        String payCondition = record.getPayCondition();//是或否
        ReimStatus reimStatus = record.getReimStatus();//报销状态
        String accountCheckPassOr = record.getAccountCheckPassOr();//null/是/否

        switch (reimPhoneSelectStatus) {
            case ALL:
                //全部
                showStatus = ReimPhoneShowStatus.NONE;
                break;
            case WAITAUDIT:
                //待审核（负责人未审核或分析人申请冻结后负责人还未确认冻结的情况）
                if (null == record.getSendDate()) {
                    //说明没有寄件,而且是报销人身份
                    if (userName.toLowerCase().equals(record.getReimer())) {
                        showStatus = ReimPhoneShowStatus.GOSEND;
                    } else {
                        showStatus = ReimPhoneShowStatus.NONE;
                    }
                } else {
                    //已填寄件，则看是审核人还是非审核人
                    if ("admin".equals(userName.toLowerCase()) || userName.toLowerCase().equals(charger)) {
                        if (!reimStatus.equals(ReimStatus.CONGEL)) {
                            //负责人第一次审核
                            showStatus = ReimPhoneShowStatus.GOAUDIT;
                        } else if (reimStatus.equals(ReimStatus.CONGEL)) {
                            //负责人第二次审核，即是否确认冻结
                            showStatus = ReimPhoneShowStatus.CONGELAUDIT;
                        }
                    } else {
                        showStatus = ReimPhoneShowStatus.SENDDETAIL;
                    }
                }

                break;
            case WAITANALISIS:
                //待分析(负责人审核通过)
                //负责人通过，且分析的人还没有全部分析完
                //查审核日志表，有
                ReimburseAuditLogDTO reimburseAuditLogDTO = new ReimburseAuditLogDTO();
                reimburseAuditLogDTO.getConditions().add(Restrict.eq("reimrecordId", record.getId()));
                reimburseAuditLogDTO.getConditions().add(Restrict.eq("empNum", userBO.getEmployeeNumber()));
                List<ReimburseAuditLog> listReimAuditLog = reimburseAuditLogSer.findByCis(reimburseAuditLogDTO);

                //不给admin来分析
                if ((listReimAuditLog != null && listReimAuditLog.size() > 0) && !"admin".equals(userName.toLowerCase())) {
                    if (ReimStatus.CHARGEPASS.equals(reimStatus) && !analisisIsAll) {
                        showStatus = ReimPhoneShowStatus.GOANALISIS;
                    }
                } else {
                    showStatus = ReimPhoneShowStatus.NONE;
                }
                break;
            case WAITCHECK:
                //待核对(帐务核对和付款还未付款的或付款未通过（这个未通过的放到待解冻里面去了）)
                //负责人通过，且分析的人全部分析完，且还未付款,且帐务核对为null
                //有权限和admin
                Boolean flag = checkPermission("reim-accountCheckAndPay");
                if (flag) {
                    if (ReimStatus.CHARGEPASS.equals(reimStatus) && analisisIsAll && "否".equals(payCondition) && StringUtils.isBlank(accountCheckPassOr)) {
                        showStatus = ReimPhoneShowStatus.GOCHECK;
                    }
                } else {
                    showStatus = ReimPhoneShowStatus.NONE;
                }
                break;
            case HASREIM:
                //已报销
                if ("是".equals(payCondition)) {
                    showStatus = ReimPhoneShowStatus.NONE;
                }
                break;
            case WAITTHAW:
                //待解冻(负责人审核不通过/负责人确认冻结/付款核对不通过（accountCheckPassOr='否'这个是不可以给重新编辑，因为已经不给报销了）/分析人申请冻结（已经转入待审核里面）)
                if ("admin".equals(userName.toLowerCase()) || userName.equals(record.getReimer())) {
                    if (ReimStatus.CHARGENOTPASS.equals(reimStatus)) {
                        showStatus = ReimPhoneShowStatus.WAITTHAWEDIT;
                    }
                    if ("否".equals(accountCheckPassOr)) {
                        showStatus = ReimPhoneShowStatus.NONE;
                    }
                } else {
                    showStatus = ReimPhoneShowStatus.NONE;
                }
                break;
        }

        return showStatus;
    }


    /**
     * 下面的是图形化
     */
    /**
     * 处理dto添加用户名和时间范围
     *
     * @param reimburseRecordDTO
     * @param userName
     * @param startTime
     * @param endTime
     * @return
     */
    private ReimburseRecordDTO addQueryCon(ReimburseRecordDTO reimburseRecordDTO, String userName, LocalDateTime startTime, LocalDateTime endTime) {
        reimburseRecordDTO = new ReimburseRecordDTO();
        reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", userName));
        reimburseRecordDTO.getConditions().add(Restrict.between("createTime", new LocalDateTime[]{startTime, endTime}));
        return reimburseRecordDTO;
    }

    /**
     * 处理dto添加待审核报销记录
     *
     * @param reimburseRecordDTO
     * @return
     */
    private ReimburseRecordDTO addWaitAuditQueryCon(ReimburseRecordDTO reimburseRecordDTO) {
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 1, 0}));
        reimburseRecordDTO.getConditions().add(Restrict.eq("analisisIsAll", false));
        return reimburseRecordDTO;
    }

    /**
     * 处理dto添加待支付报销记录
     *
     * @param reimburseRecordDTO
     * @return
     */
    private ReimburseRecordDTO addWaitPayQueryCon(ReimburseRecordDTO reimburseRecordDTO) {
        reimburseRecordDTO.getConditions().add(Restrict.eq("reimStatus", 1));
        reimburseRecordDTO.getConditions().add(Restrict.eq("analisisIsAll", true));
        reimburseRecordDTO.getConditions().add(Restrict.ne("payCondition", "是"));
        reimburseRecordDTO.getConditions().add(Restrict.isNull("accountCheckPassOr"));
        return reimburseRecordDTO;
    }

    /**
     * 处理dto添加已还款报销记录
     *
     * @param reimburseRecordDTO
     * @return
     */
    private ReimburseRecordDTO addHasPayQueryCon(ReimburseRecordDTO reimburseRecordDTO) {
        reimburseRecordDTO.getConditions().add(Restrict.eq("payCondition", "是"));
        return reimburseRecordDTO;
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
        legendVO.setData(Arrays.asList("申报报销记录", "待审核", "待支付", "已支付"));
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
        titleVO.setText(userName + "报销指标金额统计");
        titleVO.setSubtext("时间: " + start + "至" + end);
        shapeBarVO.setReimShapeTitleVO( titleVO );
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
        xAxisVO.setData(Arrays.asList("申报报销记录", "待审核", "待支付", "已支付"));
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

        //计算某用户在这个时间段内的申报报销记录个数
        ReimShapeSeriesDataVO seriesDataVO = new ReimShapeSeriesDataVO();
        ReimShapeSeriesDataVO seriesData2VO = new ReimShapeSeriesDataVO();
        ReimburseRecordDTO reimburseRecordDTO = new ReimburseRecordDTO();
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        List<ReimburseRecord> listReim = super.findByCis(reimburseRecordDTO);
        seriesDataVO.setName("申报报销记录");
        seriesData2VO.setName("申报报销记录");
        Double tempReimMoney = 0d;
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            tempReimMoney = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
            seriesData2VO.setValue(String.valueOf(tempReimMoney));
            seriesDataVOBarList.add(tempReimMoney == null || tempReimMoney.equals(0d) ? 0d : tempReimMoney);

        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);
        //计算某用户在这个时间段内的待审核报销记录个数(包括负责人未审核/申请冻结/分析人员未分析完)
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        reimburseRecordDTO = addWaitAuditQueryCon(reimburseRecordDTO);
        listReim = super.findByCis(reimburseRecordDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("待审核");
        seriesData2VO.setName("待审核");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);


        //计算某用户在这个时间段内的待支付报销记录个数
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        reimburseRecordDTO = addWaitPayQueryCon(reimburseRecordDTO);
        listReim = super.findByCis(reimburseRecordDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("待支付");
        seriesData2VO.setName("待支付");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
            seriesData2VO.setValue(String.valueOf(reimMoneySum));
            seriesDataVOBarList.add(reimMoneySum == null || reimMoneySum.equals(0d) ? 0d : reimMoneySum);
        } else {
            seriesDataVO.setValue(String.valueOf(0));
            seriesData2VO.setValue(String.valueOf(0));
            seriesDataVOBarList.add(0d);
        }
        seriesDataVOList.add(seriesDataVO);
        seriesDataVO2List.add(seriesData2VO);
        //计算某用户在这个时间段内的已支付报销记录个数
        reimburseRecordDTO = addQueryCon(reimburseRecordDTO, userName, startTime, endTime);
        reimburseRecordDTO = addHasPayQueryCon(reimburseRecordDTO);
        listReim = super.findByCis(reimburseRecordDTO);
        seriesDataVO = new ReimShapeSeriesDataVO();
        seriesData2VO = new ReimShapeSeriesDataVO();
        seriesDataVO.setName("已支付");
        seriesData2VO.setName("已支付");
        if (listReim != null && listReim.size() > 0) {
            seriesDataVO.setValue(String.valueOf(listReim.size()));
            Double reimMoneySum = listReim.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
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
        titlePieVO.setText(reimburseShapeConDTO.getUserName() + "报销情况统计");
        titlePieVO.setSubtext("时间: " + startTime + "至" + endTime + "累计记录");
        reimShapeVO = echartPieObject(seriesDataVOList, titlePieVO,"单数");
        //echart饼状图形数据封装
        ReimShapeTitleVO titlePie2VO = new ReimShapeTitleVO();
        titlePie2VO.setText(reimburseShapeConDTO.getUserName() + "报销金额指标统计");
        titlePie2VO.setSubtext("时间: " + startTime + "至" + endTime + "报销金额");
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

        ReimburseRecordDTO dto = new ReimburseRecordDTO();
        //查询第一个时间所有申报报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        List<ReimburseRecord> list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第一个时间所有待审核报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addWaitAuditQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第一个时间所有待支付报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addWaitPayQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);

        //查询第一个时间所有已支付报销记录
        dto = addQueryCon(dto, userName, startMonStart, startMonEnd);
        dto = addHasPayQueryCon(dto);
        list = super.findByCis(dto);
        reimMoney = list.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //封装返回数据
        mixSeriesVO.setName(reimburseTrendShapeDTO.getStartTime());
        mixSeriesVO.setType("bar");
        mixSeriesVO.setBarWidth("40%");
        mixSeriesVO.setSeriesDataVOList(seriesDataList);
        mixSeriesVOList.add(mixSeriesVO);

        //处理第二个数据
        seriesDataList = new ArrayList<>();
        dto = null;
        ReimburseRecordDTO dto2 = new ReimburseRecordDTO();
        //查询第二个时间所有申报报销记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        List<ReimburseRecord> list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有待审核报销记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addWaitAuditQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有待支付报销记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addWaitPayQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
        seriesDataList.add(reimMoney);
        //查询第二个时间所有已支付报销记录
        dto2 = addQueryCon(dto2, userName, endMonStart, endMonEnd);
        dto2 = addHasPayQueryCon(dto2);
        list2 = super.findByCis(dto2);
        reimMoney = list2.stream().mapToDouble(ReimburseRecord::getReimMoney).sum();
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
        xaxisVO.setData(Arrays.asList("申报报销记录", "待审核", "待支付", "已支付"));
        xaxisVO.setAxisTickBarVO(new AxisTickBarVO(true));
        xaxisVO.setAxisPointerBarVO(new AxisPointerBarVO("shadow"));
        reimShapeMixVO.setXaxisVO(xaxisVO);
        reimShapeMixVO.setTitleVO(new ReimShapeTitleVO(userName + "的报销情况变化趋势图", reimburseTrendShapeDTO.getStartTime() + "至" + reimburseTrendShapeDTO.getEndTime(), "center", "45%"));
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

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimCompanyShapeDTO, ReimburseShapeConDTO.class,"serialVersionUID");
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
        //查询所有项目组的申报报销记录
        String[] fields = new String[]{"reimMoney", "projectGroup"};
        String sql = "select sum(reimMoney) as reimMoney , projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "' ";
        String sqlGroup = "  group by projectGroup ";
        String sqlQuery = sql + sqlGroup;
        List<ReimburseRecord> listReimCord = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的待审核
        sqlQuery = sql + " and reimStatus in (5,1,0) and analisisIsAll = 0 " + sqlGroup;
        List<ReimburseRecord> listReimWait = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的待支付
        sqlQuery = sql + " and reimStatus = 1 and analisisIsAll = 1 and payCondition = '是' and accountCheckPassOr is null " + sqlGroup;
        List<ReimburseRecord> listReimWaitPay = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的已支付
        sqlQuery = sql + " and payCondition = '是' " + sqlGroup;
        List<ReimburseRecord> listReimHasPay = super.findBySql(sqlQuery, ReimburseRecord.class, fields);

        //柱状图型数据封装
        List<ReimShapeBarSeriesVO> seriesDataList = TransformBarData(groupList, listReimCord,listReimWait, listReimWaitPay,listReimHasPay);
        reimCompanyShapeBarVO.setReimShapeTitleVO(new ReimShapeTitleVO("公司内部项目组特定指标统计表", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
        reimCompanyShapeBarVO.setToolTipVO(new ReimShapeBarToolTipVO("axis"));
        reimCompanyShapeBarVO.setLegendVO(new ReimShapeLegendVO(groupList));
        reimCompanyShapeBarVO.setBarGridVO(new ReimShapeBarGridVO("22%", "22%", "44%", true));
        reimCompanyShapeBarVO.setXaxisVO(new ReimShapeXaxisVO("category", "状态", Arrays.asList("申报报销记录", "待审核", "待支付", "已支付")));
        reimCompanyShapeBarVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)"));
        reimCompanyShapeBarVO.setSeriesVOList(seriesDataList);

        //饼型图形数据封装
        shapeVO.setTitleVO(new ReimShapeTitleVO("公司特定指标统计表", "所有项目组" + reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime(), "center", "20%"));
        shapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b} : {c} ({d}%)"));
        shapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "left", Arrays.asList("申报报销记录", "待审核", "待支付", "已支付")));
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
    public ReimCompanyMixShapeVO collectProjectBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        ReimCompanyMixShapeVO mixShapeVO = new ReimCompanyMixShapeVO();
        //柱状图数据
        ReimCompanyShapeBarVO reimCompanyShapeBarVO = new ReimCompanyShapeBarVO();
        //饼状图数据
        ReimShapeVO shapeVO = new ReimShapeVO();

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimCompanyShapeDTO, ReimburseShapeConDTO.class,"serialVersionUID");
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
        //查询所有项目组的申报报销记录
        String[] fields = new String[]{"reimMoney", "projectGroup"};
        String sql = "select sum(reimMoney) as reimMoney , project as projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "' ";
        String sqlGroup = "  group by project ";
        String sqlQuery = sql + sqlGroup;
        List<ReimburseRecord> listReimCord = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的待审核
        sqlQuery = sql + " and reimStatus in (5,1,0) and analisisIsAll = 0 " + sqlGroup;
        List<ReimburseRecord> listReimWait = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的待支付
        sqlQuery = sql + " and reimStatus = 1 and analisisIsAll = 1 and payCondition = '是' and accountCheckPassOr is null " + sqlGroup;
        List<ReimburseRecord> listReimWaitPay = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的已支付
        sqlQuery = sql + " and payCondition = '是' " + sqlGroup;
        List<ReimburseRecord> listReimHasPay = super.findBySql(sqlQuery, ReimburseRecord.class, fields);

        //柱状图型数据封装
        List<ReimShapeBarSeriesVO> seriesDataList = TransformBarData(groupList, listReimCord,listReimWait, listReimWaitPay,listReimHasPay);
        reimCompanyShapeBarVO.setReimShapeTitleVO(new ReimShapeTitleVO("公司内部项目名称特定指标统计表", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
        reimCompanyShapeBarVO.setToolTipVO(new ReimShapeBarToolTipVO("axis"));
        reimCompanyShapeBarVO.setLegendVO(new ReimShapeLegendVO(groupList));
        reimCompanyShapeBarVO.setBarGridVO(new ReimShapeBarGridVO("22%", "22%", "44%", true));
        reimCompanyShapeBarVO.setXaxisVO(new ReimShapeXaxisVO("category", "状态", Arrays.asList("申报报销记录", "待审核", "待支付", "已支付")));
        reimCompanyShapeBarVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)"));
        reimCompanyShapeBarVO.setSeriesVOList(seriesDataList);

        //饼型图形数据封装
        shapeVO.setTitleVO(new ReimShapeTitleVO("公司特定指标统计表", "所有项目名称" + reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime(), "center", "20%"));
        shapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b} : {c} ({d}%)"));
        shapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "left", Arrays.asList("申报报销记录", "待审核", "待支付", "已支付")));
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
        //查询所有项目组的申报报销记录
        String[] fields = new String[]{"reimMoney", "projectGroup"};
        String sql = "select sum(reimMoney) as reimMoney , area as projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "' ";
        String sqlGroup = "  group by area ";
        String sqlQuery = sql + sqlGroup;
        List<ReimburseRecord> listReimCord = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的待审核
        sqlQuery = sql + " and reimStatus in (5,1,0) and analisisIsAll = 0 " + sqlGroup;
        List<ReimburseRecord> listReimWait = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的待支付
        sqlQuery = sql + " and reimStatus = 1 and analisisIsAll = 1 and payCondition = '是' and accountCheckPassOr is null " + sqlGroup;
        List<ReimburseRecord> listReimWaitPay = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        //查询所有项目组的已支付
        sqlQuery = sql + " and payCondition = '是' " + sqlGroup;
        List<ReimburseRecord> listReimHasPay = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
//
        //柱状图型数据封装
        List<ReimShapeBarSeriesVO> seriesDataList = TransformBarData(groupList, listReimCord,listReimWait, listReimWaitPay,listReimHasPay);
        reimCompanyShapeBarVO.setReimShapeTitleVO(new ReimShapeTitleVO("公司内部地区特定指标统计表", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
        reimCompanyShapeBarVO.setToolTipVO(new ReimShapeBarToolTipVO("axis"));
        reimCompanyShapeBarVO.setLegendVO(new ReimShapeLegendVO(groupList));
        reimCompanyShapeBarVO.setBarGridVO(new ReimShapeBarGridVO("22%", "22%", "44%", true));
        reimCompanyShapeBarVO.setXaxisVO(new ReimShapeXaxisVO("category", "状态", Arrays.asList("申报报销记录", "待审核", "待支付", "已支付")));
        reimCompanyShapeBarVO.setYaxisVO(new ReimShapeYaxisVO("value", "金额(元)"));
        reimCompanyShapeBarVO.setSeriesVOList(seriesDataList);

        //饼型图形数据封装
        shapeVO.setTitleVO(new ReimShapeTitleVO("公司特定指标统计表", "所有地区" + reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime(), "center", "20%"));
        shapeVO.setToolTipVO(new ReimShapeToolTipVO("item", "{a} <br/>{b} : {c} ({d}%)"));
        shapeVO.setRlegendVO(new ReimShapeLegendVO("vertical", "left", Arrays.asList("申报报销记录", "待审核", "待支付", "已支付")));
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


    private List<ReimShapeBarSeriesVO> TransformBarData(List<String> groupList, List<ReimburseRecord> listReimCord,
                                                        List<ReimburseRecord> listReimWait, List<ReimburseRecord> listReimWaitPay,
                                                        List<ReimburseRecord> listReimHasPay) {
        List<ReimShapeBarSeriesVO> seriesDataList = new ArrayList<>();
        for (String tempArea : groupList) {
            ReimShapeBarSeriesVO seriesVO = new ReimShapeBarSeriesVO();
            List<Double> seriesDataVOListArea = new ArrayList<>();
            List<Double> reimMoney = listReimCord.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ReimburseRecord::getReimMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listReimWait.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ReimburseRecord::getReimMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listReimWaitPay.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ReimburseRecord::getReimMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));
            reimMoney = listReimHasPay.stream().filter(str -> tempArea.equals(str.getProjectGroup())).map(ReimburseRecord::getReimMoney).collect(Collectors.toList());
            seriesDataVOListArea.add(reimMoney == null || reimMoney.size() == 0 ? 0d : reimMoney.get(0));

            seriesVO.setName(tempArea);
            seriesVO.setSeriesDataVOList(seriesDataVOListArea);
            seriesDataList.add(seriesVO);
        }
        return seriesDataList;
    }


    private List<ReimShapeSeriesDataVO> TransformPieData(List<ReimShapeBarSeriesVO> seriesDataList  ){
        Double val1 = 0d;
        Double val2 = 0d;
        Double val3 = 0d;
        Double val4 = 0d;
        List<ReimShapeSeriesDataVO> seriesPieDataVOList = new ArrayList<>();
        for (ReimShapeBarSeriesVO piedata : seriesDataList) {

            val1 = val1 + piedata.getSeriesDataVOList().get(0);
            val2 = val2 + piedata.getSeriesDataVOList().get(1);
            val3 = val3 + piedata.getSeriesDataVOList().get(2);
            val4 = val4 + piedata.getSeriesDataVOList().get(3);
        }
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("申报报销记录", String.valueOf( new BigDecimal(val1).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("待审核", String.valueOf( new BigDecimal(val2).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("待支付", String.valueOf( new BigDecimal(val3).setScale(5, BigDecimal.ROUND_UP).doubleValue())));
        seriesPieDataVOList.add(new ReimShapeSeriesDataVO("已支付", String.valueOf(new BigDecimal(val4).setScale(5, BigDecimal.ROUND_UP).doubleValue())));

        return seriesPieDataVOList;

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
    public ReimShapeVO collectAreaDetailBar(ReimburseShapeDetailDTO reimburseShapeDetailDTO) throws SerException {
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

        ReimburseShapeConDTO reimburseShapeConDTO = BeanTransform.copyProperties(reimburseShapeDetailDTO, ReimburseShapeConDTO.class,"serialVersionUID");
        reimburseShapeConDTO.setReimburseShapeStatus(reimburseShapeDetailDTO.getShapeStatus());

        //根据选择的日周月年枚举转成时间
        ReimCompanyShapeDTO reimCompanyShapeDTO = BeanTransform.copyProperties(reimburseShapeDetailDTO, ReimCompanyShapeDTO.class,"serialVersionUID");
        reimburseShapeConDTO = enumTOTime(reimCompanyShapeDTO, reimburseShapeConDTO);

        LocalDateTime startTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getStartTime()) + " 00:00:00");
        LocalDateTime endTime = DateUtil.parseDateTime(DateUtil.dateToString(reimburseShapeConDTO.getEndTime()) + " 23:59:59");

        //查询公司所有的地区
        List<AreaBO> areaBOList = departmentDetailAPI.findArea();
        if (areaBOList == null || areaBOList.size() < 0) {
            return new ReimShapeVO();
        }
        ReimburseShapeDetailStatus detailStatus = reimburseShapeDetailDTO.getDetailStatus();
        List<String> groupList = (areaBOList != null && areaBOList.size() > 0) ? areaBOList.stream().map(AreaBO::getArea).collect(Collectors.toList()) : new ArrayList<>();
        //查询所有项目组的申报报销记录
        String[] fields = new String[]{"reimMoney", "projectGroup"};
        String sql = "";
        String sqlWhere = " ";
        String sqlGroup = " ";
        switch (reimburseShapeDetailDTO.getDetailTypeStatus()) {
            case AREA:
                sql = "select sum(reimMoney) as reimMoney , area as projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "' ";
                sqlGroup = "  group by area ";
                titleStr = "各地区的";
                break;
            case GROUP:
                sql = "select sum(reimMoney) as reimMoney , projectGroup as projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "' ";
                sqlGroup = "  group by projectGroup ";
                titleStr = "各项目组的";
                break;
            case PROJECT:
                sql = "select sum(reimMoney) as reimMoney , project as projectGroup from lendreimbursement_reimburserecord where createTime between '" + startTime + "' and '" + endTime + "' ";
                sqlGroup = "  group by project ";
                titleStr = "各项目名称的";
                break;
            default:
                return null;
        }

        //数据库查询处理数据
        dataVOS = companyDetailPieData(reimburseShapeDetailDTO, groupList, fields, sql, sqlGroup, sqlWhere);
        //封装饼型图
        ReimShapeVO reimShapeVO = new ReimShapeVO();
        reimShapeVO.setTitleVO(new ReimShapeTitleVO(titleStr + ReimburseShapeDetailStatus.nameExplain(detailStatus.getCode()) + "统计", reimburseShapeConDTO.getStartTime() + "至" + reimburseShapeConDTO.getEndTime()));
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
     * 公司报销饼型图形的详细饼图
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
    private List<ReimShapeSeriesDataVO> companyDetailPieData(ReimburseShapeDetailDTO reimburseShapeDetailDTO, List<String> groupList, String[] fields, String sql, String sqlGroup, String sqlWhere) throws SerException {
        List<ReimShapeSeriesDataVO> dataVOS = new ArrayList<>();
        ReimburseShapeDetailStatus detailStatus = reimburseShapeDetailDTO.getDetailStatus();
        switch (detailStatus) {
            case ALL:
                break;
            case WAITAUIT:
                sqlWhere = " and reimStatus in (5,1,0 ) and analisisIsAll = 0 ";
                break;
            case WAITPAY:
                sqlWhere = " and reimStatus = 1 and analisisIsAll = 1 and  payCondition != '是' and accountCheckPassOr IS NULL ";
                break;
            case HASPAY:
                sqlWhere = " and payCondition = '是' ";
                break;
            default:
                break;

        }
        String sqlQuery = sql + sqlWhere + sqlGroup;

        List<String> groupNameList = new ArrayList<>();
        List<ReimburseRecord> listResult = new ArrayList<>();
        List<String> distinctGroupNameList = new ArrayList<>();

        List<ReimburseRecord> list = super.findBySql(sqlQuery, ReimburseRecord.class, fields);
        if (list != null && list.size() > 0) {
            listResult.addAll(list);
            //把没有申请报销的项目组的金额设置为0
            groupNameList = list.stream().map(ReimburseRecord::getProjectGroup).collect(Collectors.toList());
            List<String> finalGroupNameList = groupNameList;
            if (groupNameList != null && groupNameList.size() > 0) {
                distinctGroupNameList = groupList.stream().filter(str -> finalGroupNameList.contains(str)).collect(Collectors.toList());
            }
        } else {
            distinctGroupNameList = groupList;
        }
        distinctGroupNameList.forEach(str -> {
            ReimburseRecord temp = new ReimburseRecord(str, 0d);
            listResult.add(temp);
        });
        listResult.stream().sorted(new Comparator<ReimburseRecord>() {
            @Override
            public int compare(ReimburseRecord o1, ReimburseRecord o2) {
                return o1.getProjectGroup().compareTo(o2.getProjectGroup());
            }
        });
        listResult.stream().forEach(str -> {
            ReimShapeSeriesDataVO seriesDataVO = new ReimShapeSeriesDataVO();
            seriesDataVO.setName(str.getProjectGroup());
            seriesDataVO.setValue("" + str.getReimMoney());
            dataVOS.add(seriesDataVO);
        });

        return dataVOS;

    }


}