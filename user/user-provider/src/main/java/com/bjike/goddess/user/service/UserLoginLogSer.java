package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.UserLoginLogDTO;
import com.bjike.goddess.user.entity.UserLoginLog;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 14:47]
 * @Description: [用户登录注册业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserLoginLogSer extends ServiceImpl<UserLoginLog, UserLoginLogDTO> implements UserLoginLogAPI {

    /**
     * 每个用户仅保存最近的五条登录记录
     *
     * @param loginLog
     * @return
     * @throws SerException
     */
    @Transactional
    @Override
    public UserLoginLog save(UserLoginLog loginLog) throws SerException {
        UserLoginLogDTO dto = new UserLoginLogDTO();
        dto.getConditions().add(Restrict.eq("user.id",loginLog.getUser().getId()));
        dto.getSorts().put("loginTime",DESC);
        List<UserLoginLog> loginLogs = findByCis(dto);
        if (null != loginLogs && loginLogs.size() >= 5) {
            UserLoginLog old_log = loginLogs.get(4); //更新最旧的数据为最新的
            BeanUtils.copyProperties(loginLog, old_log, "id"); //复制属性忽略id
            super.update(old_log);
            return old_log;
        } else {
            return super.save(loginLog);
        }
    }

    @Transactional
    @Override
    public void save(Collection<UserLoginLog> userLoginLogs) throws SerException {
        for (UserLoginLog log : userLoginLogs) { //批量添加
            this.save(log);
        }
    }

    @Cacheable
    @Override
    public List<UserLoginLog> findByUserId(String userId) throws SerException {
        UserLoginLogDTO dto = new UserLoginLogDTO();
        dto.getConditions().add(Restrict.eq("user.id",userId));
        dto.getSorts().put("loginTime",DESC);
        return findByCis(dto);
    }
}
