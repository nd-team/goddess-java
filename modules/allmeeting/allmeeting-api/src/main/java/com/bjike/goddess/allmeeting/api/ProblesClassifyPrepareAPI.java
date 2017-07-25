package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ProblesClassifyPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesClassifyPrepareDTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.ProblesClassifyPrepareTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface ProblesClassifyPrepareAPI {
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
     * 根据id查询问题分类
     * @param id
     * @return
     * @throws SerException
     */
    ProblesClassifyPrepareBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ProblesClassifyPrepareDTO dto) throws SerException;

    /**
     * 问题分类
     * @param to
     * @return
     * @throws SerException
     */
    ProblesClassifyPrepareBO add(ProblesClassifyPrepareTO to) throws SerException;

    /**
     * 编辑
     * @param to
     * @return
     * @throws SerException
     */
    ProblesClassifyPrepareBO edit(ProblesClassifyPrepareTO to) throws SerException;

    /**
     * 冻结
     * @param id
     * @throws SerException
     */
    void freeze(String id) throws SerException;

    /**
     * 查询列表
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProblesClassifyPrepareBO> pageList(ProblesClassifyPrepareDTO dto) throws SerException;

    /**
     * 解冻
     * @param id
     * @throws SerException
     */
    void unfreeze(String id) throws SerException;
}