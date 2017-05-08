package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.HousingContractBO;
import com.bjike.goddess.contractware.dto.HousingContractDTO;
import com.bjike.goddess.contractware.entity.HousingContract;
import com.bjike.goddess.contractware.service.HousingContractSer;
import com.bjike.goddess.contractware.to.HousingContractTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 房屋合同业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:42 ]
 * @Description: [ 房屋合同业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("housingContractApiImpl")
public class HousingContractApiImpl implements HousingContractAPI {

    @Autowired
    private HousingContractSer housingContractSer;
    @Override
    public Long countHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        return housingContractSer.countHousingContract(housingContractDTO);
    }

    @Override
    public HousingContractBO getOne(String id) throws SerException {
        return housingContractSer.getOne(id);
    }

    @Override
    public List<HousingContractBO> findListHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        return housingContractSer.findListHousingContract(housingContractDTO);
    }

    @Override
    public HousingContractBO insertHousingContract(HousingContractTO housingContractTO) throws SerException {
        return housingContractSer.insertHousingContract(housingContractTO);
    }

    @Override
    public HousingContractBO editHousingContract(HousingContractTO housingContractTO) throws SerException {
        return housingContractSer.editHousingContract(housingContractTO);
    }

    @Override
    public void removeHousingContract(String id) throws SerException {
        housingContractSer.removeHousingContract(id);
    }
}