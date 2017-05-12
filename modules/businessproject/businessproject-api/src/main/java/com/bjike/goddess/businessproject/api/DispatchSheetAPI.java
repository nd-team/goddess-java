package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 商务项目派工单信息管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DispatchSheetAPI {

    /**
     * 派工单信息列表总条数
     *
     */
    default Long countDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取派工单信息列表
     * @return class DispatchSheetBO
     */
    default DispatchSheetBO getOneById(String id) throws SerException {return null;}


    /**
     * 派工单信息列表
     * @return class DispatchSheetBO
     */
    default List<DispatchSheetBO> listDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {return null;}
    /**
     *  添加
     * @param dispatchSheetTO 派工单信息
     * @return class DispatchSheetBO
     */
    default DispatchSheetBO addDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException { return null;}

    /**
     *  编辑
     * @param dispatchSheetTO 派工单信息
     * @return class DispatchSheetBO
     */
    default DispatchSheetBO editDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteDispatchSheet(String id ) throws SerException {return;};

    /**
     * 搜索
     * @param dispatchSheetDTO 搜索
     * @return class DispatchSheetBO
     */
    default List<DispatchSheetBO> searchDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws  SerException {
        return null;
    }

    /**
     * 获取地区
     * @return class String
     */
    default List<String> listArea( ) throws  SerException {
        return null;
    }

    /**
     * 获取派工单名称
     * @return class String
     */
    default List<String> listDispatchName( ) throws  SerException {
        return null;
    }

}