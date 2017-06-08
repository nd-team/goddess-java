package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusDaySum;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusDaySumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区购买情况日汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:51 ]
 * @Description: [ 地区购买情况日汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaBuyStatusDaySumSerImpl extends ServiceImpl<AreaBuyStatusDaySum, AreaBuyStatusDaySumDTO> implements AreaBuyStatusDaySumSer {

    /**
     * 分页查询地区购买情况日汇总记录
     *
     * @param dto 地区购买情况日汇总记录dto
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusDaySumBO> list(AreaBuyStatusDaySumDTO dto) throws SerException {
        List<AreaBuyStatusDaySum> list = super.findByPage(dto);
        List<AreaBuyStatusDaySumBO> listBO = BeanTransform.copyProperties(list, AreaBuyStatusDaySumBO.class);
        return listBO;
    }

    /**
     * 保存地区购买情况日汇总记录
     *
     * @param to 保存地区购买情况日汇总记录to
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaBuyStatusDaySumBO save(AreaBuyStatusDaySumTO to) throws SerException {
        AreaBuyStatusDaySum marketServeRecord = BeanTransform.copyProperties(to, AreaBuyStatusDaySum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaBuyStatusDaySumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaBuyStatusDaySumBO.class);
        return bo;
    }

    /**
     * 更新地区购买情况日汇总记录
     *
     * @param to 地区购买情况日汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaBuyStatusDaySumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaBuyStatusDaySum model = super.findById(to.getId());
            if (model != null) {
                updateAreaBuyStatusDaySum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区购买情况日汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaBuyStatusDaySum(AreaBuyStatusDaySumTO to, AreaBuyStatusDaySum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区购买情况日汇总记录
     *
     * @param id 地区购买情况日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区购买情况日汇总
     *
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaBuyStatusDaySumBO> summary() throws SerException {
        return null;
    }

}