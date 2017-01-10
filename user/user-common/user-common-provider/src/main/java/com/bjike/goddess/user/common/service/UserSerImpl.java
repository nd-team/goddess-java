package com.bjike.goddess.user.common.service;

import com.bjike.goddess.user.common.dto.UserDto;
import com.bjike.goddess.dbs.jpa.dto.Condition;
import com.bjike.goddess.dbs.jpa.dto.Restrict;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.user.common.dao.IUserRep;
import com.bjike.goddess.user.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserSerImpl extends ServiceImpl<User, UserDto> implements IUserSer {

    @Autowired
    private IUserRep userRep;

    @Cacheable
    @Override
    public List<User> findAll() throws SerException {
        return super.findAll();
    }

    @Override
    @Transactional
    public User save(User entity) throws SerException {
        return userRep.save(entity);
    }

    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Cacheable
    @Override
    public User findByNickname(String nickname) {
        return userRep.findByUsername(nickname);
    }

    @Cacheable
    @Override
    public User findByPhone(String phone) {
        return userRep.findByPhone(phone);
    }

    @Cacheable
    @Override
    public User findByAccountNumber(String accountNumber) throws SerException {
        UserDto dto = new UserDto();
        List<Condition> conditions = dto.getConditions();
        conditions.add(Restrict.or("username",accountNumber));
        conditions.add(Restrict.or("phone",accountNumber));
        conditions.add(Restrict.or("email",accountNumber));
        return findOne(dto);
    }

    @Override
    public Boolean verifyEmail(String email) throws SerException {

        return null;
    }
}