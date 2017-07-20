package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ForeignStaffingBO;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.to.ForeignStaffingTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 对外人员信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ForeignStaffingAPI {


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
     * 保存
     *
     * @param to 对外人员信息传输对象
     * @return
     * @throws SerException
     */
    default ForeignStaffingBO save(ForeignStaffingTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 对外人员信息传输对象
     * @return
     * @throws SerException
     */
    default ForeignStaffingBO update(ForeignStaffingTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 对外人员信息数据id
     * @return
     * @throws SerException
     */
    default ForeignStaffingBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 对外人员信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<ForeignStaffingBO> maps(ForeignStaffingDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取对外人员信息数据
     *
     * @param id 对外人员信息数据id
     * @return
     * @throws SerException
     */
    default ForeignStaffingBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }
}