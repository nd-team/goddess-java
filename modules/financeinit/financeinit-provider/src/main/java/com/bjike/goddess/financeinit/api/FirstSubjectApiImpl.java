package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.entity.FirstSubject;
import com.bjike.goddess.financeinit.service.FirstSubjectSer;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;
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
}