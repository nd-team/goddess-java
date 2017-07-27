package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;
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
 * @Date: [ 2017-03-18T09:24:12.803 ]
 * @Description: [ 项目外包洽谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectOutsourcingSer extends Ser<ProjectOutsourcing, ProjectOutsourcingDTO> {

    /**
     * 新增项目外包洽谈
     *
     * @param to 项目外包洽谈
     * @return 项目外包洽谈
     */
    ProjectOutsourcingBO saveProjectOutsourcing(ProjectOutsourcingTO to) throws SerException;

    /**
     * 编辑项目外包洽谈
     *
     * @param to 项目外包洽谈
     * @return 项目外包洽谈
     */
    ProjectOutsourcingBO editProjectOutsourcing(ProjectOutsourcingTO to) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 项目外包洽谈结果集
     */
    List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException;

    /**
     * 汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProjectOutsourcingCollectBO> collect(CollectConditionTO dto) throws SerException;

    /**
     * 导入Excel
     *
     * @param toList
     */
    void leadExcel(List<ProjectOutsourcingTO> toList) throws SerException;

    /**
     * 导出excel
     *
     * @param contractInProject 导出条件
     * @param startDate         开始时间
     * @param endDate           结束时间
     */
    byte[] exportExcel(String contractInProject, String startDate, String endDate) throws SerException;

    List<ProjectOutsourcingBO> prjects() throws SerException;

    List<SonPermissionObject> sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO to) throws SerException;

    byte[] exportExcelModule() throws SerException;

}