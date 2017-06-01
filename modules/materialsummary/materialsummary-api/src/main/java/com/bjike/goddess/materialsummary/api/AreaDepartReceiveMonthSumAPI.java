package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveMonthSumDTO;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveMonthSumTO;

import java.util.List;

/**
 * 地区部门领用月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaDepartReceiveMonthSumAPI {

    /**
     * 根据id查询地区部门领用月汇总
     *
     * @param id 地区部门领用月汇总唯一标识
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    AreaDepartReceiveMonthSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 地区部门领用月汇总dto
     * @throws SerException
     */
    Long count(AreaDepartReceiveMonthSumDTO dto) throws SerException;

    /**
     * 分页查询地区部门领用月汇总
     *
     * @param dto 地区部门领用月汇总dto
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    List<AreaDepartReceiveMonthSumBO> list(AreaDepartReceiveMonthSumDTO dto) throws SerException;

    /**
     * 保存地区部门领用月汇总
     *
     * @param to 地区部门领用月汇总to
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    AreaDepartReceiveMonthSumBO save(AreaDepartReceiveMonthSumTO to) throws SerException;

    /**
     * 根据id删除地区部门领用月汇总
     *
     * @param id 地区部门领用月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区部门领用月汇总
     *
     * @param to 地区部门领用月汇总to
     * @throws SerException
     */
    void update(AreaDepartReceiveMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    List<AreaDepartReceiveMonthSumBO> summary() throws SerException;

}