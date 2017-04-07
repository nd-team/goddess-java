package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.ProjectAmountBO;
import com.bjike.goddess.businessevaluate.bo.ProjectAmountInfoBO;
import com.bjike.goddess.businessevaluate.dto.ProjectAmountDTO;
import com.bjike.goddess.businessevaluate.entity.ProjectAmount;
import com.bjike.goddess.businessevaluate.to.ProjectAmountTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 项目金额业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectAmountSer extends Ser<ProjectAmount, ProjectAmountDTO> {
    /**
     * 查询项目金额信息
     *
     * @param id 项目信息id
     * @return 项目金额信息
     */
    ProjectAmountInfoBO findInfoById(String id) throws SerException;

    /**
     * 添加项目金额
     *
     * @param to 项目金额
     * @return
     * @throws SerException
     */
    ProjectAmountBO insertModel(ProjectAmountTO to) throws SerException;

    /**
     * 编辑项目金额
     *
     * @param to 项目金额
     * @return 项目金额
     */
    ProjectAmountBO updateModel(ProjectAmountTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 项目金额结果集
     */
    List<ProjectAmountBO> pageList(ProjectAmountDTO to) throws SerException;

}