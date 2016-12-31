package com.bjike.goddess.user.common.dao;

import com.bjike.goddess.user.common.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [权限认证持久化接口实现类, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public abstract class PermissionRepImpl implements IPermissionRep {

    @Autowired
    List<Permission> findPermission(String rootId) {
        return null;
    }
}
