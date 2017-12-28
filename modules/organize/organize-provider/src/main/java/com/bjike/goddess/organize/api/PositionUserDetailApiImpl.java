package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.service.PositionUserDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public Map<String, String> departPosition(String userId) throws SerException {
        return positionUserDetailSer.departPosition(userId);
    }

    @Override
    public Set<String> findMains(String name) throws SerException {
        return positionUserDetailSer.findMains(name);
    }

    @Override
    public Set<String> findCarbons(String name) throws SerException {
        return positionUserDetailSer.findCarbons(name);
    }

    @Override
    public List<PositionDetailUserBO> findManager() throws SerException {
        return positionUserDetailSer.findManager();
    }

    @Override
    public List<String> arrangementAndDepartId(String name) throws SerException {
        return positionUserDetailSer.arrangementAndDepartId(name);
    }

    @Override
    public PositionDetailBO getPosition(String name) throws SerException {
        return positionUserDetailSer.getPosition(name);
    }

    @Override
    public List<String> findCharge() throws SerException {
        return positionUserDetailSer.findCharge();
    }

    @Override
    public List<String> projectManager() throws SerException {
        return positionUserDetailSer.projectManager();
    }
}
