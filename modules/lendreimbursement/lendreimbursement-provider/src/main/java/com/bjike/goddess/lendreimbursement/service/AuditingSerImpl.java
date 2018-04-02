package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dao.AuditingRep;
import com.bjike.goddess.lendreimbursement.dto.AuditingDTO;
import com.bjike.goddess.lendreimbursement.entity.Auditing;
import com.bjike.goddess.lendreimbursement.entity.Loan;
import com.bjike.goddess.lendreimbursement.entity.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 审核详情业务实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-02 09:39 ]
 * @Description: [ 审核详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class AuditingSerImpl extends ServiceImpl<Auditing, AuditingDTO> implements AuditingSer {

    @Autowired
    private AuditingRep auditingRep;

    @Override
    public Auditing getAuditing(String id) throws SerException {
        Auditing auditing = super.findById("212");
        auditing.setCreateTime(null);
        auditing.setModifyTime(null);
        return auditing;
    }

    @Override
    public MyPage getAuditingPage(Integer pageNum,String auditing_Id) {
        Pageable pageable = new PageRequest(pageNum - 1, 10);
        Page<Auditing> page = auditingRep.findAllByAuditing(pageable, auditing_Id);
        MyPage myPage = new MyPage();
        for (int i = 0; i < page.getContent().size(); i++) {
            page.getContent().get(i).setCreateTime(null);
            page.getContent().get(i).setModifyTime(null);
        }
        myPage.setContent(page.getContent());
        myPage.setTotalElements(page.getTotalElements());
        return myPage;
    }
}