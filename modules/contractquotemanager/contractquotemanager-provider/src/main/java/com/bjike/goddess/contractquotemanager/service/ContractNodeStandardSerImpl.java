package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dao.ContractNodeStandardRep;
import com.bjike.goddess.contractquotemanager.dao.ContractQuoteDataRep;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractNodeStandard;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同节点标准信息业务实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.725 ]
 * @Description: [ 合同节点标准信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractNodeStandardSerCache")
@Service
public class ContractNodeStandardSerImpl extends ServiceImpl<ContractNodeStandard, ContractNodeStandardDTO> implements ContractNodeStandardSer {
    @Autowired
    private ContractNodeStandardRep contractNodeStandardRep;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractNodeStandardBO save(ContractNodeStandardTO contractNodeStandardTO) throws SerException {
        ContractNodeStandard contractNodeStandard = BeanTransform.copyProperties(contractNodeStandardTO, ContractNodeStandard.class, true);
        super.save(contractNodeStandard);
        contractNodeStandardTO.setId(contractNodeStandard.getId());
        return BeanTransform.copyProperties(contractNodeStandardTO, ContractNodeStandardBO.class, true);
    }
    @Override
    public List<ContractNodeStandardBO> list(ContractNodeStandardDTO dto) throws SerException {
        List<ContractNodeStandard> contractNodeStandards = super.findByCis(dto);
        return BeanTransform.copyProperties(contractNodeStandards, ContractNodeStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(ContractNodeStandardTO contractNodeStandardTO) throws SerException {
        ContractNodeStandard contractNodeStandard = super.findById(contractNodeStandardTO.getId());
        BeanTransform.copyProperties(contractNodeStandardTO, contractNodeStandard, true);
        contractNodeStandard.setModifyTime(LocalDateTime.now());
        super.update(contractNodeStandard);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }
    @Override
    public List<ContractNodeStandardBO> collect(ContractNodeStandardBO bo) throws SerException {

        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();
        if (bo.getDate() != null && !bo.getDate().equals("")) {
            dto.getConditions().add(Restrict.eq("date", bo.getDate()));
        }
        if (bo.getArea() != null && !bo.getArea().equals("")) {
            dto.getConditions().add(Restrict.eq("area", bo.getArea()));
        }
        if (bo.getProject() != null && !bo.getProject().equals("")) {
            dto.getConditions().add(Restrict.eq("project", bo.getProject()));
        }
        if (bo.getNode() != null && !bo.getNode().equals("")) {
            dto.getConditions().add(Restrict.eq("node", bo.getNode()));
        }
        return BeanTransform.copyProperties(super.findByCis(dto), ContractNodeStandardBO.class);
    }

    @Override
    public List<ContractNodeStandardBO> searchContractNodeStandard(ContractNodeStandardBO bo) throws SerException {

        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();

        if (bo.getArea() != null && !bo.getArea().equals("")) {
            dto.getConditions().add(Restrict.eq("area", bo.getArea()));
        }
        if (bo.getProject() != null && !bo.getProject().equals("")) {
            dto.getConditions().add(Restrict.eq("project", bo.getProject()));
        }
        return BeanTransform.copyProperties(super.findByCis(dto), ContractNodeStandardBO.class);
    }

}