package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusYearSum;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区购买情况年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:56 ]
 * @Description: [ 地区购买情况年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaBuyStatusYearSumSerImpl extends ServiceImpl<AreaBuyStatusYearSum, AreaBuyStatusYearSumDTO> implements AreaBuyStatusYearSumSer {

    /**
     * 分页查询地区购买情况年汇总记录
     *
     * @param dto 地区购买情况年汇总记录dto
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusYearSumBO> list(AreaBuyStatusYearSumDTO dto) throws SerException {
        List<AreaBuyStatusYearSum> list = super.findByPage(dto);
        List<AreaBuyStatusYearSumBO> listBO = BeanTransform.copyProperties(list, AreaBuyStatusYearSumBO.class);
        return listBO;
    }

    /**
     * 保存地区购买情况年汇总记录
     *
     * @param to 保存地区购买情况年汇总记录to
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaBuyStatusYearSumBO save(AreaBuyStatusYearSumTO to) throws SerException {
        AreaBuyStatusYearSum marketServeRecord = BeanTransform.copyProperties(to, AreaBuyStatusYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaBuyStatusYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaBuyStatusYearSumBO.class);
        return bo;
    }

    /**
     * 更新地区购买情况年汇总记录
     *
     * @param to 地区购买情况年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaBuyStatusYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaBuyStatusYearSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaBuyStatusYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区购买情况年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaBuyStatusYearSum(AreaBuyStatusYearSumTO to, AreaBuyStatusYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区购买情况年汇总记录
     *
     * @param id 地区购买情况年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区购买情况年汇总
     *
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaBuyStatusYearSumBO> summary() throws SerException {
        return null;
    }

}