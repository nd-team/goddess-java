package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.PledgeEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.PledgeEquityDTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.PledgeEquityTO;

import java.util.List;

/**
 * 质押股权业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:32 ]
 * @Description: [ 质押股权业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PledgeEquityAPI {
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
     * 质押股权列表总条数
     */
    default Long countPledge(PledgeEquityDTO pledgeEquityDTO) throws SerException {
        return null;
    }

    /**
     * 一个质押股权
     *
     * @return class PledgeEquityBO
     */
    default PledgeEquityBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 质押股权列表
     *
     * @param pledgeEquityDTO 质押股权dto
     * @return class PledgeEquityBO
     * @throws SerException
     */
    default List<PledgeEquityBO> findList(PledgeEquityDTO pledgeEquityDTO) throws SerException {
        return null;
    }

    /**
     * 质押股权添加
     *
     * @param pledgeEquityTO 质押股权数据to
     * @throws SerException
     */
    default PledgeEquityBO save(PledgeEquityTO pledgeEquityTO) throws SerException {
        return null;
    }

    /**
     * 质押股权编辑
     *
     * @param pledgeEquityTO 质押股权数据to
     * @throws SerException
     */
    default PledgeEquityBO edit(PledgeEquityTO pledgeEquityTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除质押股权
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
}