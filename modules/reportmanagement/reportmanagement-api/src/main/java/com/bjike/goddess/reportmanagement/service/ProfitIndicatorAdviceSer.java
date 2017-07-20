package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.ProfitIndicatorAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitIndicatorAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitIndicatorAdvice;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitIndicatorAdviceTO;

import java.util.List;

/**
 * 利润分析指标管理建议设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 05:24 ]
 * @Description: [ 利润分析指标管理建议设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProfitIndicatorAdviceSer extends Ser<ProfitIndicatorAdvice, ProfitIndicatorAdviceDTO> {
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
    List<ProfitIndicatorAdviceBO> list(ProfitIndicatorAdviceDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    ProfitIndicatorAdviceBO save(ProfitIndicatorAdviceTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(ProfitIndicatorAdviceTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    ProfitIndicatorAdviceBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(ProfitIndicatorAdviceDTO dto) throws SerException;
}