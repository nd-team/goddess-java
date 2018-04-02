package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.LoanDTO;
import com.bjike.goddess.lendreimbursement.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 借款持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-01 05:48 ]
 * @Description: [ 借款持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LoanRep extends JpaRep<Loan, LoanDTO> {

    Page<Loan> findAll(Pageable pageable);

    Page<Loan> findAllByNowProgress(Pageable pageable, String progress);

    Page<Loan> findAllByNeedAnalysis(Pageable pageable, String needAnalysis);

}