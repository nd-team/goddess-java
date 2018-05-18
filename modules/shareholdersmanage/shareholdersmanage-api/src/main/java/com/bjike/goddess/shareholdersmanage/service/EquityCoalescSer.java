package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.shareholdersmanage.bo.EquityCoalescBO;
import com.bjike.goddess.shareholdersmanage.bo.ProportioAnmountBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityCoalescDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityCoalesc;
import com.bjike.goddess.shareholdersmanage.to.EquityCoalescTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 股权合并业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:24 ]
 * @Description: [ 股权合并业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityCoalescSer extends Ser<EquityCoalesc, EquityCoalescDTO> {
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
     * 股权合并列表总条数
     */
    default Long countCoalesc(EquityCoalescDTO equityCoalescDTO) throws SerException {
        return null;
    }

    /**
     * 一个股权合并
     *
     * @return class EquityCoalescBO
     */
    default EquityCoalescBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股权合并列表
     *
     * @param equityCoalescDTO 股权合并dto
     * @return class EquityCoalescBO
     * @throws SerException
     */
    default List<EquityCoalescBO> findList(EquityCoalescDTO equityCoalescDTO) throws SerException {
        return null;
    }

    /**
     * 股权合并添加
     *
     * @param equityCoalescTO 股权合并数据to
     * @throws SerException
     */
    default EquityCoalescBO save(EquityCoalescTO equityCoalescTO) throws SerException {
        return null;
    }

    /**
     * 股权合并编辑
     *
     * @param equityCoalescTO 股权合并数据to
     * @throws SerException
     */
    default EquityCoalescBO edit(EquityCoalescTO equityCoalescTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除股权合并
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 根据被查询占股比例和金额
     *
     * @param beCombined
     * @throws SerException
     */
    default ProportioAnmountBO proAnmount(String beCombined) throws SerException {
        return null;
    }
}