package com.bjike.goddess.moneyside.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyside.dto.CustomerInfoCollectDTO;
import com.bjike.goddess.moneyside.entity.CustomerInfoCollect;

/**
 * 客户信息汇总持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerInfoCollectRep extends JpaRep<CustomerInfoCollect, CustomerInfoCollectDTO> {

}