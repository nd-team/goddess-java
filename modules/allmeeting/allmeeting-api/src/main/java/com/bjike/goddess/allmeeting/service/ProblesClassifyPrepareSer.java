package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ProblesClassifyPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesClassifyPrepareDTO;
import com.bjike.goddess.allmeeting.entity.ProblesClassifyPrepare;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.ProblesClassifyPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 问题分类议题准备信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProblesClassifyPrepareSer extends Ser<ProblesClassifyPrepare, ProblesClassifyPrepareDTO> {
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

    ProblesClassifyPrepareBO insertModel(ProblesClassifyPrepareTO to) throws SerException;

    ProblesClassifyPrepareBO editModel(ProblesClassifyPrepareTO to) throws SerException;

    void freeze(String id) throws SerException;

    List<ProblesClassifyPrepareBO> pageList(ProblesClassifyPrepareDTO dto) throws SerException;

    void unfreeze(String id) throws SerException;
}