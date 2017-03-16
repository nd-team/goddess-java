package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.rbac.GroupUserDTO;
import com.bjike.goddess.user.entity.rbac.GroupUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组用户业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 14:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class GroupUserSerImpl extends ServiceImpl<GroupUser, GroupUserDTO> implements GroupUserSer {

    @Override
    public List<GroupUser> findByUserId(String user_id) throws SerException {
        GroupUserDTO dto = new GroupUserDTO();
        dto.getConditions().add(Restrict.eq("user.id", user_id));
        dto.getConditions().add(Restrict.eq("group.status", Status.THAW));
        return findByCis(dto);
    }
}
