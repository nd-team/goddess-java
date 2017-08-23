package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.SelfProjectBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.SelfProjectDTO;
import com.bjike.goddess.capability.entity.SelfProject;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人经手项目业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:26 ]
 * @Description: [ 个人经手项目业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class SelfProjectSerImpl extends ServiceImpl<SelfProject, SelfProjectDTO> implements SelfProjectSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfProjectBO addSelfProject(String[] selfProjects, String id) throws SerException {
        List<SelfProject> selfProjectList = new ArrayList<>();
        if (selfProjects != null && selfProjects.length > 0) {
            for (String str : selfProjects) {
                SelfProject selfProject = new SelfProject();
                selfProject.setName(str);
                selfProject.setBaseId(id);
                selfProjectList.add(selfProject);
            }
        }
        if (selfProjectList != null && selfProjectList.size() > 0) {
            super.save(selfProjectList);
        }
        return new SelfProjectBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfProjectBO editSelfProject(String[] selfProjects, String id) throws SerException {

        List<SelfProject> selfProjectList = new ArrayList<SelfProject>();
        if (null != selfProjects && selfProjects.length > 0) {
            this.deleteSelfProject(id);
            for (String str : selfProjects) {
                SelfProject selfProject = new SelfProject();
                selfProject.setBaseId(id);
                selfProject.setName(str);
                selfProjectList.add(selfProject);
            }
            if (null != selfProjectList && selfProjectList.size() > 0) {
                super.save(selfProjectList);
            }

        }
        return new SelfProjectBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSelfProject(String id) throws SerException {
        SelfProjectDTO dto = new SelfProjectDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<SelfProject> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }
}