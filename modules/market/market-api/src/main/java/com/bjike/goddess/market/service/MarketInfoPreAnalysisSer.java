package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.market.bo.MarketInfoPreAnalysisBO;
import com.bjike.goddess.market.dto.MarketInfoPreAnalysisDTO;
import com.bjike.goddess.market.entity.MarketInfoPreAnalysis;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoPreAnalysisTO;

import java.util.List;

/**
 * 市场信息初步分析业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 01:36 ]
 * @Description: [ 市场信息初步分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketInfoPreAnalysisSer extends Ser<MarketInfoPreAnalysis, MarketInfoPreAnalysisDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 市场信息初步分析列表总条数
     */
    default Long countAnalysis(MarketInfoPreAnalysisDTO marketInfoPreAnalysisDTO) throws SerException {
        return null;
    }

    /**
     * 一个市场信息初步分析
     *
     * @return class MarketInfoPreAnalysisBO
     */
    default MarketInfoPreAnalysisBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 市场信息初步分析列表
     *
     * @param marketInfoPreAnalysisDTO 市场信息初步分析列表dto
     * @return class MarketInfoPreAnalysisBO
     * @throws SerException
     */
    default List<MarketInfoPreAnalysisBO> findListAnalysis(MarketInfoPreAnalysisDTO marketInfoPreAnalysisDTO) throws SerException {
        return null;
    }

    /**
     * 市场信息初步分析
     *
     * @param marketInfoPreAnalysisTO 市场信息初步分析数据to
     * @throws SerException
     */
    default void analysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        return;
    }

    /**
     * 预算模块分析
     *
     * @param marketInfoPreAnalysisTO 市场信息初步分析数据to
     * @throws SerException
     */
    default void budgetAnalysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        return;
    }

    /**
     * 规划模块分析
     *
     * @param marketInfoPreAnalysisTO 市场信息初步分析数据to
     * @throws SerException
     */
    default void planAnalysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        return;
    }

    /**
     * 根据id删除市场信息初步分析
     *
     * @param id
     * @throws SerException
     */
    default void removeAnalysis(String id) throws SerException {

    }

}