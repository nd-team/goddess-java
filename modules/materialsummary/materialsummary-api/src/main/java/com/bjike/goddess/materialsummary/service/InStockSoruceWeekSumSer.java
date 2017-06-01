package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.InStockSoruceWeekSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceWeekSum;
import com.bjike.goddess.materialsummary.to.InStockSoruceWeekSumTO;

import java.util.List;

/**
 * 入库来源周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:05 ]
 * @Description: [ 入库来源周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InStockSoruceWeekSumSer extends Ser<InStockSoruceWeekSum, InStockSoruceWeekSumDTO> {

    /**
     * 分页查询入库来源周汇总
     *
     * @param dto 入库来源周汇总dto
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    List<InStockSoruceWeekSumBO> list(InStockSoruceWeekSumDTO dto) throws SerException;

    /**
     * 保存入库来源周汇总
     *
     * @param to 入库来源周汇总to
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    InStockSoruceWeekSumBO save(InStockSoruceWeekSumTO to) throws SerException;

    /**
     * 根据id删除入库来源周汇总
     *
     * @param id 入库来源周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新入库来源周汇总
     *
     * @param to 入库来源周汇总to
     * @throws SerException
     */
    void update(InStockSoruceWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class InStockSoruceWeekSumBO
     * @throws SerException
     */
    List<InStockSoruceWeekSumBO> summary() throws SerException;

}