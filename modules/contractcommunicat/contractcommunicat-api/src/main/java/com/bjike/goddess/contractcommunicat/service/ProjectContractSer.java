package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
import com.bjike.goddess.market.bo.MarketInfoRecordBO;

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

    /**
     * 获取所有合同外部项目名称和合同项目外部编号
     * @throws SerException
     */
    List<BaseInfoManageBO> listBaseInfoManage() throws SerException;


    /**
     * 查询内部项目名称
     * @throws SerException
     */
    List<MarketInfoRecordBO> findProject() throws SerException;

    /**
     * 查询所有的责任人
     */
    default List<String> getCommunicateUser() throws SerException {
        return null;
    }
}