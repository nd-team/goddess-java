package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusYearSum;
import com.bjike.goddess.materialsummary.service.AreaBuyStatusYearSumSer;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusYearSumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区购买情况年汇总记录业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:56 ]
 * @Description: [ 地区购买情况年汇总记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("areaBuyStatusYearSumApiImpl")
public class AreaBuyStatusYearSumApiImpl implements AreaBuyStatusYearSumAPI {

    @Autowired
    private AreaBuyStatusYearSumSer areaBuyStatusYearSumSer;

    /**
     * 根据id查询地区购买情况年汇总记录
     *
     * @param id 地区购买情况年汇总记录唯一标识
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusYearSumBO findById(String id) throws SerException {
        AreaBuyStatusYearSum model = areaBuyStatusYearSumSer.findById(id);
        return BeanTransform.copyProperties(model, AreaBuyStatusYearSumBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 地区购买情况年汇总记录dto
     * @throws SerException
     */
    @Override
    public Long count(AreaBuyStatusYearSumDTO dto) throws SerException {
        return areaBuyStatusYearSumSer.count(dto);
    }

    /**
     * 分页查询地区购买情况年汇总记录
     *
     * @param dto 地区购买情况年汇总记录dto
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusYearSumBO> list(AreaBuyStatusYearSumDTO dto) throws SerException {
        return areaBuyStatusYearSumSer.list(dto);
    }

    /**
     * 保存地区购买情况年汇总记录
     *
     * @param to 地区购买情况年汇总记录to
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    @Override
    public AreaBuyStatusYearSumBO save(AreaBuyStatusYearSumTO to) throws SerException {
        return areaBuyStatusYearSumSer.save(to);
    }

    /**
     * 根据id删除地区购买情况年汇总记录
     *
     * @param id 地区购买情况年汇总记录唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        areaBuyStatusYearSumSer.remove(id);
    }

    /**
     * 更新地区购买情况年汇总记录
     *
     * @param to 地区购买情况年汇总记录to
     * @throws SerException
     */
    @Override
    public void update(AreaBuyStatusYearSumTO to) throws SerException {
        areaBuyStatusYearSumSer.update(to);
    }

    /**
     * 地区购买情况年汇总
     *
     * @return class AreaBuyStatusYearSumBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusYearSumBO> summary() throws SerException {
        return areaBuyStatusYearSumSer.summary();
    }

}