package com.bjike.goddess.staffactivity.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffactivity.dto.ActivityStaffListDTO;
import com.bjike.goddess.staffactivity.entity.ActivityStaffList;

/**
 * 活动人员名单持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 11:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivityStaffListRep extends JpaRep<ActivityStaffList, ActivityStaffListDTO> {

}