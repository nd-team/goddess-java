package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.InStockSoruceMonthSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceMonthSumDTO;
import com.bjike.goddess.materialsummary.to.InStockSoruceMonthSumTO;

import java.util.List;

/**
 * 入库来源月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:06 ]
 * @Description: [ 入库来源月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockSoruceMonthSumAPI {

    /**
     * 根据id查询入库来源月汇总
     *
     * @param id 入库来源月汇总唯一标识
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    InStockSoruceMonthSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 入库来源月汇总dto
     * @throws SerException
     */
    Long count(InStockSoruceMonthSumDTO dto) throws SerException;

    /**
     * 分页查询入库来源月汇总
     *
     * @param dto 入库来源月汇总dto
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    List<InStockSoruceMonthSumBO> list(InStockSoruceMonthSumDTO dto) throws SerException;

    /**
     * 保存入库来源月汇总
     *
     * @param to 入库来源月汇总to
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    InStockSoruceMonthSumBO save(InStockSoruceMonthSumTO to) throws SerException;

    /**
     * 根据id删除入库来源月汇总
     *
     * @param id 入库来源月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库来源月汇总
     *
     * @param to 入库来源月汇总to
     * @throws SerException
     */
    void update(InStockSoruceMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockSoruceMonthSumBO
     * @throws SerException
     */
    List<InStockSoruceMonthSumBO> summary() throws SerException;

}