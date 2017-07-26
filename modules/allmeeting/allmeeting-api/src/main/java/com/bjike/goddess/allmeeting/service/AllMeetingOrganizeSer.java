package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.AllMeetingOrganizeBO;
import com.bjike.goddess.allmeeting.bo.MeetingLayBO;
import com.bjike.goddess.allmeeting.excel.SonPermissionObject;
import com.bjike.goddess.allmeeting.to.AllMeetingOrganizeTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.WorkCollectPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.allmeeting.entity.AllMeetingOrganize;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;

import java.util.List;

/**
* 所有工作内容汇总会议组织内容业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-05-31 11:06 ]
* @Description:	[ 所有工作内容汇总会议组织内容业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AllMeetingOrganizeSer extends Ser<AllMeetingOrganize, AllMeetingOrganizeDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    AllMeetingOrganizeBO insertModel(AllMeetingOrganizeTO to) throws SerException;

    AllMeetingOrganizeBO updateModel(AllMeetingOrganizeTO to) throws SerException;

    void freeze(String id) throws SerException;

    void unfreeze(String id) throws SerException;

    List<AllMeetingOrganizeBO> pageList(AllMeetingOrganizeDTO dto) throws SerException;

    //会议编号校验，防止非法数据提交
    void validNum(String meetingNum) throws SerException;

    /**
     * 查询计划参会人员
     */
    default String[] getPlanPeople() throws SerException{
        return null;
    }

}