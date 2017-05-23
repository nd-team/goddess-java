package com.bjike.goddess.deviceinventory.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.deviceinventory.bo.InventoryBO;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.service.InventorySer;
import com.bjike.goddess.deviceinventory.to.InventoryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 盘点业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inventoryApiImpl")
public class InventoryApiImpl implements InventoryAPI {
    @Autowired
    private InventorySer inventorySer;

    @Override
    public InventoryBO findByID(String id) throws SerException {
        return inventorySer.findByID(id);
    }

    @Override
    public List<InventoryBO> list(InventoryDTO dto) throws SerException {
        return inventorySer.list(dto);
    }

    @Override
    public void inventory(InventoryTO to) throws SerException {
        inventorySer.inventory(to);
    }

    @Override
    public List<InventoryBO> export(String startTime, String endTime) throws SerException {
        return inventorySer.export(startTime, endTime);
    }

    @Override
    public Long count(InventoryDTO dto) throws SerException {
        return inventorySer.count(dto);
    }
}