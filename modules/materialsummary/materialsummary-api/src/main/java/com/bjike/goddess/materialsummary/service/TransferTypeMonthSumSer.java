package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.TransferTypeMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeMonthSum;
import com.bjike.goddess.materialsummary.to.TransferTypeMonthSumTO;

import java.util.List;

/**
 * 调动类型月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferTypeMonthSumSer extends Ser<TransferTypeMonthSum, TransferTypeMonthSumDTO> {

    /**
     * 分页查询调动类型月汇总
     *
     * @param dto 调动类型月汇总dto
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    List<TransferTypeMonthSumBO> list(TransferTypeMonthSumDTO dto) throws SerException;

    /**
     * 保存调动类型月汇总
     *
     * @param to 调动类型月汇总to
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    TransferTypeMonthSumBO save(TransferTypeMonthSumTO to) throws SerException;

    /**
     * 根据id删除调动类型月汇总
     *
     * @param id 调动类型月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动类型月汇总
     *
     * @param to 调动类型月汇总to
     * @throws SerException
     */
    void update(TransferTypeMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferTypeMonthSumBO
     * @throws SerException
     */
    List<TransferTypeMonthSumBO> summary() throws SerException;

}