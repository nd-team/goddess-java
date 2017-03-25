package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.entity.RecruitWay;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitWaySerImpl extends ServiceImpl<RecruitWay, RecruitWayDTO> implements RecruitWaySer {

    /**
     * 分页查询招聘渠道
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitWayBO> list(RecruitWayDTO dto) throws SerException {
        List<RecruitWay> recruitWayList = super.findByPage(dto);
        List<RecruitWayBO> recruitWayBOList = BeanTransform.copyProperties(recruitWayList, RecruitWayBO.class);
        return recruitWayBOList;
    }

    /**
     * 保存招聘渠道
     *
     * @param recruitWayTO
     * @return
     * @throws SerException
     */
    @Override
    public RecruitWayBO save(RecruitWayTO recruitWayTO) throws SerException {
        RecruitWay recruitWay = BeanTransform.copyProperties(recruitWayTO, RecruitWay.class, true);
        RecruitWayBO entity = BeanTransform.copyProperties(recruitWay, RecruitWayBO.class);
        return entity;
    }

    /**
     * 修改招聘渠道
     *
     * @param recruitWayTO
     * @throws SerException
     */
    @Override
    public void update(RecruitWayTO recruitWayTO) throws SerException {
        RecruitWay recruitWay = BeanTransform.copyProperties(recruitWayTO, RecruitWay.class, true);
        super.update(recruitWay);
    }

    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
