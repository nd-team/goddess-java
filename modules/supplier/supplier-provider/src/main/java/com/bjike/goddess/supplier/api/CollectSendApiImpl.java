package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.CollectSendBO;
import com.bjike.goddess.supplier.dto.CollectSendDTO;
import com.bjike.goddess.supplier.service.CollectSendSer;
import com.bjike.goddess.supplier.to.CollectSendTO;
import com.bjike.goddess.supplier.to.CollectTo;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商汇总业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-14 11:48 ]
 * @Description: [ 供应商汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("collectSendApiImpl")
public class CollectSendApiImpl implements CollectSendAPI {

    @Autowired
    private CollectSendSer collectSendSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return collectSendSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return collectSendSer.guidePermission(guidePermissionTO);
    }

    @Override
    public CollectSendBO save(CollectSendTO to) throws SerException {
        return collectSendSer.save(to);
    }

    @Override
    public CollectSendBO update(CollectSendTO to) throws SerException {
        return collectSendSer.update(to);
    }

    @Override
    public CollectSendBO delete(String id) throws SerException {
        return collectSendSer.delete(id);
    }

    @Override
    public CollectSendBO congeal(String id) throws SerException {
        return collectSendSer.congeal(id);
    }

    @Override
    public CollectSendBO thaw(String id) throws SerException {
        return collectSendSer.thaw(id);
    }

    @Override
    public CollectSendBO getById(String id) throws SerException {
        return collectSendSer.getById(id);
    }

    @Override
    public List<CollectSendBO> maps(CollectSendDTO dto) throws SerException {
        return collectSendSer.maps(dto);
    }

    @Override
    public List<CollectSendBO> findThaw() throws SerException {
        return collectSendSer.findThaw();
    }

    @Override
    public Long getTotal(CollectSendDTO dto) throws SerException {
        return collectSendSer.getTotal(dto);
    }

    @Override
    public String collect(CollectTo to) throws SerException {
        return collectSendSer.collect(to);
    }

    @Override
    public void sendEmail() throws SerException {
        collectSendSer.sendEmail();
    }
}