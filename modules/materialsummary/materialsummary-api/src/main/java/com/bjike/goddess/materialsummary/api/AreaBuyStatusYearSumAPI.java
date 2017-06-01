package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusYearSumDTO;
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
public interface AreaBuyStatusYearSumAPI {

    /**
     * 根据id查询地区购买情况年汇总
     *
     * @param id 地区购买情况年汇总唯一标识
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    AreaBuyStatusYearSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 地区购买情况年汇总dto
     * @throws SerException
     */
    Long count(AreaBuyStatusYearSumDTO dto) throws SerException;

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