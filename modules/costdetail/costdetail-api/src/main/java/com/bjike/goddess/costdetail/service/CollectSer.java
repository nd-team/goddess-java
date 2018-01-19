package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.costdetail.bo.CollectBO;
import com.bjike.goddess.costdetail.dto.CollectDTO;
import com.bjike.goddess.costdetail.entity.Collect;

import java.util.List;

/**
 * 成本明细汇总业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-03 04:10 ]
 * @Description: [ 成本明细汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectSer extends Ser<Collect, CollectDTO> {

    List<CollectBO> getCollect(String date) throws SerException;

}