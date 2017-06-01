package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveDaySumDTO;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveDaySumTO;

import java.util.List;

/**
 * 地区部门领用日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:29 ]
 * @Description: [ 地区部门领用日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaDepartReceiveDaySumAPI {

    /**
     * 根据id查询地区部门领用日汇总
     *
     * @param id 地区部门领用日汇总唯一标识
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    AreaDepartReceiveDaySumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 地区部门领用日汇总dto
     * @throws SerException
     */
    Long count(AreaDepartReceiveDaySumDTO dto) throws SerException;

    /**
     * 分页查询地区部门领用日汇总
     *
     * @param dto 地区部门领用日汇总dto
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    List<AreaDepartReceiveDaySumBO> list(AreaDepartReceiveDaySumDTO dto) throws SerException;

    /**
     * 保存地区部门领用日汇总
     *
     * @param to 地区部门领用日汇总to
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    AreaDepartReceiveDaySumBO save(AreaDepartReceiveDaySumTO to) throws SerException;

    /**
     * 根据id删除地区部门领用日汇总
     *
     * @param id 地区部门领用日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区部门领用日汇总
     *
     * @param to 地区部门领用日汇总to
     * @throws SerException
     */
    void update(AreaDepartReceiveDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    List<AreaDepartReceiveDaySumBO> summary() throws SerException;

}