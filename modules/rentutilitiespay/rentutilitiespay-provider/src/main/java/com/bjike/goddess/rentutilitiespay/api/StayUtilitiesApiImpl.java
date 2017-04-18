package com.bjike.goddess.rentutilitiespay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.service.StayUtilitiesSer;
import com.bjike.goddess.rentutilitiespay.to.StayUtilitiesTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工住宿水电费业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("stayUtilitiesApiImpl")
public class StayUtilitiesApiImpl implements StayUtilitiesAPI {
    @Autowired
    private StayUtilitiesSer stayUtilitiesSer;
    @Override
    public List<StayUtilitiesBO> findListStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        return stayUtilitiesSer.findListStayUtilities(stayUtilitiesDTO);
    }

    @Override
    public StayUtilitiesBO insertStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        return stayUtilitiesSer.insertStayUtilities(stayUtilitiesTO);
    }

    @Override
    public StayUtilitiesBO editStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        return stayUtilitiesSer.editStayUtilities(stayUtilitiesTO);
    }
    @Override
    public void removeStayUtilities(String id) throws SerException {
        stayUtilitiesSer.remove(id);

    }

    @Override
    public List<StayUtilitiesBO> collectName(String[] name) throws SerException {
        return stayUtilitiesSer.collectName(name);
    }


}