package com.bjike.goddess.system.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.system.dto.PlatformClassifyDTO;
import com.bjike.goddess.system.entity.PlatformClassify;

/**
 * 平台分类持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 01:44 ]
 * @Description: [ 平台分类持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PlatformClassifyRep extends JpaRep<PlatformClassify, PlatformClassifyDTO> {

}