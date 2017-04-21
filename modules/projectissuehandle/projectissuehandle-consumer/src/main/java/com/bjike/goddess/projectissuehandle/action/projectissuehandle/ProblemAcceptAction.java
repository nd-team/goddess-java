package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.api.ProblemAcceptAPI;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;
import com.bjike.goddess.projectissuehandle.vo.ProblemAcceptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 项目执行中的问题受理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectissuehandle/problemaccept")
public class ProblemAcceptAction {
    @Autowired
    private ProblemAcceptAPI problemAcceptAPI;
    /**
     * 项目执行中的问题受理列表总条数
     *
     * @param problemAcceptDTO 项目执行中的问题受理dto
     * @des 获取所有项目执行中的问题受理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblemAcceptDTO problemAcceptDTO) throws ActException {
        try {
            Long count = problemAcceptAPI.countProblemAccept(problemAcceptDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目执行中的问题受理列表
     *
     * @param problemAcceptDTO 项目执行中的问题受理dto
     * @return class ProblemAcceptVO
     * @des 获取所有项目执行中的问题受理
     * @version v1
     */
    @GetMapping("v1/listProblemAccept")
    public Result findListProblemAccept(ProblemAcceptDTO problemAcceptDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProblemAcceptVO> problemAcceptVOS = BeanTransform.copyProperties
                    (problemAcceptAPI.findListProblemAccept(problemAcceptDTO), ProblemAcceptVO.class);
            return ActResult.initialize(problemAcceptVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目执行中的问题受理
     *
     * @param problemAcceptTO 项目执行中的问题受理数据to
     * @return class ProblemAcceptVO
     * @des 添加项目执行中的问题受理
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addProblemAccept(ProblemAcceptTO problemAcceptTO,BindingResult bindingResult) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemAcceptAPI.insertProblemAccept(problemAcceptTO);
            return ActResult.initialize(problemAcceptBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目执行中的问题受理
     *
     * @param problemAcceptTO 项目执行中的问题受理数据to
     * @return class ProblemAcceptVO
     * @des 编辑项目执行中的问题受理
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editProblemAccept(ProblemAcceptTO problemAcceptTO) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemAcceptAPI.editProblemAccept(problemAcceptTO);
            return ActResult.initialize(problemAcceptBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目执行中的问题受理
     *
     * @param id 用户id
     * @des 根据用户id删除项目执行中的问题受理记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeProblemAccept(@PathVariable String id) throws ActException {
        try {
            problemAcceptAPI.removeProblemAccept(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @return class ProblemAcceptVO
     * @des 根据内部项目名称(internalProjectName)、工程类型(projectType) 搜索
     * @version v1
     */
    @GetMapping("v1/search")
    public Result searchProblemAccept(String internalProjectName, String projectType) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemAcceptAPI.searchProblemAccept(internalProjectName, projectType);
            ProblemAcceptVO problemAcceptVO = BeanTransform.copyProperties(problemAcceptBO, ProblemAcceptBO.class);
            return ActResult.initialize(problemAcceptVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出项目执行中的问题受理
     *
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String internalProjectName, String projectType) throws ActException {
        String excel = null;
        try {
            excel = problemAcceptAPI.exportExcel(internalProjectName,projectType);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            problemAcceptAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}