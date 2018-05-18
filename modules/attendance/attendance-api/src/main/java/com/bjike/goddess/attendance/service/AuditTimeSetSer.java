package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.AuditTimeSetBO;
import com.bjike.goddess.attendance.dto.AuditTimeSetDTO;
import com.bjike.goddess.attendance.entity.AuditTimeSet;
import com.bjike.goddess.attendance.to.AuditTimeSetTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 审批时间设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:37 ]
 * @Description: [ 审批时间设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AuditTimeSetSer extends Ser<AuditTimeSet, AuditTimeSetDTO> {
    Boolean sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AuditTimeSetBO> list(AuditTimeSetDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    AuditTimeSetBO save(AuditTimeSetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(AuditTimeSetTO to) throws SerException;

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
    AuditTimeSetBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AuditTimeSetDTO dto) throws SerException;
}