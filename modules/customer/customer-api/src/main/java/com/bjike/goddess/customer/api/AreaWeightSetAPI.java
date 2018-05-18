package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.AreaWeightSetBO;
import com.bjike.goddess.customer.dto.AreaWeightSetDTO;
import com.bjike.goddess.customer.entity.AreaWeightSet;
import com.bjike.goddess.customer.to.AreaWeightSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;

import java.util.List;

/**
 * 地区权重设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaWeightSetAPI {
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
     * 地区权重设置列表总条数
     */
    default Long countAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {
        return null;
    }

    /**
     * 一个地区权重设置
     */
    default AreaWeightSetBO getOneAreaWeight(String id) throws SerException {
        return null;
    }

    /**
     * 地区权重设置列表
     *
     * @return class AreaWeightSetBO
     */
    default List<AreaWeightSetBO> listAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param areaWeightSetTO 地区权重设置
     * @return class AreaWeightSetBO
     */
    default AreaWeightSetBO addAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param areaWeightSetTO 地区权重设置
     * @return class AreaWeightSetBO
     */
    default AreaWeightSetBO editAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        return null;
    }

    /**
     * 删除级别
     *
     * @param id id
     */
    default void deleteAreaWeight(String id) throws SerException {
        return;
    }
    /**
     * 根据省份和地区获取对应数据
     *
     * @param provinces 省份
     * @param area 地区
     */
    default AreaWeightSetBO findByProArea(String provinces, String area) throws SerException {
        return null;
    }

    /**
     * 获取所有的省份
     */
    default List<String> findProvinces()throws SerException{
        return null;
    }
    /**
     * 根据省份获取对应的地区
     */
    default List<String> findAreaByPro(String provinces)throws SerException{
        return null;
    }
}