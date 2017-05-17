package com.bjike.goddess.materialcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.bo.MaterialAnalyzeBO;
import com.bjike.goddess.materialcheck.dto.MaterialAnalyzeDTO;
import com.bjike.goddess.materialcheck.entity.MaterialAnalyze;
import com.bjike.goddess.materialcheck.to.MaterialAnalyzeTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import com.bjike.goddess.materialinstock.bo.AttributeBO;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.service.MaterialInStockSer;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 物资分析业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialcheckSerCache")
@Service
public class MaterialAnalyzeSerImpl extends ServiceImpl<MaterialAnalyze, MaterialAnalyzeDTO> implements MaterialAnalyzeSer {

    @Autowired
    private MaterialInStockSer materialInStockSer;

    /**
     * 分页查询物资分析
     *
     * @param dto 物资分析dto
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialAnalyzeBO> list(MaterialAnalyzeDTO dto) throws SerException {
        List<MaterialAnalyze> list = super.findByPage(dto);
        List<MaterialAnalyzeBO> boList = BeanTransform.copyProperties(list, MaterialAnalyzeBO.class);
        return boList;
    }

    /**
     * 保存物资分析
     *
     * @param to 物资分析to
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialAnalyzeBO save(MaterialAnalyzeTO to) throws SerException {
        MaterialAnalyze entity = BeanTransform.copyProperties(to, MaterialAnalyze.class, true);
        entity = super.save(entity);
        MaterialAnalyzeBO bo = BeanTransform.copyProperties(entity, MaterialAnalyzeBO.class);
        return bo;
    }

    /**
     * 根据id删除物资分析
     *
     * @param id 物资分析唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资分析
     *
     * @param to 物资分析to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialAnalyzeTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialAnalyze model = super.findById(to.getId());
            if (model != null) {
                updateMaterialAnalyze(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资分析
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialAnalyze(MaterialAnalyzeTO to, MaterialAnalyze model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 物资分析
     *
     * @param inventoryType 物资分析类型
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    public List<MaterialAnalyzeBO> materialAnalyze(InventoryType inventoryType) throws SerException {
        //查询所有类型的存储地区,项目组,物品类型,物资名称
        List<AttributeBO> boList = materialInStockSer.findAllKindsType();
        if (CollectionUtils.isEmpty(boList)) {
            return Collections.emptyList();
        }

        List<MaterialAnalyzeBO> analyzeBOList = new ArrayList<>(0);
        //依次遍历每条记录
        for (AttributeBO bo : boList) {
            List<MaterialInStockBO> inStockBOList = materialInStockSer.findByAttribute(bo);
            String area = bo.getStorageArea();//地区
            String projectGroup = bo.getProjectGroup();//项目组
            String type = bo.getMaterialType();        //物资类型
            String deviceName = bo.getMaterialName();  //设备名称
            Integer accountNo = boList.size();         //账目数
            Integer stockNo = Math.toIntExact(inStockBOList.stream().filter(c -> c.getUseState() == UseState.INSTOCK).count());//计算库存数
            Integer receiveNo = Math.toIntExact(inStockBOList.stream().filter(c -> c.getUseState() == UseState.RECEIVE).count());//计算领用数
            Integer repairNo = Math.toIntExact(inStockBOList.stream().filter(c -> c.getMaterialState() == MaterialState.REPAIRING).count());//计算维修数
            Integer transferNo = Math.toIntExact(inStockBOList.stream().filter(c -> c.getUseState() == UseState.TRANSFER).count());//计算调动数
            Integer scrapNo = Math.toIntExact(inStockBOList.stream().filter(c -> c.getMaterialState() == MaterialState.SCRAP).count());//计算报废数
            Integer actualInventoryNo = stockNo + receiveNo + repairNo + transferNo + scrapNo;
            Integer inventoryLossNo = accountNo - actualInventoryNo;
            inventoryLossNo = (inventoryLossNo > 0) ? inventoryLossNo : 0;//计算盘亏数
            String turnoverRatio = (receiveNo==0 || stockNo == 0)?"0%":formatDouble(1.0*receiveNo/stockNo);//计算周转率
            String vacancyRate = (stockNo == 0 || accountNo == 0)?"0%":formatDouble(1.0*stockNo/accountNo);//计算闲置率
            String balanceRate = (inventoryLossNo == 0 || actualInventoryNo == 0)?"0%":formatDouble(1.0*inventoryLossNo/actualInventoryNo);//计算盈亏率

            MaterialAnalyzeBO analyzeBO = new MaterialAnalyzeBO();
            analyzeBO.setArea(area);
            analyzeBO.setProjectGroup(projectGroup);
            analyzeBO.setType(type);
            analyzeBO.setDeviceName(deviceName);
            analyzeBO.setTurnoverRatio(turnoverRatio);
            analyzeBO.setVacancyRate(vacancyRate);
            analyzeBO.setBalanceRate(balanceRate);
            analyzeBO.setInventoryType(inventoryType);

            analyzeBOList.add(analyzeBO);
        }

        return analyzeBOList;
    }

    private String formatDouble(Double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d*100) + "%";
    }

}