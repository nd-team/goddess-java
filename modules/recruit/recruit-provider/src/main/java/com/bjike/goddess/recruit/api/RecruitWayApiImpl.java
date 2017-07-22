package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.entity.RecruitWay;
import com.bjike.goddess.recruit.service.RecruitWaySer;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("recruitWayApiImpl")
public class RecruitWayApiImpl implements RecruitWayAPI {

    @Autowired
    private RecruitWaySer recruitWaySer;

    /**
     * 根据id查询招聘渠道
     *
     * @param id 招聘渠道唯一标识
     * @return class RecruitWayBO
     * @throws SerException
     */
    @Override
    public RecruitWayBO findById(String id) throws SerException {
        RecruitWay model = recruitWaySer.findById(id);
        return BeanTransform.copyProperties(model, RecruitWayBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 招聘渠道dto
     * @throws SerException
     */
    @Override
    public Long count(RecruitWayDTO dto) throws SerException {
        return recruitWaySer.count(dto);
    }

    /**
     * 分页查询招聘渠道
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitWayBO> list(RecruitWayDTO dto) throws SerException {
        return recruitWaySer.list(dto);
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
        return recruitWaySer.save(recruitWayTO);
    }

    /**
     * 根据id删除招聘渠道
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        recruitWaySer.remove(id);
    }

    /**
     * 更新招聘渠道
     *
     * @param recruitWayTO
     * @throws SerException
     */
    @Override
    public void update(RecruitWayTO recruitWayTO) throws SerException {
        recruitWaySer.update(recruitWayTO);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return recruitWaySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return recruitWaySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Set<String> allRecruitName() throws SerException {
        return recruitWaySer.allRecruitName();
    }


}
