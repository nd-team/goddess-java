package com.bjike.goddess.commerce.api;

import com.bjike.goddess.commerce.bo.CommerceConferenceBO;
import com.bjike.goddess.commerce.dto.CommerceConferenceDTO;
import com.bjike.goddess.commerce.service.CommerceConferenceSer;
import com.bjike.goddess.commerce.to.CollectTO;
import com.bjike.goddess.commerce.to.CommerceConferenceExcelTO;
import com.bjike.goddess.commerce.to.CommerceConferenceTO;
import com.bjike.goddess.commerce.to.GuidePermissionTO;
import com.bjike.goddess.commerce.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务会议业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("commerceConferenceApiImpl")
public class CommerceConferenceApiImpl implements CommerceConferenceAPI {

    @Autowired
    private CommerceConferenceSer commerceConferenceSer;

    @Override
    public CommerceConferenceBO save(CommerceConferenceTO to) throws SerException {
        return commerceConferenceSer.save(to);
    }

    @Override
    public CommerceConferenceBO update(CommerceConferenceTO to) throws SerException {
        return commerceConferenceSer.update(to);
    }

    @Override
    public CommerceConferenceBO congeal(String id) throws SerException {
        return commerceConferenceSer.congeal(id);
    }

    @Override
    public CommerceConferenceBO getById(String id) throws SerException {
        return commerceConferenceSer.getById(id);
    }

    @Override
    public List<CommerceConferenceBO> maps(CommerceConferenceDTO dto) throws SerException {
        return commerceConferenceSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return commerceConferenceSer.getTotal();
    }

    @Override
    public void upload(List<CommerceConferenceExcelTO> list) throws SerException {
        commerceConferenceSer.upload(list);
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
        return commerceConferenceSer.exportExcel(to);
    }

    @Override
    public List<CommerceConferenceBO> findByTime(CollectTO to) throws SerException {
        return commerceConferenceSer.findByTime(to);
    }

    @Override
    public CommerceConferenceBO findByNumber(String serialNumber) throws SerException {
        return commerceConferenceSer.findByNumber(serialNumber);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return commerceConferenceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return commerceConferenceSer.guidePermission( guidePermissionTO );
    }

    @Override
    public CommerceConferenceBO importExcel(List<CommerceConferenceTO> tocs) throws SerException {
        return commerceConferenceSer.importExcel( tocs );
    }

    @Override
    public byte[] templateExport( ) throws SerException {
        return commerceConferenceSer.templateExport(   );
    }
}