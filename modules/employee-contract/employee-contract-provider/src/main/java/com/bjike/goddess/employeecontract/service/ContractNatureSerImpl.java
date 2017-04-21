package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.ContractNatureBO;
import com.bjike.goddess.employeecontract.dto.ContractNatureDTO;
import com.bjike.goddess.employeecontract.entity.ContractNature;
import com.bjike.goddess.employeecontract.to.ContractNatureTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同性质业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employee-contractSerCache")
@Service
public class ContractNatureSerImpl extends ServiceImpl<ContractNature, ContractNatureDTO> implements ContractNatureSer {

    @Override
    public ContractNatureBO save(ContractNatureTO to) throws SerException {
        ContractNature entity = BeanTransform.copyProperties(to, ContractNature.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO update(ContractNatureTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractNature entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, ContractNatureBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractNatureBO delete(String id) throws SerException {
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO congeal(String id) throws SerException {
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO thaw(String id) throws SerException {
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public List<ContractNatureBO> findThaw() throws SerException {
        ContractNatureDTO dto = new ContractNatureDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<ContractNature> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ContractNatureBO.class);
    }

    @Override
    public List<ContractNatureBO> maps(ContractNatureDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO getById(String id) throws SerException {
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }
}