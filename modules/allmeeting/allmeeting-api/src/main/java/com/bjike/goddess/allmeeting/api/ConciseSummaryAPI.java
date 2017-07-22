package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ConciseSummaryBO;
import com.bjike.goddess.allmeeting.bo.OrganizeForSummaryBO;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.to.ConciseSummaryTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 简洁交流讨论纪要业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:19 ]
 * @Description: [ 简洁交流讨论纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ConciseSummaryAPI {

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
     * 编辑简洁交流讨论
     *
     * @param to 简洁交流讨论
     * @return 简洁交流讨论
     */
    ConciseSummaryBO edit(ConciseSummaryTO to) throws SerException;

    /**
     * 冻结简洁交流讨论
     *
     * @param id 简洁交流讨论Id
     */
    void freeze(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<ConciseSummaryBO> pageList(ConciseSummaryDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(ConciseSummaryDTO dto) throws SerException;

    /**
     * 根据Id查询简洁交流讨论
     *
     * @param id 简洁交流讨论ID
     * @return 简洁交流讨论
     */
    ConciseSummaryBO findById(String id) throws SerException;

    OrganizeForSummaryBO organize(String id) throws SerException;

    void unfreeze(String id) throws SerException;
}