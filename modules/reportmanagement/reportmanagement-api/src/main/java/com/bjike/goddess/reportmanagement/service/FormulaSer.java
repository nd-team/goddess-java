package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.entity.Formula;
import com.bjike.goddess.reportmanagement.to.FormulaTO;

import java.util.List;

/**
 * 对应的公式业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FormulaSer extends Ser<Formula, FormulaDTO> {
    /**
     * 根据对应科目id查找公式详细
     *
     * @param foreignId
     * @param dto
     * @return
     * @throws SerException
     */
    List<FormulaBO> findByFid(String foreignId, FormulaDTO dto) throws SerException;

    /**
     * 利润分析
     *
     * @param foreignId
     * @param time
     * @param projectNames
     * @return
     * @throws SerException
     */
    List<FormulaBO> profitAnalyze(String foreignId, String time, String[] projectNames) throws SerException;

    /**
     * 加公式科目
     *
     * @param to
     * @return
     * @throws SerException
     */
    FormulaBO add(FormulaTO to) throws SerException;

    /**
     * 减公式科目
     *
     * @param to
     * @return
     * @throws SerException
     */
    FormulaBO remove(FormulaTO to) throws SerException;

    /**
     * 删除科目
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 添加公式
     *
     * @param to
     * @return
     * @throws SerException
     */
    FormulaBO save(FormulaTO to) throws SerException;
}