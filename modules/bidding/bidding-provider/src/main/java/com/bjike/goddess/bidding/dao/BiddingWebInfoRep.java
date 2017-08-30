package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;

/**
 * 招投标网站信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.330 ]
 * @Description: [ 招投标网站信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingWebInfoRep extends JpaRep<BiddingWebInfo, BiddingWebInfoDTO> {

}