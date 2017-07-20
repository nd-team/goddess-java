package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.costdetail.bo.DetailTypeBO;
import com.bjike.goddess.costdetail.dto.DetailTypeDTO;
import com.bjike.goddess.costdetail.entity.DetailType;
import com.bjike.goddess.costdetail.excel.SonPermissionObject;
import com.bjike.goddess.costdetail.to.DetailTypeTO;
import com.bjike.goddess.costdetail.to.GuidePermissionTO;

import java.util.List;

/**
 * 明细分类业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DetailTypeSer extends Ser<DetailType, DetailTypeDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 明细分类总条数
     */
    default Long countDetailType(DetailTypeDTO detailTypeDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取明细分类
     *
     * @return class CostDetailsBO
     */
    default DetailTypeBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 父节点查询获取所有明细分类名
     *
     * @return
     * @throws SerException
     */
    default List<String> findTypeName(String parNode) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param detailTypeTO 明细分类
     * @return class DetailTypeBO
     */
    default DetailTypeBO add(DetailTypeTO detailTypeTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param detailTypeTO 　明细分类
     * @return class DetailTypeBO
     */
    default DetailTypeBO edit(DetailTypeTO detailTypeTO) throws SerException {
        return null;
    }

    /**
     * 根据父节点查询所有分类信息
     * @return
     * @throws SerException
     */
    default List<DetailTypeBO> findByNode(String parNode) throws SerException{
        return null;
    }
}