package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.UseCommonlyBO;
import com.bjike.goddess.financeinit.dto.UseCommonlyDTO;
import com.bjike.goddess.financeinit.entity.UseCommonly;
import com.bjike.goddess.financeinit.service.UseCommonlySer;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.UseCommonlyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 常用摘要业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("useCommonlyApiImpl")
public class UseCommonlyApiImpl implements UseCommonlyAPI {
    @Autowired
    private UseCommonlySer useCommonlySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return useCommonlySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return useCommonlySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countUse(UseCommonlyDTO useCommonlyDTO) throws SerException {
        return useCommonlySer.countUse(useCommonlyDTO);
    }

    @Override
    public UseCommonlyBO getOneById(String id) throws SerException {
        return useCommonlySer.getOneById(id);
    }

    @Override
    public List<UseCommonlyBO> listUse(UseCommonlyDTO useCommonlyDTO) throws SerException {
        return useCommonlySer.listUse(useCommonlyDTO);
    }

    @Override
    public UseCommonlyBO addUse(UseCommonlyTO useCommonlyTO) throws SerException {
        return useCommonlySer.addUse(useCommonlyTO);
    }
}