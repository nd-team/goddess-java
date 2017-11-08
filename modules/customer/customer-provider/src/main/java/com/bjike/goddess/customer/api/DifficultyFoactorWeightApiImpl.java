package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.DifficultyFoactorWeightBO;
import com.bjike.goddess.customer.service.DifficultyFoactorWeightSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 难易度因素层权重业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:20 ]
 * @Description: [ 难易度因素层权重业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("difficultyFoactorWeightApiImpl")
public class DifficultyFoactorWeightApiImpl implements DifficultyFoactorWeightAPI {
    @Autowired
    private DifficultyFoactorWeightSer difficultyFoactorWeightSer;
    @Override
    public DifficultyFoactorWeightBO findByName(String difficName) throws SerException {
        return difficultyFoactorWeightSer.findByName(difficName);
    }
}