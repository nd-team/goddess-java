package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.MainIncomeBO;
import com.bjike.goddess.receivable.dto.MainIncomeDTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.MainIncomeTO;

import java.util.List;

/**
 * 主营业务收入业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MainIncomeAPI {
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
     * 主营业务收入列表总条数
     */
    default Long count(MainIncomeDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个主营业务收入
     *
     * @return class MainIncomeBO
     */
    default MainIncomeBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 获取主营业务收入
     *
     * @param dto 主营业务收入dto
     * @return class MainIncomeBO
     * @throws SerException
     */
    default List<MainIncomeBO> list(MainIncomeDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加主营业务收入
     *
     * @param to 主营业务收入数据to
     * @return class MainIncomeBO
     * @throws SerException
     */
    default MainIncomeBO add(MainIncomeTO to) throws SerException {
        return null;
    }

    /**
     * 编辑主营业务收入
     *
     * @param to 主营业务收入数据to
     * @return class MainIncomeBO
     * @throws SerException
     */
    default MainIncomeBO edit(MainIncomeTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除主营业务收入
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
}