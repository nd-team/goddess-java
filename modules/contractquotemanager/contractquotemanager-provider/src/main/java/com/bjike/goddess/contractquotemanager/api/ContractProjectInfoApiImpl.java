package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractProjectInfo;
import com.bjike.goddess.contractquotemanager.service.ContractProjectInfoSer;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同项目基本信息(临时表存放数据商务模块获取数据)业务接口实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: []
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractProjectInfoApiImpl")
public class ContractProjectInfoApiImpl implements ContractProjectInfoAPI {
    @Autowired
    private ContractProjectInfoSer contractProjectInfoSer;

    @Override
    public ContractProjectInfoBO save(ContractProjectInfoTO contractProjectInfoTO) throws SerException {
        return contractProjectInfoSer.save(contractProjectInfoTO);
    }

    @Override
    public List<ContractProjectInfoBO> list(ContractProjectInfoDTO contractProjectInfoDTO) throws SerException {
        return contractProjectInfoSer.list(contractProjectInfoDTO);
    }

}