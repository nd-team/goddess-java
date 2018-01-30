package com.bjike.goddess.problemhandle.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.problemhandle.bo.CommunicationFormworkBO;
import com.bjike.goddess.problemhandle.dto.CommunicationFormworkDTO;
import com.bjike.goddess.problemhandle.entity.CommunicationFormwork;
import com.bjike.goddess.problemhandle.to.CommunicationFormworkTO;
import com.bjike.goddess.problemhandle.to.GuidePermissionTO;

import java.util.List;

/**
 * 各类沟通模板业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-09 10:17 ]
 * @Description: [ 各类沟通模板业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommunicationFormworkSer extends Ser<CommunicationFormwork, CommunicationFormworkDTO> {

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
     * 各类沟通模板列表总条数
     */
    default Long countCommuni(CommunicationFormworkDTO communicationFormworkDTO) throws SerException {
        return null;
    }

    /**
     * 一个各类沟通模板
     *
     * @return class CommunicationFormworkBO
     */
    default CommunicationFormworkBO getOne(String id) throws SerException {
        return null;
    }


    /**
     * 各类沟通模板
     *
     * @param communicationFormworkDTO 各类沟通模板dto
     * @return class CommunicationFormworkBO
     * @throws SerException
     */
    default List<CommunicationFormworkBO> findListCommuni(CommunicationFormworkDTO communicationFormworkDTO) throws SerException {
        return null;
    }

    /**
     * 添加各类沟通模板
     *
     * @param communicationFormworkTO 各类沟通模板to
     * @throws SerException
     */
    default CommunicationFormworkBO insertCommuni(CommunicationFormworkTO communicationFormworkTO) throws SerException {
        return null;
    }

    /**
     * 编辑各类沟通模板
     *
     * @param communicationFormworkTO 各类沟通模板to
     * @return class CommunicationFormworkBO
     * @throws SerException
     */
    default CommunicationFormworkBO editCommuni(CommunicationFormworkTO communicationFormworkTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除各类沟通模板
     *
     * @param id
     * @throws SerException
     */
    default void removeCommuni(String id) throws SerException {

    }

    /**
     * 根据类型获取信息
     *
     * @param classification 类型
     * @throws SerException
     */
    default CommunicationFormworkBO findByClassification(String classification) throws SerException {
        return null;
    }
    /**
     * 获取所有的类型
     *
     * @throws SerException
     */
    default List<String> findAllType() throws SerException {
        return null;
    }
    /**
     * 通报
     *
     * @throws SerException
     */
    default void notification(String classification,String emailModule) throws SerException {
        return;
    }
}