package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ChangeEquityTypeBO;
import com.bjike.goddess.shareholdersmanage.dto.ChangeEquityTypeDTO;
import com.bjike.goddess.shareholdersmanage.to.ChangeEquityTypeTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 变更股权类型业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ChangeEquityTypeAPI {
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
     * 变更股权类型列表总条数
     */
    default Long countChangeType(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        return null;
    }

    /**
     * 一个变更股权类型
     *
     * @return class ChangeEquityTypeBO
     */
    default ChangeEquityTypeBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 变更股权类型列表
     *
     * @param changeEquityTypeDTO 变更股权类型dto
     * @return class ChangeEquityTypeBO
     * @throws SerException
     */
    default List<ChangeEquityTypeBO> findList(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        return null;
    }

    /**
     * 变更股权类型添加
     *
     * @param changeEquityTypeTO 注销股权数据to
     * @throws SerException
     */
    default ChangeEquityTypeBO save(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        return null;
    }

    /**
     * 变更股权类型编辑
     *
     * @param changeEquityTypeTO 变更股权类型to
     * @throws SerException
     */
    default ChangeEquityTypeBO edit(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除变更股权类型
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
}