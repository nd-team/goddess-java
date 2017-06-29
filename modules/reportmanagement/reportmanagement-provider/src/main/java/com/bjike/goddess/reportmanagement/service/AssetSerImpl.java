package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.AssetBO;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.bo.RepayAnalyzeBO;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
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
    @Autowired
    private DebtSer debtSer;

    @Override
    public AssetBO save(AssetTO to) throws SerException {
        Asset entity = BeanTransform.copyProperties(to, Asset.class, true);
        return BeanTransform.copyProperties(entity, AssetBO.class);
    }

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

    @Override
    public List<AssetBO> list(AssetDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        dto.getSorts().add("assetType=ASC");
        List<Asset> list = super.findByCis(dto);
        List<AssetBO> boList = new ArrayList<AssetBO>();
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        boolean b5 = true;
        double beginSum = 0;
        double endSum = 0;
        double countBegin = 0;
        double countEnd = 0;       //总资产
        for (Asset asset : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), startTime, endTime);
            if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                if (AssetType.AFLOW.equals(asset.getAssetType()) && b1) {
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("流动资产：");
                    boList.add(assetBO);
                    b1 = false;
                } else if (AssetType.BLONG.equals(asset.getAssetType()) && b2) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("流动资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setEndAsset(endSum);
                    boList.add(sumBO);
                    beginSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("长期资产：");
                    boList.add(assetBO);
                    b2 = false;
                } else if (AssetType.CFIX.equals(asset.getAssetType()) && b3) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("长期资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setEndAsset(endSum);
                    boList.add(sumBO);
                    beginSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("固定资产：");
                    boList.add(assetBO);
                    b3 = false;
                } else if (AssetType.DINVISIBLE.equals(asset.getAssetType()) && b4) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("固定资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setEndAsset(endSum);
                    boList.add(sumBO);
                    beginSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("无形资产及其他资产：");
                    boList.add(assetBO);
                    b4 = false;
                } else if (AssetType.ETAX.equals(asset.getAssetType()) && b5) {
                    AssetBO sumBO = new AssetBO();
                    sumBO.setAsset("无形资产及其他资产合计");
                    sumBO.setBeginAsset(beginSum);
                    sumBO.setEndAsset(endSum);
                    boList.add(sumBO);
                    beginSum = 0;
                    endSum = 0;    //置为0
                    AssetBO assetBO = new AssetBO();
                    assetBO.setAsset("递延税款：");
                    boList.add(assetBO);
                    b5 = false;
                }
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
                bo.setBeginAsset(formulaBO.getBegin());
                bo.setEndAsset(formulaBO.getEnd());
                beginSum += bo.getBeginAsset();
                endSum += bo.getEndAsset();
                countBegin += bo.getBeginAsset();
                countEnd += bo.getEndAsset();
                boList.add(bo);
            }
        }
        AssetBO lastBO = new AssetBO();
        lastBO.setAsset("资产总计");
        lastBO.setBeginAsset(countBegin);
        lastBO.setEndAsset(countEnd);
        boList.add(lastBO);
        return boList;
    }

    @Override
    public List<StructureBO> assetStructure(AssetDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        dto.getSorts().add("assetType=ASC");
        List<Asset> list = super.findByCis(dto);
        List<StructureBO> boList = new ArrayList<StructureBO>();
        boolean b = true;
        double flowSum = 0;
        double endSum = 0;
        double countEnd = 0;       //总资产
        for (Asset asset : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(asset.getId(), startTime, endTime);
            if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                if (AssetType.BLONG.equals(asset.getAssetType()) && b) {
                    flowSum = endSum;
                    b = false;
                }
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                AssetBO bo = BeanTransform.copyProperties(asset, AssetBO.class);
                bo.setBeginAsset(formulaBO.getBegin());
                bo.setEndAsset(formulaBO.getEnd());
                endSum += bo.getEndAsset();
                countEnd += bo.getEndAsset();
            }
        }
        StructureBO flowBO = new StructureBO();
        flowBO.setProject("流动资产合计");
        flowBO.setFee(flowSum);
        flowBO.setScale(String.format("%.2f", (flowSum / countEnd) * 100) + "%");
        boList.add(flowBO);
        double otherSum = countEnd - flowSum;
        StructureBO otherBO = new StructureBO();
        otherBO.setProject("非流动资产合计");
        otherBO.setFee(otherSum);
        otherBO.setScale(String.format("%.2f", (otherSum / countEnd) * 100) + "%");
        boList.add(otherBO);
        StructureBO sumBO = new StructureBO();
        sumBO.setProject("资产总计");
        sumBO.setFee(countEnd);
        sumBO.setScale("100%");
        boList.add(sumBO);
        return boList;
    }

    @Override
    public List<RepayAnalyzeBO> repayAnalyze(AssetDTO dto) throws SerException {
        double flowAsset = assetStructure(dto).get(0).getFee();
        double flowDebt = finds(dto).get(0);
        double asset = assetStructure(dto).get(2).getFee();
        double debt = finds(dto).get(1);
        double all = finds(dto).get(2);
        List<RepayAnalyzeBO> list = new ArrayList<>();
        RepayAnalyzeBO firstBO = new RepayAnalyzeBO();
        firstBO.setProject("一、短期偿债能力分析");
        list.add(firstBO);
        RepayAnalyzeBO flowBO = new RepayAnalyzeBO();
        flowBO.setProject("流动比率");
        flowBO.setScale(String.format("%.2f", (flowAsset / flowDebt) * 100) + "%");
        flowBO.setBestScale("200%");
        flowBO.setExplain("流动比率越高，反映企业短期偿债能力越强，但是流动比率过高则表明企业流动资产占用较多，" +
                "会影响企业的资金利用效率，进而降低企业的获利能力。");
        list.add(flowBO);
        RepayAnalyzeBO rateBO = new RepayAnalyzeBO();
        rateBO.setProject("速动比率");
        //todo:存货净额？？？
        rateBO.setBestScale("100%");
        rateBO.setExplain("速动比例较高说明公司不用动用存货，仅仅依靠速动资产就能偿还债务，偿还流动负债的能力较强，" +
                "但过高的速动比率也会造成资金的闲置，影响企业的盈利能力。");
        list.add(rateBO);
        RepayAnalyzeBO cashBO = new RepayAnalyzeBO();
        cashBO.setProject("现金比率");
        //todo：货币资金？？？
        cashBO.setBestScale("20%");
        cashBO.setExplain("现金比率越高，表明企业的直接偿付能力越强，信用也就越可靠。" +
                "但是由于现金是企业收益率最低的资产，现金比率过高将会影响企业的盈利能力。");
        list.add(cashBO);
        RepayAnalyzeBO secondBO = new RepayAnalyzeBO();
        secondBO.setProject("二、长期偿债能力分析");
        list.add(secondBO);
        RepayAnalyzeBO assetdebtBO = new RepayAnalyzeBO();
        assetdebtBO.setProject("资产负债率");
        assetdebtBO.setScale(String.format("%.2f", (debt / asset) * 100) + "%");
        assetdebtBO.setBestScale("40%-60%");
        assetdebtBO.setExplain("对于经营风险比较高的企业，为减少财务风险应选择比较低的资产负债率；" +
                "对于经营风险低的企业，为增加股东收益应选择比较高的资产负债率。");
        list.add(assetdebtBO);
        RepayAnalyzeBO equityBO = new RepayAnalyzeBO();
        equityBO.setProject("产权比率");
        equityBO.setScale(String.format("%.2f", (debt / all) * 100) + "%");
        equityBO.setBestScale("100%");
        equityBO.setExplain("较低的产权比率表明企业采用了低风险、低报酬的资本结构，债权人的利益受保护程度较高，企业财务风险较小。" +
                "但是过低的产权比率也意味着企业不能充分发挥负债带来的财务杠杆作用。产权比率高，是高风险、高报酬的财务结构");
        list.add(equityBO);
        return list;
    }

    /**
     * 查找流水负债,负债总额，所有者权益
     *
     * @param dto
     * @return
     * @throws SerException
     */
    private List<Double> finds(AssetDTO dto) throws SerException {
        DebtDTO debtDTO = BeanTransform.copyProperties(dto, DebtDTO.class);
        List<StructureBO> list = debtSer.debtStructure(debtDTO);
        Double flow = list.get(0).getFee();
        Double all = list.get(2).getFee();
        Double sum = list.get(3).getFee();
        Double debt = sum - all;
        List<Double> doubles = new ArrayList<>();
        doubles.add(flow);
        doubles.add(debt);
        doubles.add(all);
        return doubles;
    }


}