package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityStaffListDTO;
import com.bjike.goddess.staffactivity.entity.ActivityStaffList;
import com.bjike.goddess.staffactivity.to.ActivityStaffListTO;

import java.util.List;

/**
 * 活动人员名单业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 11:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivityStaffListSer extends Ser<ActivityStaffList, ActivityStaffListDTO> {

    /**
     * 分页查询活动人员名单
     *
     * @param dto 活动人员名单dto
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    List<ActivityStaffListBO> list(ActivityStaffListDTO dto) throws SerException;

    /**
     * 保存活动人员名单
     *
     * @param to 人员活动名单to
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    ActivityStaffListBO save(ActivityStaffListTO to) throws SerException;

    /**
     * 根据id删除活动人员名单
     *
     * @param id 活动人员名单唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新活动人员名单
     *
     * @param to 更新活动人员名单to
     * @throws SerException
     */
    void update(ActivityStaffListTO to) throws SerException;

}