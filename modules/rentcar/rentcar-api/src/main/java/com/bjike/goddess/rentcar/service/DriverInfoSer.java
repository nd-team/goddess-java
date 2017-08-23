package com.bjike.goddess.rentcar.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentcar.bo.DriverInfoBO;
import com.bjike.goddess.rentcar.dto.DriverInfoDTO;
import com.bjike.goddess.rentcar.entity.DriverInfo;
import com.bjike.goddess.rentcar.excel.SonPermissionObject;
import com.bjike.goddess.rentcar.to.DriverInfoTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;

import java.util.List;

/**
 * 司机信息业务接口
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:47 ]
 * @Description: [ 租车协议管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DriverInfoSer extends Ser<DriverInfo, DriverInfoDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }


    /**
     * 保存
     *
     * @param to 司机信息
     * @return
     * @throws SerException
     */
    DriverInfoBO insertModel(DriverInfoTO to) throws SerException;

    /**
     * 更新
     *
     * @param to 司机信息
     * @return
     * @throws SerException
     */
    DriverInfoBO updateModel(DriverInfoTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     */
    List<DriverInfoBO> pageList(DriverInfoDTO dto) throws SerException;

    /**
     * 审核
     *
     * @param id      id
     * @param suggest 意见
     * @param audit   结果
     */
    void audit(String id, String suggest, Boolean audit) throws SerException;
}