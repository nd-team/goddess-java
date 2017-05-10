package com.bjike.goddess.materialcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialcheck.bo.MaterialAnalyzeBO;
import com.bjike.goddess.materialcheck.dto.MaterialAnalyzeDTO;
import com.bjike.goddess.materialcheck.entity.MaterialAnalyze;
import com.bjike.goddess.materialcheck.to.MaterialAnalyzeTO;

import java.util.List;

/**
 * 物资分析业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialAnalyzeSer extends Ser<MaterialAnalyze, MaterialAnalyzeDTO> {

    /**
     * 计算总条数
     *
     * @param dto 物资分析dto
     * @throws SerException
     */
    Long count(MaterialAnalyzeDTO dto) throws SerException;

    /**
     * 分页查询物资分析
     *
     * @param dto 物资分析dto
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    List<MaterialAnalyzeBO> list(MaterialAnalyzeDTO dto) throws SerException;

    /**
     * 保存物资分析
     *
     * @param to 物资分析to
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    MaterialAnalyzeBO save(MaterialAnalyzeTO to) throws SerException;

    /**
     * 根据id删除物资分析
     *
     * @param id 物资分析唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新物资分析
     *
     * @param to 物资分析to
     * @throws SerException
     */
    void update(MaterialAnalyzeTO to) throws SerException;

}