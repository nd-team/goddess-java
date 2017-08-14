package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.AssetBO;
import com.bjike.goddess.reportmanagement.bo.AssetDebtBO;
import com.bjike.goddess.reportmanagement.bo.DebtBO;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.AssetDebtDTO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.entity.AssetDebt;
import com.bjike.goddess.reportmanagement.to.AssetDebtTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
//    @Autowired
//    private AssetSer assetSer;
//    @Autowired
//    private DebtSer debtSer;
//
//    @Override
//    public List<AssetDebtBO> list(AssetDTO dto) throws SerException{
//        String token= RpcTransmit.getUserToken();
//        List<AssetBO> assetBOs=assetSer.list(dto);
//        DebtDTO debtDTO = new DebtDTO();
//        BeanUtils.copyProperties(dto, debtDTO);
//        RpcTransmit.transmitUserToken(token);
//        List<DebtBO> debtBOs=debtSer.list(debtDTO);
//        List<AssetDebtBO> list=BeanTransform.copyProperties(assetBOs,AssetDebtBO.class);
//        for (AssetDebtBO assetDebtBO:list){
//            for (as)
//        }
//    }
}