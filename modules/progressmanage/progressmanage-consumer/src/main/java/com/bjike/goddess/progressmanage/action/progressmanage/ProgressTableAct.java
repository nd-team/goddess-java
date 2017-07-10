package com.bjike.goddess.progressmanage.action.progressmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.api.ProgressTableAPI;
import com.bjike.goddess.progressmanage.api.ProjectInfoAPI;
import com.bjike.goddess.progressmanage.bo.ProgressTableBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.to.ProgressTableTO;
import com.bjike.goddess.progressmanage.vo.ProgressTableVO;
import com.bjike.goddess.progressmanage.vo.ProjectInfoVO;
import com.bjike.goddess.progressmanage.vo.ProjectListForNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 进度表
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("progresstable")
public class ProgressTableAct {

    @Autowired
    private ProgressTableAPI progressTableAPI;
    @Autowired
    private ProjectInfoAPI projectInfoAPI;

    /**
     * 项目下拉列表
     *
     * @return class ProjectListForNodeVO
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {

        try {
            List<ProjectListForNodeVO> voList = BeanTransform.copyProperties(projectInfoAPI.projects(), ProjectListForNodeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增进度表
     *
     * @param to 进度表
     * @return class ProgressTableVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProgressTableTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProgressTableBO bo = progressTableAPI.add(to);
            ProgressTableVO vo = BeanTransform.copyProperties(bo, ProgressTableVO.class, request);
            vo.setProjectInfoVO(BeanTransform.copyProperties(bo.getProjectInfoBO(), ProjectInfoVO.class));
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑进度表
     *
     * @param to 进度表
     * @return class ProgressTableVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProgressTableTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProgressTableBO bo = progressTableAPI.edit(to);
            ProgressTableVO vo = BeanTransform.copyProperties(bo, ProgressTableVO.class, request);
            vo.setProjectInfoVO(BeanTransform.copyProperties(bo.getProjectInfoBO(), ProjectInfoVO.class));
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除进度表
     *
     * @param id 项目承包洽谈ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            progressTableAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            progressTableAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/unfreeze/{id}")
    public Result unfreeze(@PathVariable String id) throws ActException {
        try {
            progressTableAPI.unfreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class ProgressTableVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated({ProgressTableDTO.validateProject.class}) ProgressTableDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<ProgressTableBO> boList = progressTableAPI.pageList(dto);
            List<ProgressTableVO> voList = null;
            if (!CollectionUtils.isEmpty(boList)) {
                voList = new ArrayList<ProgressTableVO>();
                for (ProgressTableBO bo : boList) {
                    ProgressTableVO vo = BeanTransform.copyProperties(bo, ProgressTableVO.class, request);
                    vo.setProjectInfoVO(BeanTransform.copyProperties(bo.getProjectInfoBO(), ProjectInfoVO.class));
                    voList.add(vo);
                }
            }

            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}