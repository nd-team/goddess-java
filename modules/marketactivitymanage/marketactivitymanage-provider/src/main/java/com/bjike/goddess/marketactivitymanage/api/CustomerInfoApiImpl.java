package com.bjike.goddess.marketactivitymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.service.CustomerInfoSer;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户信息业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerInfoApiImpl")
public class CustomerInfoApiImpl implements CustomerInfoAPI {

    @Autowired
    private CustomerInfoSer customerInfoSer;

    /**
     * 根据id查询客户信息
     *
     * @param id 客户信息唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    public CustomerInfoBO findById(String id) throws SerException {
        CustomerInfo model = customerInfoSer.findById(id);
        return BeanTransform.copyProperties(model, CustomerInfoBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 客户信息dto
     * @throws SerException
     */
    @Override
    public Long count(CustomerInfoDTO dto) throws SerException {
        return customerInfoSer.count(dto);
    }

    /**
     * 分页查询客户信息
     *
     * @param dto 客户信息dto
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    public List<CustomerInfoBO> list(CustomerInfoDTO dto) throws SerException {
        return customerInfoSer.list(dto);
    }

    /**
     * 保存客户信息
     *
     * @param to 客户信息to
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    public CustomerInfoBO save(CustomerInfoTO to) throws SerException {
        return customerInfoSer.save(to);
    }

    /**
     * 根据id删除客户信息
     *
     * @param id 客户信息唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        customerInfoSer.remove(id);
    }

    /**
     * 更新客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void update(CustomerInfoTO to) throws SerException {
        customerInfoSer.update(to);
    }

    /**
     * 根据市场活动id查找客户信息
     *
     * @param id 市场活动唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    public List<CustomerInfoBO> findByMarketServeId(String id) throws SerException {
        return customerInfoSer.findByMarketServeId(id);
    }
}