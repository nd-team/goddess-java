package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.WeightalBO;
import com.bjike.goddess.projectroyalty.bo.WeightalListBO;
import com.bjike.goddess.projectroyalty.dto.WeightalDTO;
import com.bjike.goddess.projectroyalty.service.WeightalSer;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightalAdjustTO;
import com.bjike.goddess.projectroyalty.to.WeightalTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目提成权重分配表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("weightalApiImpl")
public class WeightalApiImpl implements WeightalAPI {
    @Autowired
    private WeightalSer weightalSer;

    @Override
    public void save(WeightalTO to) throws SerException {
        weightalSer.save(to);
    }

    @Override
    public void update(WeightalTO to) throws SerException {
        weightalSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        weightalSer.delete(id);
    }

    @Override
    public WeightalBO getById(String id) throws SerException {
        return weightalSer.getById(id);
    }

    @Override
    public List<WeightalBO> maps(WeightalDTO dto) throws SerException {
        return weightalSer.maps(dto);
    }

    @Override
    public Long getTotal(WeightalDTO dto) throws SerException {
        return weightalSer.getTotal(dto);
    }

    @Override
    public void adjust(WeightalAdjustTO to) throws SerException {
        weightalSer.adjust(to);
    }

    @Override
    public List<String> findProgram() throws SerException {
        return weightalSer.findProgram();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return weightalSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return weightalSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<WeightalListBO> list(WeightalDTO dto) throws SerException {
        return weightalSer.list(dto);
    }
}