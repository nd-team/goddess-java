package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.*;
import com.bjike.goddess.employeecontract.dto.ContractManageDTO;
import com.bjike.goddess.employeecontract.entity.ContractManage;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;
import com.bjike.goddess.employeecontract.to.ContractInfoTO;
import com.bjike.goddess.employeecontract.to.ContractManageTO;
import com.bjike.goddess.employeecontract.to.ContractPersonalTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employee-contractSerCache")
@Service
public class ContractManageSerImpl extends ServiceImpl<ContractManage, ContractManageDTO> implements ContractManageSer {

    @Autowired
    private ContractNatureSer contractNatureSer;
    @Autowired
    private ContractTypeSer contractTypeSer;
    @Autowired
    private ContractChangeSer contractChangeSer;

    private ContractManageBO transformBO(ContractManage entity) throws SerException {
        ContractManageBO bo = BeanTransform.copyProperties(entity, ContractManageBO.class);
        bo.setTypeId(entity.getType().getId());
        bo.setTypeName(entity.getType().getType());
        bo.setNatureId(entity.getNature().getId());
        bo.setNatureName(entity.getNature().getNature());
        return bo;
    }

    private List<ContractManageBO> transformBOList(List<ContractManage> list) throws SerException {
        List<ContractManageBO> bos = new ArrayList<>(list.size());
        for (ContractManage entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public ContractManageBO save(ContractManageTO to) throws SerException {
        ContractManage entity = BeanTransform.copyProperties(to, ContractManage.class, true);
        entity.setNature(contractNatureSer.findById(to.getNature_id()));
        entity.setType(contractTypeSer.findById(to.getType_id()));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public ContractManageBO updateDetail(ContractManageTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractManage entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setNature(contractNatureSer.findById(to.getNature_id()));
            entity.setType(contractTypeSer.findById(to.getType_id()));
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return this.transformBO(entity);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractInfoBO updateInfo(ContractInfoTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractManage entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            entity.setNature(contractNatureSer.findById(to.getNature_id()));
            entity.setType(contractTypeSer.findById(to.getType_id()));
            super.update(entity);
            ContractManageBO bo = this.transformBO(entity);
            return BeanTransform.copyProperties(bo, ContractInfoBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractPersonalBO updatePersonal(ContractPersonalTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractManage entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            ContractManageBO bo = this.transformBO(entity);
            return BeanTransform.copyProperties(bo, ContractPersonalBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractInfoBO affirm(String id) throws SerException {
        ContractManage entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        entity.setStatus(false);
        super.update(entity);
        ContractManageBO manageBO = this.transformBO(entity);
        return BeanTransform.copyProperties(manageBO, ContractInfoBO.class);
    }

    @Override
    public ContractManageBO delete(String id) throws SerException {
        ContractManage entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public ContractManageBO getById(String id) throws SerException {
        ContractManage entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        return this.transformBO(entity);
    }

    @Override
    public List<ContractPersonalBO> personalMaps(ContractManageDTO dto) throws SerException {

        return null;
    }

    @Override
    public List<ContractInfoBO> infoMaps(ContractManageDTO dto) throws SerException {
        return null;
    }

    @Override
    public Long getPersonalTotal() throws SerException {
        return null;
    }

    @Override
    public Long getInfoTotal() throws SerException {
        return null;
    }

    @Override
    public List<ContractManageChoiceBO> getChoice() throws SerException {
        return null;
    }

    @Override
    public List<ContractManageBO> findStatus() throws SerException {
        return null;
    }

    @Override
    public ContractChangeBO saveChange(ContractChangeTO to) throws SerException {
        return null;
    }
}