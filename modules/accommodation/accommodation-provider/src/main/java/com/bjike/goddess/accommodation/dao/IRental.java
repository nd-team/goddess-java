package com.bjike.goddess.accommodation.dao;

import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-10 10:16]
 * @Description: [租房信息  持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IRental extends JpaRep<Rental,RentalDTO>{
}
