package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.WeightAllocationBO;
import com.bjike.goddess.projectroyalty.dto.WeightAllocationDTO;
import com.bjike.goddess.projectroyalty.service.WeightAllocationSer;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightAllocationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目提成权重分配业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:12 ]
 * @Description: [ 项目提成权重分配业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("weightAllocationApiImpl")
public class WeightAllocationApiImpl implements WeightAllocationAPI {

    @Autowired
    private WeightAllocationSer weightAllocationSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return weightAllocationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return weightAllocationSer.guidePermission(guidePermissionTO);
    }


    @Override
    public WeightAllocationBO saveTarget(WeightAllocationTO to) throws SerException {
        return weightAllocationSer.saveTarget(to);
    }

    @Override
    public WeightAllocationBO saveActual(WeightAllocationTO to) throws SerException {
        return weightAllocationSer.saveActual(to);
    }

    @Override
    public WeightAllocationBO update(WeightAllocationTO to) throws SerException {
        return weightAllocationSer.update(to);
    }

    @Override
    public WeightAllocationBO delete(String id) throws SerException {
        return weightAllocationSer.delete(id);
    }

    @Override
    public WeightAllocationBO getById(String id) throws SerException {
        return weightAllocationSer.getById(id);
    }

    @Override
    public List<WeightAllocationBO> targetMaps(WeightAllocationDTO dto) throws SerException {
        return weightAllocationSer.targetMaps(dto);
    }

    @Override
    public List<WeightAllocationBO> actualMaps(WeightAllocationDTO dto) throws SerException {
        return weightAllocationSer.actualMaps(dto);
    }

    @Override
    public Long getTargetTotal() throws SerException {
        return weightAllocationSer.getTargetTotal();
    }

    @Override
    public Long getActualTotal() throws SerException {
        return weightAllocationSer.getActualTotal();
    }

    @Override
    public List<OpinionBO> findTargetOpinion() throws SerException {
        return weightAllocationSer.findTargetOpinion();
    }

    @Override
    public List<OpinionBO> findActualOpinion() throws SerException {
        return weightAllocationSer.findActualOpinion();
    }
}