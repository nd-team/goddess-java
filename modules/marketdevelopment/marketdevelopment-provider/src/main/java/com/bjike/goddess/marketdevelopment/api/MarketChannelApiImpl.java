//package com.bjike.goddess.marketdevelopment.api;
//
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.utils.bean.BeanTransform;
//import com.bjike.goddess.marketdevelopment.bo.MarketChannelBO;
//import com.bjike.goddess.marketdevelopment.dto.MarketChannelDTO;
//import com.bjike.goddess.marketdevelopment.service.MarketChannelSer;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.MarketChannelTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 市场挖掘业务接口实现
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:15 ]
// * @Description: [ 市场挖掘业务接口实现 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Service("marketChannelApiImpl")
//public class MarketChannelApiImpl implements MarketChannelAPI {
//
//    @Autowired
//    private MarketChannelSer marketChannelSer;
//
//    @Override
//    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return marketChannelSer.guidePermission(guidePermissionTO);
//    }
//
//    @Override
//    public MarketChannelBO save(MarketChannelTO to) throws SerException {
//        return marketChannelSer.save(to);
//    }
//
//    @Override
//    public MarketChannelBO update(MarketChannelTO to) throws SerException {
//        return marketChannelSer.update(to);
//    }
//
//    @Override
//    public MarketChannelBO delete(MarketChannelTO to) throws SerException {
//        return marketChannelSer.delete(to);
//    }
//
//    @Override
//    public List<MarketChannelBO> findByType(String type) throws SerException {
//        return marketChannelSer.findByType(type);
//    }
//
//    @Override
//    public List<MarketChannelBO> findByCourse(String course) throws SerException {
//        return marketChannelSer.findByCourse(course);
//    }
//
//    @Override
//    public List<MarketChannelBO> findByCourseType(String type, String course) throws SerException {
//        return marketChannelSer.findByCourseType(type, course);
//    }
//
//    @Override
//    public MarketChannelBO getById(String id) throws SerException {
//        return BeanTransform.copyProperties(marketChannelSer.findById(id), MarketChannelBO.class);
//    }
//
//    @Override
//    public List<MarketChannelBO> maps(MarketChannelDTO dto) throws SerException {
//        return BeanTransform.copyProperties(marketChannelSer.findByPage(dto), MarketChannelBO.class);
//    }
//
//    @Override
//    public Integer getTotal() throws SerException {
//        return marketChannelSer.findAll().size();
//    }
//
//    @Override
//    public byte[] exportExcel(CollectTO to) throws SerException {
//        return marketChannelSer.exportExcel(to);
//    }
//}