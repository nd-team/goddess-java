package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.ExamWaySetBO;
import com.bjike.goddess.balancecard.dto.ExamWaySetDTO;
import com.bjike.goddess.balancecard.service.ExamWaySetSer;
import com.bjike.goddess.balancecard.to.ExamWaySetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考核方式设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 考核方式设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("examWaySetApiImpl")
public class ExamWaySetApiImpl implements ExamWaySetAPI {


    @Autowired
    private ExamWaySetSer examWaySetSer;

    @Override
    public Long countExamWaySet(ExamWaySetDTO examWaySetDTO) throws SerException {
        return examWaySetSer.countExamWaySet( examWaySetDTO);
    }

    @Override
    public ExamWaySetBO getOneById(String id) throws SerException {
        return examWaySetSer.getOneById(id);
    }

    @Override
    public List<ExamWaySetBO> listExamWaySet(ExamWaySetDTO examWaySetDTO) throws SerException {
        return examWaySetSer.listExamWaySet(examWaySetDTO);
    }

    @Override
    public ExamWaySetBO addExamWaySet(ExamWaySetTO examWaySetTO) throws SerException {
        return examWaySetSer.addExamWaySet(examWaySetTO);
    }

    @Override
    public ExamWaySetBO editExamWaySet(ExamWaySetTO examWaySetTO) throws SerException {
        return examWaySetSer.editExamWaySet(examWaySetTO);
    }

    @Override
    public void deleteExamWaySet(String id) throws SerException {
        examWaySetSer.deleteExamWaySet(id);
    }


    @Override
    public List<String> listName( ) throws SerException {
        return examWaySetSer.listName();
    }



}