package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.ContractChangeBO;
import com.bjike.goddess.employeecontract.bo.ContractManageBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeDTO;
import com.bjike.goddess.employeecontract.entity.ContractChange;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同变更详细业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employeecontractSerCache")
@Service
public class ContractChangeSerImpl extends ServiceImpl<ContractChange, ContractChangeDTO> implements ContractChangeSer {

    @Autowired
    private ContractManageSer contractManageSer;

    @Override
    public ContractChangeBO transformBO(ContractChange entity) throws SerException {
        ContractChangeBO bo = BeanTransform.copyProperties(entity, ContractChangeBO.class);
        bo.setContractId(entity.getContract().getId());
        ContractManageBO manageBO = contractManageSer.getById(bo.getContractId());
        bo.setUsername(manageBO.getUsername());
        bo.setSerialNumber(manageBO.getSerialNumber());
        bo.setType(manageBO.getTypeName());
        bo.setNature(manageBO.getNatureName());
        return bo;
    }

    private List<ContractChangeBO> transformBOList(List<ContractChange> list) throws SerException {
        List<ContractChangeBO> bos = new ArrayList<>(list.size());
        for (ContractChange entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }


    @Override
    public ContractChangeBO update(ContractChangeTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractChange entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return this.transformBO(entity);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractChangeBO delete(String id) throws SerException {
        ContractChange entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<ContractChangeBO> maps(ContractChangeDTO dto) throws SerException {
        dto.getSorts().add("change=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        ContractChangeDTO dto = new ContractChangeDTO();
        return super.count(dto);
    }

    @Override
    public ContractChangeBO getById(String id) throws SerException {
        ContractChange entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        return this.transformBO(entity);
    }
}