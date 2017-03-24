package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingInfo;

/**
 * 招标信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.994 ]
 * @Description: [ 招标信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingInfoRep extends JpaRep<BiddingInfo, BiddingInfoDTO> {

}