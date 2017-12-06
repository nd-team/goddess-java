package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.NodeHeadersCustomTO;

import java.util.List;

/**
 * 节点表头定制业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:47 ]
 * @Description: [ 节点表头定制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NodeHeadersCustomAPI {

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
     * 节点表头定制总条数
     */
    default Long countNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取节点表头定制
     *
     * @return class NodeHeadersCustomBO
     */
    default NodeHeadersCustomBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 节点表头定制列表
     *
     * @return class NodeHeadersCustomBO
     */
    default List<NodeHeadersCustomBO> listNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param nodeHeadersCustomTO 节点表头定制
     * @return class NodeHeadersCustomBO
     */
    default NodeHeadersCustomBO addNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param nodeHeadersCustomTO 节点表头定制
     * @return class NodeHeadersCustomBO
     */
    default NodeHeadersCustomBO editNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
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

    /**
     * 根据外包单位获取所有表头字段
     *
     * @return class NodeHeadersCustomBO
     */
    default List<NodeHeadersCustomBO> getNodeByOutUnit(String outUnit) throws SerException {
        return null;
    }
    /**
     * 获取对应工作进度管理id对应的数据
     *
     * @return class NodeHeadersCustomBO
     */
    default List<NodeHeadersCustomBO> getByManageId(String passManageId) throws SerException {
        return null;
    }
    /**
     * 根据工作进度管理id删除所有对应的数据
     *
     */
    default void removeByManageId(String passManageId) throws SerException {
        return;
    }
    /**
     * 每天定时检测到哪个节点
     *
     */
    default void checkNodeCutoff() throws SerException {
        return;
    }
}