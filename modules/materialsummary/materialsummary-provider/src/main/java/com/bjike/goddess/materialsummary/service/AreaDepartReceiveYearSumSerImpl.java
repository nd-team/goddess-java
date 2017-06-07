package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveYearSum;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveYearSumTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区部门领用年汇总记录业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用年汇总记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class AreaDepartReceiveYearSumSerImpl extends ServiceImpl<AreaDepartReceiveYearSum, AreaDepartReceiveYearSumDTO> implements AreaDepartReceiveYearSumSer {

    /**
     * 分页查询地区部门领用年汇总记录
     *
     * @param dto 地区部门领用年汇总记录dto
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveYearSumBO> list(AreaDepartReceiveYearSumDTO dto) throws SerException {
        List<AreaDepartReceiveYearSum> list = super.findByPage(dto);
        List<AreaDepartReceiveYearSumBO> listBO = BeanTransform.copyProperties(list, AreaDepartReceiveYearSumBO.class);
        return listBO;
    }

    /**
     * 保存地区部门领用年汇总记录
     *
     * @param to 保存地区部门领用年汇总记录to
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public AreaDepartReceiveYearSumBO save(AreaDepartReceiveYearSumTO to) throws SerException {
        AreaDepartReceiveYearSum marketServeRecord = BeanTransform.copyProperties(to, AreaDepartReceiveYearSum.class, true);
        marketServeRecord = super.save(marketServeRecord);
        AreaDepartReceiveYearSumBO bo = BeanTransform.copyProperties(marketServeRecord, AreaDepartReceiveYearSumBO.class);
        return bo;
    }

    /**
     * 更新地区部门领用年汇总记录
     *
     * @param to 地区部门领用年汇总记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(AreaDepartReceiveYearSumTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            AreaDepartReceiveYearSum model = super.findById(to.getId());
            if (model != null) {
                updateAreaDepartReceiveYearSum(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新地区部门领用年汇总记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateAreaDepartReceiveYearSum(AreaDepartReceiveYearSumTO to, AreaDepartReceiveYearSum model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除地区部门领用年汇总记录
     *
     * @param id 地区部门领用年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 地区部门领用年汇总
     *
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaDepartReceiveYearSumBO> summary() throws SerException {
        return null;
    }

}