package com.bjike.goddess.materialcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.bo.MaterialInventoryBO;
import com.bjike.goddess.materialcheck.dto.MaterialInventoryDTO;
import com.bjike.goddess.materialcheck.entity.MaterialInventory;
import com.bjike.goddess.materialcheck.to.MaterialInventoryTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
}