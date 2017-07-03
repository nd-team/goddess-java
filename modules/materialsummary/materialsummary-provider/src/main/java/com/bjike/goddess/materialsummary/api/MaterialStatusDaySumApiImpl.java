package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusDaySumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusDaySum;
import com.bjike.goddess.materialsummary.service.MaterialStatusDaySumSer;
import com.bjike.goddess.materialsummary.to.MaterialStatusDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资状态日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:18 ]
 * @Description: [ 物资状态日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialStatusDaySumApiImpl")
public class MaterialStatusDaySumApiImpl implements MaterialStatusDaySumAPI {

    @Autowired
    private MaterialStatusDaySumSer materialStatusDaySumSer;

    /**
     * 根据id查询物资状态日汇总记录
     *
     * @param id 物资状态日汇总记录唯一标识
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusDaySumBO findById(String id) throws SerException {
        MaterialStatusDaySum model = materialStatusDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialStatusDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资状态日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialStatusDaySumDTO dto) throws SerException {
        return materialStatusDaySumSer.count(dto);
    }

    /**
     * 分页查询物资状态日汇总记录
     *
     * @param dto 物资状态日汇总记录dto
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusDaySumBO> list(MaterialStatusDaySumDTO dto) throws SerException {
        return materialStatusDaySumSer.list(dto);
    }

    /**
     * 保存物资状态日汇总记录
     *
     * @param to 物资状态日汇总记录to
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusDaySumBO save(MaterialStatusDaySumTO to) throws SerException {
        return materialStatusDaySumSer.save(to);
    }

    /**
     * 根据id删除物资状态日汇总记录
     *
     * @param id 物资状态日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialStatusDaySumSer.remove(id);
    }

    /**
     * 更新物资状态日汇总记录
     *
     * @param to 物资状态日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialStatusDaySumTO to) throws SerException {
        materialStatusDaySumSer.update(to);
    }

    /**
     * 物资状态日汇总
     *
     * @return class MaterialStatusDaySumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusDaySumBO> summary() throws SerException {
        return materialStatusDaySumSer.summary();
    }

}