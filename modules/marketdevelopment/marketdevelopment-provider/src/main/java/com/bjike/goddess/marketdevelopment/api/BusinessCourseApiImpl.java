package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.BusinessCourseBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessCourseDTO;
import com.bjike.goddess.marketdevelopment.service.BusinessCourseSer;
import com.bjike.goddess.marketdevelopment.to.BusinessCourseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务方向科目业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessCourseApiImpl")
public class BusinessCourseApiImpl implements BusinessCourseAPI {

    @Autowired
    private BusinessCourseSer businessCourseSer;

    @Override
    public BusinessCourseBO save(BusinessCourseTO to) throws SerException {
        return businessCourseSer.save(to);
    }

    @Override
    public BusinessCourseBO update(BusinessCourseTO to) throws SerException {
        return businessCourseSer.update(to);
    }

    @Override
    public BusinessCourseBO congeal(BusinessCourseTO to) throws SerException {
        return businessCourseSer.congeal(to);
    }

    @Override
    public BusinessCourseBO thaw(BusinessCourseTO to) throws SerException {
        return businessCourseSer.thaw(to);
    }

    @Override
    public BusinessCourseBO delete(BusinessCourseTO to) throws SerException {
        return businessCourseSer.delete(to);
    }

    @Override
    public List<BusinessCourseBO> findByType(String type_id) throws SerException {
        return businessCourseSer.findByType(type_id);
    }

    @Override
    public List<BusinessCourseBO> findThaw() throws SerException {
        return businessCourseSer.findThaw();
    }

    @Override
    public BusinessCourseBO getById(String id) throws SerException {
        return businessCourseSer.getById(id);
    }

    @Override
    public List<BusinessCourseBO> maps(BusinessCourseDTO dto) throws SerException {
        return businessCourseSer.maps(dto);
    }

    @Override
    public Integer getTotal() throws SerException {
        return businessCourseSer.findAll().size();
    }
}