package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusWeekSum;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusWeekSumTO;

import java.util.List;

/**
 * 地区购买情况周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:54 ]
 * @Description: [ 地区购买情况周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaBuyStatusWeekSumSer extends Ser<AreaBuyStatusWeekSum, AreaBuyStatusWeekSumDTO> {

    /**
     * 分页查询地区购买情况周汇总
     *
     * @param dto 地区购买情况周汇总dto
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    List<AreaBuyStatusWeekSumBO> list(AreaBuyStatusWeekSumDTO dto) throws SerException;

    /**
     * 保存地区购买情况周汇总
     *
     * @param to 地区购买情况周汇总to
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    AreaBuyStatusWeekSumBO save(AreaBuyStatusWeekSumTO to) throws SerException;

    /**
     * 根据id删除地区购买情况周汇总
     *
     * @param id 地区购买情况周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区购买情况周汇总
     *
     * @param to 地区购买情况周汇总to
     * @throws SerException
     */
    void update(AreaBuyStatusWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    List<AreaBuyStatusWeekSumBO> summary() throws SerException;

}