package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.TransferTypeWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeWeekSumDTO;
import com.bjike.goddess.materialsummary.to.TransferTypeWeekSumTO;

import java.util.List;

/**
 * 调动类型周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferTypeWeekSumAPI {

    /**
     * 根据id查询调动类型周汇总
     *
     * @param id 调动类型周汇总唯一标识
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    TransferTypeWeekSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 调动类型周汇总dto
     * @throws SerException
     */
    Long count(TransferTypeWeekSumDTO dto) throws SerException;

    /**
     * 分页查询调动类型周汇总
     *
     * @param dto 调动类型周汇总dto
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    List<TransferTypeWeekSumBO> list(TransferTypeWeekSumDTO dto) throws SerException;

    /**
     * 保存调动类型周汇总
     *
     * @param to 调动类型周汇总to
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    TransferTypeWeekSumBO save(TransferTypeWeekSumTO to) throws SerException;

    /**
     * 根据id删除调动类型周汇总
     *
     * @param id 调动类型周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动类型周汇总
     *
     * @param to 调动类型周汇总to
     * @throws SerException
     */
    void update(TransferTypeWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferTypeWeekSumBO
     * @throws SerException
     */
    List<TransferTypeWeekSumBO> summary() throws SerException;

}