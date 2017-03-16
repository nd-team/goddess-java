package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.to.RecruitDemandTO;

import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitDemandAPI {

    /**
     * 分页查询招聘需求
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RecruitDemandBO> list(RecruitDemandDTO dto) throws SerException;

    /**
     * 保存招聘需求
     *
     * @param recruitDemandTO
     * @return
     * @throws SerException
     */
    RecruitDemandBO save(RecruitDemandTO recruitDemandTO) throws SerException;

    /**
     * 根据id删除招聘需求
     *
     * @param id 招聘需求id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新招聘需求
     *
     * @param recruitDemandBO
     * @throws SerException
     */
    void update(RecruitDemandBO recruitDemandBO) throws SerException;
}
