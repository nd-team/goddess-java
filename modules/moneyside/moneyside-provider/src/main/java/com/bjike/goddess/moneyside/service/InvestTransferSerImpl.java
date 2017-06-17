package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.entity.InvestTransfer;
import com.bjike.goddess.moneyside.to.InvestTransferTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 投资转让业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class InvestTransferSerImpl extends ServiceImpl<InvestTransfer, InvestTransferDTO> implements InvestTransferSer {
    @Override
    public Long countInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        Long count = super.count(investTransferDTO);
        return count;
    }

    @Override
    public InvestTransferBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        InvestTransfer investTransfer = super.findById(id);
        return BeanTransform.copyProperties(investTransfer, InvestTransferBO.class);
    }

    @Override
    public List<InvestTransferBO> findListInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        List<InvestTransfer> investTransfers = super.findByPage(investTransferDTO);
        List<InvestTransferBO> investTransferBOS = BeanTransform.copyProperties(investTransfers, InvestTransferBO.class);
        return investTransferBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvestTransferBO insertInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        InvestTransfer investTransfer = BeanTransform.copyProperties(investTransferTO, InvestTransfer.class, true);
        investTransfer.setCreateTime(LocalDateTime.now());
        super.save(investTransfer);
        return BeanTransform.copyProperties(investTransfer, InvestTransferBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvestTransferBO editInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        if (StringUtils.isBlank(investTransferTO.getId())) {
            throw new SerException("id不能为空");
        }
        InvestTransfer investTransfer = super.findById(investTransferTO.getId());
        BeanTransform.copyProperties(investTransferTO, investTransfer, true);
        investTransfer.setModifyTime(LocalDateTime.now());
        super.update(investTransfer);
        return BeanTransform.copyProperties(investTransfer, InvestTransferBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeInvestTransfer(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}