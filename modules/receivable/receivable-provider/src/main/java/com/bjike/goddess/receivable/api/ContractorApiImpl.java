package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.service.ContractorSer;
import com.bjike.goddess.receivable.to.ContractorTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 承包商列表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractorApiImpl")
public class ContractorApiImpl implements ContractorAPI {
    @Autowired
    private ContractorSer contractorSer;

    @Override
    public List<ContractorBO> findListContractor(ContractorDTO contractorDTO) throws SerException {
        return contractorSer.findListContractor(contractorDTO);
    }

    @Override
    public ContractorBO insertContractor(ContractorTO contractorTO) throws SerException {
        return contractorSer.insertContractor(contractorTO);
    }

    @Override
    public ContractorBO editContractor(ContractorTO contractorTO) throws SerException {
        return contractorSer.editContractor(contractorTO);
    }

    @Override
    public void removeContractor(String id) throws SerException {
        contractorSer.remove(id);
    }


}