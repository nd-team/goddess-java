package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.businessproject.dto.SiginManageDTO;
import com.bjike.goddess.businessproject.service.SiginManageSer;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * 商务项目合同签订与立项管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.301 ]
 * @Description: [ 商务项目合同签订与立项管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("siginManageApiImpl")
public class SiginManageApiImpl implements SiginManageAPI {

    @Autowired
    private SiginManageSer siginManageSer;

    @Override
    public Long countSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        return siginManageSer.countSiginManage(siginManageDTO);
    }

    @Override
    public SiginManageBO getOneById(String id) throws SerException {
        return siginManageSer.getOneById(id);
    }

    @Override
    public List<SiginManageBO> listSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        return siginManageSer.listSiginManage(siginManageDTO);
    }

    @Override
    public SiginManageBO addSiginManage(SiginManageTO siginManageTO) throws SerException {
        return siginManageSer.addSiginManage(siginManageTO);
    }

    @Override
    public SiginManageBO editSiginManage(SiginManageTO siginManageTO) throws SerException {
        return siginManageSer.editSiginManage(siginManageTO);
    }

    @Override
    public void deleteSiginManage(String id) throws SerException {
        siginManageSer.deleteSiginManage(id);
    }

    @Override
    public SiginManageBO auditSiginManage(SiginManageTO siginManageTO) throws SerException {
        return siginManageSer.auditSiginManage( siginManageTO);
    }

    @Override
    public List<SiginManageBO> searchSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        return siginManageSer.searchSiginManage(siginManageDTO);
    }

    @Override
    public List<String> listArea() throws SerException {
        return siginManageSer.listArea();
    }

    @Override
    public SiginManageBO importExcel(List<SiginManageTO> siginManageTO) throws SerException {
        return siginManageSer.importExcel( siginManageTO );
    }

    @Override
    public byte[] exportExcel(SiginManageDTO dto) throws SerException {
        return siginManageSer.exportExcel( dto );
    }
}