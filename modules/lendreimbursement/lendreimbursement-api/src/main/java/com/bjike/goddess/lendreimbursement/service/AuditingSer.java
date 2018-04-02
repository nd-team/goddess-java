package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.lendreimbursement.dto.AuditingDTO;
import com.bjike.goddess.lendreimbursement.entity.Auditing;
import com.bjike.goddess.lendreimbursement.entity.MyPage;

/**
 * 审核详情业务接口
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-02 09:39 ]
 * @Description: [ 审核详情业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AuditingSer extends Ser<Auditing, AuditingDTO> {

    Auditing getAuditing(String id) throws SerException;

    MyPage getAuditingPage(Integer pageNum, String auditingId);

}