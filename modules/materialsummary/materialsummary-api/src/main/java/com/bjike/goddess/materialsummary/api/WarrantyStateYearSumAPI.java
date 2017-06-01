package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.WarrantyStateYearSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateYearSumDTO;
import com.bjike.goddess.materialsummary.to.WarrantyStateYearSumTO;

import java.util.List;

/**
 * 保修状态年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:16 ]
 * @Description: [ 保修状态年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WarrantyStateYearSumAPI {

    /**
     * 根据id查询保修状态年汇总
     *
     * @param id 保修状态年汇总唯一标识
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    WarrantyStateYearSumBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 保修状态年汇总dto
     * @throws SerException
     */
    Long count(WarrantyStateYearSumDTO dto) throws SerException;

    /**
     * 分页查询保修状态年汇总
     *
     * @param dto 保修状态年汇总dto
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    List<WarrantyStateYearSumBO> list(WarrantyStateYearSumDTO dto) throws SerException;

    /**
     * 保存保修状态年汇总
     *
     * @param to 保修状态年汇总to
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    WarrantyStateYearSumBO save(WarrantyStateYearSumTO to) throws SerException;

    /**
     * 根据id删除保修状态年汇总
     *
     * @param id 保修状态年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新保修状态年汇总
     *
     * @param to 保修状态年汇总to
     * @throws SerException
     */
    void update(WarrantyStateYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    List<WarrantyStateYearSumBO> summary() throws SerException;

}