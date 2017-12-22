package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.MobileVersionDTO;
import com.bjike.goddess.organize.entity.MobileVersion;

/**
 * 移动端版本持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-07 01:54 ]
 * @Description: [ 移动端版本持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MobileVersionRep extends JpaRep<MobileVersion, MobileVersionDTO> {

}