package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.ProfitRegulationAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitRegulationAdviceDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitRegulationAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitRegulationAdvice;
import com.bjike.goddess.reportmanagement.to.ProfitRegulationAdviceTO;

import java.util.List;

/**
 * 利润增减率分析管理建议设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 06:53 ]
 * @Description: [ 利润增减率分析管理建议设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProfitRegulationAdviceSer extends Ser<ProfitRegulationAdvice, ProfitRegulationAdviceDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProfitRegulationAdviceBO> list(ProfitRegulationAdviceDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ProfitRegulationAdviceBO save(ProfitRegulationAdviceTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ProfitRegulationAdviceTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    ProfitRegulationAdviceBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ProfitRegulationAdviceDTO dto) throws SerException;
}