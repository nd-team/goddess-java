package com.bjike.goddess.storage.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.token.TokenUtil;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.storage.bo.StorageUserBO;
import com.bjike.goddess.storage.constant.StorageCommon;
import com.bjike.goddess.storage.dto.StorageUserDTO;
import com.bjike.goddess.storage.entity.StorageUser;
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
        String token;
        if (null != storageUser) {
            try {
                if (PasswordHash.validatePassword(storageUserTO.getPassword(), storageUser.getPassword())) {
                    token = redis.get(storageUser.getId()); //通过用户id获取token
                    if (StringUtils.isNotBlank(token)) { //是否已登录过
                        String str_loginUser = redis.getMap(StorageCommon.STORAGE_USER, token);//通过获取token用户信息
                        if (StringUtils.isNotBlank(str_loginUser)) {
                            redis.save(storageUser.getId(), token, 60 * 60 * 24 * 1000); //更新登录失效时间
                            redis.appendToMap(StorageCommon.STORAGE_USER, storageUser.getId(), str_loginUser, 60 * 60 * 24 * 1000);//更新登录失效时间
                            return token;
                        } else {
                            return login(storageUser);
                        }
                    } else {
                        return login(storageUser);
                    }
                } else {
                    throw new SerException("登录账号或者密码错误！");
                }
            } catch (Exception e) {
                throw new SerException("登录账号或者密码错误！");
            }

        } else {
            throw new SerException("登录账号或者密码错误！");
        }
    }

    private String login(StorageUser storageUser) throws SerException {
        String token = TokenUtil.create("192.168.0.148", storageUser.getAccount());
        StorageUserBO loginUser = BeanTransform.copyProperties(storageUser, StorageUserBO.class);
        redis.save(storageUser.getId(), token);
        String str_loginUser = JSON.toJSONString(loginUser);
        redis.appendToMap(StorageCommon.STORAGE_USER, token, str_loginUser, 60 * 60 * 24 * 1000);
        return token;
    }

    @Override
    public Boolean signOut() throws SerException {
        StorageUserBO userBO = getCurrentUser();
        redis.remove(userBO.getId());
        redis.removeMap(StorageCommon.STORAGE_USER, userBO.getId());
        return true;
    }


    @Override
    public StorageUserBO getCurrentUser() throws SerException {
        Object obj = RpcContext.getContext().getAttachment("storageToken");
        if (null != obj) {
            String str_storage = redis.getMap(StorageCommon.STORAGE_USER, obj.toString());
            if (StringUtils.isNotBlank(str_storage)) {
                return JSON.parseObject(str_storage, StorageUserBO.class);

            } else {
                throw new SerException("登录已失效！");
            }
        }
        throw new SerException("存储用户未登录！");
    }

    @Override
    public String getCurrentModule() throws SerException {
        return this.getCurrentUser().getModuleName();
    }
}