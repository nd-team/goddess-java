package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.bo.ArrivalWeekCountBO;
import com.bjike.goddess.budget.dto.ArrivalWeekDTO;
import com.bjike.goddess.budget.entity.ArrivalWeek;
import com.bjike.goddess.budget.to.ArrivalWeekTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 地区收入周业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:03 ]
 * @Description: [ 地区收入周业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArrivalWeekSer extends Ser<ArrivalWeek, ArrivalWeekDTO> {
    /**
     * 添加
     *
     * @param to 地区收入周信息
     * @return class ArrivalWeekBO
     * @throws SerException
     */
    default ArrivalWeekBO save(ArrivalWeekTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 地区收入周信息
     * @throws SerException
     */
    default void edit(ArrivalWeekTO to) throws SerException {
    }

    /**
     * 删除
     *
     * @param id 地区收入周id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 查找
     *
     * @param dto 地区收入周分页信息
     * @return class ArrivalWeekBO
     * @throws SerException
     */
    default List<ArrivalWeekBO> list(ArrivalWeekDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 地区收入周id
     * @return class ArrivalWeekBO
     * @throws SerException
     */
    default ArrivalWeekBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @return class ArrivalWeekCountBO
     * @throws SerException
     */
    default List<ArrivalWeekCountBO> count() throws SerException {
        return null;
    }

    /**
     * 分地区汇总
     *
     * @param arrivals 地区数组
     * @return class ArrivalWeekCountBO
     * @throws SerException
     */
    default List<ArrivalWeekCountBO> conditionsCount(String[] arrivals) throws SerException {
        return null;
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @return class Long
     * @throws SerException
     */
    default Long countNum(ArrivalWeekDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找所有地区
     *
     * @throws SerException
     */
    default List<String> findAllArrivals() throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    List<SonPermissionObject> sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
}