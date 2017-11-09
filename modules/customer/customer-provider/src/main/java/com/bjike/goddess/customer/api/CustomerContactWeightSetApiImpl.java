package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerContactWeightSetBO;
import com.bjike.goddess.customer.dto.CustomerContactWeightSetDTO;
import com.bjike.goddess.customer.service.CustomerContactWeightSetSer;
import com.bjike.goddess.customer.service.CustomerContactWeightSetSerImpl;
import com.bjike.goddess.customer.to.CustomerContactWeightSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户接触阶段权重设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerContactWeightSetApiImpl")
public class CustomerContactWeightSetApiImpl implements CustomerContactWeightSetAPI {
   @Autowired
   private CustomerContactWeightSetSer customerContactWeightSetSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return customerContactWeightSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return customerContactWeightSetSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {
        return customerContactWeightSetSer.countContactWeight(customerContactWeightSetDTO);
    }

    @Override
    public CustomerContactWeightSetBO getOneContactWeight(String id) throws SerException {
        return customerContactWeightSetSer.getOneContactWeight(id);
    }

    @Override
    public List<CustomerContactWeightSetBO> listContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {
        return customerContactWeightSetSer.listContactWeight(customerContactWeightSetDTO);
    }

    @Override
    public CustomerContactWeightSetBO addContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        return customerContactWeightSetSer.addContactWeight(customerContactWeightSetTO);
    }

    @Override
    public CustomerContactWeightSetBO editContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        return customerContactWeightSetSer.editContactWeight(customerContactWeightSetTO);
    }

    @Override
    public void deleteContactWeight(String id) throws SerException {
        customerContactWeightSetSer.deleteContactWeight(id);
    }

    @Override
    public CustomerContactWeightSetBO findByCustomerType(String customerContactType) throws SerException {
        return customerContactWeightSetSer.findByCustomerType(customerContactType);
    }
}