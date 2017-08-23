package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.housepay.bo.*;
import com.bjike.goddess.housepay.dto.PayRecordDTO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.entity.PayRecord;
import com.bjike.goddess.housepay.to.CollectAreaTO;
import com.bjike.goddess.housepay.to.CollectProjectTO;
import com.bjike.goddess.housepay.to.GuidePermissionTO;

import java.util.List;

/**
 * 已付款记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PayRecordSer extends Ser<PayRecord, PayRecordDTO> {
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
     * 地区汇总
     *
     * @param to to
     * @return class AreaCollectBO
     * @throws SerException
     */
    default List<AreaCollectBO> collectArea(CollectAreaTO to) throws SerException {
        return null;
    }
    /**
     * 地区汇总详情
     *
     * @param to to
     * @return class CollectDetailBO
     * @throws SerException
     */
    default List<CollectDetailBO> collectAreaDetail(CollectAreaTO to) throws SerException {
        return null;
    }


    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getAreas() throws SerException {
        return null;
    }

    /**
     * 项目汇总
     *
     * @param to to
     * @return class ProjectCollectBO
     * @throws SerException
     */
    default List<ProjectCollectBO> collectProject(CollectProjectTO to) throws SerException {
        return null;
    }
    /**
     * 项目汇总详情
     *
     * @param to to
     * @return class CollectDetailBO
     * @throws SerException
     */
    default List<CollectDetailBO> collectProjectDatail(CollectProjectTO to) throws SerException {
        return null;
    }

    /**
     * 获取项目
     *
     * @return class String
     */
    default List<String> getProject() throws SerException {
        return null;
    }
}