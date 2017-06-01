package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveWeekSumDTO;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveWeekSumTO;

import java.util.List;

/**
 * 地区部门领用周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaDepartReceiveWeekSumAPI {

    /**
     * 根据id查询地区部门领用周汇总
     *
     * @param id 地区部门领用周汇总唯一标识
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    AreaDepartReceiveWeekSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 地区部门领用周汇总dto
     * @throws SerException
     */
    Long count(AreaDepartReceiveWeekSumDTO dto) throws SerException;

    /**
     * 分页查询地区部门领用周汇总
     *
     * @param dto 地区部门领用周汇总dto
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    List<AreaDepartReceiveWeekSumBO> list(AreaDepartReceiveWeekSumDTO dto) throws SerException;

    /**
     * 保存地区部门领用周汇总
     *
     * @param to 地区部门领用周汇总to
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    AreaDepartReceiveWeekSumBO save(AreaDepartReceiveWeekSumTO to) throws SerException;

    /**
     * 根据id删除地区部门领用周汇总
     *
     * @param id 地区部门领用周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区部门领用周汇总
     *
     * @param to 地区部门领用周汇总to
     * @throws SerException
     */
    void update(AreaDepartReceiveWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    List<AreaDepartReceiveWeekSumBO> summary() throws SerException;

}