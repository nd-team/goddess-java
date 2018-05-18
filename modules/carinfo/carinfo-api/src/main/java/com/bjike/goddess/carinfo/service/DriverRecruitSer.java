package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.DriverRecruitBO;
import com.bjike.goddess.carinfo.dto.DriverRecruitDTO;
import com.bjike.goddess.carinfo.entity.DriverRecruit;
import com.bjike.goddess.carinfo.to.DriverRecruitTO;
import com.bjike.goddess.carinfo.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 司机招聘信息业务接口
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 08:27 ]
 * @Description: [ 司机招聘信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DriverRecruitSer extends Ser<DriverRecruit, DriverRecruitDTO> {

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
     * 保存
     *
     * @param to 司机招聘信息
     */
    void insertModel(DriverRecruitTO to) throws SerException;

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


    /**
     * 根据Id查询
     * @param id id
     */
    DriverRecruitBO findOne(String id) throws SerException;
}