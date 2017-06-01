package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.RepairStateDaySumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateDaySumDTO;
import com.bjike.goddess.materialsummary.to.RepairStateDaySumTO;

import java.util.List;

/**
 * 维修状态日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:52 ]
 * @Description: [ 维修状态日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RepairStateDaySumAPI {

    /**
     * 根据id查询维修状态日汇总
     *
     * @param id 维修状态日汇总唯一标识
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    RepairStateDaySumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 维修状态日汇总dto
     * @throws SerException
     */
    Long count(RepairStateDaySumDTO dto) throws SerException;

    /**
     * 分页查询维修状态日汇总
     *
     * @param dto 维修状态日汇总dto
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    List<RepairStateDaySumBO> list(RepairStateDaySumDTO dto) throws SerException;

    /**
     * 保存维修状态日汇总
     *
     * @param to 维修状态日汇总to
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    RepairStateDaySumBO save(RepairStateDaySumTO to) throws SerException;

    /**
     * 根据id删除维修状态日汇总
     *
     * @param id 维修状态日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新维修状态日汇总
     *
     * @param to 维修状态日汇总to
     * @throws SerException
     */
    void update(RepairStateDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    List<RepairStateDaySumBO> summary() throws SerException;

}