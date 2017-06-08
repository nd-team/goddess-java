package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyWeekSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyWeekSum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资分类周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:45 ]
 * @Description: [ 物资分类周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialClassifyWeekSumSerImpl extends ServiceImpl<MaterialClassifyWeekSum, MaterialClassifyWeekSumDTO> implements MaterialClassifyWeekSumSer {

    /**
     * 分页查询物资分类周汇总记录
     *
     * @param dto 物资分类周汇总记录dto
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyWeekSumBO> list(MaterialClassifyWeekSumDTO dto) throws SerException {
        List<MaterialClassifyWeekSum> list = super.findByPage(dto);
        List<MaterialClassifyWeekSumBO> listBO = BeanTransform.copyProperties(list, MaterialClassifyWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存物资分类周汇总记录
     *
     * @param to 保存物资分类周汇总记录to
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialClassifyWeekSumBO save(MaterialClassifyWeekSumTO to) throws SerException {
        MaterialClassifyWeekSum marketServeRecord = BeanTransform.copyProperties(to, MaterialClassifyWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialClassifyWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialClassifyWeekSumBO.class);
        return bo;
    }

    /**
     * 更新物资分类周汇总记录
     *
     * @param to 物资分类周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialClassifyWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialClassifyWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialClassifyWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资分类周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialClassifyWeekSum(MaterialClassifyWeekSumTO to, MaterialClassifyWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资分类周汇总记录
     *
     * @param id 物资分类周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资分类周汇总
     *
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialClassifyWeekSumBO> summary() throws SerException {
        return null;
    }

}