package com.bjike.goddess.headcount.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.headcount.bo.IntervalInfoBO;
import com.bjike.goddess.headcount.service.IntervalInfoSer;
import com.bjike.goddess.headcount.to.IntervalInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 薪资区间信息接口对外提供
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-13 11:26 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("intervalInfoApiImpl")
public class IntervalInfoApiImpl implements IntervalInfoAPI{
    @Autowired
    private IntervalInfoSer intervalInfoSer;
    @Override
    public  IntervalInfoBO saveByTO(TransactionContext txContext, IntervalInfoTO to) throws SerException {
       return  intervalInfoSer.saveByTO(txContext, to);
    }
    @Override
    public List<IntervalInfoBO> list()throws SerException{
        return intervalInfoSer.list();
    }

    @Override
    public void update(IntervalInfoTO intervalInfoTO)throws SerException{
        intervalInfoSer.update(intervalInfoTO);
    }


}
