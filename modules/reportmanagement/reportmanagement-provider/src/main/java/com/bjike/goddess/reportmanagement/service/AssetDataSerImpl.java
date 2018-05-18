package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDatumDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDataDTO;
import com.bjike.goddess.reportmanagement.entity.AssetData;
import com.bjike.goddess.reportmanagement.entity.CashFlowDatum;
import com.bjike.goddess.reportmanagement.entity.ProfitData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 存储资产表数据
 *
 * @Author: [ caiwenxian ]
 * @date 18-3-26 下午5:30
 * @Description: [ 存储资产表数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class AssetDataSerImpl extends ServiceImpl<AssetData, AssetDTO> implements AssetDataSer {

    @Override
    public void save(List<AssetData> assetDataList) throws SerException {
        for (AssetData data : assetDataList) {
            if (null == data.getProject()) {
                continue;
            }
            AssetDTO dto = new AssetDTO();
            dto.getConditions().add(Restrict.eq("startTime", data.getStartTime()));
            dto.getConditions().add(Restrict.eq("endTime", data.getEndTime()));
            dto.getConditions().add(Restrict.eq("project", data.getProject()));
            List<AssetData> list = super.findByCis(dto);
            if (list == null || list.size() < 1) {
                super.save(data);
                continue;
            }
            AssetData entity = list.get(0);
            if (entity.getBeginAsset().equals(data.getBeginAsset()) && entity.getEndAsset().equals(data.getEndAsset())) {
                continue;
            }
            entity.setBeginAsset(data.getBeginAsset());
            entity.setEndAsset(data.getEndAsset());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);

        }
    }
}