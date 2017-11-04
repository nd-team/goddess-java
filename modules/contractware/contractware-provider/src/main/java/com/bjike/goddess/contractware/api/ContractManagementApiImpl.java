package com.bjike.goddess.contractware.api;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceBouncesBO;
import com.bjike.goddess.contractware.dto.ContractManagementDTO;
import com.bjike.goddess.contractware.service.ContractManagementSer;
import com.bjike.goddess.contractware.to.ContractManagementTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 合同保管业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 合同保管业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("contractManagementApiImpl")
public class ContractManagementApiImpl implements ContractManagementAPI  {
    @Autowired
    private ContractManagementSer contractManagementSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return contractManagementSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractManagementSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(ContractManagementTO contractManagementTO) throws SerException {
        contractManagementSer.add(contractManagementTO);
    }

    @Override
    public void delete(String id) throws SerException {
        contractManagementSer.delete(id);
    }

    @Override
    public void modify(ContractManagementTO contractManagementTO) throws SerException {
        contractManagementSer.modify(contractManagementTO);
    }

    @Override
    public List<ContractManagementBO> pageList(ContractManagementDTO contractManagementDTO) throws SerException{
        return contractManagementSer.pageList(contractManagementDTO);
    }

    @Override
    public Long count(ContractManagementDTO contractManagementDTO) throws SerException {
        return contractManagementSer.count(contractManagementDTO);
    }

    @Override
    public ContractManagementBO findOne(String id) throws SerException {
        return contractManagementSer.findOne(id);
    }

    @Override
    public void freeze(String id) throws SerException {
        contractManagementSer.freeze(id);
    }

    @Override
    public void breakFreeze(String id) throws SerException {
        contractManagementSer.breakFreeze(id);
    }

    @Override
    public List<DispatchSheetBO> findInformation() throws SerException {
        return contractManagementSer.findInformation();
    }

    @Override
    public void updateContract(String id) throws SerException {
        contractManagementSer.updateContract(id);
    }

    @Override
    public InvoiceBouncesBO findByNumber(String innerProjectNumber) throws SerException {
        return contractManagementSer.findByNumber(innerProjectNumber);
    }
}