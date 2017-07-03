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
}