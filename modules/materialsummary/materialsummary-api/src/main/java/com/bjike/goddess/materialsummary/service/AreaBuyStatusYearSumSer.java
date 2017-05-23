package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusYearSum;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusYearSumTO;

import java.util.List;

/**
 * 地区购买情况年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:56 ]
 * @Description: [ 地区购买情况年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaBuyStatusYearSumSer extends Ser<AreaBuyStatusYearSum, AreaBuyStatusYearSumDTO> {

    /**
     * 分页查询地区购买情况年汇总
     *
     * @param dto 地区购买情况年汇总dto
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    List<AreaBuyStatusYearSumBO> list(AreaBuyStatusYearSumDTO dto) throws SerException;

    /**
     * 保存地区购买情况年汇总
     *
     * @param to 地区购买情况年汇总to
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    AreaBuyStatusYearSumBO save(AreaBuyStatusYearSumTO to) throws SerException;

    /**
     * 根据id删除地区购买情况年汇总
     *
     * @param id 地区购买情况年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区购买情况年汇总
     *
     * @param to 地区购买情况年汇总to
     * @throws SerException
     */
    void update(AreaBuyStatusYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    List<AreaBuyStatusYearSumBO> summary() throws SerException;

}