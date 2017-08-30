package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.costdetail.dto.CompanyLendDetailDTO;
import com.bjike.goddess.costdetail.entity.CompanyLendDetail;

import java.util.List;

/**
 * 公司借出明细业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:18 ]
 * @Description: [ 公司借出明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanyLendDetailSer extends Ser<CompanyLendDetail, CompanyLendDetailDTO> {
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
     * 成本明细id查找公司借出明细数据
     *
     * @param id 成本明细
     * @return class CompanyLendDetailBO
     * @throws SerException
     */
    List<CompanyLendDetail> findByCostId(String id) throws SerException;
}