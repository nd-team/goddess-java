package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.BusinessEvaluateCollectBO;
import com.bjike.goddess.businessevaluate.bo.EvaluateCollectTotalBO;
import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.to.BusinessEvaluateCollectTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 商务评价汇总业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:12 ]
 * @Description: [ 商务评价汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessEvaluateCollectAPI {

    /**
     * 添加商务评价汇总
     *
     * @param to 商务评价汇总
     * @return 商务评价汇总
     */
    BusinessEvaluateCollectBO addModel(BusinessEvaluateCollectTO to) throws SerException;

    /**
     * 编辑商务评价汇总
     *
     * @param to 商务评价汇总
     * @return 商务评价汇总
     */
    BusinessEvaluateCollectBO editModel(BusinessEvaluateCollectTO to) throws SerException;

    /**
     * 删除商务评价汇总
     *
     * @param id 商务评价汇总id
     */
    void delete(String id) throws SerException;

    /**
     * 冻结商务评价汇总
     *
     * @param id 商务评价汇总id
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻商务评价汇总
     *
     * @param id 商务评价汇总id
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 商务评价汇总分页查询
     *
     * @param dto 分页条件
     * @return 商务评价汇总结果集
     */
    List<BusinessEvaluateCollectBO> pageList(BusinessEvaluateCollectDTO dto) throws SerException;

    /**
     * 汇总详情
     * @param area 汇总条件地区
     * @param project 汇总条件项目
     * @return 汇总结果
     */
    List<EvaluateCollectTotalBO> collectionTotal(String area, String project) throws SerException;
}