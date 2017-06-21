package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.staffmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingOrganize;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工代表大会纪要业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class MeetingSummarySerImpl extends ServiceImpl<MeetingSummary, MeetingSummaryDTO> implements MeetingSummarySer {

    @Autowired
    private MeetingOrganizeSer meetingOrganizeSer;
    @Autowired
    private MeetingTopicSer meetingTopicSer;

    @Override
    public MeetingSummaryBO findAndSet(String id) throws SerException {
        MeetingSummary model = super.findById(id);
        if (model != null) {
            MeetingSummaryBO bo = BeanTransform.copyProperties(model, MeetingSummary.class);
            setProperties(bo);
            return bo;
        } else {
            throw new SerException("非法id,纪要对象不存在!");
        }
    }

    @Override
    public MeetingSummaryBO updateModel(MeetingSummaryTO to) throws SerException {
        MeetingSummary model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, MeetingSummaryBO.class);
    }

    @Override
    public void freeze(String id) throws SerException {
        MeetingSummary model = super.findById(id);
        if (model != null) {
            model.setModifyTime(LocalDateTime.now());
            model.setStatus(Status.CONGEAL);
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    public List<MeetingSummaryBO> pageList(MeetingSummaryDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("status",Status.THAW));
        List<MeetingSummaryBO> boList = BeanTransform.copyProperties(super.findByPage(dto), MeetingSummaryBO.class);

        if (boList != null && !boList.isEmpty()) {
            for (MeetingSummaryBO bo : boList) {
                setProperties(bo);
            }
        }
        return boList;
    }

    public void setProperties(MeetingSummaryBO bo) throws SerException {
        MeetingOrganizeDTO organizeDTO = new MeetingOrganizeDTO();
        organizeDTO.getConditions().add(Restrict.eq("meetingNum", bo.getMeetingNum()));
        MeetingOrganize organizeBO = meetingOrganizeSer.findOne(organizeDTO);
        //设置会议组织信息
        if (organizeBO != null) {
            bo.setMeetingType(organizeBO.getMeetingType());
            bo.setContent(organizeBO.getContent());
            bo.setOrganizer(organizeBO.getOrganizer());
            bo.setMeetingNum(organizeBO.getMeetingNum());
            //查询
            MeetingTopicBO topic = meetingTopicSer.findByLay(organizeBO.getLayId());
            if (topic != null) {
                bo.setTopic(topic.getTopic());
            }
        }
    }
}