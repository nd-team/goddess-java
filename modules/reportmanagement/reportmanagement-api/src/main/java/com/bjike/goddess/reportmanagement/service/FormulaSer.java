package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.entity.Formula;

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
     * @param foreignId 对应科目id
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return
     * @throws SerException
     */
    List<FormulaBO> findByFid(String foreignId, String startTime, String endTime) throws SerException;
}