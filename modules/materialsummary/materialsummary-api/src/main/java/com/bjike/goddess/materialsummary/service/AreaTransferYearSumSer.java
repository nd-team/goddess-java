package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.AreaTransferYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferYearSum;
import com.bjike.goddess.materialsummary.to.AreaTransferYearSumTO;

import java.util.List;

/**
 * 地区调动年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:47 ]
 * @Description: [ 地区调动年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaTransferYearSumSer extends Ser<AreaTransferYearSum, AreaTransferYearSumDTO> {

    /**
     * 分页查询地区调动年汇总
     *
     * @param dto 地区调动年汇总dto
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    List<AreaTransferYearSumBO> list(AreaTransferYearSumDTO dto) throws SerException;

    /**
     * 保存地区调动年汇总
     *
     * @param to 地区调动年汇总to
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    AreaTransferYearSumBO save(AreaTransferYearSumTO to) throws SerException;

    /**
     * 根据id删除地区调动年汇总
     *
     * @param id 地区调动年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区调动年汇总
     *
     * @param to 地区调动年汇总to
     * @throws SerException
     */
    void update(AreaTransferYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    List<AreaTransferYearSumBO> summary() throws SerException;

}