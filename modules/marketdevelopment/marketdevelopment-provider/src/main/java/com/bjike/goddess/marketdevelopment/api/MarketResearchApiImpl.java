package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.MarketResearchBO;
import com.bjike.goddess.marketdevelopment.dto.MarketResearchDTO;
import com.bjike.goddess.marketdevelopment.service.MarketResearchSer;
import com.bjike.goddess.marketdevelopment.to.MarketResearchTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场调研业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:16 ]
 * @Description: [ 市场调研业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketResearchApiImpl")
public class MarketResearchApiImpl implements MarketResearchAPI {

    @Autowired
    private MarketResearchSer marketResearchSer;

    @Override
    public MarketResearchBO save(MarketResearchTO to) throws SerException {
        return marketResearchSer.save(to);
    }

    @Override
    public MarketResearchBO update(MarketResearchTO to) throws SerException {
        return marketResearchSer.update(to);
    }

    @Override
    public MarketResearchBO delete(MarketResearchTO to) throws SerException {
        return marketResearchSer.delete(to);
    }

    @Override
    public List<MarketResearchBO> findByType(String type) throws SerException {
        return marketResearchSer.findByType(type);
    }

    @Override
    public List<MarketResearchBO> findByCourse(String course) throws SerException {
        return marketResearchSer.findByCourse(course);
    }

    @Override
    public List<MarketResearchBO> findByCourseType(String type, String course) throws SerException {
        return marketResearchSer.findByCourseType(type, course);
    }

    @Override
    public MarketResearchBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(marketResearchSer.findById(id), MarketResearchBO.class);
    }

    @Override
    public List<MarketResearchBO> maps(MarketResearchDTO dto) throws SerException {
        return BeanTransform.copyProperties(marketResearchSer.findByPage(dto), MarketResearchBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return marketResearchSer.findAll().size();
    }

}