package com.bjike.goddess.user.common.dao;

import com.bjike.goddess.dbs.jpa.dao.MyRep;
import com.bjike.goddess.user.common.entity.Position;
import com.bjike.goddess.user.common.dto.PositionDto;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: [职位持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IPositionRep extends MyRep<Position, PositionDto> {

}
