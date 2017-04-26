package com.bjike.goddess.materialinstock.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.bo.StockWarningBO;
import com.bjike.goddess.materialinstock.dto.StockWarningDTO;
import com.bjike.goddess.materialinstock.entity.StockWarning;
import com.bjike.goddess.materialinstock.to.StockWarningTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 库存预警业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialinstockSerCache")
@Service
public class StockWarningSerImpl extends ServiceImpl<StockWarning, StockWarningDTO> implements StockWarningSer {

    /**
     * 分页查询库存预警
     *
     * @return class StockWarningBO
     * @throws SerException
     */
    @Override
    public List<StockWarningBO> list(StockWarningDTO dto) throws SerException {
        List<StockWarning> list = super.findByPage(dto);
        List<StockWarningBO> listBO = BeanTransform.copyProperties(list, StockWarningBO.class);
        return listBO;
    }

    /**
     * 保存库存预警
     *
     * @param to 库存预警to
     * @return class StockWarningBO
     * @throws SerException
     */
    @Override
    @Transactional
    public StockWarningBO save(StockWarningTO to) throws SerException {
        StockWarning entity = BeanTransform.copyProperties(to, StockWarning.class, true);
        entity = super.save(entity);
        StockWarningBO bo = BeanTransform.copyProperties(entity, StockWarningBO.class);
        return bo;
    }

    /**
     * 根据id删除库存预警
     *
     * @param id 库存预警唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新库存预警
     *
     * @param to 库存预警to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(StockWarningTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            StockWarning model = super.findById(to.getId());
            if (model != null) {
                updateStockWarning(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新库存预警
     *
     * @param to 库存预警to
     * @param model 库存预警
     * @throws SerException
     */
    private void updateStockWarning(StockWarningTO to, StockWarning model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}