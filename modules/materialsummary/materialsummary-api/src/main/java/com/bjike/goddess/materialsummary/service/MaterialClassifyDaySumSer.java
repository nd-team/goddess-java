package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyDaySumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyDaySum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyDaySumTO;

import java.util.List;

/**
 * 物资分类日汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:27 ]
 * @Description: [ 物资分类日汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialClassifyDaySumSer extends Ser<MaterialClassifyDaySum, MaterialClassifyDaySumDTO> {

    /**
     * 分页查询物资分类日汇总
     *
     * @param dto 物资分类日汇总dto
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    List<MaterialClassifyDaySumBO> list(MaterialClassifyDaySumDTO dto) throws SerException;

    /**
     * 保存物资分类日汇总
     *
     * @param to 物资分类日汇总to
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    MaterialClassifyDaySumBO save(MaterialClassifyDaySumTO to) throws SerException;

    /**
     * 根据id删除物资分类日汇总
     *
     * @param id 物资分类日汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资分类日汇总
     *
     * @param to 物资分类日汇总to
     * @throws SerException
     */
    void update(MaterialClassifyDaySumTO to) throws SerException;

    /**
     * 汇总
     *
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    List<MaterialClassifyDaySumBO> summary() throws SerException;

}