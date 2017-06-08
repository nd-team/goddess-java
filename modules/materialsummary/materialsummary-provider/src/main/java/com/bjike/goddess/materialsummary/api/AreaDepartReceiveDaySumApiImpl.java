package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveDaySum;
import com.bjike.goddess.materialsummary.service.AreaDepartReceiveDaySumSer;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区部门领用日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:29 ]
 * @Description: [ 地区部门领用日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaDepartReceiveDaySumApiImpl")
public class AreaDepartReceiveDaySumApiImpl implements AreaDepartReceiveDaySumAPI {

    @Autowired
    private AreaDepartReceiveDaySumSer areaDepartReceiveDaySumSer;

    /**
     * 根据id查询地区部门领用日汇总记录
     *
     * @param id 地区部门领用日汇总记录唯一标识
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveDaySumBO findById(String id) throws SerException {
        AreaDepartReceiveDaySum model = areaDepartReceiveDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaDepartReceiveDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区部门领用日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaDepartReceiveDaySumDTO dto) throws SerException {
        return areaDepartReceiveDaySumSer.count(dto);
    }

    /**
     * 分页查询地区部门领用日汇总记录
     *
     * @param dto 地区部门领用日汇总记录dto
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveDaySumBO> list(AreaDepartReceiveDaySumDTO dto) throws SerException {
        return areaDepartReceiveDaySumSer.list(dto);
    }

    /**
     * 保存地区部门领用日汇总记录
     *
     * @param to 地区部门领用日汇总记录to
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveDaySumBO save(AreaDepartReceiveDaySumTO to) throws SerException {
        return areaDepartReceiveDaySumSer.save(to);
    }

    /**
     * 根据id删除地区部门领用日汇总记录
     *
     * @param id 地区部门领用日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaDepartReceiveDaySumSer.remove(id);
    }

    /**
     * 更新地区部门领用日汇总记录
     *
     * @param to 地区部门领用日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaDepartReceiveDaySumTO to) throws SerException {
        areaDepartReceiveDaySumSer.update(to);
    }

    /**
     * 地区部门领用日汇总
     *
     * @return class AreaDepartReceiveDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveDaySumBO> summary() throws SerException {
        return areaDepartReceiveDaySumSer.summary();
    }
}