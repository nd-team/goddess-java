package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.MailingDTO;
import com.bjike.goddess.lendreimbursement.entity.Auditing;
import com.bjike.goddess.lendreimbursement.entity.Mailing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 寄件信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-03 03:23 ]
 * @Description: [ 寄件信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MailingRep extends JpaRep<Mailing, MailingDTO> {
    Page<Mailing> findAllByMailing(Pageable pageable, String auditing_Id);
}