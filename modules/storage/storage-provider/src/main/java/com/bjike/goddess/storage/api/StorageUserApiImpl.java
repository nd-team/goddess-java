package com.bjike.goddess.storage.api;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.bo.StorageUserBO;
import com.bjike.goddess.storage.entity.StorageUser;
import com.bjike.goddess.storage.service.StorageUserSer;
import com.bjike.goddess.storage.to.StorageUserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 存储模块用户业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-30 09:39 ]
 * @Description: [ 存储模块用户业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("storageUserApiImpl")
public class StorageUserApiImpl implements StorageUserAPI {

    @Autowired
    private StorageUserSer storageUserSer;

    @Override
    public StorageUserBO register(StorageUserTO storageUserTO) throws SerException {
        return storageUserSer.register(storageUserTO);
    }

    @Override
    public String login(StorageUserTO storageUserTO) throws SerException {
        return storageUserSer.login(storageUserTO);
    }

    @Override
    public Boolean signOut() throws SerException {
        return storageUserSer.signOut();
    }

    @Override
    public StorageUserBO getCurrentUser() throws SerException {
        return storageUserSer.getCurrentUser();
    }

    @Override
    public String getCurrentModule() throws SerException {
          return storageUserSer.getCurrentModule();
    }
}