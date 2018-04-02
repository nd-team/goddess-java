package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.lendreimbursement.dao.LoanRep;
import com.bjike.goddess.lendreimbursement.dto.LoanDTO;
import com.bjike.goddess.lendreimbursement.entity.Auditing;
import com.bjike.goddess.lendreimbursement.entity.Loan;
import com.bjike.goddess.lendreimbursement.entity.Mailing;
import com.bjike.goddess.lendreimbursement.entity.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * 借款业务实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-01 05:48 ]
 * @Description: [ 借款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class LoanSerImpl extends ServiceImpl<Loan, LoanDTO> implements LoanSer {

    @Autowired
    private LoanRep loanRep;

    @Transactional
    @Override
    public Loan getLoan(String id) throws SerException {
        Loan loan = super.findById(id);
        loan.setCreateTime(null);
        loan.setModifyTime(null);
        if (loan.getAuditings() != null) {
            List<Auditing> list = loan.getAuditings();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCreateTime(null);
                list.get(i).setModifyTime(null);
            }
        }

        if (loan.getMailings() != null) {
            Set<Mailing> set = loan.getMailings();
            for (Mailing mailing : set) {
                mailing.setCreateTime(null);
                mailing.setModifyTime(null);
            }
        }
        return loan;
    }

    @Transactional
    @Override
    public MyPage getLoanPage(Integer pageNum,String progress) {
        Pageable pageable = new PageRequest(pageNum - 1, 10);
        Page<Loan> page = loanRep.findAllByNowProgress(pageable,progress);
        MyPage myPage = new MyPage();
        for (int i = 0; i < page.getContent().size(); i++) {
            page.getContent().get(i).setCreateTime(null);
            page.getContent().get(i).setModifyTime(null);
            if (page.getContent().get(i).getAuditings() != null) {
                List<Auditing> list = page.getContent().get(i).getAuditings();
                for (int o = 0; o < list.size(); o++) {
                    list.get(o).setCreateTime(null);
                    list.get(o).setModifyTime(null);
                }
            }
            if (page.getContent().get(i).getMailings() != null) {
                Set<Mailing> set = page.getContent().get(i).getMailings();
                for (Mailing mailing : set) {
                    mailing.setCreateTime(null);
                    mailing.setModifyTime(null);
                }
            }
        }
        myPage.setContent(page.getContent());
        myPage.setTotalElements(page.getTotalElements());
        return myPage;
    }

    @Override
    public void loanSave(String data) throws IOException, SerException {
        Loan loan = WanyJackson.superman(data, Loan.class);
        loan.setFillTime(LocalDate.now().toString());
        loan.setNeedAnalysis("待分析");
        loan.setNowProgress("待审核");
        super.save(loan);
    }

    @Override
    public void loanUpdate(String data,String source) throws IOException, SerException {
        Loan loan = WanyJackson.superman(data, Loan.class);
        if ("审核".equals(source)) {
            int i = loan.getAuditings().size();
            Auditing auditing = loan.getAuditings().get(i - 1);
            auditing.setTime(LocalDate.now().toString());
            auditing.setAuditor(loan.getUser());
            if (auditing.getPass()) {
                loan.setLastProgress(loan.getNowProgress());
                loan.setNowProgress("待付款");
            } else {
                loan.setLastProgress(loan.getNowProgress());
                loan.setNowProgress("有误记录");
            }

        } else if ("付款".equals(source)) {
            loan.setLastProgress(loan.getNowProgress());
            loan.setNowProgress("待确认");
        } else if ("确认收款".equals(source)) {
            loan.setLastProgress(loan.getNowProgress());
            loan.setNowProgress("待还款");
        } else if ("还款".equals(source)) {
            loan.setLastProgress(loan.getNowProgress());
            loan.setNowProgress("待核对");
        } else if ("核对".equals(source)) {
            loan.setLastProgress(loan.getNowProgress());
            loan.setNowProgress("已完成");
        } else if ("待审核".equals(source)) {
            loan.setNowProgress("已完成");
        } else if ("待分析".equals(source)) {
            loan.setNeedAnalysis("已分析");

        } else if ("有误编辑".equals(source)) {
            loan.setNowProgress(loan.getLastProgress());
        }
        super.update(loan);
    }

    @Override
    public void loanDel(String id) throws SerException {
        super.remove(id);
    }

    @Transactional
    @Override
    public MyPage getAnalysisLoan(Integer pageNum, String analysis) {
        Pageable pageable = new PageRequest(pageNum - 1, 10);
        Page<Loan> page = loanRep.findAllByNeedAnalysis(pageable, analysis);
        MyPage myPage = new MyPage();
        for (int i = 0; i < page.getContent().size(); i++) {
            Loan loan = page.getContent().get(i);
            loan.setCreateTime(null);
            loan.setModifyTime(null);
            if (loan.getAuditings() != null) {
                for (int o=0;o<loan.getAuditings().size();o++) {
                    Auditing auditing = loan.getAuditings().get(o);
                    auditing.setCreateTime(null);
                    auditing.setModifyTime(null);
                }
            }
        }
        myPage.setContent(page.getContent());
        myPage.setTotalElements(page.getTotalElements());
        return myPage;
    }

}