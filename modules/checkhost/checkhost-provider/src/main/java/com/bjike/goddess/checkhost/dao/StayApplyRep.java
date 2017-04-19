package com.bjike.goddess.checkhost.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.checkhost.dto.StayApplyDTO;
import com.bjike.goddess.checkhost.entity.StayApply;

/**
 * 住宿申请持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StayApplyRep extends JpaRep<StayApply, StayApplyDTO> {

}