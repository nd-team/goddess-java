package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffing.bo.PlanSonBO;
import com.bjike.goddess.staffing.dto.PlanSonDTO;
import com.bjike.goddess.staffing.entity.PlanSon;
import com.bjike.goddess.staffing.to.PlanSonTO;

import java.util.List;

/**
 * 人数配置-计划子表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:58 ]
 * @Description: [ 人数配置-计划子表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PlanSonSer extends Ser<PlanSon, PlanSonDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PlanSonBO> list(PlanSonDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    PlanSonBO save(PlanSonTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(PlanSonTO to) throws SerException;

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
    PlanSonBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(PlanSonDTO dto) throws SerException;
}