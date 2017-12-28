package com.bjike.goddess.firmreward.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.firmreward.dto.RewardPeopleNoStatDTO;
import com.bjike.goddess.firmreward.entity.RewardPeopleNoStat;

/**
 * 奖励人数统计持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [ 奖励人数统计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RewardPeopleNoStatRep extends JpaRep<RewardPeopleNoStat, RewardPeopleNoStatDTO> {

}