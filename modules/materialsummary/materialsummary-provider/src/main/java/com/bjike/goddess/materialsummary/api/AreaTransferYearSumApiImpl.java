package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaTransferYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferYearSum;
import com.bjike.goddess.materialsummary.service.AreaTransferYearSumSer;
import com.bjike.goddess.materialsummary.to.AreaTransferYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区调动年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:47 ]
 * @Description: [ 地区调动年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaTransferYearSumApiImpl")
public class AreaTransferYearSumApiImpl implements AreaTransferYearSumAPI {

    @Autowired
    private AreaTransferYearSumSer areaTransferYearSumSer;

    /**
     * 根据id查询地区调动年汇总记录
     *
     * @param id 地区调动年汇总记录唯一标识
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    @Override
    public AreaTransferYearSumBO findById(String id) throws SerException {
        AreaTransferYearSum model = areaTransferYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaTransferYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区调动年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaTransferYearSumDTO dto) throws SerException {
        return areaTransferYearSumSer.count(dto);
    }

    /**
     * 分页查询地区调动年汇总记录
     *
     * @param dto 地区调动年汇总记录dto
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferYearSumBO> list(AreaTransferYearSumDTO dto) throws SerException {
        return areaTransferYearSumSer.list(dto);
    }

    /**
     * 保存地区调动年汇总记录
     *
     * @param to 地区调动年汇总记录to
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    @Override
    public AreaTransferYearSumBO save(AreaTransferYearSumTO to) throws SerException {
        return areaTransferYearSumSer.save(to);
    }

    /**
     * 根据id删除地区调动年汇总记录
     *
     * @param id 地区调动年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaTransferYearSumSer.remove(id);
    }

    /**
     * 更新地区调动年汇总记录
     *
     * @param to 地区调动年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaTransferYearSumTO to) throws SerException {
        areaTransferYearSumSer.update(to);
    }

    /**
     * 地区调动年汇总
     *
     * @return class AreaTransferYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaTransferYearSumBO> summary() throws SerException {
        return areaTransferYearSumSer.summary();
    }

}