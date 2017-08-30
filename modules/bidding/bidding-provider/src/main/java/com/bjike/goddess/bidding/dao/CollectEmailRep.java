package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.bidding.dto.CollectEmailDTO;
import com.bjike.goddess.bidding.entity.CollectEmail;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
 * 招投标信息邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 招投标信息邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectEmailRep extends JpaRep<CollectEmail, CollectEmailDTO> {

}