package com.bjike.goddess.materialinstock.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.entity.MaterialInStock;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 物资入库业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialinstockSerCache")
@Service
public class MaterialInStockSerImpl extends ServiceImpl<MaterialInStock, MaterialInStockDTO> implements MaterialInStockSer {

    /**
     * 分页查询物资入库
     *
     * @return class MaterialInStockBO
     * @throws SerException
     */
    @Override
    public List<MaterialInStockBO> list(MaterialInStockDTO dto) throws SerException {
        List<MaterialInStock> list = super.findByPage(dto);
        List<MaterialInStockBO> listBO = BeanTransform.copyProperties(list, MaterialInStockBO.class);
        return listBO;
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
        MaterialInStock entity = BeanTransform.copyProperties(to, MaterialInStock.class, true);
        entity = super.save(entity);
        MaterialInStockBO bo = BeanTransform.copyProperties(entity, MaterialInStockBO.class);
        return bo;
    }

    /**
     * 根据id删除物资入库
     *
     * @param id 物资入库唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @throws SerException
     */
    @Override
    public void update(MaterialInStockTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            MaterialInStock model = super.findById(to.getId());
            if (model != null) {
                updateMaterialInStock(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资入库
     *
     * @param to 物资入库to
     * @param model 物资入库
     * @throws SerException
     */
    private void updateMaterialInStock(MaterialInStockTO to, MaterialInStock model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

}