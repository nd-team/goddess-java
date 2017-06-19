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
import com.bjike.goddess.projectissuehandle.api.InvolvedProcessingTaskAPI;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;
import com.bjike.goddess.projectissuehandle.to.ProjectDeleteFileTO;
import com.bjike.goddess.projectissuehandle.vo.InvolvedProcessingTaskVO;
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
import java.util.List;


/**
 * 参与处理人员的任务分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("involvedprocessingtask")
public class InvolvedProcessingTaskAction extends BaseFileAction {
    @Autowired
    private InvolvedProcessingTaskAPI involvedProcessingTaskAPI;

    @Autowired
    private FileAPI fileAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = involvedProcessingTaskAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 参与处理人员的任务分配列表总条数
     *
     * @param involvedProcessingTaskDTO 参与处理人员的任务分配dto
     * @des 获取所有参与处理人员的任务分配总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws ActException {
        try {
            Long count = involvedProcessingTaskAPI.countInvolvedProcessingTask(involvedProcessingTaskDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个参与处理人员的任务分配
     *
     * @param id
     * @return class InvolvedProcessingTaskVO
     * @des 获取一个参与处理人员的任务分配
     * @version v1
     */
    @GetMapping("v1/task/{id}")
    public Result task(@PathVariable String id) throws ActException {
        try {
            InvolvedProcessingTaskBO involvedProcessingTaskBO = involvedProcessingTaskAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(involvedProcessingTaskBO, InvolvedProcessingTaskVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 参与处理人员的任务分配列表
     *
     * @param involvedProcessingTaskDTO 参与处理人员的任务分配dto
     * @return class InvolvedProcessingTaskVO
     * @des 获取所有参与处理人员的任务分配
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InvolvedProcessingTaskDTO involvedProcessingTaskDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvolvedProcessingTaskVO> involvedProcessingTaskVOS = BeanTransform.copyProperties
                    (involvedProcessingTaskAPI.findListInvolvedProcessingTask(involvedProcessingTaskDTO), InvolvedProcessingTaskVO.class, request);
            return ActResult.initialize(involvedProcessingTaskVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加参与处理人员的任务分配
     *
     * @param involvedProcessingTaskTO 参与处理人员的任务分配数据to
     * @return class InvolvedProcessingTaskVO
     * @des 添加参与处理人员的任务分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) InvolvedProcessingTaskTO involvedProcessingTaskTO, BindingResult bindingResult) throws ActException {
        try {
            InvolvedProcessingTaskBO involvedProcessingTaskBO = involvedProcessingTaskAPI.insertInvolvedProcessingTask(involvedProcessingTaskTO);
            return ActResult.initialize(BeanTransform.copyProperties(involvedProcessingTaskBO, InvolvedProcessingTaskVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑参与处理人员的任务分配
     *
     * @param involvedProcessingTaskTO 参与处理人员的任务分配数据to
     * @return class InvolvedProcessingTaskVO
     * @des 编辑参与处理人员的任务分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editInvolvedProcessingTask(@Validated(EDIT.class) InvolvedProcessingTaskTO involvedProcessingTaskTO, BindingResult bindingResult) throws ActException {
        try {
            InvolvedProcessingTaskBO involvedProcessingTaskBO = involvedProcessingTaskAPI.editInvolvedProcessingTask(involvedProcessingTaskTO);
            return ActResult.initialize(BeanTransform.copyProperties(involvedProcessingTaskBO, InvolvedProcessingTaskVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除参与处理人员的任务分配
     *
     * @param id 用户id
     * @des 根据用户id删除参与处理人员的任务分配记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            involvedProcessingTaskAPI.removeInvolvedProcessingTask(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param involvedProcessingTaskDTO 参与处理人员的任务分配dto
     * @return class InvolvedProcessingTaskVO
     * @des 搜索获取所有参与处理人员的任务分配
     * @version v1
     */
    @GetMapping("v1/search")
    public Result search(InvolvedProcessingTaskDTO involvedProcessingTaskDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvolvedProcessingTaskVO> involvedProcessingTaskVOS = BeanTransform.copyProperties
                    (involvedProcessingTaskAPI.searchInvolvedProcessingTask(involvedProcessingTaskDTO), InvolvedProcessingTaskVO.class, request);
            return ActResult.initialize(involvedProcessingTaskVOS);
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
            String paths = "/projectissuehandle/involvedprocessingtask/" + id;
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
            String path = "/projectissuehandle/involvedprocessingtask/" + id;
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
     * 导出参与处理人员的任务分配
     *
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(InvolvedProcessingTaskDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "参与处理人员的任务分配.xlsx";
            super.writeOutFile(response, involvedProcessingTaskAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}