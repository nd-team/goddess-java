package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.DormitoryInfoBO;
import com.bjike.goddess.checkhost.to.DormitoryInfoTO;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.checkhost.entity.DormitoryInfo;
import com.bjike.goddess.checkhost.dto.DormitoryInfoDTO;

import java.util.List;

/**
 * 宿舍信息管理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:02 ]
 * @Description: [ 宿舍信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DormitoryInfoSer extends Ser<DormitoryInfo, DormitoryInfoDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 宿舍信息管理列表总条数
     */
    default Long countDormitoryInfo(DormitoryInfoDTO dormitoryInfoDTO) throws SerException {
        return null;
    }
    /**
     * 一个宿舍信息管理
     *
     * @return class DormitoryInfoBO
     */
    default DormitoryInfoBO getOne(String id) throws SerException {
        return null;
    }
    /**
     * 宿舍信息列表
     *
     * @param dormitoryInfoDTO 宿舍信息dto
     * @return class DormitoryInfoBO
     * @throws SerException
     */
    default List<DormitoryInfoBO> findListDormitoryInfo(DormitoryInfoDTO dormitoryInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加宿舍信息
     *
     * @param dormitoryInfoTO 宿舍信息数据to
     * @return class DormitoryInfoBO
     * @throws SerException
     */
    default DormitoryInfoBO insertDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑宿舍信息
     *
     * @param dormitoryInfoTO 宿舍信息数据to
     * @return class DormitoryInfoBO
     * @throws SerException
     */
    default DormitoryInfoBO editDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除宿舍信息
     *
     * @param id
     * @throws SerException
     */
    default void removeDormitoryInfo(String id) throws SerException {

    }

}