package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferDaySum;
import com.bjike.goddess.materialsummary.service.AreaTransferDaySumSer;
import com.bjike.goddess.materialsummary.to.AreaTransferDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区调动日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:50 ]
 * @Description: [ 地区调动日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaTransferDaySumApiImpl")
public class AreaTransferDaySumApiImpl implements AreaTransferDaySumAPI {

    @Autowired
    private AreaTransferDaySumSer areaTransferDaySumSer;

    /**
     * 根据id查询地区调动日汇总记录
     *
     * @param id 地区调动日汇总记录唯一标识
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    @Override
    public AreaTransferDaySumBO findById(String id) throws SerException {
        AreaTransferDaySum model = areaTransferDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaTransferDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区调动日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaTransferDaySumDTO dto) throws SerException {
        return areaTransferDaySumSer.count(dto);
    }

    /**
     * 分页查询地区调动日汇总记录
     *
     * @param dto 地区调动日汇总记录dto
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferDaySumBO> list(AreaTransferDaySumDTO dto) throws SerException {
        return areaTransferDaySumSer.list(dto);
    }

    /**
     * 保存地区调动日汇总记录
     *
     * @param to 地区调动日汇总记录to
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    @Override
    public AreaTransferDaySumBO save(AreaTransferDaySumTO to) throws SerException {
        return areaTransferDaySumSer.save(to);
    }

    /**
     * 根据id删除地区调动日汇总记录
     *
     * @param id 地区调动日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaTransferDaySumSer.remove(id);
    }

    /**
     * 更新地区调动日汇总记录
     *
     * @param to 地区调动日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaTransferDaySumTO to) throws SerException {
        areaTransferDaySumSer.update(to);
    }

    /**
     * 地区调动日汇总
     *
     * @return class AreaTransferDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferDaySumBO> summary() throws SerException {
        return areaTransferDaySumSer.summary();
    }

}