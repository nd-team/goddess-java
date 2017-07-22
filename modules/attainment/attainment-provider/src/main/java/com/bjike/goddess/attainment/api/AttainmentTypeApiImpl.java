package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.AttainmentTypeBO;
import com.bjike.goddess.attainment.dto.AttainmentTypeDTO;
import com.bjike.goddess.attainment.excel.SonPermissionObject;
import com.bjike.goddess.attainment.service.AttainmentTypeSer;
import com.bjike.goddess.attainment.to.AttainmentTypeTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:49 ]
 * @Description: [ 调研类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("attainmentTypeApiImpl")
public class AttainmentTypeApiImpl implements AttainmentTypeAPI {

    @Autowired
    private AttainmentTypeSer attainmentTypeSer;

    @Override
    public AttainmentTypeBO save(AttainmentTypeTO to) throws SerException {
        return attainmentTypeSer.save(to);
    }

    @Override
    public AttainmentTypeBO update(AttainmentTypeTO to) throws SerException {
        return attainmentTypeSer.update(to);
    }

    @Override
    public AttainmentTypeBO delete(String id) throws SerException {
        return attainmentTypeSer.delete(id);
    }

    @Override
    public AttainmentTypeBO congeal(String id) throws SerException {
        return attainmentTypeSer.congeal(id);
    }

    @Override
    public AttainmentTypeBO thaw(String id) throws SerException {
        return attainmentTypeSer.thaw(id);
    }

    @Override
    public List<AttainmentTypeBO> findThaw() throws SerException {
        return attainmentTypeSer.findThaw();
    }

    @Override
    public List<AttainmentTypeBO> findRegular(Boolean regular) throws SerException {
        return attainmentTypeSer.findRegular(regular);
    }

    @Override
    public List<AttainmentTypeBO> maps(AttainmentTypeDTO dto) throws SerException {
        return attainmentTypeSer.maps(dto);
    }

    @Override
    public AttainmentTypeBO getById(String id) throws SerException {
        return attainmentTypeSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return attainmentTypeSer.getTotal();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return attainmentTypeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return attainmentTypeSer.guidePermission( guidePermissionTO );
    }
}