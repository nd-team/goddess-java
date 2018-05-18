package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.dto.ProfitDataDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitData;

import java.util.List;

/**
 * 存储利润表查询的数据
 *
 * @Author: [ caiwenxian ]
 * @date 18-3-26 下午2:16
 * @Description: [ 存储利润表查询的数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProfitDataSer extends Ser<ProfitData, ProfitDataDTO> {

    default void save(List<ProfitData> profitDataList) throws SerException {

    }
}