package com.bjike.goddess.analysis.service;

import com.bjike.goddess.analysis.bo.CollectBO;
import com.bjike.goddess.analysis.bo.IncomeCostAnalysisBO;
import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.entity.IncomeCostAnalysis;
import com.bjike.goddess.analysis.excel.SonPermissionObject;
import com.bjike.goddess.analysis.to.CollectTO;
import com.bjike.goddess.analysis.to.GuidePermissionTO;
import com.bjike.goddess.analysis.to.IncomeCostAnalysisTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 收入成本分析业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IncomeCostAnalysisSer extends Ser<IncomeCostAnalysis, IncomeCostAnalysisDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 收入成本分析列表总条数
     */
    default Long countIncomeCostAnalysis(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws SerException {
        return null;
    }

    /**
     * 一个收入成本分析
     *
     * @return class IncomeCostAnalysisBO
     */
    default IncomeCostAnalysisBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 收入成本分析
     *
     * @param dto 收入成本分析dto
     * @return class IncomeCostAnalysisBO
     * @throws SerException
     */
    default List<IncomeCostAnalysisBO> findListIncomeCostAnalysis(IncomeCostAnalysisDTO dto) throws SerException {
        return null;
    }

//    /**
//     * 添加收入成本分析
//     *
//     * @param incomeCostAnalysisTO 收入成本分析数据to
//     * @return class IncomeCostAnalysisBO
//     * @throws SerException
//     */
//    default IncomeCostAnalysisBO insertIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
//        return null;
//    }

    /**
     * 编辑收入成本分析
     *
     * @param incomeCostAnalysisTO 收入成本分析数据to
     * @throws SerException
     */
    default void editIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
    }

    /**
     * 根据id删除收入成本分析
     *
     * @param id
     * @throws SerException
     */
    default void removeIncomeCostAnalysis(String id) throws SerException {

    }

    /**
     * 汇总
     *
     * @param to 汇总条件to
     * @return class CollectBO
     * @throws SerException
     */
    default List<CollectBO> collect(CollectTO to) throws SerException {
        return null;
    }


    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }


    /**
     * 获取部门
     *
     * @return class String
     */
    default List<String> getDepartment() throws SerException {
        return null;
    }

}