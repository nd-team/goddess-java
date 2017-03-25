package com.bjike.goddess.headcount.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.headcount.bo.GroupInfoBO;
import com.bjike.goddess.headcount.to.GroupInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 *  对外提供部门信息接口
 *
 * @Author: [ yewenbo ]
 * @Date:: [ 2017-03-13 11:25 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 *
 */

public interface GroupInfoAPI {

    default GroupInfoBO saveByTO(TransactionContext txContext, GroupInfoTO to) throws SerException {
        return null;
    }

    default void update(GroupInfoTO groupInfoTO )throws SerException{

    }

    default List<GroupInfoBO> list()throws SerException{
        return null;
    }

    default GroupInfoBO findByName(String name)throws SerException{
        return null;
    }
}
