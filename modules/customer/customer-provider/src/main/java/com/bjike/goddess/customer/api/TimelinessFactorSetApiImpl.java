package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.TimelinessFactorSetBO;
import com.bjike.goddess.customer.dto.TimelinessFactorSetDTO;
import com.bjike.goddess.customer.service.TimelinessFactorSetSer;
import com.bjike.goddess.customer.service.TimelinessFactorSetSerImpl;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.to.TimelinessFactorSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 时效性因素层设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:49 ]
 * @Description: [ 时效性因素层设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("timelinessFactorSetApiImpl")
public class TimelinessFactorSetApiImpl implements TimelinessFactorSetAPI {
    @Autowired
    private TimelinessFactorSetSer timelinessFactorSetSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return timelinessFactorSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return timelinessFactorSetSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countTimeliness(TimelinessFactorSetDTO timelinessFactorSetDTO) throws SerException {
        return timelinessFactorSetSer.countTimeliness(timelinessFactorSetDTO);
    }

    @Override
    public TimelinessFactorSetBO getOneTimeliness(String id) throws SerException {
        return timelinessFactorSetSer.getOneTimeliness(id);
    }

    @Override
    public List<TimelinessFactorSetBO> listTimeliness(TimelinessFactorSetDTO timelinessFactorSetDTO) throws SerException {
        return timelinessFactorSetSer.listTimeliness(timelinessFactorSetDTO);
    }

    @Override
    public TimelinessFactorSetBO addTimeliness(TimelinessFactorSetTO timelinessFactorSetTO) throws SerException {
        return timelinessFactorSetSer.addTimeliness(timelinessFactorSetTO);
    }

    @Override
    public TimelinessFactorSetBO editTimeliness(TimelinessFactorSetTO timelinessFactorSetTO) throws SerException {
        return timelinessFactorSetSer.editTimeliness(timelinessFactorSetTO);
    }

    @Override
    public void deleteTimeliness(String id) throws SerException {
        timelinessFactorSetSer.deleteTimeliness(id);
    }
}