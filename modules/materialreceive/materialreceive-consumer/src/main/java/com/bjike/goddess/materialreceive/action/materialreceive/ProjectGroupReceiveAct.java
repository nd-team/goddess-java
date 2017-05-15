package com.bjike.goddess.materialreceive.action.materialreceive;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialreceive.api.ProjectGroupReceiveAPI;
import com.bjike.goddess.materialreceive.bo.ProjectGroupReceiveBO;
import com.bjike.goddess.materialreceive.dto.ProjectGroupReceiveDTO;
import com.bjike.goddess.materialreceive.to.ProjectGroupReceiveTO;
import com.bjike.goddess.materialreceive.vo.ProjectGroupReceiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目组领用归还
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectgroupreceive")
public class ProjectGroupReceiveAct {

    @Autowired
    private ProjectGroupReceiveAPI projectGroupReceiveAPI;

    /**
     * 根据id查询项目组领用归还
     *
     * @param id 项目组领用归还唯一标识
     * @return class ProjectGroupReceiveVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectgroupreceive/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectGroupReceiveBO bo = projectGroupReceiveAPI.findById(id);
            ProjectGroupReceiveVO vo = BeanTransform.copyProperties(bo, ProjectGroupReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 项目组领用归还dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ProjectGroupReceiveDTO dto, BindingResult result) throws ActException {
        try {
            Long count = projectGroupReceiveAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目组领用归还
     *
     * @param dto 项目组领用归还dto
     * @return class ProjectGroupReceiveVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ProjectGroupReceiveDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ProjectGroupReceiveBO> boList = projectGroupReceiveAPI.list(dto);
            List<ProjectGroupReceiveVO> voList = BeanTransform.copyProperties(boList, ProjectGroupReceiveVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目组领用归还
     *
     * @param to 项目组领用归还to
     * @return class ProjectGroupReceiveVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ProjectGroupReceiveTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProjectGroupReceiveBO bo = projectGroupReceiveAPI.save(to);
            ProjectGroupReceiveVO vo = BeanTransform.copyProperties(bo, ProjectGroupReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除项目组领用归还
     *
     * @param id 项目组领用归还唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectGroupReceiveAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资领用
     *
     * @param to 物资领用to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProjectGroupReceiveTO to, BindingResult result) throws ActException {
        try {
            projectGroupReceiveAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 归还物资
     *
     * @param to 物资领用to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/returnmaterial")
    public Result returnMaterial(@Validated(ProjectGroupReceiveTO.RETURNMATERIAL.class) ProjectGroupReceiveTO to, BindingResult result) throws ActException {
        try {
            projectGroupReceiveAPI.returnMaterial(to);
            return new ActResult("returnmaterial success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}