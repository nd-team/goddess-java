package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.royalty.bo.JobsBetABO;
import com.bjike.goddess.royalty.bo.ManageCommissionBO;
import com.bjike.goddess.royalty.dto.JobsBetADTO;
import com.bjike.goddess.royalty.dto.JobsBetDTO;
import com.bjike.goddess.royalty.dto.JobsBetEDTO;
import com.bjike.goddess.royalty.entity.JobsBet;
import com.bjike.goddess.royalty.to.CollectTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.JobsBetATO;

import java.util.List;

/**
 * 岗位间对赌表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface JobsBetSer extends Ser<JobsBet, JobsBetDTO> {
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
     * 岗位间对赌表列表总条数
     */
    default Long count(JobsBetEDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个岗位间对赌表
     *
     * @return class JobsBetABO
     */
    default JobsBetABO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 岗位间对赌表
     *
     * @param dto 岗位间对赌表数据dto
     * @return class JobsBetABO
     * @throws SerException
     */
    default List<JobsBetABO> list(JobsBetADTO dto) throws SerException {
        return null;
    }

    /**
     * 添加岗位间对赌表
     *
     * @param jobsBetATO 岗位间对赌表数据to
     * @throws SerException
     */
    default void insert(JobsBetATO jobsBetATO) throws SerException {
    }

    /**
     * 编辑岗位间对赌表
     *
     * @param jobsBetATO 岗位间对赌表数据to
     * @throws SerException
     */
    default void edit(JobsBetATO jobsBetATO) throws SerException {
    }

    /**
     * 根据id删除岗位间对赌表
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }
    /**
     * 汇总
     *
     * @param to 数据to
     * @throws SerException
     */
    default List<ManageCommissionBO> collect(CollectTO to) throws SerException {
        return null;
    }
}