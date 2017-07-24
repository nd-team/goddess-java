package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.GroupByInsurerBO;
import com.bjike.goddess.businsurance.dto.GroupByInsurerDTO;
import com.bjike.goddess.businsurance.to.GroupByInsurerTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 团体意外险被保险人信息管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:07 ]
 * @Description: [ 团体意外险被保险人信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface GroupByInsurerAPI {

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
     * 被保险人信息列表总条数
     *
     */
    default Long countGroupByInsurer(GroupByInsurerDTO groupByInsurerDTO) throws SerException {
        return null;
    }
    /**
     * 被保险人信息列表
     * @return class GroupByInsurerBO
     */
    default List<GroupByInsurerBO> listGroupByInsurer(GroupByInsurerDTO groupByInsurerDTO) throws SerException {return null;}
    /**
     *  添加
     * @param groupByInsurerTO 被保险人信息信息
     * @return class GroupByInsurerBO
     */
    default GroupByInsurerBO addGroupByInsurer(GroupByInsurerTO groupByInsurerTO) throws SerException { return null;}

    /**
     *  编辑
     * @param groupByInsurerTO 被保险人信息信息
     * @return class GroupByInsurerBO
     */
    default GroupByInsurerBO editGroupByInsurer(GroupByInsurerTO groupByInsurerTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteGroupByInsurer(String id ) throws SerException {return;};


    /**
     * 获取一个被保险人信息信息
     * @return class GroupInsureBO
     */
    default GroupByInsurerBO getGroupByInsurer(String id ) throws SerException {return null;}



}