package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.headcount.bo.IntervalInfoBO;
import com.bjike.goddess.headcount.dto.IntervalInfoDTO;
import com.bjike.goddess.headcount.entity.plancost.IntervalInfo;
import com.bjike.goddess.headcount.to.IntervalInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * 薪资区间信息接口
 *
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-09 11:43 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IntervalInfoSer extends Ser<IntervalInfo,IntervalInfoDTO> {
    default IntervalInfoBO saveByTO(TransactionContext txContext, IntervalInfoTO to) throws SerException {
        return null;
    }

    default List<IntervalInfoBO> list()throws SerException{
        return null;
    }
    default void update(IntervalInfoTO to) throws SerException {

    }



}
