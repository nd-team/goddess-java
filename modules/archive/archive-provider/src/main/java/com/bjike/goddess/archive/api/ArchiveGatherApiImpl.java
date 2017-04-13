package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ArchiveGatherBO;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.service.ArchiveGatherSer;
import com.bjike.goddess.archive.to.ArchiveGatherTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 档案收集业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:22 ]
 * @Description: [ 档案收集业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("archiveGatherApiImpl")
public class ArchiveGatherApiImpl implements ArchiveGatherAPI {

    @Autowired
    private ArchiveGatherSer archiveGatherSer;

    @Override
    public ArchiveGatherBO save(ArchiveGatherTO to) throws SerException {
        return archiveGatherSer.save(to);
    }

    @Override
    public ArchiveGatherBO update(ArchiveGatherTO to) throws SerException {
        return archiveGatherSer.update(to);
    }

    @Override
    public ArchiveGatherBO delete(String id) throws SerException {
        return archiveGatherSer.delete(id);
    }

    @Override
    public List<ArchiveGatherBO> maps(ArchiveGatherDTO dto) throws SerException {
        return archiveGatherSer.maps(dto);
    }
}