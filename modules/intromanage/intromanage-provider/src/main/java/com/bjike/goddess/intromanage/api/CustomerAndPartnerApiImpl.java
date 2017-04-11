package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.CustomerAndPartnerBO;
import com.bjike.goddess.intromanage.dto.CustomerAndPartnerDTO;
import com.bjike.goddess.intromanage.service.CustomerAndPartnerSer;
import com.bjike.goddess.intromanage.to.CustomerAndPartnerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户及合作伙伴业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customerAndPartnerApiImpl")
public class CustomerAndPartnerApiImpl implements CustomerAndPartnerAPI {

    @Autowired
    private CustomerAndPartnerSer customerAndPartnerSer;

    /**
     * 分页查询客户及合作伙伴
     *
     * @return class CustomerAndPartnerBO
     * @throws SerException
     */
    @Override
    public List<CustomerAndPartnerBO> list(CustomerAndPartnerDTO dto) throws SerException {
        return customerAndPartnerSer.list(dto);
    }

    /**
     * 保存客户及合作伙伴
     *
     * @param to 客户及合作伙伴to
     * @return class CustomerAndPartnerBO
     * @throws SerException
     */
    @Override
    public CustomerAndPartnerBO save(CustomerAndPartnerTO to) throws SerException {
        return customerAndPartnerSer.save(to);
    }

    /**
     * 根据id删除客户及合作伙伴
     *
     * @param id 客户及合作伙伴唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        customerAndPartnerSer.remove(id);
    }

    /**
     * 更新客户及合作伙伴
     *
     * @param to 客户及合作伙伴to
     * @throws SerException
     */
    @Override
    public void update(CustomerAndPartnerTO to) throws SerException {
        customerAndPartnerSer.update(to);
    }
}