package com.bjike.goddess.headcount.dao;
import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.headcount.dto.GroupInfoDTO;
import com.bjike.goddess.headcount.entity.plancost.GroupInfo;

/**
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-09 11:26 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public interface GroupInfoRep extends JpaRep<GroupInfo,GroupInfoDTO> {

    GroupInfo findByName(String name)throws RepException;
}
