package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.DimensionSetBO;
import com.bjike.goddess.balancecard.to.DimensionSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.balancecard.entity.DimensionSet;
import com.bjike.goddess.balancecard.dto.DimensionSetDTO;

import java.util.List;

/**
 * 维度设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 维度设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DimensionSetSer extends Ser<DimensionSet, DimensionSetDTO> {


    /**
     * 维度设置列表总条数
     */
    default Long countDimensionSet(DimensionSetDTO dimensionSetDTO) throws SerException {
        return null;
    }

    /**
     * 维度设置列表id
     * @return class DimensionSetBO
     */
    default DimensionSetBO getOneById (String id) throws SerException {return null;}


    /**
     * 维度设置列表
     *
     * @return class DimensionSetBO
     */
    default List<DimensionSetBO> listDimensionSet(DimensionSetDTO dimensionSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param dimensionSetTO 维度设置信息
     * @return class DimensionSetBO
     */
    default DimensionSetBO addDimensionSet(DimensionSetTO dimensionSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param dimensionSetTO 维度设置信息
     * @return class DimensionSetBO
     */
    default DimensionSetBO editDimensionSet(DimensionSetTO dimensionSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteDimensionSet(String id) throws SerException {
        return;
    }

    ;


    /**
     * 获取所有维度
     *
     */
    default List<String> listName(   ) throws SerException {
        return null;
    }


    ;


}