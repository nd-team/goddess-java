package com.bjike.goddess.dimission.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.dimission.bo.DimissionInfoCollectBO;
import com.bjike.goddess.dimission.bo.DimissionReasonBO;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.entity.DimissionInfo;
import com.bjike.goddess.dimission.enums.DimissionType;
import com.bjike.goddess.dimission.excel.SonPermissionObject;
import com.bjike.goddess.dimission.to.*;

import java.util.List;

/**
 * 离职信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DimissionInfoSer extends Ser<DimissionInfo, DimissionInfoDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 申请离职
     *
     * @param to 离职信息传输对象
     * @return
     * @throws SerException
     */
    default DimissionInfoBO apply(DimissionInfoTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 离职信息传输对象
     * @return
     * @throws SerException
     */
    default DimissionInfoBO update(DimissionInfoTO to) throws SerException {
        return null;
    }

    /**
     * 自离信息添加
     *
     * @param to 离职信息传输对象
     * @return
     * @throws SerException
     */
    default DimissionInfoBO presume(DimissionInfoTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 离职信息数据id
     * @return
     * @throws SerException
     */
    default DimissionInfoBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 保存面谈记录
     *
     * @param to 离职面谈信息传输对象
     * @return
     * @throws SerException
     */
    default DimissionInfoBO interview(DimissionInterviewTo to) throws SerException {
        return null;
    }

    /**
     * 总经办审核
     *
     * @param to 离职审核传输对象
     * @return
     * @throws SerException
     */
    default DimissionInfoBO audit(DimissionAuditTO to) throws SerException {
        return null;
    }

    /**
     * 薪资确认
     *
     * @param to 离职薪资确认传输对象
     * @return
     * @throws SerException
     */
    default DimissionInfoBO affirm(DimissionAffirmTO to) throws SerException {
        return null;
    }

    /**
     * 确认离职
     *
     * @param id 离职信息数据id
     * @return
     * @throws SerException
     */
    default DimissionInfoBO success(String id) throws SerException {
        return null;
    }

    /**
     * 修改离职类型
     *
     * @param to 离职类型修改传输对象
     * @return
     * @throws SerException
     */
    default DimissionInfoBO editType(DimissionTypeTO to) throws SerException {
        return null;
    }

    /**
     * 根据离职类型查询离职信息
     *
     * @param type 离职类型
     * @return
     * @throws SerException
     */
    default List<DimissionInfoBO> findByType(DimissionType type) throws SerException {
        return null;
    }

    /**
     * 自离信息列表
     *
     * @param dto 离职信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionInfoBO> presumeList(DimissionInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 离职信息列表
     *
     * @param dto 离职信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionInfoBO> maps(DimissionInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 查询指定离职时间内的离职信息
     *
     * @param start 查询开始时间
     * @param end   查询结束时间
     * @return
     * @throws SerException
     */
    default List<DimissionInfoBO> findByDimissionDate(String start, String end) throws SerException {
        return null;
    }

    /**
     * 获取全部离职信息
     *
     * @return
     * @throws SerException
     */
    default List<DimissionInfoBO> all() throws SerException {
        return null;
    }

    /**
     * 部门离职汇总
     *
     * @param to 离职信息汇总传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionInfoCollectBO> departmentCollect(DimissionCollectTO to) throws SerException {
        return null;
    }

    /**
     * 岗位离职汇总
     *
     * @param to 离职信息汇总传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionInfoCollectBO> positionCollect(DimissionCollectTO to) throws SerException {
        return null;
    }

    /**
     * 入职时间汇总
     *
     * @param to 离职信息汇总传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionInfoCollectBO> entryCollect(DimissionCollectTO to) throws SerException {
        return null;
    }

    /**
     * 工龄汇总
     *
     * @param to 离职信息汇总传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionInfoCollectBO> seniorityCollect(DimissionCollectTO to) throws SerException {
        return null;
    }

    /**
     * 学历汇总
     *
     * @param to 离职信息汇总传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionInfoCollectBO> educationCollect(DimissionCollectTO to) throws SerException {
        return null;
    }

    /**
     * 原因汇总
     *
     * @param to 离职信息汇总传输对象
     * @return
     * @throws SerException
     */
    default List<DimissionReasonBO> reasonCollect(DimissionCollectTO to) throws SerException {
        return null;
    }

    /**
     * 根据id获取离职信息数据
     *
     * @param id 离职信息数据id
     * @return
     * @throws SerException
     */
    default DimissionInfoBO getById(String id) throws SerException {
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
     * 获取全部用户名
     */
    default List<String> getAllName() throws SerException {
        return null;
    }
}