package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveMonthSum;
import com.bjike.goddess.materialsummary.service.AreaDepartReceiveMonthSumSer;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区部门领用月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaDepartReceiveMonthSumApiImpl")
public class AreaDepartReceiveMonthSumApiImpl implements AreaDepartReceiveMonthSumAPI {

    @Autowired
    private AreaDepartReceiveMonthSumSer areaDepartReceiveMonthSumSer;

    /**
     * 根据id查询地区部门领用月汇总记录
     *
     * @param id 地区部门领用月汇总记录唯一标识
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveMonthSumBO findById(String id) throws SerException {
        AreaDepartReceiveMonthSum model = areaDepartReceiveMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaDepartReceiveMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区部门领用月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaDepartReceiveMonthSumDTO dto) throws SerException {
        return areaDepartReceiveMonthSumSer.count(dto);
    }

    /**
     * 分页查询地区部门领用月汇总记录
     *
     * @param dto 地区部门领用月汇总记录dto
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveMonthSumBO> list(AreaDepartReceiveMonthSumDTO dto) throws SerException {
        return areaDepartReceiveMonthSumSer.list(dto);
    }

    /**
     * 保存地区部门领用月汇总记录
     *
     * @param to 地区部门领用月汇总记录to
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveMonthSumBO save(AreaDepartReceiveMonthSumTO to) throws SerException {
        return areaDepartReceiveMonthSumSer.save(to);
    }

    /**
     * 根据id删除地区部门领用月汇总记录
     *
     * @param id 地区部门领用月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaDepartReceiveMonthSumSer.remove(id);
    }

    /**
     * 更新地区部门领用月汇总记录
     *
     * @param to 地区部门领用月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaDepartReceiveMonthSumTO to) throws SerException {
        areaDepartReceiveMonthSumSer.update(to);
    }

    /**
     * 地区部门领用月汇总
     *
     * @return class AreaDepartReceiveMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveMonthSumBO> summary() throws SerException {
        return areaDepartReceiveMonthSumSer.summary();
    }

}