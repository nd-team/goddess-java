package com.bjike.goddess.staffmove.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmove.bo.StaffMoveDemandBO;
import com.bjike.goddess.staffmove.dto.StaffMoveDemandDTO;
import com.bjike.goddess.staffmove.excel.SonPermissionObject;
import com.bjike.goddess.staffmove.service.StaffMoveDemandSer;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveDemandTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员调动需求业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:03 ]
 * @Description: [ 人员调动需求业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffMoveDemandApiImpl")
public class StaffMoveDemandApiImpl implements StaffMoveDemandAPI {
    @Autowired
    private StaffMoveDemandSer staffMoveDemandSer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return staffMoveDemandSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffMoveDemandSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(StaffMoveDemandDTO dto) throws SerException {
        return staffMoveDemandSer.count(dto);
    }

    @Override
    public StaffMoveDemandBO getOne(String id) throws SerException {
        return staffMoveDemandSer.getOne(id);
    }

    @Override
    public List<StaffMoveDemandBO> list(StaffMoveDemandDTO dto) throws SerException {
        return staffMoveDemandSer.list(dto);
    }

    @Override
    public StaffMoveDemandBO insert(StaffMoveDemandTO to) throws SerException {
        return staffMoveDemandSer.insert(to);
    }

    @Override
    public StaffMoveDemandBO edit(StaffMoveDemandTO to) throws SerException {
        return staffMoveDemandSer.edit(to);
    }
    @Override
    public void remove(String id) throws SerException{
        staffMoveDemandSer.remove(id);
    }
}