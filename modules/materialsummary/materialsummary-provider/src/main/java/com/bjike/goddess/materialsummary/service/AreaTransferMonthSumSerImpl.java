package com.bjike.goddess.materialsummary.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferMonthSum;
import com.bjike.goddess.materialsummary.to.AreaTransferMonthSumTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区调动月汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:48 ]
 * @Description: [ 地区调动月汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaTransferMonthSumSerImpl extends ServiceImpl<AreaTransferMonthSum, AreaTransferMonthSumDTO> implements AreaTransferMonthSumSer {

    /**
     * 分页查询地区调动月汇总记录
     *
     * @param dto 地区调动月汇总记录dto
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferMonthSumBO> list(AreaTransferMonthSumDTO dto) throws SerException {
        List<AreaTransferMonthSum> list = super.findByPage(dto);
        List<AreaTransferMonthSumBO> listBO = BeanTransform.copyProperties(list, AreaTransferMonthSumBO.class);
        return listBO;
    }

    /**
     * 保存地区调动月汇总记录
     *
     * @param to 保存地区调动月汇总记录to
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaTransferMonthSumBO save(AreaTransferMonthSumTO to) throws SerException {
        AreaTransferMonthSum marketServeRecord = BeanTransform.copyProperties(to, AreaTransferMonthSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaTransferMonthSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaTransferMonthSumBO.class);
        return bo;
    }

    /**
     * 更新地区调动月汇总记录
     *
     * @param to 地区调动月汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaTransferMonthSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaTransferMonthSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaTransferMonthSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区调动月汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaTransferMonthSum(AreaTransferMonthSumTO to, AreaTransferMonthSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区调动月汇总记录
     *
     * @param id 地区调动月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区调动月汇总
     *
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaTransferMonthSumBO> summary() throws SerException {
        return null;
    }

}