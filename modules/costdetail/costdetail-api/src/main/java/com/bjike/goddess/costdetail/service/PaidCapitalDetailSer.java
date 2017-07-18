package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.costdetail.dto.PaidCapitalDetailDTO;
import com.bjike.goddess.costdetail.entity.PaidCapitalDetail;

import java.util.List;

/**
 * 实收资本明细业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 07:06 ]
 * @Description: [ 实收资本明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PaidCapitalDetailSer extends Ser<PaidCapitalDetail, PaidCapitalDetailDTO> {
    /**
     * 获取所有明细分类名
     *
     * @return
     * @throws SerException
     */
    default List<String> findTypeName() throws SerException {
        return null;
    }

    /**
     * 成本明细id查找实收资本明细数据
     *
     * @param id 成本明细
     * @return class PaidCapitalDetailBO
     * @throws SerException
     */
    List<PaidCapitalDetail> findByCostId(String id) throws SerException;
}