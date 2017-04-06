package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.CustomerAndPartnerBO;
import com.bjike.goddess.intromanage.dto.CustomerAndPartnerDTO;
import com.bjike.goddess.intromanage.entity.CustomerAndPartner;
import com.bjike.goddess.intromanage.to.CustomerAndPartnerTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户及合作伙伴业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class CustomerAndPartnerSerImpl extends ServiceImpl<CustomerAndPartner, CustomerAndPartnerDTO> implements CustomerAndPartnerSer {

    /**
     * 分页查询客户及合作伙伴
     *
     * @return class CustomerAndPartnerBO
     * @throws SerException
     */
    @Override
    public List<CustomerAndPartnerBO> list(CustomerAndPartnerDTO dto) throws SerException {
        List<CustomerAndPartner> list = super.findByPage(dto);
        List<CustomerAndPartnerBO> listBO = BeanTransform.copyProperties(list, CustomerAndPartnerBO.class);
        return listBO;
    }

    /**
     * 保存客户及合作伙伴
     *
     * @param to 客户及合作伙伴to
     * @return class CustomerAndPartnerBO
     * @throws SerException
     */
    @Override
    @Transactional
    public CustomerAndPartnerBO save(CustomerAndPartnerTO to) throws SerException {
        CustomerAndPartner entity = BeanTransform.copyProperties(to, CustomerAndPartner.class, true);
        entity = super.save(entity);
        CustomerAndPartnerBO bo = BeanTransform.copyProperties(entity, CustomerAndPartnerBO.class);
        return bo;
    }

    /**
     * 更新客户及合作伙伴
     *
     * @param to 客户及合作伙伴to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(CustomerAndPartnerTO to) throws SerException {
        CustomerAndPartner entity = BeanTransform.copyProperties(to, CustomerAndPartner.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除客户及合作伙伴
     *
     * @param id 客户及合作伙伴唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}