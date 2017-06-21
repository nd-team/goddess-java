package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.staffmeeting.dto.MeetingDiscussionDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingDiscussion;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通告反馈投诉业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 05:18 ]
 * @Description: [ 通告反馈投诉业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class MeetingDiscussionSerImpl extends ServiceImpl<MeetingDiscussion, MeetingDiscussionDTO> implements MeetingDiscussionSer {

    @Autowired
    private MeetingSummarySer meetingSummarySer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public MeetingDiscussionBO insertModel(MeetingDiscussionTO to) throws SerException {
        //校验用户是否未参会人员
        UserBO userBO = userAPI.currentUser();
        String currentUser = userBO.getUsername();
        String currentNum = userBO.getEmployeeNumber();
        MeetingSummary summary = meetingSummarySer.findById(to.getSummaryId());
        String actualUsers = summary.getActualUsers();
        if (actualUsers.contains(currentNum)) {
            checkUnique(currentNum, to.getSummaryId());

            to.setUser(currentUser);
            MeetingDiscussion model = BeanTransform.copyProperties(to, MeetingDiscussion.class);
            super.save(model);
            return BeanTransform.copyProperties(model, MeetingDiscussionBO.class);
        } else {
            throw new SerException("只有参会人员可填写!");
        }
    }

    //查询当前用户是否已经填写意见
    public void checkUnique(String userNum, String summaryId) throws SerException {

        MeetingDiscussionDTO dto = new MeetingDiscussionDTO();
        dto.getConditions().add(Restrict.eq("userNum", userNum));
        dto.getConditions().add(Restrict.eq("summaryId", summaryId));
        dto.setLimit(1);
        List<MeetingDiscussion> list = super.findByPage(dto);
        if (list != null && !list.isEmpty()) {
            throw new SerException("亲，您经发表过意见了哦!");
        }
    }

    @Override
    public List<MeetingDiscussionBO> listBySummaryId(String summaryId) throws SerException {
        MeetingDiscussionDTO dto = new MeetingDiscussionDTO();
        dto.getConditions().add(Restrict.eq("summaryId", summaryId));
        return BeanTransform.copyProperties(super.findByCis(dto), MeetingDiscussionBO.class);
    }
}