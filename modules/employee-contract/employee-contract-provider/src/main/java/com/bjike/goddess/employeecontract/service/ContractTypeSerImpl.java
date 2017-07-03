package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.ContractTypeBO;
import com.bjike.goddess.employeecontract.dto.ContractManageDTO;
import com.bjike.goddess.employeecontract.dto.ContractTypeDTO;
import com.bjike.goddess.employeecontract.entity.ContractType;
import com.bjike.goddess.employeecontract.to.ContractTypeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:55 ]
 * @Description: [ 合同类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employeecontractSerCache")
@Service
public class ContractTypeSerImpl extends ServiceImpl<ContractType, ContractTypeDTO> implements ContractTypeSer {

    @Autowired
    private ContractManageSer contractManageSer;

    @Override
    public ContractTypeBO save(ContractTypeTO to) throws SerException {
        ContractType entity = BeanTransform.copyProperties(to, ContractType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ContractTypeBO.class);
    }

    @Override
    public ContractTypeBO update(ContractTypeTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractType entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, ContractTypeBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractTypeBO delete(String id) throws SerException {
        ContractType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        ContractManageDTO dto = new ContractManageDTO();
        dto.getConditions().add(Restrict.eq("type.id", id));
        if (contractManageSer.findByCis(dto).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ContractTypeBO.class);
    }

    @Override
    public ContractTypeBO congeal(String id) throws SerException {
        ContractType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ContractTypeBO.class);
    }

    @Override
    public ContractTypeBO thaw(String id) throws SerException {
        ContractType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ContractTypeBO.class);
    }

    @Override
    public List<ContractTypeBO> findThaw() throws SerException {
        ContractTypeDTO dto = new ContractTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<ContractType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ContractTypeBO.class);
    }

    @Override
    public List<ContractTypeBO> maps(ContractTypeDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), ContractTypeBO.class);
    }

    @Override
    public ContractTypeBO getById(String id) throws SerException {
        ContractType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        return BeanTransform.copyProperties(entity, ContractTypeBO.class);
    }

}