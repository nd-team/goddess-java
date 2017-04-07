package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.AreaRelationerBO;
import com.bjike.goddess.festival.dto.AreaRelationerDTO;
import com.bjike.goddess.festival.to.AreaRelationerTO;

import java.util.List;

/**
 * 各地区紧急联系人业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:13 ]
 * @Description: [ 各地区紧急联系人业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaRelationerAPI {

    /**
     * 各地区紧急联系人列表总条数
     *
     */
    default Long countAreaRelationer(AreaRelationerDTO areaRelationerDTO) throws SerException {
        return null;
    }
    /**
     * 各地区紧急联系人列表
     * @return class AreaRelationerBO
     */
    default List<AreaRelationerBO> listAreaRelationer(AreaRelationerDTO areaRelationerDTO) throws SerException {return null;}
    /**
     *  添加
     * @param areaRelationerTO 各地区紧急联系人信息
     * @return class AreaRelationerBO
     */
    default AreaRelationerBO addAreaRelationer(AreaRelationerTO areaRelationerTO) throws SerException { return null;}

    /**
     *  编辑
     * @param areaRelationerTO 各地区紧急联系人信息
     * @return class AreaRelationerBO
     */
    default AreaRelationerBO editAreaRelationer(AreaRelationerTO areaRelationerTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteAreaRelationer(String id ) throws SerException {return;};

    /**
     * 根据节日方案查询各地区联系人
     * @return class AreaRelationerBO
     */
    default List<AreaRelationerBO> getAreaRelationer(String holidayProgrammeId) throws SerException {return null;}

}