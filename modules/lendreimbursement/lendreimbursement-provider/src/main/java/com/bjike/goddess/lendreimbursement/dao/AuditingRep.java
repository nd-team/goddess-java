package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.AuditingDTO;
import com.bjike.goddess.lendreimbursement.entity.Auditing;
import com.bjike.goddess.lendreimbursement.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 审核详情持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-02 09:39 ]
 * @Description: [ 审核详情持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AuditingRep extends JpaRep<Auditing, AuditingDTO> {

    Page<Auditing> findAllByAuditing(Pageable pageable, String auditing_Id);

}