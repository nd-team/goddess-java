package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.DrawRegisterBO;
import com.bjike.goddess.secure.dto.DrawRegisterDTO;
import com.bjike.goddess.secure.service.DrawRegisterSer;
import com.bjike.goddess.secure.to.DrawRegisterTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 社保卡领取登记表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 05:55 ]
 * @Description: [ 社保卡领取登记表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("drawRegisterApiImpl")
public class DrawRegisterApiImpl implements DrawRegisterAPI {
    @Autowired
    private DrawRegisterSer drawRegisterSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return drawRegisterSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return drawRegisterSer.guidePermission(guidePermissionTO);
    }

    @Override
    public DrawRegisterBO save(DrawRegisterTO to) throws SerException {
        return drawRegisterSer.save(to);
    }

    @Override
    public DrawRegisterBO edit(DrawRegisterTO to) throws SerException {
        return drawRegisterSer.edit(to);
    }

    @Override
    public List<DrawRegisterBO> list(DrawRegisterDTO dto) throws SerException {
        return drawRegisterSer.list(dto);
    }

    @Override
    public DrawRegisterBO findByID(String id) throws SerException {
        return drawRegisterSer.findByID(id);
    }

    @Override
    public void delete(String id) throws SerException {
        drawRegisterSer.delete(id);
    }

    @Override
    public Long count(DrawRegisterDTO dto) throws SerException {
        return drawRegisterSer.count(dto);
    }
    @Override
    public Set<String> allName() throws SerException {
        return drawRegisterSer.allName();
    }
    @Override
    public DrawRegisterBO chargeAudit(DrawRegisterTO to) throws SerException {
        return drawRegisterSer.chargeAudit(to);
    }
}