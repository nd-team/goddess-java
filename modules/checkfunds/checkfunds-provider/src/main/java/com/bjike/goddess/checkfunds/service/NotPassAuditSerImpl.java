package com.bjike.goddess.checkfunds.service;


import com.bjike.goddess.checkfunds.bo.BankReconciliationBO;
import com.bjike.goddess.checkfunds.bo.NotPassAuditBO;
import com.bjike.goddess.checkfunds.dto.NotPassAuditDTO;
import com.bjike.goddess.checkfunds.entity.NotPassAudit;
import com.bjike.goddess.checkfunds.to.NotPassAuditTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 审批不通过记录业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:23 ]
 * @Description: [ 审批不通过记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkfundsSerCache")
@Service
public class NotPassAuditSerImpl extends ServiceImpl<NotPassAudit, NotPassAuditDTO> implements NotPassAuditSer {
    @Autowired
    private BankReconciliationSer bankReconciliationSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public NotPassAuditBO save(NotPassAuditTO to) throws SerException {
        NotPassAudit entity = BeanTransform.copyProperties(to, NotPassAudit.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, NotPassAuditBO.class);
    }

    @Override
    public List<NotPassAuditBO> list(NotPassAuditDTO dto) throws SerException {
        List<NotPassAudit> list = super.findByCis(dto, true);
        List<NotPassAuditBO> boList = new ArrayList<NotPassAuditBO>();
        for (NotPassAudit entity : list) {
            BankReconciliationBO bankReconciliationBO = bankReconciliationSer.findByID(entity.getBankReconciliationId());
            NotPassAuditBO bo = BeanTransform.copyProperties(bankReconciliationBO, NotPassAuditBO.class);
            bo.setAuditStatus(entity.getAuditStatus());
            bo.setId(entity.getId());
            bo.setBankReconciliationId(entity.getBankReconciliationId());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public Long countNum(NotPassAuditDTO dto) throws SerException {
        return super.count(dto);
    }
}

