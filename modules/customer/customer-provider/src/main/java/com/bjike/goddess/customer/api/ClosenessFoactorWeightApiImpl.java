package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.ClosenessFoactorWeightBO;
import com.bjike.goddess.customer.service.ClosenessFoactorWeightSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 亲密度因素层权重业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:09 ]
 * @Description: [ 亲密度因素层权重业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("closenessFoactorWeightApiImpl")
public class ClosenessFoactorWeightApiImpl implements ClosenessFoactorWeightAPI {
   @Autowired
   private ClosenessFoactorWeightSer closenessFoactorWeightSer;
    @Override
    public ClosenessFoactorWeightBO findByName(String closenessName) throws SerException {
        return closenessFoactorWeightSer.findByName(closenessName);
    }
}