package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dao.UserRep;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 用户业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service("userSer")
public class UserSer extends ServiceImpl<User, UserDTO> implements UserAPI {

    @Autowired
    private UserRep userRep;

    @Autowired
    private UserDetailAPI userDetailAPI;

    @Cacheable
    @Override
    public List<UserBO> list() throws SerException {
        List<User> users = super.findAll();
        List<UserBO> userBOS = BeanTransform.copyProperties(users, UserBO.class);
        return userBOS;
    }

    @Override
    @Transactional
    public UserBO add(User entity) throws SerException {
        return BeanTransform.copyProperties(entity, UserBO.class);
    }

    @Override
    public UserBO findByUsername(String username) throws SerException {
        User user = userRep.findByUsername(username);
        return BeanTransform.copyProperties(user, UserBO.class);
    }

    @Cacheable
    @Override
    public UserBO findByNickname(String nickname) throws SerException {
        User user = userRep.findByNickname(nickname);
        return BeanTransform.copyProperties(user, UserBO.class);

    }

    @Cacheable
    @Override
    public UserBO findByPhone(String phone) throws SerException {
        User user = null;
        if (StringUtils.isNotBlank(phone)) {
            boolean isPhone = Validator.isPhone(phone);
            if (isPhone) {
                user = userRep.findByPhone(phone);
            } else {
                throw new SerException("手机格式不正确");
            }
        } else {
            throw new SerException("手机号不能为空");
        }

        return BeanTransform.copyProperties(user, UserBO.class);

    }

    @Cacheable
    @Override
    public User findByAccountNumber(String accountNumber) throws SerException {
        UserDTO dto = new UserDTO();
        List<Condition> conditions = dto.getConditions();
        conditions.add(Restrict.or("username", accountNumber));
        conditions.add(Restrict.or("phone", accountNumber));
        conditions.add(Restrict.or("email", accountNumber));
        return findOne(dto);
    }

}