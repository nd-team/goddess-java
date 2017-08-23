package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.entity.BidOpeningInfo;

/**
 * 开标信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.827 ]
 * @Description: [ 开标信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BidOpeningInfoRep extends JpaRep<BidOpeningInfo, BidOpeningInfoDTO> {

}