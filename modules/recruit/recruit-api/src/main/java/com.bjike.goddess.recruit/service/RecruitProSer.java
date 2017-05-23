package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.entity.RecruitPro;
import com.bjike.goddess.recruit.to.RecruitProTO;

import java.util.List;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitProSer extends Ser<RecruitPro, RecruitProDTO> {

    /**
     * 分页查询招聘方案
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RecruitProBO> list(RecruitProDTO dto) throws SerException;

    /**
     * 保存招聘方案
     *
     * @param recruitProTO
     * @return
     * @throws SerException
     */
    RecruitProBO save(RecruitProTO recruitProTO) throws SerException;

    /**
     * 根据id删除招聘方案
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新招聘方案
     *
     * @param recruitProTO
     * @throws SerException
     */
    void update(RecruitProTO recruitProTO) throws SerException;

    /**
     * 运营商务部审核
     *
     * @param recruitProTO
     * @param pass
     * @throws SerException
     */
    void yyEdit(RecruitProTO recruitProTO, Boolean pass) throws SerException;

    /**
     * 总经办审核
     *
     * @param recruitProTO
     * @param pass
     * @throws SerException
     */
    void managerEdit(RecruitProTO recruitProTO, Boolean pass) throws SerException;
}
