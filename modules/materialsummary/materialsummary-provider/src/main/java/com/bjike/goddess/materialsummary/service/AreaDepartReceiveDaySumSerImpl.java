package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveDaySum;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区部门领用日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:29 ]
 * @Description: [ 地区部门领用日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaDepartReceiveDaySumSerImpl extends ServiceImpl<AreaDepartReceiveDaySum, AreaDepartReceiveDaySumDTO> implements AreaDepartReceiveDaySumSer {

    /**
     * 分页查询地区部门领用日汇总记录
     *
     * @param dto 地区部门领用日汇总记录dto
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveDaySumBO> list(AreaDepartReceiveDaySumDTO dto) throws SerException {
        List<AreaDepartReceiveDaySum> list = super.findByPage(dto);
        List<AreaDepartReceiveDaySumBO> listBO = BeanTransform.copyProperties(list, AreaDepartReceiveDaySumBO.class);
        return listBO;
    }

    /**
     * 保存地区部门领用日汇总记录
     *
     * @param to 保存地区部门领用日汇总记录to
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaDepartReceiveDaySumBO save(AreaDepartReceiveDaySumTO to) throws SerException {
        AreaDepartReceiveDaySum marketServeRecord = BeanTransform.copyProperties(to, AreaDepartReceiveDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaDepartReceiveDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaDepartReceiveDaySumBO.class);
        return bo;
    }

    /**
     * 更新地区部门领用日汇总记录
     *
     * @param to 地区部门领用日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaDepartReceiveDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaDepartReceiveDaySum model = super.findById(to.getId());
            if (model != null) {
                updateAreaDepartReceiveDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区部门领用日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaDepartReceiveDaySum(AreaDepartReceiveDaySumTO to, AreaDepartReceiveDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区部门领用日汇总记录
     *
     * @param id 地区部门领用日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区部门领用日汇总
     *
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaDepartReceiveDaySumBO> summary() throws SerException {
        return null;
    }

}