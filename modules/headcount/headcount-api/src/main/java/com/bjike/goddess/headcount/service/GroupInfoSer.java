package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import com.bjike.goddess.headcount.bo.GroupInfoBO;
import com.bjike.goddess.headcount.dto.GroupInfoDTO;
import com.bjike.goddess.headcount.entity.plancost.GroupInfo;
import com.bjike.goddess.headcount.to.GroupInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * 部门信息接口
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-09 11:43 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public interface GroupInfoSer extends Ser<GroupInfo, GroupInfoDTO> {

    default GroupInfoBO saveByTO(TransactionContext txContext, GroupInfoTO groupInfoTO) throws SerException {
        return null;
    }

    default List<GroupInfoBO> list()throws SerException{
        return null;
    }

    default void update(GroupInfoTO groupInfoTO )throws SerException{

    }

    default GroupInfoBO findByName(String name)throws SerException{
        return null;
    }
}
