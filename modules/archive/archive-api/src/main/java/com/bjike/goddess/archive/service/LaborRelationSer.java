package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.LaborRelationBO;
import com.bjike.goddess.archive.dto.LaborRelationDTO;
import com.bjike.goddess.archive.entity.LaborRelation;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.LaborRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 劳动关系类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:05 ]
 * @Description: [ 劳动关系类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LaborRelationSer extends Ser<LaborRelation, LaborRelationDTO> {


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
     * @param to 劳动关系类型传输对象
     * @return
     * @throws SerException
     */
    default LaborRelationBO save(LaborRelationTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 劳动关系类型传输对象
     * @return
     * @throws SerException
     */
    default LaborRelationBO update(LaborRelationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 劳动关系类型数据id
     * @return
     * @throws SerException
     */
    default LaborRelationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 劳动关系类型数据id
     * @return
     * @throws SerException
     */
    default LaborRelationBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 劳动关系类型数据id
     * @return
     * @throws SerException
     */
    default LaborRelationBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据状态查询劳动关系类型数据
     *
     * @param status 状态
     * @return
     * @throws SerException
     */
    default List<LaborRelationBO> findByStatus(Status status) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 劳动关系类型数据传输对象
     * @return
     * @throws SerException
     */
    default List<LaborRelationBO> maps(LaborRelationDTO dto) throws SerException {
        return null;
    }


    /**
     * 根据id获取劳动关系类型数据
     *
     * @param id 劳动关系类型数据id
     * @return
     * @throws SerException
     */
    default LaborRelationBO getById(String id) throws SerException {
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