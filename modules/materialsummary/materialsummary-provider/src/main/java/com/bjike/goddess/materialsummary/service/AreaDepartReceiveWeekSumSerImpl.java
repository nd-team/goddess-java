package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveWeekSum;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveWeekSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区部门领用周汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用周汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaDepartReceiveWeekSumSerImpl extends ServiceImpl<AreaDepartReceiveWeekSum, AreaDepartReceiveWeekSumDTO> implements AreaDepartReceiveWeekSumSer {

    /**
     * 分页查询地区部门领用周汇总记录
     *
     * @param dto 地区部门领用周汇总记录dto
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveWeekSumBO> list(AreaDepartReceiveWeekSumDTO dto) throws SerException {
        List<AreaDepartReceiveWeekSum> list = super.findByPage(dto);
        List<AreaDepartReceiveWeekSumBO> listBO = BeanTransform.copyProperties(list, AreaDepartReceiveWeekSumBO.class);
        return listBO;
    }

    /**
     * 保存地区部门领用周汇总记录
     *
     * @param to 保存地区部门领用周汇总记录to
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaDepartReceiveWeekSumBO save(AreaDepartReceiveWeekSumTO to) throws SerException {
        AreaDepartReceiveWeekSum marketServeRecord = BeanTransform.copyProperties(to, AreaDepartReceiveWeekSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaDepartReceiveWeekSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaDepartReceiveWeekSumBO.class);
        return bo;
    }

    /**
     * 更新地区部门领用周汇总记录
     *
     * @param to 地区部门领用周汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaDepartReceiveWeekSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaDepartReceiveWeekSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaDepartReceiveWeekSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区部门领用周汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaDepartReceiveWeekSum(AreaDepartReceiveWeekSumTO to, AreaDepartReceiveWeekSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区部门领用周汇总记录
     *
     * @param id 地区部门领用周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区部门领用周汇总
     *
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaDepartReceiveWeekSumBO> summary() throws SerException {
        return null;
    }

}