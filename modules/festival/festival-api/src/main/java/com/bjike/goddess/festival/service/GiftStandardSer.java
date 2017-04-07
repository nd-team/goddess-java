package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.festival.bo.GiftStandardBO;
import com.bjike.goddess.festival.entity.GiftStandard;
import com.bjike.goddess.festival.dto.GiftStandardDTO;
import com.bjike.goddess.festival.to.GiftStandardTO;

import java.util.List;

/**
 * 节假日礼品标准业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:02 ]
 * @Description: [ 节假日礼品标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface GiftStandardSer extends Ser<GiftStandard, GiftStandardDTO> {

    /**
     * 节假日礼品标准列表总条数
     *
     */
    default Long countGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {
        return null;
    }
    /**
     * 节假日礼品标准列表
     * @return class GiftStandardBO
     */
    default List<GiftStandardBO> listGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {return null;}
    /**
     *  添加
     * @param giftStandardTO 节假日礼品标准信息
     * @return class GiftStandardBO
     */
    default GiftStandardBO addGiftStandard(GiftStandardTO giftStandardTO) throws SerException { return null;}

    /**
     *  编辑
     * @param giftStandardTO 节假日礼品标准信息
     * @return class GiftStandardBO
     */
    default GiftStandardBO editGiftStandard(GiftStandardTO giftStandardTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteGiftStandard(String id ) throws SerException {return;};

    /**
     * 根据获取礼品类型
     */
    default List<String> getGiftByFestivalName( ) throws SerException {return null;}


}