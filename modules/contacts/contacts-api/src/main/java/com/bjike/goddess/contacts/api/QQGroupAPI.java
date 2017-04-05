package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.QQGroupBO;
import com.bjike.goddess.contacts.dto.QQGroupDTO;
import com.bjike.goddess.contacts.to.QQGroupTO;

import java.util.List;

/**
 * QQ群管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QQGroupAPI {

    /**
     * 保存
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO save(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO update(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO delete(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 关闭QQ群
     *
     * @param to QQ群管理传输对象
     * @return
     * @throws SerException
     */
    default QQGroupBO close(QQGroupTO to) throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto QQ群管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<QQGroupBO> maps(QQGroupDTO dto) throws SerException {
        return null;
    }

}