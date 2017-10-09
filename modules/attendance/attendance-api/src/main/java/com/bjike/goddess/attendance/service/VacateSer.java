package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 请假管理业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VacateSer extends Ser<Vacate, VacateDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateBO> list(VacateDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    VacateBO save(VacateTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(VacateTO to) throws SerException;

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
    VacateBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(VacateDTO dto) throws SerException;

    /**
     * 获取请假天数
     *
     * @param to
     * @return
     * @throws SerException
     */
    Double getTime(VacateTO to) throws SerException;
}