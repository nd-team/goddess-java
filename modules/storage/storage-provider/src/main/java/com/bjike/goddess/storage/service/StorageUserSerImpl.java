package com.bjike.goddess.storage.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.token.TokenUtil;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.storage.bo.StorageUserBO;
import com.bjike.goddess.storage.constant.StorageCommon;
import com.bjike.goddess.storage.dto.StorageUserDTO;
import com.bjike.goddess.storage.entity.StorageUser;
import com.bjike.goddess.storage.session.LoginUser;
import com.bjike.goddess.storage.session.StorageSession;
import com.bjike.goddess.storage.to.StorageUserTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 存储模块用户业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-30 09:39 ]
 * @Description: [ 存储模块用户业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "storageSerCache")
@Service
public class StorageUserSerImpl extends ServiceImpl<StorageUser, StorageUserDTO> implements StorageUserSer {

    @Autowired
    private RedisClient redis;

    @Override
    public StorageUserBO register(StorageUserTO storageUserTO) throws SerException {
        StorageUserDTO dto = new StorageUserDTO();
        dto.getConditions().add(Restrict.or("moduleName", storageUserTO.getModuleName()));
        dto.getConditions().add(Restrict.or("account", storageUserTO.getAccount()));
        StorageUser storageUser = null;
        if (null == super.findOne(dto)) {
            storageUser = BeanTransform.copyProperties(storageUserTO, StorageUser.class);
            storageUser.setStatus(Status.THAW);
            try {
                storageUser.setPassword(PasswordHash.createHash(storageUser.getPassword()));
            } catch (Exception e) {
                throw new SerException(e.getMessage());
            }
            storageUser = super.save(storageUser);
        } else {
            throw new SerException("账号名或者模块名已存在！");
        }
        return BeanTransform.copyProperties(storageUser, StorageUserBO.class);
    }

    @Override
    public String login(StorageUserTO storageUserTO) throws SerException {
        StorageUserDTO dto = new StorageUserDTO();
        dto.getConditions().add(Restrict.eq("account", storageUserTO.getAccount()));
        StorageUser storageUser = findOne(dto);
        if (null != storageUser) {
            try {
                if (PasswordHash.validatePassword(storageUserTO.getPassword(), storageUser.getPassword())) {
                    return login(storageUser);
                }
                throw new SerException("登录账号或者密码错误！");

            } catch (Exception e) {
                throw new SerException("登录账号或者密码错误！");
            }

        } else {
            throw new SerException("登录账号或者密码错误！");
        }
    }

    private String login(StorageUser storageUser) throws SerException {
        String token = TokenUtil.create("192.168.0.1", storageUser.getAccount());
        LoginUser loginUser = BeanTransform.copyProperties(storageUser, LoginUser.class);
        StorageSession.put(token, loginUser);
        String str_loginUser = JSON.toJSONString(loginUser);
        redis.appendToMap(StorageCommon.LOGIN_USER, token, str_loginUser, StorageCommon.LOGIN_TIMEOUT);
        return token;
    }

    @Override
    public Boolean signOut(String storageToken) throws SerException {
        StorageUserBO userBO = getCurrentUser();
        StorageSession.remove(storageToken);
        redis.removeMap(StorageCommon.LOGIN_USER, userBO.getId());
        return true;
    }


    @Override
    public StorageUserBO getCurrentUser() throws SerException {
        String token = RpcTransmit.getStorageToken();
        if (StringUtils.isNotBlank(token)) {
            LoginUser loginUser = StorageSession.get(token);
            if (null != loginUser) {
                return BeanTransform.copyProperties(loginUser, StorageUserBO.class);
            } else {
                String login_user = redis.getMap(StorageCommon.LOGIN_USER, token);
                if (StringUtils.isNotBlank(login_user)) {
                    loginUser = JSON.parseObject(login_user, LoginUser.class);
                    return BeanTransform.copyProperties(loginUser, StorageUserBO.class);
                } else {
                    throw new SerException("登录已失效！");
                }
            }
        }
        throw new SerException("存储用户未登录！");
    }

    @Override
    public String getCurrentModule() throws SerException {
        return this.getCurrentUser().getModuleName();
    }
}