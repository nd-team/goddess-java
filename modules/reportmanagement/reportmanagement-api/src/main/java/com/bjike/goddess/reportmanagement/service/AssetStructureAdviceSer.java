package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.AssetStructureAdviceBO;
import com.bjike.goddess.reportmanagement.dto.AssetStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.AssetStructureAdvice;
import com.bjike.goddess.reportmanagement.to.AssetStructureAdviceTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;

import java.util.List;

/**
 * 资产结构管理建议设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:38 ]
 * @Description: [ 资产结构管理建议设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssetStructureAdviceSer extends Ser<AssetStructureAdvice, AssetStructureAdviceDTO> {
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
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AssetStructureAdviceBO> list(AssetStructureAdviceDTO dto) throws SerException;

    /**
     * 添加
     * @param to
     * @return
     * @throws SerException
     */
    AssetStructureAdviceBO save(AssetStructureAdviceTO to) throws SerException;

    /**
     * 编辑
     * @param to
     * @throws SerException
     */
    void edit(AssetStructureAdviceTO to) throws SerException;

    /**
     * 删除
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     * @param id
     * @return
     * @throws SerException
     */
    AssetStructureAdviceBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AssetStructureAdviceDTO dto) throws SerException;
}