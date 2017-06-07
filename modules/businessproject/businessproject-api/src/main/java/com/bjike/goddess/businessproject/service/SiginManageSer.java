package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.businessproject.entity.SiginManage;
import com.bjike.goddess.businessproject.dto.SiginManageDTO;

import java.io.InputStream;
import java.util.List;

/**
 * 商务项目合同签订与立项管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.296 ]
 * @Description: [ 商务项目合同签订与立项管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SiginManageSer extends Ser<SiginManage, SiginManageDTO> {

    /**
     * 签订与立项列表总条数
     *
     */
    default Long countSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取签订与立项列表
     * @return class SiginManageBO
     */
    default SiginManageBO getOneById(String id) throws SerException {return null;}


    /**
     * 合同签订与立项信息列表
     *
     * @return class SiginManageBO
     */
    default List<SiginManageBO> listSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param siginManageTO 合同签订与立项信息
     * @return class SiginManageBO
     */
    default SiginManageBO addSiginManage(SiginManageTO siginManageTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param siginManageTO 合同签订与立项信息
     * @return class SiginManageBO
     */
    default SiginManageBO editSiginManage(SiginManageTO siginManageTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSiginManage(String id) throws SerException {
        return;
    }

    ;

    /**
     *  审核
     * @param siginManageTO 合同签订与立项信息
     * @return class SiginManageBO
     */
    default SiginManageBO auditSiginManage(SiginManageTO siginManageTO) throws SerException { return null;}

    /**
     * 搜索
     * @param siginManageDTO 搜索
     * @return class SiginManageBO
     */
    default List<SiginManageBO> searchSiginManage(SiginManageDTO siginManageDTO) throws  SerException {
        return null;
    }

    /**
     * 获取地区
     * @return class String
     */
    default List<String> listArea( ) throws  SerException {
        return null;
    }


    /**
     *  导入
     * @param siginManageTO 合同签订与立项信息
     * @return class SiginManageBO
     */
    default SiginManageBO importExcel(List<SiginManageTO> siginManageTO) throws SerException { return null;}

    /**
     * 导出Excel
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(SiginManageDTO dto ) throws SerException;





}