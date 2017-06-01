package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.RepairStateYearSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateYearSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateYearSum;
import com.bjike.goddess.materialsummary.to.RepairStateYearSumTO;

import java.util.List;

/**
 * 维修状态年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:48 ]
 * @Description: [ 维修状态年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RepairStateYearSumSer extends Ser<RepairStateYearSum, RepairStateYearSumDTO> {

    /**
     * 分页查询维修状态年汇总
     *
     * @param dto 维修状态年汇总dto
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    List<RepairStateYearSumBO> list(RepairStateYearSumDTO dto) throws SerException;

    /**
     * 保存维修状态年汇总
     *
     * @param to 维修状态年汇总to
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    RepairStateYearSumBO save(RepairStateYearSumTO to) throws SerException;

    /**
     * 根据id删除维修状态年汇总
     *
     * @param id 维修状态年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新维修状态年汇总
     *
     * @param to 维修状态年汇总to
     * @throws SerException
     */
    void update(RepairStateYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    List<RepairStateYearSumBO> summary() throws SerException;

}