package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.bo.CostDetailsAddEditBO;
import com.bjike.goddess.costdetail.bo.CostDetailsAddReturnBO;
import com.bjike.goddess.costdetail.bo.CostDetailsBO;
import com.bjike.goddess.costdetail.bo.CostDetailsYeCollBO;
import com.bjike.goddess.costdetail.dto.CostDetailsDTO;
import com.bjike.goddess.costdetail.to.CostDetailsAddEditTO;
import com.bjike.goddess.costdetail.to.GuidePermissionTO;

import java.util.List;

/**
 * 成本明细业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostDetailsAPI {
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
     * 成本明细总条数
     */
    default Long count(CostDetailsDTO costDetailsDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取成本明细列表
     *
     * @return class CostDetailsBO
     */
    default CostDetailsBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获得成本明细及其所有明细数据
     * @return
     * @throws SerException
     */
    default CostDetailsAddEditBO getAllById(String id) throws SerException{
        return null;
    }

    /**
     * 成本明细列表
     *
     * @return class CostDetailsBO
     */
    default List<CostDetailsBO> list(CostDetailsDTO costDetailsDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param costDetailsAddEditTO 成本明细
     * @return class CostDetailsBO
     */
    default CostDetailsBO add(CostDetailsAddEditTO costDetailsAddEditTO) throws SerException {
        return null;
    }
    /**
     * 编辑
     *
     * @param costDetailsAddEditTO 　成本明细
     * @return class CostDetailsBO
     */
    default CostDetailsBO edit(CostDetailsAddEditTO costDetailsAddEditTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 查看详情
     * @param id
     * @return
     * @throws SerException
     */
    default CostDetailsAddEditBO seeDetail(String id) throws SerException {
        return null;
    }

    /**
     * 月汇总
     * @param costTime 时间
     * @param department 部门
     * @return
     * @throws SerException
     */
    default CostDetailsAddEditBO monthCollect(String costTime,String [] department) throws SerException {
        return null;
    }
    /**
     * 获取所有的部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findAllDetails() throws SerException {
        return null;
    }

    /**
     * 年汇总
     * @param years 时间
     * @return
     * @throws SerException
     */
    default List<CostDetailsYeCollBO> yearCollect(Integer years) throws SerException {
        return null;
    }
    /**
     * 返回添加数据
     * @return
     * @throws SerException
     */
    default List<CostDetailsAddReturnBO> returnAddResult() throws SerException {
        return null;
    }

    /**
     * 获取所有组织结构中的部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findAddAllDetails() throws SerException {
        return null;
    }
}