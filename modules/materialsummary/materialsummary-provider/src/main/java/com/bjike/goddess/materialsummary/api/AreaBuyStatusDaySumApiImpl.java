package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusDaySum;
import com.bjike.goddess.materialsummary.service.AreaBuyStatusDaySumSer;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusDaySumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区购买情况日汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:51 ]
 * @Description: [ 地区购买情况日汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaBuyStatusDaySumApiImpl")
public class AreaBuyStatusDaySumApiImpl implements AreaBuyStatusDaySumAPI {

    @Autowired
    private AreaBuyStatusDaySumSer areaBuyStatusDaySumSer;

    /**
     * 根据id查询地区购买情况日汇总记录
     *
     * @param id 地区购买情况日汇总记录唯一标识
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusDaySumBO findById(String id) throws SerException {
        AreaBuyStatusDaySum model = areaBuyStatusDaySumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaBuyStatusDaySumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区购买情况日汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaBuyStatusDaySumDTO dto) throws SerException {
        return areaBuyStatusDaySumSer.count(dto);
    }

    /**
     * 分页查询地区购买情况日汇总记录
     *
     * @param dto 地区购买情况日汇总记录dto
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusDaySumBO> list(AreaBuyStatusDaySumDTO dto) throws SerException {
        return areaBuyStatusDaySumSer.list(dto);
    }

    /**
     * 保存地区购买情况日汇总记录
     *
     * @param to 地区购买情况日汇总记录to
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusDaySumBO save(AreaBuyStatusDaySumTO to) throws SerException {
        return areaBuyStatusDaySumSer.save(to);
    }

    /**
     * 根据id删除地区购买情况日汇总记录
     *
     * @param id 地区购买情况日汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaBuyStatusDaySumSer.remove(id);
    }

    /**
     * 更新地区购买情况日汇总记录
     *
     * @param to 地区购买情况日汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaBuyStatusDaySumTO to) throws SerException {
        areaBuyStatusDaySumSer.update(to);
    }

    /**
     * 地区购买情况日汇总
     * 
     * @return class AreaBuyStatusDaySumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusDaySumBO> summary() throws SerException {
        return areaBuyStatusDaySumSer.summary();
    }
}