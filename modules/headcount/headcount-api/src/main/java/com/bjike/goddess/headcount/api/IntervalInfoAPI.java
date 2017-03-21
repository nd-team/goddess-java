package com.bjike.goddess.headcount.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.headcount.bo.IntervalInfoBO;
import com.bjike.goddess.headcount.to.IntervalInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * 对外提供薪资区间信息接口
 *
 * @Author: [yewenbo]
 * @Date: [2017-03-13 15:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 *
 */
public interface IntervalInfoAPI{
    default IntervalInfoBO saveByTO(TransactionContext txContext, IntervalInfoTO intervalInfoTO) throws SerException {
        return null;
    }

    default List<IntervalInfoBO> list()throws SerException{
        return null;
    }


    default void update(IntervalInfoTO intervalInfoTO)throws SerException{

    }


}
