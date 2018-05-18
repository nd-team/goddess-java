package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffing.bo.ConfigurationActualBO;
import com.bjike.goddess.staffing.dto.ConfigurationActualDTO;
import com.bjike.goddess.staffing.entity.ConfigurationActual;
import com.bjike.goddess.staffing.to.ConfigurationActualTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;

import java.util.List;

/**
 * 人数配置-实际业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-03 11:05 ]
 * @Description: [ 人数配置-实际业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConfigurationActualSer extends Ser<ConfigurationActual, ConfigurationActualDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ConfigurationActualBO> list(ConfigurationActualDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ConfigurationActualBO save(ConfigurationActualTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ConfigurationActualTO to) throws SerException;

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
    ConfigurationActualBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ConfigurationActualDTO dto) throws SerException;
}