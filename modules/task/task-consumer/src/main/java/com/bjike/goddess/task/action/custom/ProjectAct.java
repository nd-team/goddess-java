package com.bjike.goddess.task.action.custom;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.ProjectAPI;
import com.bjike.goddess.task.bo.ProjectBO;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.to.ProjectTO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-16 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("project")
public class ProjectAct {
    @Autowired
    private ProjectAPI projectAPI;

    /**
     * 所有项目列表
     *
     * @return class ProjectBO
     * @des 默认分页
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectDTO dto) throws ActException {
        try {
            List<ProjectBO> bos = projectAPI.list(dto, true);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用户项目列表
     *
     * @return class ProjectBO
     * @des 默认不分页
     * @version v1
     */
    @GetMapping("v1/list/{userId}")
    public Result list(@PathVariable String userId, ProjectDTO dto) throws ActException {
        try {
            List<ProjectBO> bos = projectAPI.list(userId, dto);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 项目添加
     *
     * @param to 项目信息
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectTO to, BindingResult rs) throws ActException {
        try {
            projectAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目解冻
     *
     * @param id 项目id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            projectAPI.thaw(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目解冻
     *
     * @param id 项目id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            projectAPI.congeal(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
