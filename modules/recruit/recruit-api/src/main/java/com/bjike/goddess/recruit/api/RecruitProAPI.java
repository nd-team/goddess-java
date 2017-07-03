package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.to.RecruitProTO;
import com.bjike.goddess.recruit.type.AuditType;

import java.util.List;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitProAPI {

    /**
     * 根据id查询招聘方案
     *
     * @param id 招聘方案唯一标识
     * @return class RecruitProBO
     * @throws SerException
     */
    RecruitProBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 招聘方案dto
     * @throws SerException
     */
    Long count(RecruitProDTO dto) throws SerException;

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
     * 综合资源部意见
     *
     * @param id 招聘方案唯一标识
     * @param zhOpinion 综合资源部意见
     * @throws SerException
     */
    void zhOpinion(String id, String zhOpinion) throws SerException;

    /**
     * 运营商务部审核
     *
     * @param id 招聘方案唯一标识
     * @param yyOpinion 运营商务部意见
     * @throws SerException
     */
    void yyOpinion(String id, String yyOpinion) throws SerException;

    /**
     * 总经办意见
     *
     * @param id 招聘方案唯一标识
     * @param zjbOpinion 总经办意见
     * @param auditType 审核类型
     * @throws SerException
     */
    void zjbOpinion(String id, String zjbOpinion, AuditType auditType) throws SerException;
}
