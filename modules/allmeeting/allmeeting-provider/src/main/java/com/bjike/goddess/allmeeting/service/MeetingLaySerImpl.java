package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MeetingLayBO;
import com.bjike.goddess.allmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.allmeeting.entity.MeetingLay;
import com.bjike.goddess.allmeeting.entity.MeetingTopic;
import com.bjike.goddess.allmeeting.to.MeetingLayTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会议层面业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-26 10:16 ]
 * @Description: [ 会议层面业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class MeetingLaySerImpl extends ServiceImpl<MeetingLay, MeetingLayDTO> implements MeetingLaySer {

    @Autowired
    private MeetingTopicSer meetingTopicSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingLayBO insertModel(MeetingLayTO to) throws SerException {
        checkData(to);
        MeetingLay model = BeanTransform.copyProperties(to, MeetingLay.class);
        super.save(model);
        return BeanTransform.copyProperties(model, MeetingLayBO.class);
    }

    //检查数据是否重复
    public void checkData(MeetingLayTO to) throws SerException{
        MeetingLayDTO dto = new MeetingLayDTO();
        dto.getConditions().add(Restrict.eq("topicId",to.getTopicId()));
        dto.getConditions().add(Restrict.eq("name",to.getName()));
        dto.setLimit(1);
        List<MeetingLay> list = super.findByPage(dto);
        if(list!=null && !list.isEmpty()){
            //新增
            if(StringUtils.isEmpty(to.getId())){
                throw new SerException("该主题对应的会议层面已存在!");
            }else if(to.getId().equals(list.get(0).getId())){//编辑时判断是否同一条数据

            }else{
                throw new SerException("该主题对应的会议层面已存在!");
            }

        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingLayBO updateModel(MeetingLayTO to) throws SerException {

        MeetingLay model = super.findById(to.getId());
        if (model != null) {
            checkData(to);
            BeanTransform.copyProperties(to, model);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, MeetingLayBO.class);
    }

    @Override
    public List<MeetingLayBO> pageList(MeetingLayDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<MeetingLayBO> boList = BeanTransform.copyProperties(super.findByPage(dto), MeetingLayBO.class);
        if (boList != null && !boList.isEmpty()) {
            for (MeetingLayBO bo : boList) {
                MeetingTopic topic = meetingTopicSer.findById(bo.getTopicId());
                if (topic != null) {
                    bo.setTopicName(topic.getTopic());
                    bo.setTopicContent(topic.getTopicContent());
                }
            }
        }
        return boList;
    }
}