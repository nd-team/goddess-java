package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.democraticmeet.entity.MeetDesign;
import com.bjike.goddess.democraticmeet.dto.MeetDesignDTO;
import com.bjike.goddess.democraticmeet.to.MeetDesignTO;
import com.bjike.goddess.organize.bo.OpinionBO;

import java.util.List;

/**
 * 会议组织部分内容业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:27 ]
 * @Description: [ 会议组织部分内容业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetDesignSer extends Ser<MeetDesign, MeetDesignDTO> {



    /**
     * 所有会议层面
     */
    default List<String> listLevel(    ) throws SerException {return null;};


    /**
     * 所有岗位
     */
    default List<OpinionBO> listPosition(    ) throws SerException {return null;};



    /**
     * 所有岗位对应的计划参与人员
     */
    default List<String> listPostionPerson(MeetDesignTO meetDesignTO) throws SerException {return null;};



    /**
     * 所有会议形式
     */
    default List<String> listForm(    ) throws SerException {return null;};



    /**
     * 所有会议主持人
     */
    default List<String> listHost(    ) throws SerException {return null;};



}