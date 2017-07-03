package com.bjike.goddess.festival.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.festival.dto.WelfareDTO;
import com.bjike.goddess.festival.entity.Welfare;

/**
 * 节假日礼品福利持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:17 ]
 * @Description: [ 节假日礼品福利持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WelfareRep extends JpaRep<Welfare, WelfareDTO> {

}