package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ForeignStaffingSetBO;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.service.ForeignStaffingSetSer;
import com.bjike.goddess.archive.to.ForeignStaffingSetTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对外人员基本信息设置业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("foreignStaffingSetApiImpl")
public class ForeignStaffingSetApiImpl implements ForeignStaffingSetAPI {

    @Autowired
    private ForeignStaffingSetSer foreignStaffingSetSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return foreignStaffingSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return foreignStaffingSetSer.guidePermission(guidePermissionTO);
    }
    @Override
    public ForeignStaffingSetBO save(ForeignStaffingSetTO to) throws SerException {
        return foreignStaffingSetSer.save(to);
    }

    @Override
    public ForeignStaffingSetBO update(ForeignStaffingSetTO to) throws SerException {
        return foreignStaffingSetSer.update(to);
    }

    @Override
    public ForeignStaffingSetBO delete(String id) throws SerException {
        return foreignStaffingSetSer.delete(id);
    }

    @Override
    public ForeignStaffingSetBO congeal(String id) throws SerException {
        return foreignStaffingSetSer.congeal(id);
    }

    @Override
    public ForeignStaffingSetBO thaw(String id) throws SerException {
        return foreignStaffingSetSer.thaw(id);
    }

    @Override
    public List<ForeignStaffingSetBO> findByStatus(Status status) throws SerException {
        return foreignStaffingSetSer.findByStatus(status);
    }

    @Override
    public List<ForeignStaffingSetBO> maps(ForeignStaffingSetDTO dto) throws SerException {
        return foreignStaffingSetSer.maps(dto);
    }

    @Override
    public ForeignStaffingSetBO getById(String id) throws SerException {
        return foreignStaffingSetSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return foreignStaffingSetSer.getTotal();
    }
}