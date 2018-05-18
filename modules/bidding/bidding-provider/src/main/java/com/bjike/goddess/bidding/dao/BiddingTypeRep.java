package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.bidding.dto.BiddingTypeDTO;
import com.bjike.goddess.bidding.entity.BiddingType;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 招投标类型持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:24 ]
 * @Description: [ 招投标类型持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingTypeRep extends JpaRep<BiddingType, BiddingTypeDTO> {

}