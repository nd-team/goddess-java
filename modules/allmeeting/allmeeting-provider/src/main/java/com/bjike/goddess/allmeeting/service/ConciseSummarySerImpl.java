package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ConciseSummaryBO;
import com.bjike.goddess.allmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.entity.AllMeetingOrganize;
import com.bjike.goddess.allmeeting.entity.ConciseSummary;
import com.bjike.goddess.allmeeting.to.ConciseSummaryTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 简洁交流讨论纪要业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:19 ]
 * @Description: [ 简洁交流讨论纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class ConciseSummarySerImpl extends ServiceImpl<ConciseSummary, ConciseSummaryDTO> implements ConciseSummarySer {

    @Autowired
    private AllMeetingOrganizeSer allMeetingOrganizeSer;
    @Autowired
    private MeetingTopicSer meetingTopicSer;

    @Override
    public ConciseSummaryBO updateModel(ConciseSummaryTO to) throws SerException {
        ConciseSummary model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, ConciseSummaryBO.class);
    }

    @Override
    public void freeze(String id) throws SerException {
        ConciseSummary model = super.findById(id);
        if (model != null) {
            model.setModifyTime(LocalDateTime.now());
            model.setStatus(Status.CONGEAL);
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    public List<ConciseSummaryBO> pageList(ConciseSummaryDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status",Status.THAW));
        List<ConciseSummaryBO> boList = BeanTransform.copyProperties(super.findByPage(dto), ConciseSummaryBO.class);

        if (boList != null && !boList.isEmpty()) {
            for (ConciseSummaryBO bo : boList) {
                setProperties(bo);
            }
        }
        return boList;
    }

    @Override
    public ConciseSummaryBO findAndSet(String id) throws SerException {
        ConciseSummary model = super.findById(id);
        if (model != null) {
            ConciseSummaryBO bo = BeanTransform.copyProperties(model, ConciseSummary.class);
            setProperties(bo);
            return bo;
        } else {
            throw new SerException("非法id,纪要对象不存在!");
        }

    }

    public void setProperties(ConciseSummaryBO bo) throws SerException {
        AllMeetingOrganizeDTO organizeDTO = new AllMeetingOrganizeDTO();
        organizeDTO.getConditions().add(Restrict.eq("meetingNum", bo.getMeetingNum()));
        AllMeetingOrganize organizeBO = allMeetingOrganizeSer.findOne(organizeDTO);
        //设置会议组织信息
        if (organizeBO != null) {
            bo.setMeetingType(organizeBO.getMeetingType());
            bo.setContent(organizeBO.getContent());
            bo.setRelation(organizeBO.getRelation());
            bo.setOrganizer(organizeBO.getOrganizer());
            bo.setCompere(organizeBO.getCompere());
            bo.setMeetingNum(organizeBO.getMeetingNum());
            //查询
            MeetingTopicBO topic = meetingTopicSer.findByLay(organizeBO.getLayId());
            if (topic != null) {
                bo.setTopic(topic.getTopic());
            }
        }
    }
}