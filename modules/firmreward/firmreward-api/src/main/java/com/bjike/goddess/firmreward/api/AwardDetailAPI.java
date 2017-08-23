package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.dto.AwardDetailDTO;
import com.bjike.goddess.firmreward.excel.SonPermissionObject;
import com.bjike.goddess.firmreward.to.AwardDetailTO;
import com.bjike.goddess.firmreward.vo.GuidePermissionTO;

import java.util.List;

/**
 * 获奖明细业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AwardDetailAPI {

    /**
     * 根据id查询获奖明细
     *
     * @param id 获奖明细唯一标识
     * @return class AwardDetailBO
     * @throws SerException
     */
    AwardDetailBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 获奖明细dto
     * @throws SerException
     */
    Long count(AwardDetailDTO dto) throws SerException;

    /**
     * 分页查询获奖明细
     *
     * @return class AwardDetailBO
     * @throws SerException
     */
    List<AwardDetailBO> list(AwardDetailDTO dto) throws SerException;

    /**
     * 保存获奖明细
     *
     * @param to 获奖明细to
     * @return class AwardDetailBO
     * @throws SerException
     */
    AwardDetailBO save(AwardDetailTO to) throws SerException;

    /**
     * 根据id删除获奖明细
     *
     * @param id 获奖明细唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新获奖明细
     *
     * @param to 获奖明细to
     * @throws SerException
     */
    void update(AwardDetailTO to) throws SerException;


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



}