package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.carinfo.dto.DriverInfoDTO;
import com.bjike.goddess.carinfo.entity.DriverInfo;
import com.bjike.goddess.carinfo.excel.SonPermissionObject;
import com.bjike.goddess.carinfo.to.DriverInfoTO;
import com.bjike.goddess.carinfo.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 司机信息管理业务接口
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:46 ]
 * @Description: [ 司机信息管理业务接口 ]
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
     */
    DriverInfoBO insertModel(DriverInfoTO to) throws SerException;

    /**
     * 更新
     *
     * @param to 司机信息
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
     * @param audit   审核
     */
    void audit(String id, String suggest, Boolean audit) throws SerException;

    /**
     * 根据名字查询
     *
     * @param driver 司机名称
     */
    DriverInfoBO findByDriver(String driver) throws SerException;
}