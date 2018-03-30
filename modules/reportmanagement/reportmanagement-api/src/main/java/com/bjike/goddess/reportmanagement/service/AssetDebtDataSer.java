package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.dto.AssetDebtDTO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.entity.AssetDebtData;
import com.bjike.goddess.reportmanagement.entity.ProfitData;

import java.util.List;

/**
 * 存储负债表查询的数据
 *
 * @Author: [ caiwenxian ]
 * @date 18-3-26 下午2:16
 * @Description: [ 存储负债表查询的数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssetDebtDataSer extends Ser<AssetDebtData, DebtDTO> {

    default void save(List<AssetDebtData> assetDataList) throws SerException{}
}