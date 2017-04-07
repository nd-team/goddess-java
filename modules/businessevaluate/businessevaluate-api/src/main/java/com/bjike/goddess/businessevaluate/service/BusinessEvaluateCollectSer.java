package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.BusinessEvaluateCollectBO;
import com.bjike.goddess.businessevaluate.bo.EvaluateCollectTotalBO;
import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.entity.BusinessEvaluateCollect;
import com.bjike.goddess.businessevaluate.to.BusinessEvaluateCollectTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 商务评估汇总业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessEvaluateCollectSer extends Ser<BusinessEvaluateCollect, BusinessEvaluateCollectDTO> {

    /**
     * 添加商务评估汇总
     *
     * @param to 商务评估汇总
     * @return 商务评估汇总
     */
    BusinessEvaluateCollectBO insertModel(BusinessEvaluateCollectTO to) throws SerException;

    /**
     * 编辑商务评估汇总
     *
     * @param to 商务评估汇总
     * @return 商务评估汇总
     */
    BusinessEvaluateCollectBO updateModel(BusinessEvaluateCollectTO to) throws SerException;

    /**
     * 冻结商务评估汇总
     *
     * @param id 商务评估汇总id
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻商务评估汇总
     *
     * @param id 商务评估汇总id
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 商务评估汇总定时器结果集
     */
    List<BusinessEvaluateCollectBO> pageList(BusinessEvaluateCollectDTO dto) throws SerException;

    /**
     * 汇总详情
     * @param area 汇总条件地区
     * @param project 汇总条件项目
     * @return
     * @throws SerException
     */
    List<EvaluateCollectTotalBO> collectPageList(String area, String project) throws SerException;
}