package com.bjike.goddess.competitorsmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.competitorsmanagement.bo.CollectBO;
import com.bjike.goddess.competitorsmanagement.service.CollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 竞争对手管理汇总业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 04:55 ]
 * @Description: [ 竞争对手管理汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("collectApiImpl")
public class CollectApiImpl implements CollectAPI {

    @Autowired
    private CollectSer collectSer;

    @Override
    public List<CollectBO> getCollet() throws SerException {
        return collectSer.getCollet();
    }
}