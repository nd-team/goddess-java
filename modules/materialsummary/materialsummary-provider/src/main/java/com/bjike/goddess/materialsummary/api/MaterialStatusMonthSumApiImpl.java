package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusMonthSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusMonthSum;
import com.bjike.goddess.materialsummary.service.MaterialStatusMonthSumSer;
import com.bjike.goddess.materialsummary.to.MaterialStatusMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资状态月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialStatusMonthSumApiImpl")
public class MaterialStatusMonthSumApiImpl implements MaterialStatusMonthSumAPI {

    @Autowired
    private MaterialStatusMonthSumSer materialStatusMonthSumSer;

    /**
     * 根据id查询物资状态月汇总记录
     *
     * @param id 物资状态月汇总记录唯一标识
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusMonthSumBO findById(String id) throws SerException {
        MaterialStatusMonthSum model = materialStatusMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialStatusMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资状态月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialStatusMonthSumDTO dto) throws SerException {
        return materialStatusMonthSumSer.count(dto);
    }

    /**
     * 分页查询物资状态月汇总记录
     *
     * @param dto 物资状态月汇总记录dto
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusMonthSumBO> list(MaterialStatusMonthSumDTO dto) throws SerException {
        return materialStatusMonthSumSer.list(dto);
    }

    /**
     * 保存物资状态月汇总记录
     *
     * @param to 物资状态月汇总记录to
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusMonthSumBO save(MaterialStatusMonthSumTO to) throws SerException {
        return materialStatusMonthSumSer.save(to);
    }

    /**
     * 根据id删除物资状态月汇总记录
     *
     * @param id 物资状态月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialStatusMonthSumSer.remove(id);
    }

    /**
     * 更新物资状态月汇总记录
     *
     * @param to 物资状态月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialStatusMonthSumTO to) throws SerException {
        materialStatusMonthSumSer.update(to);
    }

    /**
     * 物资状态月汇总
     *
     * @return class MaterialStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusMonthSumBO> summary() throws SerException {
        return materialStatusMonthSumSer.summary();
    }

}