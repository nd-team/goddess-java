package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.HolidayProgrammeBO;
import com.bjike.goddess.festival.dto.HolidayProgrammeDTO;
import com.bjike.goddess.festival.to.HolidayProgrammeTO;

import java.util.List;

/**
 * 法定节假日放假方案业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:03 ]
 * @Description: [ 法定节假日放假方案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HolidayProgrammeAPI {

    /**
     * 法定节假日放假方案列表总条数
     *
     */
    default Long countHolidayProgramme(HolidayProgrammeDTO holidayProgrammeDTO) throws SerException {
        return null;
    }
    /**
     * 法定节假日放假方案列表
     * @return class HolidayProgrammeBO
     */
    default List<HolidayProgrammeBO> listHolidayProgramme(HolidayProgrammeDTO holidayProgrammeDTO) throws SerException {return null;}
    /**
     *  添加
     * @param holidayProgrammeTO 法定节假日放假方案信息
     * @return class HolidayProgrammeBO
     */
    default HolidayProgrammeBO addHolidayProgramme(HolidayProgrammeTO holidayProgrammeTO) throws SerException { return null;}

    /**
     *  编辑
     * @param holidayProgrammeTO 法定节假日放假方案信息
     * @return class HolidayProgrammeBO
     */
    default HolidayProgrammeBO editHolidayProgramme(HolidayProgrammeTO holidayProgrammeTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteHolidayProgramme(String id ) throws SerException {return;};

}