package com.bjike.goddess.businessevaluate.api.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.FrontlineEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.FrontlineEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.FrontlineEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 一线体系评价业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:00 ]
 * @Description: [ 一线体系评价业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FrontlineEvaluateAPI {

    /**
     * 添加一线体系评价
     *
     * @param to 一线体系评价
     * @return 一线体系评价
     */
    FrontlineEvaluateBO addModel(FrontlineEvaluateTO to) throws SerException;

    /**
     * 编辑一线体系评价
     *
     * @param to 一线体系评价
     * @return 一线体系评价
     */
    FrontlineEvaluateBO editModel(FrontlineEvaluateTO to) throws SerException;

    /**
     * 删除一线体系评价
     *
     * @param id 一线体系评价id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return 一线体系评价结果集
     */
    List<FrontlineEvaluateBO> pageList(FrontlineEvaluateDTO dto) throws SerException;
}