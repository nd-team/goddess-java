package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.TransferTypeYearSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeYearSumDTO;
import com.bjike.goddess.materialsummary.to.TransferTypeYearSumTO;

import java.util.List;

/**
 * 调动类型年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:45 ]
 * @Description: [ 调动类型年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferTypeYearSumAPI {

    /**
     * 根据id查询调动类型年汇总
     *
     * @param id 调动类型年汇总唯一标识
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    TransferTypeYearSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 调动类型年汇总dto
     * @throws SerException
     */
    Long count(TransferTypeYearSumDTO dto) throws SerException;

    /**
     * 分页查询调动类型年汇总
     *
     * @param dto 调动类型年汇总dto
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    List<TransferTypeYearSumBO> list(TransferTypeYearSumDTO dto) throws SerException;

    /**
     * 保存调动类型年汇总
     *
     * @param to 调动类型年汇总to
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    TransferTypeYearSumBO save(TransferTypeYearSumTO to) throws SerException;

    /**
     * 根据id删除调动类型年汇总
     *
     * @param id 调动类型年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动类型年汇总
     *
     * @param to 调动类型年汇总to
     * @throws SerException
     */
    void update(TransferTypeYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferTypeYearSumBO
     * @throws SerException
     */
    List<TransferTypeYearSumBO> summary() throws SerException;

}