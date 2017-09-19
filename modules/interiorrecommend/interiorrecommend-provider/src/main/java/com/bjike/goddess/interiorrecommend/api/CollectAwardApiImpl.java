package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.CollectAwardBO;
import com.bjike.goddess.interiorrecommend.excel.SonPermissionObject;
import com.bjike.goddess.interiorrecommend.service.CollectAwardSer;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 内部推荐奖管理汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:43 ]
* @Description:	[ 内部推荐奖管理汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("collectAwardApiImpl")
public class CollectAwardApiImpl implements CollectAwardAPI  {
    @Autowired
    private CollectAwardSer collectAwardSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return collectAwardSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return collectAwardSer.guidePermission(guidePermissionTO);
    }

    @Override
    public CollectAwardBO collectByMonth(String year, String month) throws SerException {
        return collectAwardSer.collectByMonth(year,month);
    }

    @Override
    public CollectAwardBO allCollect(String today) throws SerException {
        return collectAwardSer.allCollect(today);
    }
}