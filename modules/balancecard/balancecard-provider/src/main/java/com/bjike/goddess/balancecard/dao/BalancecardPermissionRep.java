package com.bjike.goddess.balancecard.dao;

import com.bjike.goddess.balancecard.dto.BalancecardPermissionDTO;
import com.bjike.goddess.balancecard.entity.BalancecardPermission;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 平衡计分卡权限配置持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 平衡计分卡权限配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BalancecardPermissionRep extends JpaRep<BalancecardPermission, BalancecardPermissionDTO> {

}