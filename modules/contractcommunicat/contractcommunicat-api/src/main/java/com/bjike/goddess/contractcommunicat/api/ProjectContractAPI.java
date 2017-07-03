package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
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
     *
     * @param id 商务承包洽谈ID
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 商务承包洽谈查询对象
     * @return 商务承包洽谈结果集
     */
    List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException;

    /**
     * 商务承包洽谈汇总
     *
     * @param to 商务承包洽谈查询对象
     * @return 商务承包洽谈结果集
     */
    List<ProjectContractCollectBO> collect(CollectConditionTO to) throws SerException;

    /**
     * 定时发送邮件提示商务承包洽谈
     *
     * @param cycle
     * @throws SerException
     */
    void setCollectSend(QuartzCycleType cycle) throws SerException;

    /**
     * 根据id查询记录
     *
     * @param id 项目承包id
     * @return 项目承包记录
     */
    ProjectContractBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 项目承包记录
     */
    Long count(ProjectContractDTO dto) throws SerException;

    /**
     * 导入Excel
     *
     * @param toList
     * @throws SerException
     */
    void leadExcel(List<ProjectContractTO> toList) throws SerException;

    /**
     * 导出Excel
     *
     * @param contractInProject 内部项目名称
     * @param startDate         开始时间
     * @param endDate           结束时间
     * @throws SerException
     */
    byte[] exportExcel(String contractInProject, String startDate, String endDate) throws SerException;

    /**
     * 内部项目名称列表
     *
     * @return 内部项目名称结果集
     */
    List<ProjectContractBO> projects() throws SerException;

    List<SonPermissionObject> sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO to) throws SerException;

    byte[] exportExcelModule() throws SerException;
}