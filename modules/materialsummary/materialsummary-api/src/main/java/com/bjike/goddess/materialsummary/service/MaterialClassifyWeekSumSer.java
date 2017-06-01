package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyWeekSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyWeekSum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyWeekSumTO;

import java.util.List;

/**
 * 物资分类周汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:45 ]
 * @Description: [ 物资分类周汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialClassifyWeekSumSer extends Ser<MaterialClassifyWeekSum, MaterialClassifyWeekSumDTO> {

    /**
     * 分页查询物资分类周汇总
     *
     * @param dto 物资分类周汇总dto
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    List<MaterialClassifyWeekSumBO> list(MaterialClassifyWeekSumDTO dto) throws SerException;

    /**
     * 保存物资分类周汇总
     *
     * @param to 物资分类周汇总to
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    MaterialClassifyWeekSumBO save(MaterialClassifyWeekSumTO to) throws SerException;

    /**
     * 根据id删除物资分类周汇总
     *
     * @param id 物资分类周汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资分类周汇总
     *
     * @param to 物资分类周汇总to
     * @throws SerException
     */
    void update(MaterialClassifyWeekSumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    List<MaterialClassifyWeekSumBO> summary() throws SerException;

}