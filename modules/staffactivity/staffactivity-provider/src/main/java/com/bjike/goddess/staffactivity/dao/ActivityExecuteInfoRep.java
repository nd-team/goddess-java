package com.bjike.goddess.staffactivity.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffactivity.dto.ActivityExecuteInfoDTO;
import com.bjike.goddess.staffactivity.entity.ActivityExecuteInfo;

/**
 * 活动执行信息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivityExecuteInfoRep extends JpaRep<ActivityExecuteInfo, ActivityExecuteInfoDTO> {

}