package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.TransferTypeDaySumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeDaySum;
import com.bjike.goddess.materialsummary.to.TransferTypeDaySumTO;

import java.util.List;

/**
 * 调动类型日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:40 ]
 * @Description: [ 调动类型日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransferTypeDaySumSer extends Ser<TransferTypeDaySum, TransferTypeDaySumDTO> {

    /**
     * 分页查询调动类型日汇总
     *
     * @param dto 调动类型日汇总dto
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    List<TransferTypeDaySumBO> list(TransferTypeDaySumDTO dto) throws SerException;

    /**
     * 保存调动类型日汇总
     *
     * @param to 调动类型日汇总to
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    TransferTypeDaySumBO save(TransferTypeDaySumTO to) throws SerException;

    /**
     * 根据id删除调动类型日汇总
     *
     * @param id 调动类型日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新调动类型日汇总
     *
     * @param to 调动类型日汇总to
     * @throws SerException
     */
    void update(TransferTypeDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class TransferTypeDaySumBO
     * @throws SerException
     */
    List<TransferTypeDaySumBO> summary() throws SerException;

}