package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.PrizeDetailBO;
import com.bjike.goddess.firmreward.dto.PrizeDetailDTO;
import com.bjike.goddess.firmreward.entity.PrizeDetail;
import com.bjike.goddess.firmreward.service.PrizeDetailSer;
import com.bjike.goddess.firmreward.to.PrizeDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 奖品明细业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:16 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("prizeDetailApiImpl")
public class PrizeDetailApiImpl implements PrizeDetailAPI {

    @Autowired
    private PrizeDetailSer prizeDetailSer;

    /**
     * 根据id查询奖品明细
     *
     * @param id 奖品明细唯一标识
     * @return class PrizeDetailBO
     * @throws SerException
     */
    @Override
    public PrizeDetailBO findById(String id) throws SerException {
        PrizeDetail model = prizeDetailSer.findById(id);
        return BeanTransform.copyProperties(model, PrizeDetailBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 奖品明细dto
     * @throws SerException
     */
    @Override
    public Long count(PrizeDetailDTO dto) throws SerException {
        return prizeDetailSer.count(dto);
    }

    /**
     * 分页查询奖品明细
     *
     * @return class PrizeDetailBO
     * @throws SerException
     */
    @Override
    public List<PrizeDetailBO> list(PrizeDetailDTO dto) throws SerException {
        return prizeDetailSer.list(dto);
    }

    /**
     * 保存奖品明细
     *
     * @param to 奖品明细to
     * @return class PrizeDetailBO
     * @throws SerException
     */
    @Override
    public PrizeDetailBO save(PrizeDetailTO to) throws SerException {
        return prizeDetailSer.save(to);
    }

    /**
     * 根据id删除奖品明细
     *
     * @param id 奖品明细唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        prizeDetailSer.remove(id);
    }

    /**
     * 更新奖品明细
     *
     * @param to 奖品明细to
     * @throws SerException
     */
    @Override
    public void update(PrizeDetailTO to) throws SerException {
        prizeDetailSer.update(to);
    }
}