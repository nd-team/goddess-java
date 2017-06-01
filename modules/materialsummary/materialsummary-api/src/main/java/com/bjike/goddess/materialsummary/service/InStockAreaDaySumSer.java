package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.InStockAreaDaySumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaDaySumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaDaySum;
import com.bjike.goddess.materialsummary.to.InStockAreaDaySumTO;

import java.util.List;

/**
 * 入库地区日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:13 ]
 * @Description: [ 入库地区日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockAreaDaySumSer extends Ser<InStockAreaDaySum, InStockAreaDaySumDTO> {

    /**
     * 分页查询入库地区日汇总
     *
     * @param dto 入库地区日汇总dto
     * @return class InStockAreaDaySumBO
     * @throws SerException
     */
    List<InStockAreaDaySumBO> list(InStockAreaDaySumDTO dto) throws SerException;

    /**
     * 保存入库地区日汇总
     *
     * @param to 入库地区日汇总to
     * @return class InStockAreaDaySumBO
     * @throws SerException
     */
    InStockAreaDaySumBO save(InStockAreaDaySumTO to) throws SerException;

    /**
     * 根据id删除入库地区日汇总
     *
     * @param id 入库地区日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库地区日汇总
     *
     * @param to 入库地区日汇总to
     * @throws SerException
     */
    void update(InStockAreaDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockAreaDaySumBO
     * @throws SerException
     */
    List<InStockAreaDaySumBO> summary() throws SerException;

}