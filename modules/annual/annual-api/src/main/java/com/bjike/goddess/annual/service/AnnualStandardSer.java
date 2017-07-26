package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualStandardBO;
import com.bjike.goddess.annual.dto.AnnualStandardDTO;
import com.bjike.goddess.annual.entity.AnnualStandard;
import com.bjike.goddess.annual.excel.SonPermissionObject;
import com.bjike.goddess.annual.to.AnnualStandardTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 年假标准业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnualStandardSer extends Ser<AnnualStandard, AnnualStandardDTO> {

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
     * 保存年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws ActException
     */
    default AnnualStandardBO save(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 修改年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO update(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 删除年假表尊实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO delete(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 冻结年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO congeal(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 解冻年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO thaw(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 查询正常状态的年假标准
     *
     * @return
     * @throws SerException
     */
    default List<AnnualStandardBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 根据工龄获取年假标准
     *
     * @param seniority 工龄
     * @return
     * @throws SerException
     */
    default AnnualStandardBO findBySeniority(Integer seniority) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 年假标准数据传输对象
     * @return
     * @throws SerException
     */
    default List<AnnualStandardBO> maps(AnnualStandardDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取年假标准数据
     *
     * @param id 年假标准数据id
     * @return
     * @throws SerException
     */
    default AnnualStandardBO getById(String id) throws SerException {
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