package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffactivity.bo.ActivityDivisionBO;
import com.bjike.goddess.staffactivity.dto.ActivityDivisionDTO;
import com.bjike.goddess.staffactivity.to.ActivityDivisionTO;

import java.util.List;

/**
 * 活动分工业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivityDivisionAPI {

    /**
     * 根据id查询活动分工
     *
     * @param id 活动分工唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    ActivityDivisionBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 活动分工dto
     * @throws SerException
     */
    Long count(ActivityDivisionDTO dto) throws SerException;

    /**
     * 分页查询活动分工
     *
     * @param dto 活动分工dto
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    List<ActivityDivisionBO> list(ActivityDivisionDTO dto) throws SerException;

    /**
     * 保存活动分工
     *
     * @param to 活动分工to
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    ActivityDivisionBO save(ActivityDivisionTO to) throws SerException;

    /**
     * 根据id删除活动分工
     *
     * @param id 活动分工唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新活动分工
     *
     * @param to 活动分工to
     * @throws SerException
     */
    void update(ActivityDivisionTO to) throws SerException;

}