package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.festival.bo.WelfareBO;
import com.bjike.goddess.festival.dto.WelfareDTO;
import com.bjike.goddess.festival.entity.Welfare;
import com.bjike.goddess.festival.dto.WelfareDTO;
import com.bjike.goddess.festival.to.WelfareTO;

import java.util.List;

/**
 * 节假日礼品福利业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:17 ]
 * @Description: [ 节假日礼品福利业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WelfareSer extends Ser<Welfare, WelfareDTO> {
    /**
     * 节假日礼品福利列表总条数
     *
     */
    default Long countWelfare(WelfareDTO welfareDTO) throws SerException {
        return null;
    }
    /**
     * 节假日礼品福利列表
     * @return class WelfareBO
     */
    default List<WelfareBO> listWelfare(WelfareDTO welfareDTO) throws SerException {return null;}
    /**
     *  添加
     * @param welfareTO 节假日礼品福利信息
     * @return class WelfareBO
     */
    default WelfareBO addWelfare(WelfareTO welfareTO) throws SerException { return null;}

    /**
     *  编辑
     * @param welfareTO 节假日礼品福利信息
     * @return class WelfareBO
     */
    default WelfareBO editWelfare(WelfareTO welfareTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteWelfare(String id ) throws SerException {return;};

    /**
     * 根据节日方案查询节假日福利
     * @return class WelfareBO
     */
    default List<WelfareBO> getWelfare(String holidayProgrammeId) throws SerException {return null;}


}