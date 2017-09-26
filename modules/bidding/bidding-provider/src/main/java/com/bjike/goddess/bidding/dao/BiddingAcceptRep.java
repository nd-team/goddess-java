package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.bidding.dto.BiddingAcceptDTO;
import com.bjike.goddess.bidding.entity.BiddingAccept;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 招标问题受理和处理持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingAcceptRep extends JpaRep<BiddingAccept, BiddingAcceptDTO> {

}