package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.AttainmentWayBO;
import com.bjike.goddess.attainment.dto.AttainmentWayDTO;
import com.bjike.goddess.attainment.service.AttainmentWaySer;
import com.bjike.goddess.attainment.to.AttainmentWayTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研方式业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("attainmentWayApiImpl")
public class AttainmentWayApiImpl implements AttainmentWayAPI {



    @Autowired
    private AttainmentWaySer attainmentWaySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return attainmentWaySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return attainmentWaySer.guidePermission(guidePermissionTO);
    }

    @Override
    public AttainmentWayBO save(AttainmentWayTO to) throws SerException {
        return attainmentWaySer.save(to);
    }

    @Override
    public AttainmentWayBO update(AttainmentWayTO to) throws SerException {
        return attainmentWaySer.update(to);
    }

    @Override
    public AttainmentWayBO delete(String id) throws SerException {
        return attainmentWaySer.delete(id);
    }

    @Override
    public AttainmentWayBO congeal(String id) throws SerException {
        return attainmentWaySer.congeal(id);
    }

    @Override
    public AttainmentWayBO thaw(String id) throws SerException {
        return attainmentWaySer.thaw(id);
    }

    @Override
    public List<AttainmentWayBO> findThaw() throws SerException {
        return attainmentWaySer.findThaw();
    }

    @Override
    public List<AttainmentWayBO> maps(AttainmentWayDTO dto) throws SerException {
        return attainmentWaySer.maps(dto);
    }

    @Override
    public AttainmentWayBO getById(String id) throws SerException {
        return attainmentWaySer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return attainmentWaySer.getTotal();
    }
}