package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractChangeBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeDTO;
import com.bjike.goddess.employeecontract.entity.ContractChange;
import com.bjike.goddess.employeecontract.service.ContractChangeSer;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同变更详细业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractChangeApiImpl")
public class ContractChangeApiImpl implements ContractChangeAPI {

    @Autowired
    private ContractChangeSer contractChangeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return contractChangeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractChangeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ContractChangeBO update(ContractChangeTO to) throws SerException {
        return contractChangeSer.update(to);
    }

    @Override
    public ContractChangeBO delete(String id) throws SerException {
        return contractChangeSer.delete(id);
    }

    @Override
    public List<ContractChangeBO> maps(ContractChangeDTO dto) throws SerException {
        return contractChangeSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return contractChangeSer.getTotal();
    }

    @Override
    public ContractChangeBO getById(String id) throws SerException {
        return contractChangeSer.getById(id);
    }

}