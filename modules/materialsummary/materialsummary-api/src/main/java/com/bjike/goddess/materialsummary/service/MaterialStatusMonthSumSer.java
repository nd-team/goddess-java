package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.MaterialStatusMonthSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusMonthSum;
import com.bjike.goddess.materialsummary.to.MaterialStatusMonthSumTO;

import java.util.List;

/**
 * 物资状态月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialStatusMonthSumSer extends Ser<MaterialStatusMonthSum, MaterialStatusMonthSumDTO> {

    /**
     * 分页查询物资状态月汇总
     *
     * @param dto 物资状态月汇总dto
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    List<MaterialStatusMonthSumBO> list(MaterialStatusMonthSumDTO dto) throws SerException;

    /**
     * 保存物资状态月汇总
     *
     * @param to 物资状态月汇总to
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    MaterialStatusMonthSumBO save(MaterialStatusMonthSumTO to) throws SerException;

    /**
     * 根据id删除物资状态月汇总
     *
     * @param id 物资状态月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资状态月汇总
     *
     * @param to 物资状态月汇总to
     * @throws SerException
     */
    void update(MaterialStatusMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    List<MaterialStatusMonthSumBO> summary() throws SerException;

}