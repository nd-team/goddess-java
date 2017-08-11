package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.feedback.bo.ConnectBO;
import com.bjike.goddess.feedback.dto.ConnectDTO;
import com.bjike.goddess.feedback.entity.Connect;
import com.bjike.goddess.feedback.to.ConnectTO;

import java.util.List;

/**
 * 各类沟通模板业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConnectSer extends Ser<Connect, ConnectDTO> {
    /**
     * 各类沟通模板列表总条数
     */
    default Long count(ConnectDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个各类沟通模板
     *
     * @return class ConnectBO
     */
    default ConnectBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 各类沟通模板
     *
     * @param dto 各类沟通模板dto
     * @return class ConnectBO
     * @throws SerException
     */
    default List<ConnectBO> list(ConnectDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加各类沟通模板
     *
     * @param to 各类沟通模板数据to
     * @return class ConnectBO
     * @throws SerException
     */
    default ConnectBO insert(ConnectTO to) throws SerException {
        return null;
    }

    /**
     * 编辑各类沟通模板
     *
     * @param to 各类沟通模板数据to
     * @return class ConnectBO
     * @throws SerException
     */
    default ConnectBO edit(ConnectTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除各类沟通模板
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 获取所有类型
     *
     * @return String
     * @throws SerException
     */
    default List<String> getSort() throws SerException {
        return null;
    }
    /**
     * 根据分类获取各类沟通模板
     *
     * @param sorting
     * @return ConnectBO
     * @throws SerException
     */
    default List<ConnectBO> getConnect(String sorting) throws SerException {
        return null;
    }
}