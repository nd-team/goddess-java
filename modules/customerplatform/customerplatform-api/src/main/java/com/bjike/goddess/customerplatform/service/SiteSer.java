package com.bjike.goddess.customerplatform.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customerplatform.bo.SiteBO;
import com.bjike.goddess.customerplatform.dto.SiteDTO;
import com.bjike.goddess.customerplatform.entity.Site;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.to.SiteTO;

import java.util.List;

/**
 * 站点业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:29 ]
 * @Description: [ 站点业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SiteSer extends Ser<Site, SiteDTO> {

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
     * @param to
     * @throws SerException
     */
    default void save(SiteTO to) throws SerException {
        return;
    }

    /**
     * 修改
     *
     * @param to
     * @throws SerException
     */
    default void update(SiteTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 列表数据
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<SiteBO> maps(SiteDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取中标单位数据
     *
     * @param id
     * @throws SerException
     */
    default SiteBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal(SiteDTO dto) throws SerException {
        return null;
    }
    /**
     * 获取所有省份
     *
     * @throws SerException
     */
    List<String> getProvinces() throws SerException;

    /**
     * 根据省份获取市
     *
     * @throws SerException
     */
    List<String> getCity(String provinces) throws SerException;

    /**
     * 根据省份市获取区
     *
     * @throws SerException
     */
    List<String> getArea(String provinces,String city) throws SerException;

}