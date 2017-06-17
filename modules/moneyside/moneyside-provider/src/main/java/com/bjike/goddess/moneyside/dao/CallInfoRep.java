package com.bjike.goddess.moneyside.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.entity.CallInfo;

/**
 * 招投信息列表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CallInfoRep extends JpaRep<CallInfo, CallInfoDTO> {

}