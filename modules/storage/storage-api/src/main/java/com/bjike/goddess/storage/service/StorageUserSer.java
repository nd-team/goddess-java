package com.bjike.goddess.storage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.storage.bo.StorageUserBO;
import com.bjike.goddess.storage.dto.StorageUserDTO;
import com.bjike.goddess.storage.entity.StorageUser;
import com.bjike.goddess.storage.to.StorageUserTO;

/**
 * 存储模块用户业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-30 09:39 ]
 * @Description: [ 存储模块用户业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StorageUserSer extends Ser<StorageUser, StorageUserDTO> {
    /**
     * 注册
     *
     * @param storageUserTO
     * @return
     */
    default StorageUserBO register(StorageUserTO storageUserTO) throws SerException {
        return null;
    }

    /**
     * 登录
     *
     * @param storageUserTO
     * @return
     */
    default String login(StorageUserTO storageUserTO) throws SerException {
        return null;
    }

    /**
     * 退出
     *
     * @return
     */
    default Boolean signOut(String storageToken ) throws SerException {
        return null;
    }

    /**
     * 获取当前用户
     * @return
     * @throws SerException
     */
    default StorageUserBO getCurrentUser(String storageToken) throws SerException{
        return null;
    }

    /**
     * 获取当前登录模块名
     * @return
     * @throws SerException
     */
    default String getCurrentModule(String storageToken) throws SerException{
        return null;
    }

    /**
     * 获取当前登录模块名
     * @return
     * @throws SerException
     */
    default String getCurrentSysNO(String storageToken) throws SerException{
        return null;
    }

}