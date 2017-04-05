package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.ProblemDisposeBO;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.service.ProblemDisposeSer;
import com.bjike.goddess.businessevaluate.to.ProblemDisposeTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目问题受理和处理业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problemDisposeApiImpl")
public class ProblemDisposeApiImpl implements ProblemDisposeAPI {

    @Autowired
    private ProblemDisposeSer problemDisposeSer;

    @Override
    public ProblemDisposeBO addModel(ProblemDisposeTO to) throws SerException {
        return problemDisposeSer.insertModel(to);
    }

    @Override
    public ProblemDisposeBO editModel(ProblemDisposeTO to) throws SerException {
        return problemDisposeSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        problemDisposeSer.remove(id);
    }

    @Override
    public List<ProblemDisposeBO> pageList(ProblemDisposeDTO dto) throws SerException {
        return problemDisposeSer.pageList(dto);
    }
}