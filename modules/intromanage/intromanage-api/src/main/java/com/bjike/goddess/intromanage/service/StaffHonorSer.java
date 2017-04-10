package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.intromanage.bo.StaffHonorBO;
import com.bjike.goddess.intromanage.entity.StaffHonor;
import com.bjike.goddess.intromanage.dto.StaffHonorDTO;
import com.bjike.goddess.intromanage.to.StaffHonorTO;

import java.util.List;

/**
 * 员工荣誉业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:34 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffHonorSer extends Ser<StaffHonor, StaffHonorDTO> {

    /**
     * 分页查询员工荣誉
     *
     * @return class StaffHonorBO
     * @throws SerException
     */
    List<StaffHonorBO> list(StaffHonorDTO dto) throws SerException;

    /**
     * 保存员工荣誉
     *
     * @param to 员工荣誉to
     * @return class StaffHonorBO
     * @throws SerException
     */
    StaffHonorBO save(StaffHonorTO to) throws SerException;

    /**
     * 根据id删除员工荣誉
     *
     * @param id 员工荣誉唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新员工荣誉
     *
     * @param to 员工荣誉to
     * @throws SerException
     */
    void update(StaffHonorTO to) throws SerException;

}