package com.bjike.goddess.rentutilitiespay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rentutilitiespay.bo.AreaCollectBO;
import com.bjike.goddess.rentutilitiespay.service.CollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-20 03:59 ]
* @Description:	[ 汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("collectApiImpl")
public class CollectApiImpl implements CollectAPI  {
    @Autowired
    private CollectSer collectSer;
    @Override
    public List<AreaCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        return collectSer.monthCollect(year,month);
    }

    @Override
    public List<AreaCollectBO> yeareCollect(Integer year) throws SerException {
        return collectSer.yeareCollect(year);
    }

    @Override
    public List<AreaCollectBO> allCollect(String day) throws SerException {
        return collectSer.allCollect(day);
    }
}