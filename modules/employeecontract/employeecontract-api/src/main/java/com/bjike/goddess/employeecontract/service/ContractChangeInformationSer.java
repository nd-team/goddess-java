package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.employeecontract.bo.ContractChangeInformationBO;
import com.bjike.goddess.employeecontract.entity.ContractChangeInformation;
import com.bjike.goddess.employeecontract.dto.ContractChangeInformationDTO;
import com.bjike.goddess.employeecontract.to.ChangeEnsuerTO;
import com.bjike.goddess.employeecontract.to.ContractChangeInformationTO;
import com.bjike.goddess.employeecontract.to.ExportContractChangeInformationTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;

import java.util.List;

/**
* 合同变更信息业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-09 05:18 ]
* @Description:	[ 合同变更信息业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractChangeInformationSer extends Ser<ContractChangeInformation, ContractChangeInformationDTO> {
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
     * 添加
     */
    void add(ContractChangeInformationTO contractChangeInformationTO) throws SerException;

    /**
     * 修改
     */
    void modify(ContractChangeInformationTO contractChangeInformationTO) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 列表
     */
    List<ContractChangeInformationBO> pageList(ContractChangeInformationDTO contractChangeInformationDTO) throws SerException;

    /**
     * 列表总条数
     */
    Long count(ContractChangeInformationDTO contractChangeInformationDTO) throws SerException;

    /**
     * 根据id查询单条数据
     */
    ContractChangeInformationBO findOne(String id) throws SerException;

    /**
     * 导入
     *
     * @param toList
     * @throws SerException
     */
    default void leadExcel(List<ContractChangeInformationTO> toList) throws SerException {
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
    byte[] exportExcel(ExportContractChangeInformationTO to) throws SerException;

    /**
     * 导出Excel模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 变更确认
     */
    void changeEnsure(ChangeEnsuerTO changeEnsuerTO) throws SerException;
 }