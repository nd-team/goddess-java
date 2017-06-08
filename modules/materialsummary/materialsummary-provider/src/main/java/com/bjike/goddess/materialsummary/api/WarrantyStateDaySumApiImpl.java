package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.WarrantyStateDaySumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateDaySumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateDaySum;
import com.bjike.goddess.materialsummary.service.WarrantyStateDaySumSer;
import com.bjike.goddess.materialsummary.to.WarrantyStateDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保修状态日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:05 ]
 * @Description: [ 保修状态日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("warrantyStateDaySumApiImpl")
public class WarrantyStateDaySumApiImpl implements WarrantyStateDaySumAPI {

    @Autowired
    private WarrantyStateDaySumSer warrantyStateDaySumSer;

    /**
     * 根据id查询保修状态日汇总记录
     *
     * @param id 保修状态日汇总记录唯一标识
     * @return class WarrantyStateDaySumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateDaySumBO findById(String id) throws SerException {
        WarrantyStateDaySum model = warrantyStateDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, WarrantyStateDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 保修状态日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(WarrantyStateDaySumDTO dto) throws SerException {
        return warrantyStateDaySumSer.count(dto);
    }

    /**
     * 分页查询保修状态日汇总记录
     *
     * @param dto 保修状态日汇总记录dto
     * @return class WarrantyStateDaySumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateDaySumBO> list(WarrantyStateDaySumDTO dto) throws SerException {
        return warrantyStateDaySumSer.list(dto);
    }

    /**
     * 保存保修状态日汇总记录
     *
     * @param to 保修状态日汇总记录to
     * @return class WarrantyStateDaySumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateDaySumBO save(WarrantyStateDaySumTO to) throws SerException {
        return warrantyStateDaySumSer.save(to);
    }

    /**
     * 根据id删除保修状态日汇总记录
     *
     * @param id 保修状态日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        warrantyStateDaySumSer.remove(id);
    }

    /**
     * 更新保修状态日汇总记录
     *
     * @param to 保修状态日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(WarrantyStateDaySumTO to) throws SerException {
        warrantyStateDaySumSer.update(to);
    }

    /**
     * 保修状态日汇总
     *
     * @return class WarrantyStateDaySumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateDaySumBO> summary() throws SerException {
        return warrantyStateDaySumSer.summary();
    }

}