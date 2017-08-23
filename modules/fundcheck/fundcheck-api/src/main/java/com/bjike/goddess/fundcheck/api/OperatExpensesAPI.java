package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.OperatExpensesBO;
import com.bjike.goddess.fundcheck.dto.OperatExpensesDTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesCollectTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesTO;

import java.util.List;

/**
 * 营业费用业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OperatExpensesAPI {
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
     * 营业费用列表总条数
     */
    default Long count(OperatExpensesDTO operatExpensesDTO) throws SerException {
        return null;
    }

    /**
     * 一个营业费用
     *
     * @return class OperatExpensesBO
     */
    default OperatExpensesBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 营业费用
     *
     * @param operatExpensesDTO 营业费用dto
     * @return class OperatExpensesBO
     * @throws SerException
     */
    default List<OperatExpensesBO> findList(OperatExpensesDTO operatExpensesDTO) throws SerException {
        return null;
    }

    /**
     * 添加营业费用
     *
     * @param operatExpensesTO 营业费用数据to
     * @return class OperatExpensesBO
     * @throws SerException
     */
    default OperatExpensesBO insert(OperatExpensesTO operatExpensesTO) throws SerException {
        return null;
    }

    /**
     * 编辑营业费用
     *
     * @param operatExpensesTO 营业费用数据to
     * @return class OperatExpensesBO
     * @throws SerException
     */
    default OperatExpensesBO edit(OperatExpensesTO operatExpensesTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除营业费用
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param to
     * @return OperatExpensesBO
     * @throws SerException
     */
    default List<OperatExpensesBO> collect(OperatExpensesCollectTO to) throws SerException {
        return null;
    }
    /**
     * 获取所有类型
     * @return String
     * @throws SerException
     */
    default List<String> listType() throws SerException {
        return null;
    }
    /**
     * 导入
     *
     * @param operatExpensesTOS 营业费用
     * @return class OperatExpensesBO
     */
    default OperatExpensesBO importExcel(List<OperatExpensesTO> operatExpensesTOS) throws SerException {
        return null;
    }
    /**
     * 导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;
}