package com.bjike.goddess.balancecard.dao;

import com.bjike.goddess.balancecard.dto.BalancecardPermissionOperateDTO;
import com.bjike.goddess.balancecard.entity.BalancecardPermissionOperate;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 平衡计分卡权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 平衡计分卡权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BalancecardPermissionOperateRep extends JpaRep<BalancecardPermissionOperate, BalancecardPermissionOperateDTO> {

}