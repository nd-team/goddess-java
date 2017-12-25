package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;

import java.util.List;
import java.util.Set;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitWayAPI {
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
     * 根据id查询招聘渠道
     *
     * @param id 招聘渠道唯一标识
     * @return class RecruitWayBO
     * @throws SerException
     */
    RecruitWayBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 招聘渠道dto
     * @throws SerException
     */
    Long count(RecruitWayDTO dto) throws SerException;

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
     * 解冻招聘渠道
     *
     * @param id
     * @throws SerException
     */
    void thaw(String id) throws SerException;

    /**
     * 冻结招聘渠道
     *
     * @param id
     * @throws SerException
     */
    void congeal(String id) throws SerException;
    /**
     * 查看所有招聘网站
     *
     * @return
     * @throws SerException
     */
    Set<String> allRecruitName() throws SerException;
}
