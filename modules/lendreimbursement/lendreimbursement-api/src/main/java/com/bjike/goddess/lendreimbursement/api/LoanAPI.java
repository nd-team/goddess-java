package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.entity.Loan;
import com.bjike.goddess.lendreimbursement.entity.MyPage;

import java.io.IOException;

/**
 * 借款业务接口
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-01 05:48 ]
 * @Description: [ 借款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LoanAPI {

    Loan getLoan(String id) throws SerException;

    MyPage getLoanPage(Integer pageNum,String progress);

    void loanSave(String data) throws IOException, SerException;

    void loanUpdate(String data, String source) throws IOException, SerException;

    void loanDel(String id) throws SerException;

    MyPage getAnalysisLoan(Integer pageNum, String analysis);


}