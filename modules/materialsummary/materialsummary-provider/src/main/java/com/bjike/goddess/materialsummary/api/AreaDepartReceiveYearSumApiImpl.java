package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveYearSum;
import com.bjike.goddess.materialsummary.service.AreaDepartReceiveYearSumSer;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区部门领用年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ 地区部门领用年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaDepartReceiveYearSumApiImpl")
public class AreaDepartReceiveYearSumApiImpl implements AreaDepartReceiveYearSumAPI {

    @Autowired
    private AreaDepartReceiveYearSumSer areaDepartReceiveYearSumSer;

    /**
     * 根据id查询地区部门领用年汇总记录
     *
     * @param id 地区部门领用年汇总记录唯一标识
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveYearSumBO findById(String id) throws SerException {
        AreaDepartReceiveYearSum model = areaDepartReceiveYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaDepartReceiveYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区部门领用年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaDepartReceiveYearSumDTO dto) throws SerException {
        return areaDepartReceiveYearSumSer.count(dto);
    }

    /**
     * 分页查询地区部门领用年汇总记录
     *
     * @param dto 地区部门领用年汇总记录dto
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveYearSumBO> list(AreaDepartReceiveYearSumDTO dto) throws SerException {
        return areaDepartReceiveYearSumSer.list(dto);
    }

    /**
     * 保存地区部门领用年汇总记录
     *
     * @param to 地区部门领用年汇总记录to
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    @Override
    public AreaDepartReceiveYearSumBO save(AreaDepartReceiveYearSumTO to) throws SerException {
        return areaDepartReceiveYearSumSer.save(to);
    }

    /**
     * 根据id删除地区部门领用年汇总记录
     *
     * @param id 地区部门领用年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaDepartReceiveYearSumSer.remove(id);
    }

    /**
     * 更新地区部门领用年汇总记录
     *
     * @param to 地区部门领用年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaDepartReceiveYearSumTO to) throws SerException {
        areaDepartReceiveYearSumSer.update(to);
    }

    /**
     * 地区部门领用年汇总
     *
     * @return class AreaDepartReceiveYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaDepartReceiveYearSumBO> summary() throws SerException {
        return areaDepartReceiveYearSumSer.summary();
    }

}