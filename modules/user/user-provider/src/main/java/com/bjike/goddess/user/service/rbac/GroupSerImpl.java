package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.dto.rbac.GroupDTO;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.bo.rbac.GroupBO;
import com.bjike.goddess.user.bo.rbac.GroupTreeBO;
import com.bjike.goddess.user.to.rbac.GroupTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 组业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class GroupSerImpl extends ServiceImpl<Group, GroupDTO> implements GroupSer {
    @Override
    public List<GroupTreeBO> treeData(String id) throws SerException {
        GroupDTO dto = new GroupDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Group> groups = super.findByCis(dto);
        List<GroupTreeBO> groupTreeBOS = new ArrayList<>(groups.size());
        groups.stream().forEach(group -> {
            GroupTreeBO bo = new GroupTreeBO();
            bo.setName(group.getName());
            bo.setId(group.getId());
            bo.setParent(null == group.getParent());
            groupTreeBOS.add(bo);
        });
        return groupTreeBOS;
    }

    @Override
    public void remove(String id) throws SerException {
        GroupDTO dto = new GroupDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id)); //查找根节点
        List<Group> children = super.findByCis(dto);
        if (null != children && children.size() > 0) {
            throw new SerException("该节点存在子节点,请先删除子节点!");
        }
        super.remove(id);
    }

    @Transactional
    @Override
    public GroupBO save(GroupTO groupTO) throws SerException {
        Group group = BeanTransform.copyProperties(groupTO, Group.class, true);
        super.save(group);
        GroupBO bo = BeanTransform.copyProperties(group, GroupBO.class);
        bo.setId(group.getId());//复制id
        return bo;
    }

    @Override
    public void update(GroupTO groupTO) throws SerException {
        Group group = super.findById(groupTO.getId());
        BeanTransform.copyProperties(groupTO,group,true);
        group.setModifyTime(LocalDateTime.now());
        super.update(group);
    }
}
