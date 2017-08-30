package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.SystemBetDTO;
import com.bjike.goddess.royalty.entity.SystemBet;

/**
 * 体系间对赌表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SystemBetRep extends JpaRep<SystemBet, SystemBetDTO> {

}