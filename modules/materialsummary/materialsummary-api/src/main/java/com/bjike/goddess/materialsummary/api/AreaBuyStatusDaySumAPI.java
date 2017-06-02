package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusDaySumDTO;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusDaySumTO;

import java.util.List;

/**
 * 地区购买情况日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:51 ]
 * @Description: [ 地区购买情况日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaBuyStatusDaySumAPI {

    /**
     * 根据id查询地区购买情况日汇总
     *
     * @param id 地区购买情况日汇总唯一标识
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    AreaBuyStatusDaySumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 地区购买情况日汇总dto
     * @throws SerException
     */
    Long count(AreaBuyStatusDaySumDTO dto) throws SerException;

    /**
     * 分页查询地区购买情况日汇总
     *
     * @param dto 地区购买情况日汇总dto
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    List<AreaBuyStatusDaySumBO> list(AreaBuyStatusDaySumDTO dto) throws SerException;

    /**
     * 保存地区购买情况日汇总
     *
     * @param to 地区购买情况日汇总to
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    AreaBuyStatusDaySumBO save(AreaBuyStatusDaySumTO to) throws SerException;

    /**
     * 根据id删除地区购买情况日汇总
     *
     * @param id 地区购买情况日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新地区购买情况日汇总
     *
     * @param to 地区购买情况日汇总to
     * @throws SerException
     */
    void update(AreaBuyStatusDaySumTO to) throws SerException;

    /**
     * 汇总
     * 
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    List<AreaBuyStatusDaySumBO> summary() throws SerException;

}