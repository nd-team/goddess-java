package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.CollectionPeriodBO;
import com.bjike.goddess.projectroyalty.dto.CollectionPeriodDTO;
import com.bjike.goddess.projectroyalty.excel.SonPermissionObject;
import com.bjike.goddess.projectroyalty.service.CollectionPeriodSer;
import com.bjike.goddess.projectroyalty.to.CollectionPeriodTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回款周期业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:55 ]
 * @Description: [ 回款周期业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("collectionPeriodApiImpl")
public class CollectionPeriodApiImpl implements CollectionPeriodAPI {

    @Autowired
    private CollectionPeriodSer collectionPeriodSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return collectionPeriodSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return collectionPeriodSer.guidePermission( guidePermissionTO );
    }

    @Override
    public CollectionPeriodBO save(CollectionPeriodTO to) throws SerException {
        return collectionPeriodSer.save(to);
    }

    @Override
    public CollectionPeriodBO update(CollectionPeriodTO to) throws SerException {
        return collectionPeriodSer.update(to);
    }

    @Override
    public CollectionPeriodBO delete(String id) throws SerException {
        return collectionPeriodSer.delete(id);
    }

    @Override
    public CollectionPeriodBO getById(String id) throws SerException {
        return collectionPeriodSer.getById(id);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        return collectionPeriodSer.findOpinion();
    }

    @Override
    public List<CollectionPeriodBO> maps(CollectionPeriodDTO dto) throws SerException {
        return collectionPeriodSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return collectionPeriodSer.getTotal();
    }
}