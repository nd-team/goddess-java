package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.bo.CollectBO;
import com.bjike.goddess.costdetail.service.CollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 成本明细汇总业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-03 04:10 ]
 * @Description: [ 成本明细汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("collectApiImpl")
public class CollectApiImpl implements CollectAPI {

    @Autowired
    private CollectSer collectSer;

    @Override
    public List<CollectBO> getCollect(String date) throws SerException {
        return collectSer.getCollect(date);
    }
}