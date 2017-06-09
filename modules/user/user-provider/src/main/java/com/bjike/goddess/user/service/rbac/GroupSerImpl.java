package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.rbac.GroupBO;
import com.bjike.goddess.user.dto.rbac.GroupDTO;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.to.rbac.GroupTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Autowired
    private UserSer userSer;

    @Override
    public List<GroupBO> treeData(String id) throws SerException {
        GroupDTO dto = new GroupDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        dto.getConditions().add(Restrict.eq(SYS_NO, userSer.currentSysNO()));

        List<Group> groups = super.findByCis(dto);

        return BeanTransform.copyProperties(groups, GroupBO.class);
    }

    @Transactional
    @Override
    public void remove(String id) throws SerException {
        List<Group> children = getChild(id);
        if (null != children && children.size() > 0) {
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }

        Group department = super.findById(id);
        if (null != department) {
            Group parent = department.getParent();
            if (null != parent) {
                children = getChild(parent.getId());
                parent.setHasChild(children.size() != 0);
                super.update(parent);
            }
            super.remove(id);
        } else {
            throw new SerException("该记录不存在");
        }

    }

    @Transactional
    @Override
    public GroupBO save(GroupTO groupTO) throws SerException {
        String sysNO = userSer.currentSysNO();
        Group group = BeanTransform.copyProperties(groupTO, Group.class, true);
        group.setSystemNO(sysNO);

        if (StringUtils.isNotBlank(groupTO.getParentId())) {
            GroupDTO dto = new GroupDTO();
            dto.getConditions().add(Restrict.eq("id", groupTO.getParentId()));
            Group parent = findOne(dto);
            if (null != parent) {
                group.setParent(parent);
                parent.setHasChild(true); //更新父类子节点字段为true
                super.update(parent);
            }
        }
        group.setHasChild(false);
        super.save(group);
        GroupBO bo = BeanTransform.copyProperties(group, GroupBO.class);
        return bo;
    }


    @Transactional
    @Override
    public void update(GroupTO groupTO) throws SerException {
        Group group = super.findById(groupTO.getId());
        Group old_parent = group.getParent();
        String parentId = groupTO.getParentId();
        Group new_parent = null;
        if (StringUtils.isNotBlank(parentId)) {
            GroupDTO dto = new GroupDTO();
            dto.getConditions().add(Restrict.eq("id", groupTO.getParentId()));
            new_parent = super.findOne(dto);
        }

        //更新
        group.setModifyTime(LocalDateTime.now());
        group.setName(groupTO.getName());
        group.setParent(new_parent);
        group.setModifyTime(LocalDateTime.now());
        super.update(group);


        if (null != new_parent) {
            new_parent.setHasChild(true);
            super.update(new_parent);
        }

        /**
         * 维护该节点与其父类是否有子节点
         */
        if (null != old_parent) { //以前有父节点,删除父节点后,检查其未更改前的父节点是否还存在子节点
            List<Group> children = getChild(old_parent.getId()); //查询以未更改前其父类的所有子节点
            old_parent.setHasChild(children.size() != 0);
            super.update(old_parent);
        }
    }


    private List<Group> getChild(String id) throws SerException {
        GroupDTO dto = new GroupDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Group> children = findByCis(dto);
        return children;
    }
}
