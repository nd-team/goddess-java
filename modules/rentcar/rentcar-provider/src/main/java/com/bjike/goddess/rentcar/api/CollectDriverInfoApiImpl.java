package com.bjike.goddess.rentcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rentcar.bo.AreaBO;
import com.bjike.goddess.rentcar.bo.CollectDriverInfoBO;
import com.bjike.goddess.rentcar.service.CollectDriverInfoSer;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 租车协议管理汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("collectDriverInfoApiImpl")
public class CollectDriverInfoApiImpl implements CollectDriverInfoAPI  {
    @Autowired
    private CollectDriverInfoSer collectDriverInfoSer;
    @Override
    public List<AreaBO> monthCollect(Integer year, Integer month) throws SerException {
        return collectDriverInfoSer.monthCollect(year,month);
    }

    @Override
    public List<AreaBO> allCollect(Integer year) throws SerException {
        return collectDriverInfoSer.allCollect(year);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return collectDriverInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return collectDriverInfoSer.guidePermission(guidePermissionTO);
    }
}