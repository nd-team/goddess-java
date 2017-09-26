package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;

import java.util.List;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface InterviewInforAPI {
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
     * 根据id查询面试信息
     *
     * @param id 面试信息唯一标识
     * @return class InterviewInforBO
     * @throws SerException
     */
    InterviewInforBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 面试信息dto
     * @throws SerException
     */
    Long count(InterviewInforDTO dto) throws SerException;

    /**
     * 分页查询面试信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<InterviewInforBO> list(InterviewInforDTO dto) throws SerException;

    /**
     * 保存面试信息
     *
     * @param interviewInforTO
     * @return
     * @throws SerException
     */
    InterviewInforBO save(InterviewInforTO interviewInforTO) throws SerException;

    /**
     * 根据id删除面试信息
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新面试信息
     *
     * @param interviewInforTO
     * @throws SerException
     */
    void update(InterviewInforTO interviewInforTO) throws SerException;

    /**
     * 总经办审核录取
     *
     * @param id 面试信息唯一标识
     * @param whetherPassBoss 总经办审核是否录取
     * @param bossAdvice 总经办审批意见
     * @throws SerException
     */
    void zjbAudit(String id, Boolean whetherPassBoss, String bossAdvice) throws SerException;


    /**
     * 查询所有面试信息
     */
    List<InterviewInforBO> findInterview() throws SerException;

    /**
     * 根据姓名获取通过面试的信息
     */
    InterviewInforBO findByName(String name) throws SerException;

}
