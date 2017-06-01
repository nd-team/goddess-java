package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.RepairStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateWeekSumDTO;
import com.bjike.goddess.materialsummary.to.RepairStateWeekSumTO;

import java.util.List;

/**
 * 维修状态周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ 维修状态周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RepairStateWeekSumAPI {

    /**
     * 根据id查询维修状态周汇总
     *
     * @param id 维修状态周汇总唯一标识
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    RepairStateWeekSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 维修状态周汇总dto
     * @throws SerException
     */
    Long count(RepairStateWeekSumDTO dto) throws SerException;

    /**
     * 分页查询维修状态周汇总
     *
     * @param dto 维修状态周汇总dto
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    List<RepairStateWeekSumBO> list(RepairStateWeekSumDTO dto) throws SerException;

    /**
     * 保存维修状态周汇总
     *
     * @param to 维修状态周汇总to
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    RepairStateWeekSumBO save(RepairStateWeekSumTO to) throws SerException;

    /**
     * 根据id删除维修状态周汇总
     *
     * @param id 维修状态周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新维修状态周汇总
     *
     * @param to 维修状态周汇总to
     * @throws SerException
     */
    void update(RepairStateWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    List<RepairStateWeekSumBO> summary() throws SerException;

}