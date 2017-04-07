package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.api.ProblemHandlingResultAPI;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
import com.bjike.goddess.projectissuehandle.vo.ProblemAcceptVO;
import com.bjike.goddess.projectissuehandle.vo.ProblemHandlingResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 确认问题处理结果
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectissuehandle/problemhandlingresult")
public class ProblemHandlingResultAction {
    @Autowired
    private ProblemHandlingResultAPI problemHandlingResultAPI;

    /**
     * 获取确认问题处理结果
     *
     * @param problemHandlingResultDTO 确认问题处理结果dto
     * @return class ProblemHandlingResultVO
     * @des 获取所有确认问题处理结果
     * @version v1
     */
    @GetMapping("v1/listProblemHandlingResult")
    public Result findListProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws ActException {
        try {
            List<ProblemHandlingResultVO> problemHandlingResultVOS = BeanTransform.copyProperties
                    (problemHandlingResultAPI.findListProblemHandlingResult(problemHandlingResultDTO), ProblemHandlingResultVO.class);
            return ActResult.initialize(problemHandlingResultVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加确认问题处理结果
     *
     * @param problemHandlingResultTO 确认问题处理结果数据to
     * @return class ProblemHandlingResultVO
     * @des 添加确认问题处理结果
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws ActException {
        try {
            ProblemHandlingResultBO problemHandlingResultBO = problemHandlingResultAPI.insertProblemHandlingResult(problemHandlingResultTO);
            return ActResult.initialize(problemHandlingResultBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑确认问题处理结果
     *
     * @param problemHandlingResultTO 确认问题处理结果数据to
     * @return class ProblemHandlingResultVO
     * @des 编辑确认问题处理结果
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws ActException {
        try {
            ProblemHandlingResultBO problemHandlingResultBO = problemHandlingResultAPI.editProblemHandlingResult(problemHandlingResultTO);
            return ActResult.initialize(problemHandlingResultBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除确认问题处理结果
     *
     * @param id 用户id
     * @des 根据用户id删除确认问题处理结果记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeProblemHandlingResult(@PathVariable String id) throws ActException {
        try {
            problemHandlingResultAPI.removeProblemHandlingResult(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @return class ProblemHandlingResultVO
     * @des 根据内部项目名称(internalProjectName)、工程类型(projectType)、问题对象(problemObject) 搜索
     * @version v1
     */
    @GetMapping("v1/search")
    public Result searchProblemHandlingResult(String internalProjectName, String projectType, String problemObject) throws ActException {
        try {
            ProblemHandlingResultBO problemHandlingResultBO = problemHandlingResultAPI.searchProblemHandlingResult(internalProjectName, projectType, problemObject);
            ProblemHandlingResultVO problemHandlingResultVO = BeanTransform.copyProperties(problemHandlingResultBO, ProblemHandlingResultBO.class);
            return ActResult.initialize(problemHandlingResultVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出确认问题处理结果
     *
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String internalProjectName, String projectType) throws ActException {
        String excel = null;
        try {
            excel = problemHandlingResultAPI.exportExcel(internalProjectName,projectType);
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
            problemHandlingResultAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}