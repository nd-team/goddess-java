package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyAnalyseBO;
import com.bjike.goddess.attainment.dto.SurveyAnalyseDTO;
import com.bjike.goddess.attainment.entity.SurveyAnalyse;
import com.bjike.goddess.attainment.to.SurveyAnalyseTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研分析业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:50 ]
 * @Description: [ 调研分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyAnalyseSer extends Ser<SurveyAnalyse, SurveyAnalyseDTO> {

    /**
     * 保存
     *
     * @param to 调研分析传输对象
     * @return
     * @throws SerException
     */
    default SurveyAnalyseBO save(SurveyAnalyseTO to) throws SerException {
        return null;
    }

    /**
     * 更新
     *
     * @param to 调研分析传输对象
     * @return
     * @throws SerException
     */
    default SurveyAnalyseBO update(SurveyAnalyseTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研分析数据id
     * @return
     * @throws SerException
     */
    default SurveyAnalyseBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据调研计划查询调研分析
     *
     * @param plan_id 调研计划数据id
     * @return
     * @throws SerException
     */
    default List<SurveyAnalyseBO> findByPlan(String plan_id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 调研分析数据传输对象
     * @return
     * @throws SerException
     */
    default List<SurveyAnalyseBO> maps(SurveyAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取调研分析数据
     *
     * @param id 调研分析数据id
     * @return
     * @throws SerException
     */
    default SurveyAnalyseBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

}