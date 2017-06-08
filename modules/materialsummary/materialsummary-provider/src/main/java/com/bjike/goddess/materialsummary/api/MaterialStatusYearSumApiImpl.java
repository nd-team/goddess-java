package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialStatusYearSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusYearSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusYearSum;
import com.bjike.goddess.materialsummary.service.MaterialStatusYearSumSer;
import com.bjike.goddess.materialsummary.to.MaterialStatusYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资状态年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ 物资状态年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialStatusYearSumApiImpl")
public class MaterialStatusYearSumApiImpl implements MaterialStatusYearSumAPI {

    @Autowired
    private MaterialStatusYearSumSer materialStatusYearSumSer;

    /**
     * 根据id查询物资状态年汇总记录
     *
     * @param id 物资状态年汇总记录唯一标识
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusYearSumBO findById(String id) throws SerException {
        MaterialStatusYearSum model = materialStatusYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialStatusYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资状态年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialStatusYearSumDTO dto) throws SerException {
        return materialStatusYearSumSer.count(dto);
    }

    /**
     * 分页查询物资状态年汇总记录
     *
     * @param dto 物资状态年汇总记录dto
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusYearSumBO> list(MaterialStatusYearSumDTO dto) throws SerException {
        return materialStatusYearSumSer.list(dto);
    }

    /**
     * 保存物资状态年汇总记录
     *
     * @param to 物资状态年汇总记录to
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    @Override
    public MaterialStatusYearSumBO save(MaterialStatusYearSumTO to) throws SerException {
        return materialStatusYearSumSer.save(to);
    }

    /**
     * 根据id删除物资状态年汇总记录
     *
     * @param id 物资状态年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialStatusYearSumSer.remove(id);
    }

    /**
     * 更新物资状态年汇总记录
     *
     * @param to 物资状态年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialStatusYearSumTO to) throws SerException {
        materialStatusYearSumSer.update(to);
    }

    /**
     * 物资状态年汇总
     *
     * @return class MaterialStatusYearSumBO
     * @throws SerException
     */
    @Override
    public List<MaterialStatusYearSumBO> summary() throws SerException {
        return materialStatusYearSumSer.summary();
    }

}