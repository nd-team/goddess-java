package com.bjike.goddess.attendance.dao.overtime;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.overtime.OverWorkDTO;
import com.bjike.goddess.attendance.entity.overtime.OverWork;

/**
 * 加班持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OverWorkRep extends JpaRep<OverWork, OverWorkDTO> {

}