package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.HolidaySetBO;
import com.bjike.goddess.attendance.dto.HolidaySetDTO;
import com.bjike.goddess.attendance.entity.HolidaySet;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.HolidaySetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 假期设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-20 11:54 ]
 * @Description: [ 假期设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HolidaySetSer extends Ser<HolidaySet, HolidaySetDTO> {
    Boolean sonPermission() throws SerException;
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<HolidaySetBO> list(HolidaySetDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    HolidaySetBO save(HolidaySetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(HolidaySetTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    HolidaySetBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(HolidaySetDTO dto) throws SerException;
}