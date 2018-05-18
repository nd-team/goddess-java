package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ShareOutBonusDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusDetailDTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusDetailTO;

import java.util.List;

/**
 * 分红明细业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:55 ]
 * @Description: [ 分红明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ShareOutBonusDetailAPI {
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
     * 分红明细列表总条数
     */
    default Long countOutBonus(ShareOutBonusDetailDTO shareOutBonusDetailDTO) throws SerException {
        return null;
    }

    /**
     * 一个分红明细
     *
     * @return class ShareOutBonusDetailBO
     */
    default ShareOutBonusDetailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 根据分红管理id获取分红明细列表
     *
     * @param ShareOutBonusManageId 分红管理id
     * @return class ShareOutBonusDetailBO
     * @throws SerException
     */
    default List<ShareOutBonusDetailBO> findListBySharId(String shareOutBonusManageId) throws SerException {
        return null;
    }

    /**
     * 分红明细添加
     *
     * @param shareOutBonusDetailTO 分红管理数据to
     * @return class ShareOutBonusDetailBO
     * @throws SerException
     */
    default ShareOutBonusDetailBO save(ShareOutBonusDetailTO shareOutBonusDetailTO) throws SerException {
        return null;
    }

    /**
     * 分红明细编辑
     *
     * @param shareOutBonusDetailTO 分红明细数据to
     * @return class ShareOutBonusDetailBO
     * @throws SerException
     */
    default ShareOutBonusDetailBO edit(ShareOutBonusDetailTO shareOutBonusDetailTO) throws SerException {
        return null;
    }


    /**
     * 根据id删除分红明细
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 根据分红管理id删除所有相关的分红明细
     *
     * @param shareOutBonusManageId
     * @throws SerException
     */
    default void deleteByShareId(String shareOutBonusManageId) throws SerException {

    }

    /**
     * 计算分红额或者所得税
     *
     * @param shareOutBonusManageId 分红管理id
     * @param Propor                比例
     * @return
     */
    default Double computAmount(String shareOutBonusManageId, Double Propor) throws SerException {
        return null;
    }
    /**
     * 计算所得税
     * @param shareOutBonusAmount  分红管理id
     * @param incomeTaxPropor 所得税比例
     * @return
     */
    default Double computIncomeTax(Double shareOutBonusAmount,Double incomeTaxPropor)throws SerException{
        return null;
    }
}