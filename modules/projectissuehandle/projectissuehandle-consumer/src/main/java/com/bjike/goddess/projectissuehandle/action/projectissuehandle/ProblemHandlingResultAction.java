package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.api.ProblemHandlingResultAPI;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
import com.bjike.goddess.projectissuehandle.vo.CollectVO;
import com.bjike.goddess.projectissuehandle.vo.ProblemHandlingResultVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
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
@RequestMapping("problemhandlingresult")
public class ProblemHandlingResultAction extends BaseFileAction{
    @Autowired
    private ProblemHandlingResultAPI problemHandlingResultAPI;

    @Autowired
    private FileAPI fileAPI;
    /**
     * 确认问题处理结果列表总条数
     *
     * @param problemHandlingResultDTO 确认问题处理结果dto
     * @des 获取所有确认问题处理结果总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblemHandlingResultDTO problemHandlingResultDTO) throws ActException {
        try {
            Long count = problemHandlingResultAPI.countProblemHandlingResult(problemHandlingResultDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个确认问题处理结果
     *
     * @param id
     * @return class ProblemHandlingResultVO
     * @des 获取一个确认问题处理结果
     * @version v1
     */
    @GetMapping("v1/result/{id}")
    public Result result(@PathVariable String id) throws ActException {
        try {
            ProblemHandlingResultBO problemHandlingResultBO = problemHandlingResultAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(problemHandlingResultBO, ProblemHandlingResultVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认问题处理结果列表
     *
     * @param problemHandlingResultDTO 确认问题处理结果dto
     * @return class ProblemHandlingResultVO
     * @des 获取所有确认问题处理结果
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProblemHandlingResultDTO problemHandlingResultDTO, HttpServletRequest request) throws ActException {
        try {
            List<ProblemHandlingResultVO> problemHandlingResultVOS = BeanTransform.copyProperties
                    (problemHandlingResultAPI.findListProblemHandlingResult(problemHandlingResultDTO), ProblemHandlingResultVO.class,request);
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
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ProblemHandlingResultTO problemHandlingResultTO, BindingResult bindingResult) throws ActException {
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
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProblemHandlingResultTO problemHandlingResultTO,BindingResult bindingResult) throws ActException {
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
    public Result delete(@PathVariable String id) throws ActException {
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
     * @param problemHandlingResultDTO 确认问题处理结果dto
     * @return class ProblemHandlingResultVO
     * @des 搜索获取所有确认问题处理结果
     * @version v1
     */
    @GetMapping("v1/search")
    public Result search(ProblemHandlingResultDTO problemHandlingResultDTO, HttpServletRequest httpServletRequest) throws ActException {
        try {
            List<ProblemHandlingResultVO> problemHandlingResultVOS = BeanTransform.copyProperties(
                    problemHandlingResultAPI.searchProblemHandlingResult(problemHandlingResultDTO), ProblemHandlingResultVO.class, true);
            return ActResult.initialize(problemHandlingResultVOS);
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
            excel = problemHandlingResultAPI.exportExcel(internalProjectName, projectType);
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
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
    /**
     * 汇总项目中问题受理和处理
     *
     * @param areas 地区
     * @des 汇总项目中问题受理和处理管理
     * @return  class CollectVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect ( @RequestParam String[] areas ) throws ActException {
        try {
            List<CollectVO> collectVOS = BeanTransform.copyProperties(
                    problemHandlingResultAPI.collect(areas),CollectVO.class);
            return ActResult.initialize(collectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/area")
    public Result area() throws ActException {
        try {
            List<String> areaList = problemHandlingResultAPI.getArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}