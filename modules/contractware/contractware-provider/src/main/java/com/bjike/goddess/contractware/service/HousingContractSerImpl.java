package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.HousingContractBO;
import com.bjike.goddess.contractware.dto.HousingContractDTO;
import com.bjike.goddess.contractware.entity.HousingContract;
import com.bjike.goddess.contractware.to.HousingContractTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 房屋合同业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:42 ]
 * @Description: [ 房屋合同业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class HousingContractSerImpl extends ServiceImpl<HousingContract, HousingContractDTO> implements HousingContractSer {

    @Override
    public Long countHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        Long count = super.count(housingContractDTO);
        return count;
    }

    @Override
    public HousingContractBO getOne(String id) throws SerException {
        HousingContract housingContract = super.findById(id);
        return BeanTransform.copyProperties(housingContract, HousingContractBO.class);
    }

    @Override
    public List<HousingContractBO> findListHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        List<HousingContract> housingContracts = super.findByPage(housingContractDTO);
        List<HousingContractBO> housingContractBOS = BeanTransform.copyProperties(housingContracts, HousingContractBO.class);
        return housingContractBOS;
    }

    @Override
    public HousingContractBO insertHousingContract(HousingContractTO housingContractTO) throws SerException {
        HousingContract housingContract = BeanTransform.copyProperties(housingContractTO, HousingContract.class, true);
        housingContract.setCreateTime(LocalDateTime.now());
        super.save(housingContract);
        return BeanTransform.copyProperties(housingContract, HousingContractBO.class);
    }

    @Override
    public HousingContractBO editHousingContract(HousingContractTO housingContractTO) throws SerException {
        HousingContract housingContract = super.findById(housingContractTO.getId());
        BeanTransform.copyProperties(housingContractTO, housingContract, true);
        housingContract.setModifyTime(LocalDateTime.now());
        super.update(housingContract);
        return BeanTransform.copyProperties(housingContract, HousingContractBO.class);
    }

    @Override
    public void removeHousingContract(String id) throws SerException {
        super.remove(id);
    }
}