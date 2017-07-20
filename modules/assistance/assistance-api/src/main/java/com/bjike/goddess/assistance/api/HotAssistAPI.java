package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.HotAssistBO;
import com.bjike.goddess.assistance.dto.HotAssistDTO;
import com.bjike.goddess.assistance.to.HotAssistTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 高温补助业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:52 ]
 * @Description: [ 高温补助业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HotAssistAPI {

    /**
     * 高温补助列表总条数
     *
     */
    default Long countHotAssist(HotAssistDTO hotAssistDTO) throws SerException {
        return null;
    }
    /**
     * 一个高温补助
     * @return class HotAssistBO
     */
    default HotAssistBO getOneById(String id) throws SerException {return null;}
    /**
     * 高温补助列表
     * @return class HotAssistBO
     */
    default List<HotAssistBO> listHotAssist(HotAssistDTO hotAssistDTO) throws SerException {return null;}
    /**
     *  添加
     * @param hotAssistTO 高温补助信息
     * @return class HotAssistBO
     */
    default HotAssistBO addHotAssist(HotAssistTO hotAssistTO) throws SerException { return null;}

    /**
     *  编辑
     * @param hotAssistTO 高温补助信息
     * @return class HotAssistBO
     */
    default HotAssistBO editHotAssist(HotAssistTO hotAssistTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteHotAssist(String id ) throws SerException {return;};

    /**
     * 根据地区汇总
     * @return class HotAssistBO
     */
    default List<HotAssistBO> collectByArea (HotAssistDTO hotAssistDTO) throws SerException {return null;}
    /**
     * 根据项目组汇总
     * @return class HotAssistBO
     */
    default List<HotAssistBO> collectByProGroup (HotAssistDTO hotAssistDTO) throws SerException {return null;}


}