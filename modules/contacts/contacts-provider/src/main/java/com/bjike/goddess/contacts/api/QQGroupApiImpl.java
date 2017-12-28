package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.QQGroupBO;
import com.bjike.goddess.contacts.dto.QQGroupDTO;
import com.bjike.goddess.contacts.service.QQGroupSer;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.QQGroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QQ群管理业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("qQGroupApiImpl")
public class QQGroupApiImpl implements QQGroupAPI {

    @Autowired
    private QQGroupSer qqGroupSer;

    @Override
    public QQGroupBO save(QQGroupTO to) throws SerException {
        return qqGroupSer.save(to);
    }

    @Override
    public QQGroupBO update(QQGroupTO to) throws SerException {
        return qqGroupSer.update(to);
    }

    @Override
    public QQGroupBO delete(QQGroupTO to) throws SerException {
        return qqGroupSer.delete(to);
    }

    @Override
    public QQGroupBO close(QQGroupTO to) throws SerException {
        return qqGroupSer.close(to);
    }

    @Override
    public List<QQGroupBO> maps(QQGroupDTO dto) throws SerException {
        return qqGroupSer.maps(dto);
    }

    @Override
    public QQGroupBO getById(String id) throws SerException {
        return qqGroupSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return qqGroupSer.getTotal();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return qqGroupSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return qqGroupSer.guidePermission(guidePermissionTO);
    }

    @Override
    public QQGroupBO importExcel(List<QQGroupTO> qqGroupTO) throws SerException {
        return qqGroupSer.importExcel(qqGroupTO);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return qqGroupSer.templateExport();
    }
}