package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.DebtBO;
import com.bjike.goddess.reportmanagement.bo.DetailBO;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.entity.Debt;
import com.bjike.goddess.reportmanagement.to.DebtTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;

import java.util.List;

/**
 * 负债表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DebtSer extends Ser<Debt, DebtDTO> {
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
     * 查看负债与权益结构分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<StructureBO> debtStructure(DebtDTO dto) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<DebtBO> list(DebtDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    DebtBO save(DebtTO to) throws SerException;

    /**
     * 查找金额明细
     *
     * @param id
     * @param dto
     * @return
     * @throws SerException
     */
    List<DetailBO> findDetails(String id, DebtDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(DebtDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    DebtBO findByID(String id) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(DebtTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 列表1
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<DebtBO> list1(DebtDTO dto) throws SerException;
}