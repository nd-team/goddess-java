package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDatumDTO;
import com.bjike.goddess.reportmanagement.entity.AssetData;
import com.bjike.goddess.reportmanagement.entity.CashFlowDatum;

import java.util.List;

/**
 * 存储资产表数据
 *
 * @author [caiwenxian]
 * @date 18-3-26 下午5:28
 * @Description: [ 存储资产表数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssetDataSer extends Ser<AssetData, AssetDTO> {

    default void save(List<AssetData> list) throws SerException {}
}