package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.PositionDutyDTO;
import com.bjike.goddess.recruit.entity.PositionDuty;

/**
 * 公司岗位分类岗位职责持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 09:31 ]
 * @Description: [ 公司岗位分类岗位职责持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PositionDutyRep extends JpaRep<PositionDuty, PositionDutyDTO> {

}