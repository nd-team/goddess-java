package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateWeekSum;
import com.bjike.goddess.materialsummary.service.RepairStateWeekSumSer;
import com.bjike.goddess.materialsummary.to.RepairStateWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维修状态周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ 维修状态周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("repairStateWeekSumApiImpl")
public class RepairStateWeekSumApiImpl implements RepairStateWeekSumAPI {

    @Autowired
    private RepairStateWeekSumSer repairStateWeekSumSer;

    /**
     * 根据id查询维修状态周汇总记录
     *
     * @param id 维修状态周汇总记录唯一标识
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    @Override
    public RepairStateWeekSumBO findById(String id) throws SerException {
        RepairStateWeekSum model = repairStateWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, RepairStateWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 维修状态周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(RepairStateWeekSumDTO dto) throws SerException {
        return repairStateWeekSumSer.count(dto);
    }

    /**
     * 分页查询维修状态周汇总记录
     *
     * @param dto 维修状态周汇总记录dto
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateWeekSumBO> list(RepairStateWeekSumDTO dto) throws SerException {
        return repairStateWeekSumSer.list(dto);
    }

    /**
     * 保存维修状态周汇总记录
     *
     * @param to 维修状态周汇总记录to
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    @Override
    public RepairStateWeekSumBO save(RepairStateWeekSumTO to) throws SerException {
        return repairStateWeekSumSer.save(to);
    }

    /**
     * 根据id删除维修状态周汇总记录
     *
     * @param id 维修状态周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        repairStateWeekSumSer.remove(id);
    }

    /**
     * 更新维修状态周汇总记录
     *
     * @param to 维修状态周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(RepairStateWeekSumTO to) throws SerException {
        repairStateWeekSumSer.update(to);
    }

    /**
     * 维修状态周汇总
     *
     * @return class RepairStateWeekSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateWeekSumBO> summary() throws SerException {
        return repairStateWeekSumSer.summary();
    }

}