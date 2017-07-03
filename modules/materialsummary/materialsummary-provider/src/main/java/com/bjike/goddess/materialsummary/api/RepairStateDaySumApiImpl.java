package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateDaySumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateDaySumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateDaySum;
import com.bjike.goddess.materialsummary.service.RepairStateDaySumSer;
import com.bjike.goddess.materialsummary.to.RepairStateDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维修状态日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:52 ]
 * @Description: [ 维修状态日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("repairStateDaySumApiImpl")
public class RepairStateDaySumApiImpl implements RepairStateDaySumAPI {

    @Autowired
    private RepairStateDaySumSer mepairStateDaySumSer;

    /**
     * 根据id查询维修状态日汇总记录
     *
     * @param id 维修状态日汇总记录唯一标识
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    @Override
    public RepairStateDaySumBO findById(String id) throws SerException {
        RepairStateDaySum model = mepairStateDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, RepairStateDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 维修状态日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(RepairStateDaySumDTO dto) throws SerException {
        return mepairStateDaySumSer.count(dto);
    }

    /**
     * 分页查询维修状态日汇总记录
     *
     * @param dto 维修状态日汇总记录dto
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateDaySumBO> list(RepairStateDaySumDTO dto) throws SerException {
        return mepairStateDaySumSer.list(dto);
    }

    /**
     * 保存维修状态日汇总记录
     *
     * @param to 维修状态日汇总记录to
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    @Override
    public RepairStateDaySumBO save(RepairStateDaySumTO to) throws SerException {
        return mepairStateDaySumSer.save(to);
    }

    /**
     * 根据id删除维修状态日汇总记录
     *
     * @param id 维修状态日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        mepairStateDaySumSer.remove(id);
    }

    /**
     * 更新维修状态日汇总记录
     *
     * @param to 维修状态日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(RepairStateDaySumTO to) throws SerException {
        mepairStateDaySumSer.update(to);
    }

    /**
     * 维修状态日汇总
     *
     * @return class RepairStateDaySumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateDaySumBO> summary() throws SerException {
        return mepairStateDaySumSer.summary();
    }

}