package com.bjike.goddess.problemhandle.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.problemhandle.dto.ProPermissionOperateDTO;
import com.bjike.goddess.problemhandle.entity.ProPermissionOperate;

/**
 * 问题权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 02:12 ]
 * @Description: [ 问题权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProPermissionOperateRep extends JpaRep<ProPermissionOperate, ProPermissionOperateDTO> {

}