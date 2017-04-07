package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.BusinessTypeBO;
import com.bjike.goddess.marketdevelopment.service.BusinessTypeSer;
import com.bjike.goddess.marketdevelopment.to.BusinessTypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:20 ]
 * @Description: [ 业务类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessTypeApiImpl")
public class BusinessTypeApiImpl implements BusinessTypeAPI {

    @Autowired
    private BusinessTypeSer businessTypeSer;

    @Override
    public BusinessTypeBO save(BusinessTypeTO to) throws SerException {
        return businessTypeSer.save(to);
    }

    @Override
    public BusinessTypeBO update(BusinessTypeTO to) throws SerException {
        return businessTypeSer.update(to);
    }

    @Override
    public BusinessTypeBO congeal(BusinessTypeTO to) throws SerException {
        return businessTypeSer.congeal(to);
    }

    @Override
    public BusinessTypeBO thaw(BusinessTypeTO to) throws SerException {
        return businessTypeSer.thaw(to);
    }

    @Override
    public BusinessTypeBO delete(BusinessTypeTO to) throws SerException {
        return businessTypeSer.delete(to);
    }

    @Override
    public List<BusinessTypeBO> findThaw() throws SerException {
        return businessTypeSer.findThaw();
    }
}