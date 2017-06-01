package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyMonthSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyMonthSum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyMonthSumTO;

import java.util.List;

/**
 * 物资分类月汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:46 ]
 * @Description: [ 物资分类月汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialClassifyMonthSumSer extends Ser<MaterialClassifyMonthSum, MaterialClassifyMonthSumDTO> {

    /**
     * 分页查询物资分类月汇总
     *
     * @param dto 物资分类月汇总dto
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    List<MaterialClassifyMonthSumBO> list(MaterialClassifyMonthSumDTO dto) throws SerException;

    /**
     * 保存物资分类月汇总
     *
     * @param to 物资分类月汇总to
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    MaterialClassifyMonthSumBO save(MaterialClassifyMonthSumTO to) throws SerException;

    /**
     * 根据id删除物资分类月汇总
     *
     * @param id 物资分类月汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资分类月汇总
     *
     * @param to 物资分类月汇总to
     * @throws SerException
     */
    void update(MaterialClassifyMonthSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    List<MaterialClassifyMonthSumBO> summary() throws SerException;

}