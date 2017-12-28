package com.bjike.goddess.system.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.entity.Auswer;

/**
 * 答案持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AuswerRep extends JpaRep<Auswer, AuswerDTO> {

}