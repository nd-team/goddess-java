package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.entity.Loan;
import com.bjike.goddess.lendreimbursement.entity.MyPage;
import com.bjike.goddess.lendreimbursement.service.LoanSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 借款业务接口实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-01 05:48 ]
 * @Description: [ 借款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("loanApiImpl")
public class LoanApiImpl implements LoanAPI {

    @Autowired
    private LoanSer loanSer;

    @Override
    public Loan getLoan(String id) throws SerException {
        return loanSer.getLoan(id);
    }

    @Override
    public MyPage getLoanPage(Integer pageNum,String progress) {
        return loanSer.getLoanPage(pageNum, progress);
    }

    @Override
    public void loanSave(String data) throws IOException, SerException {
        loanSer.loanSave(data);
    }

    @Override
    public void loanUpdate(String data, String source) throws IOException, SerException {
        loanSer.loanUpdate(data, source);
    }

    @Override
    public void loanDel(String id) throws SerException {
        loanSer.loanDel(id);
    }

    @Override
    public MyPage getAnalysisLoan(Integer pageNum, String analysis) {
        return loanSer.getAnalysisLoan(pageNum, analysis);
    }
}