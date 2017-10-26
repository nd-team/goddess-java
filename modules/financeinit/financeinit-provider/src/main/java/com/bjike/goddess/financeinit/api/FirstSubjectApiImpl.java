package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.service.FirstSubjectSer;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 一级科目业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("firstSubjectApiImpl")
public class FirstSubjectApiImpl implements FirstSubjectAPI {

    @Autowired
    private FirstSubjectSer firstSubjectSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return firstSubjectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return firstSubjectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {
        return firstSubjectSer.countFirstSubject( firstSubjectDTO );
    }

    @Override
    public FirstSubjectBO getOneById(String id) throws SerException {
        return firstSubjectSer.getOneById(id);
    }
    
    @Override
    public List<FirstSubjectBO> listFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {
        return firstSubjectSer.listFirstSubject(firstSubjectDTO);
    }

    @Override
    public FirstSubjectBO addFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        return firstSubjectSer.addFirstSubject(firstSubjectTO);
    }

    @Override
    public FirstSubjectBO editFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        return firstSubjectSer.editFirstSubject(firstSubjectTO);
    }

    @Override
    public void deleteFirstSubject(String id) throws SerException {
        firstSubjectSer.deleteFirstSubject(id);
    }

    @Override
    public FirstSubjectBO getFirstSubject(String firstSubjectName) throws SerException {
        return firstSubjectSer.getFirstSubject(firstSubjectName);
    }

    @Override
    public List<String> listAllFirst() throws SerException {
        return firstSubjectSer.listAllFirst();
    }

    @Override
    public FirstSubjectBO importExcel(List<FirstSubjectTO> firstSubjectTO) throws SerException {
        return firstSubjectSer.importExcel( firstSubjectTO);
    }

    @Override
    public byte[] exportExcel(FirstSubjectDTO dto) throws SerException {
        return firstSubjectSer.exportExcel( dto );
    }

    @Override
    public byte[] templateExport() throws SerException {
        return firstSubjectSer.templateExport();
    }

    @Override
    public List<String> listAllFirstAndCode() throws SerException {
        return firstSubjectSer.listAllFirstAndCode();
    }


}