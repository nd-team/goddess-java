package com.bjike.goddess.deviceinventory.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.deviceinventory.bo.InventoryRecordBO;
import com.bjike.goddess.deviceinventory.dto.InventoryRecordDTO;
import com.bjike.goddess.deviceinventory.service.InventoryRecordSer;
import com.bjike.goddess.deviceinventory.to.InventoryRecordTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 盘点记录业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 盘点记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inventoryRecordApiImpl")
public class InventoryRecordApiImpl implements InventoryRecordAPI {
    @Autowired
    private InventoryRecordSer inventoryRecordSer;

    @Override
    public void save(InventoryRecordTO to) throws SerException {
        inventoryRecordSer.save(to);
    }

    @Override
    public List<InventoryRecordBO> list(InventoryRecordDTO dto) throws SerException {
        return inventoryRecordSer.list(dto);
    }

    @Override
    public List<InventoryRecordBO> export(String startTime, String endTime) throws SerException {
        return inventoryRecordSer.export(startTime, endTime);
    }

    @Override
    public List<InventoryRecordBO> areaCount() throws SerException {
        return inventoryRecordSer.areaCount();
    }

    @Override
    public List<InventoryRecordBO> projectGroupCount() throws SerException {
        return inventoryRecordSer.projectGroupCount();
    }

    @Override
    public List<InventoryRecordBO> materialNameCount() throws SerException {
        return inventoryRecordSer.materialNameCount();
    }

    @Override
    public List<InventoryRecordBO> stateCount() throws SerException {
        return inventoryRecordSer.stateCount();
    }

    @Override
    public Long count(InventoryRecordDTO dto) throws SerException {
        return inventoryRecordSer.count(dto);
    }
}