package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.DifficultyFoactorWeightBO;
import com.bjike.goddess.customer.dto.DifficultyFoactorWeightDTO;
import com.bjike.goddess.customer.entity.DifficultyFoactorWeight;

/**
 * 难易度因素层权重业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:20 ]
 * @Description: [ 难易度因素层权重业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DifficultyFoactorWeightSer extends Ser<DifficultyFoactorWeight, DifficultyFoactorWeightDTO> {
    /**
     * 根据难易度因素层名称获取对应权重数据
     *
     * @param difficName 难易度因素层
     */
    default DifficultyFoactorWeightBO findByName(String difficName) throws SerException {
        return null;
    }
}