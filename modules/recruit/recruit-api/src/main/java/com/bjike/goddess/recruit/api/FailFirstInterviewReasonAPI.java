package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.vo.SonPermissionObject;

import java.util.List;
import java.util.Set;

/**
 * 未应约初试原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FailFirstInterviewReasonAPI {
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
     * 根据id查询未应约初试原因
     *
     * @param id 未应约初试原因唯一标识
     * @return class FailFirstInterviewReasonBO
     * @throws SerException
     */
    FailFirstInterviewReasonBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 未应约初试原因dto
     * @throws SerException
     */
    Long count(FailFirstInterviewReasonDTO dto) throws SerException;

    /**
     * 分页查询所有未应约初试原因
     *
     * @return
     * @throws SerException
     */
    List<FailFirstInterviewReasonBO> list(FailFirstInterviewReasonDTO dto) throws SerException;

    /**
     * 保存未应约初试原因
     *
     * @param failFirstInterviewReasonTO
     * @return
     * @throws SerException
     */
    FailFirstInterviewReasonBO save(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException;

    /**
     * 根据id删除未应约初试原因
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新未应约初试原因
     *
     * @param failFirstInterviewReasonTO
     * @throws SerException
     */
    void update(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException;

    /**
     * 查找所有未应约初试原因
     *
     * @return
     * @throws SerException
     */
    Set<String> allReason() throws SerException;
}
