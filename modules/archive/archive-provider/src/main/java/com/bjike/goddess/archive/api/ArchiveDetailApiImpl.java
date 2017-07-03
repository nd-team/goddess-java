package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ArchiveDetailBO;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.service.ArchiveDetailSer;
import com.bjike.goddess.archive.to.ArchiveDetailTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 档案明细业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("archiveDetailApiImpl")
public class ArchiveDetailApiImpl implements ArchiveDetailAPI {

    @Autowired
    private ArchiveDetailSer archiveDetailSer;

    @Override
    public ArchiveDetailBO save(ArchiveDetailTO to) throws SerException {
        return archiveDetailSer.save(to);
    }

    @Override
    public ArchiveDetailBO update(ArchiveDetailTO to) throws SerException {
        return archiveDetailSer.update(to);
    }

    @Override
    public ArchiveDetailBO delete(String id) throws SerException {
        return archiveDetailSer.delete(id);
    }

    @Override
    public ArchiveDetailBO findByUsername(String username) throws SerException {
        return archiveDetailSer.findByUsername(username);
    }

    @Override
    public List<ArchiveDetailBO> maps(ArchiveDetailDTO dto) throws SerException {
        return archiveDetailSer.maps(dto);
    }

    @Override
    public ArchiveDetailBO getById(String id) throws SerException {
        return archiveDetailSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return archiveDetailSer.getTotal();
    }
}