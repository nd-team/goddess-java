package com.bjike.goddess.checkhost.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.checkhost.dto.CheckHostCollectDTO;
import com.bjike.goddess.checkhost.entity.CheckHostCollect;

/**
 * 汇总表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:13 ]
 * @Description: [ 汇总表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CheckHostCollectRep extends JpaRep<CheckHostCollect, CheckHostCollectDTO> {

}