package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.shareholdersmanage.bo.FairChangeBO;
import com.bjike.goddess.shareholdersmanage.bo.FairTypeAndPerBO;
import com.bjike.goddess.shareholdersmanage.dto.FairChangeDTO;
import com.bjike.goddess.shareholdersmanage.entity.FairChange;
import com.bjike.goddess.shareholdersmanage.to.FairChangeTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 公允值变动业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FairChangeSer extends Ser<FairChange, FairChangeDTO> {
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
     * 公允值变动列表总条数
     */
    default Long countFair(FairChangeDTO fairChangeDTO) throws SerException {
        return null;
    }

    /**
     * 一个公允值变动
     *
     * @return class FairChangeBO
     */
    default FairChangeBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 公允值变动列表
     *
     * @param fairChangeDTO 股权赠与dto
     * @return class FairChangeBO
     * @throws SerException
     */
    default List<FairChangeBO> findList(FairChangeDTO fairChangeDTO) throws SerException {
        return null;
    }

    /**
     * 公允值变动添加
     *
     * @param fairChangeTO 股权赠与数据to
     * @throws SerException
     */
    default FairChangeBO save(FairChangeTO fairChangeTO) throws SerException {
        return null;
    }

    /**
     * 公允值变动编辑
     *
     * @param fairChangeTO 公允值变动数据to
     * @throws SerException
     */
    default FairChangeBO edit(FairChangeTO fairChangeTO) throws SerException {
        return null;
    }
    /**
     * 获取所有的股权类型和对应每股价格
     *
     * @throws SerException
     */
    default List<FairTypeAndPerBO> typeAndPer() throws SerException {
        return null;
    }
    /**
     * 根据股权类型查询对应的每股价格
     *
     * @throws SerException
     */
    default Double findPriceByType(String equityType) throws SerException {
        return null;
    }
}