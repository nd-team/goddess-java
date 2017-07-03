package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusWeekSum;
import com.bjike.goddess.materialsummary.service.AreaBuyStatusWeekSumSer;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusWeekSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区购买情况周汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:54 ]
 * @Description: [ 地区购买情况周汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaBuyStatusWeekSumApiImpl")
public class AreaBuyStatusWeekSumApiImpl implements AreaBuyStatusWeekSumAPI {

    @Autowired
    private AreaBuyStatusWeekSumSer areaBuyStatusWeekSumSer;

    /**
     * 根据id查询地区购买情况周汇总记录
     *
     * @param id 地区购买情况周汇总记录唯一标识
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusWeekSumBO findById(String id) throws SerException {
        AreaBuyStatusWeekSum model = areaBuyStatusWeekSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaBuyStatusWeekSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区购买情况周汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaBuyStatusWeekSumDTO dto) throws SerException {
        return areaBuyStatusWeekSumSer.count(dto);
    }

    /**
     * 分页查询地区购买情况周汇总记录
     *
     * @param dto 地区购买情况周汇总记录dto
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusWeekSumBO> list(AreaBuyStatusWeekSumDTO dto) throws SerException {
        return areaBuyStatusWeekSumSer.list(dto);
    }

    /**
     * 保存地区购买情况周汇总记录
     *
     * @param to 地区购买情况周汇总记录to
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusWeekSumBO save(AreaBuyStatusWeekSumTO to) throws SerException {
        return areaBuyStatusWeekSumSer.save(to);
    }

    /**
     * 根据id删除地区购买情况周汇总记录
     *
     * @param id 地区购买情况周汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaBuyStatusWeekSumSer.remove(id);
    }

    /**
     * 更新地区购买情况周汇总记录
     *
     * @param to 地区购买情况周汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaBuyStatusWeekSumTO to) throws SerException {
        areaBuyStatusWeekSumSer.update(to);
    }

    /**
     * 地区购买情况周汇总
     *
     * @return class AreaBuyStatusWeekSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusWeekSumBO> summary() throws SerException {
        return areaBuyStatusWeekSumSer.summary();
    }

}