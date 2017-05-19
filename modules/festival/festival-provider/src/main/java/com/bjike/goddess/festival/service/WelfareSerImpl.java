package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.GiftStandardBO;
import com.bjike.goddess.festival.bo.WelfareBO;
import com.bjike.goddess.festival.dto.WelfareDTO;
import com.bjike.goddess.festival.dto.WelfareDTO;
import com.bjike.goddess.festival.entity.Welfare;
import com.bjike.goddess.festival.entity.HolidayProgramme;
import com.bjike.goddess.festival.entity.Welfare;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 节假日礼品福利业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:17 ]
 * @Description: [ 节假日礼品福利业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class WelfareSerImpl extends ServiceImpl<Welfare, WelfareDTO> implements WelfareSer {


    @Override
    public Long countWelfare(WelfareDTO welfareDTO) throws SerException {
        if(StringUtils.isBlank( welfareDTO.getHolidayProgrammeId())){
            throw new SerException("节假日方案id不能为空");
        }
        String holidayId = welfareDTO.getHolidayProgrammeId();
        welfareDTO.getConditions().add(Restrict.eq("holidayProgramme.id",holidayId));
        Long count = super.count( welfareDTO );
        return count;
    }

    @Override
    public List<WelfareBO> listWelfare(WelfareDTO welfareDTO) throws SerException {
        if(StringUtils.isBlank( welfareDTO.getHolidayProgrammeId())){
            throw new SerException("节假日方案id不能为空");
        }
        String holidayId = welfareDTO.getHolidayProgrammeId();
        welfareDTO.getConditions().add(Restrict.eq("holidayProgramme.id",holidayId));
        List<Welfare> welfareList = super.findByCis( welfareDTO ,true);
        return BeanTransform.copyProperties(welfareList,WelfareBO.class);
    }
    @Override
    public List<WelfareBO> getWelfare(String holidayProgrammeId) throws SerException {
        WelfareDTO dto = new WelfareDTO();

        dto.getConditions().add(Restrict.eq("holidayProgramme.id",holidayProgrammeId));
        List<Welfare> welfareList = super.findByCis( dto );
        return BeanTransform.copyProperties(welfareList,WelfareBO.class);
    }


}