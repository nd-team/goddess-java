package com.bjike.goddess.datastore.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.entity.NumSpecification;
import com.bjike.goddess.datastore.service.NumSpecificationSer;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
import com.bjike.goddess.datastore.to.NumSpecificationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据存储编号规范业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:35 ]
 * @Description: [ 数据存储编号规范业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("numSpecificationApiImpl")
public class NumSpecificationApiImpl implements NumSpecificationAPI {
    @Autowired
    private NumSpecificationSer numSpecificationSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return numSpecificationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return numSpecificationSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        return numSpecificationSer.countNumSpecification(numSpecificationDTO);
    }
    @Override
    public NumSpecificationBO getOne(String id) throws SerException {
        return numSpecificationSer.getOne(id);
    }

    @Override
    public List<NumSpecificationBO> findListNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        return numSpecificationSer.findListNumSpecification(numSpecificationDTO);
    }

    @Override
    public NumSpecificationBO insertNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        return numSpecificationSer.insertNumSpecification(numSpecificationTO);
    }

    @Override
    public NumSpecificationBO editNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        return numSpecificationSer.editNumSpecification(numSpecificationTO);
    }

    @Override
    public void removeNumSpecification(String id) throws SerException {
        numSpecificationSer.removeNumSpecification(id);
    }

}