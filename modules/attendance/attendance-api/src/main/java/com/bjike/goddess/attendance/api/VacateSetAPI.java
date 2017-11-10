package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.VacateSetBO;
import com.bjike.goddess.attendance.dto.VacateSetDTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.VacateSetTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 请假设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-12 01:46 ]
 * @Description: [ 请假设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VacateSetAPI {
    Boolean sonPermission() throws SerException;
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<VacateSetBO> list(VacateSetDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    void save(VacateSetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(VacateSetTO to) throws SerException;

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
    VacateSetBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(VacateSetDTO dto) throws SerException;
}