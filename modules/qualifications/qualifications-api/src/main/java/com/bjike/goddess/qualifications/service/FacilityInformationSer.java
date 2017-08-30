package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.bo.FacilityInformationBO;
import com.bjike.goddess.qualifications.dto.FacilityInformationDTO;
import com.bjike.goddess.qualifications.entity.FacilityInformation;
import com.bjike.goddess.qualifications.to.FacilityInformationTO;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;

import java.util.List;

/**
 * 设备信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FacilityInformationSer extends Ser<FacilityInformation, FacilityInformationDTO> {

    /**
     * 保存
     *
     * @param to 设备信息传输对象
     * @return
     * @throws SerException
     */
    default FacilityInformationBO save(FacilityInformationTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 设备信息传输对象
     * @return
     * @throws SerException
     */
    default FacilityInformationBO update(FacilityInformationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 设备信息ID
     * @return
     * @throws SerException
     */
    default FacilityInformationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 获取全部
     *
     * @return
     * @throws SerException
     */
    default List<FacilityInformationBO> all() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 设备信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<FacilityInformationBO> maps(FacilityInformationDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Integer getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return
     * @throws SerException
     */
    default FacilityInformationBO getById(String id) throws SerException {
        return null;
    }

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
}