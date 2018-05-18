package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.ReimbursementDTO;
import com.bjike.goddess.lendreimbursement.entity.Loan;
import com.bjike.goddess.lendreimbursement.entity.Reimbursement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 报销持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-12 03:02 ]
 * @Description: [ 报销持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReimbursementRep extends JpaRep<Reimbursement, ReimbursementDTO> {

    Page<Reimbursement> findAll(Pageable pageable);

    Page<Reimbursement> findAllByNowProgress(Pageable pageable, String progress);

    Page<Reimbursement> findAllByNeedAnalysis(Pageable pageable, String needAnalysis);

}