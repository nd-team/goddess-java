package com.bjike.goddess.projectcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcost.bo.CostControlBO;
import com.bjike.goddess.projectcost.bo.HistogramBO;
import com.bjike.goddess.projectcost.dto.CostControlDTO;
import com.bjike.goddess.projectcost.service.CostControlSer;
import com.bjike.goddess.projectcost.to.CostControlTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目成本控制业务接口实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目成本控制业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("costControlApiImpl")
public class CostControlApiImpl implements CostControlAPI {

    @Autowired
    private CostControlSer costControlSer;

    @Override
    public CostControlBO save(CostControlTO to) throws SerException {
        return costControlSer.save(to);
    }

    @Override
    public CostControlBO update(CostControlTO to) throws SerException {
        return costControlSer.update(to);
    }

    @Override
    public CostControlBO delete(String id) throws SerException {
        return costControlSer.delete(id);
    }

    @Override
    public CostControlBO updateActual(CostControlTO to) throws SerException {
        return costControlSer.updateActual(to);
    }

    @Override
    public List<CostControlBO> maps(CostControlDTO dto) throws SerException {
        return costControlSer.maps(dto);
    }

    @Override
    public CostControlBO getById(String id) throws SerException {
        return costControlSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return costControlSer.getTotal();
    }

    @Override
    public List<HistogramBO> histogramCollect(FindTO to) throws SerException {
        return costControlSer.histogramCollect(to);
    }

    @Override
    public List<CostControlBO> findByTo(FindTO to) throws SerException {
        return costControlSer.findByTo(to);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return costControlSer.guidePermission( guidePermissionTO );
    }
}