package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 公司信息业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 08:58 ]
 * @Description: [ 公司信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InteractionRelationSer extends Ser<InteractionRelation, InteractionRelationDTO> {

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
     * 公司信息列表总条数
     */
    default Long countInter(InteractionRelationDTO interactionRelationDTO) throws SerException {
        return null;
    }

    /**
     * 一个公司信息
     *
     * @return class InteractionRelationBO
     */
    default InteractionRelationBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 公司信息列表
     *
     * @return class InteractionRelationBO
     */
    default List<InteractionRelationBO> listIntera(InteractionRelationDTO interactionRelationDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param interactionRelationTO 公司信息
     * @return class InteractionRelationBO
     */
    default InteractionRelationBO addIntera(InteractionRelationTO interactionRelationTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param interactionRelationTO 公司信息
     * @return class LeavingMessageBO
     */
    default InteractionRelationBO editIntera(InteractionRelationTO interactionRelationTO) throws SerException {
        return null;
    }

    /**
     * 删除公司信息
     *
     * @param id id
     */
    default void deleteIntera(String id) throws SerException {
        return;
    }
    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     *  导入
     * @param interactionRelationTOS 公司信息
     */
    void importExcel(List<InteractionRelationTO> interactionRelationTOS) throws SerException;
}