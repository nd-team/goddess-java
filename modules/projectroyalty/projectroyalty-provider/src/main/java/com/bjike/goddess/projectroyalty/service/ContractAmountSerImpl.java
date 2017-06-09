package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.ContractAmountBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.ContractAmountDTO;
import com.bjike.goddess.projectroyalty.entity.ContractAmount;
import com.bjike.goddess.projectroyalty.to.ContractAmountTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同金额业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:45 ]
 * @Description: [ 合同金额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class ContractAmountSerImpl extends ServiceImpl<ContractAmount, ContractAmountDTO> implements ContractAmountSer {

    @Override
    public ContractAmountBO save(ContractAmountTO to) throws SerException {
        ContractAmount entity = BeanTransform.copyProperties(to, ContractAmount.class);
        ContractAmountDTO dto = new ContractAmountDTO();
        dto.getConditions().add(Restrict.eq("contract", to.getContract()));
        if (super.count(dto) != 0)
            throw new SerException(to.getContract() + ":已存在");
        super.save(entity);
        return BeanTransform.copyProperties(entity, ContractAmountBO.class);
    }

    @Override
    public ContractAmountBO update(ContractAmountTO to) throws SerException {
        ContractAmount entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        if (to.getContract().doubleValue() != entity.getContract().doubleValue()) {
            ContractAmountDTO dto = new ContractAmountDTO();
            dto.getConditions().add(Restrict.eq("contract", to.getContract()));
            if (super.count(dto) != 0)
                throw new SerException(to.getContract() + ":已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, ContractAmountBO.class);
    }

    @Override
    public ContractAmountBO delete(String id) throws SerException {
        ContractAmount entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ContractAmountBO.class);
    }

    @Override
    public ContractAmountBO getById(String id) throws SerException {
        ContractAmount entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, ContractAmountBO.class);
    }

    @Override
    public List<ContractAmountBO> maps(ContractAmountDTO dto) throws SerException {
        dto.getSorts().add("contract=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), ContractAmountBO.class);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        List<OpinionBO> bos = new ArrayList<>(0);
        List<ContractAmount> list = super.findAll();
        for (ContractAmount entity : list)
            bos.add(new OpinionBO(entity.getId(), entity.getContract().toString()));
        return bos;
    }

    @Override
    public Long getTotal() throws SerException {
        ContractAmountDTO dto = new ContractAmountDTO();
        return super.count(dto);
    }
}