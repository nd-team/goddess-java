package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.dto.ModulesDTO;
import com.bjike.goddess.organize.entity.Modules;

import java.util.Map;

/**
 * 岗位工作明细表-模块表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:58 ]
 * @Description: [ 岗位工作明细表-模块表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ModulesSer extends Ser<Modules, ModulesDTO> {

    Map<String, String> findModuleAndPost(String username)throws SerException;
}