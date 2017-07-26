package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfaremanage.dto.WishesStatementDTO;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.WishesStatementTO;

import java.util.List;

/**
 * 祝福语业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WishesStatementAPI {
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
     * 新增祝福语
     *
     * @param to 祝福语
     * @return 祝福语
     */
    WishesStatementBO addModel(WishesStatementTO to) throws SerException;

    /**
     * 编辑祝福语
     *
     * @param to 祝福语
     * @return 祝福语
     */
    WishesStatementBO editModel(WishesStatementTO to) throws SerException;

    /**
     * 删除祝福语
     *
     * @param id 祝福语id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询祝福语
     *
     * @param dto 分页条件
     * @return 祝福语结果集
     */
    List<WishesStatementBO> pageList(WishesStatementDTO dto) throws SerException;
}