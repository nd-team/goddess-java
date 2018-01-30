package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.rentutilitiespay.api.RentPayAPI;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.excel.SonPermissionObject;
import com.bjike.goddess.rentutilitiespay.to.GuidePermissionTO;
import com.bjike.goddess.rentutilitiespay.to.RentDeleteFileTO;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;
import com.bjike.goddess.rentutilitiespay.vo.CollectAreaVO;
import com.bjike.goddess.rentutilitiespay.vo.RentPayVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.ISCII91;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 房租缴费
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:10 ]
 * @Description: [ 房租缴费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("rentpay")
public class RentPayAction extends BaseFileAction{
    @Autowired
    private RentPayAPI rentPayAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
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
            obj.setName("cuspermission");
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

            List<SonPermissionObject> hasPermissionList = rentPayAPI.sonPermission();
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

            Boolean isHasPermission = rentPayAPI.guidePermission(guidePermissionTO);
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
     * 房租缴费列表总条数
     *
     * @param rentPayDTO 房租缴费记录dto
     * @des 获取所有房租缴费
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RentPayDTO rentPayDTO) throws ActException {
        try {
            Long count = rentPayAPI.countRentPay(rentPayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个房租缴费
     *
     * @param id
     * @return class RentPayVO
     * @des 获取一个房租缴费
     * @version v1
     */
    @GetMapping("v1/rent/{id}")
    public Result rent(@PathVariable String id) throws ActException {
        try {
            RentPayBO rentPayBO = rentPayAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(rentPayBO, RentPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取房租缴费
     *
     * @param rentPayDTO 房租缴费dto
     * @return class RentPayVO
     * @des 获取所有房租缴费
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RentPayDTO rentPayDTO, HttpServletRequest request) throws ActException {
        try {
            List<RentPayVO> rentPayVOS = BeanTransform.copyProperties
                    (rentPayAPI.findListRentPay(rentPayDTO),RentPayVO.class,request);
            return ActResult.initialize(rentPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加房租缴费
     *
     * @param rentPayTO 房租缴费数据to
     * @return class RentPayVO
     * @des 添加房租缴费
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RentPayTO rentPayTO, BindingResult bindingResult) throws ActException {
        try {
            RentPayBO rentPayBO = rentPayAPI.insertRentPay(rentPayTO);
            return ActResult.initialize(BeanTransform.copyProperties(rentPayBO,RentPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑房租缴费
     *
     * @param rentPayTO 房租缴费数据to
     * @return class RentPayVO
     * @des 编辑房租缴费
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RentPayTO rentPayTO, BindingResult bindingResult) throws ActException {
        try {
            RentPayBO rentPayBO = rentPayAPI.editRentPay(rentPayTO);
            return ActResult.initialize(BeanTransform.copyProperties(rentPayBO,RentPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除房租缴费
     *
     * @param id 用户id
     * @des 根据用户id删除房租缴费记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            rentPayAPI.removeRentPay(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param areas 地区
     * @des 根据地区汇总
     * @return  class CollectAreaVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect ( @RequestParam String[] areas ) throws ActException {
        try {
            List<CollectAreaVO> collectAreaVOS = BeanTransform.copyProperties(
                    rentPayAPI.collectArea(areas),CollectAreaVO.class,true);
            return ActResult.initialize(collectAreaVOS);
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
            List<String> areaList = rentPayAPI.getArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 运营财务部
     *
     * @param rentPayTO 房租缴费数据to
     * @return class RentPayVO
     * @des 运营财务部
     * @version v1
     */
    @PostMapping("v1/financeAudit")
    public Result financeAudit(@Validated(RentPayTO.financeAudit.class) RentPayTO rentPayTO, BindingResult bindingResult) throws ActException {
        try {
            RentPayBO rentPayBO = rentPayAPI.financeAudit(rentPayTO);
            return ActResult.initialize(BeanTransform.copyProperties(rentPayBO,RentPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询地区
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findStatus(), DepartmentDetailVO.class, request));
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
     * @param id 项目执行中的问题受理id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /problemhandle/id/....
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
     * @param rentDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(RentDeleteFileTO.TestDEL.class) RentDeleteFileTO rentDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != rentDeleteFileTO.getPaths() && rentDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), rentDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }



}