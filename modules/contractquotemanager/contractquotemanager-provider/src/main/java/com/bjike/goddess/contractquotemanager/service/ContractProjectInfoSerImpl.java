package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractProjectInfo;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同项目基本信息(临时表存放数据商务模块获取数据)业务实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: []
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractquotemanagerSerCache")
@Service
public class ContractProjectInfoSerImpl extends ServiceImpl<ContractProjectInfo, ContractProjectInfoDTO> implements ContractProjectInfoSer {

    /**
     * 分页查询合同项目基本信息
     *
     * @param dto 合同项目基本信息dto
     * @return class ContractProjectInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ContractProjectInfoBO> list(ContractProjectInfoDTO dto) throws SerException {
        List<ContractProjectInfo> list = super.findByPage(dto);
        List<ContractProjectInfoBO> boList = BeanTransform.copyProperties(list, ContractProjectInfoBO.class);
        return boList;
    }

    /**
     * 保存合同项目基本信息
     *
     * @param to 合同项目基本信息to
     * @return class ContractProjectInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ContractProjectInfoBO save(ContractProjectInfoTO to) throws SerException {
        ContractProjectInfo entity = BeanTransform.copyProperties(to, ContractProjectInfo.class, true);
        entity = super.save(entity);
        ContractProjectInfoBO bo = BeanTransform.copyProperties(entity, ContractProjectInfoBO.class);
        return bo;
    }

    /**
     * 根据id删除合同项目基本信息
     *
     * @param id 合同项目基本信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新合同项目基本信息
     *
     * @param to 合同项目基本信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ContractProjectInfoTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ContractProjectInfo model = super.findById(to.getId());
            if (model != null) {
                updateContractProjectInfo(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新合同项目基本信息
     *
     * @param to    合同项目基本信息to
     * @param model 合同项目基本信息
     * @throws SerException
     */
    private void updateContractProjectInfo(ContractProjectInfoTO to, ContractProjectInfo model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

}