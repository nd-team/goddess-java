package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.GiftStandardBO;
import com.bjike.goddess.festival.dto.GiftStandardDTO;
import com.bjike.goddess.festival.service.GiftStandardSer;
import com.bjike.goddess.festival.to.GiftStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节假日礼品标准业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:02 ]
 * @Description: [ 节假日礼品标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("giftStandardApiImpl")
public class GiftStandardApiImpl implements GiftStandardAPI {

    @Autowired
    private GiftStandardSer giftStandardSer;

    @Override
    public Long countGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {
        return giftStandardSer.countGiftStandard(giftStandardDTO);
    }

    @Override
    public List<GiftStandardBO> listGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {
        return giftStandardSer.listGiftStandard(giftStandardDTO);
    }

    @Override
    public GiftStandardBO addGiftStandard(GiftStandardTO giftStandardTO) throws SerException {
        return giftStandardSer.addGiftStandard(giftStandardTO);
    }

    @Override
    public GiftStandardBO editGiftStandard(GiftStandardTO giftStandardTO) throws SerException {
        return giftStandardSer.editGiftStandard(giftStandardTO);
    }

    @Override
    public void deleteGiftStandard(String id) throws SerException {
        giftStandardSer.deleteGiftStandard(id);
    }

    @Override
    public List<String> getGiftByFestivalName() throws SerException {
        return giftStandardSer.getGiftByFestivalName();
    }
}