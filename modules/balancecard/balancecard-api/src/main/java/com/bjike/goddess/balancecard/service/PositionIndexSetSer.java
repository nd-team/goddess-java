package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.PositionIndexSetBO;
import com.bjike.goddess.balancecard.to.PositionIndexSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;

import java.util.List;

/**
 * 岗位指标设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PositionIndexSetSer extends Ser<PositionIndexSet, PositionIndexSetDTO> {



    /**
     * 岗位指标列表总条数
     */
    default Long countPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 岗位指标列表id
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO getOneById (String id) throws SerException {return null;}


    /**
     * 岗位指标列表
     *
     * @return class PositionIndexSetBO
     */
    default List<PositionIndexSetBO> listPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO addPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO editPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deletePositionIndexSet(String id) throws SerException {
        return;
    }

    ;

    /**
     * 本月岗位指标列表总条数
     */
    default Long countNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 本月岗位指标列表
     *
     * @return class PositionIndexSetBO
     */
    default List<PositionIndexSetBO> listNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }


    /**
     * 我的指标列表总条数
     */
    default Long countSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }



    /**
     * 我的指标列表
     *
     * @return class PositionIndexSetBO
     */
    default List<PositionIndexSetBO> listSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加我的指标
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO addSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;

    }

    /**
     * 编辑我的指标
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO editSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 删除我的指标
     *
     * @param id id
     */
    default void deleteSelf(String id) throws SerException {
        return;
    }

    ;



}