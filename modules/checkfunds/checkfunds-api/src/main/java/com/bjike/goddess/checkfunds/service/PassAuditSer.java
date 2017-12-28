package com.bjike.goddess.checkfunds.service;

import com.bjike.goddess.checkfunds.bo.PassAuditBO;
import com.bjike.goddess.checkfunds.dto.PassAuditDTO;
import com.bjike.goddess.checkfunds.dto.RemainAdjustDTO;
import com.bjike.goddess.checkfunds.entity.PassAudit;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.to.PassAuditTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 已完成核对记录业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:18 ]
 * @Description: [ 已完成核对记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PassAuditSer extends Ser<PassAudit, PassAuditDTO> {
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
     * 添加
     *
     * @param to 已完成核对记录
     * @return
     * @throws SerException
     */
    PassAuditBO save(PassAuditTO to) throws SerException;

    /**
     * 列表
     * @param dto
     * @return
     * @throws SerException
     */
    List<PassAuditBO> list(PassAuditDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long countNum(PassAuditDTO dto) throws SerException;
}
