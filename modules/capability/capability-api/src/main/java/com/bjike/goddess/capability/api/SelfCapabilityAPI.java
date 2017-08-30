package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.SelfCapabilityBO;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.capability.to.SelfCapabilityTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 个人能力展示业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SelfCapabilityAPI {


    /**
     * 总条数
     */
    default Long counts(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        return null;
    }

    /**
     * 一个个人能力展示
     *
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO getOne(String id) throws SerException {
        return null;
    }


    /**
     * 个人能力展示列表
     *
     * @return class SelfCapabilityBO
     */
    default List<SelfCapabilityBO> listSelfCapability(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param selfCapabilityTO 个人能力展示信息
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO addSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        return null;
    }


    /**
     * 编辑
     *
     * @param selfCapabilityTO 个人能力展示信息
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO editSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        return null;
    }


    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSelfCapability(String id) throws SerException {
        return;
    }

    ;




    /**
     * 搜索
     *
     * @return class SelfCapabilityBO
     */
    default List<SelfCapabilityBO> listSelfCapabilityByName(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        return null;
    }

    /**
     * 根据姓名查找个人能力
     *
     * @return class SelfCapabilityBO
     */
    default SelfCapabilityBO getSelf(String name) throws SerException {
        return null;
    }

    /**
     * 查找所有姓名
     *
     */
    default List<String> listAllSelfName() throws SerException {
        return null;
    }

    /**
     * 导出
     */
    byte[] exportExcel(String name) throws SerException;

    /**
     * 功能导航权限
     * @throws SerException
     * @version v1
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
}