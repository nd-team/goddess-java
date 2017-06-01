package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.WarrantyStateMonthSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateMonthSum;
import com.bjike.goddess.materialsummary.to.WarrantyStateMonthSumTO;

import java.util.List;

/**
 * 保修状态月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:15 ]
 * @Description: [ 保修状态月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WarrantyStateMonthSumSer extends Ser<WarrantyStateMonthSum, WarrantyStateMonthSumDTO> {

    /**
     * 分页查询保修状态月汇总
     *
     * @param dto 保修状态月汇总dto
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    List<WarrantyStateMonthSumBO> list(WarrantyStateMonthSumDTO dto) throws SerException;

    /**
     * 保存保修状态月汇总
     *
     * @param to 保修状态月汇总to
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    WarrantyStateMonthSumBO save(WarrantyStateMonthSumTO to) throws SerException;

    /**
     * 根据id删除保修状态月汇总
     *
     * @param id 保修状态月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新保修状态月汇总
     *
     * @param to 保修状态月汇总to
     * @throws SerException
     */
    void update(WarrantyStateMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    List<WarrantyStateMonthSumBO> summary() throws SerException;

}