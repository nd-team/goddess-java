package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.RotationConditionBO;
import com.bjike.goddess.rotation.dto.RotationConditionDTO;
import com.bjike.goddess.rotation.service.RotationConditionSer;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RotationConditionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换条件业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:41 ]
 * @Description: [ 岗位轮换条件业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rotationConditionApiImpl")
public class RotationConditionApiImpl implements RotationConditionAPI {

    @Autowired
    private RotationConditionSer rotationConditionSer;

    @Override
    public RotationConditionBO save(RotationConditionTO to) throws SerException {
        return rotationConditionSer.save(to);
    }

    @Override
    public RotationConditionBO update(RotationConditionTO to) throws SerException {
        return rotationConditionSer.update(to);
    }

    @Override
    public RotationConditionBO delete(String id) throws SerException {
        return rotationConditionSer.delete(id);
    }

    @Override
    public RotationConditionBO getById(String id) throws SerException {
        return rotationConditionSer.getById(id);
    }

    @Override
    public List<RotationConditionBO> maps(RotationConditionDTO dto) throws SerException {
        return rotationConditionSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return rotationConditionSer.getTotal();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return rotationConditionSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return rotationConditionSer.guidePermission(guidePermissionTO);
    }
}