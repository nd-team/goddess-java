package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.CompanyFestivalTimeBO;
import com.bjike.goddess.festival.bo.HolidayProgrammeBO;
import com.bjike.goddess.festival.dto.*;
import com.bjike.goddess.festival.dto.HolidayProgrammeDTO;
import com.bjike.goddess.festival.entity.*;
import com.bjike.goddess.festival.entity.HolidayProgramme;
import com.bjike.goddess.festival.to.HolidayProgrammeTO;
import com.bjike.goddess.festival.to.HolidayWorkPlanTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 法定节假日放假方案业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:03 ]
 * @Description: [ 法定节假日放假方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class HolidayProgrammeSerImpl extends ServiceImpl<HolidayProgramme, HolidayProgrammeDTO> implements HolidayProgrammeSer {

    @Autowired
    private HolidayWorkPlanSer holidayWorkPlanSer;
    @Autowired
    private AreaRelationerSer areaRelationerSer;
    @Autowired
    private WelfareSer welfareSer;
    @Autowired
    private NoticeThingSer noticeThingSer;

    @Override
    public Long countHolidayProgramme(HolidayProgrammeDTO holidayProgrammeDTO) throws SerException {
        return super.count( holidayProgrammeDTO );
    }

    @Override
    public List<HolidayProgrammeBO> listHolidayProgramme(HolidayProgrammeDTO holidayProgrammeDTO) throws SerException {

        holidayProgrammeDTO.getSorts().add("createTime=desc");
        List<HolidayProgramme> list = super.findByCis(holidayProgrammeDTO);

        return BeanTransform.copyProperties(list, HolidayProgrammeBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HolidayProgrammeBO addHolidayProgramme(HolidayProgrammeTO holidayProgrammeTO) throws SerException {
        HolidayProgramme holidayProgramme = BeanTransform.copyProperties(holidayProgrammeTO,HolidayProgramme.class,true);
        holidayProgramme.setCreateTime(LocalDateTime.now());
        if( holidayProgrammeTO.getHolidayWorkPlanTOList() != null && holidayProgrammeTO.getHolidayWorkPlanTOList().size()>0){
            holidayProgramme.setOverTimeCondition("是");
        }else{
            holidayProgramme.setOverTimeCondition("否");
        }
        super.save( holidayProgramme );

        //添加公司节假日工作安排模板
        if( holidayProgrammeTO.getHolidayWorkPlanTOList()!= null && holidayProgrammeTO.getHolidayWorkPlanTOList().size()>0 ){
            List<HolidayWorkPlan> hwork = BeanTransform.copyProperties( holidayProgrammeTO.getHolidayWorkPlanTOList(),HolidayWorkPlan.class,true);
            hwork.stream().forEach(str->{
                str.setHolidayProgramme( holidayProgramme );
            });
            holidayWorkPlanSer.save( hwork );
        }

        //添加各地区紧急联系人
        if( holidayProgrammeTO.getAreaRelationerTOList()!= null && holidayProgrammeTO.getAreaRelationerTOList().size()>0 ) {
            List<AreaRelationer> areaRelationerList = BeanTransform.copyProperties(holidayProgrammeTO.getAreaRelationerTOList(), AreaRelationer.class, true);
            areaRelationerList.stream().forEach(str -> {
                str.setHolidayProgramme(holidayProgramme);
            });
            areaRelationerSer.save(areaRelationerList);
        }
        //添加节假日福利
        if( holidayProgrammeTO.getWelfareTOList()!= null && holidayProgrammeTO.getWelfareTOList().size()>0 ) {
            List<Welfare> welfareList = BeanTransform.copyProperties(holidayProgrammeTO.getWelfareTOList(), Welfare.class, true);
            welfareList.stream().forEach(str -> {
                str.setHolidayProgramme(holidayProgramme);
            });
            welfareSer.save(welfareList);
        }
        //注意事项
        if( holidayProgrammeTO.getNoticeThingTOList()!= null && holidayProgrammeTO.getNoticeThingTOList().size()>0 ) {
            List<NoticeThing> noticeThingList = BeanTransform.copyProperties(holidayProgrammeTO.getNoticeThingTOList(), NoticeThing.class, true);
            noticeThingList.stream().forEach(str -> {
                str.setHolidayProgramme(holidayProgramme);
            });
            noticeThingSer.save( noticeThingList );
        }

        return BeanTransform.copyProperties(holidayProgramme, HolidayProgrammeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HolidayProgrammeBO editHolidayProgramme(HolidayProgrammeTO holidayProgrammeTO) throws SerException {
        HolidayProgramme holidayProgramme = BeanTransform.copyProperties(holidayProgrammeTO,HolidayProgramme.class,true);
        HolidayProgramme temp = super.findById( holidayProgrammeTO.getId() );

        BeanUtils.copyProperties(holidayProgramme,temp,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        if( holidayProgrammeTO.getHolidayWorkPlanTOList() != null && holidayProgrammeTO.getHolidayWorkPlanTOList().size()>0){
            temp.setOverTimeCondition("是");
        }else{
            temp.setOverTimeCondition("否");
        }
        super.update( temp );

        //修改公司节假日工作安排模板
        if( holidayProgrammeTO.getHolidayWorkPlanTOList()!= null && holidayProgrammeTO.getHolidayWorkPlanTOList().size()>0 ) {
            List<HolidayWorkPlan> tempHwork = new ArrayList<>();
            List<HolidayWorkPlan> hwork = BeanTransform.copyProperties(holidayProgrammeTO.getHolidayWorkPlanTOList(), HolidayWorkPlan.class, true);
            hwork.stream().forEach(str -> {
                try {
                    HolidayWorkPlan holidayWorkPlan = holidayWorkPlanSer.findById(str.getId());
                    BeanUtils.copyProperties(str, holidayWorkPlan, "id", "createTime", "holidayProgramme");
                    tempHwork.add(holidayWorkPlan);
                } catch (SerException e) {
                    e.printStackTrace();
                }
            });
            holidayWorkPlanSer.update(tempHwork);
        }
        //添加各地区紧急联系人
        if( holidayProgrammeTO.getAreaRelationerTOList()!= null && holidayProgrammeTO.getAreaRelationerTOList().size()>0 ) {
            List<AreaRelationer> tempAreaRe = new ArrayList<>();
            List<AreaRelationer> areaRelationerList = BeanTransform.copyProperties(holidayProgrammeTO.getAreaRelationerTOList(), AreaRelationer.class, true);
            areaRelationerList.stream().forEach(str -> {
                try {
                    AreaRelationer areaRelationer = areaRelationerSer.findById(str.getId());
                    BeanUtils.copyProperties(str, areaRelationer, "id", "createTime", "holidayProgramme");
                    tempAreaRe.add(areaRelationer);
                } catch (SerException e) {
                    e.printStackTrace();
                }
            });
            areaRelationerSer.update(tempAreaRe);
        }
        //添加节假日福利
        if( holidayProgrammeTO.getWelfareTOList()!= null && holidayProgrammeTO.getWelfareTOList().size()>0 ) {
            List<Welfare> tempWelfare = new ArrayList<>();
            List<Welfare> welfareList = BeanTransform.copyProperties(holidayProgrammeTO.getWelfareTOList(), Welfare.class, true);
            welfareList.stream().forEach(str -> {
                try {
                    Welfare welfare = welfareSer.findById(str.getId());
                    BeanUtils.copyProperties(str, welfare, "id", "createTime", "holidayProgramme");
                    tempWelfare.add(welfare);
                } catch (SerException e) {
                    e.printStackTrace();
                }
            });
            welfareSer.update(tempWelfare);
        }

        //注意事项
        if( holidayProgrammeTO.getNoticeThingTOList()!= null && holidayProgrammeTO.getNoticeThingTOList().size()>0 ) {
            List<NoticeThing> tempNoticeThing = new ArrayList<>();
            List<NoticeThing> noticeThingList = BeanTransform.copyProperties(holidayProgrammeTO.getNoticeThingTOList(), NoticeThing.class, true);
            noticeThingList.stream().forEach(str -> {
                try {
                    NoticeThing noticeThing = noticeThingSer.findById(str.getId());
                    BeanUtils.copyProperties(str, noticeThing, "id", "createTime", "holidayProgramme");
                    tempNoticeThing.add(noticeThing);
                } catch (SerException e) {
                    e.printStackTrace();
                }
            });
            noticeThingSer.update(tempNoticeThing);
        }
        return BeanTransform.copyProperties(holidayProgramme, HolidayProgrammeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteHolidayProgramme(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        //先删除公司节假日工作安排模板
        HolidayWorkPlanDTO holidayWorkPlanDTO = new HolidayWorkPlanDTO();
        holidayWorkPlanDTO.getConditions().add(Restrict.eq("holidayProgramme.id",id));
        holidayWorkPlanSer.remove( holidayWorkPlanSer.findByCis(holidayWorkPlanDTO) );
        //先删除各地区紧急联系人
        AreaRelationerDTO areaRelationerDTO = new AreaRelationerDTO();
        areaRelationerDTO.getConditions().add(Restrict.eq("holidayProgramme.id",id));
        areaRelationerSer.remove( areaRelationerSer.findByCis(areaRelationerDTO));
        //先删除节假日福利
        WelfareDTO welfareDTO = new WelfareDTO();
        welfareDTO.getConditions().add(Restrict.eq("holidayProgramme.id",id));
        welfareSer.remove( welfareSer.findByCis(welfareDTO));
        //先删除注意事项
        NoticeThingDTO noticeThingDTO = new NoticeThingDTO();
        noticeThingDTO.getConditions().add(Restrict.eq("holidayProgramme.id",id));
        noticeThingSer.remove( noticeThingSer.findByCis(noticeThingDTO));

        super.remove(id);
    }
}