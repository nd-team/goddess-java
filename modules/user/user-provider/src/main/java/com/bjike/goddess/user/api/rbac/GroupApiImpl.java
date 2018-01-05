package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.user.bo.rbac.GroupBO;
import com.bjike.goddess.user.service.rbac.GroupSer;
import com.bjike.goddess.user.to.rbac.GroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 14:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("groupApiImpl")
public class GroupApiImpl implements GroupAPI {
    @Autowired
    private GroupSer groupSer;

    @Override
    public List<GroupBO> treeData(String id) throws SerException {
        return groupSer.treeData(id);
    }

    @Override
    public GroupBO save(GroupTO groupTO) throws SerException {
        return groupSer.save(groupTO);
    }

    @Override
    public void remove(String id) throws SerException {
        groupSer.remove(id);
    }

    @Override
    public void update(GroupTO groupTO) throws SerException {
        groupSer.update(groupTO);
    }
}
