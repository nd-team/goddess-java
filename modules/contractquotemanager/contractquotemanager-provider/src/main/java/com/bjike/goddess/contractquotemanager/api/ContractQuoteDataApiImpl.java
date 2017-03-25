package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.service.ContractQuoteDataSer;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同单价资料信息业务接口实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.321 ]
 * @Description: [ 合同单价资料信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractQuoteDataApiImpl")
public class ContractQuoteDataApiImpl implements ContractQuoteDataAPI {
    @Autowired
    private ContractQuoteDataSer contractQuoteDataSer;

    @Override
    public ContractQuoteDataBO save(ContractQuoteDataTO contractQuoteDataTO) throws SerException {
        return contractQuoteDataSer.save(contractQuoteDataTO);
    }

    @Override
    public List<ContractQuoteDataBO> list(ContractQuoteDataDTO contractQuoteDataDTO) throws SerException {
        return contractQuoteDataSer.list(contractQuoteDataDTO);
    }

    @Override
    public void update(ContractQuoteDataTO contractQuoteDataTO) throws SerException {
        contractQuoteDataSer.update(contractQuoteDataTO);
    }

    @Override
    public void remove(String id) throws SerException {
        contractQuoteDataSer.remove(id);
    }

    @Override
    public void congealStatus(String id) throws SerException {
        contractQuoteDataSer.congealStatus(id);
    }

    @Override
    public void thawStatus(String id) throws SerException {
        contractQuoteDataSer.thawStatus(id);
    }

    @Override
    public List<ContractQuoteDataBO> collect(ContractQuoteDataDTO dto) throws SerException {
        return contractQuoteDataSer.collect(dto);
    }

    @Override
    public List<ContractQuoteDataBO> searchs(ContractQuoteDataBO bo) throws SerException {
        return contractQuoteDataSer.searchs(bo);
    }


}