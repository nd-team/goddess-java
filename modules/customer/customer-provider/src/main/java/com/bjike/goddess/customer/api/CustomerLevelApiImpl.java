package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerLevelDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.service.CustomerLevelSer;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户级别业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T17:00:40.878 ]
 * @Description: [ 客户级别业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerLevelApiImpl")
public class CustomerLevelApiImpl implements CustomerLevelAPI {

    @Autowired
    private CustomerLevelSer customerLevelSer;

    @Override
    public Long countCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {
        return customerLevelSer.countCustomerLevel(customerLevelDTO);
    }

    @Override
    public List<CustomerLevelBO> listCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {
        return customerLevelSer.listCustomerLevel(customerLevelDTO);
    }

    @Override
    public CustomerLevelBO addCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException {
        return customerLevelSer.addCustomerLevel( customerLevelTO);
    }

    @Override
    public CustomerLevelBO editCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException {
        return customerLevelSer.editCustomerLevel(customerLevelTO);
    }

    @Override
    public void deleteCustomerLevel(String id) throws SerException {
        customerLevelSer.deleteCustomerLevel(id);
    }

    @Override
    public void congealCustomerLevel(String id) throws SerException {
        customerLevelSer.congealCustomerLevel(id);
    }

    @Override
    public void thawCustomerLevel(String id) throws SerException {
        customerLevelSer.thawCustomerLevel(id);
    }

    @Override
    public CustomerLevelBO getCustomerLevelByName(String name) throws SerException {
        return customerLevelSer.getCustomerLevelByName( name);
    }

    @Override
    public List<String> getAllLevel() throws SerException {
        return customerLevelSer.getAllLevel();
    }
}