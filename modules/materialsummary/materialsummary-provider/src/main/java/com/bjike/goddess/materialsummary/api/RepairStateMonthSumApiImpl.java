package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateMonthSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateMonthSum;
import com.bjike.goddess.materialsummary.service.RepairStateMonthSumSer;
import com.bjike.goddess.materialsummary.to.RepairStateMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维修状态月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ 维修状态月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("repairStateMonthSumApiImpl")
public class RepairStateMonthSumApiImpl implements RepairStateMonthSumAPI {

    @Autowired
    private RepairStateMonthSumSer repairStateMonthSumSer;

    /**
     * 根据id查询维修状态月汇总记录
     *
     * @param id 维修状态月汇总记录唯一标识
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    @Override
    public RepairStateMonthSumBO findById(String id) throws SerException {
        RepairStateMonthSum model = repairStateMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, RepairStateMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 维修状态月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(RepairStateMonthSumDTO dto) throws SerException {
        return repairStateMonthSumSer.count(dto);
    }

    /**
     * 分页查询维修状态月汇总记录
     *
     * @param dto 维修状态月汇总记录dto
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateMonthSumBO> list(RepairStateMonthSumDTO dto) throws SerException {
        return repairStateMonthSumSer.list(dto);
    }

    /**
     * 保存维修状态月汇总记录
     *
     * @param to 维修状态月汇总记录to
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    @Override
    public RepairStateMonthSumBO save(RepairStateMonthSumTO to) throws SerException {
        return repairStateMonthSumSer.save(to);
    }

    /**
     * 根据id删除维修状态月汇总记录
     *
     * @param id 维修状态月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        repairStateMonthSumSer.remove(id);
    }

    /**
     * 更新维修状态月汇总记录
     *
     * @param to 维修状态月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(RepairStateMonthSumTO to) throws SerException {
        repairStateMonthSumSer.update(to);
    }

    /**
     * 维修状态月汇总
     *
     * @return class RepairStateMonthSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateMonthSumBO> summary() throws SerException {
        return repairStateMonthSumSer.summary();
    }

}