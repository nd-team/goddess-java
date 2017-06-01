package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.TransferHandlerYearSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerYearSumDTO;
import com.bjike.goddess.materialsummary.to.TransferHandlerYearSumTO;

import java.util.List;

/**
 * 调动经手人年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:56 ]
 * @Description: [ 调动经手人年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferHandlerYearSumAPI {

    /**
     * 根据id查询调动经手人年汇总
     *
     * @param id 调动经手人年汇总唯一标识
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    TransferHandlerYearSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 调动经手人年汇总dto
     * @throws SerException
     */
    Long count(TransferHandlerYearSumDTO dto) throws SerException;

    /**
     * 分页查询调动经手人年汇总
     *
     * @param dto 调动经手人年汇总dto
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    List<TransferHandlerYearSumBO> list(TransferHandlerYearSumDTO dto) throws SerException;

    /**
     * 保存调动经手人年汇总
     *
     * @param to 调动经手人年汇总to
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    TransferHandlerYearSumBO save(TransferHandlerYearSumTO to) throws SerException;

    /**
     * 根据id删除调动经手人年汇总
     *
     * @param id 调动经手人年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动经手人年汇总
     *
     * @param to 调动经手人年汇总to
     * @throws SerException
     */
    void update(TransferHandlerYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferHandlerYearSumBO
     * @throws SerException
     */
    List<TransferHandlerYearSumBO> summary() throws SerException;

}