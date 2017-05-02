package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BasicInfoBO;
import com.bjike.goddess.buyticket.dto.BasicInfoDTO;
import com.bjike.goddess.buyticket.entity.BasicInfo;
import com.bjike.goddess.buyticket.service.BasicInfoSer;
import com.bjike.goddess.buyticket.to.BasicInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 基本信息设置业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:14 ]
 * @Description: [ 基本信息设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("basicInfoApiImpl")
public class BasicInfoApiImpl implements BasicInfoAPI {
    @Autowired
    private BasicInfoSer basicInfoSer;
    @Override
    public Long countBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        return basicInfoSer.countBasicInfo(basicInfoDTO);
    }
    @Override
    public BasicInfoBO getOne(String id) throws SerException {
        return basicInfoSer.getOne(id);
    }

    @Override
    public List<BasicInfoBO> findListBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        return basicInfoSer.findListBasicInfo(basicInfoDTO);
    }

    @Override
    public BasicInfoBO insertBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        return basicInfoSer.insertBasicInfo(basicInfoTO);
    }

    @Override
    public BasicInfoBO editBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        return basicInfoSer.editBasicInfo(basicInfoTO);
    }

    @Override
    public void removeBasicInfo(String id) throws SerException {
        basicInfoSer.removeBasicInfo(id);
    }

}