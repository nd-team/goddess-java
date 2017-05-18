package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.ProjectBasicInfoAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectBasicInfoBO;
import com.bjike.goddess.projectmeasure.dto.ProjectBasicInfoDTO;
import com.bjike.goddess.projectmeasure.service.ProjectBasicInfoSer;
import com.bjike.goddess.projectmeasure.to.ProjectBasicInfoTO;
import com.bjike.goddess.projectmeasure.vo.ProjectBasicInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 项目基本信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:07 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmeasure/projectbasicinfo")
public class ProjectBasicInfoAct {

    @Autowired
    private ProjectBasicInfoAPI projectBasicInfoAPI;

    /**
     * 分页查询项目基本信息
     *
     * @param dto 项目基本信息传输对象
     * @return class ProjectBasicInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectBasicInfoDTO dto) throws ActException {
        try {
            List<ProjectBasicInfoBO> boList = projectBasicInfoAPI.list(dto);
            List<ProjectBasicInfoVO> voList = BeanTransform.copyProperties(boList, ProjectBasicInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目基本信息
     *
     * @param to 项目基本信息to信息
     * @return class ProjectBasicInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectBasicInfoTO to) throws ActException {
        try {
            ProjectBasicInfoBO bo = projectBasicInfoAPI.save(to);
            ProjectBasicInfoVO vo = BeanTransform.copyProperties(bo, ProjectBasicInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目基本信息
     *
     * @param id 项目基本信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectBasicInfoAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目基本信息
     *
     * @param to 项目基本信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectBasicInfoTO to) throws ActException {
        try {
            projectBasicInfoAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}