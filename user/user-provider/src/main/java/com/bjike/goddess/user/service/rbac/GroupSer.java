package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.dto.rbac.GroupDTO;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.sto.DepartmentSTO;
import com.bjike.goddess.user.sto.rbac.GroupSTO;
import com.bjike.goddess.user.sto.rbac.GroupTreeSTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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
@Service("groupSer")
public class GroupSer extends ServiceImpl<Group, GroupDTO> implements GroupAPI {
    @Override
    public List<GroupTreeSTO> treeData(String id) throws SerException {
        GroupDTO dto = new GroupDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Group> groups = super.findByCis(dto);
        List<GroupTreeSTO> groupTreeSTOS = new ArrayList<>(groups.size());
        groups.stream().forEach(group -> {
            GroupTreeSTO sto = new GroupTreeSTO();
            sto.setName(group.getName());
            sto.setId(group.getId());
            sto.setParent(null == group.getParent());
            groupTreeSTOS.add(sto);
        });
        return groupTreeSTOS;
    }

    @Override
    public void delete(String id) throws SerException {
        GroupDTO dto = new GroupDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id)); //查找根节点
        List<Group> children = super.findByCis(dto);
        if (null != children && children.size() > 0) {
            throw new SerException("该节点存在子节点,请先删除子节点!");
        }
        super.delete(id);
    }

    @Override
    public GroupSTO saveGroup(Group group) throws SerException {
        return BeanTransform.copyProperties(super.save(group),DepartmentSTO.class);
    }
}
