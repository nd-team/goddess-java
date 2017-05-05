package com.bjike.goddess.courier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.courier.bo.CourierCompanyBO;
import com.bjike.goddess.courier.dto.CourierCompanyDTO;
import com.bjike.goddess.courier.service.CourierCompanySer;
import com.bjike.goddess.courier.to.CourierCompanyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 快递公司信息业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("courierCompanyApiImpl")
public class CourierCompanyApiImpl implements CourierCompanyAPI {
    @Autowired
    private CourierCompanySer courierCompanySer;

    @Override
    public CourierCompanyBO save(CourierCompanyTO to) throws SerException {
        return courierCompanySer.save(to);
    }

    @Override
    public CourierCompanyBO edit(CourierCompanyTO to) throws SerException {
        return courierCompanySer.edit(to);
    }

    @Override
    public List<CourierCompanyBO> list(CourierCompanyDTO dto) throws SerException {
        return courierCompanySer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        courierCompanySer.delete(id);
    }

    @Override
    public CourierCompanyBO findByID(String id) throws SerException {
        return courierCompanySer.findByID(id);
    }
}