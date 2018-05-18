package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.lendreimbursement.dao.ReimbursementRep;
import com.bjike.goddess.lendreimbursement.dto.ReimbursementDTO;
import com.bjike.goddess.lendreimbursement.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 报销业务实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-12 03:02 ]
 * @Description: [ 报销业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class ReimbursementSerImpl extends ServiceImpl<Reimbursement, ReimbursementDTO> implements ReimbursementSer {

    @Autowired
    private ReimbursementRep reimbursementRep;

    @Override
    public void reimbursementAdd(String data) throws IOException, SerException {
        Reimbursement reimbursement = WanyJackson.superman(data, Reimbursement.class);
        reimbursement.setNowProgress("待审核");
        super.save(reimbursement);
    }

    @Override
    public MyPage getReimbursementPage(Integer pageNum,String progress) {
        Pageable pageable = new PageRequest(pageNum - 1, 10);
        Page<Reimbursement> page = null;
        if ("待分析".equals(progress)) {
            page = reimbursementRep.findAllByNeedAnalysis(pageable, progress);
        } else {
            page = reimbursementRep.findAllByNowProgress(pageable, progress);
        }
        MyPage myPage = new MyPage();
        for (int i = 0; i < page.getContent().size(); i++) {
            Reimbursement reimbursement = page.getContent().get(i);
            reimbursement.setCreateTime(null);
            reimbursement.setModifyTime(null);
            if (reimbursement.getAuditings() != null) {
                for (AuditingByReim auditingByReim : reimbursement.getAuditings()) {
                    auditingByReim.setCreateTime(null);
                    auditingByReim.setModifyTime(null);
                }
            }
            if (reimbursement.getMailings() != null) {
                for (mailingByReim mailingByReim : reimbursement.getMailings()) {
                    mailingByReim.setCreateTime(null);
                    mailingByReim.setModifyTime(null);
                }
            }
        }
        myPage.setContent(page.getContent());
        myPage.setTotalElements(page.getTotalElements());
        return myPage;
    }

    @Override
    public void reimbursementEdit(String data, String source) throws IOException, SerException {
        Reimbursement reimbursement = WanyJackson.superman(data, Reimbursement.class);
        int i = 0;
        if (reimbursement.getAuditings() != null) {
            i = reimbursement.getAuditings().size()-1;
        }
        if ("审核".equals(source)) {
            if (reimbursement.getAuditings().get(i).getPass()) {
                reimbursement.setNowProgress("待核对");
            } else {
                reimbursement.setLastProgress("待核对");
                reimbursement.setNowProgress("有误记录");
            }
        } else if ("分析".equals(source)) {

        } else if ("核对".equals(source)) {
            System.out.println("核对if----"+reimbursement);
            if (reimbursement.getAuditings().get(i).getPass()) {
                reimbursement.setLastProgress(reimbursement.getNowProgress());
                reimbursement.setNowProgress("待付款");
            } else {
                reimbursement.setLastProgress(reimbursement.getNowProgress());
                reimbursement.setNowProgress("有误记录");
            }

        } else if ("付款".equals(source)) {
            if (reimbursement.getAuditings().get(i).getPass()) {
                reimbursement.setLastProgress(reimbursement.getNowProgress());
                reimbursement.setNowProgress("已完成");
            } else {
                reimbursement.setLastProgress(reimbursement.getNowProgress());
                reimbursement.setNowProgress("有误记录");
            }
        } else if ("已完成".equals(source)) {

        } else if ("有误记录".equals(source)) {
            reimbursement.setNowProgress(reimbursement.getLastProgress());
        }
        super.update(reimbursement);
    }

    @Override
    public void reimbursementDel(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public Reimbursement getReimbursementById(String id) throws SerException {
        Reimbursement reimbursement = super.findById(id);
        reimbursement.setCreateTime(null);
        reimbursement.setModifyTime(null);
        if (reimbursement.getMailings() != null) {
            for (mailingByReim mailingByReim : reimbursement.getMailings()) {
                mailingByReim.setCreateTime(null);
                mailingByReim.setModifyTime(null);
            }
        }
        if (reimbursement.getAuditings() != null) {
            for (AuditingByReim auditingByReim : reimbursement.getAuditings()) {
                auditingByReim.setModifyTime(null);
                auditingByReim.setCreateTime(null);
            }
        }
        return reimbursement;
    }
}