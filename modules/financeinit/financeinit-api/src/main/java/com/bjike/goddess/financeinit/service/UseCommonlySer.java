package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.UseCommonlyBO;
import com.bjike.goddess.financeinit.dto.UseCommonlyDTO;
import com.bjike.goddess.financeinit.entity.UseCommonly;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.UseCommonlyTO;

import java.util.List;

/**
 * 常用摘要业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface UseCommonlySer extends Ser<UseCommonly, UseCommonlyDTO> {
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
     * 常用摘要列表总条数
     */
    default Long countUse(UseCommonlyDTO useCommonlyDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取常用摘要列表
     *
     * @return class UseCommonlyBO
     */
    default UseCommonlyBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 常用摘要列表
     *
     * @return class UseCommonlyBO
     */
    default List<UseCommonlyBO> listUse(UseCommonlyDTO useCommonlyDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param useCommonlyTO 常用摘要
     * @return class UseCommonlyBO
     */
    default UseCommonlyBO addUse(UseCommonlyTO useCommonlyTO) throws SerException {
        return null;
    }
}