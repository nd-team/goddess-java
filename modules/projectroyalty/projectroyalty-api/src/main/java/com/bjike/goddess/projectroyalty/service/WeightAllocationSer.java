package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.WeightAllocationBO;
import com.bjike.goddess.projectroyalty.dto.WeightAllocationDTO;
import com.bjike.goddess.projectroyalty.entity.WeightAllocation;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightAllocationTO;

import java.util.List;

/**
 * 项目提成权重分配业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:12 ]
 * @Description: [ 项目提成权重分配业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WeightAllocationSer extends Ser<WeightAllocation, WeightAllocationDTO> {


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
     * 保存项目提成目标权重分配
     *
     * @param to 项目提成权重分配传输对象
     * @return
     * @throws SerException
     */
    default WeightAllocationBO saveTarget(WeightAllocationTO to) throws SerException {
        return null;
    }

    /**
     * 保存项目提成实际权重分配
     *
     * @param to 项目提成权重分配传输对象
     * @return
     * @throws SerException
     */
    default WeightAllocationBO saveActual(WeightAllocationTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 项目提成权重分配传输对象
     * @return
     * @throws SerException
     */
    default WeightAllocationBO update(WeightAllocationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 项目提成权重分配数据id
     * @return
     * @throws SerException
     */
    default WeightAllocationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取项目提成权重数据
     *
     * @param id 项目提成权重分配数据id
     * @return
     * @throws SerException
     */
    default WeightAllocationBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 项目提成目标权重分配列表
     *
     * @param dto 项目提成权重分配数据传输对象
     * @return
     * @throws SerException
     */
    default List<WeightAllocationBO> targetMaps(WeightAllocationDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目提成实际权重分配列表
     *
     * @param dto 项目提成权重分配数据传输对象
     * @return
     * @throws SerException
     */
    default List<WeightAllocationBO> actualMaps(WeightAllocationDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取项目提成目标权重分配总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTargetTotal() throws SerException {
        return null;
    }

    /**
     * 获取项目提成实际权重分配总条数
     *
     * @return
     * @throws SerException
     */
    default Long getActualTotal() throws SerException {
        return null;
    }

    /**
     * 获取项目提成目标权重分配选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findTargetOpinion() throws SerException {
        return null;
    }

    /**
     * 获取项目提成实际权重分配选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findActualOpinion() throws SerException {
        return null;
    }

}