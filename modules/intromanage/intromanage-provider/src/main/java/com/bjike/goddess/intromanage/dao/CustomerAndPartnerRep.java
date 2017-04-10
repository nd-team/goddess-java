package com.bjike.goddess.intromanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.intromanage.dto.CustomerAndPartnerDTO;
import com.bjike.goddess.intromanage.entity.CustomerAndPartner;

/**
 * 客户及合作伙伴持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerAndPartnerRep extends JpaRep<CustomerAndPartner, CustomerAndPartnerDTO> {

}