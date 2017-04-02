package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.ProjectCostBO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.to.ProjectCostTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface ProjectCostAPI {

    /**
     * 添加项目费用
     *
     * @param to 项目费用
     * @return 项目费用
     */
    ProjectCostBO addModel(ProjectCostTO to) throws SerException;

    /**
     * 编辑项目费用
     *
     * @param to 项目费用
     * @return 项目费用
     */
    ProjectCostBO editModel(ProjectCostTO to) throws SerException;

    /**
     * 删除项目费用
     *
     * @param id 项目费用id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 项目费用结果集
     */
    List<ProjectCostBO> pageList(ProjectCostDTO dto) throws SerException;
}