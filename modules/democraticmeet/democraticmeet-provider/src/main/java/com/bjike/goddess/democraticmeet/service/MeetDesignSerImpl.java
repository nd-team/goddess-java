package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.democraticmeet.dto.MeetDesignDTO;
import com.bjike.goddess.democraticmeet.entity.MeetDesign;
import com.bjike.goddess.democraticmeet.to.MeetDesignTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会议组织部分内容业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:27 ]
 * @Description: [ 会议组织部分内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "democraticmeetSerCache")
@Service
public class MeetDesignSerImpl extends ServiceImpl<MeetDesign, MeetDesignDTO> implements MeetDesignSer {

    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    public List<String> listLevel() throws SerException {
        List<String> titles = new ArrayList<>();
        String [] fields = new String[]{"meetLevel"};
        String sql = " select meetLevel from democraticmeet_meetdesign group by meetLevel";
        List<MeetDesign> list = super.findBySql(sql , MeetDesign.class , fields);
        titles = list.stream().map(MeetDesign::getMeetLevel).collect(Collectors.toList());
        return titles;
    }

    @Override
    public List<OpinionBO> listPosition() throws SerException {
        List<OpinionBO> list = positionDetailAPI.findAllOpinion();
        return list;
    }

    @Override
    public List<String> listPostionPerson(MeetDesignTO meetDesignTO) throws SerException {
        List<String> userName = new ArrayList<>();
        List<UserBO> userList = new ArrayList<>();
        String [] positionIds = meetDesignTO.getPositionIds();
        if(positionIds != null && positionIds.length>0 ){
            for(String pid : positionIds){
                List<UserBO> user = positionDetailUserAPI.findByPosition( pid );
                userList.addAll( user );
            }
        }
        if( userList != null && userList.size()>0 ){
            userName = userList.stream().map(UserBO::getUsername).collect(Collectors.toList());
        }
        return userName;
    }

    @Override
    public List<String> listForm() throws SerException {
        List<String> titles = new ArrayList<>();
        String [] fields = new String[]{"meetForm"};
        String sql = " select meetForm from democraticmeet_meetdesign group by meetForm";
        List<MeetDesign> list = super.findBySql(sql , MeetDesign.class , fields);
        titles = list.stream().map(MeetDesign::getMeetForm).collect(Collectors.toList());
        return titles;
    }

    @Override
    public List<String> listHost() throws SerException {
        List<String> list = new ArrayList<>();
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<UserBO> userList =  userAPI.findByCis(dto);
        if(userList != null && userList.size()>0 ){
            list = userList.stream().map(UserBO::getUsername).collect(Collectors.toList());
        }
        return list;
    }
}