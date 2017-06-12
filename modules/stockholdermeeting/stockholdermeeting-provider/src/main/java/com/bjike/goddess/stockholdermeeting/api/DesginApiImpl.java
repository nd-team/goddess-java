package com.bjike.goddess.stockholdermeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.stockholdermeeting.bo.DesginBO;
import com.bjike.goddess.stockholdermeeting.dto.DesginDTO;
import com.bjike.goddess.stockholdermeeting.service.DesginSer;
import com.bjike.goddess.stockholdermeeting.to.DesginTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:33 ]
 * @Description: [ 股东大会组织内容设计业务接口实现 ]
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
    public String topicContent(String meetingTopic) throws SerException {
        return desginSer.topicContent(meetingTopic);
    }

    @Override
    public String planTime(String meetingTopic, String meetingLevel) throws SerException {
        return desginSer.planTime(meetingTopic, meetingLevel);
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