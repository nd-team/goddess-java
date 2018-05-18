package com.bjike.goddess.democraticmeet.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.democraticmeet.service.MeetDesignSer;
import com.bjike.goddess.democraticmeet.to.MeetDesignTO;
import com.bjike.goddess.organize.bo.OpinionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议组织部分内容业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:27 ]
 * @Description: [ 会议组织部分内容业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetDesignApiImpl")
public class MeetDesignApiImpl implements MeetDesignAPI {

    @Autowired
    private MeetDesignSer meetDesignSer;

    @Override
    public List<String> listLevel() throws SerException {
        return meetDesignSer.listLevel();
    }

    @Override
    public List<OpinionBO> listPosition() throws SerException {
        return meetDesignSer.listPosition();
    }

    @Override
    public List<String> listPostionPerson(MeetDesignTO meetDesignTO) throws SerException {
        return meetDesignSer.listPostionPerson(meetDesignTO);
    }

    @Override
    public List<String> listForm() throws SerException {
        return meetDesignSer.listForm();
    }

    @Override
    public List<String> listHost() throws SerException {
        return meetDesignSer.listHost();
    }
}