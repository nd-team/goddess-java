package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ResponsibleIdeaBO;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ResponsibleIdeaTO;

import java.util.List;

/**
 * 非责任相关人意见业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ResponsibleIdeaAPI {
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
     * 非责任相关人意见列表总条数
     */
    default Long count(ResponsibleIdeaDTO dto) throws SerException {
        return null;
    }

    /**
     * 非责任相关人意见id
     */
    default ResponsibleIdeaBO getId(String id) throws SerException {
        return null;
    }

    /**
     * 非责任相关人意见
     *
     * @param dto 非责任相关人意见dto
     * @return class ResponsibleIdeaBO
     * @throws SerException
     */
    default List<ResponsibleIdeaBO> list(ResponsibleIdeaDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加非责任相关人意见
     *
     * @param to 非责任相关人意见数据to
     * @return class ResponsibleIdeaBO
     * @throws SerException
     */
    default ResponsibleIdeaBO insert(ResponsibleIdeaTO to) throws SerException {
        return null;
    }

    /**
     * 采纳
     *
     * @param to 非责任相关人意见数据to
     * @return class ResponsibleIdeaBO
     * @throws SerException
     */
    default ResponsibleIdeaBO adopt(ResponsibleIdeaTO to) throws SerException {
        return null;
    }
}