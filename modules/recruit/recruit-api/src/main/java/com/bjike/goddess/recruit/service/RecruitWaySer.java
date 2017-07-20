package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.entity.RecruitWay;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;

import java.util.List;
import java.util.Set;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitWaySer extends Ser<RecruitWay, RecruitWayDTO> {
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
     * 分页查询招聘渠道
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RecruitWayBO> list(RecruitWayDTO dto) throws SerException;

    /**
     * 保存招聘渠道
     *
     * @param recruitWayTO
     * @return
     * @throws SerException
     */
    RecruitWayBO save(RecruitWayTO recruitWayTO) throws SerException;

    /**
     * 根据id删除招聘渠道
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新招聘渠道
     *
     * @param recruitWayTO
     * @throws SerException
     */
    void update(RecruitWayTO recruitWayTO) throws SerException;

    /**
     * 查看所有招聘网站
     *
     * @return
     * @throws SerException
     */
    Set<String> allRecruitName() throws SerException;
}
