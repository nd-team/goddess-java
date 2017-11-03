package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerWeightFirstFactorBO;
import com.bjike.goddess.customer.dto.CustomerWeightFirstFactorDTO;
import com.bjike.goddess.customer.service.CustomerWeightFirstFactorSer;
import com.bjike.goddess.customer.to.CustomerWeightFirstFactorTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户权重一层因素层设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:16 ]
 * @Description: [ 客户权重一层因素层设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerWeightFirstFactorApiImpl")
public class CustomerWeightFirstFactorApiImpl implements CustomerWeightFirstFactorAPI {
    @Autowired
    private CustomerWeightFirstFactorSer customerWeightFirstFactorSer;
    @Override
    public Long countFirstFactor(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws SerException {
        return customerWeightFirstFactorSer.countFirstFactor(customerWeightFirstFactorDTO);
    }

    @Override
    public CustomerWeightFirstFactorBO getOneFirstFactor(String id) throws SerException {
        return customerWeightFirstFactorSer.getOneFirstFactor(id);
    }

    @Override
    public List<CustomerWeightFirstFactorBO> listFirstFactor(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws SerException {
        return customerWeightFirstFactorSer.listFirstFactor(customerWeightFirstFactorDTO);
    }

    @Override
    public CustomerWeightFirstFactorBO addFirstFactor(CustomerWeightFirstFactorTO customerWeightFirstFactorTO) throws SerException {
        return customerWeightFirstFactorSer.addFirstFactor(customerWeightFirstFactorTO);
    }

    @Override
    public CustomerWeightFirstFactorBO editFirstFactor(CustomerWeightFirstFactorTO customerWeightFirstFactorTO) throws SerException {
        return customerWeightFirstFactorSer.editFirstFactor(customerWeightFirstFactorTO);
    }

    @Override
    public void deleteFirstFactor(String id) throws SerException {
        customerWeightFirstFactorSer.deleteFirstFactor(id);
    }
}