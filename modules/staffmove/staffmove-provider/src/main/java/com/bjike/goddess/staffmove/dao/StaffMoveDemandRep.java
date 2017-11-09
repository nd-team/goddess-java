package com.bjike.goddess.staffmove.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffmove.dto.StaffMoveDemandDTO;
import com.bjike.goddess.staffmove.entity.StaffMoveDemand;

/**
 * 人员调动需求持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:03 ]
 * @Description: [ 人员调动需求持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffMoveDemandRep extends JpaRep<StaffMoveDemand, StaffMoveDemandDTO> {

}