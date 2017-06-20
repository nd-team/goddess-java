package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.AssetBO;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.entity.Asset;
import com.bjike.goddess.reportmanagement.enums.AssetType;
import com.bjike.goddess.reportmanagement.to.AssetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class AssetSerImpl extends ServiceImpl<Asset, AssetDTO> implements AssetSer {
    @Autowired
    private FormulaSer formulaSer;

//    @Override
//    public AssetBO save(AssetTO to) throws SerException {
//        Asset entity = BeanTransform.copyProperties(to, Asset.class, true);
//        return BeanTransform.copyProperties(entity, AssetBO.class);
//    }

    @Override
    public AssetBO find(String id, String startTime, String endTime) throws SerException {
        Asset entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        AssetBO bo = BeanTransform.copyProperties(entity, AssetBO.class);
        bo.setStartTime(startTime);
        bo.setEndTime(endTime);
        return bo;
    }

//    @Override
//    public List<AssetBO> list(AssetDTO dto) throws SerException {
//        String startTime = dto.getStartTime();
//        String endTime = dto.getEndTime();
//        dto.getSorts().add("assetType=ASC");
//        List<Asset> list = super.findByCis(dto);
//        List<AssetBO> boList = new ArrayList<AssetBO>();
//        boolean b1 = true;
//        boolean b2 = true;
//        boolean b3 = true;
//        boolean b4 = true;
//        boolean b5 = true;
//        double beginSum=0;
//        double endSum=0;
//        double countBegin=0;
//        double countEnd=0;       //总资产
//        for (Asset asset : list) {
//            List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), startTime, endTime);
//            if (formulaBOs != null) {
//                if (AssetType.AFLOW.equals(asset.getAssetType()) && b1) {
//                    AssetBO assetBO = new AssetBO();
//                    assetBO.setAsset("流动资产：");
//                    boList.add(assetBO);
//                    b1 = false;
//                } else if (AssetType.BLONG.equals(asset.getAssetType()) && b2) {
//                    AssetBO sumBO=new AssetBO();
//                    sumBO.setAsset("流动资产合计");
//                    sumBO.setBeginAsset(beginSum);
//                    sumBO.setEndAsset(endSum);
//                    boList.add(sumBO);
//                    beginSum=0;
//                    endSum=0;    //置为0
//                    AssetBO assetBO = new AssetBO();
//                    assetBO.setAsset("长期资产：");
//                    boList.add(assetBO);
//                    b2 = false;
//                } else if (AssetType.CFIX.equals(asset.getAssetType()) && b3) {
//                    AssetBO sumBO=new AssetBO();
//                    sumBO.setAsset("长期资产合计");
//                    sumBO.setBeginAsset(beginSum);
//                    sumBO.setEndAsset(endSum);
//                    boList.add(sumBO);
//                    beginSum=0;
//                    endSum=0;    //置为0
//                    AssetBO assetBO = new AssetBO();
//                    assetBO.setAsset("固定资产：");
//                    boList.add(assetBO);
//                    b3 = false;
//                } else if (AssetType.DINVISIBLE.equals(asset.getAssetType()) && b4) {
//                    AssetBO sumBO=new AssetBO();
//                    sumBO.setAsset("固定资产合计");
//                    sumBO.setBeginAsset(beginSum);
//                    sumBO.setEndAsset(endSum);
//                    boList.add(sumBO);
//                    beginSum=0;
//                    endSum=0;    //置为0
//                    AssetBO assetBO = new AssetBO();
//                    assetBO.setAsset("无形资产及其他资产：");
//                    boList.add(assetBO);
//                    b4 = false;
//                } else if (AssetType.ETAX.equals(asset.getAssetType()) && b5) {
//                    AssetBO sumBO=new AssetBO();
//                    sumBO.setAsset("无形资产及其他资产合计");
//                    sumBO.setBeginAsset(beginSum);
//                    sumBO.setEndAsset(endSum);
//                    boList.add(sumBO);
//                    beginSum=0;
//                    endSum=0;    //置为0
//                    AssetBO assetBO = new AssetBO();
//                    assetBO.setAsset("递延税款：");
//                    boList.add(assetBO);
//                    b5 = false;
//                }
//                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
//                AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
//                bo.setBeginAsset(formulaBO.getBegin());
//                bo.setEndAsset(formulaBO.getEnd());
//                beginSum+=bo.getBeginAsset();
//                endSum+=bo.getEndAsset();
//                countBegin+=bo.getBeginAsset();
//                countEnd+=bo.getEndAsset();
//                boList.add(bo);
//            }
//        }
//        AssetBO lastBO=new AssetBO();
//        lastBO.setAsset("资产总计");
//        lastBO.setBeginAsset(countBegin);
//        lastBO.setEndAsset(countEnd);
//        boList.add(lastBO);
//        return boList;
//    }
}