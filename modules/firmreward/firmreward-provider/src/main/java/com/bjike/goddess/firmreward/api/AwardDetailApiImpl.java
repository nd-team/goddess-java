package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.dto.AwardDetailDTO;
import com.bjike.goddess.firmreward.service.AwardDetailSer;
import com.bjike.goddess.firmreward.to.AwardDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获奖明细业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("awardDetailApiImpl")
public class AwardDetailApiImpl implements AwardDetailAPI {

    @Autowired
    private AwardDetailSer awardDetailSer;

    /**
     * 分页查询获奖明细
     *
     * @return class AwardDetailBO
     * @throws SerException
     */
    @Override
    public List<AwardDetailBO> list(AwardDetailDTO dto) throws SerException {
        return awardDetailSer.list(dto);
    }

    /**
     * 保存获奖明细
     *
     * @param to 获奖明细to
     * @return class AwardDetailBO
     * @throws SerException
     */
    @Override
    public AwardDetailBO save(AwardDetailTO to) throws SerException {
        return awardDetailSer.save(to);
    }

    /**
     * 根据id删除获奖明细
     *
     * @param id 获奖明细唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        awardDetailSer.remove(id);
    }

    /**
     * 更新获奖明细
     *
     * @param to 获奖明细to
     * @throws SerException
     */
    @Override
    public void update(AwardDetailTO to) throws SerException {
        awardDetailSer.update(to);
    }
}