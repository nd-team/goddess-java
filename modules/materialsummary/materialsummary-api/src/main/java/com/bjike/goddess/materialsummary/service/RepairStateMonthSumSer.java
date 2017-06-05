package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.RepairStateMonthSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateMonthSum;
import com.bjike.goddess.materialsummary.to.RepairStateMonthSumTO;

import java.util.List;

/**
 * 维修状态月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ 维修状态月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RepairStateMonthSumSer extends Ser<RepairStateMonthSum, RepairStateMonthSumDTO> {

    /**
     * 分页查询维修状态月汇总
     *
     * @param dto 维修状态月汇总dto
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    List<RepairStateMonthSumBO> list(RepairStateMonthSumDTO dto) throws SerException;

    /**
     * 保存维修状态月汇总
     *
     * @param to 维修状态月汇总to
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    RepairStateMonthSumBO save(RepairStateMonthSumTO to) throws SerException;

    /**
     * 根据id删除维修状态月汇总
     *
     * @param id 维修状态月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新维修状态月汇总
     *
     * @param to 维修状态月汇总to
     * @throws SerException
     */
    void update(RepairStateMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    List<RepairStateMonthSumBO> summary() throws SerException;

}