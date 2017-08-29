package com.bjike.goddess.royalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.royalty.bo.SystemBetABO;
import com.bjike.goddess.royalty.dto.SystemBetADTO;
import com.bjike.goddess.royalty.dto.SystemBetDDTO;
import com.bjike.goddess.royalty.service.SystemBetSer;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.SystemBetATO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 体系间对赌表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("systemBetApiImpl")
public class SystemBetApiImpl implements SystemBetAPI {
    @Autowired
    private SystemBetSer systemBetSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return systemBetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return systemBetSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(SystemBetDDTO dto) throws SerException {
        return systemBetSer.count(dto);
    }

    @Override
    public SystemBetABO getOne(String id) throws SerException {
        return systemBetSer.getOne(id);
    }

    @Override
    public List<SystemBetABO> list(SystemBetADTO dto) throws SerException {
        return systemBetSer.list(dto);
    }

    @Override
    public void insert(SystemBetATO systemBetATO) throws SerException {
        systemBetSer.insert(systemBetATO);
    }

    @Override
    public void edit(SystemBetATO systemBetATO) throws SerException {
        systemBetSer.edit(systemBetATO);
    }

    @Override
    public void delete(String id) throws SerException {
        systemBetSer.delete(id);
    }

    @Override
    public List<String> getProjectName() throws SerException {
        return systemBetSer.getProjectName();
    }

    @Override
    public List<String> getDepartment() throws SerException {
        return systemBetSer.getDepartment();
    }
    @Override
    public SystemBetABO getSystem(String projectName) throws SerException {
        return systemBetSer.getSystem(projectName);
    }
}