package com.bjike.goddess.deviceinventory.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.deviceinventory.bo.InventoryRecordBO;
import com.bjike.goddess.deviceinventory.dto.InventoryRecordDTO;
import com.bjike.goddess.deviceinventory.entity.InventoryRecord;
import com.bjike.goddess.deviceinventory.to.InventoryRecordTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 盘点记录业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 盘点记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "deviceinventorySerCache")
@Service
public class InventoryRecordSerImpl extends ServiceImpl<InventoryRecord, InventoryRecordDTO> implements InventoryRecordSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(InventoryRecordTO to) throws SerException {
        InventoryRecord inventoryRecord = BeanTransform.copyProperties(to, InventoryRecord.class, true);
        super.save(inventoryRecord);
    }

    @Override
    public List<InventoryRecordBO> list(InventoryRecordDTO dto) throws SerException {
        List<InventoryRecord> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, InventoryRecordBO.class);
    }

    @Override
    public List<InventoryRecordBO> export(String startTime, String endTime) throws SerException {
        LocalDate s = null;
        LocalDate e = null;
        try {
            s = DateUtil.parseDate(startTime);
            e = DateUtil.parseDate(endTime);
        } catch (Exception ex) {
            throw new SerException("日期格式错误");
        }
        LocalDate[] time = new LocalDate[]{s, e};
        InventoryRecordDTO dto = new InventoryRecordDTO();
        dto.getConditions().add(Restrict.between("inventoryTime", time));
        List<InventoryRecord> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, InventoryRecordBO.class);
    }

    @Override
    public List<InventoryRecordBO> areaCount() throws SerException {
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String area : findAllAreas()) {
            for (InventoryRecord i : list) {
                if (area.equals(i.getStorageArea())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public List<InventoryRecordBO> projectGroupCount() throws SerException {
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String projectGroup : findAllProjectGroups()) {
            for (InventoryRecord i : list) {
                if (projectGroup.equals(i.getProjectGroup())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public List<InventoryRecordBO> materialNameCount() throws SerException {
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String materialName : findAllMaterialNames()) {
            for (InventoryRecord i : list) {
                if (materialName.equals(i.getMaterialName())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public List<InventoryRecordBO> stateCount() throws SerException {
        List<InventoryRecord> list = super.findAll();
        List<InventoryRecordBO> boList = new ArrayList<InventoryRecordBO>();
        int quantitySum = 0;
        int inventoryNumSum = 0;
        int profitLossSum = 0;
        double profitLossCountSum = 0.00;
        for (String state : findAllStates()) {
            for (InventoryRecord i : list) {
                if (state.equals(i.getState())) {
                    quantitySum += i.getQuantity();
                    inventoryNumSum += i.getInventoryNum();
                    profitLossSum += i.getProfitLoss();
                    profitLossCountSum += i.getProfitLossCount();
                    InventoryRecordBO bo = BeanTransform.copyProperties(i, InventoryRecordBO.class);
                    boList.add(bo);
                }
            }
            if (quantitySum != 0) {
                InventoryRecordBO bo = new InventoryRecordBO();
                bo.setQuantitySum(quantitySum);
                bo.setInventoryNumSum(inventoryNumSum);
                bo.setProfitLossSum(profitLossSum);
                bo.setProfitLossCountSum(profitLossCountSum);
                boList.add(bo);
                quantitySum = 0;
                inventoryNumSum = 0;
                profitLossSum = 0;
                profitLossCountSum = 0.00;  //置为0
            }
        }
        return boList;
    }

    @Override
    public Long count(InventoryRecordDTO dto) throws SerException{
        return super.count(dto);
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllAreas() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getStorageArea());
        }
        return set;
    }

    /**
     * 查找所有部门
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllProjectGroups() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getProjectGroup());
        }
        return set;
    }

    /**
     * 查找所有物资名称
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllMaterialNames() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getMaterialName());
        }
        return set;
    }

    /**
     * 查找所有状态
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllStates() throws SerException {
        List<InventoryRecord> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (InventoryRecord i : list) {
            set.add(i.getState());
        }
        return set;
    }
}