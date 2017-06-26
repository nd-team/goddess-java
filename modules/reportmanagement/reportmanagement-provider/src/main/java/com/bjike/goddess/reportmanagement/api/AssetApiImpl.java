package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.AssetBO;
import com.bjike.goddess.reportmanagement.service.AssetSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 资产表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assetApiImpl")
public class AssetApiImpl implements AssetAPI {
    @Autowired
    private AssetSer assetSer;

    @Override
    public AssetBO findByID(String id) throws SerException {
        return assetSer.findByID(id);
    }
}