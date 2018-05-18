package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.AreaCollectBO;
import com.bjike.goddess.dispatchcar.service.DispatchcarRecordCollectSer;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
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
    public Boolean sonPermission() throws SerException {
        return dispatchcarRecordCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dispatchcarRecordCollectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<AreaCollectBO> dayCollect(String day) throws SerException {
        return  dispatchcarRecordCollectSer.dayCollect(day);
    }

    @Override
    public List<AreaCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return dispatchcarRecordCollectSer.weekCollect(year,month,week);
    }

    @Override
    public List<AreaCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        return dispatchcarRecordCollectSer.monthCollect(year,month);
    }

    @Override
    public List<AreaCollectBO> allCollect(String day) throws SerException {
        return  dispatchcarRecordCollectSer.allCollect(day);
    }


}