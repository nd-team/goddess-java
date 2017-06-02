package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.HonorAndQualityBO;
import com.bjike.goddess.intromanage.dto.HonorAndQualityDTO;
import com.bjike.goddess.intromanage.to.HonorAndQualityTO;

import java.util.List;

/**
 * 荣誉与资质业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:28 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HonorAndQualityAPI {

    /**
     * 根据id查询荣誉与资质
     *
     * @param id 荣誉与资质唯一标识
     * @return class HonorAndQualityBO
     * @throws SerException
     */
    HonorAndQualityBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 荣誉与资质dto
     * @throws SerException
     */
    Long count(HonorAndQualityDTO dto) throws SerException;

    /**
     * 分页查询荣誉与资质
     *
     * @return class HonorAndQualityBO
     * @throws SerException
     */
    List<HonorAndQualityBO> list(HonorAndQualityDTO dto) throws SerException;

    /**
     * 保存荣誉与资质
     *
     * @param to 荣誉与资质to
     * @return class HonorAndQualityBO
     * @throws SerException
     */
    HonorAndQualityBO save(HonorAndQualityTO to) throws SerException;

    /**
     * 根据id删除荣誉与资质
     *
     * @param id 荣誉与资质唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新荣誉与资质
     *
     * @param to 荣誉与资质to
     * @throws SerException
     */
    void update(HonorAndQualityTO to) throws SerException;

}