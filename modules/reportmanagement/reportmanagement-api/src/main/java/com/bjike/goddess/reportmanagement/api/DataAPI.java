package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.DataBO;
import com.bjike.goddess.reportmanagement.dto.DataDTO;
import com.bjike.goddess.reportmanagement.to.DataTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;

import java.util.List;

/**
 * 补充资料表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 02:29 ]
 * @Description: [ 补充资料表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DataAPI {

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
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<DataBO> list(DataDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(DataTO to) throws SerException {
        return;
    }

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    default DataBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(DataTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long count(DataDTO dto) throws SerException {
        return null;
    }
}