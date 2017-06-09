package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.WarrantyStateMonthSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateMonthSum;
import com.bjike.goddess.materialsummary.service.WarrantyStateMonthSumSer;
import com.bjike.goddess.materialsummary.to.WarrantyStateMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保修状态月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:15 ]
 * @Description: [ 保修状态月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("warrantyStateMonthSumApiImpl")
public class WarrantyStateMonthSumApiImpl implements WarrantyStateMonthSumAPI {

    @Autowired
    private WarrantyStateMonthSumSer warrantyStateMonthSumSer;

    /**
     * 根据id查询保修状态月汇总记录
     *
     * @param id 保修状态月汇总记录唯一标识
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateMonthSumBO findById(String id) throws SerException {
        WarrantyStateMonthSum model = warrantyStateMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, WarrantyStateMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 保修状态月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(WarrantyStateMonthSumDTO dto) throws SerException {
        return warrantyStateMonthSumSer.count(dto);
    }

    /**
     * 分页查询保修状态月汇总记录
     *
     * @param dto 保修状态月汇总记录dto
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateMonthSumBO> list(WarrantyStateMonthSumDTO dto) throws SerException {
        return warrantyStateMonthSumSer.list(dto);
    }

    /**
     * 保存保修状态月汇总记录
     *
     * @param to 保修状态月汇总记录to
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateMonthSumBO save(WarrantyStateMonthSumTO to) throws SerException {
        return warrantyStateMonthSumSer.save(to);
    }

    /**
     * 根据id删除保修状态月汇总记录
     *
     * @param id 保修状态月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        warrantyStateMonthSumSer.remove(id);
    }

    /**
     * 更新保修状态月汇总记录
     *
     * @param to 保修状态月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(WarrantyStateMonthSumTO to) throws SerException {
        warrantyStateMonthSumSer.update(to);
    }

    /**
     * 保修状态月汇总
     *
     * @return class WarrantyStateMonthSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateMonthSumBO> summary() throws SerException {
        return warrantyStateMonthSumSer.summary();
    }

}