package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractInformationBO;
import com.bjike.goddess.employeecontract.dto.ContractInformationDTO;
import com.bjike.goddess.employeecontract.excel.SonPermissionObject;
import com.bjike.goddess.employeecontract.service.ContractInformationSer;
import com.bjike.goddess.employeecontract.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 员工合同信息业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-08 10:56 ]
* @Description:	[ 员工合同信息业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("contractInformationApiImpl")
public class ContractInformationApiImpl implements ContractInformationAPI  {
    @Autowired
    private ContractInformationSer contractInformationSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return contractInformationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractInformationSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(ContractInformationTO contractInformationTO) throws SerException {
        contractInformationSer.add(contractInformationTO);
    }

    @Override
    public void delete(String id) throws SerException {
        contractInformationSer.delete(id);
    }

    @Override
    public void modify(ContractInformationTO contractInformationTO) throws SerException {
        contractInformationSer.modify(contractInformationTO);
    }

    @Override
    public List<ContractInformationBO> pageList(ContractInformationDTO contractInformationDTO) throws SerException {
        return contractInformationSer.pageList(contractInformationDTO);
    }

    @Override
    public ContractInformationBO findOne(String id) throws SerException {
        return contractInformationSer.findOne(id);
    }

    @Override
    public Long count(ContractInformationDTO contractInformationDTO) throws SerException {
        return contractInformationSer.count(contractInformationDTO);
    }

    @Override
    public void renewEnsure(RenewEnsureTO renewEnsureTO) throws SerException {
        contractInformationSer.renewEnsure(renewEnsureTO);
    }

    @Override
    public void relieveContract(RelieveContractTO relieveContractTO) throws SerException {
        contractInformationSer.relieveContract(relieveContractTO);
    }

    @Override
    public void leadExcel(List<ContractInformationTO> toList) throws SerException {
        contractInformationSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportContractInformationTO to) throws SerException {
        return contractInformationSer.exportExcel(to);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return contractInformationSer.templateExport();
    }

    @Override
    public String getRegularDate(String userName) throws SerException {
        return contractInformationSer.getRegularDate(userName);
    }

    @Override
    public String getDimissionDate(String userName) throws SerException {
        return contractInformationSer.getDimissionDate(userName);
    }
}