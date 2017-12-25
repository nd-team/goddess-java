package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceBO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceDTO;
import com.bjike.goddess.socialinsurance.excel.SonPermissionObject;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;
import com.bjike.goddess.socialinsurance.to.SocialInsuranceTO;

import java.util.List;

/**
 * 社会保险管理业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-21 04:23 ]
 * @Description: [ 社会保险管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SocialInsuranceSer extends Ser<SocialInsurance, SocialInsuranceDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 统计社保总数
     *
     * @param
     * @return class
     * @version v1
     */
    Long count(SocialInsuranceDTO dto) throws SerException;

    /**
     * 社保列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<SocialInsuranceBO> list(SocialInsuranceDTO dto) throws SerException;

    /**
     * 获取单个社保
     *
     * @param
     * @return class
     * @version v1
     */
    SocialInsuranceBO getOne(String id) throws SerException;

    /**
     * 添加单个社保
     *
     * @param
     * @return class
     * @version v1
     */
    void add(SocialInsuranceTO to) throws SerException;

    /**
     * 更新单个社保
     *
     * @param
     * @return class
     * @version v1
     */
    void update(SocialInsuranceTO to) throws SerException;

    /**
     * 删除单个社保
     *
     * @param
     * @return class
     * @version v1
     */
    void delete(String id) throws SerException;

    /**
     * 导入
     *
     * @param
     * @return class
     * @version v1
     */
    void importExcel(List<SocialInsuranceTO> tos) throws SerException;

    /**
     * 导出
     *
     * @param
     * @return class
     * @version v1
     */
    byte[] exportExcel(SocialInsuranceDTO dto) throws SerException;



}