package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.TransferHandlerMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerMonthSum;
import com.bjike.goddess.materialsummary.to.TransferHandlerMonthSumTO;

import java.util.List;

/**
 * 调动经手人月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ 调动经手人月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferHandlerMonthSumSer extends Ser<TransferHandlerMonthSum, TransferHandlerMonthSumDTO> {

    /**
     * 分页查询调动经手人月汇总
     *
     * @param dto 调动经手人月汇总dto
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    List<TransferHandlerMonthSumBO> list(TransferHandlerMonthSumDTO dto) throws SerException;

    /**
     * 保存调动经手人月汇总
     *
     * @param to 调动经手人月汇总to
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    TransferHandlerMonthSumBO save(TransferHandlerMonthSumTO to) throws SerException;

    /**
     * 根据id删除调动经手人月汇总
     *
     * @param id 调动经手人月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动经手人月汇总
     *
     * @param to 调动经手人月汇总to
     * @throws SerException
     */
    void update(TransferHandlerMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferHandlerMonthSumBO
     * @throws SerException
     */
    List<TransferHandlerMonthSumBO> summary() throws SerException;

}