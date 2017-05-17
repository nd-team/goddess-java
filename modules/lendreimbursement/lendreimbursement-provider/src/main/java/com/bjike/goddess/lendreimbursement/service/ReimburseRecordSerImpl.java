package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.CollectDataBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.*;
import com.bjike.goddess.lendreimbursement.entity.*;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;
import com.bjike.goddess.lendreimbursement.enums.Words;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
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
    private ApplyLendSer applyLendSer;

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

        Long count = super.count(reimburseRecordDTO);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
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
        applyLendDTO.getConditions().add(Restrict.eq("reimMoney", 0d));
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

        super.save(reimburseRecord);

        //冻结该报销单，说明已经被使用了
        FinoddinforDTO finoddinforDTO = new FinoddinforDTO();
        finoddinforDTO.getConditions().add(Restrict.eq("runNum", runNum));
        finoddinforSer.congealFinoddinfor(finoddinforSer.findOne(finoddinforDTO).getId());

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
        if (StringUtils.isBlank(reimburseRecordTO.getReimer())) {
            throw new SerException("报销人不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
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

        super.remove(id);


    }

    @Override
    public ReimburseRecordBO getReimburseRecordById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        ReimburseRecord reimburseRecord = super.findById(id);
        return BeanTransform.copyProperties(reimburseRecord, ReimburseRecordBO.class);
    }

    @Override
    public Long countErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        reimburseRecordDTO.getConditions().add(Restrict.eq("chargerAuditStatus", "不通过"));
        reimburseRecordDTO.getConditions().add(Restrict.or("reimStatus", ReimStatus.CHARGECONGEL));

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


        Long count = super.count(reimburseRecordDTO);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        reimburseRecordDTO.getConditions().add(Restrict.eq("chargerAuditStatus", "不通过"));
        reimburseRecordDTO.getConditions().add(Restrict.or("reimStatus", ReimStatus.CHARGECONGEL));

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

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO,true);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(list, ReimburseRecordBO.class, true);
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO editErrorRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        if (StringUtils.isBlank(reimburseRecordTO.getReimer())) {
            throw new SerException("报销人不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
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
        reimburseRecordDTO.getConditions().add(Restrict.isNull("chargerAuditStatus"));
//        reimburseRecordDTO.getConditions().add(Restrict.or("chargerAuditStatus", null));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 0}));

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
            reimburseRecordDTO.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }


        Long count = super.count(reimburseRecordDTO);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        reimburseRecordDTO.getConditions().add(Restrict.isNull("chargerAuditStatus"));
//        reimburseRecordDTO.getConditions().add(Restrict.or("chargerAuditStatus", null));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{5, 0}));

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

        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO, true);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(list, ReimburseRecordBO.class);
        return boList;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO auditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = null;
        if (udetailBO != null) {
            positionBO = positionAPI.findById(udetailBO.getPositionId());
        }

        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
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
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
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
        UserBO userBO = userAPI.currentUser();
        String userName = userBO.getUsername();
        UserDetailBO udetailBO = userDetailAPI.findByUserId(userBO.getId());
        PositionBO positionBO = null;
        if (udetailBO != null) {
            positionBO = positionAPI.findById(udetailBO.getPositionId());
        }

        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
        if(StringUtils.isBlank(reimburseRecordTO.getSureCongel())){
            throw new SerException("是否确认冻结不能为空");
        }
        if( !"是".equals(reimburseRecordTO.getSureCongel()) && !"否".equals(reimburseRecordTO.getSureCongel()) ){
            throw new SerException("是否确认冻结只能填写是或否");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        if( "是".equals(reimburseRecordTO.getSureCongel())  ){
            temp.setChargerAuditStatus("通过");
            temp.setChargerAuditTime(LocalDate.now());
            temp.setReimStatus(ReimStatus.CHARGEPASS);
        }else if("否".equals(reimburseRecordTO.getSureCongel())){
            temp.setChargerAuditStatus("不通过");
            temp.setChargerAuditTime(LocalDate.now());
            temp.setReimStatus(ReimStatus.CHARGECONGEL);
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
        ReimburseRecordDTO dto = reimburseRecordDTO;
//        dto.getConditions().add(Restrict.in("id", StringUtils.substringBeforeLast(sb.toString(), ",")));
        dto.getConditions().add(Restrict.eq("reimStatus", 1));
        dto.getConditions().add(Restrict.eq("ticketCondition", "是"));
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
        //负责人审核通过的记录
        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.eq("reimStatus", 1));
        dto.getConditions().add(Restrict.eq("ticketCondition", "是"));
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
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }

        List<ReimburseRecord> recordBOList = super.findByCis(dto, true);
        List<ReimburseRecordBO> recordBOList1 = BeanTransform.copyProperties(recordBOList, ReimburseRecordBO.class);
        return recordBOList1;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO analisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        }
//        if (StringUtils.isBlank(reimburseRecordTO.getChargerAuditStatus())) {
//            throw new SerException("负责人还未审核");
//        }
        if (reimburseRecordTO.getReimStatus().getCode() == 0) {
            throw new SerException("负责人还未审核");
        }
        if (reimburseRecordTO.getReimStatus().getCode() == 6) {
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

        if (reimburseRecordTO.getReimStatus().equals(ReimStatus.PASS)) {
            reimburseAuditLog.setAuditStatus("分析通过");
        } else if (reimburseRecordTO.getReimStatus().equals(ReimStatus.NOTPASS)) {
            reimburseAuditLog.setAuditStatus("分析不通过");
        } else {
            throw new SerException("分析人员只能选择通过或不通过或申请冻结三个状态");
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

        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setReimStatus(reimburseRecordTO.getReimStatus());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO congelAnalisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
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

        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
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
            dto.getConditions().add(Restrict.eq("occureDate", reimburseRecordDTO.getStartTime()));
        }

        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3, 4}));
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

        List<ReimburseRecord> recordBOList = super.findByCis(dto, true);
        List<ReimburseRecordBO> recordBOList1 = BeanTransform.copyProperties(recordBOList, ReimburseRecordBO.class);
        return recordBOList1;
    }

    @Override
    public Long countAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        reimburseRecordDTO.getConditions().equals(Restrict.eq("chargerAuditStatus", "通过"));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1, 3, 4}));
        reimburseRecordDTO.getConditions().add(Restrict.eq("ticketCondition", "是"));
        reimburseRecordDTO.getConditions().equals(Restrict.eq("receiveTicketCheck", "否"));
        Long count = super.count(reimburseRecordDTO);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
//        reimburseRecordDTO.getConditions().equals(Restrict.eq("chargerAuditStatus", "通过"));
        reimburseRecordDTO.getConditions().add(Restrict.in("reimStatus", new Integer[]{1, 3, 4}));
        reimburseRecordDTO.getConditions().add(Restrict.eq("ticketCondition", "是"));
        reimburseRecordDTO.getConditions().equals(Restrict.eq("receiveTicketCheck", "否"));
        List<ReimburseRecord> list = super.findByCis(reimburseRecordDTO, true);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(list, ReimburseRecordBO.class);
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReimburseRecordBO recieveTicketCondition(ReimburseRecordTO reimburseRecordTO) throws SerException {
        if (StringUtils.isBlank(reimburseRecordTO.getId())) {
            throw new SerException("id不能为空");
        } if (StringUtils.isBlank(reimburseRecordTO.getReceiveTicketCheck())) {
            throw new SerException("是否收到单据不能为空");
        }
        ReimburseRecord temp = super.findById(reimburseRecordTO.getId());
        temp.setReceiveTicketer(reimburseRecordTO.getReceiveTicketer());
        temp.setReceiveTicketCon(reimburseRecordTO.getReceiveTicketCon());
        temp.setReceiveTicketTime(LocalDate.parse(reimburseRecordTO.getReceiveTicketTime()));
        if( "是".equals(reimburseRecordTO.getReceiveTicketCheck())){
            temp.setReceiveTicketCheck("是");
        }else if("否".equals(reimburseRecordTO.getReceiveTicketCheck()) ){
            temp.setReceiveTicketCheck("否");
        }
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);
        return BeanTransform.copyProperties(temp, ReimburseRecordBO.class);
    }

    @Override
    public Long countWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {


        ReimburseRecordDTO dto = reimburseRecordDTO;
        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3,4}));
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
        dto.getConditions().add(Restrict.eq("receiveTicketCheck", "是"));
        dto.getConditions().add(Restrict.in("reimStatus", new Integer[]{3,4}));
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

        List<ReimburseRecord> recordList = super.findByCis(dto,true);
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

        ReimburseRecordDTO dto = new ReimburseRecordDTO();
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

        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<ReimburseRecordBO> listHasPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        ReimburseRecordDTO dto = new ReimburseRecordDTO();
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


        List<ReimburseRecord> recordList = super.findByCis(dto,true);
        List<ReimburseRecordBO> boList = BeanTransform.copyProperties(recordList, ReimburseRecordBO.class);
        return boList;
    }

    @Override
    public List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("生成记账凭证失败，id不能为空");
        }
        String userName = userAPI.currentUser().getUsername();

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
        list.add(accountVoucherBO);

        accountVoucherBO = new AccountVoucherBO();
        accountVoucherBO.setWords(Words.PAY);
        accountVoucherBO.setDates(dates);
        accountVoucherBO.setStage(stage);
        accountVoucherBO.setTicketNum(ticketNum);
        accountVoucherBO.setTicketUser(userName);
        accountVoucherBO.setBorrowResion(reim.getSummary());
        accountVoucherBO.setSubject(reim.getPayOrigin());
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
    public List<CollectDataBO> collectLender(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"lender", "money"};
        //当没选择报销人和时间时，表头有：(报销人 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getReimer()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select reimer as lender , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by reimer");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getReimer()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选报销人时，表头有:(报销人/时间段/金额)
            fields = new String[]{"lender", "reimDate", "money"};
            sb = new StringBuffer("");
            sb.append("select reimer as lender,occureDate as reimDate , sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by reimer ");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

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
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
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
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

        }

        return collectDataBOList;
    }

    @Override
    public List<CollectDataBO> collectArea(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"area", "money"};
        //当没选择地区和时间时，表头有：(地区 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getArea()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select area , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by area");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getArea()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选地区时，表头有:(地区/时间段/金额)
            fields = new String[]{"area", "reimDate", "money"};
            sb = new StringBuffer("");
            sb.append("select area,occureDate as reimDate , sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by area ");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

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
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
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
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

        }

        return collectDataBOList;
    }

    @Override
    public List<CollectDataBO> collectFirstSubject(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"firstSubject", "money"};
        //当没选择一级科目和时间时，表头有：(一级科目 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select firstSubject , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by firstSubject");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选一级科目时，表头有:(一级科目/时间段/金额)
            fields = new String[]{"firstSubject", "reimDate", "money"};
            sb = new StringBuffer("");
            sb.append("select firstSubject,occureDate as reimDate , sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by firstSubject ");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            //当有选一级科目，没选日期时，表头有:(报销人/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where payCondition = '是' and firstSubejct = '" + reimburseRecordDTO.getFirstSubject().trim() + "'  ")
                    .append("  order by firstSubject desc , secondSubject desc , thirdSubject desc  ");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else if (StringUtils.isNotBlank(reimburseRecordDTO.getFirstSubject()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期和一级科目时，表头有:(报销人/时间/地区/项目/项目组/一级科目/二级科目/三级科目/金额)
            fields = new String[]{"lender", "reimDate", "area", "projectGroup", "projectName", "firstSubject", "secondSubject",
                    "thirdSubject", "money"};
            sb.append("SELECT  reimer as lender,occureDate as reimDate,  area, projectGroup,")
                    .append("  project as projectName,  firstSubject,  secondSubject, ")
                    .append("  thirdSubject,  reimMoney as money ")
                    .append("   FROM lendreimbursement_reimburserecord where firstSubejct = '" + reimburseRecordDTO.getFirstSubject().trim() + "'")
                    .append(" and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "'")
                    .append("  and  payCondition = '是' ")
                    .append(" order by firstSubject desc , secondSubject desc , thirdSubject desc ");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

        }

        return collectDataBOList;
    }

    @Override
    public List<CollectDataBO> collectProjectName(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        if ((StringUtils.isBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime()))) {
            throw new SerException("两个时间必须同时选");
        }
        if (StringUtils.isNotBlank(reimburseRecordDTO.getStartTime()) && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            throw new SerException("两个时间必须同时选");
        }

        List<CollectDataBO> collectDataBOList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        String[] fields = new String[]{"projectName", "money"};
        //当没选择项目和时间时，表头有：(项目 \金额)
        if (StringUtils.isBlank(reimburseRecordDTO.getProject()) && StringUtils.isBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isBlank(reimburseRecordDTO.getEndTime())) {
            sb = new StringBuffer("");
            sb.append("select project as projectName , sum(reimMoney) as money from lendreimbursement_reimburserecord where payCondition = '是' group by project");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
        } else if (StringUtils.isBlank(reimburseRecordDTO.getProject()) && StringUtils.isNotBlank(reimburseRecordDTO.getStartTime())
                && StringUtils.isNotBlank(reimburseRecordDTO.getEndTime())) {
            //当有选日期，没选项目时，表头有:(项目/时间段/金额)
            fields = new String[]{"projectName", "reimDate", "money"};
            sb = new StringBuffer("");
            sb.append("select project as projectName ,occureDate as reimDate , sum(reimMoney) as money from lendreimbursement_reimburserecord " +
                    " where payCondition = '是' and occureDate between '" + reimburseRecordDTO.getStartTime() + "' and '" + reimburseRecordDTO.getEndTime() + "' group by project ");
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

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
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);
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
            collectDataBOList = super.findBySql(sb.toString(), CollectDataBO.class, fields);

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
}