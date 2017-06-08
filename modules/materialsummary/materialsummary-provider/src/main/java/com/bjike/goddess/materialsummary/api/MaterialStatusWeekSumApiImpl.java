package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusWeekSum;
import com.bjike.goddess.materialsummary.service.MaterialStatusWeekSumSer;
import com.bjike.goddess.materialsummary.to.MaterialStatusWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资状态周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialStatusWeekSumApiImpl")
public class MaterialStatusWeekSumApiImpl implements MaterialStatusWeekSumAPI {

    @Autowired
    private MaterialStatusWeekSumSer materialStatusWeekSumSer;

    /**
     * 根据id查询物资状态周汇总记录
     *
     * @param id 物资状态周汇总记录唯一标识
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusWeekSumBO findById(String id) throws SerException {
        MaterialStatusWeekSum model = materialStatusWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialStatusWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资状态周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialStatusWeekSumDTO dto) throws SerException {
        return materialStatusWeekSumSer.count(dto);
    }

    /**
     * 分页查询物资状态周汇总记录
     *
     * @param dto 物资状态周汇总记录dto
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusWeekSumBO> list(MaterialStatusWeekSumDTO dto) throws SerException {
        return materialStatusWeekSumSer.list(dto);
    }

    /**
     * 保存物资状态周汇总记录
     *
     * @param to 物资状态周汇总记录to
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusWeekSumBO save(MaterialStatusWeekSumTO to) throws SerException {
        return materialStatusWeekSumSer.save(to);
    }

    /**
     * 根据id删除物资状态周汇总记录
     *
     * @param id 物资状态周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialStatusWeekSumSer.remove(id);
    }

    /**
     * 更新物资状态周汇总记录
     *
     * @param to 物资状态周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialStatusWeekSumTO to) throws SerException {
        materialStatusWeekSumSer.update(to);
    }

    /**
     * 物资状态周汇总
     *
     * @return class MaterialStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusWeekSumBO> summary() throws SerException {
        return materialStatusWeekSumSer.summary();
    }

}