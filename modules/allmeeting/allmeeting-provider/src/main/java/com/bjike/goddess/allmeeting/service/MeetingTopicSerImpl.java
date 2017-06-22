package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.entity.MeetingLay;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.allmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.allmeeting.dto.MeetingTopicDTO;
import com.bjike.goddess.allmeeting.entity.MeetingTopic;
import com.bjike.goddess.allmeeting.to.MeetingTopicTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 议题管理业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-24 04:49 ]
 * @Description: [ 议题管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class MeetingTopicSerImpl extends ServiceImpl<MeetingTopic, MeetingTopicDTO> implements MeetingTopicSer {

    @Autowired
    private MeetingLaySer meetingLaySer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingTopicBO insertModel(MeetingTopicTO to) throws SerException {
        checkData(to);
        MeetingTopic model = BeanTransform.copyProperties(to, MeetingTopic.class);
        super.save(model);
        return BeanTransform.copyProperties(model, MeetingTopicBO.class);
    }

    //检查数据是否重复
    public void checkData(MeetingTopicTO to) throws SerException{
        MeetingTopicDTO dto = new MeetingTopicDTO();
        dto.getConditions().add(Restrict.eq("topic",to.getTopic()));
        dto.getConditions().add(Restrict.eq("topicContent",to.getTopicContent()));
        dto.setLimit(1);
        List<MeetingTopic> list = super.findByPage(dto);
        if(list!=null && !list.isEmpty()){
            //新增
            if(StringUtils.isEmpty(to.getId())){
                throw new SerException("该主题对应的议题包含内容已存在!");
            }else if(to.getId().equals(list.get(0).getId())){//编辑时判断是否同一条数据

            }else{
                throw new SerException("该主题对应的议题包含内容已存在!");
            }

        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingTopicBO updateModel(MeetingTopicTO to) throws SerException {
        MeetingTopic model = super.findById(to.getId());
        if (model != null) {

            checkData(to);
            BeanTransform.copyProperties(to, model);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空!");
        }
        return BeanTransform.copyProperties(model,MeetingTopicBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MeetingTopicBO> pageList(MeetingTopicDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto),MeetingTopicBO.class);
    }

    @Override
    public MeetingTopicBO findByLay(String layId) throws SerException {
        MeetingLay lay = meetingLaySer.findById(layId);
        if(lay!=null){
            return BeanTransform.copyProperties(super.findById(lay.getTopicId()),MeetingTopicBO.class);
        }else{
            throw new SerException("非法层面id，层面对象不存在!");
        }
    }
}