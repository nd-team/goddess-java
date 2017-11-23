package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.api.olddata.AjoaUserAPI;
import com.bjike.goddess.lendreimbursement.dto.olddata.*;
import com.bjike.goddess.lendreimbursement.entity.ReimburseAuditLog;
import com.bjike.goddess.lendreimbursement.entity.ReimburseRecord;
import com.bjike.goddess.lendreimbursement.entity.ReimburseRecordLog;
import com.bjike.goddess.lendreimbursement.entity.olddata.*;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;
import com.bjike.goddess.lendreimbursement.service.ReimburseAuditLogSer;
import com.bjike.goddess.lendreimbursement.service.ReimburseRecordLogSer;
import com.bjike.goddess.lendreimbursement.service.ReimburseRecordSer;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 老系统的报销业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 01:55 ]
 * @Description: [ 老系统的报销业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class AjoaReimSerImpl extends ServiceImpl<AjoaReim, AjoaReimDTO> implements AjoaReimSer {

    @Autowired
    private ReimburseRecordSer reimburseRecordSer;
    @Autowired
    private AjoaSubjectSer subjectSer;
    @Autowired
    private AjoaMattersSer ajoaMattersSer;
    @Autowired
    private AjoaFinanceAuditLogSer logSer;
    @Autowired
    private ReimburseAuditLogSer recordLogSer;
    @Autowired
    private AjoaUserSer ajoaUserSer;


    @Override
    public void importReimOldData() throws SerException {
        List<ReimburseRecord> recordList = new ArrayList<>();
        List<ReimburseAuditLog> recordLogList = new ArrayList<>();
        List<AjoaFinanceAuditLog> ajLogList = new ArrayList<>();

        List<AjoaReim> list = super.findAll();
        if ( list != null && list.size()>0 ){
            for(AjoaReim str : list ){
                ReimburseRecord record = new ReimburseRecord();
                record.setOccureDate(str.getOccurrenceDate() );//报销发生日期
                record.setFiller(str.getReimburseName() );//填单人
                record.setCommitDate( null == str.getReportDate()?null : LocalDate.of(str.getReportDate().getYear(), str.getReportDate().getMonthValue(), str.getReportDate().getDayOfMonth()));//提交时间
                record.setEditDate( null == str.getRuleDate()?null : LocalDate.of(str.getRuleDate().getYear(), str.getRuleDate().getMonthValue(), str.getRuleDate().getDayOfMonth()) );//处理时间
                record.setArea( str.getArea() );//地区
                record.setProjectGroup( "" );//项目组
                record.setDayTask( ""  );//当天任务
                record.setAddContent( ""  );//补充内容
                record.setReimNumber( str.getRunNum() );//报销单号
                record.setReimer( str.getReimburseName() );//报销人
                record.setProject( str.getProject() );//项目
                record.setTicketNumber( str.getOddNumber() );//单据编号
                record.setTicketQuantity( (double) str.getBillNumber() );//单据数量
                record.setReimMoney( str.getTotal() );//报销总金额
                record.setReimerRemark( str.getRemark() );//报销人备注
                record.setTicketCondition( "是" );//是否有发票(是/否)
                record.setNoTicketRemark("" );//无发票备注
                record.setReceiveTicketer( str.getBiller() );//收票人
                record.setReceiveTicketTime(null == str.getBillDate()?null : LocalDate.of(str.getBillDate().getYear(), str.getBillDate().getMonthValue(), str.getBillDate().getDayOfMonth())  );//收票时间
                record.setReceiveTicketCon( str.getBillCase());//收到发票情况
                record.setReceiveTicketCheck( null != str.getBillDate() ? "是":"否" );//是否已收到单据(是/否)
                record.setAccountCheckPassOr(  str.getPayState()==1?"是":"否" );//帐务核对是否通过（null/是/否）
                record.setBudgetPayTime( null == str.getPlanTime()?null : LocalDate.of(str.getPlanTime().getYear(), str.getPlanTime().getMonthValue(), str.getPlanTime().getDayOfMonth()) );//预计付款时间
                record.setCharger( str.getAuditor() );//负责人
                record.setChargerAuditStatus( str.getAuditorState()==1?"通过":str.getAuditorState()==2?"不通过" :null );//负责人审核状态(通过/不通过)
                record.setChargerAuditTime( null == str.getAuditorTime()?null : LocalDate.of(str.getAuditorTime().getYear(), str.getAuditorTime().getMonthValue(), str.getAuditorTime().getDayOfMonth())  );//负责人审核时间
                record.setAuditAdvice( str.getAuditorIdea() );//负责人审核意见
                record.setChargerCongelTime( null );//负责人确认冻结或取消冻结的时间
                record.setChargerAuditStatus( null );//负责人确认冻结或取消冻结的意见
                record.setAnalisisIsAll(true );//是否全部分析完
                record.setPayCondition( str.getPayState()==1?"是":"否");//是否支付(是/否)
                record.setAttender( str.getParticipant());//参与人

                AjoaSubjectDTO subjectDTO = new AjoaSubjectDTO();
                subjectDTO.getConditions().add(Restrict.eq("id",str.getSub()));
                AjoaSubject sub = subjectSer.findOne( subjectDTO );
                record.setFirstSubject( sub.getSort());//一级科目
                record.setSecondSubject( sub.getClassify() );//二级科目
                record.setThirdSubject( sub.getDetail() );//三级科目
                record.setPlainInfo( sub.getDefine()  );//说明

                AjoaMattersDTO mattersDTO = new AjoaMattersDTO();
                mattersDTO.getConditions().add(Restrict.eq("reimburse_id",str.getId() ));
                List<AjoaMatters> materList = ajoaMattersSer.findByCis( mattersDTO );
                String mt = "";
                if ( materList != null && materList.size()>0 ){
                    mt = String.join(",",materList.stream().map(AjoaMatters::getMatter).collect(Collectors.toList()));
                }
                record.setSummary(  mt );//摘要
                record.setPayPlan( str.getPayPlan());//支付计划
                if( null != str.getPayTime()) {
                    record.setPayTime(LocalDate.of(str.getPayTime().getYear(), str.getPayTime().getMonthValue(), str.getPayTime().getDayOfMonth()));//支付时间
                }else{
                    record.setPayTime(null);
                }
                record.setReimStatus(ReimStatus.CHARGEPASS );//状态
                record.setAccountFlag( "否" );//是否已生成记账凭证(是/否)
                record.setPayOrigin( str.getPayment() );//付款来源
                record.setSendRecevier( str.getBiller() );//寄件的收件人
                record.setSender( str.getReimburseName() );//寄件人

                if( null != str.getBillDate()){
                    record.setSendDate(LocalDate.of(str.getBillDate().getYear(),str.getBillDate().getMonthValue(),str.getBillDate().getDayOfMonth()) );//寄件日期
                }else{
                    record.setSendDate(null);
                }
                record.setSendCondition( str.getBillCase() );//寄件情况
                record.setReceiveArea( str.getArea());//收件地区
                record.setReceiveAddr( str.getArea() );//收件地址

                record = reimburseRecordSer.save( record );

                AjoaFinanceAuditLogDTO logDTO = new AjoaFinanceAuditLogDTO();
                logDTO.getConditions().add(Restrict.eq("auditId", str.getId() ));
                List<AjoaFinanceAuditLog> logList = logSer.findByCis( logDTO );

                if( logList!= null && logList.size()>0 ) {
                    for(AjoaFinanceAuditLog lo: logList  ) {
                        ReimburseAuditLog reimburseRecordLog = new ReimburseAuditLog();
                        reimburseRecordLog.setContent(
                                (lo.getAuditState()==0?"未处理":lo.getAuditState()==1?"通过":"拒绝")+lo.getAuditorIdea() );
                        AjoaUserDTO userDTO = new AjoaUserDTO();
                        userDTO.getConditions().add(Restrict.eq("username",lo.getAuditorName().trim()));
                        List<AjoaUser> userBO = ajoaUserSer.findByCis( userDTO );
                        reimburseRecordLog.setEmpNum( userBO!= null && userBO.size()>0 ?userBO.get(0).getEmployeeNumber():"" );
                        reimburseRecordLog.setPosition( lo.getPositionName() );
                        reimburseRecordLog.setUserName( lo.getAuditorName() );
                        reimburseRecordLog.setReimrecordId(record.getId());
                        reimburseRecordLog.setCreateTime( lo.getCreateTime() );
                        reimburseRecordLog.setModifyTime( lo.getModifyTime() );
                        //NONE(0),未处理-ALLOWED(1)通过-DENIED(2)//拒绝
                        reimburseRecordLog.setAuditStatus( lo.getAuditState()==0?"未处理":lo.getAuditState()==1?"分析通过":"分析不通过" );
                        if( lo.getAuditTime()!=null){
                            reimburseRecordLog.setAuditTime( LocalDate.of(lo.getAuditTime().getYear(),lo.getAuditTime().getMonthValue(),lo.getAuditTime().getDayOfMonth()) );
                        }

                        recordLogList.add(reimburseRecordLog);
                    }

                }


//                recordList.add( record );

            }

            if( recordLogList!= null && recordLogList.size()>0 ){
                recordLogSer.save( recordLogList );
            }
//            if( recordList!= null && recordList.size()>0 ){
//                reimburseRecordSer.save( recordList );
//            }
        }
    }
}