package com.bjike.goddess.user.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.user.dto.UserLoginLogDTO;
import com.bjike.goddess.user.entity.UserLoginLog;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 16:47]
 * @Description: [用户登录日志持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserLoginLogRep extends JpaRep<UserLoginLog,UserLoginLogDTO> {


}
