package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractProjectInfo;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractProjectInfoBO save(ContractProjectInfoTO contractProjectInfoTO) throws SerException {
        ContractProjectInfo contractProjectInfo = BeanTransform.copyProperties(contractProjectInfoTO, ContractProjectInfo.class, true);
        super.save(contractProjectInfo);
        contractProjectInfoTO.setId(contractProjectInfo.getId());
        return BeanTransform.copyProperties(contractProjectInfoTO, ContractProjectInfoBO.class, true);
    }

    @Override
    public List<ContractProjectInfoBO> list(ContractProjectInfoDTO contractProjectInfoDTO) throws SerException {
        // List<ContractProjectInfo> contractProjectInfos = super.findByCis(contractProjectInfoDTO);
        List<ContractProjectInfo> contractProjectInfos = super.findByPage(contractProjectInfoDTO);
        List<ContractProjectInfoBO> contractProjectInfoBOs = BeanTransform.copyProperties(contractProjectInfos, ContractProjectInfoBO.class, true);
        return contractProjectInfoBOs;
    }

}