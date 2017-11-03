package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.AreaWeightSetBO;
import com.bjike.goddess.customer.dto.AreaWeightSetDTO;
import com.bjike.goddess.customer.service.AreaWeightSetSer;
import com.bjike.goddess.customer.to.AreaWeightSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区权重设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaWeightSetApiImpl")
public class AreaWeightSetApiImpl implements AreaWeightSetAPI {
    @Autowired
    private AreaWeightSetSer areaWeightSetSer;
    @Override
    public Long countAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {
        return areaWeightSetSer.countAreaWeight(areaWeightSetDTO);
    }

    @Override
    public AreaWeightSetBO getOneAreaWeight(String id) throws SerException {
        return areaWeightSetSer.getOneAreaWeight(id);
    }

    @Override
    public List<AreaWeightSetBO> listAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {
        return areaWeightSetSer.listAreaWeight(areaWeightSetDTO);
    }

    @Override
    public AreaWeightSetBO addAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        return areaWeightSetSer.addAreaWeight(areaWeightSetTO);
    }

    @Override
    public AreaWeightSetBO editAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        return areaWeightSetSer.editAreaWeight(areaWeightSetTO);
    }

    @Override
    public void deleteAreaWeight(String id) throws SerException {
        areaWeightSetSer.deleteAreaWeight(id);
    }
}