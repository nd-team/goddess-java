package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.service.ModulesSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 岗位工作明细表-模块表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:58 ]
 * @Description: [ 岗位工作明细表-模块表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("modulesApiImpl")
public class ModulesApiImpl implements ModulesAPI {
    @Autowired
    private ModulesSer modulesSer;
    @Override
    public Map<String, String> findModuleAndPost(String username)throws SerException {
        return modulesSer.findModuleAndPost(username);
    }
}