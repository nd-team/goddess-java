package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveWeekSum;
import com.bjike.goddess.materialsummary.service.AreaDepartReceiveWeekSumSer;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区部门领用周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaDepartReceiveWeekSumApiImpl")
public class AreaDepartReceiveWeekSumApiImpl implements AreaDepartReceiveWeekSumAPI {

    @Autowired
    private AreaDepartReceiveWeekSumSer areaDepartReceiveWeekSumSer;

    /**
     * 根据id查询地区部门领用周汇总记录
     *
     * @param id 地区部门领用周汇总记录唯一标识
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveWeekSumBO findById(String id) throws SerException {
        AreaDepartReceiveWeekSum model = areaDepartReceiveWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaDepartReceiveWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区部门领用周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaDepartReceiveWeekSumDTO dto) throws SerException {
        return areaDepartReceiveWeekSumSer.count(dto);
    }

    /**
     * 分页查询地区部门领用周汇总记录
     *
     * @param dto 地区部门领用周汇总记录dto
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveWeekSumBO> list(AreaDepartReceiveWeekSumDTO dto) throws SerException {
        return areaDepartReceiveWeekSumSer.list(dto);
    }

    /**
     * 保存地区部门领用周汇总记录
     *
     * @param to 地区部门领用周汇总记录to
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveWeekSumBO save(AreaDepartReceiveWeekSumTO to) throws SerException {
        return areaDepartReceiveWeekSumSer.save(to);
    }

    /**
     * 根据id删除地区部门领用周汇总记录
     *
     * @param id 地区部门领用周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaDepartReceiveWeekSumSer.remove(id);
    }

    /**
     * 更新地区部门领用周汇总记录
     *
     * @param to 地区部门领用周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaDepartReceiveWeekSumTO to) throws SerException {
        areaDepartReceiveWeekSumSer.update(to);
    }

    /**
     * 地区部门领用周汇总
     *
     * @return class AreaDepartReceiveWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveWeekSumBO> summary() throws SerException {
        return areaDepartReceiveWeekSumSer.summary();
    }

}