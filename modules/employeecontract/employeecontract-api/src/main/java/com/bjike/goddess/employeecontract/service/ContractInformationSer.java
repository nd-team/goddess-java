package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.employeecontract.bo.ContractInformationBO;
import com.bjike.goddess.employeecontract.entity.ContractInformation;
import com.bjike.goddess.employeecontract.dto.ContractInformationDTO;
import com.bjike.goddess.employeecontract.excel.SonPermissionObject;
import com.bjike.goddess.employeecontract.to.*;

import java.util.List;

/**
* 员工合同信息业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-08 10:56 ]
* @Description:	[ 员工合同信息业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractInformationSer extends Ser<ContractInformation, ContractInformationDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 增加
     */
    void add(ContractInformationTO contractInformationTO) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 修改
     */
    void modify(ContractInformationTO contractInformationTO) throws SerException;

    /**
     * 列表
     */
    List<ContractInformationBO> pageList(ContractInformationDTO contractInformationDTO) throws SerException;

    /**
     * 根据id查询单条数据
     */
    ContractInformationBO findOne(String id) throws SerException;

    /**
     * 列表总条数
     */
    Long count(ContractInformationDTO contractInformationDTO) throws SerException;

    /**
     * 是否续签确认
     */
    void renewEnsure(RenewEnsureTO renewEnsureTO) throws SerException;

    /**
     * 合同解除
     */
    void relieveContract(RelieveContractTO relieveContractTO) throws SerException;

    /**
     * 导入
     *
     * @param toList
     * @throws SerException
     */
    default void leadExcel(List<ContractInformationTO> toList) throws SerException {
        return;
    }

    ;

    /**
     * 导出
     *
     * @param to
     * @return
     * @throws SerException
     */
    byte[] exportExcel(ExportContractInformationTO to) throws SerException;

    /**
     * 导出Excel模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 根据姓名去转正管理获取转正日期
     */
    String getRegularDate(String userName) throws SerException;

    /**
     * 根据姓名去离职管理获取离职日期
     */
    String getDimissionDate(String userName) throws SerException;
 }