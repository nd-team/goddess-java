package com.bjike.goddess.system.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.FieldDockBO;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.to.FieldDockTO;
import com.bjike.goddess.system.to.GuidePermissionTO;

import java.util.List;

/**
 * 字段对接业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FieldDockAPI {
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
     * 字段对接列表总条数
     */
    default Long count(FieldDockDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个字段对接
     *
     * @return class FieldDockBO
     */
    default FieldDockBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 字段对接
     *
     * @param dto 字段对接dto
     * @return class FieldDockBO
     * @throws SerException
     */
    default List<FieldDockBO> list(FieldDockDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加字段对接
     *
     * @param to 字段对接数据to
     * @return class FieldDockBO
     * @throws SerException
     */
    default FieldDockBO insert(FieldDockTO to) throws SerException {
        return null;
    }

    /**
     * 编辑字段对接
     *
     * @param to 字段对接数据to
     * @return class FieldDockBO
     * @throws SerException
     */
    default FieldDockBO edit(FieldDockTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除字段对接
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 导入
     *
     * @param fieldDockTOS 字段对接
     * @return class FieldDockBO
     */
    default FieldDockBO importExcel(List<FieldDockTO> fieldDockTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(FieldDockDTO dto) throws SerException;

    /**
     * Excel下载模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;


}