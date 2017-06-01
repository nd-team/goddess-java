package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.AreaTransferWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferWeekSum;
import com.bjike.goddess.materialsummary.to.AreaTransferWeekSumTO;

import java.util.List;

/**
 * 地区调动周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:48 ]
 * @Description: [ 地区调动周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaTransferWeekSumSer extends Ser<AreaTransferWeekSum, AreaTransferWeekSumDTO> {

    /**
     * 分页查询地区调动周汇总
     *
     * @param dto 地区调动周汇总dto
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    List<AreaTransferWeekSumBO> list(AreaTransferWeekSumDTO dto) throws SerException;

    /**
     * 保存地区调动周汇总
     *
     * @param to 地区调动周汇总to
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    AreaTransferWeekSumBO save(AreaTransferWeekSumTO to) throws SerException;

    /**
     * 根据id删除地区调动周汇总
     *
     * @param id 地区调动周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区调动周汇总
     *
     * @param to 地区调动周汇总to
     * @throws SerException
     */
    void update(AreaTransferWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    List<AreaTransferWeekSumBO> summary() throws SerException;

}