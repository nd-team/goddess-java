package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.HotAssistBO;
import com.bjike.goddess.assistance.dto.HotAssistDTO;
import com.bjike.goddess.assistance.service.HotAssistSer;
import com.bjike.goddess.assistance.to.HotAssistTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 高温补助业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:52 ]
 * @Description: [ 高温补助业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("hotAssistApiImpl")
public class HotAssistApiImpl implements HotAssistAPI {


    @Autowired
    private HotAssistSer hotAssistSer;

    @Override
    public Long countHotAssist(HotAssistDTO hotAssistDTO) throws SerException {
        return hotAssistSer.countHotAssist( hotAssistDTO);
    }

    @Override
    public List<HotAssistBO> listHotAssist(HotAssistDTO hotAssistDTO) throws SerException {
        return hotAssistSer.listHotAssist(hotAssistDTO);
    }

    @Override
    public HotAssistBO addHotAssist(HotAssistTO hotAssistTO) throws SerException {
        return hotAssistSer.addHotAssist(hotAssistTO);
    }

    @Override
    public HotAssistBO editHotAssist(HotAssistTO hotAssistTO) throws SerException {
        return hotAssistSer.editHotAssist(hotAssistTO);
    }

    @Override
    public void deleteHotAssist(String id) throws SerException {
        hotAssistSer.deleteHotAssist(id);
    }

    @Override
    public List<HotAssistBO> collectByArea(HotAssistDTO hotAssistDTO) throws SerException {
        return hotAssistSer.collectByArea( hotAssistDTO);
    }

    @Override
    public List<HotAssistBO> collectByProGroup(HotAssistDTO hotAssistDTO) throws SerException {
        return hotAssistSer.collectByProGroup(hotAssistDTO);
    }
    
}