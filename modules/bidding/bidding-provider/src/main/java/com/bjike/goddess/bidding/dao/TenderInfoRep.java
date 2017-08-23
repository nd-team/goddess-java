package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.entity.TenderInfo;

/**
 * 标书资料持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.337 ]
 * @Description: [ 标书资料持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TenderInfoRep extends JpaRep<TenderInfo, TenderInfoDTO> {

}