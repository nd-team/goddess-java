package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.excel.ArchiveAccessImportExcel;
import com.bjike.goddess.archive.excel.SonPermissionObject;
import com.bjike.goddess.archive.service.ArchiveAccessSer;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 档案调阅业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("archiveAccessApiImpl")
public class ArchiveAccessApiImpl implements ArchiveAccessAPI {

    @Autowired
    private ArchiveAccessSer archiveAccessSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return archiveAccessSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return archiveAccessSer.guidePermission( guidePermissionTO );
    }

    @Override
    public byte[] exportExcel(ArchiveAccessDTO dto) throws SerException {
        return archiveAccessSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return archiveAccessSer.templateExcel();
    }

    @Override
    public void upload(List<ArchiveAccessTO> tos) throws SerException {
        archiveAccessSer.upload(tos);
    }

    @Override
    public List<String> findUserName() throws SerException {
        return archiveAccessSer.findUserName();
    }

    @Override
    public ArchiveAccessBO save(ArchiveAccessTO to) throws SerException {
        return archiveAccessSer.save(to);
    }

    @Override
    public ArchiveAccessBO update(ArchiveAccessTO to) throws SerException {
        return archiveAccessSer.update(to);
    }

    @Override
    public ArchiveAccessBO delete(String id) throws SerException {
        return archiveAccessSer.delete(id);
    }

    @Override
    public ArchiveAccessBO audit(AccessAuditTO to) throws SerException {
        return archiveAccessSer.audit(to);
    }

    @Override
    public List<ArchiveAccessBO> maps(ArchiveAccessDTO dto) throws SerException {
        return archiveAccessSer.maps(dto);
    }

    @Override
    public ArchiveAccessBO getById(String id) throws SerException {
        return archiveAccessSer.getById(id);
    }

    @Override
    public Long getTotal(ArchiveAccessDTO dto) throws SerException {
        return archiveAccessSer.getTotal(dto);
    }
}