package com.bjike.goddess.rentcar.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.rentcar.bo.DriverRecruitBO;
import com.bjike.goddess.rentcar.dto.DriverRecruitDTO;
import com.bjike.goddess.rentcar.entity.DriverRecruit;
import com.bjike.goddess.rentcar.to.DriverRecruitTO;

import java.util.List;

/**
 * 司机招聘信息业务接口
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 08:28 ]
 * @Description: [ 司机招聘信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DriverRecruitSer extends Ser<DriverRecruit, DriverRecruitDTO> {

    /**
     * 保存
     *
     * @param to 司机招聘信息
     */
    DriverRecruitBO insertModel(DriverRecruitTO to) throws SerException;

    /**
     * 更新
     *
     * @param to 司机招聘信息
     */
    DriverRecruitBO updateModel(DriverRecruitTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     */
    List<DriverRecruitBO> pageList(DriverRecruitDTO dto) throws SerException;

    /**
     * 审核
     *
     * @param id      id
     * @param suggest 意见
     * @param audit   结果
     */
    void audit(String id, String suggest, Boolean audit) throws SerException;
}