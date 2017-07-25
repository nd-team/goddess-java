package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.EnterpriseCultureInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PeriodicalProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.bo.PublicizeProgramInfoBO;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.service.EnterpriseCultureInfoSer;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoEditTO;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业文化信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("enterpriseCultureInfoApiImpl")
public class EnterpriseCultureInfoApiImpl implements EnterpriseCultureInfoAPI {

    @Autowired
    private EnterpriseCultureInfoSer enterpriseCultureInfoSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return enterpriseCultureInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return enterpriseCultureInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public EnterpriseCultureInfoBO addModel(EnterpriseCultureInfoTO to) throws SerException {
        return enterpriseCultureInfoSer.insertModel(to);
    }

    @Override
    public EnterpriseCultureInfoBO editModel(EnterpriseCultureInfoEditTO to) throws SerException {
        return enterpriseCultureInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        enterpriseCultureInfoSer.remove(id);
    }

    @Override
    public List<EnterpriseCultureInfoBO> pageList(EnterpriseCultureInfoDTO dto) throws SerException {
        return enterpriseCultureInfoSer.pageList(dto);
    }

    @Override
    public PublicizeProgramInfoBO findPublicize(String id) throws SerException {
        return enterpriseCultureInfoSer.findPublicize(id);
    }

    @Override
    public PeriodicalProgramInfoBO findPeriodical(String id) throws SerException {
        return enterpriseCultureInfoSer.findPeriodical(id);
    }

    @Override
    public EnterpriseCultureInfoBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(enterpriseCultureInfoSer.findById(id), EnterpriseCultureInfoBO.class);
    }

    @Override
    public Long count(EnterpriseCultureInfoDTO dto) throws SerException {
        return enterpriseCultureInfoSer.count(dto);
    }

    @Override
    public List<EnterpriseCultureInfoBO> infos() throws SerException {
        return enterpriseCultureInfoSer.findThawAll();
    }
}