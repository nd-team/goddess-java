package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.WarrantyStateDaySumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateDaySumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateDaySum;
import com.bjike.goddess.materialsummary.to.WarrantyStateDaySumTO;

import java.util.List;

/**
 * 保修状态日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:05 ]
 * @Description: [ 保修状态日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WarrantyStateDaySumSer extends Ser<WarrantyStateDaySum, WarrantyStateDaySumDTO> {

    /**
     * 分页查询保修状态日汇总
     *
     * @param dto 保修状态日汇总dto
     * @return class WarrantyStateDaySumBO
     * @throws SerException
     */
    List<WarrantyStateDaySumBO> list(WarrantyStateDaySumDTO dto) throws SerException;

    /**
     * 保存保修状态日汇总
     *
     * @param to 保修状态日汇总to
     * @return class WarrantyStateDaySumBO
     * @throws SerException
     */
    WarrantyStateDaySumBO save(WarrantyStateDaySumTO to) throws SerException;

    /**
     * 根据id删除保修状态日汇总
     *
     * @param id 保修状态日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新保修状态日汇总
     *
     * @param to 保修状态日汇总to
     * @throws SerException
     */
    void update(WarrantyStateDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class WarrantyStateDaySumBO
     * @throws SerException
     */
    List<WarrantyStateDaySumBO> summary() throws SerException;

}