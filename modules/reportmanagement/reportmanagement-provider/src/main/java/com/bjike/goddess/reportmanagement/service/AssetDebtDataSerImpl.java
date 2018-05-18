package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.AssetDebtDTO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.entity.AssetData;
import com.bjike.goddess.reportmanagement.entity.AssetDebtData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 存储负债表数据
 *
 * @Author: [ caiwenxian ]
 * @date 18-3-26 下午5:30
 * @Description: [ 存储负债表数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class AssetDebtDataSerImpl extends ServiceImpl<AssetDebtData, DebtDTO> implements AssetDebtDataSer {

    @Override
    public void save(List<AssetDebtData> assetDataList) throws SerException {
        for (AssetDebtData data : assetDataList) {
            if (null == data.getProject()) {
                continue;
            }
            DebtDTO dto = new DebtDTO();
            dto.getConditions().add(Restrict.eq("startTime", data.getStartTime()));
            dto.getConditions().add(Restrict.eq("endTime", data.getEndTime()));
            dto.getConditions().add(Restrict.eq("project", data.getProject()));
            List<AssetDebtData> list = super.findByCis(dto);
            if (list == null || list.size() < 1) {
                super.save(data);
                continue;
            }
          /*  AssetDebtData entity = list.get(0);
            if (entity.getBeginAsset().equals(data.getBeginAsset()) && entity.getEndAsset().equals(data.getEndAsset())) {
                continue;
            }*/
            /*entity.setBeginAsset(data.getBeginAsset());
            entity.setEndAsset(data.getEndAsset());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);*/

        }
    }
}