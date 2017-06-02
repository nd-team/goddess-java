package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.TransferHandlerWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerWeekSum;
import com.bjike.goddess.materialsummary.to.TransferHandlerWeekSumTO;

import java.util.List;

/**
 * 调动经手人周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ 调动经手人周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferHandlerWeekSumSer extends Ser<TransferHandlerWeekSum, TransferHandlerWeekSumDTO> {

    /**
     * 分页查询调动经手人周汇总
     *
     * @param dto 调动经手人周汇总dto
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    List<TransferHandlerWeekSumBO> list(TransferHandlerWeekSumDTO dto) throws SerException;

    /**
     * 保存调动经手人周汇总
     *
     * @param to 调动经手人周汇总to
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    TransferHandlerWeekSumBO save(TransferHandlerWeekSumTO to) throws SerException;

    /**
     * 根据id删除调动经手人周汇总
     *
     * @param id 调动经手人周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动经手人周汇总
     *
     * @param to 调动经手人周汇总to
     * @throws SerException
     */
    void update(TransferHandlerWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferHandlerWeekSumBO
     * @throws SerException
     */
    List<TransferHandlerWeekSumBO> summary() throws SerException;

}