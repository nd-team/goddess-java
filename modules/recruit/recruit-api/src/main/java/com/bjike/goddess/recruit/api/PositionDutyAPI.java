package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.PositionDutyBO;
import com.bjike.goddess.recruit.dto.PositionDutyDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.PositionDutyTO;

import java.util.List;

/**
 * 公司岗位分类岗位职责业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 09:31 ]
 * @Description: [ 公司岗位分类岗位职责业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PositionDutyAPI {
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
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(PositionDutyDTO dto) throws SerException;

    /**
     * 根据id获取公司岗位分类岗位职责
     *
     * @param id
     * @return
     * @throws SerException
     */
    PositionDutyBO getId(String id) throws SerException;

    /**
     * 分页查询公司岗位分类岗位职责
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PositionDutyBO> list(PositionDutyDTO dto) throws SerException;

    /**
     * 保存公司岗位分类岗位职责
     *
     * @param to
     * @return
     * @throws SerException
     */
    PositionDutyBO save(PositionDutyTO to) throws SerException;

    /**
     * 根据id删除公司岗位分类岗位职责
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新公司岗位分类岗位职责
     *
     * @param to
     * @throws SerException
     */
    PositionDutyBO update(PositionDutyTO to) throws SerException;
    /**
     * 解冻公司岗位分类岗位职责
     *
     * @param id
     * @throws SerException
     */
    void thaw(String id) throws SerException;

    /**
     * 冻结公司岗位分类岗位职责
     *
     * @param id
     * @throws SerException
     */
    void congeal(String id) throws SerException;

}