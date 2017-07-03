package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveMonthSum;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveMonthSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区部门领用月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaDepartReceiveMonthSumSerImpl extends ServiceImpl<AreaDepartReceiveMonthSum, AreaDepartReceiveMonthSumDTO> implements AreaDepartReceiveMonthSumSer {

    /**
     * 分页查询地区部门领用月汇总记录
     *
     * @param dto 地区部门领用月汇总记录dto
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveMonthSumBO> list(AreaDepartReceiveMonthSumDTO dto) throws SerException {
        List<AreaDepartReceiveMonthSum> list = super.findByPage(dto);
        List<AreaDepartReceiveMonthSumBO> listBO = BeanTransform.copyProperties(list, AreaDepartReceiveMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存地区部门领用月汇总记录
     *
     * @param to 保存地区部门领用月汇总记录to
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaDepartReceiveMonthSumBO save(AreaDepartReceiveMonthSumTO to) throws SerException {
        AreaDepartReceiveMonthSum marketServeRecord = BeanTransform.copyProperties(to, AreaDepartReceiveMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaDepartReceiveMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaDepartReceiveMonthSumBO.class);
        return bo;
    }

    /**
     * 更新地区部门领用月汇总记录
     *
     * @param to 地区部门领用月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaDepartReceiveMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaDepartReceiveMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaDepartReceiveMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区部门领用月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaDepartReceiveMonthSum(AreaDepartReceiveMonthSumTO to, AreaDepartReceiveMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区部门领用月汇总记录
     *
     * @param id 地区部门领用月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区部门领用月汇总
     *
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaDepartReceiveMonthSumBO> summary() throws SerException {
        return null;
    }

}