package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.MeetingOrganizeBO;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingOrganizeTO;

import java.util.List;

/**
 * 员工代表大会组织内容业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingOrganizeAPI {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(MeetingOrganizeDTO dto) throws SerException;

    /**
     * 根据Id查询会议组织内容
     *
     * @param id 会议组织Id
     * @return 会议组织内容
     */
    MeetingOrganizeBO findById(String id) throws SerException;

    /**
     * 新增会议组织内容
     *
     * @param to 会议组织内容
     * @return 会议组织内容
     */
    MeetingOrganizeBO add(MeetingOrganizeTO to) throws SerException;

    /**
     * 编辑会议组织内容
     *
     * @param to 会议组织内容
     * @return 会议组织内容
     */
    MeetingOrganizeBO edit(MeetingOrganizeTO to) throws SerException;

    /**
     * 冻结会议组织内容
     *
     * @param id 会议组织内容Id
     */
    void freeze(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     * @throws SerException
     */
    List<MeetingOrganizeBO> pageList(MeetingOrganizeDTO dto) throws SerException;

    /**
     * 解冻
     *
     * @param id
     */
    void unfreeze(String id) throws SerException;
}