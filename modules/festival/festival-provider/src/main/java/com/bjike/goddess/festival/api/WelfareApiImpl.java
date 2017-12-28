package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.WelfareBO;
import com.bjike.goddess.festival.dto.WelfareDTO;
import com.bjike.goddess.festival.service.WelfareSer;
import com.bjike.goddess.festival.to.WelfareTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节假日礼品福利业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:17 ]
 * @Description: [ 节假日礼品福利业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("welfareApiImpl")
public class WelfareApiImpl implements WelfareAPI {

    @Autowired
    private WelfareSer welfareSer;

    @Override
    public Long countWelfare(WelfareDTO welfareDTO) throws SerException {
        return welfareSer.countWelfare(welfareDTO);
    }

    @Override
    public List<WelfareBO> listWelfare(WelfareDTO welfareDTO) throws SerException {
        return welfareSer.listWelfare(welfareDTO);
    }

    @Override
    public WelfareBO addWelfare(WelfareTO welfareTO) throws SerException {
        return welfareSer.addWelfare(welfareTO);
    }

    @Override
    public WelfareBO editWelfare(WelfareTO welfareTO) throws SerException {
        return welfareSer.editWelfare(welfareTO);
    }

    @Override
    public void deleteWelfare(String id) throws SerException {
        welfareSer.deleteWelfare(id);
    }

    @Override
    public List<WelfareBO> getWelfare(String holidayProgrammeId) throws SerException {
        return welfareSer.getWelfare(holidayProgrammeId);
    }
}