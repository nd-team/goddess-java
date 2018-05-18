package com.bjike.goddess.fixedassets.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fixedassets.bo.BaseInfoBO;
import com.bjike.goddess.fixedassets.bo.BaseInfoDetailBO;
import com.bjike.goddess.fixedassets.bo.SummationBO;
import com.bjike.goddess.fixedassets.dto.BaseInfoDTO;
import com.bjike.goddess.fixedassets.excel.SonPermissionObject;
import com.bjike.goddess.fixedassets.to.BaseInfoTO;
import com.bjike.goddess.fixedassets.to.GuidePermissionTO;

import java.util.List;

/**
 * 基本信息业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BaseInfoAPI {

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
     * 基本信息列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<BaseInfoBO> list(BaseInfoDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    BaseInfoBO save(BaseInfoTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    BaseInfoBO edit(BaseInfoTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void deleteBaseInfo(String id) throws SerException;

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long countBaseInfo(BaseInfoDTO baseInfoDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取基本信息
     *
     * @param id 基本信息id
     * @return
     * @throws SerException
     */
    default BaseInfoBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 导出excel
     *
     * @return
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;


    /**
     * 导入
     *
     * @param baseInfoTOS 基本信息
     */
    void importExcel(List<BaseInfoTO> baseInfoTOS) throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;
    /**
     * 折旧汇总表
     *
     * @param year 年份
     * @param month 月份
     * @return
     * @throws SerException
     */
    default List<SummationBO> summation(Integer year, Integer month) throws SerException {
        return null;
    }
    /**
     * 折旧明细表
     *
     * @param year 年份
     * @param month 月份
     * @return
     * @throws SerException
     */
    default List<BaseInfoDetailBO> baseInfoDetail(Integer year, Integer month) throws SerException {
        return null;
    }
    /**
     * 获取所有用户
     *
     * @return
     * @throws SerException
     */
    default List<String> findallUser() throws SerException {
        return null;
    }

}