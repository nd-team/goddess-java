package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.*;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitFormulaDTO;
import com.bjike.goddess.reportmanagement.entity.Profit;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitFormulaTO;
import com.bjike.goddess.reportmanagement.to.ProfitTO;

import java.util.List;

/**
 * 利润表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProfitSer extends Ser<Profit, ProfitDTO> {
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
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ProfitBO save(ProfitTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitBO> list(ProfitDTO dto) throws SerException;

    /**
     * 水平分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitLevelBO> levelAnalyze(ProfitDTO dto) throws SerException;

    /**
     * 垂直分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitVerticalBO> verticalAnalyze(ProfitDTO dto) throws SerException;

    /**
     * 分析指标
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitAnalyzeIndicatorBO> analyzeIndicator(ProfitDTO dto) throws SerException;

    /**
     * 查看金额明细
     *
     * @param id
     * @param dto
     * @return
     * @throws SerException
     */
    List<DetailBO> findDetails(String id, ProfitDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    ProfitBO findByID(String id) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ProfitTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ProfitDTO dto) throws SerException;

    /**
     * 利润增减率分析添加
     * @param to
     * @throws SerException
     * zhuangkaiqin
     */
    default void addFormula(ProfitFormulaTO to) throws SerException {
        return;
    }

    /**
     * 利润增减率分析编辑公式
     *
     * @param to
     * @throws SerException
     */
    default void editFormula(ProfitFormulaTO to) throws SerException {
        return;
    }
    /**
     * 变动情况列表编辑公式
     *
     * @param to
     * @throws SerException
     */
    default void editChangeAnalysis(ProfitFormulaTO to) throws SerException {
        return;
    }

    /**
     * 利润增减率分析删除
     *
     * @param id
     * @throws SerException
     */
    default void deleteFormula(String id) throws SerException {
        return;
    }

    /**
     * 变动情况列表删除
     *
     * @param id
     * @throws SerException
     */
    default void deleteChangeAnalysis(String id) throws SerException {
        return;
    }

    /**
     * 查看利润增减率分析
     *
     * @param id
     * @return
     * @throws SerException
     */
    default String findFormula(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查看利润增减率分析
     *
     * @return
     * @throws SerException
     */
    default ProfitFormulaBO findFormulaByID(String id) throws SerException {
        return null;
    }

    /**
     * 利润增减率分析列表
     *
     * @param profitFormulaDTO
     * @return
     * @throws SerException
     */
    default List<ProfitFormulaBO> decreaseRatioList(ProfitFormulaDTO profitFormulaDTO) throws SerException {
        return null;
    }

    /**
     * 变动情况分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<String> analysisChanges(ProfitDTO dto) throws SerException {
        return null;
    }

    /**
     * 查询利润增减率分析总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getFormulaTotal(ProfitFormulaDTO dto) throws SerException {
        return null;
    }

    /**
     * 变动情况分析列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ProfitFormulaBO> analysisChangesList(ProfitFormulaDTO dto) throws SerException {
        return null;
    }

    /**
     * 变动情况分析列表添加
     *
     * @param to
     * @throws SerException
     */
    default void analysisChangesAdd(ProfitFormulaTO to) throws SerException {
        return;
    }

    /**
     * 变动情况分析列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long analysisChangesTotal(ProfitFormulaDTO dto) throws SerException {
        return null;
    }
    /**
     * 导出利润表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(ProfitDTO dto) throws SerException {
        return null;
    }


    default void profitTask() throws SerException{}
}