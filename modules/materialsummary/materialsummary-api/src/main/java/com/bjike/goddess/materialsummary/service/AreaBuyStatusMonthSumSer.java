package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusMonthSum;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusMonthSumTO;

import java.util.List;

/**
 * 地区购买情况月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:54 ]
 * @Description: [ 地区购买情况月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaBuyStatusMonthSumSer extends Ser<AreaBuyStatusMonthSum, AreaBuyStatusMonthSumDTO> {

    /**
     * 分页查询地区购买情况月汇总
     *
     * @param dto 地区购买情况月汇总dto
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    List<AreaBuyStatusMonthSumBO> list(AreaBuyStatusMonthSumDTO dto) throws SerException;

    /**
     * 保存地区购买情况月汇总
     *
     * @param to 地区购买情况月汇总to
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    AreaBuyStatusMonthSumBO save(AreaBuyStatusMonthSumTO to) throws SerException;

    /**
     * 根据id删除地区购买情况月汇总
     *
     * @param id 地区购买情况月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区购买情况月汇总
     *
     * @param to 地区购买情况月汇总to
     * @throws SerException
     */
    void update(AreaBuyStatusMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    List<AreaBuyStatusMonthSumBO> summary() throws SerException;

}