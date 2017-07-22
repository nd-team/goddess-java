package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ProblesAllotPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesAllotPrepareDTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.ProblesAllotPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 问题分配责任模块议题准备信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblesAllotPrepareAPI {

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

    ProblesAllotPrepareBO add(ProblesAllotPrepareTO to) throws SerException;

    ProblesAllotPrepareBO edit(ProblesAllotPrepareTO to) throws SerException;

    void freeze(String id) throws SerException;

    List<ProblesAllotPrepareBO> pageList(ProblesAllotPrepareDTO dto) throws SerException;

    Long count(ProblesAllotPrepareDTO dto) throws SerException;

    ProblesAllotPrepareBO findById(String id) throws SerException;

}