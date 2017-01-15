package com.bjike.goddess.user.dao;


import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.user.dto.GroupDTO;
import com.bjike.goddess.user.entity.Group;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: [用户组持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupRep extends JpaRep<Group, GroupDTO> {

}
