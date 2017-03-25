package com.bjike.goddess.headcount.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.headcount.bo.GroupInfoBO;
import com.bjike.goddess.headcount.service.GroupInfoSer;
import com.bjike.goddess.headcount.to.GroupInfoTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-13 11:26 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("groupInfoApiImpl")
public class GroupInfoApiImpl implements GroupInfoAPI {
    @Autowired
    private GroupInfoSer groupInfoSer;
    @Override
    public GroupInfoBO saveByTO(TransactionContext txContext,GroupInfoTO groupInfoTO)throws SerException{
        return groupInfoSer.saveByTO(txContext, groupInfoTO);

    }

    @Override
    public List<GroupInfoBO> list()throws SerException{
        return groupInfoSer.list();
    }
    @Override
    public void update(GroupInfoTO groupInfoTO)throws SerException{
        groupInfoSer.update(groupInfoTO);
    }

    @Override
    public GroupInfoBO findByName(String name)throws SerException{
        return groupInfoSer.findByName(name);
    }

}
