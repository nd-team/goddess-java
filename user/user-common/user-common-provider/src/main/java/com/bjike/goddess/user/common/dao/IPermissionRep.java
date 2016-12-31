package com.bjike.goddess.user.common.dao;

import com.bjike.goddess.dbs.jpa.dao.MyRep;
import com.bjike.goddess.user.common.entity.Permission;
import com.bjike.goddess.user.common.dto.PermissionDto;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [认证资源持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IPermissionRep extends MyRep<Permission, PermissionDto> {


}
