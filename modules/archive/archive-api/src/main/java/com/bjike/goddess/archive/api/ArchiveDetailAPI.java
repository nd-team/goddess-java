package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ArchiveDetailBO;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.to.ArchiveDetailTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 档案明细业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArchiveDetailAPI {

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
     * @param to 档案明细传输对象
     * @return
     * @throws SerException
     */
    default ArchiveDetailBO save(ArchiveDetailTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 档案明细传输对象
     * @return
     * @throws SerException
     */
    default ArchiveDetailBO update(ArchiveDetailTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 档案明细数据id
     * @return
     * @throws SerException
     */
    default ArchiveDetailBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据姓名查询档案明细
     *
     * @param username 姓名
     * @return
     * @throws SerException
     */
    default ArchiveDetailBO findByUsername(String username) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 档案明细数据传输对象
     * @return
     * @throws SerException
     */
    default List<ArchiveDetailBO> maps(ArchiveDetailDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取档案明细数据
     *
     * @param id 档案明细数据id
     * @return
     * @throws SerException
     */
    default ArchiveDetailBO getById(String id) throws SerException {
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
     * 根据姓名获取管理等级
     *
     * @return
     * @throws SerException
     */
    default String findManage(String name) throws SerException {
        return null;
    }

    /**
     * 根据姓名获取处罚和奖励
     *
     * @param name
     * @return
     * @throws SerException
     */
    default String[] findPushAndReward(String name) throws SerException {
        return null;
    }
}