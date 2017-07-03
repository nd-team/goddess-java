package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;

import java.util.List;

/**
 * 项目外包洽谈业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.802 ]
 * @Description: [ 项目外包洽谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectOutsourcingAPI {

    /**
     * 新增项目外包洽谈
     *
     * @param to 项目外包洽谈
     * @return 项目外包洽谈
     */
    ProjectOutsourcingBO saveProjectOutsource(ProjectOutsourcingTO to) throws SerException;

    /**
     * 编辑项目外包洽谈
     *
     * @param to 项目外包洽谈
     * @return 项目外包洽谈
     */
    ProjectOutsourcingBO editProjectOutsource(ProjectOutsourcingTO to) throws SerException;

    /**
     * 删除 项目外包洽谈信息
     *
     * @param id 项目外包洽谈ID
     */
    void delete(String id) throws SerException;

    /**
     * 项目外包洽谈分页查询
     *
     * @param dto 分页条件
     * @return 项目外包洽谈结果集
     */
    List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException;

    /**
     * 项目外包洽谈汇总
     *
     * @param to
     * @return 汇总条件
     */
    List<ProjectOutsourcingCollectBO> collect(CollectConditionTO to) throws SerException;

    /**
     * 定时发送汇总
     *
     * @param cycleType 定时器条件
     */
    void setCollectSend(QuartzCycleType cycleType) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(ProjectOutsourcingDTO dto) throws SerException;

    /**
     * 根据id查询项目外包洽谈
     *
     * @param id 项目外包洽谈
     * @return 项目外包洽谈
     */
    ProjectOutsourcingBO findById(String id) throws SerException;

    /**
     * 导入excel
     *
     * @param toList
     */
    void leadExcel(List<ProjectOutsourcingTO> toList) throws SerException;

    /**
     * 导出Excel
     *
     * @param contractInProject 内部项目名称
     * @param startDate         开始日期
     * @param endDate           结束日期
     */
    byte[] exportExcel(String contractInProject, String startDate, String endDate) throws SerException;

    List<ProjectOutsourcingBO> projects() throws SerException;

    List<SonPermissionObject> sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO to) throws SerException;

    byte[] exportExcelModule() throws SerException;
}