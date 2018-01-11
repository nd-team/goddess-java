package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;
import com.bjike.goddess.businessinteraction.service.TalkDetailSer;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.SonPermissionObject;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资料信息业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("talkDetailApiImpl")
public class TalkDetailApiImpl implements TalkDetailAPI {
    @Autowired
    private TalkDetailSer talkDetailSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return talkDetailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return talkDetailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countInter(TalkDetailDTO talkDetailDTO) throws SerException {
        return talkDetailSer.countInter(talkDetailDTO);
    }

    @Override
    public TalkDetailBO getOneById(String id) throws SerException {
        return talkDetailSer.getOneById(id);
    }

    @Override
    public List<TalkDetailBO> listIntera(TalkDetailDTO talkDetailDTO) throws SerException {
        return talkDetailSer.listIntera(talkDetailDTO);
    }

    @Override
    public TalkDetailBO addIntera(TalkDetailTO talkDetailTO) throws SerException {
        return talkDetailSer.addIntera(talkDetailTO);
    }

    @Override
    public TalkDetailBO editIntera(TalkDetailTO talkDetailTO) throws SerException {
        return talkDetailSer.editIntera(talkDetailTO);
    }

    @Override
    public void deleteIntera(String id) throws SerException {
        talkDetailSer.deleteIntera(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return talkDetailSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return talkDetailSer.templateExport();
    }

    @Override
    public void importExcel(List<TalkDetailTO> talkDetailTOS) throws SerException {
        talkDetailSer.importExcel(talkDetailTOS);
    }

    @Override
    public List<String> findBussType() throws SerException {
        return talkDetailSer.findBussType();
    }
}