package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyWeekSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyWeekSum;
import com.bjike.goddess.materialsummary.service.MaterialClassifyWeekSumSer;
import com.bjike.goddess.materialsummary.to.MaterialClassifyWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资分类周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:45 ]
 * @Description: [ 物资分类周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialClassifyWeekSumApiImpl")
public class MaterialClassifyWeekSumApiImpl implements MaterialClassifyWeekSumAPI {

    @Autowired
    private MaterialClassifyWeekSumSer materialClassifyWeekSumSer;

    /**
     * 根据id查询物资分类周汇总记录
     *
     * @param id 物资分类周汇总记录唯一标识
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyWeekSumBO findById(String id) throws SerException {
        MaterialClassifyWeekSum model = materialClassifyWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialClassifyWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资分类周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialClassifyWeekSumDTO dto) throws SerException {
        return materialClassifyWeekSumSer.count(dto);
    }

    /**
     * 分页查询物资分类周汇总记录
     *
     * @param dto 物资分类周汇总记录dto
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyWeekSumBO> list(MaterialClassifyWeekSumDTO dto) throws SerException {
        return materialClassifyWeekSumSer.list(dto);
    }

    /**
     * 保存物资分类周汇总记录
     *
     * @param to 物资分类周汇总记录to
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyWeekSumBO save(MaterialClassifyWeekSumTO to) throws SerException {
        return materialClassifyWeekSumSer.save(to);
    }

    /**
     * 根据id删除物资分类周汇总记录
     *
     * @param id 物资分类周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialClassifyWeekSumSer.remove(id);
    }

    /**
     * 更新物资分类周汇总记录
     *
     * @param to 物资分类周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialClassifyWeekSumTO to) throws SerException {
        materialClassifyWeekSumSer.update(to);
    }

    /**
     * 物资分类周汇总
     *
     * @return class MaterialClassifyWeekSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyWeekSumBO> summary() throws SerException {
        return materialClassifyWeekSumSer.summary();
    }

}