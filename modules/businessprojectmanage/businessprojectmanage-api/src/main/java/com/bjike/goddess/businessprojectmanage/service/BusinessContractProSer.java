package com.bjike.goddess.businessprojectmanage.service;

import com.bjike.goddess.businessprojectmanage.api.BusinessContractProAPI;
import com.bjike.goddess.businessprojectmanage.bo.BusinessContractCollectBO;
import com.bjike.goddess.businessprojectmanage.bo.BusinessContractProBO;
import com.bjike.goddess.businessprojectmanage.to.BusinessContractProTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.businessprojectmanage.entity.BusinessContractPro;
import com.bjike.goddess.businessprojectmanage.dto.BusinessContractProDTO;

import java.util.List;

/**
 * 项目合同基本信息业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-11 10:03 ]
 * @Description: [ 项目合同基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessContractProSer extends Ser<BusinessContractPro, BusinessContractProDTO> {

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
     * 获取项目合同进度列表
     *
     * @param dto
     * @return class
     * @version v1
     */
    List<BusinessContractProBO> listBusinessContactProgress(BusinessContractProDTO dto) throws SerException;

    /**
     * 获取项目合同进度总数
     *
     * @param dto
     * @return class
     * @version v1
     */
    Long countBusinessContactProgress(BusinessContractProDTO dto) throws SerException;

    /**
     * 添加项目合同进度
     *
     * @param to
     * @return class
     * @version v1
     */
    void addBusinessContactProgress(BusinessContractProTO to) throws SerException;

    /**
     * 更新项目合同进度
     *
     * @param to
     * @return class
     * @version v1
     */
    void updateBusinessContactProgress(BusinessContractProTO to) throws SerException;

    /**
     * 导入项目合同进度列表
     *
     * @param to
     * @return class
     * @version v1
     */
    void importExcel(List<BusinessContractProTO> to) throws SerException;

    /**
     * 导出项目合同进度列表
     *
     * @param dto
     * @return class
     * @version v1
     */
    byte[] exportExcel(BusinessContractProDTO dto) throws SerException;

    /**
     * 商务合同管理日汇总
     *
     * @param time
     * @return class BusinessContractProBO
     * @throws SerException
     */
    default List<BusinessContractCollectBO> dayCollect(String time) throws SerException {
        return null;
    }

    /**
     * 商务合同管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class BusinessContractProBO
     * @throws SerException
     */
    default List<BusinessContractCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 商务合同管理月汇总
     *
     * @param year
     * @param month
     * @return class BusinessContractProBO
     * @throws SerException
     */
    default List<BusinessContractCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 商务合同季度汇总
     *
     * @param year
     * @param quarter
     * @return class BusinessContractProBO
     * @throws SerException
     */
    default List<BusinessContractCollectBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 商务合同管理年汇总
     *
     * @param year
     * @return class BusinessContractProBO
     * @throws SerException
     */
    default List<BusinessContractCollectBO> yearCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 商务合同管理累计汇总
     *
     * @param time
     * @return class BusinessContractProBO
     * @throws SerException
     */
    default List<BusinessContractCollectBO> totalCollect(String time) throws SerException {
        return null;
    }

    /**
     * 检测发送邮件
     *
     * @param
     * @return class
     * @version v1
     */
    List<BusinessContractCollectBO> collectCollectEmail(String condition) throws SerException;
}