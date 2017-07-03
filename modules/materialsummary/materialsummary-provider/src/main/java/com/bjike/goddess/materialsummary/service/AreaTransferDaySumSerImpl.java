package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferDaySum;
import com.bjike.goddess.materialsummary.to.AreaTransferDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区调动日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:50 ]
 * @Description: [ 地区调动日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaTransferDaySumSerImpl extends ServiceImpl<AreaTransferDaySum, AreaTransferDaySumDTO> implements AreaTransferDaySumSer {

    /**
     * 分页查询地区调动日汇总记录
     *
     * @param dto 地区调动日汇总记录dto
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferDaySumBO> list(AreaTransferDaySumDTO dto) throws SerException {
        List<AreaTransferDaySum> list = super.findByPage(dto);
        List<AreaTransferDaySumBO> listBO = BeanTransform.copyProperties(list, AreaTransferDaySumBO.class);
        return listBO;
    }

    /**
     * 保存地区调动日汇总记录
     *
     * @param to 保存地区调动日汇总记录to
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaTransferDaySumBO save(AreaTransferDaySumTO to) throws SerException {
        AreaTransferDaySum marketServeRecord = BeanTransform.copyProperties(to, AreaTransferDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaTransferDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaTransferDaySumBO.class);
        return bo;
    }

    /**
     * 更新地区调动日汇总记录
     *
     * @param to 地区调动日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaTransferDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaTransferDaySum model = super.findById(to.getId());
            if (model != null) {
                updateAreaTransferDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区调动日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaTransferDaySum(AreaTransferDaySumTO to, AreaTransferDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区调动日汇总记录
     *
     * @param id 地区调动日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区调动日汇总
     *
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaTransferDaySumBO> summary() throws SerException {
        return null;
    }

}