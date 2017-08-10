package com.bjike.goddess.staffentry.action.staffentry;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.staffentry.to.EntryBasicInfoTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.SiginManageDeleteFileTO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
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
import javax.validation.Valid;
import java.io.InputStream;
import java.util.List;

/**
 * 入职基本信息
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 12:08]
 * @Description: [入职基本信息]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("entrybasicinfo")
public class EntryBasicInfoAction extends BaseFileAction{

    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;
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

            Boolean isHasPermission = entryBasicInfoAPI.guidePermission(guidePermissionTO);
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
     * 入职基本信息列表总条数
     *
     * @param entryBasicInfoDTO 入职基本信息dto
     * @des 获取所有薪资确认信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EntryBasicInfoDTO entryBasicInfoDTO) throws ActException {
        try {
            Long count = entryBasicInfoAPI.countEntryBasicInfo(entryBasicInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 入职基本信息列表
     *
     * @param entryBasicInfoDTO 入职基本信息dto
     * @des 获取所有入职基本信息
     * @return class EntryBasicInfoVO
     * @version v1
     */
    @GetMapping("v1/listEntryBasicInfo")
    public Result findListEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws ActException {
        try {
            List<EntryBasicInfoVO> entryBasicInfoVOList = BeanTransform.copyProperties(
                    entryBasicInfoAPI.listEntryBasicInfo(entryBasicInfoDTO), EntryBasicInfoVO.class, true);
            return ActResult.initialize(entryBasicInfoVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工入职
     *
     * @param entryBasicInfoTO 员工入职基本信息数据to
     * @des 添加员工入职
     * @return class EntryBasicInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEntryBasicInfo(@Validated(EntryBasicInfoTO.TestAdd.class) EntryBasicInfoTO entryBasicInfoTO) throws ActException {
        try {
            EntryBasicInfoBO entryBasicInfoBO1 = entryBasicInfoAPI.insertEntryBasicInfo(entryBasicInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(entryBasicInfoBO1,EntryBasicInfoVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑员工入职
     *
     * @param entryBasicInfoTO 员工入职基本信息数据bo
     * @des 编辑员工入职
     * @return class EntryBasicInfoVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editEntryBasicInfo(@Validated(EntryBasicInfoTO.TestAdd.class) EntryBasicInfoTO entryBasicInfoTO) throws ActException {
        try {
            EntryBasicInfoBO entryBasicInfoBO1 = entryBasicInfoAPI.editEntryBasicInfo(entryBasicInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(entryBasicInfoBO1,EntryBasicInfoVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除入职信息
     *
     * @param id 列表id
     * @des 根据列表id删除员工入职基本信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteEntryBasicInfo(@PathVariable String id) throws ActException {
        try {
            entryBasicInfoAPI.removeEntryBasicInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个入职信息
     *
     * @param id 员工入职基本信息id
     * @des 根据id查找某个员工入职基本信息
     * @return class EntryBasicInfoVO
     * @version v1
     */
    @GetMapping("v1/getEntryBasicInfo/{id}")
    public Result findOneEntryBasicInfo(@PathVariable String id) throws ActException {
        try {
            EntryBasicInfoVO entryBasicInfoVO = BeanTransform.copyProperties(
                    entryBasicInfoAPI.getEntryBasicInfo(id), EntryBasicInfoVO.class, true);
            return ActResult.initialize(entryBasicInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 入职通告邮件
     *
     * @param entryBasicInfoTO 员工入职基本信息bo 主要id 和 emails
     * @des 根据id emails发送入职通告邮件
     * @return class EntryBasicInfoVO
     * @version v1
     */
    @GetMapping("v1/sendEmailEntryBasicInfo")
    public Result emialEntryBasicInfo(@Validated EntryBasicInfoTO entryBasicInfoTO) throws ActException {
        try {
            EntryBasicInfoVO entryBasicInfoVO = BeanTransform.copyProperties(
                    entryBasicInfoAPI.sendEntryBasicInfo(entryBasicInfoTO), EntryBasicInfoVO.class, true);
            return ActResult.initialize(entryBasicInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总入职情况统计
     *
     * @param entryBasicInfoDTO 员工入职基本信息bo 主要position 和 entryTime
     * @des 根据岗位(position)、时间段(entryTime) 汇总入职情况统计
     * @return class EntryBasicInfoVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collectEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws ActException {
        try {
            List<EntryBasicInfoVO> entryBasicInfoVOList = BeanTransform.copyProperties(
                    entryBasicInfoAPI.collectEntryBasicInfo(entryBasicInfoDTO), EntryBasicInfoVO.class, true);
            return ActResult.initialize(entryBasicInfoVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总获取所有岗位
     *
     * @des 汇总获取所有岗位
     * @version v1
     */
    @GetMapping("v1/listPost")
    public Result listPost( ) throws ActException {
        try {
            List<String> entryBasicInfoVOList =  entryBasicInfoAPI.listPost( ) ;
            return ActResult.initialize(entryBasicInfoVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据姓名查找入职信息
     *
     * @param name name
     * @des 根据姓名查找入职信息
     * @return class EntryBasicInfoVO
     * @version v1
     */
    @GetMapping("v1/getInfoByName/{name}")
    public Result getInfoByName(@PathVariable String name) throws ActException {
        try {
            List<EntryBasicInfoVO> entryBasicInfoVO = BeanTransform.copyProperties(
                    entryBasicInfoAPI.getEntryBasicInfoByName(name), EntryBasicInfoVO.class, true);
            return ActResult.initialize(entryBasicInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @des 上传附件
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/staffentry/entrybasicinfo/" + id;
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
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/staffentry/entrybasicinfo/" + id;
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }




}
