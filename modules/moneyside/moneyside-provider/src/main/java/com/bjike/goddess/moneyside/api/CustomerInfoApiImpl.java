package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CustomerInfoBO;
import com.bjike.goddess.moneyside.dto.CustomerInfoDTO;
import com.bjike.goddess.moneyside.entity.CustomerInfo;
import com.bjike.goddess.moneyside.service.CustomerInfoSer;
import com.bjike.goddess.moneyside.to.CustomerInfoTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:58 ]
 * @Description: [ 客户信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerInfoApiImpl")
public class CustomerInfoApiImpl implements CustomerInfoAPI {
    @Autowired
    private CustomerInfoSer customerInfoSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return customerInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return customerInfoSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countCustomerInfo(CustomerInfoDTO customerInfoDTO) throws SerException {
        return customerInfoSer.countCustomerInfo(customerInfoDTO);
    }

    @Override
    public CustomerInfoBO getOne(String id) throws SerException {
        return customerInfoSer.getOne(id);
    }

    @Override
    public List<CustomerInfoBO> findListCustomerInfo(CustomerInfoDTO customerInfoDTO) throws SerException {
        return customerInfoSer.findListCustomerInfo(customerInfoDTO);
    }

    @Override
    public CustomerInfoBO insertCustomerInfo(CustomerInfoTO customerInfoTO) throws SerException {
        return customerInfoSer.insertCustomerInfo(customerInfoTO);
    }

    @Override
    public CustomerInfoBO editCustomerInfo(CustomerInfoTO customerInfoTO) throws SerException {
        return customerInfoSer.editCustomerInfo(customerInfoTO);
    }

    @Override
    public void removeCustomerInfo(String id) throws SerException {
        customerInfoSer.removeCustomerInfo(id);
    }
}