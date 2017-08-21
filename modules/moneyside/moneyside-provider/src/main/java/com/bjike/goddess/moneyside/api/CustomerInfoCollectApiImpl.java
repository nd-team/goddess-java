package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CustomerInfoCollectBO;
import com.bjike.goddess.moneyside.dto.CustomerInfoCollectDTO;
import com.bjike.goddess.moneyside.service.CustomerInfoCollectSer;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户信息汇总业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerInfoCollectApiImpl")
public class CustomerInfoCollectApiImpl implements CustomerInfoCollectAPI {
    @Autowired
    private CustomerInfoCollectSer customerInfoCollectSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return customerInfoCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return customerInfoCollectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<CustomerInfoCollectBO> collect(CustomerInfoCollectDTO dto) throws SerException {
        return customerInfoCollectSer.collect(dto);
    }
}