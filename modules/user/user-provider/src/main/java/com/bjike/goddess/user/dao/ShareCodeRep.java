package com.bjike.goddess.user.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.user.dto.ShareCodeDTO;
import com.bjike.goddess.user.entity.ShareCode;

/**
 * 首页导航持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ShareCodeRep extends JpaRep<ShareCode, ShareCodeDTO> {

}