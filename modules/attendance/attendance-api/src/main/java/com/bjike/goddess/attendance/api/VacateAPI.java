package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.dto.VacateConDTO;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 请假管理业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VacateAPI {
    /**
     * 获取请假天数
     *
     * @param to
     * @return
     * @throws SerException
     */
    Double getTime(VacateTO to) throws SerException;

    /**
     * 根据请假人和请假开始时间查询请假数据
     * @return
     */
    default List<VacateBO> findByCon(VacateConDTO vacateConDTO) throws SerException{return null;}


}