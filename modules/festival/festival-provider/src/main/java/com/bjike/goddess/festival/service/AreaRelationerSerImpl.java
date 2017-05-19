package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.AreaRelationerBO;
import com.bjike.goddess.festival.dto.AreaRelationerDTO;
import com.bjike.goddess.festival.entity.AreaRelationer;
import com.bjike.goddess.festival.entity.HolidayProgramme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各地区紧急联系人业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:13 ]
 * @Description: [ 各地区紧急联系人业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class AreaRelationerSerImpl extends ServiceImpl<AreaRelationer, AreaRelationerDTO> implements AreaRelationerSer {


    @Override
    public Long countAreaRelationer(AreaRelationerDTO areaRelationerDTO) throws SerException {
        if(StringUtils.isBlank( areaRelationerDTO.getHolidayProgrammeId())){
            throw new SerException("节假日方案id不能为空");
        }
        String holidayId = areaRelationerDTO.getHolidayProgrammeId();
        areaRelationerDTO.getConditions().add(Restrict.eq("holidayProgramme.id",holidayId));
        Long count = super.count( areaRelationerDTO );
        return count;
    }

    @Override
    public List<AreaRelationerBO> listAreaRelationer(AreaRelationerDTO areaRelationerDTO) throws SerException {
        if(StringUtils.isBlank( areaRelationerDTO.getHolidayProgrammeId())){
            throw new SerException("节假日方案id不能为空");
        }
        String holidayId = areaRelationerDTO.getHolidayProgrammeId();
        areaRelationerDTO.getConditions().add(Restrict.eq("holidayProgramme.id",holidayId));
        List<AreaRelationer> areaRelationerList = super.findByCis( areaRelationerDTO ,true);
        return BeanTransform.copyProperties(areaRelationerList,AreaRelationerBO.class);
    }

    @Override
    public List<AreaRelationerBO> getAreaRelationer(String holidayProgrammeId) throws SerException {
        AreaRelationerDTO dto = new AreaRelationerDTO();

        dto.getConditions().add(Restrict.eq("holidayProgramme.id",holidayProgrammeId));
        List<AreaRelationer> areaRelationerList = super.findByCis( dto );
        return BeanTransform.copyProperties(areaRelationerList,AreaRelationerBO.class);
    }
}