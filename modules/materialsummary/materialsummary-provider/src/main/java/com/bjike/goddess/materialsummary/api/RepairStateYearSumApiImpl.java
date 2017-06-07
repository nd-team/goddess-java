package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.RepairStateYearSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateYearSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateYearSum;
import com.bjike.goddess.materialsummary.service.RepairStateYearSumSer;
import com.bjike.goddess.materialsummary.to.RepairStateYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维修状态年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:48 ]
 * @Description: [ 维修状态年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("repairStateYearSumApiImpl")
public class RepairStateYearSumApiImpl implements RepairStateYearSumAPI {

    @Autowired
    private RepairStateYearSumSer repairStateYearSumSer;

    /**
     * 根据id查询维修状态年汇总记录
     *
     * @param id 维修状态年汇总记录唯一标识
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    @Override
    public RepairStateYearSumBO findById(String id) throws SerException {
        RepairStateYearSum model = repairStateYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, RepairStateYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 维修状态年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(RepairStateYearSumDTO dto) throws SerException {
        return repairStateYearSumSer.count(dto);
    }

    /**
     * 分页查询维修状态年汇总记录
     *
     * @param dto 维修状态年汇总记录dto
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateYearSumBO> list(RepairStateYearSumDTO dto) throws SerException {
        return repairStateYearSumSer.list(dto);
    }

    /**
     * 保存维修状态年汇总记录
     *
     * @param to 维修状态年汇总记录to
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    @Override
    public RepairStateYearSumBO save(RepairStateYearSumTO to) throws SerException {
        return repairStateYearSumSer.save(to);
    }

    /**
     * 根据id删除维修状态年汇总记录
     *
     * @param id 维修状态年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        repairStateYearSumSer.remove(id);
    }

    /**
     * 更新维修状态年汇总记录
     *
     * @param to 维修状态年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(RepairStateYearSumTO to) throws SerException {
        repairStateYearSumSer.update(to);
    }

    /**
     * 维修状态年汇总
     *
     * @return class RepairStateYearSumBO
     * @throws SerException
     */
    @Override
    public List<RepairStateYearSumBO> summary() throws SerException {
        return repairStateYearSumSer.summary();
    }

}