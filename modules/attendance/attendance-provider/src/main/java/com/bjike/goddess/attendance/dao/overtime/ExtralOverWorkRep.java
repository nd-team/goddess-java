package com.bjike.goddess.attendance.dao.overtime;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDTO;
import com.bjike.goddess.attendance.entity.overtime.ExtralOverWork;

/**
 * 补班设置持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExtralOverWorkRep extends JpaRep<ExtralOverWork, ExtralOverWorkDTO> {

}