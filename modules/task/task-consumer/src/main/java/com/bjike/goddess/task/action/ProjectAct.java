package com.bjike.goddess.task.action;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.ProjectAPI;
import com.bjike.goddess.task.bo.ProjectBO;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.to.ProjectTO;
import com.bjike.goddess.task.vo.ProjectVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
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
     * 项目列表
     *
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectDTO dto) throws ActException {
        try {
            List<ProjectBO> vos = projectAPI.list(dto);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目列表
     *
     * @version v1
     */
    @GetMapping("v1/list/{userId}")
    public Result list(@PathVariable String userId, Status status) throws ActException {
        try {
            List<ProjectBO> bos = projectAPI.list(userId,status);
            return ActResult.initialize(bos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 项目添加
     *
     * @param to 项目信息
     * @return
     * @throws ActException
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
}
