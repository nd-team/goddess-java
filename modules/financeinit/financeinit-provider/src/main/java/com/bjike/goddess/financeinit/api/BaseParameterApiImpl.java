package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.financeinit.service.BaseParameterSer;
import com.bjike.goddess.financeinit.to.BaseParameterTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基本参数业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("baseParameterApiImpl")
public class BaseParameterApiImpl implements BaseParameterAPI {
    @Autowired
    private BaseParameterSer baseParameterSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return baseParameterSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return baseParameterSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        return baseParameterSer.countBasicPara(baseParameterDTO);
    }

    @Override
    public BaseParameterBO getOneById(String id) throws SerException {
        return baseParameterSer.getOneById(id);
    }

    @Override
    public List<BaseParameterBO> listBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        return baseParameterSer.listBasicPara(baseParameterDTO);
    }

    @Override
    public BaseParameterBO addBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        return baseParameterSer.addBasicPara(baseParameterTO);
    }

    @Override
    public BaseParameterBO editBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        return baseParameterSer.editBasicPara(baseParameterTO);
    }

    @Override
    public void deleteBasicPara(String id) throws SerException {
        baseParameterSer.deleteBasicPara(id);
    }

    @Override
    public String findDoudap() throws SerException {
        return baseParameterSer.findDoudap();
    }
}