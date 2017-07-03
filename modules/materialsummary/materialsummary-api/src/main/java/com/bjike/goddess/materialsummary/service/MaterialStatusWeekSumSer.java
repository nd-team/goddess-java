package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.MaterialStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusWeekSum;
import com.bjike.goddess.materialsummary.to.MaterialStatusWeekSumTO;

import java.util.List;

/**
 * 物资状态周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialStatusWeekSumSer extends Ser<MaterialStatusWeekSum, MaterialStatusWeekSumDTO> {

    /**
     * 分页查询物资状态周汇总
     *
     * @param dto 物资状态周汇总dto
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    List<MaterialStatusWeekSumBO> list(MaterialStatusWeekSumDTO dto) throws SerException;

    /**
     * 保存物资状态周汇总
     *
     * @param to 物资状态周汇总to
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    MaterialStatusWeekSumBO save(MaterialStatusWeekSumTO to) throws SerException;

    /**
     * 根据id删除物资状态周汇总
     *
     * @param id 物资状态周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资状态周汇总
     *
     * @param to 物资状态周汇总to
     * @throws SerException
     */
    void update(MaterialStatusWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    List<MaterialStatusWeekSumBO> summary() throws SerException;

}