package com.bjike.goddess.intromanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.intromanage.dto.IndividualDisplayUserDTO;
import com.bjike.goddess.intromanage.entity.IndividualDisplayUser;

/**
 * 个人简介显示用户名称集合持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 04:12 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndividualDisplayUserRep extends JpaRep<IndividualDisplayUser, IndividualDisplayUserDTO> {

}