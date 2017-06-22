package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.AssetBO;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.entity.Asset;

/**
 * 资产表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssetSer extends Ser<Asset, AssetDTO> {
    /**
     * 通过id和日期查找
     *
     * @param id
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return
     * @throws SerException
     */
    AssetBO find(String id, String startTime, String endTime) throws SerException;
}