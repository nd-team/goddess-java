package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.entity.NotEntryReason;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;

import java.util.List;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface NotEntryReasonSer extends Ser<NotEntryReason, NotEntryReasonDTO> {
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

}
