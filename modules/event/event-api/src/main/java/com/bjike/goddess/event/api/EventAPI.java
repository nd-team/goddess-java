package com.bjike.goddess.event.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.event.bo.*;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.to.EventTO;
import com.bjike.goddess.event.to.GuidePermissionTO;
import com.bjike.goddess.event.vo.ClassifyCountVO;
import com.bjike.goddess.event.vo.SonPermissionObject;
import com.bjike.goddess.user.entity.rbac.Permission;

import java.util.List;

/**
 * 事件业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EventAPI {
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
     * 事件列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FatherBO> list(FatherDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    EventBO save(EventTO to) throws SerException;

    /**
     * 删除
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 查找id
     * @param eventId 事件id
     * @param name 处理人
     * @return
     * @throws SerException
     */
    String findId(String eventId,String name) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(EventTO to) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    EventBO findByID(String id) throws SerException;

    /**
     * 检测定时发送
     *
     * @throws SerException
     */
    void quarzt() throws SerException;

    /**
     * 通过日历查看待办事件
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ContentBO> findByMonth(EventDTO dto) throws SerException;

    /**
     * 所有人事件列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FatherBO> allList(FatherDTO dto) throws SerException;

    /**
     * 所有人事件总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long allCount(FatherDTO dto) throws SerException;

    /**
     * 事件总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(FatherDTO dto) throws SerException;

    /**
     * 总事件汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AreaCountBO> zongCount(EventDTO dto) throws SerException;

    /**
     * 已处理事件汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AreaCountBO> haveDealCount(EventDTO dto) throws SerException;

    /**
     * 未处理事件汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AreaCountBO> noDealCount(EventDTO dto) throws SerException;

    /**
     * 逾期未处理事件汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AreaCountBO> passNoDealCount(EventDTO dto) throws SerException;

    /**
     * 按照分类汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ClassifyCountBO> classifyCount(EventDTO dto) throws SerException;

    /**
     * 移动端列表获取数据
     *
     * @throws SerException
     */
    List<AppListDataBO> findAppList(String type) throws SerException;
    /**
     * 移动端跳转详情所需数据
     *
     * @throws SerException
     */
    FatherBO findFatherById(String id) throws SerException;
}