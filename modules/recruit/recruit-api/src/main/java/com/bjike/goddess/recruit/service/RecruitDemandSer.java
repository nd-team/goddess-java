package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.entity.RecruitDemand;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandTO;

import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitDemandSer extends Ser<RecruitDemand, RecruitDemandDTO> {
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
