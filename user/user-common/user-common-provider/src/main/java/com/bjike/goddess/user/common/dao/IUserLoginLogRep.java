package com.bjike.goddess.user.common.dao;

import com.bjike.goddess.dbs.jpa.dao.MyRep;
import com.bjike.goddess.user.common.entity.UserLoginLog;
import com.bjike.goddess.user.common.dto.UserLoginLogDto;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 16:47]
 * @Description: [用户登录日志持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserLoginLogRep extends MyRep<UserLoginLog,UserLoginLogDto> {


}
