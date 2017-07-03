package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyYearSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyYearSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyYearSum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资分类年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:48 ]
 * @Description: [ 物资分类年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialClassifyYearSumSerImpl extends ServiceImpl<MaterialClassifyYearSum, MaterialClassifyYearSumDTO> implements MaterialClassifyYearSumSer {

    /**
     * 分页查询物资分类年汇总记录
     *
     * @param dto 物资分类年汇总记录dto
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyYearSumBO> list(MaterialClassifyYearSumDTO dto) throws SerException {
        List<MaterialClassifyYearSum> list = super.findByPage(dto);
        List<MaterialClassifyYearSumBO> listBO = BeanTransform.copyProperties(list, MaterialClassifyYearSumBO.class);
        return listBO;
    }

    /**
     * 保存物资分类年汇总记录
     *
     * @param to 保存物资分类年汇总记录to
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialClassifyYearSumBO save(MaterialClassifyYearSumTO to) throws SerException {
        MaterialClassifyYearSum marketServeRecord = BeanTransform.copyProperties(to, MaterialClassifyYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialClassifyYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialClassifyYearSumBO.class);
        return bo;
    }

    /**
     * 更新物资分类年汇总记录
     *
     * @param to 物资分类年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialClassifyYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialClassifyYearSum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialClassifyYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资分类年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialClassifyYearSum(MaterialClassifyYearSumTO to, MaterialClassifyYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资分类年汇总记录
     *
     * @param id 物资分类年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资分类年汇总
     *
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialClassifyYearSumBO> summary() throws SerException {
        return null;
    }

}