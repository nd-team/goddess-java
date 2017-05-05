package com.bjike.goddess.courier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.courier.bo.CourierCompanyBO;
import com.bjike.goddess.courier.dto.CourierCompanyDTO;
import com.bjike.goddess.courier.entity.CourierCompany;
import com.bjike.goddess.courier.to.CourierCompanyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 快递公司信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "courierSerCache")
@Service
public class CourierCompanySerImpl extends ServiceImpl<CourierCompany, CourierCompanyDTO> implements CourierCompanySer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierCompanyBO save(CourierCompanyTO to) throws SerException {
        CourierCompany courierCompany = BeanTransform.copyProperties(to, CourierCompany.class, true);
        super.save(courierCompany);
        return BeanTransform.copyProperties(courierCompany, CourierCompanyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierCompanyBO edit(CourierCompanyTO to) throws SerException {
        CourierCompany courierCompany = super.findById(to.getId());
        courierCompany = BeanTransform.copyProperties(to, CourierCompany.class, true);
        super.update(courierCompany);
        return BeanTransform.copyProperties(courierCompany, CourierCompanyBO.class);
    }

    @Override
    public List<CourierCompanyBO> list(CourierCompanyDTO dto) throws SerException {
        List<CourierCompany> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CourierCompanyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public CourierCompanyBO findByID(String id) throws SerException {
        CourierCompany courierCompany = super.findById(id);
        return BeanTransform.copyProperties(courierCompany, CourierCompanyBO.class);
    }
}