package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.TimelinessFactorSetBO;
import com.bjike.goddess.customer.dto.TimelinessFactorSetDTO;
import com.bjike.goddess.customer.entity.TimelinessFactorSet;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.to.TimelinessFactorSetTO;

import java.util.List;

/**
 * 时效性因素层设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:49 ]
 * @Description: [ 时效性因素层设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimelinessFactorSetSer extends Ser<TimelinessFactorSet, TimelinessFactorSetDTO> {

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
     * 时效性因素层设置总条数
     */
    default Long countTimeliness(TimelinessFactorSetDTO timelinessFactorSetDTO) throws SerException {
        return null;
    }

    /**
     * 一个时效性因素层设置
     */
    default TimelinessFactorSetBO getOneTimeliness(String id) throws SerException {
        return null;
    }

    /**
     * 时效性因素层设置列表
     *
     * @return class TimelinessFactorSetBO
     */
    default List<TimelinessFactorSetBO> listTimeliness(TimelinessFactorSetDTO timelinessFactorSetDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param timelinessFactorSetTO 时效性因素层设置
     * @return class TimelinessFactorSetBO
     */
    default TimelinessFactorSetBO addTimeliness(TimelinessFactorSetTO timelinessFactorSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param timelinessFactorSetTO 时效性因素层设置
     * @return class TimelinessFactorSetBO
     */
    default TimelinessFactorSetBO editTimeliness(TimelinessFactorSetTO timelinessFactorSetTO) throws SerException {
        return null;
    }

    /**
     * 删除时效性因素层设置
     *
     * @param id id
     */
    default void deleteTimeliness(String id) throws SerException {
        return;
    }
}