package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.DifficultyFoactorWeightBO;
import com.bjike.goddess.customer.dto.DifficultyFoactorWeightDTO;
import com.bjike.goddess.customer.entity.DifficultyFoactorWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 难易度因素层权重业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:20 ]
 * @Description: [ 难易度因素层权重业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class DifficultyFoactorWeightSerImpl extends ServiceImpl<DifficultyFoactorWeight, DifficultyFoactorWeightDTO> implements DifficultyFoactorWeightSer {
    @Override
    public DifficultyFoactorWeightBO findByName(String difficName) throws SerException {
        DifficultyFoactorWeightDTO difficultyFoactorWeightDTO = new DifficultyFoactorWeightDTO();
        difficultyFoactorWeightDTO.getConditions().add(Restrict.eq("difficName",difficName));
        DifficultyFoactorWeight difficultyFoactorWeight = super.findOne(difficultyFoactorWeightDTO);
        return BeanTransform.copyProperties(difficultyFoactorWeight,DifficultyFoactorWeightBO.class);

    }
}