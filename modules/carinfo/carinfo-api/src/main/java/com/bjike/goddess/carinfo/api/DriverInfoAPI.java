package com.bjike.goddess.carinfo.api;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.carinfo.dto.DriverInfoDTO;
import com.bjike.goddess.carinfo.excel.SonPermissionObject;
import com.bjike.goddess.carinfo.to.DriverInfoTO;
import com.bjike.goddess.carinfo.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 司机信息管理业务接口
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:46 ]
 * @Description: [ 车辆信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DriverInfoAPI {


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
    DriverInfoBO save(DriverInfoTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 司机信息
     */
    DriverInfoBO edit(DriverInfoTO to) throws SerException;

    /**
     * 根据ｉd查询
     *
     * @param id id
     */
    DriverInfoBO findById(String id) throws SerException;

    /**
     * 列表
     *
     * @param dto 　分页条件
     */
    List<DriverInfoBO> pageList(DriverInfoDTO dto) throws SerException;

    /**
     * 删除
     *
     * @param id id
     */
    void delete(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     */
    Long count(DriverInfoDTO dto) throws SerException;

    /**
     * 审核
     *
     * @param id      id
     * @param suggest 意见
     * @param audit   结果
     */
    void audit(String id, String suggest, Boolean audit) throws SerException;

    /**
     * 根据名称查询
     * @param driver 司机
     */
    DriverInfoBO findByDriver(String driver) throws SerException;

    /**
     * 根据司机姓名获取是否提供行驶证照片-是否提供驾驶证照片-是否提供车辆保险－是否提供协议附件－是否签订租车协议的信息
     */
     DriverInfoBO findByName(String name) throws SerException;

    /**
     * 拷贝旧服务器上的数据到本地
     */
    void copyDriver() throws SerException;
}