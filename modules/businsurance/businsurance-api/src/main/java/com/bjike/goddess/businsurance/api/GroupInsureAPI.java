package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.GroupInsureBO;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.to.GroupInsureTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 团体意外险信息管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface GroupInsureAPI {

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
     * 团体意外险列表总条数
     *
     */
    default Long countGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {
        return null;
    }
    /**
     * 团体意外险列表
     * @return class GroupInsureBO
     */
    default List<GroupInsureBO> listGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {return null;}
    /**
     *  添加
     * @param groupInsureTO 团体意外险信息
     * @return class GroupInsureBO
     */
    default GroupInsureBO addGroupInsure(GroupInsureTO groupInsureTO) throws SerException { return null;}

    /**
     *  编辑
     * @param groupInsureTO 团体意外险信息
     * @return class GroupInsureBO
     */
    default GroupInsureBO editGroupInsure(GroupInsureTO groupInsureTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteGroupInsure(String id ) throws SerException {return;};

    /**
     * 获取一个团体意外险信息
     * @return class GroupInsureBO
     */
    default GroupInsureBO getGroupInsure(String id ) throws SerException {return null;}


}