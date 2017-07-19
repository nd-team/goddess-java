package com.bjike.goddess.materialinstock.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.bo.AttributeBO;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.service.MaterialInStockSer;
import com.bjike.goddess.materialinstock.to.GuidePermissionTO;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import com.bjike.goddess.materialinstock.type.UseState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * 物资入库业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialInStockApiImpl")
public class MaterialInStockApiImpl implements MaterialInStockAPI {

    @Autowired
    private MaterialInStockSer materialInStockSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return materialInStockSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return materialInStockSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询物资入库
     *
     * @param id 物资入库唯一标识
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public MaterialInStockBO findById(String id) throws SerException {
        MaterialInStock model = materialInStockSer.findById(id);
        return BeanTransform.copyProperties(model,MaterialInStockBO.class);
    }

    /**
     * 查询物资入库记录条数
     *
     * @param dto 物资入库dto
     * @throws SerException
     */
    public Long count(MaterialInStockDTO dto) throws SerException {
        return materialInStockSer.count(dto);
    }

    /**
     * 分页查询物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public List<MaterialInStockBO> list(MaterialInStockDTO dto) throws SerException {
        return materialInStockSer.list(dto);
    }

    /**
     * 更新物资使用状态
     *
     * @param materialNum 物资编号集合
     * @param useState 使用状态
     * @throws SerException
     */
    public void updateUseState(String[] materialNum, UseState useState) throws SerException {
        materialInStockSer.updateUseState(materialNum, useState);
    }

    /**
     * 保存物资入库
     *
     * @param to 物资入库to
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public MaterialInStockBO save(MaterialInStockTO to) throws SerException {
        return materialInStockSer.save(to);
    }

    /**
     * 根据id删除物资入库
     *
     * @param id 物资入库唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialInStockSer.remove(id);
    }

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @throws SerException
     */
    @Override
    public void update(MaterialInStockTO to) throws SerException {
        materialInStockSer.update(to);
    }

    /**
     * 根据dto查询物资入库
     *
     * @param dto 物资入库dto
     * @return class MaterialInStockBO
     * @throws SerException
     */
    public List<MaterialInStockBO> findBOByCis(MaterialInStockDTO dto) throws SerException {
        List<MaterialInStock> list = materialInStockSer.findByCis(dto);
        return BeanTransform.copyProperties(list, MaterialInStockBO.class);
    }

    /**
     * 更新物资入库集合
     *
     * @param listBO 物资入库bo集合
     * @throws SerException
     */
    public void updateBO(List<MaterialInStockBO> listBO) throws SerException {
        if (!CollectionUtils.isEmpty(listBO)) {
            List<MaterialInStock> list = BeanTransform.copyProperties(listBO, MaterialInStock.class, true);
            materialInStockSer.update(list);
        }
    }

    /**
     * 更新单个物资入库
     *
     * @param bo 物资入库bo
     * @throws SerException
     */
    public void updateSingleBO(MaterialInStockBO bo) throws SerException {
        MaterialInStockTO to = BeanTransform.copyProperties(bo, MaterialInStockTO.class);
        materialInStockSer.update(to);
    }

    /**
     * 查询单个物资入库
     *
     * @param dto 查询物资入库dto
     * @return class MaterialInStockBO
     * @throws SerException
     */
    public MaterialInStockBO findOne(MaterialInStockDTO dto) throws SerException {
        MaterialInStock model = materialInStockSer.findOne(dto);
        if (model == null) {
            return null;
        }
        MaterialInStockBO bo = BeanTransform.copyProperties(model, MaterialInStockBO.class);
        return bo;
    }

    /**
     * 根据物资编号查询物资入库
     *
     * @param materialCoding 物资编号
     * @return class MaterialInStockBO
     * @throws SerException
     */
    public MaterialInStockBO findByMaterialCoding(String materialCoding) throws SerException {
        return materialInStockSer.findByMaterialCoding(materialCoding);
    }

    /**
     * 查询所有相同类型的物资入库
     *
     * @return
     * @throws SerException
     */
    public List<AttributeBO> findAllKindsType() throws SerException {
        return materialInStockSer.findAllKindsType();
    }

    /**
     * 根据属性查找物资入库
     *
     * @param bo 属性bo
     * @return class MaterialInStock
     * @throws SerException
     */
    public List<MaterialInStockBO> findByAttribute(AttributeBO bo) throws SerException {
        return materialInStockSer.findByAttribute(bo);
    }

    /**
     * 查询所有物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public List<MaterialInStockBO> findAll() throws SerException {
        List<MaterialInStock> list = materialInStockSer.findAll();
        return BeanTransform.copyProperties(list, MaterialInStockBO.class);
    }

    @Override
    //cjh
    public Set<String> allstockEncoding() throws SerException {
        return materialInStockSer.allstockEncoding();
    }

    @Override
    public void updateLijuntao(MaterialInStockTO to) throws SerException {
        materialInStockSer.updateLijuntao(to);
    }
}