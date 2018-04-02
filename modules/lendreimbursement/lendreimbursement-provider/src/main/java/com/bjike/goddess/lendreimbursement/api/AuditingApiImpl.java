package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.entity.Auditing;
import com.bjike.goddess.lendreimbursement.entity.MyPage;
import com.bjike.goddess.lendreimbursement.service.AuditingSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 审核详情业务接口实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-02 09:39 ]
 * @Description: [ 审核详情业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("auditingApiImpl")
public class AuditingApiImpl implements AuditingAPI {

    @Autowired
    private AuditingSer auditingSer;

    @Override
    public Auditing getAuditing(String id) throws SerException {
        return auditingSer.getAuditing(id);
    }

    @Override
    public MyPage getAuditingPage(Integer pageNum, String auditingId) {
        return auditingSer.getAuditingPage(pageNum, auditingId);
    }
}