package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.InStockAreaMonthSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaMonthSum;
import com.bjike.goddess.materialsummary.to.InStockAreaMonthSumTO;

import java.util.List;

/**
 * 入库地区月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ 入库地区月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockAreaMonthSumSer extends Ser<InStockAreaMonthSum, InStockAreaMonthSumDTO> {

    /**
     * 分页查询入库地区月汇总
     *
     * @param dto 入库地区月汇总dto
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    List<InStockAreaMonthSumBO> list(InStockAreaMonthSumDTO dto) throws SerException;

    /**
     * 保存入库地区月汇总
     *
     * @param to 入库地区月汇总to
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    InStockAreaMonthSumBO save(InStockAreaMonthSumTO to) throws SerException;

    /**
     * 根据id删除入库地区月汇总
     *
     * @param id 入库地区月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库地区月汇总
     *
     * @param to 入库地区月汇总to
     * @throws SerException
     */
    void update(InStockAreaMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockAreaMonthSumBO
     * @throws SerException
     */
    List<InStockAreaMonthSumBO> summary() throws SerException;

}