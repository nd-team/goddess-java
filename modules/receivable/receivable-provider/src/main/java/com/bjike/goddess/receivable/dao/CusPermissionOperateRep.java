package com.bjike.goddess.receivable.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.receivable.dto.CusPermissionOperateDTO;
import com.bjike.goddess.receivable.entity.CusPermissionOperate;

/**
 * 回款管理权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 回款管理权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CusPermissionOperateRep extends JpaRep<CusPermissionOperate, CusPermissionOperateDTO> {

}