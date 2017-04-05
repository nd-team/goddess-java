package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.LabourCostBO;
import com.bjike.goddess.businessevaluate.dto.LabourCostDTO;
import com.bjike.goddess.businessevaluate.to.LabourCostTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 劳动成本业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 09:16 ]
 * @Description: [ 劳动成本业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LabourCostAPI {

    /**
     * 添加劳动成本
     *
     * @param to 劳动成本
     * @return 劳动成本
     */
    LabourCostBO addModel(LabourCostTO to) throws SerException;

    /**
     * 编辑劳动成本
     *
     * @param to 劳动成本
     * @return 劳动成本
     */
    LabourCostBO editModel(LabourCostTO to) throws SerException;

    /**
     * 删除劳动成本
     *
     * @param id 劳动成本id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 劳动成本结果集
     */
    List<LabourCostBO> pageList(LabourCostDTO dto) throws SerException;
}