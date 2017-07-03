package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferMonthSum;
import com.bjike.goddess.materialsummary.service.AreaTransferMonthSumSer;
import com.bjike.goddess.materialsummary.to.AreaTransferMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区调动月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:48 ]
 * @Description: [ 地区调动月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaTransferMonthSumApiImpl")
public class AreaTransferMonthSumApiImpl implements AreaTransferMonthSumAPI {

    @Autowired
    private AreaTransferMonthSumSer areaTransferMonthSumSer;

    /**
     * 根据id查询地区调动月汇总记录
     *
     * @param id 地区调动月汇总记录唯一标识
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    @Override
    public AreaTransferMonthSumBO findById(String id) throws SerException {
        AreaTransferMonthSum model = areaTransferMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaTransferMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区调动月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaTransferMonthSumDTO dto) throws SerException {
        return areaTransferMonthSumSer.count(dto);
    }

    /**
     * 分页查询地区调动月汇总记录
     *
     * @param dto 地区调动月汇总记录dto
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferMonthSumBO> list(AreaTransferMonthSumDTO dto) throws SerException {
        return areaTransferMonthSumSer.list(dto);
    }

    /**
     * 保存地区调动月汇总记录
     *
     * @param to 地区调动月汇总记录to
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    @Override
    public AreaTransferMonthSumBO save(AreaTransferMonthSumTO to) throws SerException {
        return areaTransferMonthSumSer.save(to);
    }

    /**
     * 根据id删除地区调动月汇总记录
     *
     * @param id 地区调动月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaTransferMonthSumSer.remove(id);
    }

    /**
     * 更新地区调动月汇总记录
     *
     * @param to 地区调动月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaTransferMonthSumTO to) throws SerException {
        areaTransferMonthSumSer.update(to);
    }

    /**
     * 地区调动月汇总
     *
     * @return class AreaTransferMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferMonthSumBO> summary() throws SerException {
        return areaTransferMonthSumSer.summary();
    }

}