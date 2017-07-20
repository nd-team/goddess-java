package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandTO;

import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitDemandAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据id查询未入职原因
     *
     * @param id 未入职原因唯一标识
     * @return class RecruitDemandBO
     * @throws SerException
     */
    RecruitDemandBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 未入职原因dto
     * @throws SerException
     */
    Long count(RecruitDemandDTO dto) throws SerException;

    /**
     * 分页查询招聘需求
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RecruitDemandBO> list(RecruitDemandDTO dto) throws SerException;

    /**
     * 保存招聘需求
     *
     * @param recruitDemandTO
     * @return
     * @throws SerException
     */
    RecruitDemandBO save(RecruitDemandTO recruitDemandTO) throws SerException;

    /**
     * 根据id删除招聘需求
     *
     * @param id 招聘需求id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新招聘需求
     *
     * @param recruitDemandTO
     * @throws SerException
     */
    void update(RecruitDemandTO recruitDemandTO) throws SerException;
}
