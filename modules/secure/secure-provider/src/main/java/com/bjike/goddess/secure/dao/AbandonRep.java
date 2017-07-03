package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.AbandonDTO;
import com.bjike.goddess.secure.entity.Abandon;

/**
 * 放弃购买名单持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:52 ]
 * @Description: [ 放弃购买名单持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AbandonRep extends JpaRep<Abandon, AbandonDTO> {

}