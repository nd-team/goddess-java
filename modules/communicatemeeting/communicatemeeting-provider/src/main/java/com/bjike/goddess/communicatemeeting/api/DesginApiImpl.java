package com.bjike.goddess.communicatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.communicatemeeting.bo.DesginBO;
import com.bjike.goddess.communicatemeeting.dto.DesginDTO;
import com.bjike.goddess.communicatemeeting.service.DesginSer;
import com.bjike.goddess.communicatemeeting.to.DesginTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 交流会组织内容设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 03:31 ]
 * @Description: [ 交流会组织内容设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("desginApiImpl")
public class DesginApiImpl implements DesginAPI {
    @Autowired
    private DesginSer desginSer;

    @Override
    public Set<String> meetingTopics() throws SerException {
        return desginSer.meetingTopics();
    }

    @Override
    public Set<String> meetingLevels(String meetingTopic) throws SerException {
        return desginSer.meetingLevels(meetingTopic);
    }

    @Override
    public Set<String> normalPlanJobs(String meetingTopic, String meetingLevel) throws SerException {
        return desginSer.normalPlanJobs(meetingTopic, meetingLevel);
    }

    @Override
    public Set<String> normalPlanTimes(String meetingTopic, String meetingLevel) throws SerException {
        return desginSer.normalPlanTimes(meetingTopic, meetingLevel);
    }

    @Override
    public Set<String> freezePlanJobs(String meetingTopic, String meetingLevel) throws SerException {
        return desginSer.freezePlanJobs(meetingTopic, meetingLevel);
    }

    @Override
    public Set<String> freezePlanTimes(String meetingTopic, String meetingLevel) throws SerException {
        return desginSer.freezePlanTimes(meetingTopic, meetingLevel);
    }

    @Override
    public String findNumber(String meetingLevel) throws SerException {
        return desginSer.findNumber(meetingLevel);
    }

    @Override
    public void freezeJob(String id) throws SerException {
        desginSer.freezeJob(id);
    }

    @Override
    public void thawJob(String id) throws SerException {
        desginSer.thawJob(id);
    }

    @Override
    public void editJob(DesginTO to) throws SerException {
        desginSer.editJob(to);
    }

    @Override
    public void freezeTime(String id) throws SerException {
        desginSer.freezeTime(id);
    }

    @Override
    public void thawTime(String id) throws SerException {
        desginSer.thawTime(id);
    }

    @Override
    public void editTime(DesginTO to) throws SerException {
        desginSer.editTime(to);
    }

    @Override
    public DesginBO findByID(String id) throws SerException {
        return desginSer.findByID(id);
    }

    @Override
    public List<DesginBO> list(DesginDTO dto) throws SerException {
        return desginSer.list(dto);
    }

    @Override
    public Long countNum(DesginDTO dto) throws SerException {
        return desginSer.countNum(dto);
    }

    @Override
    public DesginBO save(DesginTO to) throws SerException {
        return desginSer.save(to);
    }

    @Override
    public void edit(DesginTO to) throws SerException {
        desginSer.edit(to);
    }
}