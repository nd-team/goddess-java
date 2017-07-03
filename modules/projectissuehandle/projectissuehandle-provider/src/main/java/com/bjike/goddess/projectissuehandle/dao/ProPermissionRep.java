package com.bjike.goddess.projectissuehandle.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectissuehandle.dto.ProPermissionDTO;
import com.bjike.goddess.projectissuehandle.entity.ProPermission;

/**
 * 问题权限配置持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProPermissionRep extends JpaRep<ProPermission, ProPermissionDTO> {

}