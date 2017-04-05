package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerDetailBO;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.service.CustomerDetailSer;
import com.bjike.goddess.customer.to.CustomerDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户详细信息业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.475 ]
 * @Description: [ 客户详细信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerDetailApiImpl")
public class CustomerDetailApiImpl implements CustomerDetailAPI {

    @Autowired
    private CustomerDetailSer customerDetailSer;

    @Override
    public Long countCustomerDetail(CustomerDetailDTO customerDetailDTO) throws SerException {
        return customerDetailSer.countCustomerDetail(customerDetailDTO);
    }

    @Override
    public List<CustomerDetailBO> listCustomerDetail(CustomerDetailDTO customerDetailDTO) throws SerException {
        return customerDetailSer.listCustomerDetail(customerDetailDTO);
    }

    @Override
    public CustomerDetailBO addCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {
        return customerDetailSer.addCustomerDetail(customerDetailTO);
    }

    @Override
    public CustomerDetailBO editCustomerDetail(CustomerDetailTO customerDetailTO) throws SerException {
        return customerDetailSer.editCustomerDetail(customerDetailTO);
    }

    @Override
    public void deleteCustomerDetail(String id) throws SerException {
        customerDetailSer.deleteCustomerDetail(id);
    }

    @Override
    public CustomerDetailBO getCustomerDetailById(String id) throws SerException {
        return customerDetailSer.getCustomerDetailById(id);
    }

    @Override
    public CustomerDetailBO getCustomerDetailByNum(String customerNum) throws SerException {
        return customerDetailSer.getCustomerDetailByNum(customerNum);
    }
}