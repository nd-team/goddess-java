package com.bjike.goddess.feedback.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.feedback.dto.ConnectDTO;
import com.bjike.goddess.feedback.entity.Connect;

/**
 * 各类沟通模板持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConnectRep extends JpaRep<Connect, ConnectDTO> {

}