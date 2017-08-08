package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.RightSetBO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.RightSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.assistance.entity.RightSet;
import com.bjike.goddess.assistance.dto.RightSetDTO;

import java.util.List;

/**
 * 权限设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:14 ]
 * @Description: [ 权限设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RightSetSer extends Ser<RightSet, RightSetDTO> {
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
     * 一个权限设置
     * @return class RightSetBO
     */
    default RightSetBO getOneById(String id) throws SerException {return null;}

    
    /**
     * 权限设置列表总条数
     *
     */
    default Long countRightSet(RightSetDTO rightSetDTO) throws SerException {
        return null;
    }
    /**
     * 权限设置列表
     * @return class RightSetBO
     */
    default List<RightSetBO> listRightSet(RightSetDTO rightSetDTO) throws SerException {return null;}
    /**
     *  添加
     * @param rightSetTO 权限设置信息
     * @return class RightSetBO
     */
    default RightSetBO addRightSet(RightSetTO rightSetTO) throws SerException { return null;}

    /**
     *  编辑
     * @param rightSetTO 权限设置信息
     * @return class RightSetBO
     */
    default RightSetBO editRightSet(RightSetTO rightSetTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteRightSet(String id ) throws SerException {return;};



}