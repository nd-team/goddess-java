package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.LogoutShareBO;
import com.bjike.goddess.shareholdersmanage.bo.LogoutShareLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.LogoutShareDTO;
import com.bjike.goddess.shareholdersmanage.service.LogoutShareSer;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.LogoutShareTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注销股东业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:55 ]
 * @Description: [ 注销股东业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("logoutShareApiImpl")
public class LogoutShareApiImpl implements LogoutShareAPI {
    @Autowired
    private LogoutShareSer logoutShareSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return logoutShareSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return logoutShareSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countLogout(LogoutShareDTO logoutShareDTO) throws SerException {
        return logoutShareSer.countLogout(logoutShareDTO);
    }

    @Override
    public LogoutShareBO getOne(String id) throws SerException {
        return logoutShareSer.getOne(id);
    }

    @Override
    public List<LogoutShareBO> findList(LogoutShareDTO logoutShareDTO) throws SerException {
        return logoutShareSer.findList(logoutShareDTO);
    }

    @Override
    public LogoutShareBO save(LogoutShareTO logoutShareTO) throws SerException {
        return logoutShareSer.save(logoutShareTO);
    }

    @Override
    public LogoutShareBO edit(LogoutShareTO logoutShareTO) throws SerException {
        return logoutShareSer.edit(logoutShareTO);
    }

    @Override
    public void delete(String id) throws SerException {
        logoutShareSer.delete(id);
    }

    @Override
    public LogoutShareLinkDateBO linkDateByName(String logoutShareName) throws SerException {
        return logoutShareSer.linkDateByName(logoutShareName);
    }
}