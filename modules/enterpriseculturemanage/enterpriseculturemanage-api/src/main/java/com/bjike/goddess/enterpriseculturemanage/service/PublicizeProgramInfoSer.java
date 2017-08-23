package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.PublicizeProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.PublicizeProgramInfo;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;
import com.bjike.goddess.enterpriseculturemanage.to.PublicizeProgramInfoTO;

import java.util.List;

/**
 * 宣传方案信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PublicizeProgramInfoSer extends Ser<PublicizeProgramInfo, PublicizeProgramInfoDTO> {

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
     * 添加宣传方案信息
     *
     * @param to 宣传方案信息
     * @return 宣传方案信息
     */
    PublicizeProgramInfoBO insertModel(PublicizeProgramInfoTO to) throws SerException;

    /**
     * 编辑宣传方案信息
     *
     * @param to 宣传方案信息
     * @return 宣传方案信息
     */
    PublicizeProgramInfoBO updateModel(PublicizeProgramInfoTO to) throws SerException;

    /**
     * 审核宣传方案信息
     *
     * @param id              id
     * @param auditResult     结果
     * @param auditSuggestion 意见
     * @throws SerException
     */
    void audit(String id, AuditResult auditResult, String auditSuggestion) throws SerException;

    /**
     * 宣传方案信息分页查询
     *
     * @param to 分页条件
     * @return 宣传方案信息结果集
     */
    List<PublicizeProgramInfoBO> pageList(PublicizeProgramInfoDTO to) throws SerException;
}