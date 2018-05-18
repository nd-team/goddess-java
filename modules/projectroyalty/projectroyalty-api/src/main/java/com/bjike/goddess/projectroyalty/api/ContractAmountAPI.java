package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.ContractAmountBO;
import com.bjike.goddess.projectroyalty.dto.ContractAmountDTO;
import com.bjike.goddess.projectroyalty.to.ContractAmountTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;

import java.util.List;

/**
 * 合同金额业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:45 ]
 * @Description: [ 合同金额业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractAmountAPI {


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
     * @param to 合同金额传输对象
     * @return
     * @throws SerException
     */
    default ContractAmountBO save(ContractAmountTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 合同金额传输对象
     * @return
     * @throws SerException
     */
    default ContractAmountBO update(ContractAmountTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 合同金额数据id
     * @return
     * @throws SerException
     */
    default ContractAmountBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取合同金额数据
     *
     * @param id 合同金额数据id
     * @return
     * @throws SerException
     */
    default ContractAmountBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 合同金额数据传输对象
     * @return
     * @throws SerException
     */
    default List<ContractAmountBO> maps(ContractAmountDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取合同金额选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findOpinion() throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

}