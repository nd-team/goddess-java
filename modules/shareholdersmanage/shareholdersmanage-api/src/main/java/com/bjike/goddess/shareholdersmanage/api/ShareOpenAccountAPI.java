package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ShareOpenAccountBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOpenAccountDTO;
import com.bjike.goddess.shareholdersmanage.excel.SonPermissionObject;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountBTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountTO;

import java.util.List;

/**
 * 股东开户业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ShareOpenAccountAPI {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 股东开户列表总条数
     */
    default Long countShareOpen(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        return null;
    }

    /**
     * 一个股东开户
     *
     * @return class ShareOpenAccountBO
     */
    default ShareOpenAccountBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股东开户列表
     *
     * @param shareOpenAccountDTO 股东开户dto
     * @return class ShareOpenAccountBO
     * @throws SerException
     */
    default List<ShareOpenAccountBO> findList(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        return null;
    }

    /**
     * 股东开户添加
     *
     * @param shareOpenAccountTO 股东开户数据to
     * @return class ShareOpenAccountBO
     * @throws SerException
     */
    default ShareOpenAccountBO save(ShareOpenAccountTO shareOpenAccountTO) throws SerException {
        return null;
    }

    /**
     * 股东开户编辑
     *
     * @param shareOpenAccountBTO 股东开户数据to
     * @return class ShareOpenAccountBO
     * @throws SerException
     */
    default ShareOpenAccountBO edit(ShareOpenAccountBTO shareOpenAccountBTO) throws SerException {
        return null;
    }

    /**
     * 查看所有的股权类型
     *
     * @throws SerException
     */
    default List<String> findEquityType() throws SerException {
        return null;
    }

    /**
     * 根据id删除股东开户
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    default List<ShareOpenAccountBO> summationShareOpen(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param shareOpenAccountTOS 股东开户
     * @return class ShareOpenAccountBO
     */
    default ShareOpenAccountBO importExcel(List<ShareOpenAccountTO> shareOpenAccountTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param shareOpenAccountDTO
     * @throws SerException
     */
    byte[] exportExcel(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException;

    /**
     * 导出Excel模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;
    /**
     * 根据股东姓名查看股东信息
     *
     * @throws SerException
     */
    default ShareOpenAccountBO findByName(String shareholderName) throws SerException {
        return null;
    }
    /**
     * 查看所有的股东姓名
     *
     * @throws SerException
     */
    default List<String> findShareholderName() throws SerException {
        return null;
    }
    /**
     * 查看所有的地区
     *
     * @throws SerException
     */
    default List<String> findArea() throws SerException {
        return null;
    }
}