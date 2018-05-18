package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusCaseBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusManageBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareRosterBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareRosterDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusManageDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusManage;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusManageTO;

import java.util.List;

/**
 * 分红管理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ShareOutBonusManageSer extends Ser<ShareOutBonusManage, ShareOutBonusManageDTO> {
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
     * 分红管理列表总条数
     */
    default Long countOutBonus(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return null;
    }

    /**
     * 一个分红管理
     *
     * @return class ShareOutBonusManageBO
     */
    default ShareOutBonusManageBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 分红管理列表
     *
     * @param shareOutBonusManageDTO 分红管理dto
     * @return class ShareOutBonusManageBO
     * @throws SerException
     */
    default List<ShareOutBonusManageBO> findList(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return null;
    }

    /**
     * 分红管理添加
     *
     * @param shareOutBonusManageTO 分红管理数据to
     * @return class ShareOutBonusManageBO
     * @throws SerException
     */
    default ShareOutBonusManageBO save(ShareOutBonusManageTO shareOutBonusManageTO) throws SerException {
        return null;
    }

    /**
     * 分红管理编辑
     *
     * @param shareOutBonusManageTO 分红管理数据to
     * @return class ShareOutBonusManageBO
     * @throws SerException
     */
    default ShareOutBonusManageBO edit(ShareOutBonusManageTO shareOutBonusManageTO) throws SerException {
        return null;
    }


    /**
     * 根据id删除分红管理
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
    /**
     * 分红情况查询列表
     *
     * @return class ShareOutBonusCaseBO
     * @throws SerException
     */
    default List<ShareOutBonusCaseBO> findShareCase(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return null;
    }
    /**
     * 分红情况查询汇总
     *
     * @return class ShareOutBonusCaseBO
     * @throws SerException
     */
    default List<ShareOutBonusCaseBO> summShareCase(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return null;
    }

    /**
     * 股东花名册列表
     *
     * @return class ShareRosterBO
     * @throws SerException
     */
    default List<ShareRosterBO> findShareRoster(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return null;
    }
    /**
     * 股东花名册查看分红明细情况
     *
     * @return class ShareRosterDetailBO
     * @throws SerException
     */
    default List<ShareRosterDetailBO> findShareRosterDetail(String shareholderName) throws SerException {
        return null;
    }
    /**
     * 汇总股东花名册
     *
     * @return class ShareRosterBO
     * @throws SerException
     */
    default List<ShareRosterBO> summShareRoster(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        return null;
    }
    /**
     * 导出股东花名册Excel
     * @param shareOutBonusManageDTO
     * @throws SerException
     */
    byte[] exportExcel(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException;
}