package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.InStockAreaYearSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaYearSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaYearSum;
import com.bjike.goddess.materialsummary.to.InStockAreaYearSumTO;

import java.util.List;

/**
 * 入库地区年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ 入库地区年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockAreaYearSumSer extends Ser<InStockAreaYearSum, InStockAreaYearSumDTO> {

    /**
     * 分页查询入库地区年汇总
     *
     * @param dto 入库地区年汇总dto
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    List<InStockAreaYearSumBO> list(InStockAreaYearSumDTO dto) throws SerException;

    /**
     * 保存入库地区年汇总
     *
     * @param to 入库地区年汇总to
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    InStockAreaYearSumBO save(InStockAreaYearSumTO to) throws SerException;

    /**
     * 根据id删除入库地区年汇总
     *
     * @param id 入库地区年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库地区年汇总
     *
     * @param to 入库地区年汇总to
     * @throws SerException
     */
    void update(InStockAreaYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockAreaYearSumBO
     * @throws SerException
     */
    List<InStockAreaYearSumBO> summary() throws SerException;

}