package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.workjoin.api.TaskJoinAPI;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.bo.TaskJoinBO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.dto.TaskJoinDTO;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.JoinInfoTO;
import com.bjike.goddess.workjoin.to.TaskJoinTO;
import com.bjike.goddess.workjoin.to.WorkJoinDeleteFileTO;
import com.bjike.goddess.workjoin.vo.JoinInfoVO;
import com.bjike.goddess.workjoin.vo.TaskJoinVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 任务交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("taskjoin")
public class TaskJoinAction extends BaseFileAction{
    @Autowired
    private TaskJoinAPI taskJoinAPI;
    @Autowired
    private FileAPI fileAPI;
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

            Boolean isHasPermission = taskJoinAPI.guidePermission(guidePermissionTO);
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
     * 任务交接列表总条数
     *
     * @param taskJoinDTO 任务交接dto
     * @des 获取所有任务交接总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TaskJoinDTO taskJoinDTO) throws ActException {
        try {
            Long count = taskJoinAPI.countTaskJoin(taskJoinDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个任务交接
     *
     * @param id
     * @return class TaskJoinVO
     * @des 获取一个任务交接
     * @version v1
     */
    @GetMapping("v1/task/{id}")
    public Result task(@PathVariable String id) throws ActException {
        try {
            TaskJoinBO taskJoinBO = taskJoinAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(taskJoinBO, TaskJoinVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 任务交接列表
     *
     * @param taskJoinDTO 任务交接dto
     * @return class TaskJoinVO
     * @des 获取所有任务交接
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TaskJoinDTO taskJoinDTO, HttpServletRequest request) throws ActException {
        try {
            List<TaskJoinVO> taskJoinVOS = BeanTransform.copyProperties
                    (taskJoinAPI.findListTaskJoin(taskJoinDTO),TaskJoinVO.class, request);
            return ActResult.initialize(taskJoinVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加任务交接
     *
     * @param taskJoinTO 任务交接数据to
     * @return class TaskJoinVO
     * @des 添加任务交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) TaskJoinTO taskJoinTO, BindingResult bindingResult) throws ActException {
        try {
            TaskJoinBO joinInfoBO = taskJoinAPI.insertTaskJoin(taskJoinTO);
            return ActResult.initialize(joinInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑任务交接
     *
     * @param taskJoinTO 任务交接数据to
     * @return class TaskJoinVO
     * @des 编辑任务交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TaskJoinTO taskJoinTO, BindingResult bindingResult) throws ActException {
        try {
            TaskJoinBO taskJoinBO = taskJoinAPI.editTaskJoin(taskJoinTO);
            return ActResult.initialize(taskJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除任务交接
     *
     * @param id 用户id
     * @des 根据用户id删除任务交接记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            taskJoinAPI.removeTaskJoin(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 招标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 招标信息id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /bidding/id/....
            String path = "/" + id;
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
     * @param workJoinDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(WorkJoinDeleteFileTO.TestDEL.class) WorkJoinDeleteFileTO workJoinDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != workJoinDeleteFileTO.getPaths() && workJoinDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),workJoinDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }



}