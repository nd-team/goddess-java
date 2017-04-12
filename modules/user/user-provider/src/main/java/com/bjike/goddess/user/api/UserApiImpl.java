package com.bjike.goddess.user.api;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.user.session.constant.UserCommon;
import com.bjike.goddess.common.user.session.valid_right.LoginUser;
import com.bjike.goddess.common.user.session.valid_right.UserSession;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.to.UserTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 15:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userApiImpl")
public class UserApiImpl implements UserAPI {
    @Autowired
    private UserSer userSer;

    @Autowired
    private RedisClient redis;

    @Override
    public UserBO currentUser() throws SerException {

        Object token = RpcContext.getContext().getAttachment("userToken");
        if (null != token) {
            LoginUser loginUser = UserSession.get(token.toString());
            if (null != loginUser) {
                return BeanTransform.copyProperties(loginUser, UserBO.class);
            } else { //从redis获取
                String loginUser_str = redis.getMap(UserCommon.LOGIN_USER, token.toString());
                if (StringUtils.isNotBlank(loginUser_str)) {
                    loginUser = JSON.parseObject(loginUser_str, LoginUser.class);
                    return BeanTransform.copyProperties(loginUser, UserBO.class);
                }
            }

            throw new SerException("登录已过期!");
        }
        throw new SerException("notLogin");
    }


    @Override
    public UserBO currentUser(String userToken) throws SerException {
        if (null == userToken) {
            throw new SerException("用户未登录!");
        } else {
            // 从会话session获取
            LoginUser loginUser = UserSession.get(userToken.toString());
            if (null == loginUser) {
                // 从redis缓存获取
                String loginUser_str = redis.getMap(UserCommon.LOGIN_USER, userToken.toString());
                if (StringUtils.isNotBlank(loginUser_str)) {
                    loginUser = JSON.parseObject(loginUser_str, LoginUser.class);
                    return BeanTransform.copyProperties(loginUser, UserBO.class);
                }
            } else {
                return BeanTransform.copyProperties(loginUser, UserBO.class);
            }
            throw new SerException("登录已过期!");
        }
    }

    @Override
    public UserBO add(UserTO userTO) throws SerException {
        return userSer.add(userTO);
    }

    @Override
    public void update(UserTO userTO) throws SerException {
        userSer.update(userTO);
    }

    @Override
    public UserBO findByUsername(String username) throws SerException {
        return userSer.findByUsername(username);
    }

    @Override
    public UserBO findByNickname(String nickname) throws SerException {
        return userSer.findByNickname(nickname);
    }

    @Override
    public UserBO findByPhone(String phone) throws SerException {
        return userSer.findByPhone(phone);
    }

    @Override
    public UserBO findByAccountNumber(String accountNumber) throws SerException {
        return userSer.findByAccountNumber(accountNumber);
    }

    @Override
    public List<UserBO> findByCis(UserDTO dto) throws SerException {
        return BeanTransform.copyProperties(userSer.findByCis(dto), UserBO.class);
    }

    @Override
    public List<UserBO> findOne(UserDTO dto) throws SerException {
        return BeanTransform.copyProperties(userSer.findOne(dto), UserBO.class);
    }

    @Override
    public List<UserBO> findByGroup(String... groups) throws SerException {
        return userSer.findByGroup(groups);
    }

    @Override
    public List<UserBO> findAllUser() throws SerException {
        return userSer.findAllUser();
    }
}
