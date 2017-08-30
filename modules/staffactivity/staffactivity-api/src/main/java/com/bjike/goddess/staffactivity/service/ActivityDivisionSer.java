package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffactivity.bo.ActivityDivisionBO;
import com.bjike.goddess.staffactivity.dto.ActivityDivisionDTO;
import com.bjike.goddess.staffactivity.entity.ActivityDivision;
import com.bjike.goddess.staffactivity.to.ActivityDivisionTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;

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
public interface ActivityDivisionSer extends Ser<ActivityDivision, ActivityDivisionDTO> {
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