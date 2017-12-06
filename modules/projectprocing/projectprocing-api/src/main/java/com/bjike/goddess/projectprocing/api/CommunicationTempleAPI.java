package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.CommunicationTempleBO;
import com.bjike.goddess.projectprocing.dto.CommunicationTempleDTO;
import com.bjike.goddess.projectprocing.to.CommunicationTempleTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;

import java.util.List;

/**
 * 各类沟通交流模板业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-25 05:41 ]
 * @Description: [ 各类沟通交流模板业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommunicationTempleAPI {

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
     * 各类沟通交流模板总条数
     */
    default Long countCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取各类沟通交流模板
     *
     * @return class CommunicationTempleBO
     */
    default CommunicationTempleBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 各类沟通交流模板列表
     *
     * @return class CommunicationTempleBO
     */
    default List<CommunicationTempleBO> listCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param communicationTempleTO 各类沟通交流模板
     * @return class NodeHeadersCustomBO
     */
    default CommunicationTempleBO addCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param communicationTempleTO 各类沟通交流模板
     * @return class NodeHeadersCustomBO
     */
    default CommunicationTempleBO editCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteNode(String id) throws SerException {
        return;
    }
}