package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.RightSetBO;
import com.bjike.goddess.assistance.dto.RightSetDTO;
import com.bjike.goddess.assistance.service.RightSetSer;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.RightSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:14 ]
 * @Description: [ 权限设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rightSetApiImpl")
public class RightSetApiImpl implements RightSetAPI {

    @Autowired
    private RightSetSer rightSetSer;

    @Override
    public Long countRightSet(RightSetDTO rightSetDTO) throws SerException {
        return rightSetSer.count( rightSetDTO);
    }

    @Override
    public List<RightSetBO> listRightSet(RightSetDTO rightSetDTO) throws SerException {
        return rightSetSer.listRightSet(rightSetDTO);
    }

    @Override
    public RightSetBO addRightSet(RightSetTO rightSetTO) throws SerException {
        return rightSetSer.addRightSet(rightSetTO);
    }

    @Override
    public RightSetBO editRightSet(RightSetTO rightSetTO) throws SerException {
        return rightSetSer.editRightSet(rightSetTO);
    }

    @Override
    public void deleteRightSet(String id) throws SerException {
        rightSetSer.deleteRightSet(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return rightSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return rightSetSer.guidePermission(guidePermissionTO);
    }
}