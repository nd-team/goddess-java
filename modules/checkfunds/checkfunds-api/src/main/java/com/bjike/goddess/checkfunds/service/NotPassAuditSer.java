package com.bjike.goddess.checkfunds.service;

import com.bjike.goddess.checkfunds.bo.NotPassAuditBO;
import com.bjike.goddess.checkfunds.dto.NotPassAuditDTO;
import com.bjike.goddess.checkfunds.entity.NotPassAudit;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.to.NotPassAuditTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 审批不通过记录业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:23 ]
 * @Description: [ 审批不通过记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NotPassAuditSer extends Ser<NotPassAudit, NotPassAuditDTO> {
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
     * @param to 审批不通过记录
     * @return
     * @throws SerException
     */
    NotPassAuditBO save(NotPassAuditTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<NotPassAuditBO> list(NotPassAuditDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long countNum(NotPassAuditDTO dto) throws SerException;
}
