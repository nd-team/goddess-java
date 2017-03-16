package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.GroupBO;
import com.bjike.goddess.user.bo.rbac.GroupTreeBO;
import com.bjike.goddess.user.to.rbac.GroupTO;

import java.util.List;

/**
 * 对外提供组业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 13:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupAPI {

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
     * 保存组
     * @param groupTO
     * @return
     * @throws SerException
     */
    default GroupBO save(GroupTO groupTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除组
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {
    }

    /**
     * 更新部门
     * @param groupTO
     * @throws SerException
     */
    default void update(GroupTO groupTO)throws SerException{

    }
}
