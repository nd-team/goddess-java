package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.allmeeting.dto.MeetingDiscussionDTO;
import com.bjike.goddess.allmeeting.entity.ConciseSummary;
import com.bjike.goddess.allmeeting.entity.MeetingDiscussion;
import com.bjike.goddess.allmeeting.entity.MultiwheelSummary;
import com.bjike.goddess.allmeeting.to.FirstDiscussionTO;
import com.bjike.goddess.allmeeting.to.SecondDiscussionTO;
import com.bjike.goddess.allmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会议讨论意见业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 03:10 ]
 * @Description: [ 会议讨论意见业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class MeetingDiscussionSerImpl extends ServiceImpl<MeetingDiscussion, MeetingDiscussionDTO> implements MeetingDiscussionSer {

    @Autowired
    private ConciseSummarySer conciseSummarySer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MultiwheelSummarySer multiwheelSummarySer;


    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingDiscussionBO addFrist(FirstDiscussionTO to) throws SerException {
        //校验用户是否未参会人员
        UserBO userBO = userAPI.currentUser();
        String currentUser = userBO.getUsername();
        String currentNum = userBO.getEmployeeNumber();
        MultiwheelSummary summary = multiwheelSummarySer.findById(to.getSummaryId());
        if (summary != null) {
            String actualUsers = summary.getActualUsers();
            if (actualUsers.contains(currentUser)) {
                checkUnique(currentNum, to.getSummaryId());

                MeetingDiscussion model = BeanTransform.copyProperties(to, MeetingDiscussion.class);
                model.setUser(currentUser);
                model.setUserNum(currentNum);
                super.save(model);
                return BeanTransform.copyProperties(model, MeetingDiscussionBO.class);

            } else {
                throw new SerException("只有参会人员可填写!");
            }
        } else {
            throw new SerException("非法summaryId,纪要对象不存在!");
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingDiscussionBO addSecond(SecondDiscussionTO to) throws SerException {

        MeetingDiscussion model = super.findById(to.getId());
        if (model != null) {
            if(StringUtils.isEmpty(model.getSecondDis())){
                model.setSecondDis(to.getSecondDis());
                model.setFinalDis(model.getFirstDis() + model.getSecondDis());
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            }else{
                throw new SerException("二轮意见已存在,不可重复提交意见");
            }
            return BeanTransform.copyProperties(model, MeetingDiscussionBO.class);
        } else {
            throw new SerException("非法id,交流讨论对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingDiscussionBO addFirstByCon(MeetingDiscussionTO to) throws SerException {
        //校验用户是否未参会人员
        UserBO userBO = userAPI.currentUser();
        String currentUser = userBO.getUsername();
        String currentNum = userBO.getEmployeeNumber();
        ConciseSummary summary = conciseSummarySer.findById(to.getSummaryId());
        if (summary != null) {
            String actualUsers = summary.getActualUsers();
            if (actualUsers.contains(currentUser)) {
                checkUnique(currentNum, to.getSummaryId());

                MeetingDiscussion model = BeanTransform.copyProperties(to, MeetingDiscussion.class);
                model.setUser(currentUser);
                model.setUserNum(currentNum);
                super.save(model);
                return BeanTransform.copyProperties(model, MeetingDiscussionBO.class);

            } else {
                throw new SerException("只有参会人员可填写!");
            }
        } else {
            throw new SerException("非法summaryId,纪要对象不存在!");
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