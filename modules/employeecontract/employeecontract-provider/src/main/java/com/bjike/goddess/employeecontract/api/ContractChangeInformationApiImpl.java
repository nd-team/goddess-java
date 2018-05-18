package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractChangeInformationBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeInformationDTO;
import com.bjike.goddess.employeecontract.service.ContractChangeInformationSer;
import com.bjike.goddess.employeecontract.to.ChangeEnsuerTO;
import com.bjike.goddess.employeecontract.to.ContractChangeInformationTO;
import com.bjike.goddess.employeecontract.to.ExportContractChangeInformationTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 合同变更信息业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-09 05:18 ]
* @Description:	[ 合同变更信息业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("contractChangeInformationApiImpl")
public class ContractChangeInformationApiImpl implements ContractChangeInformationAPI  {

    @Autowired
    private ContractChangeInformationSer contractChangeInformationSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return contractChangeInformationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractChangeInformationSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(ContractChangeInformationTO contractChangeInformationTO) throws SerException {
        contractChangeInformationSer.add(contractChangeInformationTO);
    }

    @Override
    public void modify(ContractChangeInformationTO contractChangeInformationTO) throws SerException {
        contractChangeInformationSer.modify(contractChangeInformationTO);
    }

    @Override
    public void delete(String id) throws SerException {
        contractChangeInformationSer.delete(id);
    }

    @Override
    public List<ContractChangeInformationBO> pageList(ContractChangeInformationDTO contractChangeInformationDTO) throws SerException {
        return contractChangeInformationSer.pageList(contractChangeInformationDTO);
    }

    @Override
    public Long count(ContractChangeInformationDTO contractChangeInformationDTO) throws SerException {
        return contractChangeInformationSer.count(contractChangeInformationDTO);
    }

    @Override
    public ContractChangeInformationBO findOne(String id) throws SerException {
        return contractChangeInformationSer.findOne(id);
    }

    @Override
    public void leadExcel(List<ContractChangeInformationTO> toList) throws SerException {
        contractChangeInformationSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportContractChangeInformationTO to) throws SerException {
        return contractChangeInformationSer.exportExcel(to);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return contractChangeInformationSer.templateExport();
    }

    @Override
    public void changeEnsure(ChangeEnsuerTO changeEnsuerTO) throws SerException {
        contractChangeInformationSer.changeEnsure(changeEnsuerTO);
    }
}