package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.service.CustomerBaseInfoSer;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户基本信息业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.063 ]
 * @Description: [ 客户基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerBaseInfoApiImpl")
public class CustomerBaseInfoApiImpl implements CustomerBaseInfoAPI {

    @Autowired
    private CustomerBaseInfoSer customerBaseInfoSer;

    @Override
    public Long countCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        return customerBaseInfoSer.countCustomerBaseInfo(customerBaseInfoDTO);
    }

    @Override
    public CustomerBaseInfoBO generateCustomerNum() throws SerException {
        return customerBaseInfoSer.generateCustomerNum();
    }

    @Override
    public List<CustomerBaseInfoBO> listCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        return customerBaseInfoSer.listCustomerBaseInfo(customerBaseInfoDTO);
    }

    @Override
    public CustomerBaseInfoBO addCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        return customerBaseInfoSer.addCustomerBaseInfo(customerBaseInfoTO);
    }

    @Override
    public CustomerBaseInfoBO editCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        return customerBaseInfoSer.editCustomerBaseInfo(customerBaseInfoTO);
    }

    @Override
    public void deleteCustomerBaseInfo(String id) throws SerException {
        customerBaseInfoSer.deleteCustomerBaseInfo(id);
    }

    @Override
    public void congealCustomerBaseInfo(String id) throws SerException {
        customerBaseInfoSer.congealCustomerBaseInfo(id);
    }

    @Override
    public void thawCustomerBaseInfo(String id) throws SerException {
        customerBaseInfoSer.thawCustomerBaseInfo(id);
    }

    @Override
    public List<String> getCustomerBaseInfoCusNum() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoCusNum();
    }

    @Override
    public List<String> getCustomerBaseInfoArea() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoArea();
    }

    @Override
    public List<String> getCustomerBaseInfoName() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoName();
    }


    @Override
    public CustomerBaseInfoBO addMarketCustomerInfo(@NotBlank String customerName,String origanizion) throws SerException {
        return customerBaseInfoSer.addMarketCustomerInfo( customerName ,origanizion );
    }

    @Override
    public CustomerBaseInfoBO getCustomerInfoByNum(String customerNum) throws SerException {
        return customerBaseInfoSer.getCustomerInfoByNum(customerNum);
    }

    @Override
    public List<String> getCustomerBaseInfoWorks() throws SerException {
        return customerBaseInfoSer.getCustomerBaseInfoWorks();
    }
}