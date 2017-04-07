package com.bjike.goddess.intromanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.intromanage.dto.FirmDisplayUserDTO;
import com.bjike.goddess.intromanage.entity.FirmDisplayUser;

/**
 * 公司简介显示用户名称集合持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 09:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirmDisplayUserRep extends JpaRep<FirmDisplayUser, FirmDisplayUserDTO> {

}