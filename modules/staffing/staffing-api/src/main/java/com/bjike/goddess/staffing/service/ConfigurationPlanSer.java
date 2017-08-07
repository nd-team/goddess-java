package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffing.bo.ConfigurationPlanBO;
import com.bjike.goddess.staffing.dto.ConfigurationPlanDTO;
import com.bjike.goddess.staffing.entity.ConfigurationPlan;
import com.bjike.goddess.staffing.to.ConfigurationPlanTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;

import java.util.List;

/**
 * 人数配置-计划业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:33 ]
 * @Description: [ 人数配置-计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConfigurationPlanSer extends Ser<ConfigurationPlan, ConfigurationPlanDTO> {
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
    List<ConfigurationPlanBO> list(ConfigurationPlanDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ConfigurationPlanBO save(ConfigurationPlanTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ConfigurationPlanTO to) throws SerException;

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
    ConfigurationPlanBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ConfigurationPlanDTO dto) throws SerException;
}