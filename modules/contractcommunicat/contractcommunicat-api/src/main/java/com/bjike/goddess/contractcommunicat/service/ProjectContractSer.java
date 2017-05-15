package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目承包洽谈业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.916 ]
 * @Description: [ 项目承包洽谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectContractSer extends Ser<ProjectContract, ProjectContractDTO> {

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈
     * @return 项目承包洽谈
     */
    ProjectContractBO saveProjectContract(ProjectContractTO to) throws SerException;

    /**
     * 编辑项目承包洽谈
     *
     * @param to 项目承包洽谈过
     * @return
     */
    ProjectContractBO editProjectContract(ProjectContractTO to) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException;

    /**
     * 汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    List<ProjectContractCollectBO> collect(CollectConditionTO to) throws SerException;

    /**
     * 设置汇总周期
     *
     * @param cycle
     * @throws SerException
     */
    void setCollectSend(QuartzCycleType cycle) throws SerException;

}