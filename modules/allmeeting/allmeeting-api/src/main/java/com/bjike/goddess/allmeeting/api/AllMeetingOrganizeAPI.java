package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.AllMeetingOrganizeBO;
import com.bjike.goddess.allmeeting.bo.MeetingLayBO;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;
import com.bjike.goddess.allmeeting.excel.SonPermissionObject;
import com.bjike.goddess.allmeeting.to.AllMeetingOrganizeTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 所有工作内容汇总会议组织内容业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AllMeetingOrganizeAPI {

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

    /**
     * 根据Id查询会议组织内容
     *
     * @param id 会议组织内容Id
     * @return 会议组织内容
     */
    AllMeetingOrganizeBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(AllMeetingOrganizeDTO dto) throws SerException;

    /**
     * 新增会议组织内容
     *
     * @param to 会议组织内容
     * @return 会议组织内容
     */
    AllMeetingOrganizeBO add(AllMeetingOrganizeTO to) throws SerException;

    /**
     * 编辑会议组织内容
     *
     * @param to 会议组织内容
     * @return 会议组织内容
     */
    AllMeetingOrganizeBO edit(AllMeetingOrganizeTO to) throws SerException;

    /**
     * 冻结会议组织内容
     *
     * @param id 会议组织内容Id
     * @throws SerException
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻会议组织内容
     *
     * @param id 会议组织内容Id
     */
    void unfreeze(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<AllMeetingOrganizeBO> pageList(AllMeetingOrganizeDTO dto) throws SerException;

}