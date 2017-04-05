package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.*;
import com.bjike.goddess.qualifications.dto.QualificationsHandleDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.to.QualificationsHandleForeignTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;

import java.util.List;

/**
 * 资质办理管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationsHandleSer extends Ser<QualificationsHandle, QualificationsHandleDTO> {


    /**
     * 保存
     *
     * @param to 资质办理管理传输对象
     * @return
     * @throws SerException
     */
    default QualificationsHandleBO save(QualificationsHandleTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 资质办理管理传输对象
     * @return
     * @throws SerException
     */
    default QualificationsHandleBO update(QualificationsHandleTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 资质办理id
     * @return
     * @throws SerException
     */
    default QualificationsHandleBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 查询未获取资质
     *
     * @return
     * @throws SerException
     */
    default List<QualificationsHandleBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto 资质办理管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<QualificationsHandleBO> maps(QualificationsHandleDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id根据资质类型查询资质办理
     *
     * @param type 资质类型
     * @return
     * @throws SerException
     */
    default QualificationsHandleBO findByType(String type) throws SerException {
        return null;
    }

    /**
     * 根据id获取人员信息资料
     *
     * @param id 资质办理id
     * @return
     * @throws SerException
     */
    default List<PersonnelInformationBO> getPersonnel(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取财务资料业务
     *
     * @param id 资质办理id
     * @return
     * @throws SerException
     */
    default List<FinanceInfoBO> getFinance(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取设备信息
     *
     * @param id 资质办理id
     * @return
     * @throws SerException
     */
    default List<FacilityInformationBO> getFacility(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取公司基本信息
     *
     * @param id 资质办理id
     * @return
     * @throws SerException
     */
    default List<CompanyInfoBO> getCompany(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取审核资料
     *
     * @param id 资质办理id
     * @return
     * @throws SerException
     */
    default List<AuditMaterialBO> getAudit(String id) throws SerException {
        return null;
    }

    /**
     * 保存资料管理
     *
     * @param to 资质办理管理外键id传输对象
     * @return
     * @throws SerException
     */
    default QualificationsHandleBO saveForeign(QualificationsHandleForeignTO to) throws SerException {
        return null;
    }

}