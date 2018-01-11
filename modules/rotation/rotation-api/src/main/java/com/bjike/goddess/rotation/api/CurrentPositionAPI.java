package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.CurrentPositionBO;
import com.bjike.goddess.rotation.dto.CurrentPositionDTO;
import com.bjike.goddess.rotation.excel.SonPermissionObject;
import com.bjike.goddess.rotation.to.CurrentPositionTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;

import java.util.List;

/**
 * 目前岗位情况业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:30 ]
 * @Description: [ 目前岗位情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CurrentPositionAPI {


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
     * 获取当前岗位总条数
     *
     * @param
     * @return class
     * @version v1
     */
    Long count(CurrentPositionDTO dto) throws SerException;

    /**
     * 获取当前岗位列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<CurrentPositionBO> list(CurrentPositionDTO dto) throws SerException;

    /**
     * 添加当前岗位
     *
     * @param
     * @return class
     * @version v1
     */
    void add(CurrentPositionTO vo) throws SerException;

    /**
     * 更新当前岗位
     *
     * @param
     * @return class
     * @version v1
     */
    void update(CurrentPositionTO vo) throws SerException;


    /**
     * 更新当前岗位(当前岗位层级)
     *
     * @param arrangement 当前岗位层级
     * @return class
     * @version v1
     */
    void update(String id, String arrangement) throws SerException;


    /**
     * 删除当前岗位
     *
     * @param
     * @return class
     * @version v1
     */
    void delete(String id) throws SerException;

    /**
     * 获取一个当前岗位
     *
     * @param
     * @return class
     * @version v1
     */
    CurrentPositionBO getOne(String id) throws SerException;


    /**
     * 导入
     *
     * @param
     * @return class
     * @version v1
     */
    void importExcel(List<CurrentPositionTO> tos) throws SerException;

    /**
     * 导出
     *
     * @param
     * @return class
     * @version v1
     */
    byte[] exportExcel(CurrentPositionDTO dto) throws SerException;

    /**
     * 导出模板
     *
     * @param
     * @return class
     * @version v1
     */
    byte[] exportTemplate() throws SerException;
}