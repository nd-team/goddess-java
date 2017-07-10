package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.FeedbackComplainBO;
import com.bjike.goddess.staffmeeting.dto.FeedbackComplainDTO;
import com.bjike.goddess.staffmeeting.entity.FeedbackComplain;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.to.FeedbackComplainTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    private UserAPI userAPI;
    @Autowired
    private MeetingSummarySer meetingSummarySer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FeedbackComplainBO insertModel(FeedbackComplainTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String username = userBO.getUsername();
        String userNum = userBO.getEmployeeNumber();
        MeetingSummary summary = meetingSummarySer.findById(to.getSummaryId());
        if (summary != null) {
            if (isAttend(username, summary)) {
                FeedbackComplain model = BeanTransform.copyProperties(to, FeedbackComplain.class);
                model.setMeetingSummary(summary);
                model.setDissentUser(username);
                model.setDissentUserNum(userNum);
                super.save(model);
                return BeanTransform.copyProperties(model, FeedbackComplainBO.class);
            } else {
                throw new SerException("只有该会议参会人员才能反馈!");
            }
        } else {
            throw new SerException("非法纪要Id,纪要对象不能为空!");
        }
    }

    //检查当前用户是否参与会议
    public Boolean isAttend(String username, MeetingSummary summary) throws SerException {
        String userStr = summary.getActualUsers();
        if (userStr.contains(",")) {
            String[] users = summary.getActualUsers().split(",");
            if (!Arrays.asList(users).contains(username)) {
                return false;
            }
        } else {
            if (!username.equals(userStr)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FeedbackComplainBO updateModel(FeedbackComplainTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String username = userBO.getUsername();
        String userNum = userBO.getEmployeeNumber();
        FeedbackComplain model = super.findById(to.getId());
        if (model != null) {
            MeetingSummary summary = meetingSummarySer.findById(to.getSummaryId());
            if (summary != null) {
                if (isAttend(username, summary)) {
                    BeanTransform.copyProperties(to, model);
                    model.setDissentUser(username);
                    model.setDissentUserNum(userNum);
                    model.setModifyTime(LocalDateTime.now());
                    super.update(model);
                    return BeanTransform.copyProperties(model, FeedbackComplainBO.class);
                } else {
                    throw new SerException("只有该会议参会人员才能反馈!");
                }
            } else {
                throw new SerException("非法纪要Id,纪要对象不能为空!");
            }
        } else {
            throw new SerException("非法Id，反馈对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        FeedbackComplain model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setStatus(Status.CONGEAL);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("该记录无需冻结!");
            }
        } else {
            throw new SerException("非法Id，反馈对象不能为空");
        }
    }

    @Override
    public List<FeedbackComplainBO> pageList(FeedbackComplainDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getSorts().add("dissentUserNum=asc");
        dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        return BeanTransform.copyProperties(super.findByPage(dto), FeedbackComplainBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void unfreeze(String id) throws SerException {
        FeedbackComplain model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setStatus(Status.THAW);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("该记录无需解冻!");
            }
        } else {
            throw new SerException("非法Id，反馈对象不能为空");
        }
    }
}