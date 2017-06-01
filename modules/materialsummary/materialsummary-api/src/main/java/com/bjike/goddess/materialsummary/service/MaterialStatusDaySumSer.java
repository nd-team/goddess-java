package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.MaterialStatusDaySumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusDaySum;
import com.bjike.goddess.materialsummary.to.MaterialStatusDaySumTO;

import java.util.List;

/**
 * 物资状态日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:18 ]
 * @Description: [ 物资状态日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialStatusDaySumSer extends Ser<MaterialStatusDaySum, MaterialStatusDaySumDTO> {

    /**
     * 分页查询物资状态日汇总
     *
     * @param dto 物资状态日汇总dto
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    List<MaterialStatusDaySumBO> list(MaterialStatusDaySumDTO dto) throws SerException;

    /**
     * 保存物资状态日汇总
     *
     * @param to 物资状态日汇总to
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    MaterialStatusDaySumBO save(MaterialStatusDaySumTO to) throws SerException;

    /**
     * 根据id删除物资状态日汇总
     *
     * @param id 物资状态日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资状态日汇总
     *
     * @param to 物资状态日汇总to
     * @throws SerException
     */
    void update(MaterialStatusDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    List<MaterialStatusDaySumBO> summary() throws SerException;

}