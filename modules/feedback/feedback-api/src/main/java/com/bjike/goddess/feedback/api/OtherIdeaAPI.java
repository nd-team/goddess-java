package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.OtherIdeaBO;
import com.bjike.goddess.feedback.dto.OtherIdeaDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.OtherIdeaTO;

import java.util.List;

/**
 * 其他模块意见业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:48 ]
 * @Description: [ 其他模块意见业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OtherIdeaAPI {
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
     * 其他模块意见列表总条数
     */
    default Long count(OtherIdeaDTO dto) throws SerException {
        return null;
    }

    /**
     * 其他模块意见
     *
     * @param dto 其他模块意见dto
     * @return class OtherIdeaBO
     * @throws SerException
     */
    default List<OtherIdeaBO> list(OtherIdeaDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加其他模块意见
     *
     * @param to 其他模块意见数据to
     * @return class OtherIdeaBO
     * @throws SerException
     */
    default OtherIdeaBO insert(OtherIdeaTO to) throws SerException {
        return null;
    }
}