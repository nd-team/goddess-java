package com.bjike.goddess.workjoin.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.entity.WorkJoin;

/**
 * 工作交接持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkJoinRep extends JpaRep<WorkJoin, WorkJoinDTO> {

}