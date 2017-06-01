package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.WarrantyStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateWeekSumDTO;
import com.bjike.goddess.materialsummary.to.WarrantyStateWeekSumTO;

import java.util.List;

/**
 * 保修状态周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:50 ]
 * @Description: [ 保修状态周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WarrantyStateWeekSumAPI {

    /**
     * 根据id查询保修状态周汇总
     *
     * @param id 保修状态周汇总唯一标识
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    WarrantyStateWeekSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 保修状态周汇总dto
     * @throws SerException
     */
    Long count(WarrantyStateWeekSumDTO dto) throws SerException;

    /**
     * 分页查询保修状态周汇总
     *
     * @param dto 保修状态周汇总dto
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    List<WarrantyStateWeekSumBO> list(WarrantyStateWeekSumDTO dto) throws SerException;

    /**
     * 保存保修状态周汇总
     *
     * @param to 保修状态周汇总to
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    WarrantyStateWeekSumBO save(WarrantyStateWeekSumTO to) throws SerException;

    /**
     * 根据id删除保修状态周汇总
     *
     * @param id 保修状态周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新保修状态周汇总
     *
     * @param to 保修状态周汇总to
     * @throws SerException
     */
    void update(WarrantyStateWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    List<WarrantyStateWeekSumBO> summary() throws SerException;

}