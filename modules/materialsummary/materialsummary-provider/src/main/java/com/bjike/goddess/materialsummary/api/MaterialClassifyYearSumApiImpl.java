package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyYearSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyYearSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyYearSum;
import com.bjike.goddess.materialsummary.service.MaterialClassifyYearSumSer;
import com.bjike.goddess.materialsummary.to.MaterialClassifyYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资分类年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:48 ]
 * @Description: [ 物资分类年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialClassifyYearSumApiImpl")
public class MaterialClassifyYearSumApiImpl implements MaterialClassifyYearSumAPI {

    @Autowired
    private MaterialClassifyYearSumSer materialClassifyYearSumSer;

    /**
     * 根据id查询物资分类年汇总记录
     *
     * @param id 物资分类年汇总记录唯一标识
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyYearSumBO findById(String id) throws SerException {
        MaterialClassifyYearSum model = materialClassifyYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialClassifyYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资分类年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialClassifyYearSumDTO dto) throws SerException {
        return materialClassifyYearSumSer.count(dto);
    }

    /**
     * 分页查询物资分类年汇总记录
     *
     * @param dto 物资分类年汇总记录dto
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyYearSumBO> list(MaterialClassifyYearSumDTO dto) throws SerException {
        return materialClassifyYearSumSer.list(dto);
    }

    /**
     * 保存物资分类年汇总记录
     *
     * @param to 物资分类年汇总记录to
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyYearSumBO save(MaterialClassifyYearSumTO to) throws SerException {
        return materialClassifyYearSumSer.save(to);
    }

    /**
     * 根据id删除物资分类年汇总记录
     *
     * @param id 物资分类年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialClassifyYearSumSer.remove(id);
    }

    /**
     * 更新物资分类年汇总记录
     *
     * @param to 物资分类年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialClassifyYearSumTO to) throws SerException {
        materialClassifyYearSumSer.update(to);
    }

    /**
     * 物资分类年汇总
     *
     * @return class MaterialClassifyYearSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyYearSumBO> summary() throws SerException {
        return materialClassifyYearSumSer.summary();
    }

}