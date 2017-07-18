package com.bjike.goddess.announcement.api;

import com.bjike.goddess.announcement.bo.ClassBO;
import com.bjike.goddess.announcement.dto.ClassDTO;
import com.bjike.goddess.announcement.to.ClassTO;
import com.bjike.goddess.announcement.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;
import java.util.Set;

/**
 * 公告分类业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:20 ]
 * @Description: [ 公告分类业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ClassAPI {
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
    List<ClassBO> list(ClassDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ClassBO save(ClassTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ClassTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    ClassBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ClassDTO dto) throws SerException;

    /**
     * 查找所有分类
     *
     * @return
     * @throws SerException
     */
    Set<String> allClass() throws SerException;
}