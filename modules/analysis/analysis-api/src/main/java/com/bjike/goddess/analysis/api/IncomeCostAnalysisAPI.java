package com.bjike.goddess.analysis.api;

import com.bjike.goddess.analysis.bo.CollectAreaBO;
import com.bjike.goddess.analysis.bo.CollectDepartmentBO;
import com.bjike.goddess.analysis.bo.CollectMonthBO;
import com.bjike.goddess.analysis.bo.IncomeCostAnalysisBO;
import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.to.IncomeCostAnalysisTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface IncomeCostAnalysisAPI {

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
     * @param incomeCostAnalysisDTO 收入成本分析dto
     * @return class IncomeCostAnalysisBO
     * @throws SerException
     */
    default List<IncomeCostAnalysisBO> findListIncomeCostAnalysis(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws SerException {
        return null;
    }

    /**
     * 添加收入成本分析
     *
     * @param incomeCostAnalysisTO 收入成本分析数据to
     * @return class IncomeCostAnalysisBO
     * @throws SerException
     */
    default IncomeCostAnalysisBO insertIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        return null;
    }

    /**
     * 编辑收入成本分析
     *
     * @param incomeCostAnalysisTO 收入成本分析数据to
     * @return class IncomeCostAnalysisBO
     * @throws SerException
     */
    default IncomeCostAnalysisBO editIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        return null;
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
     * 汇总地区
     *
     * @param areas areas
     * @return class CollectAreaBO
     * @throws SerException
     */
    default List<CollectAreaBO> collectArea(String[] areas) throws SerException {
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
     * 汇总月份
     *
     * @param months months
     * @return class CollectMonthBO
     * @throws SerException
     */
    default List<CollectMonthBO> collectMonth(String[] months) throws SerException {
        return null;
    }

    /**
     * 获取月份
     *
     * @return class String
     */
    default List<String> getMonth() throws SerException {
        return null;
    }

    /**
     * 汇总部门
     *
     * @param departments departments
     * @return class CollectDepartmentBO
     * @throws SerException
     */
    default List<CollectDepartmentBO> collectDepartment(String[] departments) throws SerException {
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