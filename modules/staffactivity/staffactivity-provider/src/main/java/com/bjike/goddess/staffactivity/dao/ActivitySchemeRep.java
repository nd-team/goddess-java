package com.bjike.goddess.staffactivity.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.entity.ActivityScheme;

/**
 * 活动方案持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivitySchemeRep extends JpaRep<ActivityScheme, ActivitySchemeDTO> {

}