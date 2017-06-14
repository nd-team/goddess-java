package com.bjike.goddess.checkfunds.service;

import com.bjike.goddess.checkfunds.bo.BankReconciliationBO;
import com.bjike.goddess.checkfunds.bo.PassAuditBO;
import com.bjike.goddess.checkfunds.dto.PassAuditDTO;
import com.bjike.goddess.checkfunds.entity.PassAudit;
import com.bjike.goddess.checkfunds.to.PassAuditTO;
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
 * 已完成核对记录业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:18 ]
 * @Description: [ 已完成核对记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkfundsSerCache")
@Service
public class PassAuditSerImpl extends ServiceImpl<PassAudit, PassAuditDTO> implements PassAuditSer {
    @Autowired
    private BankReconciliationSer bankReconciliationSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PassAuditBO save(PassAuditTO to) throws SerException {
        PassAudit entity = BeanTransform.copyProperties(to, PassAudit.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, PassAuditBO.class);
    }

    @Override
    public List<PassAuditBO> list(PassAuditDTO dto) throws SerException {
        List<PassAudit> list = super.findByCis(dto, true);
        List<PassAuditBO> boList = new ArrayList<PassAuditBO>();
        for (PassAudit entity : list) {
            BankReconciliationBO bankReconciliationBO = bankReconciliationSer.findByID(entity.getId());
            PassAuditBO bo = BeanTransform.copyProperties(bankReconciliationBO, PassAuditBO.class);
            bo.setAuditStatus(entity.getAuditStatus());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public Long countNum(PassAuditDTO dto) throws SerException {
        return super.count(dto);
    }
}