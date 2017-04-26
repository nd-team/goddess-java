package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractNatureBO;
import com.bjike.goddess.employeecontract.dto.ContractNatureDTO;
import com.bjike.goddess.employeecontract.service.ContractNatureSer;
import com.bjike.goddess.employeecontract.to.ContractNatureTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同性质业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractNatureApiImpl")
public class ContractNatureApiImpl implements ContractNatureAPI {

    @Autowired
    private ContractNatureSer contractNatureSer;

    @Override
    public ContractNatureBO save(ContractNatureTO to) throws SerException {
        return contractNatureSer.save(to);
    }

    @Override
    public ContractNatureBO update(ContractNatureTO to) throws SerException {
        return contractNatureSer.update(to);
    }

    @Override
    public ContractNatureBO delete(String id) throws SerException {
        return contractNatureSer.delete(id);
    }

    @Override
    public ContractNatureBO congeal(String id) throws SerException {
        return contractNatureSer.congeal(id);
    }

    @Override
    public ContractNatureBO thaw(String id) throws SerException {
        return contractNatureSer.thaw(id);
    }

    @Override
    public List<ContractNatureBO> findThaw() throws SerException {
        return contractNatureSer.findThaw();
    }

    @Override
    public List<ContractNatureBO> maps(ContractNatureDTO dto) throws SerException {
        return contractNatureSer.maps(dto);
    }

    @Override
    public ContractNatureBO getById(String id) throws SerException {
        return contractNatureSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        ContractNatureDTO dto = new ContractNatureDTO();
        return contractNatureSer.count(dto);
    }
}