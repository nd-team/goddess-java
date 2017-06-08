package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferWeekSum;
import com.bjike.goddess.materialsummary.service.AreaTransferWeekSumSer;
import com.bjike.goddess.materialsummary.to.AreaTransferWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区调动周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:48 ]
 * @Description: [ 地区调动周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaTransferWeekSumApiImpl")
public class AreaTransferWeekSumApiImpl implements AreaTransferWeekSumAPI {

    @Autowired
    private AreaTransferWeekSumSer areaTransferWeekSumSer;

    /**
     * 根据id查询地区调动周汇总记录
     *
     * @param id 地区调动周汇总记录唯一标识
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    @Override
    public AreaTransferWeekSumBO findById(String id) throws SerException {
        AreaTransferWeekSum model = areaTransferWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaTransferWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区调动周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaTransferWeekSumDTO dto) throws SerException {
        return areaTransferWeekSumSer.count(dto);
    }

    /**
     * 分页查询地区调动周汇总记录
     *
     * @param dto 地区调动周汇总记录dto
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferWeekSumBO> list(AreaTransferWeekSumDTO dto) throws SerException {
        return areaTransferWeekSumSer.list(dto);
    }

    /**
     * 保存地区调动周汇总记录
     *
     * @param to 地区调动周汇总记录to
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    @Override
    public AreaTransferWeekSumBO save(AreaTransferWeekSumTO to) throws SerException {
        return areaTransferWeekSumSer.save(to);
    }

    /**
     * 根据id删除地区调动周汇总记录
     *
     * @param id 地区调动周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaTransferWeekSumSer.remove(id);
    }

    /**
     * 更新地区调动周汇总记录
     *
     * @param to 地区调动周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaTransferWeekSumTO to) throws SerException {
        areaTransferWeekSumSer.update(to);
    }

    /**
     * 地区调动周汇总
     *
     * @return class AreaTransferWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferWeekSumBO> summary() throws SerException {
        return areaTransferWeekSumSer.summary();
    }

}