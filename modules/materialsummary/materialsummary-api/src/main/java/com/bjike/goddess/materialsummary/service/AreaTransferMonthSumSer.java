package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.AreaTransferMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferMonthSum;
import com.bjike.goddess.materialsummary.to.AreaTransferMonthSumTO;

import java.util.List;

/**
 * 地区调动月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:48 ]
 * @Description: [ 地区调动月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaTransferMonthSumSer extends Ser<AreaTransferMonthSum, AreaTransferMonthSumDTO> {

    /**
     * 分页查询地区调动月汇总
     *
     * @param dto 地区调动月汇总dto
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    List<AreaTransferMonthSumBO> list(AreaTransferMonthSumDTO dto) throws SerException;

    /**
     * 保存地区调动月汇总
     *
     * @param to 地区调动月汇总to
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    AreaTransferMonthSumBO save(AreaTransferMonthSumTO to) throws SerException;

    /**
     * 根据id删除地区调动月汇总
     *
     * @param id 地区调动月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区调动月汇总
     *
     * @param to 地区调动月汇总to
     * @throws SerException
     */
    void update(AreaTransferMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    List<AreaTransferMonthSumBO> summary() throws SerException;

}