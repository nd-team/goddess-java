package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FailInviteReasonBO;
import com.bjike.goddess.recruit.dto.FailInviteReasonDTO;
import com.bjike.goddess.recruit.to.FailInviteReasonTO;

import java.util.List;

/**
 * 未邀约成功原因
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FailInviteReasonAPI {

    /**
     * 根据id查询未邀约成功原因
     *
     * @param id 未邀约成功原因唯一标识
     * @return class FailInviteReasonBO
     * @throws SerException
     */
    FailInviteReasonBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 未邀约成功原因dto
     * @throws SerException
     */
    Long count(FailInviteReasonDTO dto) throws SerException;

    /**
     * 分页查询所有未成功邀约原因
     *
     * @param failInviteReasonDTO
     * @return
     * @throws SerException
     */
    List<FailInviteReasonBO> list(FailInviteReasonDTO failInviteReasonDTO) throws SerException;

    /**
     * 保存未邀约成功原因
     *
     * @param failInviteReasonTO
     * @return
     * @throws SerException
     */
    FailInviteReasonBO save(FailInviteReasonTO failInviteReasonTO) throws SerException;

    /**
     * 根据id删除未邀约成功原因
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新未邀约成功原因
     *
     * @param failInviteReasonTO
     * @throws SerException
     */
    void update(FailInviteReasonTO failInviteReasonTO) throws SerException;
}
