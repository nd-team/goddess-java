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
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.projectissuehandle.api.ProblemAcceptAPI;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.enums.AffectedDepartment;
import com.bjike.goddess.projectissuehandle.enums.ProblemProcessingTime;
import com.bjike.goddess.projectissuehandle.excel.SonPermissionObject;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;
import com.bjike.goddess.projectissuehandle.to.ProjectDeleteFileTO;
import com.bjike.goddess.projectissuehandle.vo.ProblemAcceptVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("propermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = problemAcceptAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = problemAcceptAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
            return ActResult.initialize(BeanTransform.copyProperties(problemAcceptBO, ProblemAcceptVO.class));
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
            return ActResult.initialize(BeanTransform.copyProperties(problemAcceptBO, ProblemAcceptVO.class));
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
                    (problemAcceptAPI.searchProblemAccept(problemAcceptDTO), ProblemAcceptVO.class, request);
            return ActResult.initialize(problemAcceptVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目执行中的问题受理
     *
     * @param projectNum 项目问题编号
     * @return class ProblemAcceptVO
     * @des 根据项目问题编号获取项目执行中的问题受理
     * @version v1
     */
    @GetMapping("v1/getProjectNum")
    public Result getProjectNum(String projectNum) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemAcceptAPI.getProjectNum(projectNum);
            return ActResult.initialize(BeanTransform.copyProperties(problemAcceptBO, ProblemAcceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目问题编号
     *
     * @des 获取项目问题编号
     * @version v1
     */
    @GetMapping("v1/getNum")
    public Result getNum() throws ActException {
        try {
            List<String> list = problemAcceptAPI.getProjectNum();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题紧急程度
     *
     * @param problemProcessingTime 问题处理时间
     * @param affectedDepartment    受影响部门
     * @return class ProblemAcceptVO
     * @des 根据问题处理时间和受影响部门得到问题紧急程度
     * @version v1
     */
    @GetMapping("v1/degree")
    public Result degree(ProblemProcessingTime problemProcessingTime, AffectedDepartment affectedDepartment, HttpServletRequest request) throws ActException {
        try {
            String string = problemAcceptAPI.degree(problemProcessingTime, affectedDepartment);
            return ActResult.initialize(string);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 上传项目执行中的问题受理
     * @version v1
     */
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String paths = "/projectissuehandle/problemaccept/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 文件附件列表
     *
     * @param id 项目执行中的问题受理id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /projectissuehandle/id/....
            String path = "/projectissuehandle/problemaccept/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {


            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param projectDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(ProjectDeleteFileTO.TestDEL.class) ProjectDeleteFileTO projectDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != projectDeleteFileTO.getPaths() && projectDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), projectDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


    /**
     * 导出excel
     *
     * @param dto 项目执行中的问题受理
     * @des 导出项目执行中的问题受理
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(ProblemAcceptDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目执行中的问题受理.xlsx";
            super.writeOutFile(response, problemAcceptAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}