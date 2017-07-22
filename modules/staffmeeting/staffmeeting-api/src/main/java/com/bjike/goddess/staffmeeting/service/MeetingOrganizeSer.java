package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmeeting.bo.MeetingOrganizeBO;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingOrganize;
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
public interface MeetingOrganizeSer extends Ser<MeetingOrganize, MeetingOrganizeDTO> {

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
     * 新增组织内容
     *
     * @param to 组织内容
     * @return 组织内容
     */
    MeetingOrganizeBO insertModel(MeetingOrganizeTO to) throws SerException;

    /**
     * 更新组织内容
     *
     * @param to 组织内容
     * @return 组织内容
     */
    MeetingOrganizeBO updateModel(MeetingOrganizeTO to) throws SerException;

    /**
     * 冻结组织内容
     *
     * @param id 组织内容Id
     */
    void freeze(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<MeetingOrganizeBO> pageList(MeetingOrganizeDTO dto) throws SerException;

    void unfreeze(String id) throws SerException;
}