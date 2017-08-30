package com.bjike.goddess.announcement.api;

import com.bjike.goddess.announcement.bo.AnnouncementBO;
import com.bjike.goddess.announcement.dto.AnnouncementDTO;
import com.bjike.goddess.announcement.to.AnnouncementTO;
import com.bjike.goddess.announcement.to.GuidePermissionTO;
import com.bjike.goddess.announcement.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;
import java.util.Set;

/**
 * 公告业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnouncementAPI {
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
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AnnouncementBO> list(AnnouncementDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    AnnouncementBO save(AnnouncementTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(AnnouncementTO to) throws SerException;

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
    AnnouncementBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AnnouncementDTO dto) throws SerException;

    /**
     * 查询有无必读公告
     *
     * @return
     * @throws SerException
     */
    boolean checkRequired() throws SerException;

    /**
     * 查看该分类有无未读公告
     *
     * @param classify
     * @return
     * @throws SerException
     */
    boolean checkByClass(String classify) throws SerException;

    /**
     * 获取当前用户必读且未读的公告
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AnnouncementBO> requiredReads(AnnouncementDTO dto) throws SerException;

    /**
     * 读取公告
     *
     * @param id
     * @return
     * @throws SerException
     */
    AnnouncementBO read(String id) throws SerException;

    /**
     * 查找所有编号
     *
     * @return
     * @throws SerException
     */
    Set<String> allNumbers() throws SerException;

    /**
     * 查找所有分类
     *
     * @return
     * @throws SerException
     */
    Set<String> allClass() throws SerException;

    /**
     * 查找所有作者
     *
     * @return
     * @throws SerException
     */
    Set<String> allAuthors() throws SerException;

    /**
     * 搜索
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AnnouncementBO> search(AnnouncementDTO dto) throws SerException;

    /**
     * 通过分类查找
     *
     * @param classify
     * @return
     * @throws SerException
     */
    List<AnnouncementBO> listByClass(String classify) throws SerException;

    /**
     * 添加个人公告
     *
     * @param to
     * @return
     * @throws SerException
     */
    AnnouncementBO addPerson(AnnouncementTO to) throws SerException;

    /**
     * 冻结
     *
     * @param id
     * @throws SerException
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻
     *
     * @param id
     * @throws SerException
     */
    void thaw(String id) throws SerException;

    /**
     * 必读公告条数
     *
     * @return
     * @throws SerException
     */
    Long requiredCount() throws SerException;

    /**
     * 查找所有未冻结用户
     *
     * @return
     * @throws SerException
     */
    List<UserBO> allUsers() throws SerException;
}