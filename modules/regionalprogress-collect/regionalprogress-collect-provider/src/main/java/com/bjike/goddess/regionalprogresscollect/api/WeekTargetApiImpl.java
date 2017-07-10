package com.bjike.goddess.regionalprogresscollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regionalprogresscollect.bo.WeekTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.WeekTargetDTO;
import com.bjike.goddess.regionalprogresscollect.service.WeekTargetSer;
import com.bjike.goddess.regionalprogresscollect.to.GuidePermissionTO;
import com.bjike.goddess.regionalprogresscollect.to.StandardTO;
import com.bjike.goddess.regionalprogresscollect.to.WeekTargetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 周指标业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 周指标业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("weekTargetApiImpl")
public class WeekTargetApiImpl implements WeekTargetAPI {

    @Autowired
    private WeekTargetSer weekTargetSer;

    @Override
    public WeekTargetBO save(WeekTargetTO to) throws SerException {
        return weekTargetSer.save(to);
    }

    @Override
    public WeekTargetBO update(WeekTargetTO to) throws SerException {
        return weekTargetSer.update(to);
    }

    @Override
    public WeekTargetBO delete(String id) throws SerException {
        return weekTargetSer.delete(id);
    }

    @Override
    public WeekTargetBO getById(String id) throws SerException {
        return weekTargetSer.getById(id);
    }

    @Override
    public WeekTargetBO updateStandard(StandardTO to) throws SerException {
        return weekTargetSer.updateStandard(to);
    }

    @Override
    public List<WeekTargetBO> maps(WeekTargetDTO dto) throws SerException {
        return weekTargetSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return weekTargetSer.getTotal();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return weekTargetSer.guidePermission( guidePermissionTO );
    }
}