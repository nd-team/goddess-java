package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.HolidayProgrammeBO;
import com.bjike.goddess.festival.dto.HolidayProgrammeDTO;
import com.bjike.goddess.festival.excel.SonPermissionObject;
import com.bjike.goddess.festival.service.HolidayProgrammeSer;
import com.bjike.goddess.festival.to.GuidePermissionTO;
import com.bjike.goddess.festival.to.HolidayProgrammeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 法定节假日放假方案业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:03 ]
 * @Description: [ 法定节假日放假方案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("holidayProgrammeApiImpl")
public class HolidayProgrammeApiImpl implements HolidayProgrammeAPI {

    @Autowired
    private HolidayProgrammeSer holidayProgrammeSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return holidayProgrammeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return holidayProgrammeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countHolidayProgramme(HolidayProgrammeDTO holidayProgrammeDTO) throws SerException {
        return holidayProgrammeSer.countHolidayProgramme(holidayProgrammeDTO);
    }

    @Override
    public HolidayProgrammeBO getOneById(String id) throws SerException {
        return holidayProgrammeSer.getOneById(id);
    }

    @Override
    public List<HolidayProgrammeBO> listHolidayProgramme(HolidayProgrammeDTO holidayProgrammeDTO) throws SerException {
        return holidayProgrammeSer.listHolidayProgramme(holidayProgrammeDTO);
    }

    @Override
    public HolidayProgrammeBO addHolidayProgramme(HolidayProgrammeTO holidayProgrammeTO) throws SerException {
        return holidayProgrammeSer.addHolidayProgramme(holidayProgrammeTO);
    }

    @Override
    public HolidayProgrammeBO editHolidayProgramme(HolidayProgrammeTO holidayProgrammeTO) throws SerException {
        return holidayProgrammeSer.editHolidayProgramme(holidayProgrammeTO);
    }

    @Override
    public void deleteHolidayProgramme(String id) throws SerException {
        holidayProgrammeSer.deleteHolidayProgramme(id);
    }
}