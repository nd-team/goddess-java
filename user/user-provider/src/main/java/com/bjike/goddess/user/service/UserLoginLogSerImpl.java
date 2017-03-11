package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.dto.UserLoginLogDTO;
import com.bjike.goddess.user.entity.UserLoginLog;
import com.bjike.goddess.user.bo.UserLoginLogBO;
import com.bjike.goddess.user.to.UserLoginLogTO;
import com.bjike.goddess.user.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 用户登录注册业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-28 14:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserLoginLogSerImpl extends ServiceImpl<UserLoginLog, UserLoginLogDTO> implements UserLoginLogSer {
    @Autowired
    private UserSer userAPI;

    /**
     * 每个用户仅保存最近的五条登录记录
     *
     * @param loginLogTO
     * @return
     * @throws SerException
     */
    @Transactional
    @Override
    public void saveLoginLog(UserLoginLogTO loginLogTO) throws SerException {
        UserLoginLogDTO dto = new UserLoginLogDTO();
        String userId = "";
        dto.getConditions().add(Restrict.eq("user.id", userId));
        dto.getSorts().add("loginTime=DESC");
        List<UserLoginLog> loginLogs = findByCis(dto);
        if (null != loginLogs && loginLogs.size() >= 5) {
            UserLoginLog old_log = loginLogs.get(4); //更新最旧的数据为最新的
            BeanTransform.copyProperties(loginLogTO, old_log, "id"); //复制属性忽略id
            super.update(old_log);
        } else {
            UserLoginLog loginLog = BeanTransform.copyProperties(loginLogTO,UserLoginLog.class);
            loginLog.setUser(userAPI.findById(loginLog.getUser().getId()));
            super.save(loginLog);
        }
    }

    @Transactional
    @Override
    public void save(Collection<UserLoginLog> userLoginLogs) throws SerException {
        for (UserLoginLog log : userLoginLogs) { //批量添加
            this.save(log);
        }
    }

    @Override
    public List<UserLoginLogBO> findByUserId(String userId) throws SerException {
        UserLoginLogDTO dto = new UserLoginLogDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        dto.getSorts().add("loginTime=DESC");
        return BeanTransform.copyProperties(super.findByCis(dto), UserLoginLogBO.class);
    }

    @Override
    public List<UserLoginLog> findAll() throws SerException {
        List<UserLoginLog> logs = super.findAll();
        return logs;
    }

}
