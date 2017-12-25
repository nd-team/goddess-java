package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MultiwheelSummaryBO;
import com.bjike.goddess.allmeeting.bo.OrganizeForSummaryBO;
import com.bjike.goddess.allmeeting.dto.MultiwheelSummaryDTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.MultiwheelSummaryTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 简洁交流讨论纪要业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MultiwheelSummaryAPI {

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
     * 根据id查询纪要信息
     * @param id
     * @return
     * @throws SerException
     */

    MultiwheelSummaryBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(MultiwheelSummaryDTO dto) throws SerException;

    /**
     * 编辑纪要内容
     * @param to
     * @return
     * @throws SerException
     */
    MultiwheelSummaryBO edit(MultiwheelSummaryTO to) throws SerException;

    /**
     * 简洁纪要内容列表查询
     * @param dto
     * @return
     * @throws SerException
     */
    List<MultiwheelSummaryBO> pageList(MultiwheelSummaryDTO dto) throws SerException;

    /**
     * 冻结
     * @param id
     * @throws SerException
     */
    void freeze(String id) throws SerException;

    /**
     * 组织内容
     * @param id
     * @return
     * @throws SerException
     */
    OrganizeForSummaryBO organize(String id) throws SerException;

    /**
     * 解冻
     * @param id
     * @throws SerException
     */
    void unfreeze(String id) throws SerException;
}