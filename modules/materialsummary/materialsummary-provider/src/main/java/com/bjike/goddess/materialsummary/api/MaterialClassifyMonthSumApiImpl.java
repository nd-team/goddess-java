package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyMonthSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyMonthSum;
import com.bjike.goddess.materialsummary.service.MaterialClassifyMonthSumSer;
import com.bjike.goddess.materialsummary.to.MaterialClassifyMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资分类月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:46 ]
 * @Description: [ 物资分类月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialClassifyMonthSumApiImpl")
public class MaterialClassifyMonthSumApiImpl implements MaterialClassifyMonthSumAPI {

    @Autowired
    private MaterialClassifyMonthSumSer materialClassifyMonthSumSer;

    /**
     * 根据id查询物资分类月汇总记录
     *
     * @param id 物资分类月汇总记录唯一标识
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyMonthSumBO findById(String id) throws SerException {
        MaterialClassifyMonthSum model = materialClassifyMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialClassifyMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资分类月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialClassifyMonthSumDTO dto) throws SerException {
        return materialClassifyMonthSumSer.count(dto);
    }

    /**
     * 分页查询物资分类月汇总记录
     *
     * @param dto 物资分类月汇总记录dto
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyMonthSumBO> list(MaterialClassifyMonthSumDTO dto) throws SerException {
        return materialClassifyMonthSumSer.list(dto);
    }

    /**
     * 保存物资分类月汇总记录
     *
     * @param to 物资分类月汇总记录to
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyMonthSumBO save(MaterialClassifyMonthSumTO to) throws SerException {
        return materialClassifyMonthSumSer.save(to);
    }

    /**
     * 根据id删除物资分类月汇总记录
     *
     * @param id 物资分类月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialClassifyMonthSumSer.remove(id);
    }

    /**
     * 更新物资分类月汇总记录
     *
     * @param to 物资分类月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialClassifyMonthSumTO to) throws SerException {
        materialClassifyMonthSumSer.update(to);
    }

    /**
     * 物资分类月汇总
     *
     * @return class MaterialClassifyMonthSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyMonthSumBO> summary() throws SerException {
        return materialClassifyMonthSumSer.summary();
    }

}