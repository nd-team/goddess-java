package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.InStockSoruceDaySumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceDaySumDTO;
import com.bjike.goddess.materialsummary.to.InStockSoruceDaySumTO;

import java.util.List;

/**
 * 入库来源日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:04 ]
 * @Description: [ 入库来源日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockSoruceDaySumAPI {

    /**
     * 根据id查询入库来源日汇总
     *
     * @param id 入库来源日汇总唯一标识
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    InStockSoruceDaySumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 入库来源日汇总dto
     * @throws SerException
     */
    Long count(InStockSoruceDaySumDTO dto) throws SerException;

    /**
     * 分页查询入库来源日汇总
     *
     * @param dto 入库来源日汇总dto
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    List<InStockSoruceDaySumBO> list(InStockSoruceDaySumDTO dto) throws SerException;

    /**
     * 保存入库来源日汇总
     *
     * @param to 入库来源日汇总to
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    InStockSoruceDaySumBO save(InStockSoruceDaySumTO to) throws SerException;

    /**
     * 根据id删除入库来源日汇总
     *
     * @param id 入库来源日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库来源日汇总
     *
     * @param to 入库来源日汇总to
     * @throws SerException
     */
    void update(InStockSoruceDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockSoruceDaySumBO
     * @throws SerException
     */
    List<InStockSoruceDaySumBO> summary() throws SerException;

}