package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.LabourCostBO;
import com.bjike.goddess.businessevaluate.dto.LabourCostDTO;
import com.bjike.goddess.businessevaluate.entity.LabourCost;
import com.bjike.goddess.businessevaluate.to.LabourCostTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 项目基本信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 09:16 ]
 * @Description: [ 项目基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LabourCostSer extends Ser<LabourCost, LabourCostDTO> {

    /**
     * 添加劳动成本
     *
     * @param to 劳动成本
     * @return 劳动成本
     */
    LabourCostBO insertModel(LabourCostTO to) throws SerException;

    /**
     * 编辑劳动成本
     *
     * @param to 劳动成本
     * @return 劳动成本
     */
    LabourCostBO updateModel(LabourCostTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 劳动成本结果集
     */
    List<LabourCostBO> pageList(LabourCostDTO dto) throws SerException;
}