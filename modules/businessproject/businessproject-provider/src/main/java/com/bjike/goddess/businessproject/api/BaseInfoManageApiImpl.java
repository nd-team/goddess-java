package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.dto.BaseInfoManageDTO;
import com.bjike.goddess.businessproject.service.BaseInfoManageSer;
import com.bjike.goddess.businessproject.to.BaseInfoManageTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务项目合同基本信息管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:34:51.353 ]
 * @Description: [ 商务项目合同基本信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("baseInfoManageApiImpl")
public class BaseInfoManageApiImpl implements BaseInfoManageAPI {

    @Autowired
    private BaseInfoManageSer baseInfoManageSer;

    @Override
    public List<BaseInfoManageBO> listBaseInfoManage(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        return baseInfoManageSer.listBaseInfoManage(baseInfoManageDTO);
    }

    @Override
    public BaseInfoManageBO addBaseInfoManage(BaseInfoManageTO baseInfoManageTO) throws SerException {
        return baseInfoManageSer.addBaseInfoManage(baseInfoManageTO);
    }

    @Override
    public BaseInfoManageBO editBaseInfoManage(BaseInfoManageTO baseInfoManageTO) throws SerException {
        return baseInfoManageSer.editBaseInfoManage(baseInfoManageTO);
    }

    @Override
    public void deleteBaseInfoManage(String id) throws SerException {
        baseInfoManageSer.deleteBaseInfoManage(id);
    }

    @Override
    public BaseInfoManageBO getInfoByInnerProjectNum(String innerProjectNum) throws SerException {
        return baseInfoManageSer.getInfoByInnerProjectNum( innerProjectNum);
    }

    @Override
    public List<BaseInfoManageBO> searchSiginManage(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        return baseInfoManageSer.searchSiginManage(baseInfoManageDTO);
    }

    @Override
    public List<String> listFirstCompany() throws SerException {
        return null;
    }
}