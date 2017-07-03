package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.WarrantyStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateWeekSum;
import com.bjike.goddess.materialsummary.service.WarrantyStateWeekSumSer;
import com.bjike.goddess.materialsummary.to.WarrantyStateWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保修状态周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:50 ]
 * @Description: [ 保修状态周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("warrantyStateWeekSumApiImpl")
public class WarrantyStateWeekSumApiImpl implements WarrantyStateWeekSumAPI {

    @Autowired
    private WarrantyStateWeekSumSer warrantyStateWeekSumSer;

    /**
     * 根据id查询保修状态周汇总记录
     *
     * @param id 保修状态周汇总记录唯一标识
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateWeekSumBO findById(String id) throws SerException {
        WarrantyStateWeekSum model = warrantyStateWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, WarrantyStateWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 保修状态周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(WarrantyStateWeekSumDTO dto) throws SerException {
        return warrantyStateWeekSumSer.count(dto);
    }

    /**
     * 分页查询保修状态周汇总记录
     *
     * @param dto 保修状态周汇总记录dto
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateWeekSumBO> list(WarrantyStateWeekSumDTO dto) throws SerException {
        return warrantyStateWeekSumSer.list(dto);
    }

    /**
     * 保存保修状态周汇总记录
     *
     * @param to 保修状态周汇总记录to
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateWeekSumBO save(WarrantyStateWeekSumTO to) throws SerException {
        return warrantyStateWeekSumSer.save(to);
    }

    /**
     * 根据id删除保修状态周汇总记录
     *
     * @param id 保修状态周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        warrantyStateWeekSumSer.remove(id);
    }

    /**
     * 更新保修状态周汇总记录
     *
     * @param to 保修状态周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(WarrantyStateWeekSumTO to) throws SerException {
        warrantyStateWeekSumSer.update(to);
    }

    /**
     * 保修状态周汇总
     *
     * @return class WarrantyStateWeekSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateWeekSumBO> summary() throws SerException {
        return warrantyStateWeekSumSer.summary();
    }

}