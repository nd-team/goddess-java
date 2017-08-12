package com.bjike.goddess.lendreimbursement.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.lendreimbursement.bo.*;
import com.bjike.goddess.lendreimbursement.dto.*;
import com.bjike.goddess.lendreimbursement.entity.*;
import com.bjike.goddess.lendreimbursement.enums.GuideAddrStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;
import com.bjike.goddess.lendreimbursement.enums.Words;
import com.bjike.goddess.lendreimbursement.excel.ReimburseRecordExcel;
import com.bjike.goddess.lendreimbursement.excel.SonPermissionObject;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcel;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcelTO;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //商务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务模块人员,没有该操作权限");
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
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
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
//                reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
                sql.append(" and r2.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
            } else {
//                reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", userName));
                sql.append(" and r2.reimer = '" + userName + "' ");
            }
            if (StringUtils.isNotBlank(reimburseRecordDTO.getCharger())) {
//                reimburseRecordDTO.getConditions().add(Restrict.eq("charger", reimburseRecordDTO.getCharger()));
                sql.append(" and r2.charger = '" + reimburseRecordDTO.getCharger() + "' ");
            } else {
//                reimburseRecordDTO.getConditions().add(Restrict.or("charger", userName));
                sql.append(" or r2.charger = '" + userName + "' ");
            }
//            reimburseRecordDTO.getConditions().add(Restrict.or("filler", userName));
            sql.append(" or r2.filler = '" + userName + "' ");
            RpcTransmit.transmitUserToken(userToken);
        } else {
            if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
//                reimburseRecordDTO.getConditions().add(Restrict.eq("reimer", reimburseRecordDTO.getReimer()));
                sql.append(" and r2.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
            }
            if (StringUtils.isNotBlank(reimburseRecordDTO.getCharger())) {
//                reimburseRecordDTO.getConditions().add(Restrict.eq("charger", reimburseRecordDTO.getCharger()));
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
        applyLendDTO.getConditions().add(Restrict.eq("lendMoney", 0d));
        List<ApplyLend> applyLendList = applyLendSer.findByCis(applyLendDTO);
        if (applyLendList != null && applyLendList.size() > 0) {
            throw new SerException("报销人有超过15天的借款未还，请先还款，再来报销");
        }

        //获取最小报销单号
        String runNum = finoddinforSer.getMinRunNum();
        if (StringUtils.isBlank(runNum)) {
            throw new SerException("不好意思报销单号已用完,请稍等,谢谢！");
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
//        List<Finoddinfor> finoddinforList=  finoddinforSer.findByCis(finoddinforDTO);
//        finoddinforSer.congealFinoddinfor(finoddinforSer.findOne(finoddinforDTO).getId());
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
        sql.append(" WHERE re1.chargerAuditStatus = '不通过'  or re1.reimStatus  = 6  ");

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

        sql.append(" WHERE re1.chargerAuditStatus = '不通过'  or re1.reimStatus  = 6  ");
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

        super.update(temp);

        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

//        reimburseRecordDTO.getConditions().add(Restrict.isNull("chargerAuditStatus"));
//        reimburseRecordDTO.getConditions().add(Restrict.or("chargerAuditStatus", null));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 0}));


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
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(list, ReimburseRecordBO.class);
        return boList;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO auditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
        RpcTransmit.transmitUserToken(userToken);
        PositionBO positionBO = null;
        if (udetailBO != null) {
            positionBO = positionAPI.findById(udetailBO.getPositionId());
            RpcTransmit.transmitUserToken(userToken);
        }

        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if (!userName.equals(temp.getCharger()) || !userName.equals("admin")) {
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
        }
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);

        //填审核日志表
        ReimburseAuditLog reimburseAuditLog = new ReimburseAuditLog();
        reimburseAuditLog.setUserName(userName);
        reimburseAuditLog.setEmpNum(userBO.getEmployeeNumber());
        reimburseAuditLog.setAuditStatus("通过".equals(reimburseRecordTO.getChargerAuditStatus()) ? "负责人通过" : "负责人不通过");
        reimburseAuditLog.setAuditTime(LocalDate.now());
        reimburseAuditLog.setContent(reimburseRecordTO.getAuditAdvice());
        reimburseAuditLog.setPosition(positionBO != null ? positionBO.getName() : "");
        reimburseAuditLog.setReimrecordId(reimburseRecordTO.getId());
        reimburseAuditLog.setCreateTime(LocalDateTime.now());
        reimburseAuditLog.setModifyTime(LocalDateTime.now());
        reimburseAuditLogSer.save(reimburseAuditLog);

        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO congelAuditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
        RpcTransmit.transmitUserToken(userToken);
        PositionBO positionBO = null;
        if (udetailBO != null) {
            positionBO = positionAPI.findById(udetailBO.getPositionId());
            RpcTransmit.transmitUserToken(userToken);
        }

        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if (!userName.equals(temp.getCharger())) {
            throw new SerException("您不是负责人，不能确认冻结");
        }


        if (StringUtils.isBlank(reimburseRecordTO.getSureCongel())) {
            throw new SerException("是否确认冻结不能为空");
        }
        if (!"是".equals(reimburseRecordTO.getSureCongel()) && !"否".equals(reimburseRecordTO.getSureCongel())) {
            throw new SerException("是否确认冻结只能填写是或否");
        }

        if ("是".equals(reimburseRecordTO.getSureCongel())) {
            temp.setChargerAuditStatus("不通过");
            temp.setChargerAuditTime(LocalDate.now());
            temp.setReimStatus(ReimStatus.CHARGECONGEL);
        } else if ("否".equals(reimburseRecordTO.getSureCongel())) {
            temp.setChargerAuditStatus("通过");
            temp.setChargerAuditTime(LocalDate.now());
            temp.setReimStatus(ReimStatus.CHARGEPASS);
        }

        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);

        //填审核日志表
        ReimburseAuditLog reimburseAuditLog = new ReimburseAuditLog();
        reimburseAuditLog.setUserName(userName);
        reimburseAuditLog.setEmpNum(userBO.getEmployeeNumber());
        reimburseAuditLog.setAuditStatus("不通过");
        reimburseAuditLog.setAuditTime(LocalDate.now());
        reimburseAuditLog.setContent("冻结");
        reimburseAuditLog.setPosition(positionBO != null ? positionBO.getName() : "");
        reimburseAuditLog.setReimrecordId(reimburseRecordTO.getId());
        reimburseAuditLog.setCreateTime(LocalDateTime.now());
        reimburseAuditLog.setModifyTime(LocalDateTime.now());
        reimburseAuditLogSer.save(reimburseAuditLog);

        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        //但前用户审核分析报销日志
//        String useName = userAPI.currentUser().getUsername();
//        ReimburseAuditLogDTO raDTO = new ReimburseAuditLogDTO();
//        raDTO.getConditions().add(Restrict.eq("userName", useName));
//        raDTO.getConditions().add(Restrict.eq("auditStatus", "未处理"));
//        List<ReimburseAuditLog> list = reimburseAuditLogSer.findByCis(raDTO);
//        List<String> reimIds = list.stream().map(ReimburseAuditLog::getReimrecordId).distinct().collect(Collectors.toList());
//
//        StringBuffer sb = new StringBuffer(" ");
//        reimIds.stream().forEach(str -> {
//            sb.append("'" + str + "',");
//        });
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        //查询分析人员表
        ReimburseAnalisisorDTO reimAnalisisDTO = new ReimburseAnalisisorDTO();
        reimAnalisisDTO.getConditions().add(Restrict.eq("empNum", userBO.getEmployeeNumber()));
        List<ReimburseAnalisisor> analisisorList = reimburseAnalisisorSer.findByCis(reimAnalisisDTO);
        if (analisisorList == null || analisisorList.size() <= 0) {
            return 0L;
        }
        //查询审核分析表,如果已分析了的，就不会有
        String[] fields = new String[]{"count"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT count(*) as count ")
                .append(" FROM lendreimbursement_reimburserecord  re1 ")
                .append(" WHERE re1.reimStatus = 1 AND re1.ticketCondition = '是' ");
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and re1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and re1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }

        sql.append(" and re1.id not in ( ")
                .append(" SELECT record1.id ")
                .append(" FROM lendreimbursement_reimburserecord record1 ")
                .append(" LEFT JOIN lendreimbursement_reimburseauditlog aulog1 ON record1.id = aulog1.reimrecordId ")
                .append(" WHERE record1.reimStatus = 1 AND record1.ticketCondition = '是' AND aulog1.empNum = '" + userBO.getEmployeeNumber() + "' ");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and record1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and record1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }

        sql.append(" ) ");

        List<ReimburseRecordBO> count = super.findBySql(sql.toString(), ReimburseRecordBO.class, fields);
        return count != null ? count.size() : 0L;
    }


    @Override
    public List<ReimburseRecordBO> listAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        //负责人审核通过的记录
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        //查询分析人员表
        ReimburseAnalisisorDTO reimAnalisisDTO = new ReimburseAnalisisorDTO();
        reimAnalisisDTO.getConditions().add(Restrict.eq("empNum", userBO.getEmployeeNumber()));
        List<ReimburseAnalisisor> analisisorList = reimburseAnalisisorSer.findByCis(reimAnalisisDTO);
        if (analisisorList == null || analisisorList.size() <= 0) {
            return null;
        }
        //查询审核分析表,如果已分析了的，就不会有
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
                .append(" WHERE re1.reimStatus = 1 AND re1.ticketCondition = '是' ");
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and re1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and re1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }

        sql.append(" and re1.id not in ( ")
                .append(" SELECT record1.id ")
                .append(" FROM lendreimbursement_reimburserecord record1 ")
                .append(" LEFT JOIN lendreimbursement_reimburseauditlog aulog1 ON record1.id = aulog1.reimrecordId ")
                .append(" WHERE record1.reimStatus = 1 AND record1.ticketCondition = '是' AND aulog1.empNum = '" + userBO.getEmployeeNumber() + "' ");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and record1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and record1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }
        sql.append(" ) ");
        sql.append(" order by re1.modifyTime desc ");
        sql.append(" limit " + (reimburseRecordDTO.getPage()) + "," + reimburseRecordDTO.getLimit() + " ");

        List<ReimburseRecordBO> list = super.findBySql(sql.toString(), ReimburseRecordBO.class, fields);
        return list;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO analisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
//        if (StringUtils.isBlank(reimburseRecordTO.getChargerAuditStatus())) {
//            throw new SerException("负责人还未审核");
//        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if (temp.getReimStatus().getCode() == 0) {
            throw new SerException("负责人还未审核");
        }
        if (temp.getReimStatus().getCode() == 6) {
            throw new SerException("负责人还未确认冻结");
        }

        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = null;
        if (udetailBO != null) {
            positionBO = positionAPI.findById(udetailBO.getPositionId());
        }

        ReimburseAuditLog reimburseAuditLog = new ReimburseAuditLog();

        if (StringUtils.isNotBlank(reimburseRecordTO.getChargerAuditStatus()) && reimburseRecordTO.getChargerAuditStatus().equals("通过")) {
            reimburseAuditLog.setAuditStatus("分析通过");
        } else if (StringUtils.isNotBlank(reimburseRecordTO.getChargerAuditStatus()) && reimburseRecordTO.getChargerAuditStatus().equals("不通过")) {
            reimburseAuditLog.setAuditStatus("分析不通过");
        } else {
            throw new SerException("分析人员只能选择通过或不通过二个状态,reimStatus");
        }
        //审核日志表
        reimburseAuditLog.setContent("分析意见:" + reimburseRecordTO.getAuditAdvice());
        reimburseAuditLog.setUserName(userName);
        reimburseAuditLog.setEmpNum(userBO.getEmployeeNumber());
        reimburseAuditLog.setAuditTime(LocalDate.now());
        reimburseAuditLog.setPosition(positionBO != null ? positionBO.getName() : "");
        reimburseAuditLog.setReimrecordId(reimburseRecordTO.getId());
        reimburseAuditLog.setCreateTime(LocalDateTime.now());
        reimburseAuditLog.setModifyTime(LocalDateTime.now());
        reimburseAuditLogSer.save(reimburseAuditLog);


        temp.setReimStatus(ReimStatus.CHARGEPASS);
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO congelAnalisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }


        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = null;
        if (udetailBO != null) {
            positionBO = positionAPI.findById(udetailBO.getPositionId());
        }

        //审核日志记录
        ReimburseAuditLog reimburseAuditLog = new ReimburseAuditLog();
        reimburseAuditLog.setUserName(userName);
        reimburseAuditLog.setEmpNum(userBO.getEmployeeNumber());
        reimburseAuditLog.setAuditTime(LocalDate.now());
        reimburseAuditLog.setPosition(positionBO != null ? positionBO.getName() : "");
        reimburseAuditLog.setReimrecordId(reimburseRecordTO.getId());
        reimburseAuditLog.setAuditStatus("申请冻结");
        reimburseAuditLog.setContent("");
        reimburseAuditLog.setCreateTime(LocalDateTime.now());
        reimburseAuditLog.setModifyTime(LocalDateTime.now());

        reimburseAuditLogSer.save(reimburseAuditLog);

        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setReimStatus(ReimStatus.CONGEL);
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {

//        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        //查询分析人员表
        ReimburseAnalisisorDTO reimAnalisisDTO = new ReimburseAnalisisorDTO();
        reimAnalisisDTO.getConditions().add(Restrict.eq("empNum", userBO.getEmployeeNumber()));
        List<ReimburseAnalisisor> analisisorList = reimburseAnalisisorSer.findByCis(reimAnalisisDTO);
        if (analisisorList == null || analisisorList.size() <= 0) {
            return 0L;
        }
        //查询审核分析表,如果已分析了的，就不会有
        String[] fields = new String[]{"count"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT count(*) as count ")
                .append(" FROM lendreimbursement_reimburserecord re1 ")
                .append(" WHERE re1.reimStatus = 1 AND re1.ticketCondition = '是' ");
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and re1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and re1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }

        sql.append(" and re1.id  in ( ")
                .append(" SELECT record1.id ")
                .append(" FROM lendreimbursement_reimburserecord record1 ")
                .append(" LEFT JOIN lendreimbursement_reimburseauditlog aulog1 ON record1.id = aulog1.reimrecordId ")
                .append(" WHERE record1.reimStatus = 1 AND record1.ticketCondition = '是' AND aulog1.empNum = '" + userBO.getEmployeeNumber() + "' ");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and record1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and record1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }
        sql.append(" ) ");

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

        //查询分析人员表
        ReimburseAnalisisorDTO reimAnalisisDTO = new ReimburseAnalisisorDTO();
        reimAnalisisDTO.getConditions().add(Restrict.eq("empNum", userBO.getEmployeeNumber()));
        List<ReimburseAnalisisor> analisisorList = reimburseAnalisisorSer.findByCis(reimAnalisisDTO);
        if (analisisorList == null || analisisorList.size() <= 0) {
            return null;
        }
        //查询审核分析表,如果已分析了的，就会有
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
                .append(" WHERE re1.reimStatus = 1 AND re1.ticketCondition = '是' ");
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and re1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and re1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and re1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }

        sql.append(" and re1.id in ( ")
                .append(" SELECT record1.id ")
                .append(" FROM lendreimbursement_reimburserecord record1 ")
                .append(" LEFT JOIN lendreimbursement_reimburseauditlog aulog1 ON record1.id = aulog1.reimrecordId ")
                .append(" WHERE record1.reimStatus = 1 AND record1.ticketCondition = '是' AND aulog1.empNum = '" + userBO.getEmployeeNumber() + "' ");

        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimNumber())) {
            sql.append(" and record1.reimNumber = '" + reimburseRecordDTO.getReimNumber() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate = '" + reimburseRecordDTO.getStartTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())) {
            sql.append(" and record1.occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getReimer())) {
            sql.append(" and record1.reimer = '" + reimburseRecordDTO.getReimer() + "' ");
        }
        sql.append(" ) ");
        sql.append(" order by re1.modifyTime desc ");
        sql.append(" limit " + (reimburseRecordDTO.getPage()) + "," + reimburseRecordDTO.getLimit() + " ");

        List<ReimburseRecordBO> list = super.findBySql(sql.toString(), ReimburseRecordBO.class, fields);
        return list;

    }

    @Override
    public Long countAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        reimburseRecordDTO.getConditions().equals(Restrict.eq("chargerAuditStatus", "通过"));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1, 3, 4}));
        reimburseRecordDTO.getConditions().add(Restrict.eq("ticketCondition", "是"));
        reimburseRecordDTO.getConditions().add(Restrict.isNull("receiveTicketCheck"));
        reimburseRecordDTO.getConditions().add(Restrict.eq("receiveTicketCheck", "否"));

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
        reimburseRecordDTO.getConditions().add(Restrict.isNull("receiveTicketCheck"));
        reimburseRecordDTO.getConditions().add(Restrict.or("receiveTicketCheck", "否"));

        reimburseRecordDTO = addCondition(reimburseRecordDTO);

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
        //若审核分析表的分析人员全部分析完才能使用这个功能
        //查询是否全部人已经分析完
        String[] fields = new String[]{"count"};
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT count(*) as count ")
                .append(" FROM lendreimbursement_reimburseanalisisor analisisor ")
                .append(" WHERE analisisor.empNum NOT IN (SELECT empNum ")
                .append(" FROM lendreimbursement_reimburseauditlog ")
                .append("  WHERE reimrecordId = '" + reimburseRecordTO.getId() + "') ");
        List<ReimburseAnalisisorBO> reimburseRecordBOList = reimburseAnalisisorSer.findBySql(sql.toString(), ReimburseAnalisisorBO.class, fields);
        if (reimburseRecordBOList != null && reimburseRecordBOList.size() > 0) {
            if (reimburseRecordBOList.get(0).getCount() > 0) {
                throw new SerException("规定的运营分析人员还没有全部分析完，不能确认收到单据，请所有分析人员先分析完该条数据");
            }
        }


        if (StringUtils.isBlank(reimburseRecordTO.getReceiveTicketCheck())) {
            throw new SerException("是否收到单据不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setReceiveTicketer(reimburseRecordTO.getReceiveTicketer());
        temp.setReceiveTicketCon(reimburseRecordTO.getReceiveTicketCon());
        temp.setReceiveTicketTime(LocalDate.parse(reimburseRecordTO.getReceiveTicketTime()));
        if ("是".equals(reimburseRecordTO.getReceiveTicketCheck())) {
            temp.setReceiveTicketCheck("是");
        } else if ("否".equals(reimburseRecordTO.getReceiveTicketCheck())) {
            temp.setReceiveTicketCheck("否");
        }
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {


        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
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
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
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
        dto.getConditions().add(Restrict.ne("payCondition", "是"));
        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
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
        StringBuffer sb = new StringBuffer(" ");
        for (String str : reimNumbers) {
            sb.append("'" + str + "' , ");
        }
        ReimburseRecordDTO dto = new ReimburseRecordDTO();
        dto.getConditions().add(Restrict.in("reimNumber", StringUtils.substringBeforeLast(sb.toString(), ",")));
        List<ReimburseRecord> recordList = super.findByCis(dto);

        recordList.stream().forEach(str -> {
            str.setBudgetPayTime(LocalDate.now());
            str.setPayPlan(reimburseRecordTO.getPayPlan());
            str.setModifyTime(LocalDateTime.now());
        });

        super.update(recordList);
        ReimburseRecord reimburseRecord = new ReimburseRecord();
        return BeanTransform.copyProperties(reimburseRecord, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO waitPay(ReimburseRecordTO reimburseRecordTO) throws SerException {
        checkPermission();
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
            System.out.println("返回：" + sb.toString());
            collectDataBOList = super.findBySql(sb.toString(), CollectReimerDataBO.class, fields);

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
        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
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
        checkPermission();
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
            reimburseRecordDTO.getConditions().add(Restrict.between("budgetPayTime", budgetPayTime));//借款时间段查询
        }
        reimburseRecordDTO.getConditions().add(Restrict.ne("payCondition", "是"));
        reimburseRecordDTO.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1}));

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO);

        List<ReimburseRecordExcel> reimburseRecordExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ReimburseRecordExcel excel = BeanTransform.copyProperties(str, ReimburseRecordExcel.class, "lendStatus");
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
        checkPermission();
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
            reimburseRecordDTO.getConditions().add(Restrict.between("budgetPayTime", budgetPayTime));//借款时间段查询
        }
        reimburseRecordDTO.getConditions().add(Restrict.eq("payCondition", "是"));

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO);

        List<ReimburseRecordExcel> reimburseRecordExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ReimburseRecordExcel excel = BeanTransform.copyProperties(str, ReimburseRecordExcel.class, "lendStatus");
            excel.setReimStatus(ReimStatus.exportStrConvert(str.getReimStatus()));

            reimburseRecordExcels.add(excel);
        });
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
        reimburseRecordDTO.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
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
        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
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
}