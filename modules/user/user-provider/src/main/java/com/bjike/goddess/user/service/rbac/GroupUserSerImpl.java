package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.bo.UserSimpleBO;
import com.bjike.goddess.user.bo.rbac.GroupUserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.dto.rbac.GroupDTO;
import com.bjike.goddess.user.dto.rbac.GroupUserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.entity.rbac.GroupUser;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.to.rbac.GroupUserTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GroupSer groupSer;
    @Autowired
    private UserSer userSer;

    @Override
    public List<GroupUser> findByUserId(String user_id) throws SerException {
        GroupUserDTO dto = new GroupUserDTO();
        dto.getConditions().add(Restrict.eq("user.id", user_id));
        dto.getConditions().add(Restrict.eq("group.status", Status.THAW));
        return findByCis(dto);
    }

    @Override
    public GroupUserBO saveByTO(GroupUserTO groupUserTO) throws SerException {
        String groupId =groupUserTO.getGroupId();
        String userId =groupUserTO.getUserId();
        GroupUserDTO dto = new GroupUserDTO();
        dto.getConditions().add(Restrict.eq("group.id",groupId));
        dto.getConditions().add(Restrict.eq("user.id",userId));
        if(null == super.findOne(dto)){
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.getConditions().add(Restrict.eq("id",groupId));
            Group group = groupSer.findOne(groupDTO);
            if(null==group){
                throw  new SerException("组不存在!");
            }
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq("id",userId));
            User user = userSer.findOne(userDTO);
            if(null==user){
                throw  new SerException("用户不存在!");
            }
            GroupUser groupUser = new GroupUser();
            groupUser.setUser(user);
            groupUser.setGroup(group);
            super.save(groupUser);
            GroupUserBO groupUserBO = new GroupUserBO();
            groupUserBO.setUserId(userId);
            groupUserBO.setGroupId(groupId);
            groupUserBO.setId(groupUser.getId());
            return  groupUserBO;
        }else {
            throw new SerException("该组已存在该用户!");
        }
    }
}
