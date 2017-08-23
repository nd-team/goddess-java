package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.SelfProjectBO;
import com.bjike.goddess.capability.service.SelfProjectSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人经手项目业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:26 ]
 * @Description: [ 个人经手项目业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("selfProjectApiImpl")
public class SelfProjectApiImpl implements SelfProjectAPI {
    @Autowired
    private SelfProjectSer selfProjectSer;

    @Override
    public SelfProjectBO addSelfProject(String[] selfProjects, String companyId) throws SerException {
        return selfProjectSer.addSelfProject(selfProjects, companyId);
    }

    @Override
    public SelfProjectBO editSelfProject(String[] selfProjects, String companyId) throws SerException {
        return selfProjectSer.editSelfProject(selfProjects, companyId);
    }

    @Override
    public void deleteSelfProject(String id) throws SerException {
        selfProjectSer.deleteSelfProject(id);
    }

}