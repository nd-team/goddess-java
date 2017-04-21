package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.DemandAnalysisBO;
import com.bjike.goddess.marketdevelopment.dto.DemandAnalysisDTO;
import com.bjike.goddess.marketdevelopment.service.DemandAnalysisSer;
import com.bjike.goddess.marketdevelopment.to.DemandAnalysisTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场需求分析业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:10 ]
 * @Description: [ 市场需求分析业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("demandAnalysisApiImpl")
public class DemandAnalysisApiImpl implements DemandAnalysisAPI {

    @Autowired
    private DemandAnalysisSer demandAnalysisSer;

    @Override
    public DemandAnalysisBO save(DemandAnalysisTO to) throws SerException {
        return demandAnalysisSer.save(to);
    }

    @Override
    public DemandAnalysisBO update(DemandAnalysisTO to) throws SerException {
        return demandAnalysisSer.update(to);
    }

    @Override
    public DemandAnalysisBO delete(DemandAnalysisTO to) throws SerException {
        return demandAnalysisSer.delete(to);
    }

    @Override
    public List<DemandAnalysisBO> findByType(String type) throws SerException {
        return demandAnalysisSer.findByType(type);
    }

    @Override
    public List<DemandAnalysisBO> findByCourse(String course) throws SerException {
        return demandAnalysisSer.findByType(course);
    }

    @Override
    public List<DemandAnalysisBO> findByCourseType(String type, String course) throws SerException {
        return demandAnalysisSer.findByCourseType(type, course);
    }

    @Override
    public DemandAnalysisBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(demandAnalysisSer.findById(id), DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysisBO> maps(DemandAnalysisDTO dto) throws SerException {
        return BeanTransform.copyProperties(demandAnalysisSer.findByPage(dto), DemandAnalysisBO.class);
    }
}