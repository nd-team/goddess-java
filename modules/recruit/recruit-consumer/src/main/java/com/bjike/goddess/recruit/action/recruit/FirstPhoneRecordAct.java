package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.recruit.api.FirstPhoneRecordAPI;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.excel.FirstPhoneRecordExcel;
import com.bjike.goddess.recruit.to.DeleteFileTO;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.vo.FirstPhoneRecordVO;
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
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 15:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("firstPhoneRecord")
public class FirstPhoneRecordAct extends BaseFileAction {
    @Autowired
    private FirstPhoneRecordAPI firstPhoneRecordAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private MyWebSocket myWebSocket;

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

            Boolean isHasPermission = firstPhoneRecordAPI.guidePermission(guidePermissionTO);
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
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/recruit/firstPhoneRecord/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/recruit/firstPhoneRecord/" + id;
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
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
     * @param deleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DeleteFileTO.TestDEL.class) DeleteFileTO deleteFileTO, HttpServletRequest request) throws SerException {
        if (null != deleteFileTO.getPaths() && deleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), deleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 根据id查询第一次电访记录
     *
     * @param id 第一次电访记录唯一标识
     * @return class FirstPhoneRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/firstPhoneRecord/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FirstPhoneRecordBO bo = firstPhoneRecordAPI.findById(id);
            FirstPhoneRecordVO vo = BeanTransform.copyProperties(bo, FirstPhoneRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 第一次电访记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated FirstPhoneRecordDTO dto, BindingResult result) throws ActException {
        try {
            Long count = firstPhoneRecordAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 第一次电访记录dto
     * @return class FirstPhoneRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated FirstPhoneRecordDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<FirstPhoneRecordBO> boList = firstPhoneRecordAPI.list(dto);
            List<FirstPhoneRecordVO> voList = BeanTransform.copyProperties(boList, FirstPhoneRecordVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加第一次电访记录
     *
     * @param to 第一次电访记录to信息
     * @return class FirstPhoneRecordVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) FirstPhoneRecordTO to, BindingResult result, HttpServletRequest request) throws ActException, IOException, SerException {
        if (to.getWhetherFirstInterview()) {
            myWebSocket.sendMsg1("是否初试", "亲，有一份公司面试待安排哦：详情信息如下：\n" +
                    "“应聘地区”+“应聘部门”+“应聘者姓名”+“邀约初试时间”+“初试地点”");
        } else {
            myWebSocket.sendMsg1("fuck", "亲，虽然失去一位面试者，但是下一位会更棒的，加油！");
        }
        try {
            FirstPhoneRecordBO bo = firstPhoneRecordAPI.save(to);
            FirstPhoneRecordVO vo = BeanTransform.copyProperties(bo, FirstPhoneRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除第一次电访记录
     *
     * @param id 第一次电访记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            firstPhoneRecordAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑第一次电访记录
     *
     * @param to 第一次电访记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) FirstPhoneRecordTO to, BindingResult result) throws ActException {
        try {
            firstPhoneRecordAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<FirstPhoneRecordExcel> tos = ExcelUtil.excelToClazz(is, FirstPhoneRecordExcel.class, excel);
            List<FirstPhoneRecordTO> tocs = new ArrayList<>();
            for (FirstPhoneRecordExcel str : tos) {
                FirstPhoneRecordTO recordTO = BeanTransform.copyProperties(str, FirstPhoneRecordTO.class,
                        "whetherPass", "whetherPhoneSuccess", "whetherWorkExperience",
                        "whetherFirstInviteSuccess", "whetherFirstInterview", "whetherFaceTest",
                        "retrial");
                //简历筛选是否通过
                if (null != str.getWhetherPass()) {
                    if (str.getWhetherPass().equals("是")) {
                        recordTO.setWhetherPass(true);
                    } else {
                        recordTO.setWhetherPass(false);
                    }

                }
                //通话是否成功
                if (null != str.getWhetherPhoneSuccess()) {
                    if (str.getWhetherPhoneSuccess().equals("是")) {
                        recordTO.setWhetherPhoneSuccess(true);
                    } else {
                        recordTO.setWhetherPhoneSuccess(false);
                    }

                }
                //是否有相关工作经验
                if (null != str.getWhetherWorkExperience()) {
                    if (str.getWhetherWorkExperience().equals("是")) {
                        recordTO.setWhetherWorkExperience(true);
                    } else {
                        recordTO.setWhetherWorkExperience(false);
                    }
                }
                //是否成功邀约初试
                if (null != str.getWhetherFirstInviteSuccess()) {
                    if (str.getWhetherFirstInviteSuccess().equals("是")) {
                        recordTO.setWhetherFirstInviteSuccess(true);
                    } else {
                        recordTO.setWhetherFirstInviteSuccess(false);
                    }

                }
                //是否初试
                if (null != str.getWhetherFirstInterview()) {
                    if (str.getWhetherFirstInterview().equals("是")) {
                        recordTO.setWhetherFirstInterview(true);
                    } else {
                        recordTO.setWhetherFirstInterview(false);
                    }

                }
                //初试是否为面试
                if (null != str.getWhetherFaceTest()) {
                    if (str.getWhetherFaceTest().equals("是")) {
                        recordTO.setWhetherFaceTest(true);
                    } else {
                        recordTO.setWhetherFaceTest(false);
                    }

                }
                //是否需要复试
                if (null != str.getRetrial()) {
                    if (str.getRetrial().equals("是")) {
                        recordTO.setRetrial(true);
                    } else {
                        recordTO.setRetrial(false);
                    }

                }
                tocs.add(recordTO);
            }
            //注意序列化
            firstPhoneRecordAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @param dto 第一次电访记录
     * @des 导出第一次电访记录
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(FirstPhoneRecordDTO dto, HttpServletResponse response, BindingResult result) throws ActException {
        try {
            String fileName = "第一次电访记录.xlsx";
            super.writeOutFile(response, firstPhoneRecordAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板第一次电访记录
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "第一次电访记录导入模板.xlsx";
            super.writeOutFile(response, firstPhoneRecordAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}
