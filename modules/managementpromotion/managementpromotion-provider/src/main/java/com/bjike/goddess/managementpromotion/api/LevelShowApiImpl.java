package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.dto.LevelShowDTO;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managementpromotion.service.LevelShowSer;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.LevelShowTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理等级情况慨览业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:53 ]
 * @Description: [ 管理等级情况慨览业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("levelShowApiImpl")
public class LevelShowApiImpl implements LevelShowAPI {
    @Autowired
    private LevelShowSer levelShowSer;

    @Override
    public LevelShowBO save(LevelShowTO to) throws SerException {
        return levelShowSer.save(to);
    }

    @Override
    public List<LevelShowBO> find(LevelShowDTO dto) throws SerException {
        return levelShowSer.find(dto);
    }

    @Override
    public Long count(LevelShowDTO dto) throws SerException {
        return levelShowSer.count(dto);
    }

    @Override
    public List<LevelShowBO> findAll(LevelShowDTO dto) throws SerException {
        return levelShowSer.findAll(dto);
    }

    @Override
    public LevelShowBO findByID(String id) throws SerException {
        return levelShowSer.findByID(id);
    }

    @Override
    public void update(LevelShowTO to) throws SerException {
        levelShowSer.update(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return levelShowSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return levelShowSer.guidePermission(guidePermissionTO);
    }

    @Override
    public LevelShow findByEmployeeId(String employeeId) throws SerException {
        return levelShowSer.findByEmployeeId(employeeId);
    }

    @Override
    public LevelShow findByName(String name) throws SerException {
        return levelShowSer.findByName(name);
    }

    @Override
    public LevelShowBO findEmployeeId(String employeeId) throws SerException {
        return levelShowSer.findEmployeeId(employeeId);
    }
}