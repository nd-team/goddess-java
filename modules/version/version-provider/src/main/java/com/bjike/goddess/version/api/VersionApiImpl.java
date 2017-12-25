package com.bjike.goddess.version.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.version.bo.VersionBO;
import com.bjike.goddess.version.dto.VersionDTO;
import com.bjike.goddess.version.entity.Version;
import com.bjike.goddess.version.service.VersionSer;
import com.bjike.goddess.version.to.HelpTO;
import com.bjike.goddess.version.to.VersionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版本信息业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:03 ]
 * @Description: [ 版本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("versionApiImpl")
public class VersionApiImpl implements VersionAPI {
    @Autowired
    private VersionSer versionSer;

    @Override
    public List<VersionBO> list(VersionDTO dto) throws SerException {
        return versionSer.list(dto);
    }

    @Override
    public VersionBO save(VersionTO to) throws SerException {
        return versionSer.save(to);
    }

    @Override
    public void edit(VersionTO to) throws SerException {
        versionSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        versionSer.delete(id);
    }

    @Override
    public VersionBO findByID(String id) throws SerException {
        return versionSer.findByID(id);
    }

    @Override
    public Long count(VersionDTO dto) throws SerException {
        return versionSer.count(dto);
    }

    @Override
    public void ask(String id, HelpTO helpTO) throws SerException {
        versionSer.ask(id,helpTO);
    }

    @Override
    public VersionBO findDetail(String id) throws SerException {
        return versionSer.findDetail(id);
    }
}