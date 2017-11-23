package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaLendDTO;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaSubjectDTO;
import com.bjike.goddess.lendreimbursement.entity.ApplyLend;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaLend;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaSubject;
import com.bjike.goddess.lendreimbursement.enums.LendRetunStatus;
import com.bjike.goddess.lendreimbursement.enums.LendStatus;
import com.bjike.goddess.lendreimbursement.service.ApplyLendSer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 老系统的借款业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的借款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class AjoaLendSerImpl extends ServiceImpl<AjoaLend, AjoaLendDTO> implements AjoaLendSer {

    @Autowired
    private ApplyLendSer applyLendSer;
    @Autowired
    private AjoaSubjectSer ajoaSubjectSer;

    @Override
    public void importLendOldData() throws SerException {
        List<ApplyLend> lendList = new ArrayList<>();
        List<AjoaLend> list = super.findAll();
        if( list != null && list.size()>0 ){
            for( AjoaLend str : list ){
                ApplyLend lend = new ApplyLend();
                lend.setEstimateLendDate(DateUtil.parseDate(str.getPlanDate()));//预计借款日期
                lend.setLender(str.getBorrower());//借款人
                lend.setCharger(str.getAuditor());//负责人
                lend.setArea(str.getArea());//地区
                lend.setProjectGroup("");//项目组
                lend.setProjectName(str.getProject());//项目名称
                lend.setAttender(str.getUsername());//参与人员
                lend.setLendWay(str.getLendWay());//借款方式

                AjoaSubjectDTO subjectDTO = new AjoaSubjectDTO();
                subjectDTO.getConditions().add(Restrict.eq("id",str.getSub_id()));
                AjoaSubject subject = ajoaSubjectSer.findOne( subjectDTO );
                lend.setFirstSubject(subject.getSubject() );//一级科目
                lend.setSecondSubject( subject.getDetail() );//二级科目
                lend.setThirdSubject( subject.getNode() );//三级科目
                lend.setExplains( subject.getDefine() );//说明

                lend.setWriteUp("否");//是否补写(是/否)
                lend.setWriteUpCondition("无");//补写情况
                lend.setLendReson(str.getReason());//借款事由
                lend.setMoney(str.getMoney());//金额
                //TODO
                lend.setInvoice("是");//是否有发票（是/否）

                lend.setNoInvoiceRemark("");//无发票备注
                lend.setGoodsLink(str.getUrl());//商品链接
                lend.setRemark(str.getRemark());//备注
                lend.setFillSingler(str.getWriter());//填单人
                //TODO
                lend.setLendDate(StringUtils.isNotBlank(str.getLendDate())?DateUtil.parseDate(str.getLendDate()):null);//借款日期(付款后才有，相当于付款日期)

                lend.setChargerOpinion(str.getManageIdea());//负责人审核意见
                //TODO
                lend.setChargerPass(str.getManageState()==1?"是":"否");//负责人是否通过（是/否）
                lend.setChargerPassTime(str.getManageDate());//负责人审核时间
                lend.setFinacer(str.getFinance());//财务运营部
                lend.setFincerOpinion(str.getFinanceIdea());//财务运营部审核意见
                lend.setFincerPass(str.getFundState()==1?"是":"否");//财务运营部是否通过（是/否）
                lend.setFincerPassTime(str.getFinanceDate());//财务运营部审核时间
                lend.setManager(str.getLeader());//总经办
                lend.setManagerOpinion(str.getLeadIdea());//总经办审核意见
                lend.setManagerPass(str.getLeadState()==1?"是":"否");//总经办是否通过（是/否）
                lend.setManagerPassTime(str.getLeadDate());//总经办审核时间

                lend.setProxyAuditRemark(str.getContent());//代理审核备注
                //TODO
                lend.setPayCondition( StringUtils.isNotBlank(str.getPayer())? "是":"否" );//是否付款（是/否）
                lend.setPayer(str.getPayer());//付款人
                if(StringUtils.isNoneBlank(str.getPayDate())){
                    LocalDateTime time = DateUtil.parseDateTime(str.getPayDate());
                    lend.setPayDate( LocalDate.of(time.getYear(),time.getMonthValue(),time.getDayOfMonth()) );//确认已收款时间
                }else{
                    lend.setPayDate( null );//确认已收款时间
                }
                //TODO
                lend.setPayOrigin( str.getPayment() );//支付来源

                lend.setDocumentQuantity(str.getBillNum());//单据数量
                lend.setDocumentCondition(StringUtils.isNotBlank(str.getBillDate())? "是":"否" );//是否收到单据（是/否）
                lend.setReimMoney( str.getRealMoney() );//报销金额
                lend.setLendMoney( str.getPayMoney() );//借款金额
                lend.setReturnMoney( str.getQuitMoney() );//退回金额
                lend.setReturnDate(StringUtils.isNoneBlank(str.getQuitDate())? DateUtil.parseDate(str.getQuitDate()) :null );//退回日期
                lend.setReturnWays( str.getRepayWay() );//归还方式
                lend.setReturnAccount( str.getBank() );//归还账户
                lend.setReturnRemark( "" );//还款说明
                lend.setSendRecevier( str.getBiller() );//寄件的收件人
                lend.setSender( str.getSender() );//寄件人
                lend.setSendDate( StringUtils.isNotBlank(str.getSendDate())?DateUtil.parseDate(str.getSendDate()):null );//寄件日期
                lend.setSendCondition( str.getSendCase() );//寄件情况
                lend.setReceiveArea( str.getAddress() );//收件地区(寄件的时候填的地区)
                lend.setReceiveAddr( str.getAddress());//收件地址
                lend.setTicketer( str.getBiller() );//收票人
                lend.setTicketDate( StringUtils.isNotBlank(str.getBillDate())?DateUtil.parseDate(str.getBillDate()):null );//收票日期
                lend.setTicketCondition( str.getBillCase() );//收票情况
                lend.setReceiveTicket( null ==lend.getTicketDate()? "是":"否" );//是否已收票（是/否）
                //可填可不填
                if( StringUtils.isNotBlank(str.getBillDate()) ) {
                    lend.setChecker(str.getBiller());//核对人
                    lend.setCheckDate(DateUtil.parseDate(str.getBillDate()));//核对日期
                    lend.setCheckcontent(str.getBillCase());//核对内容
                }

                lend.setLendRetunStatus("是".equals(lend.getDocumentCondition())?LendRetunStatus.WAITRETURNCHECK:LendRetunStatus.NOTPASS);//还款核对状态
                if( "否".equals(lend.getChargerPass()) ){
                    lend.setLendStatus(LendStatus.CHARGENOTPASS);//状态
                }else if(  ("是".equals(lend.getChargerPass()) && "是".equals(lend.getFincerPass())) ||
                        ("是".equals(lend.getChargerPass()) && "否".equals(lend.getFincerPass()) && "是".equals(lend.getManagerPass())) ){
                    lend.setLendStatus(LendStatus.MANAGEPASS);//状态
                }else if("是".equals(lend.getChargerPass()) && "否".equals(lend.getFincerPass()) && "否".equals(lend.getManagerPass()) ){
                    lend.setLendStatus(LendStatus.MANAGENOTPASS);//状态
                }
                lend.setLendError(0 );//申请单有误状态标识
                lend.setReceivePay( StringUtils.isNoneBlank(str.getPayDate())?"是" :"否");//是否已收款（是/否）是否确认收款
                if(StringUtils.isNoneBlank(str.getPayDate())){
                    LocalDateTime time = DateUtil.parseDateTime(str.getPayDate());
                    lend.setReceivePayTime( LocalDate.of(time.getYear(),time.getMonthValue(),time.getDayOfMonth()) );//确认已收款时间
                }else{
                    lend.setReceivePayTime( null );//确认已收款时间
                }
                lend.setCommitTime( str.getModifyTime());//提交时间(填单人的操作)
                lendList.add( lend );
            }

            if( lendList != null && lendList.size()>0 ){
                applyLendSer.save( lendList );
            }
        }
    }

}