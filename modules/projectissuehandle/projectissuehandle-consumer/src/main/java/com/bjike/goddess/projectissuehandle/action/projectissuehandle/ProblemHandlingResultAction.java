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
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
import com.bjike.goddess.projectissuehandle.to.ProjectDeleteFileTO;
import com.bjike.goddess.projectissuehandle.vo.ProblemHandlingResultVO;
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
public class ProblemHandlingResultAction extends BaseFileAction {
    @Autowired
    private ProblemHandlingResultAPI problemHandlingResultAPI;

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

            Boolean isHasPermission = problemHandlingResultAPI.guidePermission(guidePermissionTO);
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

//        try {
//            List<ProblemHandlingResultBO> bo = problemHandlingResultAPI.findListProblemHandlingResult(problemHandlingResultDTO);
//            List<ProblemHandlingResultVO> problemHandlingResultVOS = BeanTransform.copyProperties
//                    ( bo ,ProblemHandlingResultVO.class,request);
//
//            for( int i =0 ;i<bo.size();i++){
//                ProblemHandlingResultBO temp = bo.get(i);
//                ProblemAcceptVO pvo = BeanTransform.copyProperties( temp.getProblemAcceptBO() ,ProblemAcceptVO.class);
//                problemHandlingResultVOS.get(i).setProblemAcceptVO( pvo );
//            }
//
//            return ActResult.initialize(problemHandlingResultVOS);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        try {
            List<ProblemHandlingResultVO> problemHandlingResultVOS = BeanTransform.copyProperties
                    (problemHandlingResultAPI.findListProblemHandlingResult(problemHandlingResultDTO), ProblemHandlingResultVO.class, request);
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
            return ActResult.initialize(BeanTransform.copyProperties(problemHandlingResultBO, ProblemHandlingResultVO.class));
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
    public Result edit(@Validated(EDIT.class) ProblemHandlingResultTO problemHandlingResultTO, BindingResult bindingResult) throws ActException {
        try {
            ProblemHandlingResultBO problemHandlingResultBO = problemHandlingResultAPI.editProblemHandlingResult(problemHandlingResultTO);
            return ActResult.initialize(BeanTransform.copyProperties(problemHandlingResultBO, ProblemHandlingResultVO.class));
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
    public Result search(ProblemHandlingResultDTO problemHandlingResultDTO, HttpServletRequest request) throws ActException {
        try {
            List<ProblemHandlingResultVO> problemHandlingResultVOS = BeanTransform.copyProperties(
                    problemHandlingResultAPI.searchProblemHandlingResult(problemHandlingResultDTO), ProblemHandlingResultVO.class, request);
            return ActResult.initialize(problemHandlingResultVOS);
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
            String paths = "/" + id;
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
     * @param id 确认问题处理结果id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /projectissuehandle/id/....
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
     * 导出确认问题处理结果
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(ProblemHandlingResultDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "确认问题处理结果.xlsx";
            super.writeOutFile(response, problemHandlingResultAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}