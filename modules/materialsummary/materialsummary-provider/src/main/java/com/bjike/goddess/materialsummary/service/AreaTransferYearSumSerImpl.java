package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferYearSum;
import com.bjike.goddess.materialsummary.to.AreaTransferYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区调动年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:47 ]
 * @Description: [ 地区调动年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaTransferYearSumSerImpl extends ServiceImpl<AreaTransferYearSum, AreaTransferYearSumDTO> implements AreaTransferYearSumSer {

    /**
     * 分页查询地区调动年汇总记录
     *
     * @param dto 地区调动年汇总记录dto
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferYearSumBO> list(AreaTransferYearSumDTO dto) throws SerException {
        List<AreaTransferYearSum> list = super.findByPage(dto);
        List<AreaTransferYearSumBO> listBO = BeanTransform.copyProperties(list, AreaTransferYearSumBO.class);
        return listBO;
    }

    /**
     * 保存地区调动年汇总记录
     *
     * @param to 保存地区调动年汇总记录to
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaTransferYearSumBO save(AreaTransferYearSumTO to) throws SerException {
        AreaTransferYearSum marketServeRecord = BeanTransform.copyProperties(to, AreaTransferYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaTransferYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaTransferYearSumBO.class);
        return bo;
    }

    /**
     * 更新地区调动年汇总记录
     *
     * @param to 地区调动年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaTransferYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaTransferYearSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaTransferYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区调动年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaTransferYearSum(AreaTransferYearSumTO to, AreaTransferYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区调动年汇总记录
     *
     * @param id 地区调动年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区调动年汇总
     *
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaTransferYearSumBO> summary() throws SerException {
        return null;
    }

}