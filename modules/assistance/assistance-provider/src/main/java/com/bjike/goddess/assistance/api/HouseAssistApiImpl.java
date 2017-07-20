package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.assistance.dto.HouseAssistDTO;
import com.bjike.goddess.assistance.service.HouseAssistSer;
import com.bjike.goddess.assistance.to.HouseAssistTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 住宿补助业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 11:27 ]
 * @Description: [ 住宿补助业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("houseAssistApiImpl")
public class HouseAssistApiImpl implements HouseAssistAPI {



    @Autowired
    private HouseAssistSer houseAssistSer;

    @Override
    public Long countHouseAssist(HouseAssistDTO houseAssistDTO) throws SerException {
        return houseAssistSer.countHouseAssist( houseAssistDTO);
    }

    @Override
    public HouseAssistBO getOneById(String id) throws SerException {
        return houseAssistSer.getOneById(id);
    }

    @Override
    public List<HouseAssistBO> listHouseAssist(HouseAssistDTO houseAssistDTO) throws SerException {
        return houseAssistSer.listHouseAssist(houseAssistDTO);
    }

    @Override
    public HouseAssistBO addHouseAssist(HouseAssistTO houseAssistTO) throws SerException {
        return houseAssistSer.addHouseAssist(houseAssistTO);
    }

    @Override
    public HouseAssistBO editHouseAssist(HouseAssistTO houseAssistTO) throws SerException {
        return houseAssistSer.editHouseAssist(houseAssistTO);
    }

    @Override
    public void deleteHouseAssist(String id) throws SerException {
        houseAssistSer.deleteHouseAssist(id);
    }

}