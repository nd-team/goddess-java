package com.bjike.goddess.bonus.api;

import com.bjike.goddess.bonus.bo.PerformanceIndicatorBO;
import com.bjike.goddess.bonus.dto.PerformanceIndicatorDTO;
import com.bjike.goddess.bonus.excel.SonPermissionObject;
import com.bjike.goddess.bonus.service.PerformanceIndicatorSer;
import com.bjike.goddess.bonus.to.GuidePermissionTO;
import com.bjike.goddess.bonus.to.PerformanceIndicatorTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 绩效指标业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("performanceIndicatorApiImpl")
public class PerformanceIndicatorApiImpl implements PerformanceIndicatorAPI {

    @Autowired
    private PerformanceIndicatorSer performanceIndicatorSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return performanceIndicatorSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return performanceIndicatorSer.guidePermission(guidePermissionTO);
    }

    @Override
    public PerformanceIndicatorBO save(PerformanceIndicatorTO to) throws SerException {
        return performanceIndicatorSer.save(to);
    }

    @Override
    public PerformanceIndicatorBO update(PerformanceIndicatorTO to) throws SerException {
        return performanceIndicatorSer.update(to);
    }

    @Override
    public PerformanceIndicatorBO delete(String id) throws SerException {
        return performanceIndicatorSer.delete(id);
    }

    @Override
    public PerformanceIndicatorBO start(String id) throws SerException {
        return performanceIndicatorSer.start(id);
    }

    @Override
    public PerformanceIndicatorBO close(String id) throws SerException {
        return performanceIndicatorSer.close(id);
    }

    @Override
    public List<PerformanceIndicatorBO> findByStatus(Boolean status) throws SerException {
        return performanceIndicatorSer.findByStatus(status);
    }

    @Override
    public List<PerformanceIndicatorBO> maps(PerformanceIndicatorDTO dto) throws SerException {
        return performanceIndicatorSer.maps(dto);
    }

    @Override
    public PerformanceIndicatorBO getById(String id) throws SerException {
        return performanceIndicatorSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return performanceIndicatorSer.getTotal();
    }
}