//package com.bjike.goddess.marketdevelopment.api;
//
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.utils.bean.BeanTransform;
//import com.bjike.goddess.marketdevelopment.bo.BusinessTypeBO;
//import com.bjike.goddess.marketdevelopment.dto.BusinessTypeDTO;
//import com.bjike.goddess.marketdevelopment.service.BusinessTypeSer;
//import com.bjike.goddess.marketdevelopment.to.BusinessTypeTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 业务类型业务接口实现
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:20 ]
// * @Description: [ 业务类型业务接口实现 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Service("businessTypeApiImpl")
//public class BusinessTypeApiImpl implements BusinessTypeAPI {
//
//    @Autowired
//    private BusinessTypeSer businessTypeSer;
//
//    @Override
//    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return businessTypeSer.guidePermission(guidePermissionTO);
//    }
//
//    @Override
//    public BusinessTypeBO save(BusinessTypeTO to) throws SerException {
//        return businessTypeSer.save(to);
//    }
//
//    @Override
//    public BusinessTypeBO update(BusinessTypeTO to) throws SerException {
//        return businessTypeSer.update(to);
//    }
//
//    @Override
//    public BusinessTypeBO congeal(BusinessTypeTO to) throws SerException {
//        return businessTypeSer.congeal(to);
//    }
//
//    @Override
//    public BusinessTypeBO thaw(BusinessTypeTO to) throws SerException {
//        return businessTypeSer.thaw(to);
//    }
//
//    @Override
//    public BusinessTypeBO delete(BusinessTypeTO to) throws SerException {
//        return businessTypeSer.delete(to);
//    }
//
//    @Override
//    public List<BusinessTypeBO> findThaw() throws SerException {
//        return businessTypeSer.findThaw();
//    }
//
//    @Override
//    public BusinessTypeBO getById(String id) throws SerException {
//        return BeanTransform.copyProperties(businessTypeSer.findById(id), BusinessTypeBO.class);
//    }
//
//    @Override
//    public List<BusinessTypeBO> maps(BusinessTypeDTO dto) throws SerException {
//        return BeanTransform.copyProperties(businessTypeSer.findByPage(dto), BusinessTypeBO.class);
//    }
//
//    @Override
//    public Integer getTotal() throws SerException {
//        return businessTypeSer.findAll().size();
//    }
//
//    @Override
//    public List<String> findDirection() throws SerException {
//        return businessTypeSer.findDirection();
//    }
//}