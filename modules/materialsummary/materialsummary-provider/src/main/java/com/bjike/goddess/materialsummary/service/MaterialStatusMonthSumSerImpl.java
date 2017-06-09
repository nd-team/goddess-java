package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusMonthSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusMonthSum;
import com.bjike.goddess.materialsummary.to.MaterialStatusMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资状态月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialStatusMonthSumSerImpl extends ServiceImpl<MaterialStatusMonthSum, MaterialStatusMonthSumDTO> implements MaterialStatusMonthSumSer {

    /**
     * 分页查询物资状态月汇总记录
     *
     * @param dto 物资状态月汇总记录dto
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusMonthSumBO> list(MaterialStatusMonthSumDTO dto) throws SerException {
        List<MaterialStatusMonthSum> list = super.findByPage(dto);
        List<MaterialStatusMonthSumBO> listBO = BeanTransform.copyProperties(list, MaterialStatusMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存物资状态月汇总记录
     *
     * @param to 保存物资状态月汇总记录to
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialStatusMonthSumBO save(MaterialStatusMonthSumTO to) throws SerException {
        MaterialStatusMonthSum marketServeRecord = BeanTransform.copyProperties(to, MaterialStatusMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialStatusMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialStatusMonthSumBO.class);
        return bo;
    }

    /**
     * 更新物资状态月汇总记录
     *
     * @param to 物资状态月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialStatusMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialStatusMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialStatusMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资状态月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialStatusMonthSum(MaterialStatusMonthSumTO to, MaterialStatusMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资状态月汇总记录
     *
     * @param id 物资状态月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资状态月汇总
     *
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialStatusMonthSumBO> summary() throws SerException {
        return null;
    }

}