package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.CollectBO;
import com.bjike.goddess.projectroyalty.bo.WeightalsBO;
import com.bjike.goddess.projectroyalty.dto.WeightalsDTO;
import com.bjike.goddess.projectroyalty.service.WeightalsSer;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightalAdjustTO;
import com.bjike.goddess.projectroyalty.to.WeightalsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目提成权重分配表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-20 03:34 ]
 * @Description: [ 项目提成权重分配表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("weightalsApiImpl")
public class WeightalsApiImpl implements WeightalsAPI {
    @Autowired
    private WeightalsSer weightalsSer;

    @Override
    public void save(WeightalsTO to) throws SerException {
        weightalsSer.save(to);
    }

    @Override
    public void update(WeightalsTO to) throws SerException {
        weightalsSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        weightalsSer.delete(id);
    }

    @Override
    public WeightalsBO getById(String id) throws SerException {
        return weightalsSer.getById(id);
    }

    @Override
    public List<WeightalsBO> maps(WeightalsDTO dto) throws SerException {
        return weightalsSer.maps(dto);
    }

    @Override
    public Long getTotal(WeightalsDTO dto) throws SerException {
        return weightalsSer.getTotal(dto);
    }

    @Override
    public void adjust(WeightalAdjustTO to) throws SerException {
        weightalsSer.adjust(to);
    }

    @Override
    public List<String> findProgram() throws SerException {
        return weightalsSer.findProgram();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return weightalsSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return weightalsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<CollectBO> dayCollect(String time) throws SerException {
        return weightalsSer.dayCollect(time);
    }

    @Override
    public List<CollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return weightalsSer.weekCollect(year, month, week);
    }

    @Override
    public List<CollectBO> monthCollect(String month) throws SerException {
        return weightalsSer.monthCollect(month);
    }

    @Override
    public List<CollectBO> totalCollect() throws SerException {
        return weightalsSer.totalCollect();
    }
}