package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.NoticeThingBO;
import com.bjike.goddess.festival.dto.NoticeThingDTO;
import com.bjike.goddess.festival.dto.NoticeThingDTO;
import com.bjike.goddess.festival.entity.NoticeThing;
import com.bjike.goddess.festival.entity.HolidayProgramme;
import com.bjike.goddess.festival.entity.NoticeThing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注意事项业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class NoticeThingSerImpl extends ServiceImpl<NoticeThing, NoticeThingDTO> implements NoticeThingSer {


    @Override
    public List<NoticeThingBO> getNoticeThing(String holidayProgrammeId) throws SerException {
        NoticeThingDTO dto = new NoticeThingDTO();

        dto.getConditions().add(Restrict.eq("holidayProgramme.id",holidayProgrammeId));
        List<NoticeThing> noticeThingList = super.findByCis( dto );
        return BeanTransform.copyProperties(noticeThingList,NoticeThingBO.class);
    }
}