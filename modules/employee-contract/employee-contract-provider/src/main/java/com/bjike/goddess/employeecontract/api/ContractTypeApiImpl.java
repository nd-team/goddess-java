package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractTypeBO;
import com.bjike.goddess.employeecontract.dto.ContractTypeDTO;
import com.bjike.goddess.employeecontract.service.ContractTypeSer;
import com.bjike.goddess.employeecontract.to.ContractTypeTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:55 ]
 * @Description: [ 合同类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractTypeApiImpl")
public class ContractTypeApiImpl implements ContractTypeAPI {

    @Autowired
    private ContractTypeSer contractTypeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return contractTypeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractTypeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ContractTypeBO save(ContractTypeTO to) throws SerException {
        return contractTypeSer.save(to);
    }

    @Override
    public ContractTypeBO update(ContractTypeTO to) throws SerException {
        return contractTypeSer.update(to);
    }

    @Override
    public ContractTypeBO delete(String id) throws SerException {
        return contractTypeSer.delete(id);
    }

    @Override
    public ContractTypeBO congeal(String id) throws SerException {
        return contractTypeSer.congeal(id);
    }

    @Override
    public ContractTypeBO thaw(String id) throws SerException {
        return contractTypeSer.thaw(id);
    }

    @Override
    public List<ContractTypeBO> findThaw() throws SerException {
        return contractTypeSer.findThaw();
    }

    @Override
    public List<ContractTypeBO> maps(ContractTypeDTO dto) throws SerException {
        return contractTypeSer.maps(dto);
    }

    @Override
    public ContractTypeBO getById(String id) throws SerException {
        return contractTypeSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        ContractTypeDTO dto = new ContractTypeDTO();
        return contractTypeSer.count(dto);
    }
}