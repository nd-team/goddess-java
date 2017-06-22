package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.AssetDebtBO;
import com.bjike.goddess.reportmanagement.dto.AssetDebtDTO;
import com.bjike.goddess.reportmanagement.entity.AssetDebt;
import com.bjike.goddess.reportmanagement.to.AssetDebtTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 资产负债总表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:37 ]
 * @Description: [ 资产负债总表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class AssetDebtSerImpl extends ServiceImpl<AssetDebt, AssetDebtDTO> implements AssetDebtSer {
    @Override
    public AssetDebtBO save(AssetDebtTO to) throws SerException {
        AssetDebt entity = BeanTransform.copyProperties(to, AssetDebt.class, true);
        entity.setNum(1);
        return BeanTransform.copyProperties(entity, AssetDebtBO.class);
    }

    @Override
    public void editNum(String id) throws SerException{
        AssetDebt entity=super.findById(id);
        if (entity==null){
            throw new SerException("该对象不存在");
        }
        entity.setNum(entity.getNum()+1);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }
}