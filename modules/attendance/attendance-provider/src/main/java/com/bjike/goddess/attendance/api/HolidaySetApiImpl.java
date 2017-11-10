package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.HolidaySetBO;
import com.bjike.goddess.attendance.dto.HolidaySetDTO;
import com.bjike.goddess.attendance.service.HolidaySetSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.HolidaySetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 假期设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-20 11:54 ]
 * @Description: [ 假期设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("holidaySetApiImpl")
public class HolidaySetApiImpl implements HolidaySetAPI {
    @Autowired
    private HolidaySetSer holidaySetSer;

    @Override
    public List<HolidaySetBO> list(HolidaySetDTO dto) throws SerException {
        return holidaySetSer.list(dto);
    }

    @Override
    public HolidaySetBO save(HolidaySetTO to) throws SerException {
        return holidaySetSer.save(to);
    }

    @Override
    public void edit(HolidaySetTO to) throws SerException {
        holidaySetSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        holidaySetSer.delete(id);
    }

    @Override
    public HolidaySetBO findByID(String id) throws SerException {
        return holidaySetSer.findByID(id);
    }

    @Override
    public Long count(HolidaySetDTO dto) throws SerException {
        return holidaySetSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return holidaySetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return holidaySetSer.guidePermission(guidePermissionTO);
    }
}