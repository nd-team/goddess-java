package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.SchemeApplicationBO;
import com.bjike.goddess.staffshares.bo.SchemeBO;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.SchemeDTO;
import com.bjike.goddess.staffshares.to.SchemeApplyTO;

import java.util.List;

/**
 * 员工持股管理业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 08:59 ]
 * @Description: [ 员工持股管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SchemeAPI {

    /**
     * 申请
     */
    void save(SchemeApplyTO to) throws SerException;

    /**
     * 编辑
     */
    void update(SchemeApplyTO to) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 股份发行方案列表
     */
    List<SchemeApplicationBO> maps(SchemeDTO dto) throws SerException;

    /**
     * 获取一条数据
     */
    SchemeBO getById(String id) throws SerException;

    /**
     * 获取总条数
     */
    Long getTotal(SchemeDTO schemeDTO) throws SerException;

    /**
     * 审核
     */
    void examine(SchemeApplyTO to) throws SerException;

    /**
     * 发行
     */
    void issue(String id) throws SerException;



    /**
     * 交易中心列表
     */
    List<SchemeIssueBO> list(SchemeDTO dto) throws SerException;

    /**
     * 获取一条交易中心数据
     */
    SchemeIssueBO getOne(String id) throws SerException;

    /**
     * 获取交易中心总条数
     */
    Long count(SchemeDTO schemeDTO) throws SerException;

}