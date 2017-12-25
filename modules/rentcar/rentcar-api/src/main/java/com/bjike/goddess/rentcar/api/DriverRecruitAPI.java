package com.bjike.goddess.rentcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rentcar.bo.DriverRecruitBO;
import com.bjike.goddess.rentcar.dto.DriverRecruitDTO;
import com.bjike.goddess.rentcar.to.DriverRecruitTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;

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
public interface DriverRecruitAPI {

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
     * 新增
     * @param to 司机招聘信息
     * @return
     * @throws SerException
     */
    DriverRecruitBO save(DriverRecruitTO to) throws SerException;

    /**
     * 编辑
     * @param to 司机招聘信息
     * @return
     * @throws SerException
     */
    DriverRecruitBO edit(DriverRecruitTO to) throws SerException;

    /**
     * 删除
     * @param id id
     */
    void delete(String id) throws SerException;

    /**
     * 查询总记录数
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(DriverRecruitDTO dto) throws SerException;

    /**
     * 根据Id查询
     * @param id id
     */
    DriverRecruitBO findById(String id) throws SerException;

    /**
     * 列表
     * @param dto
     * @return
     * @throws SerException
     */
    List<DriverRecruitBO> pageList(DriverRecruitDTO dto) throws SerException;

    /**
     * 审核
     * @param id id
     * @param suggest 意见
     * @param audit 结果
     */
    void audit(String id, String suggest, Boolean audit) throws SerException;
}