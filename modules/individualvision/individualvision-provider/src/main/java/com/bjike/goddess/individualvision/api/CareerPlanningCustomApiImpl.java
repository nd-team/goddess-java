package com.bjike.goddess.individualvision.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.bo.CareerPlanningCustomBO;
import com.bjike.goddess.individualvision.dto.CareerPlanningCustomDTO;
import com.bjike.goddess.individualvision.entity.CareerPlanningCustom;
import com.bjike.goddess.individualvision.service.CareerPlanningCustomSer;
import com.bjike.goddess.individualvision.to.CareerPlanningCustomTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 职业规划定制业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:45 ]
 * @Description: [ 职业规划定制业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("careerPlanningCustomApiImpl")
public class CareerPlanningCustomApiImpl implements CareerPlanningCustomAPI {
    @Autowired
    private CareerPlanningCustomSer careerPlanningCustomSer;
    @Override
    public List<CareerPlanningCustomBO> findListCareerPlanningCustom(CareerPlanningCustomDTO careerPlanningCustomDTO) throws SerException {
        return careerPlanningCustomSer.findListCareerPlanningCustom(careerPlanningCustomDTO);
    }

    @Override
    public CareerPlanningCustomBO insertCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        return careerPlanningCustomSer.insertCareerPlanningCustom(careerPlanningCustomTO);
    }

    @Override
    public CareerPlanningCustomBO editCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        return careerPlanningCustomSer.editCareerPlanningCustom(careerPlanningCustomTO);
    }

    @Override
    public void removeCareerPlanningCustom(String id) throws SerException {
        careerPlanningCustomSer.remove(id);
    }
    @Override
    public CareerPlanningCustomBO sendCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        return careerPlanningCustomSer.sendCareerPlanningCustom(careerPlanningCustomTO);
    }
}