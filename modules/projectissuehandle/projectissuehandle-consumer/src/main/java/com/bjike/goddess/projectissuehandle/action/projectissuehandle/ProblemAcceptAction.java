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
import com.bjike.goddess.projectissuehandle.api.ProblemAcceptAPI;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;
import com.bjike.goddess.projectissuehandle.vo.ProblemAcceptVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
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
@RequestMapping("problemaccept")
public class ProblemAcceptAction extends BaseFileAction {
    @Autowired
    private ProblemAcceptAPI problemAcceptAPI;
    @Autowired
    private FileAPI fileAPI;

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
     * 一个项目执行中的问题受理
     *
     * @param id
     * @return class ProblemAcceptVO
     * @des 获取一个项目执行中的问题受理
     * @version v1
     */
    @GetMapping("v1/problem/{id}")
    public Result problem(@PathVariable String id) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemAcceptAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(problemAcceptBO, ProblemAcceptVO.class));
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
    @GetMapping("v1/list")
    public Result list(ProblemAcceptDTO problemAcceptDTO, HttpServletRequest request) throws ActException {
        try {
            List<ProblemAcceptVO> problemAcceptVOS = BeanTransform.copyProperties
                    (problemAcceptAPI.findListProblemAccept(problemAcceptDTO), ProblemAcceptVO.class, request);
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
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ProblemAcceptTO problemAcceptTO, BindingResult result) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemAcceptAPI.insertProblemAccept(problemAcceptTO);
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
    public Result deleteProblemAccept(@PathVariable String id) throws ActException {
        try {
            problemAcceptAPI.removeProblemAccept(id);
            return new ActResult("delete success");
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
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editProblemAccept(@Validated(EDIT.class) ProblemAcceptTO problemAcceptTO, BindingResult result) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemAcceptAPI.editProblemAccept(problemAcceptTO);
            return ActResult.initialize(problemAcceptBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param problemAcceptDTO 项目执行中的问题受理dto
     * @return class ProblemAcceptVO
     * @des 搜索获取所有项目执行中的问题受理
     * @version v1
     */
    @GetMapping("v1/search")
    public Result search(ProblemAcceptDTO problemAcceptDTO, HttpServletRequest request) throws ActException {
        try {
            List<ProblemAcceptVO> problemAcceptVOS = BeanTransform.copyProperties
                    (problemAcceptAPI.searchProblemAccept(problemAcceptDTO), ProblemAcceptVO.class,request);
            return ActResult.initialize(problemAcceptVOS);
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
            excel = problemAcceptAPI.exportExcel(internalProjectName, projectType);
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
    public Result upload(HttpServletRequest request, String path) throws ActException {
        try {
            path = "/user/xxx/";
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}