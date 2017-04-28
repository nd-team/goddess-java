package com.bjike.goddess.managefee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.entity.OutFee;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.to.OutFeeTO;

import java.util.List;

/**
 * 外包费业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:39 ]
 * @Description: [ 外包费业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OutFeeSer extends Ser<OutFee, OutFeeDTO> {

    /**
     * 外包费列表总条数
     */
    default Long countOutFee(OutFeeDTO outFeeDTO) throws SerException {
        return null;
    }


    /**
     * 外包费列表id
     *
     * @return class OutFeeBO
     */
    default OutFeeBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 外包费列表
     *
     * @return class OutFeeBO
     */
    default List<OutFeeBO> listOutFee(OutFeeDTO outFeeDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param outFeeTO 外包费信息
     * @return class OutFeeBO
     */
    default OutFeeBO addOutFee(OutFeeTO outFeeTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param outFeeTO 外包费信息
     * @return class OutFeeBO
     */
    default OutFeeBO editOutFee(OutFeeTO outFeeTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteOutFee(String id) throws SerException {
        return;
    }

    ;


    /**
     * 根据地区汇总
     *
     * @param outFeeDTO outFeeDTO
     */
    default List<OutFeeBO> collectArea(OutFeeDTO outFeeDTO) throws SerException {

        return null;
    }

    ;/**
     * 根据项目组汇总
     *
     * @param outFeeDTO outFeeDTO
     */
    default List<OutFeeBO> collectGroup(OutFeeDTO outFeeDTO) throws SerException {

        return null;
    }

    ;/**
     * 根据项目汇总
     *
     * @param outFeeDTO outFeeDTO
     */
    default List<OutFeeBO> collectProject(OutFeeDTO outFeeDTO) throws SerException {

        return null;
    }

    ;/**
     * 根据类别汇总
     *
     * @param outFeeDTO outFeeDTO
     */
    default List<OutFeeBO> collectType(OutFeeDTO outFeeDTO) throws SerException {

        return null;
    }

    ;

    /**
     * 获取所有年份
     *
     */
    default List<String> yearList( ) throws SerException {

        return null;
    }

    ;

    /**
     * 获取所有地区
     *
     */
    default List<String> areaList( ) throws SerException {

        return null;
    }

    ;/**
     * 获取所有项目组
     *
     */
    default List<String> groupList( ) throws SerException {

        return null;
    }

    ;/**
     * 获取所有项目
     *
     */
    default List<String> projectList( ) throws SerException {

        return null;
    }

    ;

}