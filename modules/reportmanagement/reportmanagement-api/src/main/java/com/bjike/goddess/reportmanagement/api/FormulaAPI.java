package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
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
public interface FormulaAPI {
    /**
     * 根据对应科目id查找公式详细
     *
     * @param foreignId
     * @param startTime
     * @param endTime
     * @param projectGroup
     * @return
     * @throws SerException
     */
    List<FormulaBO> findByFid(String foreignId, String startTime, String endTime, String projectGroup) throws SerException;

    /**
     * 利润分析
     *
     * @param foreignId
     * @param time
     * @param projectGroup
     * @return
     * @throws SerException
     */
    List<FormulaBO> profitAnalyze(String foreignId, String time, String projectGroup) throws SerException;

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