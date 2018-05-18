package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ResumeInfoBO;
import com.bjike.goddess.archive.bo.ResumeInfoDataBO;
import com.bjike.goddess.archive.dto.ResumeInfoDTO;
import com.bjike.goddess.archive.entity.ResumeInfo;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.ResumeInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 人员简历信息业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-14 11:30 ]
 * @Description: [ 人员简历信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ResumeInfoSer extends Ser<ResumeInfo, ResumeInfoDTO> {

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
     * 根据名字获取信息
     */
    default ResumeInfoDataBO findDataByName(String name) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void add(ResumeInfoTO to) throws SerException {
        return;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(ResumeInfoTO to) throws SerException {
        return;
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default ResumeInfoBO findEntity(String id) throws SerException {
        return null;
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
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(ResumeInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id
     * @throws SerException
     */
    default void freeze(String id) throws SerException {
        return;
    }

    /**
     * 解冻
     *
     * @param id
     * @throws SerException
     */
    default void thaw(String id) throws SerException {
        return;
    }

    /**
     * 导入
     *
     * @param toList
     * @throws SerException
     */
    default void upload(List<ResumeInfoTO> toList) throws SerException {
        return;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ResumeInfoBO> maps(ResumeInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(ResumeInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出导入的excel模板
     */
    default byte[] templateExcel() throws SerException {
        return null;
    }

    /**
     * 导出时获取的姓名
     *
     * @return
     * @throws SerException
     */
    default List<String> findUserName() throws SerException {
        return null;
    }
}