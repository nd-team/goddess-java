package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.TransferHandlerDaySumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerDaySumDTO;
import com.bjike.goddess.materialsummary.to.TransferHandlerDaySumTO;

import java.util.List;

/**
 * 调动经手人日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:53 ]
 * @Description: [ 调动经手人日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferHandlerDaySumAPI {

    /**
     * 根据id查询调动经手人日汇总
     *
     * @param id 调动经手人日汇总唯一标识
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    TransferHandlerDaySumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 调动经手人日汇总dto
     * @throws SerException
     */
    Long count(TransferHandlerDaySumDTO dto) throws SerException;

    /**
     * 分页查询调动经手人日汇总
     *
     * @param dto 调动经手人日汇总dto
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    List<TransferHandlerDaySumBO> list(TransferHandlerDaySumDTO dto) throws SerException;

    /**
     * 保存调动经手人日汇总
     *
     * @param to 调动经手人日汇总to
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    TransferHandlerDaySumBO save(TransferHandlerDaySumTO to) throws SerException;

    /**
     * 根据id删除调动经手人日汇总
     *
     * @param id 调动经手人日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动经手人日汇总
     *
     * @param to 调动经手人日汇总to
     * @throws SerException
     */
    void update(TransferHandlerDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferHandlerDaySumBO
     * @throws SerException
     */
    List<TransferHandlerDaySumBO> summary() throws SerException;

}