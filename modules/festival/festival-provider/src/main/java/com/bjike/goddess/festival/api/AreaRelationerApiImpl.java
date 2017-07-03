package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.AreaRelationerBO;
import com.bjike.goddess.festival.dto.AreaRelationerDTO;
import com.bjike.goddess.festival.service.AreaRelationerSer;
import com.bjike.goddess.festival.to.AreaRelationerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各地区紧急联系人业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:13 ]
 * @Description: [ 各地区紧急联系人业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaRelationerApiImpl")
public class AreaRelationerApiImpl implements AreaRelationerAPI {

    @Autowired
    private AreaRelationerSer areaRelationerSer;

    @Override
    public Long countAreaRelationer(AreaRelationerDTO areaRelationerDTO) throws SerException {
        return areaRelationerSer.countAreaRelationer(areaRelationerDTO);
    }

    @Override
    public List<AreaRelationerBO> listAreaRelationer(AreaRelationerDTO areaRelationerDTO) throws SerException {
        return areaRelationerSer.listAreaRelationer(areaRelationerDTO);
    }

    @Override
    public AreaRelationerBO addAreaRelationer(AreaRelationerTO areaRelationerTO) throws SerException {
        return areaRelationerSer.addAreaRelationer(areaRelationerTO);
    }

    @Override
    public AreaRelationerBO editAreaRelationer(AreaRelationerTO areaRelationerTO) throws SerException {
        return areaRelationerSer.editAreaRelationer(areaRelationerTO);
    }

    @Override
    public void deleteAreaRelationer(String id) throws SerException {
        areaRelationerSer.deleteAreaRelationer(id);
    }

    @Override
    public List<AreaRelationerBO> getAreaRelationer(String holidayProgrammeId) throws SerException {
        return areaRelationerSer.getAreaRelationer(holidayProgrammeId);
    }
}