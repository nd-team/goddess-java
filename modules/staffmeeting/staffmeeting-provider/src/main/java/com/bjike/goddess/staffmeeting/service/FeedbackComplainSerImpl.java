package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.FeedbackComplainBO;
import com.bjike.goddess.staffmeeting.dto.FeedbackComplainDTO;
import com.bjike.goddess.staffmeeting.entity.FeedbackComplain;
import com.bjike.goddess.staffmeeting.to.FeedbackComplainTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 通告反馈投诉业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class FeedbackComplainSerImpl extends ServiceImpl<FeedbackComplain, FeedbackComplainDTO> implements FeedbackComplainSer {

    @Autowired
    private MeetingOrganizeSer meetingOrganizeSer;
    @Autowired
    private MeetingSummarySer meetingSummarySer;

    @Override
    public FeedbackComplainBO insertModel(FeedbackComplainTO to) throws SerException {
        FeedbackComplain model = BeanTransform.copyProperties(to, FeedbackComplain.class);
        super.save(model);
        return BeanTransform.copyProperties(model, FeedbackComplainBO.class);
    }

    @Override
    public FeedbackComplainBO updateModel(FeedbackComplainTO to) throws SerException {
        FeedbackComplain model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, FeedbackComplainBO.class);
    }

    @Override
    public void freeze(String id) throws SerException {
        FeedbackComplain model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.CONGEAL);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    public List<FeedbackComplainBO> pageList(FeedbackComplainDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return BeanTransform.copyProperties(super.findByPage(dto),FeedbackComplainBO.class);
    }
}