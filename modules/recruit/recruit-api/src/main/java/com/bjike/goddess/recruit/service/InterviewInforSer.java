package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.IdeaTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;

import java.util.List;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface InterviewInforSer extends Ser<InterviewInfor, InterviewInforDTO> {
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
     * 初试面试意见
     *
     * @param to
     * @throws SerException
     */
    void firstIdea(IdeaTO to) throws SerException;
    /**
     * 复试面试意见
     *
     * @param to
     * @throws SerException
     */
    void reexamineIdea(IdeaTO to) throws SerException;
    /**
     * 薪资面谈意见
     *
     * @param to
     * @throws SerException
     */
    void wagesIdea(IdeaTO to) throws SerException;
    /**
     * 总经办审核录取
     *
     * @param to
     * @throws SerException
     */
    void zjbAudit(IdeaTO to) throws SerException;
    /**
     * 入职信息获取
     *
     * @param to
     * @throws SerException
     */
    void staffEntryInfo(IdeaTO to) throws SerException;


    /**
     * 查询所有面试信息
     */
    List<InterviewInforBO> findInterview() throws SerException;

    /**
     * 根据姓名获取通过面试的信息
     */
    InterviewInforBO findByName(String name) throws SerException;
}
