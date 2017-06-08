package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyDaySumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyDaySum;
import com.bjike.goddess.materialsummary.service.MaterialClassifyDaySumSer;
import com.bjike.goddess.materialsummary.to.MaterialClassifyDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资分类日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:27 ]
 * @Description: [ 物资分类日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialClassifyDaySumApiImpl")
public class MaterialClassifyDaySumApiImpl implements MaterialClassifyDaySumAPI {

    @Autowired
    private MaterialClassifyDaySumSer materialClassifyDaySumSer;

    /**
     * 根据id查询入库来源年汇总记录
     *
     * @param id 入库来源年汇总记录唯一标识
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyDaySumBO findById(String id) throws SerException {
        MaterialClassifyDaySum model = materialClassifyDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialClassifyDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 入库来源年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialClassifyDaySumDTO dto) throws SerException {
        return materialClassifyDaySumSer.count(dto);
    }

    /**
     * 分页查询入库来源年汇总记录
     *
     * @param dto 入库来源年汇总记录dto
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyDaySumBO> list(MaterialClassifyDaySumDTO dto) throws SerException {
        return materialClassifyDaySumSer.list(dto);
    }

    /**
     * 保存入库来源年汇总记录
     *
     * @param to 入库来源年汇总记录to
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    @Override
    public MaterialClassifyDaySumBO save(MaterialClassifyDaySumTO to) throws SerException {
        return materialClassifyDaySumSer.save(to);
    }

    /**
     * 根据id删除入库来源年汇总记录
     *
     * @param id 入库来源年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialClassifyDaySumSer.remove(id);
    }

    /**
     * 更新入库来源年汇总记录
     *
     * @param to 入库来源年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(MaterialClassifyDaySumTO to) throws SerException {
        materialClassifyDaySumSer.update(to);
    }

    /**
     * 入库来源年汇总
     *
     * @return class MaterialClassifyDaySumBO
     * @throws SerException
     */
    @Override
    public List<MaterialClassifyDaySumBO> summary() throws SerException {
        return materialClassifyDaySumSer.summary();
    }

}