package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.WarrantyStateYearSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateYearSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateYearSum;
import com.bjike.goddess.materialsummary.service.WarrantyStateYearSumSer;
import com.bjike.goddess.materialsummary.to.WarrantyStateYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保修状态年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:16 ]
 * @Description: [ 保修状态年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("warrantyStateYearSumApiImpl")
public class WarrantyStateYearSumApiImpl implements WarrantyStateYearSumAPI {

    @Autowired
    private WarrantyStateYearSumSer warrantyStateYearSumSer;

    /**
     * 根据id查询保修状态年汇总记录
     *
     * @param id 保修状态年汇总记录唯一标识
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateYearSumBO findById(String id) throws SerException {
        WarrantyStateYearSum model = warrantyStateYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, WarrantyStateYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 保修状态年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(WarrantyStateYearSumDTO dto) throws SerException {
        return warrantyStateYearSumSer.count(dto);
    }

    /**
     * 分页查询保修状态年汇总记录
     *
     * @param dto 保修状态年汇总记录dto
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateYearSumBO> list(WarrantyStateYearSumDTO dto) throws SerException {
        return warrantyStateYearSumSer.list(dto);
    }

    /**
     * 保存保修状态年汇总记录
     *
     * @param to 保修状态年汇总记录to
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    @Override
    public WarrantyStateYearSumBO save(WarrantyStateYearSumTO to) throws SerException {
        return warrantyStateYearSumSer.save(to);
    }

    /**
     * 根据id删除保修状态年汇总记录
     *
     * @param id 保修状态年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        warrantyStateYearSumSer.remove(id);
    }

    /**
     * 更新保修状态年汇总记录
     *
     * @param to 保修状态年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(WarrantyStateYearSumTO to) throws SerException {
        warrantyStateYearSumSer.update(to);
    }

    /**
     * 保修状态年汇总
     *
     * @return class WarrantyStateYearSumBO
     * @throws SerException
     */
    @Override
    public List<WarrantyStateYearSumBO> summary() throws SerException {
        return warrantyStateYearSumSer.summary();
    }

}