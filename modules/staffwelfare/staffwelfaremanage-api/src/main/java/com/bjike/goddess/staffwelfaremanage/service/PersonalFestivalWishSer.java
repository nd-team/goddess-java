package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalWishBO;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalWishDTO;
import com.bjike.goddess.staffwelfaremanage.entity.PersonalFestivalWish;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 节日祝福语业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 03:08 ]
 * @Description: [ 节日祝福语业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PersonalFestivalWishSer extends Ser<PersonalFestivalWish, PersonalFestivalWishDTO> {

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
     * 分页查询个人节日祝福
     *
     * @param dto 分页条件
     * @return 个人节日祝福结果集
     */
    List<PersonalFestivalWishBO> pageList(PersonalFestivalWishDTO dto) throws SerException;
}