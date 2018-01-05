package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualApplyBO;
import com.bjike.goddess.annual.dto.AnnualApplyDTO;
import com.bjike.goddess.annual.entity.AnnualApply;
import com.bjike.goddess.annual.to.AnnualApplyAuditTo;
import com.bjike.goddess.annual.to.AnnualApplyTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 年假申请业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnualApplySer extends Ser<AnnualApply, AnnualApplyDTO> {

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
     * 保存年假申请实体数据
     *
     * @param to 年假申请传输对象
     * @return
     * @throws SerException
     */
    default AnnualApplyBO save(AnnualApplyTO to) throws SerException {
        return null;
    }

    /**
     * 删除年假申请实体数据
     *
     * @param to 年假申请传输对象
     * @return
     * @throws SerException
     */
    default AnnualApplyBO delete(AnnualApplyTO to) throws SerException {
        return null;
    }

    /**
     * 审核年假申请
     *
     * @param to 年假申请审核传输对象
     * @return
     * @throws SerException
     */
    default AnnualApplyBO audit(AnnualApplyAuditTo to) throws SerException {
        return null;
    }

    /**
     * 根据用户名查询年假申请记录
     *
     * @param username 用户名
     * @return
     * @throws SerException
     */
    default List<AnnualApplyBO> findByUsername(String username) throws SerException {
        return null;
    }

    /**
     * 根据年假信息查询年假申请记录
     *
     * @param info_id 年假信息ID
     * @return
     * @throws SerException
     */
    default List<AnnualApplyBO> findByInfo(String info_id) throws SerException {
        return null;
    }

    /**
     * 查询列表
     *
     * @param dto 年假申请数据传输对象
     * @return
     * @throws SerException
     */
    default List<AnnualApplyBO> maps(AnnualApplyDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取年假申请数据
     *
     * @param id 年假申请数据id
     * @return
     * @throws SerException
     */
    default AnnualApplyBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 获取申请开始时间
     *
     * @return
     * @throws SerException
     */
    default String getStartTime() throws SerException {
        return null;
    }
}