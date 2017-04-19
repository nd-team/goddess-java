package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.service.AgeAssistSer;
import com.bjike.goddess.assistance.to.AgeAssistTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工龄补助业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("ageAssistApiImpl")
public class AgeAssistApiImpl implements AgeAssistAPI {



    @Autowired
    private AgeAssistSer ageAssistSer;

    @Override
    public Long countAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        return ageAssistSer.count( ageAssistDTO);
    }

    @Override
    public List<AgeAssistBO> listAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        return ageAssistSer.listAgeAssist(ageAssistDTO);
    }

    @Override
    public AgeAssistBO addAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        return ageAssistSer.addAgeAssist(ageAssistTO);
    }

    @Override
    public AgeAssistBO editAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        return ageAssistSer.editAgeAssist(ageAssistTO);
    }

    @Override
    public void deleteAgeAssist(String id) throws SerException {
        ageAssistSer.deleteAgeAssist(id);
    }
    
}