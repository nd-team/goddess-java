package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyYearSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyYearSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyYearSum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyYearSumTO;

import java.util.List;

/**
 * 物资分类年汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:48 ]
 * @Description: [ 物资分类年汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialClassifyYearSumSer extends Ser<MaterialClassifyYearSum, MaterialClassifyYearSumDTO> {

    /**
     * 分页查询物资分类年汇总
     *
     * @param dto 物资分类年汇总dto
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    List<MaterialClassifyYearSumBO> list(MaterialClassifyYearSumDTO dto) throws SerException;

    /**
     * 保存物资分类年汇总
     *
     * @param to 物资分类年汇总to
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    MaterialClassifyYearSumBO save(MaterialClassifyYearSumTO to) throws SerException;

    /**
     * 根据id删除物资分类年汇总
     *
     * @param id 物资分类年汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资分类年汇总
     *
     * @param to 物资分类年汇总to
     * @throws SerException
     */
    void update(MaterialClassifyYearSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    List<MaterialClassifyYearSumBO> summary() throws SerException;

}