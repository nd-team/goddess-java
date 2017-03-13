package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.bo.rbac.GroupBO;
import com.bjike.goddess.user.bo.rbac.GroupTreeBO;
import com.bjike.goddess.user.dto.rbac.GroupDTO;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.to.rbac.GroupTO;

import java.util.List;

/**
 * 组业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupSer extends Ser<Group, GroupDTO> {

    /**
     * 逐层查询,逐层加载
     *
     * @param id 组id
     * @return
     */
    default List<GroupTreeBO> treeData(String id) throws SerException {
        return null;
    }

    /**
     * 保存部门
     *
     * @param groupTO
     * @throws SerException
     */
    default GroupBO save(GroupTO groupTO) throws SerException {
        return null;
    }

    /**
     * 更新部门
     *
     * @param groupTO
     * @throws SerException
     */
    default void update(GroupTO groupTO) throws SerException {

    }
}
