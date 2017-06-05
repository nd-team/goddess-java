package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.InStockSoruceYearSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceYearSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceYearSum;
import com.bjike.goddess.materialsummary.to.InStockSoruceYearSumTO;

import java.util.List;

/**
 * 入库来源年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:07 ]
 * @Description: [ 入库来源年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockSoruceYearSumSer extends Ser<InStockSoruceYearSum, InStockSoruceYearSumDTO> {

    /**
     * 分页查询入库来源年汇总
     *
     * @param dto 入库来源年汇总dto
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    List<InStockSoruceYearSumBO> list(InStockSoruceYearSumDTO dto) throws SerException;

    /**
     * 保存入库来源年汇总
     *
     * @param to 入库来源年汇总to
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    InStockSoruceYearSumBO save(InStockSoruceYearSumTO to) throws SerException;

    /**
     * 根据id删除入库来源年汇总
     *
     * @param id 入库来源年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库来源年汇总
     *
     * @param to 入库来源年汇总to
     * @throws SerException
     */
    void update(InStockSoruceYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockSoruceYearSumBO
     * @throws SerException
     */
    List<InStockSoruceYearSumBO> summary() throws SerException;

}