package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;

import java.util.List;

/**
 * 项目承包洽谈业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.915 ]
 * @Description: [ 项目承包洽谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectContractAPI {

    /**
     * 新增商务承包洽谈
     *
     * @param to 商务承包洽谈信息
     * @return 商务承包洽谈信息
     */
    ProjectContractBO saveProjectContract(ProjectContractTO to) throws SerException;

    /**
     * 编辑商务承包洽谈
     *
     * @param to 商务承包洽谈信息
     * @return 商务承包洽谈信息
     */
    ProjectContractBO editProjectContract(ProjectContractTO to) throws SerException;

    /**
     * 删除
     * @param id 商务承包洽谈ID
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     * @param dto 商务承包洽谈查询对象
     * @return 商务承包洽谈结果集
     */
    List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException;

    /**
     * 商务承包洽谈汇总
     * @param dto 商务承包洽谈查询对象
     * @return 商务承包洽谈结果集
     */
    List<ProjectContractBO> collect(ProjectContractDTO dto) throws SerException;

    /**
     * 定时发送邮件提示商务承包洽谈
     * @param cycle
     * @throws SerException
     */
    void setCollectSend(QuartzCycleType cycle)  throws SerException ;
}