package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.TargetInformationBO;
import com.bjike.goddess.marketdevelopment.service.TargetInformationSer;
import com.bjike.goddess.marketdevelopment.to.TargetInformationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 确定目标信息业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:12 ]
 * @Description: [ 确定目标信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("targetInformationApiImpl")
public class TargetInformationApiImpl implements TargetInformationAPI {

    @Autowired
    private TargetInformationSer targetInformationSer;

    @Override
    public TargetInformationBO save(TargetInformationTO to) throws SerException {
        return targetInformationSer.save(to);
    }

    @Override
    public TargetInformationBO update(TargetInformationTO to) throws SerException {
        return targetInformationSer.update(to);
    }

    @Override
    public TargetInformationBO delete(TargetInformationTO to) throws SerException {
        return targetInformationSer.delete(to);
    }

    @Override
    public List<TargetInformationBO> findByType(String type) throws SerException {
        return targetInformationSer.findByType(type);
    }

    @Override
    public List<TargetInformationBO> findByCourse(String course) throws SerException {
        return targetInformationSer.findByCourse(course);
    }

    @Override
    public List<TargetInformationBO> findByCourseType(String type, String course) throws SerException {
        return targetInformationSer.findByCourseType(type, course);
    }

    @Override
    public List<TargetInformationBO> findByArea(String area) throws SerException {
        return targetInformationSer.findByArea(area);
    }
}