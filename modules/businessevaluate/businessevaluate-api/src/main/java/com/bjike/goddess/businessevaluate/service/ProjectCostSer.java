package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.ProjectCostBO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.entity.ProjectCost;
import com.bjike.goddess.businessevaluate.to.ProjectCostTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 项目费用业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 02:17 ]
 * @Description: [ 项目费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectCostSer extends Ser<ProjectCost, ProjectCostDTO> {

    /**
     * 添加项目费用
     *
     * @param to 项目费用
     * @return 项目费用
     */
    ProjectCostBO insertModel(ProjectCostTO to) throws SerException;

    /**
     * 编辑项目费用
     *
     * @param to 项目费用
     * @return 项目费用
     */
    ProjectCostBO updateModel(ProjectCostTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 项目费用结果集
     */
    List<ProjectCostBO> pageList(ProjectCostDTO dto) throws SerException;
}