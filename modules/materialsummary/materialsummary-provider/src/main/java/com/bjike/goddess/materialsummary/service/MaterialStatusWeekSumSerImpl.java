package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusWeekSum;
import com.bjike.goddess.materialsummary.to.MaterialStatusWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资状态周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialStatusWeekSumSerImpl extends ServiceImpl<MaterialStatusWeekSum, MaterialStatusWeekSumDTO> implements MaterialStatusWeekSumSer {

    /**
     * 分页查询物资状态周汇总记录
     *
     * @param dto 物资状态周汇总记录dto
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusWeekSumBO> list(MaterialStatusWeekSumDTO dto) throws SerException {
        List<MaterialStatusWeekSum> list = super.findByPage(dto);
        List<MaterialStatusWeekSumBO> listBO = BeanTransform.copyProperties(list, MaterialStatusWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存物资状态周汇总记录
     *
     * @param to 保存物资状态周汇总记录to
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialStatusWeekSumBO save(MaterialStatusWeekSumTO to) throws SerException {
        MaterialStatusWeekSum marketServeRecord = BeanTransform.copyProperties(to, MaterialStatusWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialStatusWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialStatusWeekSumBO.class);
        return bo;
    }

    /**
     * 更新物资状态周汇总记录
     *
     * @param to 物资状态周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialStatusWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialStatusWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialStatusWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资状态周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialStatusWeekSum(MaterialStatusWeekSumTO to, MaterialStatusWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资状态周汇总记录
     *
     * @param id 物资状态周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资状态周汇总
     *
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialStatusWeekSumBO> summary() throws SerException {
        return null;
    }

}