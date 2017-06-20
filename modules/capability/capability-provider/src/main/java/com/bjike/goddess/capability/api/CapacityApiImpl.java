package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CapacityBO;
import com.bjike.goddess.capability.service.CapacitySer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 个人资质业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:26 ]
* @Description:	[ 个人资质业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("capacityApiImpl")
public class CapacityApiImpl implements CapacityAPI  {

    @Autowired
    private CapacitySer capacitySer;

    @Override
    public CapacityBO addCapacity(String [] capacitys , String companyId) throws SerException {
        return capacitySer.addCapacity( capacitys , companyId );
    }

    @Override
    public CapacityBO editCapacity(String [] capacitys , String companyId) throws SerException {
        return capacitySer.editCapacity( capacitys, companyId );
    }

    @Override
    public void deleteCapacity(String id) throws SerException {
        capacitySer.deleteCapacity(id);
    }
 }