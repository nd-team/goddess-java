package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rotation.bo.SubsidyStandardBO;
import com.bjike.goddess.rotation.dto.SubsidyStandardDTO;
import com.bjike.goddess.rotation.entity.SubsidyStandard;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.SubsidyStandardTO;

import java.util.List;

/**
 * 岗位补贴标准业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:06 ]
 * @Description: [ 岗位补贴标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SubsidyStandardSer extends Ser<SubsidyStandard, SubsidyStandardDTO> {

    /**
     * 保存
     *
     * @param to 岗位补贴标准传输对象
     * @return
     * @throws SerException
     */
    default SubsidyStandardBO save(SubsidyStandardTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 岗位补贴标准传输对象
     * @return
     * @throws SerException
     */
    default SubsidyStandardBO update(SubsidyStandardTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 岗位补贴标准数据id
     * @return
     * @throws SerException
     */
    default SubsidyStandardBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 岗位补贴标准数据id
     * @return
     * @throws SerException
     */
    default SubsidyStandardBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 岗位补贴标准数据id
     * @return
     * @throws SerException
     */
    default SubsidyStandardBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询岗位补贴标准数据
     *
     * @param id 岗位补贴标准数据id
     * @return
     * @throws SerException
     */
    default SubsidyStandardBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位补贴标准数据传输对象
     * @return
     * @throws SerException
     */
    default List<SubsidyStandardBO> maps(SubsidyStandardDTO dto) throws SerException {
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

    /**
     * 根据岗位层级查询岗位补贴标准数据
     *
     * @param arrangement 岗位层级
     * @return
     * @throws SerException
     */
    default SubsidyStandardBO findByArrangement(String arrangement) throws SerException {
        return null;
    }

    /**
     * 查询未冻结层级
     *
     * @return
     * @throws SerException
     */
    default List<SubsidyStandardBO> findThaw() throws SerException {
        return null;
    }

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

}