package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyDaySumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyDaySum;
import com.bjike.goddess.materialsummary.to.MaterialClassifyDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资分类日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:27 ]
 * @Description: [ 物资分类日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialClassifyDaySumSerImpl extends ServiceImpl<MaterialClassifyDaySum, MaterialClassifyDaySumDTO> implements MaterialClassifyDaySumSer {

    /**
     * 分页查询物资分类日汇总记录
     *
     * @param dto 物资分类日汇总记录dto
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyDaySumBO> list(MaterialClassifyDaySumDTO dto) throws SerException {
        List<MaterialClassifyDaySum> list = super.findByPage(dto);
        List<MaterialClassifyDaySumBO> listBO = BeanTransform.copyProperties(list, MaterialClassifyDaySumBO.class);
        return listBO;
    }

    /**
     * 保存物资分类日汇总记录
     *
     * @param to 保存物资分类日汇总记录to
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialClassifyDaySumBO save(MaterialClassifyDaySumTO to) throws SerException {
        MaterialClassifyDaySum marketServeRecord = BeanTransform.copyProperties(to, MaterialClassifyDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialClassifyDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialClassifyDaySumBO.class);
        return bo;
    }

    /**
     * 更新物资分类日汇总记录
     *
     * @param to 物资分类日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialClassifyDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialClassifyDaySum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialClassifyDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资分类日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialClassifyDaySum(MaterialClassifyDaySumTO to, MaterialClassifyDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资分类日汇总记录
     *
     * @param id 物资分类日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资分类日汇总
     *
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialClassifyDaySumBO> summary() throws SerException {
        return null;
    }

}