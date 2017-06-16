package com.bjike.goddess.workjoin.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.entity.JoinInfo;

/**
 * 交接资料持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:34 ]
 * @Description: [ 交接资料持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface JoinInfoRep extends JpaRep<JoinInfo, JoinInfoDTO> {

}