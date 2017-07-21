package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.dto.AwardDetailDTO;
import com.bjike.goddess.firmreward.entity.AwardDetail;
import com.bjike.goddess.firmreward.excel.SonPermissionObject;
import com.bjike.goddess.firmreward.service.AwardDetailSer;
import com.bjike.goddess.firmreward.to.AwardDetailTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;
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
     * 根据id查询获奖明细
     *
     * @param id 获奖明细唯一标识
     * @return class AwardDetailBO
     * @throws SerException
     */
    @Override
    public AwardDetailBO findById(String id) throws SerException {
        AwardDetail model = awardDetailSer.findById(id);
        return BeanTransform.copyProperties(model, AwardDetailBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 获奖明细dto
     * @throws SerException
     */
    @Override
    public Long count(AwardDetailDTO dto) throws SerException {
        return awardDetailSer.count(dto);
    }

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

    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
}