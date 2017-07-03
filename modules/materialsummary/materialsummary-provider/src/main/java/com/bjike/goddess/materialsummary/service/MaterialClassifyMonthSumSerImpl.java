package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyMonthSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyMonthSum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资分类月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:46 ]
 * @Description: [ 物资分类月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialClassifyMonthSumSerImpl extends ServiceImpl<MaterialClassifyMonthSum, MaterialClassifyMonthSumDTO> implements MaterialClassifyMonthSumSer {

    /**
     * 分页查询物资分类月汇总记录
     *
     * @param dto 物资分类月汇总记录dto
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyMonthSumBO> list(MaterialClassifyMonthSumDTO dto) throws SerException {
        List<MaterialClassifyMonthSum> list = super.findByPage(dto);
        List<MaterialClassifyMonthSumBO> listBO = BeanTransform.copyProperties(list, MaterialClassifyMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存物资分类月汇总记录
     *
     * @param to 保存物资分类月汇总记录to
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialClassifyMonthSumBO save(MaterialClassifyMonthSumTO to) throws SerException {
        MaterialClassifyMonthSum marketServeRecord = BeanTransform.copyProperties(to, MaterialClassifyMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialClassifyMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialClassifyMonthSumBO.class);
        return bo;
    }

    /**
     * 更新物资分类月汇总记录
     *
     * @param to 物资分类月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialClassifyMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialClassifyMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialClassifyMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资分类月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialClassifyMonthSum(MaterialClassifyMonthSumTO to, MaterialClassifyMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资分类月汇总记录
     *
     * @param id 物资分类月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资分类月汇总
     *
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialClassifyMonthSumBO> summary() throws SerException {
        return null;
    }

}