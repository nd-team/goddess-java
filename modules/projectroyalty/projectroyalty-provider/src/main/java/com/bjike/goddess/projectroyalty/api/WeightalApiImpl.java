package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.service.WeightalSer;
import com.bjike.goddess.projectroyalty.to.WeightalTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 项目提成权重分配表业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-14 01:55 ]
* @Description:	[ 项目提成权重分配表业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("weightalApiImpl")
public class WeightalApiImpl implements WeightalAPI  {
    @Autowired
    private WeightalSer weightalSer;

    @Override
    public void save(WeightalTO to) throws SerException {
        weightalSer.save(to);
    }
}