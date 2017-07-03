package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusMonthSum;
import com.bjike.goddess.materialsummary.service.AreaBuyStatusMonthSumSer;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusMonthSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区购买情况月汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:54 ]
 * @Description: [ 地区购买情况月汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaBuyStatusMonthSumApiImpl")
public class AreaBuyStatusMonthSumApiImpl implements AreaBuyStatusMonthSumAPI {

    @Autowired
    private AreaBuyStatusMonthSumSer areaBuyStatusMonthSumSer;

    /**
     * 根据id查询地区购买情况月汇总记录
     *
     * @param id 地区购买情况月汇总记录唯一标识
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusMonthSumBO findById(String id) throws SerException {
        AreaBuyStatusMonthSum model = areaBuyStatusMonthSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaBuyStatusMonthSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区购买情况月汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaBuyStatusMonthSumDTO dto) throws SerException {
        return areaBuyStatusMonthSumSer.count(dto);
    }

    /**
     * 分页查询地区购买情况月汇总记录
     *
     * @param dto 地区购买情况月汇总记录dto
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusMonthSumBO> list(AreaBuyStatusMonthSumDTO dto) throws SerException {
        return areaBuyStatusMonthSumSer.list(dto);
    }

    /**
     * 保存地区购买情况月汇总记录
     *
     * @param to 地区购买情况月汇总记录to
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusMonthSumBO save(AreaBuyStatusMonthSumTO to) throws SerException {
        return areaBuyStatusMonthSumSer.save(to);
    }

    /**
     * 根据id删除地区购买情况月汇总记录
     *
     * @param id 地区购买情况月汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaBuyStatusMonthSumSer.remove(id);
    }

    /**
     * 更新地区购买情况月汇总记录
     *
     * @param to 地区购买情况月汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaBuyStatusMonthSumTO to) throws SerException {
        areaBuyStatusMonthSumSer.update(to);
    }

    /**
     * 地区购买情况月汇总记录
     *
     * @return class AreaBuyStatusMonthSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusMonthSumBO> summary() throws SerException {
        return areaBuyStatusMonthSumSer.summary();
    }

}