package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.ReplaceRegisterBO;
import com.bjike.goddess.secure.dto.ReplaceRegisterDTO;
import com.bjike.goddess.secure.service.ReplaceRegisterSer;
import com.bjike.goddess.secure.to.DrawRegisterTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.ReplaceRegisterTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 社保卡补办登记表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 06:09 ]
 * @Description: [ 社保卡补办登记表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("replaceRegisterApiImpl")
public class ReplaceRegisterApiImpl implements ReplaceRegisterAPI {
    @Autowired
    private ReplaceRegisterSer replaceRegisterSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return replaceRegisterSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return replaceRegisterSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ReplaceRegisterBO save(ReplaceRegisterTO to) throws SerException {
        return replaceRegisterSer.save(to);
    }

    @Override
    public ReplaceRegisterBO edit(ReplaceRegisterTO to) throws SerException {
        return replaceRegisterSer.edit(to);
    }

    @Override
    public List<ReplaceRegisterBO> list(ReplaceRegisterDTO dto) throws SerException {
        return replaceRegisterSer.list(dto);
    }

    @Override
    public ReplaceRegisterBO findByID(String id) throws SerException {
        return replaceRegisterSer.findByID(id);
    }

    @Override
    public Long count(ReplaceRegisterDTO dto) throws SerException {
        return replaceRegisterSer.count(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        replaceRegisterSer.delete(id);

    }

    @Override
    public Set<String> allName() throws SerException {
        return replaceRegisterSer.allName();
    }

    @Override
    public ReplaceRegisterBO resourcesAudit(ReplaceRegisterTO to) throws SerException {
        return replaceRegisterSer.resourcesAudit(to);
    }

    @Override
    public ReplaceRegisterBO financeAudit(ReplaceRegisterTO to) throws SerException {
        return replaceRegisterSer.financeAudit(to);
    }

    @Override
    public ReplaceRegisterBO generalAudit(ReplaceRegisterTO to) throws SerException {
        return replaceRegisterSer.generalAudit(to);
    }
}