package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.InStockAreaWeekSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaWeekSumDTO;
import com.bjike.goddess.materialsummary.to.InStockAreaWeekSumTO;

import java.util.List;

/**
 * 入库地区周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:10 ]
 * @Description: [ 入库地区周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockAreaWeekSumAPI {

    /**
     * 根据id查询入库地区周汇总
     *
     * @param id 入库地区周汇总唯一标识
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    InStockAreaWeekSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 入库地区周汇总dto
     * @throws SerException
     */
    Long count(InStockAreaWeekSumDTO dto) throws SerException;

    /**
     * 分页查询入库地区周汇总
     *
     * @param dto 入库地区周汇总dto
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    List<InStockAreaWeekSumBO> list(InStockAreaWeekSumDTO dto) throws SerException;

    /**
     * 保存入库地区周汇总
     *
     * @param to 入库地区周汇总to
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    InStockAreaWeekSumBO save(InStockAreaWeekSumTO to) throws SerException;

    /**
     * 根据id删除入库地区周汇总
     *
     * @param id 入库地区周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库地区周汇总
     *
     * @param to 入库地区周汇总to
     * @throws SerException
     */
    void update(InStockAreaWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockAreaWeekSumBO
     * @throws SerException
     */
    List<InStockAreaWeekSumBO> summary() throws SerException;

}