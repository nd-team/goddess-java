package com.bjike.goddess.businessevaluate.api.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.ProjectPrincipalEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.ProjectPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.ProjectPrincipalEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 项目负责人评价业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 01:55 ]
 * @Description: [ 项目负责人评价业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectPrincipalEvaluateAPI {

    /**
     * 添加项目负责人评价
     *
     * @param to 项目负责人评价
     * @return 项目负责人评价
     */
    ProjectPrincipalEvaluateBO addModel(ProjectPrincipalEvaluateTO to) throws SerException;

    /**
     * 编辑项目负责人评价
     *
     * @param to 项目负责人评价
     * @return 项目负责人评价
     */
    ProjectPrincipalEvaluateBO editModel(ProjectPrincipalEvaluateTO to) throws SerException;

    /**
     * 删除项目负责人评价
     *
     * @param id 项目负责人评价id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 项目负责人评价结果集
     */
    List<ProjectPrincipalEvaluateBO> pageList(ProjectPrincipalEvaluateDTO dto) throws SerException;
}