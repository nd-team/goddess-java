package com.bjike.goddess.materialcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.bo.MaterialInventoryBO;
import com.bjike.goddess.materialcheck.dto.MaterialInventoryDTO;
import com.bjike.goddess.materialcheck.entity.MaterialInventory;
import com.bjike.goddess.materialcheck.service.MaterialInventorySer;
import com.bjike.goddess.materialcheck.to.MaterialInventoryTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资盘点业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [ 物资盘点业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialInventoryApiImpl")
public class MaterialInventoryApiImpl implements MaterialInventoryAPI {

    @Autowired
    private MaterialInventorySer materialInventorySer;

    /**
     * 根据id查询物资盘点
     *
     * @param id 物资盘点唯一标识
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    public MaterialInventoryBO findById(String id) throws SerException {
        MaterialInventory model = materialInventorySer.findById(id);
        return BeanTransform.copyProperties(model, MaterialInventoryBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资盘点dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialInventoryDTO dto) throws SerException {
        return materialInventorySer.count(dto);
    }

    /**
     * 分页查询物资盘点
     *
     * @param dto 物资盘点dto
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    public List<MaterialInventoryBO> list(MaterialInventoryDTO dto) throws SerException {
        return materialInventorySer.list(dto);
    }

    /**
     * 保存物资盘点
     *
     * @param to 物资盘点to
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    public MaterialInventoryBO save(MaterialInventoryTO to) throws SerException {
        return materialInventorySer.save(to);
    }

    /**
     * 根据id删除物资盘点
     *
     * @param id 物资盘点唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialInventorySer.remove(id);
    }

    /**
     * 更新物资盘点
     *
     * @param to 物资盘点to
     * @throws SerException
     */
    @Override
    public void update(MaterialInventoryTO to) throws SerException {
        materialInventorySer.update(to);
    }

    /**
     * 经办人确认情况
     *
     * @param id             物资盘点唯一标识
     * @param operatorStatus 经办人确认情况
     * @throws SerException
     */
    @Override
    public void operatorConfirm(String id, String operatorStatus) throws SerException {
        materialInventorySer.operatorConfirm(id, operatorStatus);
    }

    /**
     * 账务模块核实
     *
     * @param id            物资盘点唯一标识
     * @param accountStatus 账务模块核实情况
     * @throws SerException
     */
    @Override
    public void accountModuleConfirm(String id, String accountStatus) throws SerException {
        materialInventorySer.accountModuleConfirm(id, accountStatus);
    }

    /**
     * 总经办审核
     *
     * @param id        物资盘点唯一标识
     * @param zjbStatus 总经办审核意见
     * @throws SerException
     */
    @Override
    public void zjbConfirm(String id, String zjbStatus) throws SerException {
        materialInventorySer.zjbConfirm(id, zjbStatus);
    }

    /**
     * 物资盘点
     *
     * @param inventoryType 物资盘点类型
     * @return class MaterialInventoryBO
     * @throws SerException
     */
    @Override
    public List<MaterialInventoryBO> materialInventory(InventoryType inventoryType) throws SerException {
        return materialInventorySer.materialInventory(inventoryType);
    }
}