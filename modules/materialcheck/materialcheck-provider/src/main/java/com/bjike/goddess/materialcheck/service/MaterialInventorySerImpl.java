package com.bjike.goddess.materialcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.bo.MaterialInventoryBO;
import com.bjike.goddess.materialcheck.dto.MaterialInventoryDTO;
import com.bjike.goddess.materialcheck.entity.MaterialInventory;
import com.bjike.goddess.materialcheck.to.MaterialInventoryTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.AttributeBO;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 物资盘点业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [ 物资盘点业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialcheckSerCache")
@Service
public class MaterialInventorySerImpl extends ServiceImpl<MaterialInventory, MaterialInventoryDTO> implements MaterialInventorySer {

    @Autowired
    private MaterialInStockAPI materialInStockAPI;

    /**
     * 分页查询物资盘点
     *
     * @param dto 物资盘点dto
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialInventoryBO> list(MaterialInventoryDTO dto) throws SerException {
        List<MaterialInventory> list = super.findByPage(dto);
        List<MaterialInventoryBO> boList = BeanTransform.copyProperties(list, MaterialInventoryBO.class);
        return boList;
    }

    /**
     * 保存物资盘点
     *
     * @param to 物资盘点to
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialInventoryBO save(MaterialInventoryTO to) throws SerException {
        MaterialInventory entity = BeanTransform.copyProperties(to, MaterialInventory.class, true);
        entity = super.save(entity);
        MaterialInventoryBO bo = BeanTransform.copyProperties(entity, MaterialInventoryBO.class);
        return bo;
    }

    /**
     * 根据id删除物资盘点
     *
     * @param id 物资盘点唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资盘点
     *
     * @param to 物资盘点to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialInventoryTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialInventory model = super.findById(to.getId());
            if (model != null) {
                updateMaterialInventory(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资盘点
     *
     * @param to
     * @param model
     */
    private void updateMaterialInventory(MaterialInventoryTO to, MaterialInventory model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 经办人确认情况
     *
     * @param id             物资盘点唯一标识
     * @param operatorStatus 经办人确认情况
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void operatorConfirm(String id, String operatorStatus) throws SerException {
        MaterialInventory model = super.findById(id);
        model.setOperatorConfirm(operatorStatus);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 账务模块核实
     *
     * @param id            物资盘点唯一标识
     * @param accountStatus 账务模块核实情况
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void accountModuleConfirm(String id, String accountStatus) throws SerException {
        MaterialInventory model = super.findById(id);
        model.setAccountModuleConfirm(accountStatus);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 总经办审核
     *
     * @param id        物资盘点唯一标识
     * @param zjbStatus 总经办审核意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void zjbConfirm(String id, String zjbStatus) throws SerException {
        MaterialInventory model = super.findById(id);
        model.setZjbOpinion(zjbStatus);
        super.update(model);
    }

    /**
     * 物资盘点
     *
     * @param inventoryType 物资盘点类型
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialInventoryBO> materialInventory(InventoryType inventoryType) throws SerException {
        List<MaterialInventoryBO> inventoryBOList = new ArrayList<>(0);
        //查询到所有类型的存储地区,项目组,物品类型,物资名称
        List<AttributeBO> attributeBOList = materialInStockAPI.findAllKindsType();
        if (attributeBOList.isEmpty()) {
            return Collections.emptyList();
        }
        //依次遍历计算每条记录
        for (AttributeBO bo : attributeBOList) {
            List<MaterialInStockBO> boList = materialInStockAPI.findByAttribute(bo);
            String area = bo.getStorageArea();//地区
            String projectGroup = bo.getProjectGroup();//项目组
            String type = bo.getMaterialType();        //物资类型
            String deviceName = bo.getMaterialName();  //设备名称
            String unit = bo.getUnit();                //单位
            Integer accountNo = boList.size();         //账目数
            Double total = boList.stream().mapToDouble(c -> c.getUnitPrice()).sum();//计算总额
            Integer stockNo = Math.toIntExact(boList.stream().filter(c -> c.getUseState() == UseState.INSTOCK).count());//计算库存数
            Integer receiveNo = Math.toIntExact(boList.stream().filter(c -> c.getUseState() == UseState.RECEIVE).count());//计算领用数
            Integer repairNo = Math.toIntExact(boList.stream().filter(c -> c.getMaterialState() == MaterialState.REPAIRING).count());//计算维修数
            Integer transferNo = Math.toIntExact(boList.stream().filter(c -> c.getUseState() == UseState.TRANSFER).count());//计算调动数
            Integer scrapNo = Math.toIntExact(boList.stream().filter(c -> c.getMaterialState() == MaterialState.SCRAP).count());//计算报废数
            Integer inventoryLossNo = accountNo - stockNo - receiveNo - repairNo - transferNo - scrapNo;
            inventoryLossNo = (inventoryLossNo > 0) ? inventoryLossNo : 0;//计算盘亏数
            Double inventoryLossTotal = (accountNo == 0) ? 0 : (total * inventoryLossNo / accountNo);//计算盘亏总额
            Integer inventorySurplusNo = accountNo - stockNo - receiveNo - repairNo - transferNo - scrapNo;
            inventorySurplusNo = (inventorySurplusNo < 0) ? -inventorySurplusNo : 0;   //计算盘盈数
            Double inventorySurplusTotal = (accountNo == 0) ? 0 : (total * inventorySurplusNo / accountNo);//计算盘盈总额

            //逐个设置属性
            MaterialInventoryBO materialInventoryBO = new MaterialInventoryBO();
            materialInventoryBO.setArea(area);
            materialInventoryBO.setProjectGroup(projectGroup);
            materialInventoryBO.setType(type);
            materialInventoryBO.setDeviceName(deviceName);
            materialInventoryBO.setUnit(unit);
            materialInventoryBO.setAccountNo(accountNo);
            materialInventoryBO.setTotal(total);
            materialInventoryBO.setStockNo(stockNo);
            materialInventoryBO.setReceiveNo(receiveNo);
            materialInventoryBO.setRepairNo(repairNo);
            materialInventoryBO.setTransferNo(transferNo);
            materialInventoryBO.setScrapNo(scrapNo);
            materialInventoryBO.setInventoryLossNo(inventoryLossNo);
            materialInventoryBO.setInventoryLossTotal(inventoryLossTotal);
            materialInventoryBO.setInventorySurplusNo(inventorySurplusNo);
            materialInventoryBO.setInventorySurplusTotal(inventorySurplusTotal);
            materialInventoryBO.setInventoryType(inventoryType);

            inventoryBOList.add(materialInventoryBO);
        }

        //如果不为空,则拷贝数据并且保存
        if (!inventoryBOList.isEmpty()) {
            List<MaterialInventory> inventoryList = BeanTransform.copyProperties(inventoryBOList, MaterialInventory.class, true);
            super.save(inventoryList);
        }

        return inventoryBOList;
    }

}