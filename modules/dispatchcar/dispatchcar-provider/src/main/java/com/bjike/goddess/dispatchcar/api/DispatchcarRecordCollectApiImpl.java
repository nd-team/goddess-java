package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.AreaCollectBO;
import com.bjike.goddess.dispatchcar.service.DispatchcarRecordCollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 出车记录管理汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-27 05:16 ]
* @Description:	[ 出车记录管理汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("dispatchcarRecordCollectApiImpl")
public class DispatchcarRecordCollectApiImpl implements DispatchcarRecordCollectAPI  {
    @Autowired
    private DispatchcarRecordCollectSer dispatchcarRecordCollectSer;

    @Override
    public List<AreaCollectBO> dayCollect(String day) throws SerException {
        return  dispatchcarRecordCollectSer.dayCollect(day);
    }

    @Override
    public List<AreaCollectBO> weekCollect(String day) throws SerException {
        return dispatchcarRecordCollectSer.weekCollect(day);
    }

    @Override
    public List<AreaCollectBO> monthCollect(String year, String month) throws SerException {
        return dispatchcarRecordCollectSer.monthCollect(year,month);
    }

    @Override
    public List<AreaCollectBO> allCollect(String day) throws SerException {
        return  dispatchcarRecordCollectSer.allCollect(day);
    }
}