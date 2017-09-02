package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.LogoutEquityBO;
import com.bjike.goddess.shareholdersmanage.bo.LogoutEquityLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.LogoutEquityDTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.LogoutEquityTO;

import java.util.List;

/**
 * 注销股权业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:51 ]
 * @Description: [ 注销股权业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LogoutEquityAPI {
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
     * 注销股权列表总条数
     */
    default Long countLogEquity(LogoutEquityDTO logoutEquityDTO) throws SerException {
        return null;
    }

    /**
     * 一个注销股权
     *
     * @return class LogoutEquityBO
     */
    default LogoutEquityBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 注销股权列表
     *
     * @param logoutEquityDTO 注销股权dto
     * @return class LogoutEquityBO
     * @throws SerException
     */
    default List<LogoutEquityBO> findList(LogoutEquityDTO logoutEquityDTO) throws SerException {
        return null;
    }

    /**
     * 注销股权添加
     *
     * @param logoutEquityTO 注销股权数据to
     * @throws SerException
     */
    default LogoutEquityBO save(LogoutEquityTO logoutEquityTO) throws SerException {
        return null;
    }

    /**
     * 注销股权编辑
     *
     * @param logoutEquityTO 注销股权数据to
     * @throws SerException
     */
    default LogoutEquityBO edit(LogoutEquityTO logoutEquityTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除注销股权
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
    /**
     * 根据注销股东名称链接数据
     *
     * @param logoutShareName
     * @throws SerException
     */
    default LogoutEquityLinkDateBO linkDateByName(String logoutShareName) throws SerException {
        return null;
    }
}