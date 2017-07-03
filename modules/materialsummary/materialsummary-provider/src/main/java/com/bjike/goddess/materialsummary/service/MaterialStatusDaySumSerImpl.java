package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusDaySumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusDaySum;
import com.bjike.goddess.materialsummary.to.MaterialStatusDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资状态日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:18 ]
 * @Description: [ 物资状态日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialStatusDaySumSerImpl extends ServiceImpl<MaterialStatusDaySum, MaterialStatusDaySumDTO> implements MaterialStatusDaySumSer {

    /**
     * 分页查询物资状态日汇总记录
     *
     * @param dto 物资状态日汇总记录dto
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusDaySumBO> list(MaterialStatusDaySumDTO dto) throws SerException {
        List<MaterialStatusDaySum> list = super.findByPage(dto);
        List<MaterialStatusDaySumBO> listBO = BeanTransform.copyProperties(list, MaterialStatusDaySumBO.class);
        return listBO;
    }

    /**
     * 保存物资状态日汇总记录
     *
     * @param to 保存物资状态日汇总记录to
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialStatusDaySumBO save(MaterialStatusDaySumTO to) throws SerException {
        MaterialStatusDaySum marketServeRecord = BeanTransform.copyProperties(to, MaterialStatusDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialStatusDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialStatusDaySumBO.class);
        return bo;
    }

    /**
     * 更新物资状态日汇总记录
     *
     * @param to 物资状态日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialStatusDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialStatusDaySum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialStatusDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资状态日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialStatusDaySum(MaterialStatusDaySumTO to, MaterialStatusDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资状态日汇总记录
     *
     * @param id 物资状态日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资状态日汇总
     *
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialStatusDaySumBO> summary() throws SerException {
        return null;
    }

}