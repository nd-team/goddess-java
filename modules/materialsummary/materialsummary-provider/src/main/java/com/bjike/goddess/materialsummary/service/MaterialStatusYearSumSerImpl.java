package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusYearSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusYearSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusYearSum;
import com.bjike.goddess.materialsummary.to.MaterialStatusYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资状态年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class MaterialStatusYearSumSerImpl extends ServiceImpl<MaterialStatusYearSum, MaterialStatusYearSumDTO> implements MaterialStatusYearSumSer {

    /**
     * 分页查询物资状态年汇总记录
     *
     * @param dto 物资状态年汇总记录dto
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusYearSumBO> list(MaterialStatusYearSumDTO dto) throws SerException {
        List<MaterialStatusYearSum> list = super.findByPage(dto);
        List<MaterialStatusYearSumBO> listBO = BeanTransform.copyProperties(list, MaterialStatusYearSumBO.class);
        return listBO;
    }

    /**
     * 保存物资状态年汇总记录
     *
     * @param to 保存物资状态年汇总记录to
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialStatusYearSumBO save(MaterialStatusYearSumTO to) throws SerException {
        MaterialStatusYearSum marketServeRecord = BeanTransform.copyProperties(to, MaterialStatusYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        MaterialStatusYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, MaterialStatusYearSumBO.class);
        return bo;
    }

    /**
     * 更新物资状态年汇总记录
     *
     * @param to 物资状态年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialStatusYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialStatusYearSum model = super.findById(to.getId());
            if (model != null) {
                updateMaterialStatusYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新物资状态年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialStatusYearSum(MaterialStatusYearSumTO to, MaterialStatusYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除物资状态年汇总记录
     *
     * @param id 物资状态年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 物资状态年汇总
     *
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialStatusYearSumBO> summary() throws SerException {
        return null;
    }

}