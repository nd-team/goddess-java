package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingLayBO;
import com.bjike.goddess.staffmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingLay;
import com.bjike.goddess.staffmeeting.entity.MeetingOrganize;
import com.bjike.goddess.staffmeeting.entity.MeetingTopic;
import com.bjike.goddess.staffmeeting.to.MeetingLayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 会议层面业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:34 ]
 * @Description: [ 会议层面业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class MeetingLaySerImpl extends ServiceImpl<MeetingLay, MeetingLayDTO> implements MeetingLaySer {
    @Autowired
    private MeetingTopicSer meetingTopicSer;
    @Autowired
    private MeetingOrganizeSer meetingOrganizeSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingLayBO insertModel(MeetingLayTO to) throws SerException {
        MeetingTopic topic = meetingTopicSer.findById(to.getTopicId());
        if (topic == null) {
            throw new SerException("非法主题Id,主题对象不存在");
        }
        checkData(to);

        MeetingLay model = BeanTransform.copyProperties(to, MeetingLay.class);
        model.setMeetingTopic(topic);
        super.save(model);
        return BeanTransform.copyProperties(model, MeetingLayBO.class);
    }

    //检查数据是否重复
    public void checkData(MeetingLayTO to) throws SerException {
        MeetingLayDTO dto = new MeetingLayDTO();
        dto.getConditions().add(Restrict.eq("meetingTopic.id", to.getTopicId()));
        dto.getConditions().add(Restrict.eq("name", to.getName()));
        dto.setLimit(1);
        List<MeetingLay> list = super.findByPage(dto);
        if (list != null && !list.isEmpty()) {
            //新增
            if (StringUtils.isEmpty(to.getId())) {
                throw new SerException("该主题对应的会议层面已存在!");
            } else if (to.getId().equals(list.get(0).getId())) {//编辑时判断是否同一条数据

            } else {
                throw new SerException("该主题对应的会议层面已存在!");
            }

        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingLayBO updateModel(MeetingLayTO to) throws SerException {
        MeetingTopic topic = meetingTopicSer.findById(to.getTopicId());
        if (topic == null) {
            throw new SerException("非法主题Id,主题对象不存在");
        }
        MeetingLay model = super.findById(to.getId());
        if (model != null) {
            checkData(to);
            BeanTransform.copyProperties(to, model);
            model.setModifyTime(LocalDateTime.now());
            model.setMeetingTopic(topic);
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, MeetingLayBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        MeetingOrganizeDTO organizeDTO = new MeetingOrganizeDTO();
        organizeDTO.getConditions().add(Restrict.eq("meetingLay.id", id));
        List<MeetingOrganize> organizeList = meetingOrganizeSer.findByCis(organizeDTO);
        if (CollectionUtils.isEmpty(organizeList)) {
            super.remove(id);
        }else{
            throw new SerException("该层面存在会议组织关联,请确保会议会议层面无数据关联!");
        }
    }

    @Override
    public List<MeetingLayBO> pageList(MeetingLayDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<MeetingLay> list = super.findByPage(dto);

        return setTopic(list);
    }

    @Override
    public List<MeetingLayBO> lays() throws SerException {
        List<MeetingLay> list = super.findAll();

        return setTopic(list);
    }


    public List<MeetingLayBO> setTopic(List<MeetingLay> list) {
        List<MeetingLayBO> boList = null;
        if (!CollectionUtils.isEmpty(list)) {
            boList = new ArrayList<MeetingLayBO>();
            for (MeetingLay model : list) {
                MeetingLayBO bo = BeanTransform.copyProperties(model, MeetingLayBO.class);
                bo.setTopicName(model.getMeetingTopic().getTopic());
                bo.setTopicContent(model.getMeetingTopic().getTopicContent());
                boList.add(bo);
            }
        }
        return boList;
    }
}