package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.SettlementProcessAPI;
import com.bjike.goddess.projectprocing.bo.SettlementProcessBO;
import com.bjike.goddess.projectprocing.dto.SettlementProcessDTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.SettlementProcessTO;
import com.bjike.goddess.projectprocing.to.SiginManageDeleteFileTO;
import com.bjike.goddess.projectprocing.vo.SettlementProcessVO;
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
 * 结算流程存储记录
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("settlementprocess")
public class SettlementProcessAction extends BaseFileAction {
    @Autowired
    private SettlementProcessAPI settlementProcessAPI;
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

            Boolean isHasPermission = settlementProcessAPI.guidePermission(guidePermissionTO);
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
     * 结算流程存储记录总条数
     *
     * @param settlementProcessDTO 结算流程存储记录dto
     * @des 获取所有结算流程存储记录总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(SettlementProcessDTO settlementProcessDTO) throws ActException {
        try {
            Long count = settlementProcessAPI.countSetProcess(settlementProcessDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个结算流程存储记录
     *
     * @param id 结算流程存储记录id
     * @return class SettlementProcessVO
     * @des 根据id获取结算流程存储记录
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SettlementProcessVO settlementProcessVO = BeanTransform.copyProperties(
                    settlementProcessAPI.getOneById(id), SettlementProcessVO.class, true);
            return ActResult.initialize(settlementProcessVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 结算流程存储记录列表
     *
     * @param settlementProcessDTO 结算流程存储记录dto
     * @return class SettlementProcessVO
     * @des 获取所有结算流程存储记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(SettlementProcessDTO settlementProcessDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SettlementProcessVO> projectCarryVOList = BeanTransform.copyProperties(
                    settlementProcessAPI.listSetProcess(settlementProcessDTO), SettlementProcessVO.class, request);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加结算流程存储记录
     *
     * @param settlementProcessTO 结算流程存储记录数据to
     * @return class SettlementProcessVO
     * @des 添加结算流程存储记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) SettlementProcessTO settlementProcessTO, BindingResult bindingResult) throws ActException {
        try {
            SettlementProcessBO settlementProcessBO = settlementProcessAPI.addSetProcess(settlementProcessTO);
            return ActResult.initialize(BeanTransform.copyProperties(settlementProcessBO, SettlementProcessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑结算流程存储记录
     *
     * @param settlementProcessTO 结算流程存储记录数据bo
     * @return class SettlementProcessVO
     * @des 添加结算流程存储记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) SettlementProcessTO settlementProcessTO) throws ActException {
        try {
            SettlementProcessBO settlementProcessBO = settlementProcessAPI.editSetProcess(settlementProcessTO);
            return ActResult.initialize(BeanTransform.copyProperties(settlementProcessBO, SettlementProcessVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除结算流程存储记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            settlementProcessAPI.deleteSetProcess(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
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
            String path = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            settlementProcessAPI.updateSettProceAttach(id);
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
    /**
     * 获取所有的外包单位
     *
     * @des 获取所有的外包单位
     * @version v1
     */
    @GetMapping("v1/outUnit")
    public Result getOutUnit() throws ActException {
        try {
            List<String> outUnits = settlementProcessAPI.findOutUnit();
            return ActResult.initialize(outUnits);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}