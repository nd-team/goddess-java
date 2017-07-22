package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.DemandTypeBO;
import com.bjike.goddess.attainment.dto.DemandTypeDTO;
import com.bjike.goddess.attainment.to.DemandTypeTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 调研需求类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:46 ]
 * @Description: [ 调研需求类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemandTypeAPI {

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
     * @param to 调研需求类型传输对象
     * @return
     * @throws SerException
     */
    default DemandTypeBO save(DemandTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研需求类型传输对象
     * @return
     * @throws SerException
     */
    default DemandTypeBO update(DemandTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研需求类型数据id
     * @return
     * @throws SerException
     */
    default DemandTypeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 调研需求类型数据id
     * @return
     * @throws SerException
     */
    default DemandTypeBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 调研需求类型数据id
     * @return
     * @throws SerException
     */
    default DemandTypeBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结数据
     *
     * @return
     * @throws SerException
     */
    default List<DemandTypeBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 调研需求类型数据传输对象
     * @return
     * @throws SerException
     */
    default List<DemandTypeBO> maps(DemandTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取调研需求类型数据
     *
     * @param id 调研需求类型数据id
     * @return
     * @throws SerException
     */
    default DemandTypeBO getById(String id) throws SerException {
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