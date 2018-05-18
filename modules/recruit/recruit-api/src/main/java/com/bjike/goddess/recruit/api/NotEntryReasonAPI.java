package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;

import java.util.List;
import java.util.Set;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface NotEntryReasonAPI {
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
     * 根据id查询未入职原因
     *
     * @param id 未入职原因唯一标识
     * @return class NotEntryReasonBO
     * @throws SerException
     */
    NotEntryReasonBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 未入职原因dto
     * @throws SerException
     */
    Long count(NotEntryReasonDTO dto) throws SerException;

    /**
     * 分页查询未入职原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<NotEntryReasonBO> list(NotEntryReasonDTO dto) throws SerException;

    /**
     * 保存未入职原因
     *
     * @param notEntryReasonTO
     * @return
     * @throws SerException
     */
    NotEntryReasonBO save(NotEntryReasonTO notEntryReasonTO) throws SerException;

    /**
     * 根据id删除未入职原因
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新未入职原因
     *
     * @param notEntryReasonTO
     * @throws SerException
     */
    void update(NotEntryReasonTO notEntryReasonTO) throws SerException;

    /**
     * 查找所有未入职原因
     *
     * @return
     * @throws SerException
     */
    Set<String> allReason() throws SerException;
}
