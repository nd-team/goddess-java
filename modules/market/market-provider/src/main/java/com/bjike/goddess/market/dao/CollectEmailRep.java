package com.bjike.goddess.market.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.market.dto.CollectEmailDTO;
import com.bjike.goddess.market.entity.CollectEmail;

/**
 * 市场信息邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 市场信息邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectEmailRep extends JpaRep<CollectEmail, CollectEmailDTO> {

}