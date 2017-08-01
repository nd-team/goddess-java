package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfare.dto.HeadPortraitHatDTO;
import com.bjike.goddess.staffwelfare.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.HeadPortraitHatTO;

import java.util.List;

/**
 * 头像帽业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HeadPortraitHatAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 新增头像帽
     *
     * @param to 头像帽
     * @return 头像帽
     */
    HeadPortraitHatBO addModel(HeadPortraitHatTO to) throws SerException;

    /**
     * 编辑头像帽
     *
     * @param to 头像帽
     * @return 头像帽
     */
    HeadPortraitHatBO editModel(HeadPortraitHatTO to) throws SerException;

    /**
     * 删除头像帽
     *
     * @param id 头像帽id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询头像帽
     *
     * @param dto 分页条件
     * @return 头像帽结果集
     */
    List<HeadPortraitHatBO> pageList(HeadPortraitHatDTO dto) throws SerException;
}