package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingOrganize;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private UserAPI userAPI;
    @Autowired
    private ReferPermissionSer referPermissionSer;

    @Override
    public MeetingSummaryBO findAndSet(String id) throws SerException {
        MeetingSummary model = super.findById(id);
        if (model != null) {
            MeetingSummaryBO bo = setProperties(model);

            return bo;
        } else {
            throw new SerException("非法id,纪要对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingSummaryBO updateModel(MeetingSummaryTO to) throws SerException {
        MeetingSummary model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，纪要对象不能为空!");
        }
        return BeanTransform.copyProperties(model, MeetingSummaryBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        MeetingSummary model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("该记录无需冻结!");
            }

        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MeetingSummaryBO> pageList(MeetingSummaryDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", dto.getStatus()));

        UserBO user = userAPI.currentUser();


        List<MeetingSummary> list = null;
        List<MeetingSummary> summaries = null;
        //没有申请权限的只能查看自己参与的会议数据
        if (!referPermissionSer.getPermission(user.getEmployeeNumber())) {
            dto.getConditions().add(Restrict.like("actualUsers", user.getUsername()));
            list = super.findByPage(dto);
            summaries = new ArrayList<MeetingSummary>();
            if (!CollectionUtils.isEmpty(list)) {
                for (MeetingSummary model : list) {
                    String actualUsers = model.getActualUsers();
                    List<String> users = Arrays.asList(actualUsers.split(","));
                    if (users.contains(user.getUsername())) {
                        summaries.add(model);
                    }
                }
            }
        } else {
            summaries = super.findByPage(dto);
        }

        if (!CollectionUtils.isEmpty(summaries)) {
            List<MeetingSummaryBO> boList = new ArrayList<MeetingSummaryBO>();
            for (MeetingSummary model : summaries) {
                MeetingSummaryBO bo = setProperties(model);
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void unFreeze(String id) throws SerException {
        MeetingSummary model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("该记录无需解冻!");
            }

        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    public MeetingSummaryBO setProperties(MeetingSummary model) {
        MeetingSummaryBO bo = BeanTransform.copyProperties(model, MeetingSummaryBO.class);
        MeetingOrganize organize = model.getMeetingOrganize();
        bo.setMeetingType(organize.getMeetingType());
        bo.setContent(organize.getContent());
        bo.setOrganizer(organize.getOrganizer());
        bo.setLay(organize.getMeetingLay().getName());
        bo.setMeetingPurpose(organize.getMeetingPurpose());
        bo.setTopic(organize.getMeetingLay().getMeetingTopic().getTopic());
        return bo;
    }

}