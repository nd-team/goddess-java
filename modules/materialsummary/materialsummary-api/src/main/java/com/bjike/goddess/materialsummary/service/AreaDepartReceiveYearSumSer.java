package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveYearSum;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveYearSumTO;

import java.util.List;

/**
 * 地区部门领用年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaDepartReceiveYearSumSer extends Ser<AreaDepartReceiveYearSum, AreaDepartReceiveYearSumDTO> {

    /**
     * 分页查询地区部门领用年汇总
     *
     * @param dto 地区部门领用年汇总dto
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    List<AreaDepartReceiveYearSumBO> list(AreaDepartReceiveYearSumDTO dto) throws SerException;

    /**
     * 保存地区部门领用年汇总
     *
     * @param to 地区部门领用年汇总to
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    AreaDepartReceiveYearSumBO save(AreaDepartReceiveYearSumTO to) throws SerException;

    /**
     * 根据id删除地区部门领用年汇总
     *
     * @param id 地区部门领用年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区部门领用年汇总
     *
     * @param to 地区部门领用年汇总to
     * @throws SerException
     */
    void update(AreaDepartReceiveYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    List<AreaDepartReceiveYearSumBO> summary() throws SerException;

}