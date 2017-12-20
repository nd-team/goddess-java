package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.HeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.HeadersCustomDTO;
import com.bjike.goddess.projectprocing.service.HeadersCustomSer;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.HeadersCustomTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表头定制业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 表头定制业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("headersCustomApiImpl")
public class HeadersCustomApiImpl implements HeadersCustomAPI {
    @Autowired
    private HeadersCustomSer headersCustomSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return headersCustomSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return headersCustomSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countHeaders(HeadersCustomDTO headersCustomDTO) throws SerException {
        return headersCustomSer.countHeaders(headersCustomDTO);
    }

    @Override
    public HeadersCustomBO getOneById(String id) throws SerException {
        return headersCustomSer.getOneById(id);
    }

    @Override
    public List<HeadersCustomBO> listHeaders(HeadersCustomDTO headersCustomDTO) throws SerException {
        return headersCustomSer.listHeaders(headersCustomDTO);
    }

    @Override
    public HeadersCustomBO addHeaders(HeadersCustomTO headersCustomTO) throws SerException {
        return headersCustomSer.addHeaders(headersCustomTO);
    }

    @Override
    public HeadersCustomBO editHeaders(HeadersCustomTO headersCustomTO) throws SerException {
        return headersCustomSer.editHeaders(headersCustomTO);
    }

    @Override
    public void deleteHeaders(String id) throws SerException {
        headersCustomSer.deleteHeaders(id);
    }

    @Override
    public List<HeadersCustomBO> getHeaderByOutUnit(String outUnit) throws SerException {
        return headersCustomSer.getHeaderByOutUnit(outUnit);
    }

    @Override
    public List<HeadersCustomBO> getByManageId(String prossManageId) throws SerException {
        return headersCustomSer.getByManageId(prossManageId);
    }
}