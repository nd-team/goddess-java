package com.bjike.goddess.deviceinventory.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.deviceinventory.api.InventoryRecordAPI;
import com.bjike.goddess.deviceinventory.bo.InventoryBO;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.entity.Inventory;
import com.bjike.goddess.deviceinventory.to.InventoryRecordTO;
import com.bjike.goddess.deviceinventory.to.InventoryTO;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 盘点业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "deviceinventorySerCache")
@Service
public class InventorySerImpl extends ServiceImpl<Inventory, InventoryDTO> implements InventorySer {
    @Autowired
    private MaterialInStockAPI materialInStockAPI;
    @Autowired
    private InventoryRecordAPI inventoryRecordAPI;

    @Override
    public InventoryBO findByID(String id) throws SerException {
        Inventory inventory = super.findById(id);
        if (inventory == null) {
            throw new SerException("该对象不存在");
        }
        InventoryBO bo = BeanTransform.copyProperties(inventory, InventoryBO.class);
        if (bo.getInventoryNum() != null) {
            int a = bo.getInventoryNum() - bo.getQuantity();
            bo.setProfitLoss(a);
            bo.setProfitLossCount(a * bo.getUnitPrice());
        }
        return bo;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<InventoryBO> list(InventoryDTO dto) throws SerException {
        List<MaterialInStockBO> list = materialInStockAPI.findBOByCis(new MaterialInStockDTO());
        List<Inventory> inventorys = super.findAll();
        if (list != null) {
            for (MaterialInStockBO v : list) {
                if (inventorys.size() == 0) {
                    Inventory inventory = new Inventory();
                    BeanUtils.copyProperties(v, inventory);
                    inventory.setPurchaseDate(DateUtil.parseDate(v.getPurchaseDate()));
                    inventory.setMaterialinstockId(v.getId());
                    super.save(inventory);
                } else {
                    boolean b1 = true;
                    for (Inventory p : inventorys) {
                        if (p.getMaterialinstockId().equals(v.getId())) {
                            p.setStockEncoding(v.getStockEncoding());
                            p.setStorageArea(v.getStorageArea());
                            p.setProjectGroup(v.getProjectGroup());
                            p.setMaterialName(v.getMaterialName());
                            p.setUnit(v.getUnit());
                            p.setUnitPrice(v.getUnitPrice());
                            p.setPurchaseDate(DateUtil.parseDate(v.getPurchaseDate()));
                            p.setWarrantyExpire(v.getWarrantyExpire());
                            super.update(p);
                            b1 = false;
                        }
                    }
                    if (b1) {
                        Inventory inventory = new Inventory();
                        BeanUtils.copyProperties(v, inventory);
                        inventory.setPurchaseDate(DateUtil.parseDate(v.getPurchaseDate()));
                        inventory.setMaterialinstockId(v.getId());
                        super.save(inventory);
                    }
                }
            }
        }
        for (Inventory p : super.findAll()) {
            MaterialInStockBO v = materialInStockAPI.findById(p.getMaterialinstockId());
            if (v == null) {
                super.remove(p.getId());
            }
        }
        List<Inventory> list1 = super.findByCis(dto, true);
        List<InventoryBO> boList = new ArrayList<InventoryBO>();
        for (Inventory i : list1) {
            InventoryBO bo = BeanTransform.copyProperties(i, InventoryBO.class);
            if (bo.getInventoryNum() != null) {
                int a = bo.getInventoryNum() - bo.getQuantity();
                bo.setProfitLoss(a);
                bo.setProfitLossCount(a * bo.getUnitPrice());
            }
            boList.add(bo);
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void inventory(InventoryTO to) throws SerException {
        Inventory inventory = super.findById(to.getId());
        if (inventory == null) {
            throw new SerException("该对象不存在");
        }
        inventory.setInventoryTime(DateUtil.parseDate(to.getInventoryTime()));
        inventory.setQuantity(to.getQuantity());
        inventory.setInventoryNum(to.getInventoryNum());
        inventory.setDifferThat(to.getDifferThat());
        inventory.setResponsible(to.getResponsible());
        inventory.setState(to.getState());
        inventory.setInventoryMan(to.getInventoryMan());
        inventory.setNote(to.getNote());
        inventory.setModifyTime(LocalDateTime.now());
        super.update(inventory);
        InventoryRecordTO recordTO = BeanTransform.copyProperties(inventory, InventoryRecordTO.class);
        int a = recordTO.getInventoryNum() - recordTO.getQuantity();
        recordTO.setProfitLoss(a);
        recordTO.setProfitLossCount(a * recordTO.getUnitPrice());
        inventoryRecordAPI.save(recordTO);
    }

    @Override
    public List<InventoryBO> export(String startTime, String endTime) throws SerException {
        LocalDate s = null;
        LocalDate e = null;
        try {
            s = DateUtil.parseDate(startTime);
            e = DateUtil.parseDate(endTime);
        } catch (Exception ex) {
            throw new SerException("日期格式错误");
        }
        LocalDate[] time = new LocalDate[]{s, e};
        InventoryDTO dto = new InventoryDTO();
        dto.getConditions().add(Restrict.between("inventoryTime", time));
        List<Inventory> list = super.findByCis(dto);
        List<InventoryBO> boList = new ArrayList<InventoryBO>();
        for (Inventory i : list) {
            InventoryBO bo = BeanTransform.copyProperties(i, InventoryBO.class);
            if (bo.getInventoryNum() != null) {
                int a = bo.getInventoryNum() - bo.getQuantity();
                bo.setProfitLoss(a);
                bo.setProfitLossCount(a * bo.getUnitPrice());
            }
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public Long count(InventoryDTO dto) throws SerException {
        return super.count(dto);
    }
}