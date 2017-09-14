package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.service.PositionUserDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("positionUserDetailApiImpl")
public class PositionUserDetailApiImpl implements PositionUserDetailAPI {

    @Autowired
    private PositionUserDetailSer positionUserDetailSer;

    @Override
    public List<String> findMainUser() throws SerException {
        return positionUserDetailSer.findMainUser();
    }

    @Override
    public List<String> findAgentUser() throws SerException {
        return positionUserDetailSer.findAgentUser();
    }

    @Override
    public List<String> getPosition() throws SerException {
        return positionUserDetailSer.getPosition();
    }

    @Override
    public Long getAreaNum(String startTime,String endTime) throws SerException {
        return positionUserDetailSer.getAreaNum(startTime,endTime);
    }
}
